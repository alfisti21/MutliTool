package com.ladopoulos.mutlitool;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.webkit.WebView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.tbruyelle.rxpermissions2.RxPermissions;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import org.jsoup.Jsoup;
import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {
    SharedPreferences myPrefs;

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        try {
            int size = new DownloadFilesTask().execute("https://www.eurail.com/en/get-inspired").get();
            myPrefs = getSharedPreferences("prefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = myPrefs.edit();
            editor.putString("DIVS", String.valueOf(size));
            editor.apply();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        WebView view = new WebView(getApplicationContext());
        view.loadUrl("https://www.eurail.com/en/get-inspired");

        //RxPermission used for permissions. Saves code
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions
                .request(Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION) // ask single or multiple permission once
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean granted) {
                    }
                });

        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_Map, R.id.navigation_Countdown, R.id.navigation_Storetext, R.id.navigation_Web)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    private static class DownloadFilesTask extends AsyncTask<String, String, Integer> {
        @RequiresApi(api = Build.VERSION_CODES.N)
        protected Integer doInBackground(String... url) {
            try {
                Document doc = Jsoup.connect(url[0])
                        .userAgent(HttpConnection.DEFAULT_UA)
                        .referrer("http://www.google.com")
                        .maxBodySize(0)
                        .timeout(12000)
                        .followRedirects(true)
                        .get();
                Elements divs = doc.getElementsByTag("div");
                return divs.size();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return 0;
        }
        protected void onPostExecute(String result) {}
    }
}

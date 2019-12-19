package com.ladopoulos.mutlitool.ui.storetext;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.ladopoulos.mutlitool.R;

import java.util.Objects;

public class StoretextFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_storetext, container, false);
        final TextView textView = root.findViewById(R.id.text_storetext);
        final EditText editText = root.findViewById(R.id.editText);
        final Button button = root.findViewById(R.id.button);
        final SharedPreferences myPrefs = Objects.requireNonNull(this.getActivity()).getSharedPreferences("prefs", Context.MODE_PRIVATE);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) Objects.requireNonNull(getActivity()).getSystemService(Context.INPUT_METHOD_SERVICE);
                Objects.requireNonNull(imm).hideSoftInputFromWindow(root.getWindowToken(), 0);
                SharedPreferences.Editor editor = myPrefs.edit();
                editor.putString("STRING", editText.getText().toString());
                editor.apply();
                textView.setText(myPrefs.getString("STRING", null));
                editText.getText().clear();
            }
        });
        textView.setText(myPrefs.getString("STRING", null));
        return root;
    }
}
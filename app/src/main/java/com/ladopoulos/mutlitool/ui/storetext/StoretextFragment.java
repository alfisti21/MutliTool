package com.ladopoulos.mutlitool.ui.storetext;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.ladopoulos.mutlitool.R;

import java.util.Objects;

public class StoretextFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_storetext, container, false);
        final TextView textView = root.findViewById(R.id.text_storetext);
        final EditText editText = root.findViewById(R.id.editText);
        final Button button = root.findViewById(R.id.button);
        final SharedPreferences myPrefs = Objects.requireNonNull(this.getActivity()).getSharedPreferences("prefs", Context.MODE_PRIVATE);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SharedPreferences.Editor editor = myPrefs.edit();
                editor.putString("STRING", editText.getText().toString());
                editor.apply();
                textView.setText(myPrefs.getString("STRING", null));
            }
        });
        textView.setText(myPrefs.getString("STRING", null));
        return root;
    }
}
package com.ladopoulos.mutlitool.ui.web;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.ladopoulos.mutlitool.R;

public class WebFragment extends Fragment {

    private WebViewModel webViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        webViewModel =
                ViewModelProviders.of(this).get(WebViewModel.class);
        View root = inflater.inflate(R.layout.fragment_web, container, false);
        final TextView textView = root.findViewById(R.id.text_web);
        webViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
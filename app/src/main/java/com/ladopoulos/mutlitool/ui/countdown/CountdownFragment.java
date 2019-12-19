package com.ladopoulos.mutlitool.ui.countdown;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.ladopoulos.mutlitool.R;

public class CountdownFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        CountdownViewModel countdownViewModel = ViewModelProviders.of(this).get(CountdownViewModel.class);
        View root = inflater.inflate(R.layout.fragment_countdown, container, false);
        final TextView textView = root.findViewById(R.id.text_countdown);
        final ProgressBar bar = root.findViewById(R.id.progressBar);
        bar.setProgress(0);
        countdownViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        countdownViewModel.loaded().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer isLoading) {
                bar.setProgress(isLoading);
            }
        });
        return root;
    }
}
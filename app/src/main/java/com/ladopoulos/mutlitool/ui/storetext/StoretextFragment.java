package com.ladopoulos.mutlitool.ui.storetext;

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

public class StoretextFragment extends Fragment {

    private StoretextViewModel storetextViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        storetextViewModel =
                ViewModelProviders.of(this).get(StoretextViewModel.class);
        View root = inflater.inflate(R.layout.fragment_storetext, container, false);
        final TextView textView = root.findViewById(R.id.text_storetext);
        storetextViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
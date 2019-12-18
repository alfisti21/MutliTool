package com.ladopoulos.mutlitool.ui.storetext;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class StoretextViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public StoretextViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
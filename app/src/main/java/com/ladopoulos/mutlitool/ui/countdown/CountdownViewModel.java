package com.ladopoulos.mutlitool.ui.countdown;

import android.os.CountDownTimer;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CountdownViewModel extends ViewModel {
    private int unicode = 0x1F680;
    private String emoji;

    private MutableLiveData<String> mText;
    private MutableLiveData<Boolean> isLoading;

    public CountdownViewModel() {

        mText = new MutableLiveData<>();
        isLoading = new MutableLiveData<>();
        isLoading.setValue(false);
        new CountDownTimer(5000, 100) {
            public void onTick(long millisUntilFinished) {
                mText.setValue("Rocket launch in: \n" + ((millisUntilFinished / 500)+1));
            }

            public void onFinish() {
                emoji = getEmojiByUnicode(unicode);
                mText.setValue("Lift off!"+ emoji);
                isLoading.setValue(true);
            }
        }.start();
    }

    LiveData<String> getText() {
        return mText;
    }
    LiveData<Boolean> loaded() {
        return isLoading;
    }
    private String getEmojiByUnicode(int unicode){
        return new String(Character.toChars(unicode));
    }
    private void downloadFinished() {
        isLoading.setValue(true);
    }
}
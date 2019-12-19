package com.ladopoulos.mutlitool.ui.countdown;

import android.os.CountDownTimer;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CountdownViewModel extends ViewModel {
    private int unicode = 0x1F680;
    private String emoji;
    private MutableLiveData<String> mText;
    private MutableLiveData<Integer> isLoading;
    int i=0;

    public CountdownViewModel() {

        mText = new MutableLiveData<>();
        isLoading = new MutableLiveData<>();
        isLoading.setValue(0);
        new CountDownTimer(5000, 100) {
            public void onTick(long millisUntilFinished) {
                i++;
                mText.setValue("Rocket launch in: \n" + ((millisUntilFinished / 500)+1));
                isLoading.setValue(i*100/(5000/100));
            }

            public void onFinish() {
                emoji = getEmojiByUnicode(unicode);
                mText.setValue("Lift off!"+ emoji);
                isLoading.setValue(100);
            }
        }.start();
    }

    LiveData<String> getText() {
        return mText;
    }
    LiveData<Integer> loaded() {
        return isLoading;
    }
    private String getEmojiByUnicode(int unicode){
        return new String(Character.toChars(unicode));
    }
    private void downloadFinished() {
        isLoading.setValue(100);
    }
}
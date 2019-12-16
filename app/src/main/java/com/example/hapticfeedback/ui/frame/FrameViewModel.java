package com.example.hapticfeedback.ui.frame;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FrameViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public FrameViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("You can create a Frame here");
    }



    public LiveData<String> getText() {
        return mText;
    }
}
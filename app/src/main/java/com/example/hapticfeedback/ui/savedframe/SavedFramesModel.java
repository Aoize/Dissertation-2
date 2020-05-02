package com.example.hapticfeedback.ui.savedframe;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SavedFramesModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SavedFramesModel() {
        mText = new MutableLiveData<>();
        mText.setValue("View your Saved Frames here.");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
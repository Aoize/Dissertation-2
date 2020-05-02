package com.example.hapticfeedback.ui.savedsequence;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SavedSequenceModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SavedSequenceModel() {
        mText = new MutableLiveData<>();
        mText.setValue("View your Saved Sequences here");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
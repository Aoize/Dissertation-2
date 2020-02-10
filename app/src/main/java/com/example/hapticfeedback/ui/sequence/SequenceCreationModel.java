package com.example.hapticfeedback.ui.sequence;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SequenceCreationModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SequenceCreationModel() {
        mText = new MutableLiveData<>();
        mText.setValue("You can create a Sequence here");
}

    public LiveData<String> getText() {
        return mText;
    }
}
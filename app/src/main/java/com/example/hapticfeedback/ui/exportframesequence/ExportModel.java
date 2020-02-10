package com.example.hapticfeedback.ui.exportframesequence;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ExportModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ExportModel() {
        mText = new MutableLiveData<>();
        mText.setValue("You can Export your Frames or Sequences here");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
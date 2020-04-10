package com.example.hapticfeedback.ui.sequence;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;

import com.example.hapticfeedback.R;

public class Select_Frames_Help extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_frames_helper);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .9), (int) (height * .65));

    }
}

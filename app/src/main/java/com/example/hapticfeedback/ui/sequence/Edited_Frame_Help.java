package com.example.hapticfeedback.ui.sequence;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;

import com.example.hapticfeedback.R;

public class Edited_Frame_Help extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edited_frame_helper);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .9), (int) (height * .35));

    }

}

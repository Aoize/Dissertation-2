package com.example.hapticfeedback.ui.frame;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;

import com.example.hapticfeedback.R;

public class Pin_Help extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pin_helper);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .9), (int) (height * .55));

    }
}

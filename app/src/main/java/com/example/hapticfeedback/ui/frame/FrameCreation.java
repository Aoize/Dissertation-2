package com.example.hapticfeedback.ui.frame;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.hapticfeedback.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class FrameCreation extends Fragment {

    private FrameCreationModel frameViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        frameViewModel =
                ViewModelProviders.of(this).get(FrameCreationModel.class);
        View root = inflater.inflate(R.layout.frame_creation, container, false);
        final TextView textView = root.findViewById(R.id.frame_creation);
        frameViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
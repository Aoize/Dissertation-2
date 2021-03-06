package com.example.hapticfeedback.ui.sequence;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.hapticfeedback.R;

public class SequenceCreation extends Fragment {

    private SequenceCreationModel sequenceViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        sequenceViewModel =
                ViewModelProviders.of(this).get(SequenceCreationModel.class);
        View root = inflater.inflate(R.layout.sequence_creation, container, false);
        final TextView textView = root.findViewById(R.id.sequence_creation);
        sequenceViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
package com.fknussel.challengeo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

public class AnswerFragment extends Fragment {

    /*
     * Static factory method that takes an int parameter, initializes
     * the fragment's arguments, and returns the new fragment to the client.
     */
    public static AnswerFragment newInstance(boolean correct) {
        AnswerFragment f = new AnswerFragment();
        Bundle args = new Bundle();
        args.putBoolean("correct", correct);
        f.setArguments(args);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        int layout;
        boolean correct = getArguments().getBoolean("correct");

        // Hide action bar
        ((ChallengeActivity)getActivity()).getSupportActionBar().hide();

        // Use appropriate layout
        if (correct) {
            layout = R.layout.answer_correct;
        } else {
            layout = R.layout.answer_error;
        }

        return inflater.inflate(layout, container, false);
    }
}

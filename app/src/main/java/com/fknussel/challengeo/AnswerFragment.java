package com.fknussel.challengeo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

public class AnswerFragment extends Fragment {

    /*
     * Static factory method that takes an boolean parameter, initializes
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

        View v = inflater.inflate(layout, container, false);

        final FragmentManager fm = getFragmentManager();

        // Try again button listener
        if (v.findViewById(R.id.try_again) != null) {
            Button tryAgainBtn = (Button) v.findViewById(R.id.try_again);
            tryAgainBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Go back to the previous fragment
                    fm.popBackStack();
                }
            });
        }

        // Next question button listener
        Button nextQuestionBtn = (Button) v.findViewById(R.id.next_question);
        nextQuestionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Take the user to the next question
                fm.beginTransaction()
                        .replace(R.id.container, new ChallengeFragment())
                        .commit();
            }
        });

        return v;
    }
}

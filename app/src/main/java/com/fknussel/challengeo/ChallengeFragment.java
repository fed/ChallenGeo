package com.fknussel.challengeo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Random;

public class ChallengeFragment extends Fragment {

    private static String TAG = ChallengeFragment.class.getSimpleName();

    public ChallengeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_challenge, container, false);

        ImageView flagView = (ImageView) rootView.findViewById(R.id.challenge_flag);
        RadioButton option1 = (RadioButton) rootView.findViewById(R.id.challenge_option1);
        RadioButton option2 = (RadioButton) rootView.findViewById(R.id.challenge_option2);
        RadioButton option3 = (RadioButton) rootView.findViewById(R.id.challenge_option3);
        RadioButton option4 = (RadioButton) rootView.findViewById(R.id.challenge_option4);

        int numOptions = 4;
        int size = AppHelper.listNames.size();
        int min = 0;
        int max = size - 1;
        int random;
        Random rand;
        String randomName;
        String randomCode;
        boolean truthValue;
        boolean CORRECT_ANSWER_ALREADY_SET = false;
        int correctOptionIndex = 0;

        final ArrayList<Answer> options = new ArrayList<>();

        // Make sure u can't get the same country twice
        for (int i=0; i < numOptions; i++) {

            rand = new Random();
            random = rand.nextInt((max - min) + 1) + min;
            randomName = AppHelper.listNames.get(random);
            randomCode = AppHelper.mapCodes.get(randomName);

            if (CORRECT_ANSWER_ALREADY_SET) {
                truthValue = false;
            } else {
                // Force true answer on last iteration
                if (i == (numOptions - 1)) {
                    truthValue = true;
                    CORRECT_ANSWER_ALREADY_SET = true;
                    correctOptionIndex = i;
                } else {
                    truthValue = rand.nextBoolean();
                    if (truthValue) {
                        CORRECT_ANSWER_ALREADY_SET = true;
                        correctOptionIndex = i;
                    }
                }
            }

            options.add(new Answer(randomName, randomCode, truthValue));
        }

        // Display flag on screen
        String flagFilename = AppHelper.FLAG_BASE_URL + options.get(correctOptionIndex).getCountryCode().toLowerCase() + AppHelper.FLAG_DEFAULT_EXTENSION;
        Picasso.with(getActivity())
                .load(flagFilename)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.broken_link)
                .into(flagView);

        option1.setText(options.get(0).getCountryName());
        option2.setText(options.get(1).getCountryName());
        option3.setText(options.get(2).getCountryName());
        option4.setText(options.get(3).getCountryName());

        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (options.get(0).isCorrectAnswer()) {
                    Toast.makeText(getActivity(), "GOOD JOB :)", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "TRY AGAIN :(", Toast.LENGTH_SHORT).show();
                }
            }
        });

        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (options.get(1).isCorrectAnswer()) {
                    Toast.makeText(getActivity(), "GOOD JOB :)", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "TRY AGAIN :(", Toast.LENGTH_SHORT).show();
                }
            }
        });

        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (options.get(2).isCorrectAnswer()) {
                    Toast.makeText(getActivity(), "GOOD JOB :)", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "TRY AGAIN :(", Toast.LENGTH_SHORT).show();
                }
            }
        });

        option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (options.get(3).isCorrectAnswer()) {
                    Toast.makeText(getActivity(), "GOOD JOB :)", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "TRY AGAIN :(", Toast.LENGTH_SHORT).show();
                }
            }
        });
        
        return rootView;
    }
}

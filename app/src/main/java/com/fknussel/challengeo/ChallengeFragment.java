package com.fknussel.challengeo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
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

    private ArrayList<Answer> options;
    private int correctOptionIndex;

    public static ChallengeFragment newInstance() {
        return new ChallengeFragment();
    }

    public static ChallengeFragment newInstance(ArrayList<Answer> options, int correctOptionIndex) {
        ChallengeFragment f = new ChallengeFragment();
        Bundle args = new Bundle();
        args.putSerializable("options", options);
        args.putInt("correctOptionIndex", correctOptionIndex);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle arguments = getArguments();

        if (arguments != null) {
            // Try again button got pressed
            this.options = (ArrayList<Answer>) getArguments().getSerializable("options");
            this.correctOptionIndex = getArguments().getInt("correctOptionIndex");
        } else if (savedInstanceState != null) {
            // Handle screen rotations
            this.options = (ArrayList<Answer>) savedInstanceState.getSerializable("options");
            this.correctOptionIndex = savedInstanceState.getInt("correctOptionIndex");
        } else {
            // Generate random country here, otherwise the "try again"
            // button fails to load the same flag twice
            generateOptions();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Action bar might be hidden
        ((ChallengeActivity)getActivity()).getSupportActionBar().show();

        // Inflate view
        View rootView = inflater.inflate(R.layout.fragment_challenge, container, false);

        ImageView flagView = (ImageView) rootView.findViewById(R.id.challenge_flag);
        RadioButton option1 = (RadioButton) rootView.findViewById(R.id.challenge_option1);
        RadioButton option2 = (RadioButton) rootView.findViewById(R.id.challenge_option2);
        RadioButton option3 = (RadioButton) rootView.findViewById(R.id.challenge_option3);
        RadioButton option4 = (RadioButton) rootView.findViewById(R.id.challenge_option4);

        // Set title and subtitle
        getActivity().setTitle(R.string.challenge_accepted);
        ((ChallengeActivity)getActivity()).getSupportActionBar().setSubtitle("What's this flag?");

        // Display flag on screen
        String flagFilename = AppHelper.FLAG_BASE_URL + this.options.get(this.correctOptionIndex).getCountryCode().toLowerCase() + AppHelper.FLAG_DEFAULT_EXTENSION;
        Picasso.with(getActivity())
                .load(flagFilename)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.broken_link)
                .into(flagView);

        // Add labels to radio buttons
        option1.setText(this.options.get(0).getCountryName());
        option2.setText(this.options.get(1).getCountryName());
        option3.setText(this.options.get(2).getCountryName());
        option4.setText(this.options.get(3).getCountryName());

        // Attach listeners to all radio buttons
        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayResult(0);
            }
        });

        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayResult(1);
            }
        });

        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayResult(2);
            }
        });

        option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayResult(3);
            }
        });
        
        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable("options", this.options);
        outState.putInt("correctOptionIndex", this.correctOptionIndex);
        super.onSaveInstanceState(outState);
    }

    private void displayResult(int index) {
        boolean isCorrect = this.options.get(index).isCorrectAnswer();

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.container,
                AnswerFragment.newInstance(isCorrect, this.options, this.correctOptionIndex));
        ft.commit();
    }

    private void generateOptions() {
        this.options = new ArrayList<>();
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

        // TO DO: Make sure u can't get the same country twice
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
                    this.correctOptionIndex = i;
                } else {
                    truthValue = rand.nextBoolean();
                    if (truthValue) {
                        CORRECT_ANSWER_ALREADY_SET = true;
                        this.correctOptionIndex = i;
                    }
                }
            }

            this.options.add(new Answer(randomName, randomCode, truthValue));
        }
    }
}

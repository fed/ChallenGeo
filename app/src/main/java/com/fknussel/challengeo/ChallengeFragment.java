package com.fknussel.challengeo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.squareup.picasso.Picasso;

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
        boolean THIS_IS_THE_CORRECT_ANSWER = true;

        // Make sure u can't get the same country twice
        for (int i=1; i <= numOptions; i++) {

            rand = new Random();
            random = rand.nextInt((max - min) + 1) + min;
            randomName = AppHelper.listNames.get(random);
            randomCode = AppHelper.mapCodes.get(randomName);
            
            if (THIS_IS_THE_CORRECT_ANSWER) {

                String flagFilename = AppHelper.FLAG_BASE_URL + randomCode.toLowerCase() + AppHelper.FLAG_DEFAULT_EXTENSION;
                Picasso.with(getActivity())
                        .load(flagFilename)
                        .placeholder(R.drawable.placeholder)
                        .error(R.drawable.broken_link)
                        .into(flagView);
                
                THIS_IS_THE_CORRECT_ANSWER = false;
            }
            
            switch (i) {
                case 1:
                    option1.setText(randomName);
                    break;
                case 2:
                    option2.setText(randomName);
                    break;
                case 3:
                    option3.setText(randomName);
                    break;
                case 4:
                    option4.setText(randomName);
                    break;
            }
        }
 
        return rootView;
    }
}

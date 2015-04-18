package com.fknussel.challengeo.activities;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.fknussel.challengeo.fragments.ChallengeFragment;
import com.fknussel.challengeo.R;
import com.fknussel.challengeo.utils.AppHelper;


public class ChallengeActivity extends ActionBarActivity {

    private int streak;
    private int high;
    private int wrongs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
        
        setContentView(R.layout.activity_challenge);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.challenge_accepted);
        actionBar.setSubtitle("What's this flag?");

        if (savedInstanceState == null) {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            this.streak = preferences.getInt("streak", 0);
            this.high = preferences.getInt("high", 0);
            this.wrongs = preferences.getInt("wrongs", 0);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new ChallengeFragment())
                    .commit();
        } else {
            this.streak = savedInstanceState.getInt("streak");
            this.high = savedInstanceState.getInt("high");
            this.wrongs = savedInstanceState.getInt("wrongs");
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("streak", this.streak);
        outState.putInt("high", this.high);
        outState.putInt("wrongs", this.wrongs);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_challenge, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_report:
                // Created a new Dialog
                final Dialog dialog = new Dialog(this);

                // Set the title
                dialog.setTitle("Report Question");

                // inflate the layout
                dialog.setContentView(R.layout.dialog_report);

                final TextView comments = (TextView) dialog.findViewById(R.id.report_comments);
                Button submit = (Button)dialog.findViewById(R.id.report_submit);
                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

                // Display the dialog
                dialog.show();

                break;
            
            case R.id.action_reset:

                //Ask the user whether they really want to reset the score
                final ChallengeActivity context = this;
                new AlertDialog.Builder(this)
                        .setTitle("Are you sure?")
                        .setMessage("You are about to delete your score... Shall I proceed?")
                        .setPositiveButton("Yes, please", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Reset score (delete shared prefs)
                                SharedPreferences score = PreferenceManager.getDefaultSharedPreferences(context);
                                score.edit().clear().apply();
                                context.resetScore();
                                ChallengeFragment fragment = (ChallengeFragment)getSupportFragmentManager().findFragmentById(R.id.container);
                                fragment.resetScore();
                                Toast.makeText(getApplicationContext(), "Score was successfully reset :)", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
    }

    public int getStreak() {
        return this.streak;
    }

    public int getHigh() {
        return this.high;
    }

    public int getWrongs() {
        return this.wrongs;
    }

    public void persistScore() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("streak", this.streak);
        editor.putInt("high", this.high);
        editor.putInt("wrongs", this.wrongs);
        editor.apply();
    }

    public void keepScore(boolean correct) {
        if (correct) {
            this.streak++;      // increment rights

            // is new highest score?
            if (this.streak > this.high) {
                this.high = this.streak;
            }
        } else {
            this.wrongs++;      // increment wrongs
            this.streak = 0;    // reset streak
        }

        this.persistScore();
    }

    public void resetScore() {
        this.streak = 0;
        this.high = 0;
        this.wrongs = 0;
    }
}

package com.marianavalentinamitroi.ssoftscorestatistics;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class ScoreAndStatistics extends AppCompatActivity {
    MainViewModel viewModel = new MainViewModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_and_statistics);

        // set the up button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // find the textViews for all the teams
        TextView teamOneV = findViewById(R.id.textView2);
        TextView teamOneH = findViewById(R.id.textView5);
        TextView teamOneB = findViewById(R.id.textView18);

        TextView teamTwoV = findViewById(R.id.textView3);
        TextView teamTwoH = findViewById(R.id.textView6);
        TextView teamTwoB = findViewById(R.id.textView19);

        TextView teamThreeV = findViewById(R.id.textView4);
        TextView teamThreeH = findViewById(R.id.textView7);
        TextView teamThreeB = findViewById(R.id.textView20);

        // find the views for all the scores
        TextView t1t2 = findViewById(R.id.textView9);
        TextView t1t3 = findViewById(R.id.textView10);
        TextView t2t1 = findViewById(R.id.textView11);
        TextView t2t3 = findViewById(R.id.textView13);
        TextView t3t1 = findViewById(R.id.textView14);
        TextView t3t2 = findViewById(R.id.textView15);

        // get the intent
        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        // put the info from the bundle in an ArrayList<TeamsViewModel>
        ArrayList<TeamsViewModel> arrTeam = (ArrayList<TeamsViewModel>) args.getSerializable("ARRAYLIST");

        int i = 0;
        assert arrTeam != null;
        if (arrTeam.size() > i + 1) {
            //we compare the names of the teams so we can not add 4 teams to the table
            if (!arrTeam.get(i).getTeamOneName().equals(arrTeam.get(i + 1).getTeamOneName()) &&
                    !arrTeam.get(i).getTeamOneName().equals(arrTeam.get(i + 1).getTeamTwoName())) {
                if (!arrTeam.get(i).getTeamTwoName().equals(arrTeam.get(i + 1).getTeamOneName()) &&
                        !arrTeam.get(i).getTeamTwoName().equals(arrTeam.get(i + 1).getTeamTwoName())) {
                    Toast.makeText(this, "You cannot add 4 teams ", Toast.LENGTH_LONG).show();
                    viewModel.clearPreferences(this);
                } else {
                    // add the teams to the table
                    if (arrTeam.get(i).getTeamTwoName().equals(arrTeam.get(i + 1).getTeamOneName())) {
                        teamOneV.setText(arrTeam.get(i).getTeamOneName());
                        teamOneH.setText(arrTeam.get(i).getTeamOneName());
                        teamOneB.setText(arrTeam.get(i).getTeamOneName());

                        teamTwoV.setText(arrTeam.get(i).getTeamTwoName());
                        teamTwoH.setText(arrTeam.get(i).getTeamTwoName());
                        teamTwoB.setText(arrTeam.get(i).getTeamTwoName());

                        teamThreeV.setText(arrTeam.get(i + 1).getTeamTwoName());
                        teamThreeH.setText(arrTeam.get(i + 1).getTeamTwoName());
                        teamThreeB.setText(arrTeam.get(i + 1).getTeamTwoName());

                        // add the score to the table
                        t1t2.setText(new StringBuilder(arrTeam.get(i).getTeamOneScore()).append("-").append(arrTeam.get(i).getTeamTwoScore()));
                        t1t3.setText(new StringBuilder(arrTeam.get(i).getTeamOneScore()).append("-").append(arrTeam.get(i + 1).getTeamTwoScore()));
                        t2t1.setText(new StringBuilder(arrTeam.get(i).getTeamTwoScore()).append("-").append(arrTeam.get(i).getTeamOneScore()));
                        t2t3.setText(new StringBuilder(arrTeam.get(i).getTeamTwoScore()).append("-").append(arrTeam.get(i + 1).getTeamTwoScore()));
                        t3t1.setText(new StringBuilder(arrTeam.get(i + 1).getTeamTwoScore()).append("-").append(arrTeam.get(i).getTeamTwoScore()));
                        t3t2.setText(new StringBuilder(arrTeam.get(i + 1).getTeamTwoScore()).append("-").append(arrTeam.get(i).getTeamTwoScore()));
                    } else {
                        // add the teams to the table
                        teamOneV.setText(arrTeam.get(i).getTeamOneName());
                        teamOneH.setText(arrTeam.get(i).getTeamOneName());
                        teamOneB.setText(arrTeam.get(i).getTeamOneName());

                        teamTwoV.setText(arrTeam.get(i).getTeamTwoName());
                        teamTwoH.setText(arrTeam.get(i).getTeamTwoName());
                        teamTwoB.setText(arrTeam.get(i).getTeamTwoName());

                        teamThreeV.setText(arrTeam.get(i + 1).getTeamOneName());
                        teamThreeH.setText(arrTeam.get(i + 1).getTeamOneName());
                        teamThreeB.setText(arrTeam.get(i + 1).getTeamOneName());

                        // add the score to the table
                        t1t2.setText(new StringBuilder(arrTeam.get(i).getTeamOneScore()).append("-").append(arrTeam.get(i).getTeamTwoScore()));
                        t1t3.setText(new StringBuilder(arrTeam.get(i).getTeamOneScore()).append("-").append(arrTeam.get(i + 1).getTeamOneScore()));
                        t2t1.setText(new StringBuilder(arrTeam.get(i).getTeamTwoScore()).append("-").append(arrTeam.get(i).getTeamOneScore()));
                        t2t3.setText(new StringBuilder(arrTeam.get(i).getTeamTwoScore()).append("-").append(arrTeam.get(i + 1).getTeamOneScore()));
                        t3t1.setText(new StringBuilder(arrTeam.get(i + 1).getTeamOneScore()).append("-").append(arrTeam.get(i).getTeamTwoScore()));
                        t3t2.setText(new StringBuilder(arrTeam.get(i + 1).getTeamOneScore()).append("-").append(arrTeam.get(i).getTeamOneScore()));
                    }
                }
            } else {
                // add the teams to the table
                if (arrTeam.get(i).getTeamOneName().equals(arrTeam.get(i + 1).getTeamTwoName())) {
                    teamOneV.setText(arrTeam.get(i).getTeamTwoName());
                    teamOneH.setText(arrTeam.get(i).getTeamTwoName());
                    teamOneB.setText(arrTeam.get(i).getTeamTwoName());

                    teamTwoV.setText(arrTeam.get(i + 1).getTeamOneName());
                    teamTwoH.setText(arrTeam.get(i + 1).getTeamOneName());
                    teamTwoB.setText(arrTeam.get(i + 1).getTeamOneName());

                    teamThreeV.setText(arrTeam.get(i + 1).getTeamTwoName());
                    teamThreeH.setText(arrTeam.get(i + 1).getTeamTwoName());
                    teamThreeB.setText(arrTeam.get(i + 1).getTeamTwoName());

                    // add the score to the table
                    t1t2.setText(new StringBuilder(arrTeam.get(i).getTeamTwoScore()).append("-").append(arrTeam.get(i + 1).getTeamOneScore()));
                    t1t3.setText(new StringBuilder(arrTeam.get(i).getTeamOneScore()).append("-").append(arrTeam.get(i + 1).getTeamTwoScore()));
                    t2t1.setText(new StringBuilder(arrTeam.get(i + 1).getTeamOneScore()).append("-").append(arrTeam.get(i).getTeamTwoScore()));
                    t2t3.setText(new StringBuilder(arrTeam.get(i + 1).getTeamOneScore()).append("-").append(arrTeam.get(i + 1).getTeamTwoScore()));
                    t3t1.setText(new StringBuilder(arrTeam.get(i + 1).getTeamTwoScore()).append("-").append(arrTeam.get(i).getTeamTwoScore()));
                    t3t2.setText(new StringBuilder(arrTeam.get(i + 1).getTeamTwoScore()).append("-").append(arrTeam.get(i + 1).getTeamOneScore()));

                } else {
                    // add the teams to the table
                    teamOneV.setText(arrTeam.get(i).getTeamOneName());
                    teamOneH.setText(arrTeam.get(i).getTeamOneName());
                    teamOneB.setText(arrTeam.get(i).getTeamOneName());

                    teamTwoV.setText(arrTeam.get(i + 1).getTeamOneName());
                    teamTwoH.setText(arrTeam.get(i + 1).getTeamOneName());
                    teamTwoB.setText(arrTeam.get(i + 1).getTeamOneName());

                    teamThreeV.setText(arrTeam.get(i + 1).getTeamTwoName());
                    teamThreeH.setText(arrTeam.get(i + 1).getTeamTwoName());
                    teamThreeB.setText(arrTeam.get(i + 1).getTeamTwoName());

                    // add the score to the table
                    t1t2.setText(new StringBuilder(arrTeam.get(i).getTeamOneScore()).append("-").append(arrTeam.get(i + 1).getTeamOneScore()));
                    t1t3.setText(new StringBuilder(arrTeam.get(i).getTeamOneScore()).append("-").append(arrTeam.get(i + 1).getTeamTwoScore()));
                    t2t1.setText(new StringBuilder(arrTeam.get(i + 1).getTeamOneScore()).append("-").append(arrTeam.get(i).getTeamOneScore()));
                    t2t3.setText(new StringBuilder(arrTeam.get(i + 1).getTeamOneScore()).append("-").append(arrTeam.get(i + 1).getTeamTwoScore()));
                    t3t1.setText(new StringBuilder(arrTeam.get(i + 1).getTeamTwoScore()).append("-").append(arrTeam.get(i).getTeamTwoScore()));
                    t3t2.setText(new StringBuilder(arrTeam.get(i + 1).getTeamTwoScore()).append("-").append(arrTeam.get(i + 1).getTeamOneScore()));
                }
            }
        } else {
            Toast.makeText(this, "You need to add data", Toast.LENGTH_LONG).show();
        }
    }
}


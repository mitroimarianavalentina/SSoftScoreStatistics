package com.marianavalentinamitroi.ssoftscorestatistics;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    MainViewModel viewModel;
    static ArrayList<TeamsViewModel> arrTeam = new ArrayList<>();

    // create the alert Dialog for adding the teams to shredPreferences*/
    private static AlertDialog getAddTeamDialog(final WeakReference<MainActivity> weakActivity) {
        @SuppressLint("InflateParams")
        View mView = weakActivity.get().getLayoutInflater().inflate(R.layout.activity_add_team, null);

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(weakActivity.get());
        mBuilder.setView(mView);
        final AlertDialog dialog = mBuilder.create();

        // find the edit Text from the add team alert Dialog */
        final EditText teamName = mView.findViewById(R.id.team_name_edit_text);

        // find the Button from the add team alert Dialog */
        Button saveTeam = mView.findViewById(R.id.save_team);

        // set the listener to saveTeam Button in order to save the team to shared Preferences */
        saveTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // id we can add a new team to shared Preferences, addTeam returns true */
                if (addTeam(weakActivity, teamName.getText().toString().trim())) {
                    // the alert dialog should dissapear */
                    dialog.dismiss();
                }
            }
        });
        return dialog;
    }

    // create the alert Dialog for adding the score to shredPreferences*/
    private static AlertDialog getAddScoreDialog(final WeakReference<MainActivity> weakActivity) {
        @SuppressLint("InflateParams")
        // inflate to the alert Dialog the apropriate layout */
                View mView = weakActivity.get().getLayoutInflater().inflate(R.layout.activity_add_score, null);

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(weakActivity.get());
        mBuilder.setView(mView);
        final AlertDialog dialog = mBuilder.create();

        // find the two spinners */
        final Spinner teamOneSpinner = mView.findViewById(R.id.team1);
        final Spinner teamTwoSpinner = mView.findViewById(R.id.team2);

        // set the ArrayList of teams to the teamOneSpinner */
        ArrayAdapter<String> teamOneAdapter = new ArrayAdapter<>(weakActivity.get(), android.R.layout.simple_spinner_item, weakActivity.get().viewModel.getTeamList(weakActivity.get()));
        teamOneAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        teamOneSpinner.setAdapter(teamOneAdapter);

        // set the ArrayList of teams to the teamTwoSpinner */
        ArrayAdapter<String> teamTwoAdapter = new ArrayAdapter<>(weakActivity.get(), android.R.layout.simple_spinner_item, weakActivity.get().viewModel.getTeamList(weakActivity.get()));
        teamTwoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        teamTwoSpinner.setAdapter(teamTwoAdapter);

        // find the edit Texts for teams one score
        final EditText teamOneScore = mView.findViewById(R.id.team1_score);
        final EditText teamTwoScore = mView.findViewById(R.id.team2_score);

        // find the Button from the add team alert Dialog */
        Button saveScore = mView.findViewById(R.id.save_score);

        // set the listener to saveScore Button in order to save the score to shared Preferences */
        saveScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // save the teams selected from the spinner */
                String selectedT1 = teamOneSpinnerSelected(teamOneSpinner);
                String selectedT2 = teamTwoSpinnerSelected(teamTwoSpinner);

                if (!selectedT1.equals(selectedT2)) {
                    boolean resultAddTeamOneScore = addTeamOneScore(weakActivity, teamOneScore.getText().toString().trim(), teamTwoScore.getText().toString().trim());
                    boolean resultAddTeamTwoScore = addTeamTwoScore(weakActivity, teamTwoScore.getText().toString().trim(), teamOneScore.getText().toString().trim());
                    if (resultAddTeamOneScore && resultAddTeamTwoScore) {
                        dialog.dismiss();
                    }
                    // add to the ArrayLists the team selected and it's score*/
                    arrTeam.add(new TeamsViewModel(selectedT1, selectedT2, teamOneScore.getText().toString().trim(),
                            teamTwoScore.getText().toString().trim()));
                } else {
                    Toast.makeText(weakActivity.get(), "You need to choose different teams", Toast.LENGTH_LONG).show();
                }
            }
        });
        return dialog;
    }

    // methods to return the team selected from the spinner
    public static String teamOneSpinnerSelected(Spinner teamOme) {
        return teamOme.getSelectedItem().toString();
    }

    public static String teamTwoSpinnerSelected(Spinner teamTwo) {
        return teamTwo.getSelectedItem().toString();
    }

    // Saving the teams to shared-preferences
    @SuppressLint("ApplySharedPref")
    public static boolean addTeam(final WeakReference<MainActivity> weakActivity, String teamName) {
        if (teamName.isEmpty()) {
            Toast.makeText(weakActivity.get(), " No team was added! Try again!", Toast.LENGTH_LONG).show();
            return false;
        } else {
            weakActivity.get().viewModel.addTeam(weakActivity.get(), teamName);
            Toast.makeText(weakActivity.get(), "team " + teamName + " added", Toast.LENGTH_LONG).show();
            return true;
        }
    }

    // Saving team's one score to shared-preferences
    @SuppressLint("ApplySharedPref")
    public static boolean addTeamOneScore(final WeakReference<MainActivity> weakActivity, String teamOneScore, String teamTwoScore) {
        if (teamOneScore.isEmpty() || teamTwoScore.isEmpty()) {
            Toast.makeText(weakActivity.get(), "You must fill in the score for team one", Toast.LENGTH_LONG).show();
            return false;
        } else {
            weakActivity.get().viewModel.addTeamOneScore(weakActivity.get(), teamOneScore);
            Toast.makeText(weakActivity.get(), "team one has the score: " + teamOneScore, Toast.LENGTH_LONG).show();
            return true;
        }
    }

    // Saving team's two score to shared-preferences
    @SuppressLint("ApplySharedPref")
    public static boolean addTeamTwoScore(final WeakReference<MainActivity> weakActivity, String teamTwoScore, String teamOneScore) {
        if (teamTwoScore.isEmpty() || teamOneScore.isEmpty()) {
            Toast.makeText(weakActivity.get(), "You must fill in the score for team two", Toast.LENGTH_LONG).show();
            return false;
        } else {
            weakActivity.get().viewModel.addTeamTwoScore(weakActivity.get(), teamTwoScore);
            Toast.makeText(weakActivity.get(), "team two has the score: " + teamTwoScore, Toast.LENGTH_LONG).show();
            return true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        // find all the buttons from first layout
        Button addTeam = findViewById(R.id.add_team);
        Button addScore = findViewById(R.id.add_score);
        Button showStatistics = findViewById(R.id.show_statistics);
        Button resetStatistics = findViewById(R.id.reset_statistics);

        // set the listener when clicking the addTeam button */
        addTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAddTeamDialog(new WeakReference<>(MainActivity.this)).show();
            }
        });

        // set the listener when clicking the addScore button */
        addScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAddScoreDialog(new WeakReference<>(MainActivity.this)).show();
            }
        });

        // set the listener when clicking the showStatistics button */
        showStatistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get the intent to navigate to ScoreAndStatistics class */
                Intent intent = new Intent(MainActivity.this, ScoreAndStatistics.class);
                Bundle args = new Bundle();
                // send to the new class my Array of objects */
                args.putSerializable("ARRAYLIST", arrTeam);
                intent.putExtra("BUNDLE", args);
                startActivity(intent);

            }
        });
        //set the listener when clicking the resetStatistics button
        resetStatistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.clearPreferences(getApplicationContext());
            }
        });
    }
}

package com.marianavalentinamitroi.ssoftscorestatistics;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class MainViewModel extends ViewModel {

    public MainViewModel() {
    }

    private static final String SHARED_PREFS_NAME = "SHARED_PREFS_NAME";
    private static final String SHARED_PREFS_TEAMS = "SHARED_PREFS_TEAMS";
    private static final String SHARED_PREFS_TEAM_ONE = "SHARED_PREFS_TEAM_ONE";
    private static final String SHARED_PREFS_TEAM_TWO = "SHARED_PREFS_TEAM_TWO";


    private SharedPreferences sharedPreferences = null;
    private ArrayList<String> teamList = null;
    private ArrayList<String> teamOneScoreList = null;
    private ArrayList<String> teamTwoScoreList = null;

    private Gson gson = new Gson();


    private SharedPreferences getSharedPreferences(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(SHARED_PREFS_NAME, MODE_PRIVATE);
        }

        return sharedPreferences;
    }

    // add the teams to Shared Preferences */
    @SuppressLint("ApplySharedPref")
    public void addTeam(Context context, String teamName) {
        getTeamList(context).add(teamName);

        String teamsJson = gson.toJson(getTeamList(context));
        getSharedPreferences(context).edit().putString(SHARED_PREFS_TEAMS, teamsJson).commit();
    }

    // get the arrayList of teams from Shared Preferences */
    public ArrayList<String> getTeamList(Context context) {
        if (teamList == null) {
            teamList = gson.fromJson(getSharedPreferences(context).getString(SHARED_PREFS_TEAMS, "[]"), new TypeToken<ArrayList<String>>() {
            }.getType());
        }

        return teamList;
    }

    // add team's one score to Shared Preferences */
    @SuppressLint("ApplySharedPref")
    public void addTeamOneScore(Context context, String teamOneScore) {
        getTeamOneScore(context).add(teamOneScore);

        String teamOneScoreJson = gson.toJson(getTeamOneScore(context));
        getSharedPreferences(context).edit().putString(SHARED_PREFS_TEAM_ONE, teamOneScoreJson).commit();
    }

    // get the arrayList of team's one score from Shared Preferences */
    public ArrayList<String> getTeamOneScore(Context context) {
        if (teamOneScoreList == null) {
            teamOneScoreList = gson.fromJson(getSharedPreferences(context).getString(SHARED_PREFS_TEAM_ONE, "[]"), new TypeToken<ArrayList<String>>() {
            }.getType());
        }

        return teamOneScoreList;
    }

    // add team's two score to Shared Preferences */
    @SuppressLint("ApplySharedPref")
    public void addTeamTwoScore(Context context, String teamTwoScore) {
        getTeamTwoScore(context).add(teamTwoScore);

        String teamTwoScoreJson = gson.toJson(getTeamTwoScore(context));
        getSharedPreferences(context).edit().putString(SHARED_PREFS_TEAM_TWO, teamTwoScoreJson).commit();
    }

    // get the arrayList of team's two score from Shared Preferences */
    public ArrayList<String> getTeamTwoScore(Context context) {
        if (teamTwoScoreList == null) {
            teamTwoScoreList = gson.fromJson(getSharedPreferences(context).getString(SHARED_PREFS_TEAM_TWO, "[]"), new TypeToken<ArrayList<String>>() {
            }.getType());
        }

        return teamTwoScoreList;
    }

    // clear the data */
    public void clearPreferences(Context context) {
        getSharedPreferences(context).edit().clear().apply();
        Toast.makeText(context, "cleared ", Toast.LENGTH_SHORT).show();
    }
}

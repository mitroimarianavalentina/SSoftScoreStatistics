package com.marianavalentinamitroi.ssoftscorestatistics;

import android.arch.lifecycle.ViewModel;

import java.io.Serializable;

public class TeamsViewModel extends ViewModel implements Serializable {
    private String teamOneName;
    private String teamTwoName;
    private String teamOneScore;
    private String teamTwoScore;


    public TeamsViewModel(String teamOneName, String teamTwoName, String teamOneScore, String teamTwoScore) {
        this.teamOneName = teamOneName;
        this.teamTwoName = teamTwoName;
        this.teamOneScore = teamOneScore;
        this.teamTwoScore = teamTwoScore;
    }

    public String getTeamOneName() {
        return teamOneName;
    }

    public String getTeamOneScore() {
        return teamOneScore;
    }

    public String getTeamTwoName() {
        return teamTwoName;
    }

    public String getTeamTwoScore() {
        return teamTwoScore;
    }

}

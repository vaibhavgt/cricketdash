package com.vaibhavgupta.cricketdash.model;

public class Winner {
    Team team;
    Integer winByWicket;
    Integer winByRuns;

    public Winner(Team team, Integer winByWicket, Integer winByRuns) {
        this.team = team;
        this.winByWicket = winByWicket;
        this.winByRuns = winByRuns;
    }

    public String resultString(){
        String result = "";
        if(winByRuns != null){
            result = team.getName() + " won the match by " + winByRuns + " runs";
        }else if(winByWicket != null){
            result = team.getName() + " won the match by " + winByWicket + " wickets";
        }
        return result;
    }
}

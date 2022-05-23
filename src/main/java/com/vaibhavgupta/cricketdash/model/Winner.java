package com.vaibhavgupta.cricketdash.model;

public class Winner {
    private Team team;
    private Integer winByWicket;
    private Integer winByRuns;

    public Winner(Team team, Integer winByWicket, Integer winByRuns) {
        this.team = team;
        this.winByWicket = winByWicket;
        this.winByRuns = winByRuns;
    }

    public Team getTeam() {
        return team;
    }

    public Integer getWinByWicket() {
        return winByWicket;
    }

    public Integer getWinByRuns() {
        return winByRuns;
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

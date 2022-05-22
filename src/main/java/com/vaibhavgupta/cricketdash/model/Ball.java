package com.vaibhavgupta.cricketdash.model;

public class Ball {

    private Player bowler;
    private Player batsman;
    private BallType ballType;
    private RunType runType;
    private int runs;
    private Wicket wicket;
    private String comments;

    @Override
    public String toString() {
        return "Ball{" +
                "bowler=" + bowler +
                ", batsman=" + batsman +
                ", ballType=" + ballType +
                ", runType=" + runType +
                ", runs=" + runs +
                ", wicket=" + wicket +
                ", comments='" + comments + '\'' +
                '}';
    }

    public Ball(Player batsman, Player bowler) {
        this.bowler = bowler;
        this.batsman = batsman;
        this.runs = 0;
        this.wicket = null;
        this.comments = "";
    }

    public Player getBowler() {
        return bowler;
    }

    public void setBowler(Player bowler) {
        this.bowler = bowler;
    }

    public Player getBatsman() {
        return batsman;
    }

    public void setBatsman(Player batsman) {
        this.batsman = batsman;
    }

    public BallType getBallType() {
        return ballType;
    }

    public void setBallType(BallType ballType) {
        this.ballType = ballType;
    }

    public RunType getRunType() {
        return runType;
    }

    public void setRunType(RunType runType) {
        this.runType = runType;
    }

    public int getRuns() {
        return runs;
    }

    public void setRuns(int runs) {
        this.runs = runs;
    }

    public Wicket getWicket() {
        return wicket;
    }

    public void setWicket(Wicket wicket) {
        this.wicket = wicket;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void addRuns(int runs) {
        this.runs += runs;
    }
}

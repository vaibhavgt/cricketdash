package com.vaibhavgupta.cricketdash.model;

public class PlayerPerformance {

    private int score;
    private int fours;
    private int sixes;
    private int wickets;
    private int extras;
    private int balls;
    private int catches;
    private Boolean isOut;

    public PlayerPerformance() {
        this.score = 0;
        this.fours = 0;
        this.sixes = 0;
        this.wickets = 0;
        this.extras = 0;
        this.balls = 0;
        this.catches = 0;
        this.isOut = null;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getFours() {
        return fours;
    }

    public void setFours(int fours) {
        this.fours = fours;
    }

    public int getSixes() {
        return sixes;
    }

    public void setSixes(int sixes) {
        this.sixes = sixes;
    }

    public int getWickets() {
        return wickets;
    }

    public void setWickets(int wickets) {
        this.wickets = wickets;
    }

    public int getExtras() {
        return extras;
    }

    public void setExtras(int extras) {
        this.extras = extras;
    }

    public int getBalls() {
        return balls;
    }

    public void setBalls(int balls) {
        this.balls = balls;
    }

    public int getCatches() {
        return catches;
    }

    public void setCatches(int catches) {
        this.catches = catches;
    }

    public Boolean isOut() {
        return isOut;
    }

    public void setOut(boolean out) {
        isOut = out;
    }

    public void addFours(int n){
        this.fours += n;
    }

    public void addSixes(int n){
        this.sixes += n;
    }

    public void addScore(int n){
        this.score += n;
    }

    public void addBalls(int n){
        this.balls += 1;
    }



}

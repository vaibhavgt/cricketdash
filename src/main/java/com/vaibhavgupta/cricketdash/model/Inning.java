package com.vaibhavgupta.cricketdash.model;

import java.util.ArrayList;
import java.util.List;

public class Inning {

    private List<Over> overs;
    private Team battingTeam;
    private Team bowlingTeam;
    private Player batsmanAtStrike;
    private Player batsmanAtBowlerEnd;
    private Player currentBowler;


    public Inning(Team battingTeam, Team bowlingTeam) {
        this.overs = new ArrayList<>();
        this.battingTeam = battingTeam;
        this.bowlingTeam = bowlingTeam;
    }

    public Player getCurrentBowler() {
        return currentBowler;
    }

    public void setCurrentBowler(Player currentBowler) {
        this.currentBowler = currentBowler;
    }

    public Player getBatsmanAtStrike() {
        return batsmanAtStrike;
    }

    public void setBatsmanAtStrike(Player batsmanAtStrike) {
        this.batsmanAtStrike = batsmanAtStrike;
    }

    public Player getBatsmanAtBowlerEnd() {
        return batsmanAtBowlerEnd;
    }

    public void setBatsmanAtBowlerEnd(Player batsmanAtBowlerEnd) {
        this.batsmanAtBowlerEnd = batsmanAtBowlerEnd;
    }


    public void addOver(Over over){
        this.overs.add(over);
    }

    public List<Over> getOvers() {
        return overs;
    }

    public Team getBattingTeam() {
        return battingTeam;
    }

    public Team getBowlingTeam() {
        return bowlingTeam;
    }

    public void switchStrike(){
        Player temp =  batsmanAtStrike;
        batsmanAtStrike = batsmanAtBowlerEnd;
        batsmanAtBowlerEnd = temp;
    }


}

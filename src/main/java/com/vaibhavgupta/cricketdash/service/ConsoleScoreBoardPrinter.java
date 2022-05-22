package com.vaibhavgupta.cricketdash.service;

import com.vaibhavgupta.cricketdash.model.*;

import java.util.List;
import java.util.Map;

public class ConsoleScoreBoardPrinter implements IScoreBoardPrinter{

    public void displayInningsSummary(InningsManager inningsManager){
        Map<Player, PlayerPerformance> playerPerformanceMap  = inningsManager.calculatePlayerPerformances();
        List<Player> battingTeamPlayers = inningsManager.getBattingTeamPlayers();

        System.out.println("ScoreCard For: " + inningsManager.getInning().getBattingTeam().getName());
        System.out.println("Player \t Score \t Fours \t Sixes \t balls \t OutStatus");
        for(Player player: battingTeamPlayers){
            if(playerPerformanceMap.containsKey(player)){
                PlayerPerformance perf =  playerPerformanceMap.get(player);
                System.out.println(getNameWithNotOutStatus(player.getName(), perf.isOut()) + "\t"+ perf.getScore() + "\t" + perf.getFours() + "\t" + perf.getSixes() + "\t" +  perf.getBalls() + "\t");
            }else{
                System.out.println(player.getName() + "\t"+ 0 + "\t" + 0+ "\t" + 0 + "\t" + 0);
            }
        }

        System.out.println("TotalRuns: " + inningsManager.getInningsScore());
        System.out.println("TotalWickets: " + inningsManager.getWickets());
        System.out.println("TotalExtras: "+ inningsManager.getExtras());

        System.out.println("Overs: " + inningsManager.getInning().getOvers().size());
        System.out.println("---------------------------");

    }

    private String getNameWithNotOutStatus(String name, boolean isOut){
        if(isOut){
            return name;
        }else{
            return name+"*" ;
        }
    }

    public void displayResult(Winner winner, MatchState matchState){
        if(matchState == MatchState.DRAWN){
            System.out.println("Match Draw");
        }else if(matchState == MatchState.FINISHED){
            System.out.println(winner.resultString());
        }else{
            System.out.println("Match State: " + matchState.toString());
        }
    }


}

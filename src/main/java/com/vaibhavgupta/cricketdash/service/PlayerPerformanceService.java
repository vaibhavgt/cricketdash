package com.vaibhavgupta.cricketdash.service;

import com.vaibhavgupta.cricketdash.model.*;

import java.util.HashMap;
import java.util.Map;

public class PlayerPerformanceService {

    private Map<Player, PlayerPerformance> playerPerformanceMap;

    public PlayerPerformanceService(Map<Player,PlayerPerformance> playerPerformanceMap){
        this.playerPerformanceMap = playerPerformanceMap;
    }

    public void addPlayer(Player player){
        playerPerformanceMap.put(player, new PlayerPerformance());
    }
    
    void update(Ball ball, Player batsman, Player batsmanAtBowlerEnd){
//        Player bowler = ball.getBowler();

        if(!playerPerformanceMap.containsKey(batsman)){
            playerPerformanceMap.put(batsman, new PlayerPerformance());
        }

        if(!playerPerformanceMap.containsKey(batsman)){
            playerPerformanceMap.put(batsmanAtBowlerEnd, new PlayerPerformance());
        }

//        if(!playerPerformanceMap.containsKey(bowler)){
//            playerPerformanceMap.put(bowler, new PlayerPerformance());
//        }

        PlayerPerformance batsmanPerformance = playerPerformanceMap.get(batsman);
        PlayerPerformance batsmanAtBowlerEndPerformance = playerPerformanceMap.get(batsmanAtBowlerEnd);
        batsmanPerformance.setOut(false);

        if(ball.getBallType() == BallType.NORMAL || ball.getBallType() == BallType.NO_BALL){
            if(ball.getRunType() == RunType.FOUR){
                batsmanPerformance.addFours(1);
            }else if(ball.getRunType() == RunType.SIX){
                batsmanPerformance.addSixes(1);
            }

            if(ball.getBallType() == BallType.NO_BALL){
                batsmanPerformance.addScore(ball.getRuns()-1);
            }else{
                batsmanPerformance.addScore(ball.getRuns());
                batsmanPerformance.addBalls(1);
            }
        }

        if(ball.getBallType() == BallType.WICKET){
            batsmanPerformance.setOut(true);
            batsmanPerformance.addBalls(1);
        }

        if(ball.getBallType() == BallType.RUN_OUT){
            if(ball.getRuns() == 1 || ball.getRuns() ==3 ){
                batsmanPerformance.addScore(ball.getRuns());
                batsmanPerformance.addBalls(1);
                batsmanPerformance.setOut(true);
            }else{
               batsmanAtBowlerEndPerformance.setOut(true);
            }
        }

    }

    public Map<Player, PlayerPerformance> getPlayerPerformanceMap() {
        return playerPerformanceMap;
    }

    public PlayerPerformance getPlayerPerformance(Player player) {
        return playerPerformanceMap.get(player);
    }

    public void setBatsmanOnStrike(Player player) {
        PlayerPerformance perf = playerPerformanceMap.get(player);
        perf.setOut(false);
        playerPerformanceMap.put(player, perf);
    }
}

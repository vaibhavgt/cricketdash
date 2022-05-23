package com.vaibhavgupta.cricketdash.service;

import com.vaibhavgupta.cricketdash.model.*;

import java.util.HashMap;
import java.util.Map;

public class PlayerPerformanceService {

    private Map<Player, PlayerPerformance> playerPerformanceMap;
    private static PlayerPerformanceService playerPerformanceService = new PlayerPerformanceService();

    public static PlayerPerformanceService getInstance(){
        if(playerPerformanceService == null){
            playerPerformanceService = new PlayerPerformanceService();
        }
        return playerPerformanceService;
    }

    private PlayerPerformanceService(){
        playerPerformanceMap = new HashMap<>();
    }

    public void addPlayer(Player player){
        playerPerformanceMap.put(player, new PlayerPerformance());
    }
    
    void update(Ball ball){
        Player batsman = ball.getBatsman();
        Player bowler = ball.getBowler();

        if(!playerPerformanceMap.containsKey(batsman)){
            playerPerformanceMap.put(batsman, new PlayerPerformance());
        }

        if(!playerPerformanceMap.containsKey(bowler)){
            playerPerformanceMap.put(bowler, new PlayerPerformance());
        }

        PlayerPerformance batsmanPerformance = playerPerformanceMap.get(batsman);
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

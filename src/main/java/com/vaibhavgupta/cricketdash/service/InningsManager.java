package com.vaibhavgupta.cricketdash.service;

import com.vaibhavgupta.cricketdash.AppConstants;
import com.vaibhavgupta.cricketdash.model.PlayerPerformance;
import com.vaibhavgupta.cricketdash.model.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class InningsManager {

    private Inning inning;
    private Match match;

    private final List<BallType> extraBallTypes = List.of(BallType.WIDE);

    public InningsManager(Match match, Team battingTeam, Team bowlingTeam) {
        this.match = match;
        this.inning = new Inning(battingTeam, bowlingTeam);
        setUpBatsmanAndBowler();
    }

    private void setUpBatsmanAndBowler(){
        this.inning.setBatsmanAtStrike(nextBatsman());
        this.inning.setBatsmanAtBowlerEnd(nextBatsman());
        this.inning.setCurrentBowler(nextBowler());
    }

    public void addNewOver(){
        inning.addOver(new Over());
        switchStrike();
        inning.setCurrentBowler(nextBowler());
    }

    public void switchStrike(){
        inning.switchStrike();
    }

    public Over getCurrentOver(){
        if(inning.getOvers().size() == 0){
            inning.addOver(new Over());
        }
        return inning.getOvers().get(inning.getOvers().size()-1);
    }

    public int getInningsScore(){
        int sum =0;
        for(Over over: inning.getOvers()){
            for(Ball ball: over.getBalls()){
               sum += ball.getRuns();
            }
        }
        return sum;
    }

    public int getWickets(){
        int wickets =0;
        for(Over over: inning.getOvers()){
            for(Ball ball: over.getBalls()){
                if(ball.getBallType() == BallType.WICKET) wickets += 1;
            }
        }
        return wickets;
    }

    public int getExtras(){
        int extras =0;
        for(Over over: inning.getOvers()){
            for(Ball ball: over.getBalls()){
                if(extraBallTypes.contains(ball.getBallType())) extras += ball.getRuns();
            }
        }
        return extras;
    }

    public  Map<Player, PlayerPerformance> calculatePlayerPerformances(){
        Map<Player, PlayerPerformance> playerPerformanceMap = new HashMap<>();

        int activePlayersCount = 0;

        for(Over over: inning.getOvers()){
            for(Ball ball: over.getBalls()){

                Player batsman = ball.getBatsman();
                Player bowler = ball.getBowler();

                if(!playerPerformanceMap.containsKey(batsman)){
                    activePlayersCount += 1;
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
                    activePlayersCount -= 1;
                    batsmanPerformance.addBalls(1);
                }

            }
        }

        if(activePlayersCount == 1){
            PlayerPerformance perf = new PlayerPerformance();
            perf.setOut(false);
            playerPerformanceMap.put(inning.getBatsmanAtBowlerEnd(), perf);
        }
        return playerPerformanceMap;
    }

    public Inning getInning() {
        return inning;
    }

    public List<Player> getBattingTeamPlayers(){
        return match.getTeamListHashMap().get(inning.getBattingTeam());
    }
    public List<Player> getBowlingTeamPlayers(){
        return match.getTeamListHashMap().get(inning.getBowlingTeam());
    }

    Player nextBatsman(){
        Player nextBatsman = null;
        for(Player player: match.getTeamListHashMap().get(getInning().getBattingTeam())){
            PlayerPerformance perf = match.getPlayerPerformanceMap().get(player);

            if(perf.isOut() == null){
                perf.setOut(false);
                nextBatsman = player;
                match.getPlayerPerformanceMap().put(player, perf);
                break;
            }
        }

        return nextBatsman;
    }

    public Player nextBowler(){
        List<Player> players  = match.getTeamListHashMap().get(getInning().getBowlingTeam());
        Random rand = new Random();
        int next = rand.nextInt(players.size()-1);
        return players.get(next);
    }

    public void addBall(Ball ball) {
        if(ball.getBallType() == BallType.WICKET){
            inning.setBatsmanAtStrike(nextBatsman());
        }
        if(AppConstants.strikeChangeRuns.contains(ball.getRuns())){
            switchStrike();
        }
        getCurrentOver().addBall(ball);
    }
}

package com.vaibhavgupta.cricketdash.service;

import com.vaibhavgupta.cricketdash.AppConstants;
import com.vaibhavgupta.cricketdash.exception.InvalidBallException;
import com.vaibhavgupta.cricketdash.exception.MatchFinishedException;
import com.vaibhavgupta.cricketdash.model.*;

import java.util.ArrayList;
import java.util.List;

public class GameManager {
    private Match match;
    private MatchState matchState;
    private int maxOverPerInning;
    private int playersPerTeam;

    private List<InningsManager> inningManagerList;
    private InningsManager currentInningManager;
    private IScoreBoardPrinter printer;
    private Winner winner;

    public GameManager(Match match,int maxOverPerInning, int playersPerTeam, Team battingTeam, Team bowlingTeam, ConsoleScoreBoardPrinter scoreBoardPrinter){
        this.match = match;
        this.playersPerTeam = playersPerTeam;
        this.maxOverPerInning = maxOverPerInning;
        this.inningManagerList = new ArrayList<>();
        this.printer = scoreBoardPrinter;
        this.matchState = MatchState.LIVE;

        setUpNewInning(battingTeam, bowlingTeam);
    }

    void setUpNewInning(Team battingTeam, Team bowlingTeam){
        this.currentInningManager = new InningsManager(match, battingTeam, bowlingTeam);
        this.inningManagerList.add(currentInningManager);
    }

    public void addBall(Ball ball) throws MatchFinishedException {

        if(matchState != MatchState.LIVE){
            printMatchResults();
            throw new MatchFinishedException();
        }

        currentInningManager.addBall(ball);

        matchState = updateGameState();
        if(matchState != MatchState.LIVE){
            declareWinner();
            return;
        }

        Over currentOver = currentInningManager.getCurrentOver();
        if(currentOver.isFinished()){
            handleOverFinish();
        }
    }

    public MatchState updateGameState(){

        if (inningManagerList.size() < 2){
            return MatchState.LIVE;
        }else if (inningManagerList.size() == 2){
            InningsManager previousInningManager = inningManagerList.get(0);

             int prevScore =  previousInningManager.getInningsScore();
             int currScore =  currentInningManager.getInningsScore();
            Over currentOver = currentInningManager.getCurrentOver();

            int currWickets =  currentInningManager.getWickets();
             if(currScore > prevScore){
                 winner =  new Winner(currentBattingTeam(), playersPerTeam - currWickets, null );
                 return MatchState.FINISHED;
             }else if((currWickets == playersPerTeam-1) && currScore < prevScore){
                 winner =  new Winner(currentBowlingTeam(),null , prevScore - currScore);
                 return MatchState.FINISHED;

             }else if((currWickets == playersPerTeam-1) || (currentInningManager.getInning().getOvers().size() == maxOverPerInning && currentOver.isFinished()) && prevScore == currScore){
                 return MatchState.DRAWN;
            }
        }
        return MatchState.LIVE;
    }

    public Ball convertToBallObject(String source) throws InvalidBallException {
        Player batsman = currentInningManager.getInning().getBatsmanAtStrike();
        Player bowler = currentInningManager.getInning().getCurrentBowler();
        Ball ball = new BallTokenizer(source, batsman, bowler).getBall();
        return ball;
    }

    private void handleOverFinish(){
        printScoreCard();
        if((currentInningManager.getInning().getOvers().size() == maxOverPerInning) || (currentInningManager.getWickets() == playersPerTeam-1) && inningManagerList.size() < 2){
            setUpNewInning(currentBowlingTeam(), currentBattingTeam());
            return;
        }
        if(currentInningManager.getInning().getOvers().size() < maxOverPerInning) currentInningManager.addNewOver();
    }

    private Team currentBattingTeam(){
        return currentInningManager.getInning().getBattingTeam();
    }

    private Team currentBowlingTeam(){
        return currentInningManager.getInning().getBowlingTeam();
    }

    private void declareWinner(){
        printScoreCard();
        printMatchResults();
    }

    public void printScoreCard(){
        printer.displayInningsSummary(match.getPlayerPerformanceService().getPlayerPerformanceMap(), currentInningManager);
//        System.out.println("===================");
//        printScoreCardFromLog();
//        System.out.println("===================");
    }

    public void printScoreCardFromLog(){
        printer.displayInningsSummaryFromBallLog(currentInningManager);
    }

    public void printMatchResults() {
        printer.displayResult(winner, matchState);
    }

    public List<InningsManager> getInningManagerList() {
        return inningManagerList;
    }

    public InningsManager getCurrentInningManager() {
        return currentInningManager;
    }

    public Winner getWinner() {
        return winner;
    }

}

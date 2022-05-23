package com.vaibhavgupta.cricketdash.service;

import com.vaibhavgupta.cricketdash.exception.InvalidBallException;
import com.vaibhavgupta.cricketdash.model.*;

import java.util.HashMap;
import java.util.Map;

public class BallTokenizer {
    private final String source;
    private Ball ball;

    BallTokenizer(String source) {
        this.source = source;
    }

    private static final Map<String, RunType> runTypes;
    private static final Map<String, BallType> ballTypes;
    private static final Map<String, Integer> ballTypeRuns;
    static {
        runTypes = new HashMap<>();
        runTypes.put("6", RunType.SIX );
        runTypes.put("4", RunType.FOUR);

        ballTypes = new HashMap<>();
        ballTypes.put("W", BallType.WICKET);
        ballTypes.put("WD", BallType.WIDE);
        ballTypes.put("R0", BallType.RUN_OUT);
        ballTypes.put("R1", BallType.RUN_OUT);
        ballTypes.put("R2", BallType.RUN_OUT);
        ballTypes.put("R3", BallType.RUN_OUT);

        ballTypeRuns = new HashMap<>();
        ballTypeRuns.put("WD", 1);
        ballTypeRuns.put("R0", 0);
        ballTypeRuns.put("R1", 1);
        ballTypeRuns.put("R2", 2);
        ballTypeRuns.put("R3", 3);
    }

    public BallTokenizer(String source, Player batsman, Player bowler) throws InvalidBallException {
        this.ball = new Ball(batsman, bowler);
        this.source =source;
        addInfo();
    }

    public Ball getBall() {
        return ball;
    }

    private void addInfo() throws InvalidBallException {
        ball.setRunType(getRunType());
        ball.setBallType(getBallType());
        ball.addRuns(getRuns());
    }

    private int getRuns(){
        if(isDigit(source)){
            return Integer.parseInt(source);
        } else if(ballTypeRuns.containsKey(source)){
            return  ballTypeRuns.get(source);
        }
        return 0;
    }

    private BallType getBallType() throws InvalidBallException {
       if(ballTypes.containsKey(source)){
            return ballTypes.get(source);
       } else if(isDigit(source)){
            return BallType.NORMAL;
       }else {
           throw new InvalidBallException();
       }
    }


    private RunType getRunType(){
        if(isDigit(source)){
            if(runTypes.containsKey(source)){
                return runTypes.get(source);
            }
        }
        return RunType.NORMAL;
    }

    boolean isDigit(String s){
        if(s.length() == 1 && s.charAt(0) >= '0' && s.charAt(0) <= '6'){
            return true;
        }
        return false;
    }

}

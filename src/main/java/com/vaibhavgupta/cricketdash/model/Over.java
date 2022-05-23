package com.vaibhavgupta.cricketdash.model;

import com.vaibhavgupta.cricketdash.service.PlayerPerformanceService;

import java.util.ArrayList;
import java.util.List;

public class Over {

    private List<Ball> balls;

    public Over() {
        this.balls = new ArrayList<>();
    }

    public List<Ball> getBalls() {
        return balls;
    }

    public void addBall(Ball ball){
        balls.add(ball);
    }

    public boolean isFinished(int maxBalls){
        if(noOfvalidBalls() == maxBalls){
            return true;
        }
        return false;
    }

    public boolean isFinished(){
        if(noOfvalidBalls() == 6){
            return true;
        }
        return false;
    }

    public int noOfvalidBalls(){
        int noOfBalls = 0;
        for(Ball ball: balls){
            if(ball.getBallType() == BallType.WICKET || ball.getBallType() == BallType.NORMAL){
                noOfBalls +=1;
            }
        }
        return noOfBalls;
    }


}

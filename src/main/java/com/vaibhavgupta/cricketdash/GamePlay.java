package com.vaibhavgupta.cricketdash;

import com.vaibhavgupta.cricketdash.model.Ball;
import com.vaibhavgupta.cricketdash.model.Match;
import com.vaibhavgupta.cricketdash.model.Player;
import com.vaibhavgupta.cricketdash.model.Team;
import com.vaibhavgupta.cricketdash.service.GameManager;
import com.vaibhavgupta.cricketdash.service.ConsoleScoreBoardPrinter;

import java.util.List;

public class GamePlay {

    public static void main(String[] args) throws Exception {

        Team team1 = new Team(1, "Team1");
        Team team2 = new Team(2, "Team2");
        List<Team> teams = List.of(team1, team2);
        Match match = new Match("1", teams);

        for(int i = 1; i <= 5; i++){
            match.addPlayer(team1, new Player(i, "Player"+i));
        }

        for(int i = 6; i <= 10; i++){
            match.addPlayer(team2, new Player(i, "Player"+i));
        }

        GameManager manager = new GameManager(match, 2, 5, team1, team2, new ConsoleScoreBoardPrinter());

        String[] balls = {"1", "1", "1", "1", "1", "2", "W", "4", "4", "WD", "W", "1", "6"};
        for(String ballstr: balls){
            Ball ball = manager.convertToBallObject(ballstr);

            manager.addBall(ball);
            System.out.println(ball);
            System.out.println(manager.getCurrentInningManager().getInning().getBatsmanAtStrike().getName());
        }

        String[] secondInningballs = {"4", "6", "W", "W", "1", "1", "6", "1", "W", "W"};
        for(String ballstr: secondInningballs){
            Ball ball = manager.convertToBallObject(ballstr);
            manager.addBall(ball);
        }

    }

}

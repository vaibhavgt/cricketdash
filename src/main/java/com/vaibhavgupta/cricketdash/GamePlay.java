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

        String[] balls = {"1", "2", "3", "W", "WD", "1", "1", "2", "3", "5", "3", "4", "6"};
        for(String ballstr: balls){
            Ball ball = manager.convertToBallObject(ballstr);
///            System.out.println(ball);
            manager.addBall(ball);
        }

        String[] secondInningballs = {"6", "6", "6", "6", "W", "6", "W", "W", "1", "0", "1", "0"};
        for(String ballstr: secondInningballs){
            Ball ball = manager.convertToBallObject(ballstr);
///            System.out.println(ball);
            manager.addBall(ball);
        }

    }

}

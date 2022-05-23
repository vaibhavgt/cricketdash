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

        for(int i = 1; i <= 4; i++){
            match.addPlayer(team1, new Player(i, "Player"+i));
        }

        for(int i = 5; i <= 8; i++){
            match.addPlayer(team2, new Player(i, "Player"+i));
        }

        GameManager manager = new GameManager(match, 2, 4, team1, team2, new ConsoleScoreBoardPrinter());

        String[] balls = {"1", "2", "R0", "1", "R1", "3", "WD", "WD", "6", "1", "W"};
        for(String ballstr: balls){
            Ball ball = manager.convertToBallObject(ballstr);

            manager.addBall(ball);
///            System.out.println(ball);
 //           System.out.println(manager.getCurrentInningManager().getInningsScore());
        }

        String[] secondInningballs = {"1","1", "1", "WD", "WD", "2", "W", "R0", "2", "2", "W"};
        for(String ballstr: secondInningballs){
            Ball ball = manager.convertToBallObject(ballstr);
            manager.addBall(ball);
        }

    }

}

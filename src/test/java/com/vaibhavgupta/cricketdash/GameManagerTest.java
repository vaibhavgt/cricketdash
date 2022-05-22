package com.vaibhavgupta.cricketdash;


import com.vaibhavgupta.cricketdash.exception.InvalidBallException;
import com.vaibhavgupta.cricketdash.exception.MatchFinishedException;
import com.vaibhavgupta.cricketdash.model.Ball;
import com.vaibhavgupta.cricketdash.model.Match;
import com.vaibhavgupta.cricketdash.model.Player;
import com.vaibhavgupta.cricketdash.model.Team;
import com.vaibhavgupta.cricketdash.service.ConsoleScoreBoardPrinter;
import com.vaibhavgupta.cricketdash.service.GameManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


import java.util.List;

public class GameManagerTest {
    private Team team1;
    private Team team2;
    private Match match;


    @BeforeEach
    void setUp(){
         team1 = new Team(1, "Team1");
         team2 = new Team(2, "Team2");
         List<Team> teams = List.of(team1, team2);
         match = new Match("1", teams);
    }

    @Test
    public void testStrikeChangedAtOverChange() throws InvalidBallException, MatchFinishedException {


        Player player1 = new Player(1, "Player1");
        Player player2 = new Player(2, "Player2");
        Player player3 = new Player(3, "Player3");

        match.addPlayer(team1, player1);
        match.addPlayer(team1, player2);
        match.addPlayer(team1, player3);

        for(int i = 4; i <= 6; i++){
            match.addPlayer(team2, new Player(i, "Player"+i));
        }
        GameManager manager = new GameManager(match, 2, 3, team1, team2, new ConsoleScoreBoardPrinter());
        String[] balls = {"0", "0", "0", "0", "0", "0"};
        for(String ballstr: balls){
            Ball ball = manager.convertToBallObject(ballstr);
            manager.addBall(ball);
        }

        assertEquals(player2, manager.getCurrentInningManager().getInning().getBatsmanAtStrike());
        assertEquals(player1, manager.getCurrentInningManager().getInning().getBatsmanAtBowlerEnd());
    }

    @Test
    public void testStrikeChangedAfterOneRun() throws InvalidBallException, MatchFinishedException {
        Player player1 = new Player(1, "Player1");
        Player player2 = new Player(2, "Player2");
        Player player3 = new Player(3, "Player3");

        match.addPlayer(team1, player1);
        match.addPlayer(team1, player2);
        match.addPlayer(team1, player3);

        for(int i = 4; i <= 6; i++){
            match.addPlayer(team2, new Player(i, "Player"+i));
        }
        GameManager manager = new GameManager(match, 2, 3, team1, team2, new ConsoleScoreBoardPrinter());
        String[] balls = {"1"};
        for(String ballstr: balls){
            Ball ball = manager.convertToBallObject(ballstr);
            manager.addBall(ball);
        }

        assertEquals(player2, manager.getCurrentInningManager().getInning().getBatsmanAtStrike());
        assertEquals(player1, manager.getCurrentInningManager().getInning().getBatsmanAtBowlerEnd());
    }

//    public void testWinByCorrectRuns() throws InvalidBallException, MatchFinishedException {
//        Player player1 = new Player(1, "Player1");
//        Player player2 = new Player(2, "Player2");
//        Player player3 = new Player(3, "Player3");
//
//        match.addPlayer(team1, player1);
//        match.addPlayer(team1, player2);
//        match.addPlayer(team1, player3);
//
//        for(int i = 4; i <= 6; i++){
//            match.addPlayer(team2, new Player(i, "Player"+i));
//        }
//        GameManager manager = new GameManager(match, 2, 3, team1, team2, new ConsoleScoreBoardPrinter());
//        String[] balls = {"1", "2", "3", "W", "WD", "1", "1", "2", "3", "5", "3", "4", "6"};
//        for(String ballstr: balls){
//            Ball ball = manager.convertToBallObject(ballstr);
//            manager.addBall(ball);
//        }
//
//
//    }



}

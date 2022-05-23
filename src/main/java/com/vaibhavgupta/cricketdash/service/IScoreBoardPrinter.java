package com.vaibhavgupta.cricketdash.service;

import com.vaibhavgupta.cricketdash.model.MatchState;
import com.vaibhavgupta.cricketdash.model.Player;
import com.vaibhavgupta.cricketdash.model.PlayerPerformance;
import com.vaibhavgupta.cricketdash.model.Winner;

import java.util.Map;

public interface IScoreBoardPrinter {
    public void displayInningsSummary(Map<Player, PlayerPerformance> playerPerformanceMap, InningsManager inningsManager);
    public void displayResult(Winner winner, MatchState matchState);
    public void displayInningsSummaryFromBallLog(InningsManager inningsManager);
}

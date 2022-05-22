package com.vaibhavgupta.cricketdash.service;

import com.vaibhavgupta.cricketdash.model.MatchState;
import com.vaibhavgupta.cricketdash.model.Winner;

public interface IScoreBoardPrinter {
    public void displayInningsSummary(InningsManager inningsManager);
    public void displayResult(Winner winner, MatchState matchState);
}

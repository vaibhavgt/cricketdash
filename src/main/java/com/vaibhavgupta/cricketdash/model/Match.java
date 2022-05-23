package com.vaibhavgupta.cricketdash.model;

import com.vaibhavgupta.cricketdash.service.PlayerPerformanceService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Match {

    private final String id;
    private Map<Team, List<Player>> teamListHashMap = new HashMap<>();
    private PlayerPerformanceService playerPerformanceService;
    private MatchType matchType;


    public Match(String id, List<Team> teams) {
        this.id = id;
        for(Team team: teams){
            teamListHashMap.put(team, new ArrayList<Player>());
        }
        playerPerformanceService =  new PlayerPerformanceService(new HashMap<>());
    }

    public PlayerPerformanceService getPlayerPerformanceService() {
        return playerPerformanceService;
    }

    public void addPlayer(Team team, Player player){
        teamListHashMap.get(team).add(player);
        playerPerformanceService.addPlayer(player);
    }

    public String getId() {
        return id;
    }

    public Map<Team, List<Player>> getTeamListHashMap() {
        return teamListHashMap;
    }

    public MatchType getMatchType() {
        return matchType;
    }
}

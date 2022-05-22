package com.vaibhavgupta.cricketdash.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Match {

    private final String id;
    private Map<Team, List<Player>> teamListHashMap = new HashMap<>();
    private Map<Player, PlayerPerformance> playerPerformanceMap = new HashMap<>();
    private MatchType matchType;

    public Match(String id, List<Team> teams) {
        this.id = id;
        for(Team team: teams){
            teamListHashMap.put(team, new ArrayList<Player>());
        }
    }

    public void addPlayer(Team team, Player player){
        teamListHashMap.get(team).add(player);
        playerPerformanceMap.put(player, new PlayerPerformance());
    }

    public Map<Player, PlayerPerformance> getPlayerPerformanceMap() {
        return playerPerformanceMap;
    }

    public void setPlayerPerformanceMap(Map<Player, PlayerPerformance> playerPerformanceMap) {
        this.playerPerformanceMap = playerPerformanceMap;
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

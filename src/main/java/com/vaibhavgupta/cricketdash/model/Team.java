package com.vaibhavgupta.cricketdash.model;

import java.util.List;

public class Team {

    private final int id;
    private String name;

    public Team(int id, String name) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }
}

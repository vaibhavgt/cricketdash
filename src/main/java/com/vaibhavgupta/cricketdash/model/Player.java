package com.vaibhavgupta.cricketdash.model;

public class Player {

    private final int id;
    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Player(int id, String name){
        this.id = id;
        this.name = name;
    }
}

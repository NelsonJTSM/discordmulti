package com.discordmulti;

public class Player {

    private String name;
    private long id;

    public String toString() {
        return id + ":" + name;
    }

    public Player(String name, long id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }
}

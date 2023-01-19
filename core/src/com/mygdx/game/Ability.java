package com.mygdx.game;

public class Ability {

    private String name;

    public Ability() {}

    public void run(Fighter actor, Fighter target) {}

    public int getTimer() {
        return 200;
    }

    public String getName() {
        return "Ability";
    }

    public String getExplanation() {
        return "Select a Ability";
    }

    public int getPrice() {
        return 0;
    }

    public boolean enoughGils(int gils) {
        return true;
    }
}

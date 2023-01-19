package com.mygdx.game.abilities;

import com.mygdx.game.Ability;
import com.mygdx.game.Fighter;

public class Donate extends Ability {

    int amount;
    public Donate(int amount) {
        this.amount = amount;
    }

    public String getName() {
        return "Donate (" + amount + ")";
    }

    public boolean enoughGils(int gils, int delta) {
        if (gils >= (delta/10)) return true;
        return false;
    }

    public void run(Fighter actor, Fighter target) {
        if (actor.getGil() >= amount) {
            actor.changeGil(-amount);
            target.changeGil(amount);
        }
    }

    public int getTimer() {
        return 50;
    }

    public String getExplanation() {
        return "Donate: Spende deinem Gegner " + amount + " Gil. (" + amount + " Gil).";
    }
}

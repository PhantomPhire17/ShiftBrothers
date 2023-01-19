package com.mygdx.game.abilities;

import com.mygdx.game.Ability;
import com.mygdx.game.Fighter;

public class Cure extends Ability {

    int delta;
    public Cure(int delta) {
        this.delta = delta;
    }

    public String getName() {
        return "Cure (" + delta/10 + ")";
    }

    public void run(Fighter actor, Fighter target) {

        if (actor.getGil() >= (delta/10)) {
            actor.changeHP(delta);
            actor.changeGil(-delta/10);
        }
    }

    public int getTimer() {
        return 200;
    }

    public String getExplanation() {
        return "Cure: Erhöhe HP um " + delta + ". (" + delta/10 + " Gil).";
    }

    public boolean enoughGils(int gils) {
        if (gils >= (delta/10)) return true;
        return false;
    }

}

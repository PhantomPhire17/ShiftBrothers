package com.mygdx.game.abilities;

import com.mygdx.game.Ability;
import com.mygdx.game.Fighter;

public class Slow extends Ability {

    public Slow() {}

    public String getName() {
        return "Slow (10)";
    }

    public void run(Fighter actor, Fighter target) {
        if(actor.getGil() >= 10) {
            actor.changeGil(-10);
            actor.changeSpeed(-2);
        }
    }

    public String getExplanation() {
        return "Verringere deine Geschwindigkeit.";
    }

    public boolean enoughGils(int gils) {
        if (gils >= 10) return true;
        return false;
    }

    public int getTimer() {
        return 300;
    }
}

package com.mygdx.game.abilities;

import com.mygdx.game.Ability;
import com.mygdx.game.Fighter;

public class Slow extends Ability {

    public Slow() {}

    public String getName() {
        return "Slow (15)";
    }

    public void run(Fighter actor, Fighter target) {
        if(actor.getGil() >= 15) {
            actor.changeGil(-15);
            target.changeSpeed(target.getSpeed()/2);
        }
    }

    public String getExplanation() {
        return "Verlangsame dich oder deinen Gegner.";
    }

    public boolean enoughGils(int gils) {
        if (gils >= 15) return true;
        return false;
    }

    public int getTimer() {
        return 300;
    }
}

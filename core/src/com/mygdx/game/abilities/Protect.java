package com.mygdx.game.abilities;

import com.mygdx.game.Ability;
import com.mygdx.game.Fighter;

public class Protect extends Ability {

    public Protect() {}

    public String getExplanation()  {
        return "Protect: Erhöhe deine Verteidigung (5 Gil).";
    }
    public String getName() {
        return "Protect (5)";
    }

    public void run(Fighter actor, Fighter target) {
        if (actor.getGil() >= 5) {
            actor.changeGil(-5);
            actor.changeDefense(3);
        }
    }

    public boolean enoughGils(int gils) {
        if (gils >= 5) return true;
        return false;
    }


    public int getTimer() {
        return 300;
    }
}

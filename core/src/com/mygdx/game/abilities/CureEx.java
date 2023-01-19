package com.mygdx.game.abilities;

import com.mygdx.game.Ability;
import com.mygdx.game.Fighter;

import java.util.Random;

public class CureEx extends Ability {

    Fighter actor;
    Fighter target;
    int seconds = 1;

    public CureEx() {
    }

    Random rgen = new Random();

    public void run(Fighter actor, Fighter target) {
        if (actor.getGil() >= 10) {
            actor.changeGil(-10);
            target.changeHP(100);
        }
    }

    public boolean enoughGils(int gils) {
        if (gils >= 10) return true;
        return false;
    }

    public String getName() {
        return "CureEx (10)";
    }

    public int getTimer() {
        return 50;
    }

    public int getPrice() {
        return 10;
    }

    public String getExplanation() { return "CureEx: Heile deinen Gegner mit 100 HP (10 Gil).";
    }
}

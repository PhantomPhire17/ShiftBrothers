package com.mygdx.game.abilities;

import com.mygdx.game.Ability;
import com.mygdx.game.Fighter;

public class Trade extends Ability {

    public Trade() {}

    public void run(Fighter actor, Fighter target) {
        actor.changeGil(2);
        actor.changeHP(-actor.getHP()/10);
        actor.changeAttack(-actor.getAttack()/10);
        actor.changeDefense(-actor.getDefense()/10);
        actor.changeSpeed(-actor.getSpeed()/10);
    }

    public String getName() {
        return "GetGil";
    }

    public String getExplanation() {
        return "Tausche 10% deiner Werte (HP, Strength, Defense, Speed) gegen 2 Gil.";
    }

    public boolean enoughGils(int gils) {
        return true;
    }

    public int getTimer() {
        return 400;
    }
}

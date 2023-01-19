package com.mygdx.game.abilities;

import com.mygdx.game.Ability;
import com.mygdx.game.Fighter;

public class Defend extends Ability {
    public Defend() {}

    public String getName() {
        return "Defend";
    }

    public void run(Fighter actor, Fighter target) {
        actor.changeDefense(10);
    }

    public int getPrice() {
        return 0;
    }

    public String getExplanation() {
        return "Erhöhe deine Verteidigung bis du die nächste Fähigkeit auswählst.";
    }

    public boolean enoughGils(int gils) {
        return true;
    }
}

package com.mygdx.game.abilities;

import com.mygdx.game.Ability;
import com.mygdx.game.Fighter;

import java.util.Random;

public class Harm extends Ability {

    public Harm() {
    }

    Random rgen = new Random();

    public void run(Fighter actor, Fighter target) {
        int strength = actor.getAttack();
        actor.changeGil(1);
        actor.setDamage(strength/2);
    }

    public boolean enoughGils() {
        return true;
    }

    public String getName() {
        return "Harm";
    }

    public int getTimer() {
        return 50;
    }

    public int getPrice() {
        return 0;
    }

    public String getExplanation() {
        return "Harm: Verletze dich selbst (geringerer Schaden).";
    }
}

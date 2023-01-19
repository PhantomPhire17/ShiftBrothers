package com.mygdx.game.abilities;

import com.mygdx.game.Ability;
import com.mygdx.game.interfaces.Constants;
import com.mygdx.game.Fighter;

import java.util.Random;

public class Attack extends Ability implements Constants {

    Fighter actor;
    Fighter target;
    int seconds = 1;

    public Attack() {
    }

    Random rgen = new Random();
    int n;

    public void run(Fighter actor, Fighter target) {
        int strength = actor.getAttack();
        target.setDamage(strength);
        actor.changeGil(2);
    }

    public boolean enoughGils() {
        return true;
    }

    public String getName() {
        return "Attack";
    }

    public int getTimer() {
        return 50;
    }

    public int getPrice() {
        return 0;
    }

    public String getExplanation() {
        return "Attack: Attackiere deinen Gegner und erhalte etwas Geld.";
    }
}

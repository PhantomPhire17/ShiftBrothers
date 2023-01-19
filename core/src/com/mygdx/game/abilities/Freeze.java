package com.mygdx.game.abilities;

import com.mygdx.game.Ability;
import com.mygdx.game.Fighter;

public class Freeze extends Ability {

    public Freeze() {}

    public void run(Fighter actor, Fighter target) {
        target.setSpeed(0);
    }
}

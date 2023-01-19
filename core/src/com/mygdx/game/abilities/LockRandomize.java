package com.mygdx.game.abilities;

import com.mygdx.game.Ability;
import com.mygdx.game.interfaces.Constants;
import com.mygdx.game.Fighter;

public class LockRandomize extends Ability implements Constants {

    public LockRandomize() {}

    public String getName() {
        return "Lock (10)";
    }

    public void run(Fighter actor, Fighter target) {
        if (actor.getGil() >= 10) {
            actor.changeGil(-10);
            b.setLocked(true);
        }
    }

    public String getExplanation() {
        return "Lock: Hindere dich und deinen Gegner daran, 'Random' zu aktivieren. \nWird beim nächsten Switch aufgehoben. (10 Gil)";
    }

    public boolean enoughGils(int gils) {
        if (gils >= 10 && b.lockIsActivated() == false) return true;
        return false;
    }
}

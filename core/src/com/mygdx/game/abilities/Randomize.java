package com.mygdx.game.abilities;
import com.mygdx.game.Ability;
import com.mygdx.game.*;
import com.mygdx.game.interfaces.Constants;

public class Randomize extends Ability implements Constants {

    public Randomize() {}

    public String getName() {
        return "Random (20)";
    }

    public void run(Fighter actor, Fighter target) {
        if (actor.getGil() >= 20) {
            actor.changeGil(-20);
            int n = rgen.nextInt(3,33);
            if (n < 3) n = 3;
            game.b.setSwitchTime(n);
            game.b.setRandomTime(true);
        }
    }

    public String getExplanation() {
        return "Random: Der nächste Switch geschieht komplett zufällig (20 Gil).";
    }

    public boolean enoughGils(int gils) {
        if (gils >= 20 && game.b.lockIsActivated() == false) return true;
        return false;
    }

    public int getTimer() {
        return 500;
    }

    //------------

    public void setSwitchTime(int time) {

    }

    public void setRandomTime(boolean b) {
        isRandom = b;
    }

    private boolean isRandom = false;
    public boolean timeIsRandom() {
        return isRandom;
    }

}

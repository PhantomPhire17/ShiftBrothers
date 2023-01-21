package com.mygdx.game.abilities;

import com.mygdx.game.Ability;
import com.mygdx.game.interfaces.Constants;
import com.mygdx.game.Fighter;

public class Quick extends Ability implements Constants {

    public Quick() {}

    public String getName() {
        return "Quick (15)";
    }

    public void run(Fighter actor, Fighter target) {
        if ((game.b.getCurrentSwitchTime() >= 11) && actor.getGil() >= 15 && game.b.lockIsActivated()==false) {
            actor.changeGil(-15);
            //harald.setCurrentSwitchTime(harald.getCurrentSwitchTime()-10);
            //game.b.setQuickIsActive(true);
            t.changeTime(10);
        }
    }
    public String getExplanation() {
        return "Quick: Reduziere die Zeit bis zum nächsten Switch um 10 Sekunden\n(Nicht wirksam, wenn weniger als 11 Sekunden verbleiben). (15 Gil)";
    }

    public boolean enoughGils(int gils) {
        if (gils >= 15 && game.b.lockIsActivated() == false) {return true;} else {return false;}
    }

    public int getTimer() {
        return 400;
    }
}

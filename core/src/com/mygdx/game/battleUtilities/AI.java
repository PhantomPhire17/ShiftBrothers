package com.mygdx.game.battleUtilities;

import com.mygdx.game.Ability;
import com.mygdx.game.interfaces.Constants;
import com.mygdx.game.Fighter;

public class AI implements Constants {

    private int action;

    public AI() {
    }

    public void setActionRate(int probability) {
        action = 100 - probability;
    }

    private boolean isActive = false;

    public boolean isActive() {
        rint = rgen.nextInt(0, 100);
        if (rint > action) {
            return true;
        } return false;
    }

    private int rint;

    public Ability getNextAbility(Fighter player, Fighter enemy) {
        if (rint > action) {
            if ((enemy.getHP() < 300)) {
                if (enemy.getGil() > 10) {
                    return cure1;
                } else {
                    if (enemy.getGil() >= 6) {
                        return trd;
                    } else {
                        return prt;
                    }
                }
            }
            if (player.getHP() < 600) {
                if (rgen.nextInt() > 0) {
                    return att;
                } else {
                    return tmp;
                }
            }
            if ((t.getSeconds() < 5) && (enemy.getGil() > 10)) {
                if (enemy.getGil() > 20) {
                    return don2;
                } else {
                    return don1;
                }
            }
            if (player.getDefense() > enemy.getDefense() + 10) return prt;
            if (player.getAttack() > enemy.getAttack() + 10) return tmp;
            if (enemy.getHP() > 1400) {
                if (rgen.nextInt() > 0) {
                    return hrm;
                } else {
                    return tmp;
                }
            }
            if (rgen.nextInt() > 0) {
                if (enemy.getGil() >= 20) {
                    return rand;
                } else {
                    return att;
                }

            }
            return att;
        }
        return att;
    }
}

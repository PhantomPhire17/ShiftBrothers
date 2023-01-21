package com.mygdx.game.battleUtilities;

public class BackendFunctions {

    public BackendFunctions() {}

    private boolean gameHasStarted = false;
    public void setNewGame(boolean b) {
        gameHasStarted = b;
    }

    public boolean gameHasStarted() {
        return gameHasStarted;
    }

    private int curTimer;
    private int timer;
    public void setCurrentSwitchTime(int i) {
        if (quickIsActive) {curTimer = i - 10; } else {
            curTimer = i;
        }
    }

    public int getCurrentSwitchTime() {
        return curTimer;
    }
    public void setSwitchTime(int i) {
        if (lockActivated == false)
            timer = i;
        if (timer < 3) timer = 3;
    }

    public int getSwitchTime() {
        return timer;
    }

    //--------LOCK-------

    boolean lockActivated = false;
    public void setLocked(boolean b) {
        lockActivated = b;
    }

    public boolean lockIsActivated() {
        return lockActivated;
    }

    //-------QUICK--------

    boolean quickIsActive = false;

    public boolean quickIsActive() {
        return quickIsActive;
    }

    public void setQuickIsActive(boolean b) {
        quickIsActive = b;
    }


    //--------RANDOM------

    boolean randomTime = false;
    public void setRandomTime(boolean b) {
        randomTime = b;
    }
    public boolean timeIsRandom() {
        return randomTime;
    }


    //---------SET MAX TIME-------

    private int maxTime;
    public void setMaxTime(int time) {
        maxTime = time;
    }

    public int getMaxTime() {
        return maxTime;
    }
}

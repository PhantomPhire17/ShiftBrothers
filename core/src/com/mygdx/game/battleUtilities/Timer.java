package com.mygdx.game.battleUtilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.interfaces.Constants;
import com.mygdx.game.KLabel;

public class Timer implements Constants {

    public Timer() {}

    private int seconds;
    private boolean useFPS = false;
    private boolean timeOver = false;
    private KLabel timeLabel;
    private int frame = 0;
    public void useCurrentFPS(boolean b) {
        useFPS = b;
    }

    public boolean timeIsOver() {
        return timeOver;
    }

    public void setTimeOver(boolean b) {
        timeOver = b;
    }

    public int getSeconds() {
        return seconds;
    }

    public void drawTimer(SpriteBatch batch) {
        if (isStatic == true) frame++;
        // seconds = harald.getSwitchTime() - frame/30;
        if (b.gameHasStarted() == true) {
            seconds = b.getSwitchTime();
        } else {
            seconds =  b.getCurrentSwitchTime();
        }
        timeLabel = new KLabel("" + seconds);
        if (b.timeIsRandom()==false) {
            timeLabel.drawLabel("" + seconds, Gdx.graphics.getWidth()/2, CLOCK_Y, batch); }
        else {
            timeLabel.drawLabel("?", Gdx.graphics.getWidth()/2, CLOCK_Y, batch);
        }
        if (frame%30 == 0) {
            if (b.gameHasStarted() == true) { b.setNewGame(false); }
            b.setCurrentSwitchTime(b.getSwitchTime() - frame/30);
        }
        if (seconds == -1) {
            timeOver = true;
            frame = 0;
            b.setCurrentSwitchTime(b.getSwitchTime());
        }
    }

    boolean isStatic = false;
    public void setStatic(boolean b) {
        isStatic = b;
    }
}

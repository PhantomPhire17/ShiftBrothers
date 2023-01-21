package com.mygdx.game.battleUtilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.interfaces.Constants;
import com.mygdx.game.KLabel;

public class Timer implements Constants {

    private int maxTime;
    public Timer() {
    }

    public void setMaxTime(int maxTime) {
        this.maxTime = maxTime;
    }

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
        frame++;
        // seconds = harald.getSwitchTime() - frame/30;
        if (game.b.gameHasStarted() == true) {
            seconds = maxTime;
        } else {
            seconds =  game.b.getCurrentSwitchTime();
        }
        timeLabel = new KLabel("" + seconds);
        if (game.b.timeIsRandom()==false) {
            timeLabel.drawLabel("" + seconds, Gdx.graphics.getWidth()/2, CLOCK_Y, batch); }
        else {
            timeLabel.drawLabel("?", Gdx.graphics.getWidth()/2, CLOCK_Y, batch);
        }
        if (frame%FPS == 0) {
            if (game.b.gameHasStarted() == true) { game.b.setNewGame(false); }
            game.b.setCurrentSwitchTime(maxTime - frame/FPS);
        }
        if (seconds < 0) {
            timeOver = true;
            frame = 0;
            game.b.setCurrentSwitchTime(maxTime);
        }
    }

    public void changeTime(int time) {
        frame = frame + time * FPS;
    }

    boolean isStatic = false;
    public void setStatic(boolean b) {
        isStatic = b;
    }
}

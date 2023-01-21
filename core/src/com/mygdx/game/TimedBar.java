package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.interfaces.Constants;

public class TimedBar extends Rect implements Constants {

    private int frames;
    private int y;
    private int width;
    private int maxWidth = 360;
    private int height;
    private int counter = 0;
    private int frameCounter = 0;
    private Direction dir = Direction.BACKWARDS;
    private Fighter fighter;


    public TimedBar(Fighter fighter) {
        this.fighter = fighter;
        if (fighter == gustav) x = P2_X;
    }

    public int getRemovedWidthPerFrame() {
        return getRectWidth()/frames;
    }

    public void setCounter(int i) {
        counter = i;
    }

    public enum Direction { FORWARDS, BACKWARDS };

    public void setDirection(Direction dir) {
        this.dir = dir;
    }
  /*  public void drawTimedBar(ShapeRenderer shape) {
        if (width > 0) {
            if (dir == Direction.FORWARDS) {
                x = getRectX() + Gdx.graphics.getFramesPerSecond()/2;
                this.setRectX(x);
            }
            width = this.getRectWidth() - Gdx.graphics.getFramesPerSecond()/2;
            this.setRectWidth(width);
        } else {
            width = maxWidth;
            fighter.setActive(true);
        }
    }*/
    private int x = P1_X;
    public void drawTimedBar(ShapeRenderer shape) {
            this.setDimensions(x, P_Y + 300, width, 30);
            this.draw(shape);
            if (width > 0) {
                if (fighter == gustav) x = x + FPS/(20-fighter.getSpeed());
                width = width - FPS/(20-fighter.getSpeed());
            } else {
                //haraldBarCounter = 30;
               // harald.getTempAbility().run(harald,gustav);
                width = 360;
                if (fighter == gustav) x = P2_X;
                if (fighter == harald) fighter.getFixedAbility().run(harald, gustav);
                if (fighter == gustav) fighter.getFixedAbility().run(gustav, harald);
                fighter.setActiveTimer(false);
                fighter.setABLocked(false);
            }


    }
    public int getCounter() {
        return counter;
    }

}

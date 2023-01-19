package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.game.interfaces.Constants;

public class KLabel extends KObject implements Constants {

    private int x = 0;
    private int y = 0;
    private SpriteBatch batch = new SpriteBatch();

    private CharSequence text;

    //---------------------------------------
    //---------------------------------------
    //---------------------------------------
    //---------------------------------------
    //---0. CONSTRUCTORS------------------------------
    //---------------------------------------
    //---------------------------------------
    //---------------------------------------
    //---------------------------------------

    public KLabel(CharSequence text) {
        this.text = text;
    }
    public KLabel(CharSequence text, int x, int y) {
        this.x = x;
        this.y = y;
        this.text = text;
    }

    public KLabel() {}

    //---------------------------------------
    //---------------------------------------
    //---------------------------------------
    //---------------------------------------
    //---1. GETTER------------------------------
    //---------------------------------------
    //---------------------------------------
    //---------------------------------------
    //---------------------------------------

    public String getText() {
        return text.toString();
    }


    //---------------------------------------
    //---------------------------------------
    //---------------------------------------
    //---------------------------------------
    //---2. SETTER------------------------------
    //---------------------------------------
    //---------------------------------------
    //---------------------------------------
    //---------------------------------------

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    float size = 2.0f;
    public void setSize(float size) {
        this.size = size;
    }

    //---------------------------------------
    //---------------------------------------
    //---------------------------------------
    //---------------------------------------
    //---3. DRAW------------------------------
    //---------------------------------------
    //---------------------------------------
    //---------------------------------------
    //---------------------------------------

    private BitmapFont font = new BitmapFont();

    public void drawLabel(int x, int y, SpriteBatch batch) {
          font.getData().setScale(size);
          font.draw(batch, text, x, y);
         // renderer.draw(font, text, x, y);
    }
    public void drawLabel(String text, int x, int y, SpriteBatch batch) {
          font.getData().setScale(size);
          font.draw(batch, text, x, y);
         // renderer.draw(font, text, x, y);
    }

    //---------------------------------------
    //---------------------------------------
    //---------------------------------------
    //---------------------------------------
    //---4. GENERAL------------------------------
    //---------------------------------------
    //---------------------------------------
    //---------------------------------------
    //---------------------------------------
}

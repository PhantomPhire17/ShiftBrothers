package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.interfaces.Constants;

public class Rect extends KObject implements Constants {

    //1. VARIABLES
    //1.1 Integer

    private int x;
    private int y;
    private int width;
    private int height;
    private int thickness = 1;

    //1.2 String
    private CharSequence text = "DEFAULT";

    //1.3. Color

    private Color color = Color.WHITE;
    private Color frameColor = Color.WHITE;

    //1.4 Boolean

    private boolean frameIsColored = true;
    private boolean isVisible = true;

    //1.5 Project Classes
    private KLabel label = new KLabel("Default");
    private Ability ab = new Ability();

    //1.6 Renderer
    private ShapeRenderer shape;

    //---------------------------------------
    //---------------------------------------
    //---------------------------------------
    //---------------------------------------
    //---0. CONSTRUCTORS------------------------------
    //---------------------------------------
    //---------------------------------------
    //---------------------------------------
    //---------------------------------------

    public Rect() {}
    public Rect(int x, int y, int width, int height) {
        super();
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
       // label = new KLabel(text, batch);
    }

    //---------------------------------------
    //---------------------------------------
    //---------------------------------------
    //---------------------------------------
    //---1. GETTER------------------------------
    //---------------------------------------
    //---------------------------------------
    //---------------------------------------
    //---------------------------------------

    public int getRectX() {return x;}
    public int getRectY() {
        return y;
    }
    public int getRectWidth() {return width;}
    public int getRectHeight() {
        return height;
    }
    public Ability getAbility() { return ab; }
    public boolean isVisible() {
        return isVisible;
    }

    public KLabel getLabel() {
        return label;
    }

    public int getXOffset() {
        String s = (String) text;
        return s.length()*4;
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

    public void setPosition(int x, int y) { this.x = x; this.y = y; }
    public void setSize(int width, int height) { this.width = width; this.height = height;}
    public void setDimensions(int x, int y, int width, int height) {this.x = x; this.y = y; this.width = width; this.height = height;}
    public void setColor(Color color) {
        this.color = color;
    }
    public void setVisible(boolean b) {
        isVisible = b;
    }
    public void setLabel(CharSequence text, int rectX, int rectY) {
        label = new KLabel(text, rectX + width/2, rectY + height/2);
    }
    public void setLabel(CharSequence text) {
        label = new KLabel(text);
    }

    public void setAbility(Ability ab) { this.ab = ab; }

    //---------------------------------------
    //---------------------------------------
    //---------------------------------------
    //---------------------------------------
    //---3. DRAW------------------------------
    //---------------------------------------
    //---------------------------------------
    //---------------------------------------
    //---------------------------------------

    public void draw(ShapeRenderer shape) {
        if (isVisible == true) {
            shape.setColor(color);
            shape.rect(x,y-height,width,height);
            if (frameIsColored == true) {
            }
        }
    }

    public void drawLabel(SpriteBatch batch) {
        label.drawLabel(x + width/4, y - height/2, batch);
    }

    //---------------------------------------
    //---------------------------------------
    //---------------------------------------
    //---------------------------------------
    //---UNUSED------------------------------
    //---------------------------------------
    //---------------------------------------
    //---------------------------------------
    //---------------------------------------

    public void setFrameColor(Color frameColor, int thickness) {
        this.frameColor = frameColor;
        this.thickness = thickness;
        frameIsColored = true;
    }
    public void setFrameColor(Color frameColor) {
        this.frameColor = frameColor;
        frameIsColored = true;
    }
    public void setThickness(int thickness) {
        this.thickness = thickness;
    }

    public void setAbility(Ability ab, int x, int y) {
        this.ab = ab;
        setLabel(ab.getName(), x, y);
    }

    protected void setRectWidth(int width) {
        this.width = width;
    }

    public void setRectX(int x) {
        this.x = x;
    }
}


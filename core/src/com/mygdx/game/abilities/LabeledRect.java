package com.mygdx.game.abilities;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.KLabel;
import com.mygdx.game.Rect;

public class LabeledRect extends Rect {

    ShapeRenderer shape = new ShapeRenderer();
    SpriteBatch batch = new SpriteBatch();
    Rect rect;

    public LabeledRect(int x, int y, int width, int height, ShapeRenderer shape) {
        this.shape = shape;
        rect = new Rect();
        rect.setDimensions(x,y,width,height);
    }

    public LabeledRect() {
        rect = new Rect();
    }

    public void setColor(Color color) {
        rect.setColor(color);
    }

    public int getRectX() {
        return rect.getRectX();
    }

    public int getRectY() {
        return rect.getRectY();
    }
    public void drawLabeledRect() {
        rect.draw(shape);
    }

    public void setLabel(CharSequence text, SpriteBatch batch) {
        this.batch = batch;
        int x = rect.getRectX();
        int y = rect.getRectY();
        int rectWidth = rect.getRectWidth();
        int length = text.length();
        String s = (String) text;
    }
}

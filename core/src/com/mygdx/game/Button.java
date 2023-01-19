package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Button extends Rect {
    private int x;
    private int y;
    private int ex;
    private int ey;
    private int width;
    private int height;
    private KLabel lab;

    public Button(){
        super();
    }

    public KLabel getLabel() {
        return lab;
    }

    public void setLabel(KLabel lab) {
        this.lab = lab;
    }

    public void setLabel(CharSequence text) {
        lab = new KLabel(text);
    }

    public void drawLabel(SpriteBatch batch) {
       // lab.drawLabel(centerX(), centerY(), batch);
    }
    public int centerX() {
       return x + width/2;// - lab.getText().length()/2;
    }

    public int centerY() {
        return y - height/2;
    }
    Ability ab;

    public void setMouseListeners(int ex, int ey) {
        this.ex = ex;
        this.ey = ey;
    }

    public boolean isButton(int ex, int ey) {
        if ((ex > getRectX()) && (ex < getRectX() + getRectWidth()) && (ey > getRectY() - getRectHeight()) && (ey < getRectY())) {
            return true;
        } else {
            return false;
        }
    }

    private Color highlightcolor;
    public void setHighlightColor(Color color) {
        this.highlightcolor = color;
    }
}

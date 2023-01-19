package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.interfaces.Constants;

public class MLabel extends KLabel implements Constants {

    private String superString = "";

    public MLabel(Ability[] abilities) {
        super();
        fillString(abilities);
    }

    public MLabel(String text) {
        super();
        superString = text;
    }

    public void fillString(Ability[] abilities) {
        for (int i=0; i<abilities.length; i++) {
            superString = superString + abilities[i].getName() + "\n\n\n";
        }
    }

    public void drawLabel(int x, int y, SpriteBatch batch) {
        this.setSize(2.0f);
        this.drawLabel(superString, x , y,batch);
    }

    public String getTotalString() {
        return "";
    }
}

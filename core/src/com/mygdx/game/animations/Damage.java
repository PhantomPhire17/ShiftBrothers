package com.mygdx.game.animations;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Animation;
import com.mygdx.game.interfaces.Constants;
import com.mygdx.game.Fighter;

public class Damage extends Animation implements Constants {


    public Damage() {
    }

    public void draw(int x, int y, Fighter fighter, SpriteBatch batch) {
        if (fighter == harald) {
         //   batch.draw(hartex, x-50, y);
        } else {
         //   batch.draw(gustex, x+50, y);
        }
    }

    public int getTimer() {
        return 10;
    }

}

package com.mygdx.game.rendering;

import com.badlogic.gdx.utils.ScreenUtils;

public class Clear {

    public Clear() {}

    public void clearColor(float r, float g, float b, float a) {
        ScreenUtils.clear(0f, 0.1f, 0, 1);
    }
}

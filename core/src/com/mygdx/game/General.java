package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.abilities.LabeledRect;

import java.util.ArrayList;

public class General {


    public General() {}


    public ArrayList<Object> add(Object[] objects) {
        ArrayList<Object> list = new ArrayList<>();
        for (int i=0; i< objects.length; i++) {
            list.add(objects[i]);
        }
        return list;
    }

    public ArrayList<Rect> fillRects(int n, SpriteBatch batch) {
        ArrayList<Rect> rectList = new ArrayList<>();
        for (int i=0; i<n; i++) {
            rectList.add(new Rect());
        }
        return rectList;
    }

    public ArrayList<Integer> fillInt(int n) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i=0; i<n; i++) {
            list.add(i);
        }
        return list;
    }

    public ArrayList<Ability> fillAbilities(int n) {
        ArrayList<Ability> list = new ArrayList<>();
        for (int i=0; i<n; i++) {
            list.add(new Ability());
        }
        return list;
    }
}

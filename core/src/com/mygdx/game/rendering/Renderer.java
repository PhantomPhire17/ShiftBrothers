package com.mygdx.game.rendering;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.KObject;
import com.mygdx.game.Rect;

import java.util.ArrayList;

public class Renderer {

    private SpriteBatch batch;
    private ShapeRenderer shape;

    private ArrayList<KObject> batchObjects = new ArrayList<KObject>();
    private ArrayList<KObject> shapeObjects = new ArrayList<KObject>();
    public Renderer() {}

    public void begin() {
        batch = new SpriteBatch();
        shape = new ShapeRenderer();
    }

    public void draw(KObject object) {
        if (object instanceof Rect) {
            shapeObjects.add(object);
        } else {
            batchObjects.add(object);
        }
    }

    public void end() {
        shape.begin(ShapeRenderer.ShapeType.Filled);
        for (int i=0; i<shapeObjects.size(); i++) {
            shapeObjects.get(i).draw(shape);
        }
        shape.end();
        batch.begin();
        for (int i=0; i<batchObjects.size(); i++) {
            batchObjects.get(i).draw(batch);
        }
        batch.end();
    }
}

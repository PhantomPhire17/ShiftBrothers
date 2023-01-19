package com.mygdx.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.interfaces.Constants;
import com.mygdx.game.screens.BattleScreen;
import com.mygdx.game.screens.StartScreen;

public class Game extends com.badlogic.gdx.Game implements Constants {

	public Game() {}
	public SpriteBatch batch;
	public ShapeRenderer shape;

	BattleScreen screen;
	StartScreen start;
	int ex;
	int ey;

	OrthographicCamera camera;



	@Override
	public void create () {
		screen = new BattleScreen(this);
		start = new StartScreen(this);
		batch = new SpriteBatch();
		shape = new ShapeRenderer();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		//img = new Texture("badlogic.jpg");
		setScreen(start);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		shape.dispose();
	}
}

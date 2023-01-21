package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.*;
import com.mygdx.game.interfaces.Constants;

public class GameOverScreen extends ScreenAdapter implements Constants {

    Game game;
    Fighter winner;
    boolean playerHasWon;
    String gameOver;
    KLabel gOLabel;
    ShapeRenderer shape;
    Button btn;
    public GameOverScreen(Game game, Fighter winner, ShapeRenderer shape, boolean oneplayer, int player) {
        this.shape = shape;
        this.game = game;
        this.winner = winner;
        this.playerHasWon = playerHasWon;
        gOLabel = new KLabel();
        btn = new Button();
        btn.setLabel("Play Again");
        btn.setDimensions(Gdx.graphics.getWidth()/2 - 180, 300, 300, 100);
        btn.setColor(Color.BLUE);
        if (oneplayer == true && player <= 1) { gameOver = "VICTORY!"; } else { gameOver = "GAME OVER... \n\nTRY AGAIN"; }
        if (oneplayer == false && player <=1) { gameOver = "CONGRATULATIONS, PLAYER 1!"; } else { gameOver = "CONGRATULATIONS, PLAYER 2!"; }
    }
    int ex;
    int ey;

    boolean setNewScreen = false;
    public void render(float delta) {
        if (setNewScreen == true) game.setScreen(new StartScreen(game));
        ex = Gdx.input.getX();
        ey = Gdx.graphics.getHeight() - Gdx.input.getY();
        ScreenUtils.clear(0, 0, 0, 1);
        shape.begin(ShapeRenderer.ShapeType.Filled);
        btn.draw(shape);
        if (Gdx.input.isTouched() && btn.isButton(ex,ey)) {
            harald.setMaxHP(2000);
            harald.setHP(2000);
            harald.setAttack(20);
            harald.setDefense(10);
            gustav.setMaxHP(2000);
            gustav.setHP(2000);
            gustav.setAttack(20);
            gustav.setDefense(10);
            setNewScreen = true;
        }
        shape.end();
        game.batch.begin();
        btn.getLabel().drawLabel(Gdx.graphics.getWidth()/2 - 100, 260, game.batch);
        if (winner == gustav) { game.batch.draw(gustex, P2_X, P_Y); } else { game.batch.draw(hartex, P1_X, P_Y); }
        gOLabel.drawLabel(gameOver, Gdx.graphics.getWidth() / 2 - gameOver.length(), CLOCK_Y, game.batch);
        game.batch.end();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}

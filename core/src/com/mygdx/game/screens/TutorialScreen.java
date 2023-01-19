package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Button;
import com.mygdx.game.interfaces.Constants;
import com.mygdx.game.Game;

public class TutorialScreen extends ScreenAdapter implements Constants {

    Game game;
    String tutorialtext;
    public TutorialScreen(Game game) {
        this.game = game;
        fillTutorialText();
        tutorial.setLabel(tutorialtext);
        tutorial.setDimensions(Gdx.graphics.getWidth()/2 - 180, 300, 300, 100);
        tutorial.setColor(Color.BLUE);
        blitzkrieg.setLabel("Blitzkrieg\n(11 Sekunden)");
        blitzkrieg.setDimensions(Gdx.graphics.getWidth()/2 - 180, 300, 300, 100);
        blitzkrieg.setColor(Color.BLUE);
        gemetzel.setLabel("Standard\n(33 Sekunden)");
        gemetzel.setDimensions(Gdx.graphics.getWidth()/2 - 180, 300, 300, 100);
        gemetzel.setColor(Color.ORANGE);
        kaffeklatsch.setLabel("Kaffeeklatsch\n(55 Sekunden)");
        kaffeklatsch.setDimensions(Gdx.graphics.getWidth()/2 - 180, 300, 300, 100);
        kaffeklatsch.setColor(Color.BLUE);
    }

    public void fillTutorialText() {
        tutorialtext = "Zwei Brüder wollen sich verkloppen.\nDoch leider sind sie Shift Brothers und\n tauschen alle 33 Sekunden ihre Körper.\n\n Ziel: Setze die HP deines Gegners auf 0.\n In wessen Körper du am Ende überlebst,\n spielt keine Rolle.\n\n Sobald du mit deinem Gegner Körper tauscht,\n übernimmst du alle seine Werte:\n HP, Angriffsstärke, Verteidigung,\n und selbstverständlich sein Geld\n (auf das du natürlich ganz liebevoll aufpasst).";
    }

    Button tutorial;
    Button blitzkrieg;
    Button gemetzel;
    Button kaffeklatsch;
    int ex;
    int ey;
    boolean setNewScreen = false;
    public void render(float delta) {
        if (setNewScreen == true) game.setScreen(new GameOverScreen(game, harald, game.shape, true));
        ex = Gdx.input.getX();
        ey = Gdx.graphics.getHeight() - Gdx.input.getY();
        ScreenUtils.clear(0, 0, 0, 1);
        game.shape.begin(ShapeRenderer.ShapeType.Filled);
        for (int i=0; i<3; i++) displayButton(i);
        if (Gdx.input.isTouched() && blitzkrieg.isButton(ex,ey)) {
            b.setSwitchTime(11);
            setNewScreen = true;
        }
        if (Gdx.input.isTouched() && gemetzel.isButton(ex+100,ey+300)) {
            b.setSwitchTime(33);
            //setNewScreen = true;
        }
        if (Gdx.input.isTouched() && kaffeklatsch.isButton(ex,ey)) {
            b.setSwitchTime(55);
            setNewScreen = true;
        }
        game.shape.end();
        game.batch.begin();
        game.batch.end();
    }

    public void displayButton(int i) {

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

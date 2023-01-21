package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Button;
import com.mygdx.game.interfaces.Constants;
import com.mygdx.game.Game;

public class StartScreen extends ScreenAdapter implements Constants {

    Game game;
    Texture bg;
    Texture logo;
    Button btn1P, btn2P;

    Button tutorial;
    Button blitzkrieg;
    Button gemetzel;
    Button kaffeklatsch;
    String tutorialtext;
    int ex;
    int ey;
    int width = 400;
    int btnx1 = 300;
    int btnx2 = Gdx.graphics.getWidth()/2-width/2;
    int btnx3 = Gdx.graphics.getWidth()-btnx1-width;
    Button interval;
    public StartScreen(Game game) {
        if (harald.isDead()) harald.revive();
        if (gustav.isDead()) gustav.revive();
        bg = new Texture("bg.jpg");
        btn1P = new Button();
        btn2P = new Button();
        tutorial = new Button();
        interval = new Button();
        blitzkrieg = new Button();
        gemetzel = new Button();
        kaffeklatsch = new Button();
        btn1P.setLabel("1 Player");
        btn1P.setDimensions(Gdx.graphics.getWidth()/2 - 150, 350, 300, 100);
        btn1P.setColor(Color.BLUE);
        btn2P.setLabel("2 Players");
        btn2P.setDimensions(Gdx.graphics.getWidth()/2 - 150, 200, 300, 100);
        btn2P.setColor(Color.BLUE);
        logo = new Texture("shiftbrothersalpha.png");
        this.game = game;

        fillTutorialText();
        tutorial.setLabel(tutorialtext);
        tutorial.setDimensions(Gdx.graphics.getWidth()/2 - 300, 900, 600, 550);
        tutorial.setColor(Color.BLUE);
        interval.setLabel("Wähle das Zeitintervall, nach dem die Spieler ihre Körper tauschen:");
        interval.setDimensions(btnx1, 350, Gdx.graphics.getWidth()-600, 50);
        interval.setColor(Color.BLUE);
        blitzkrieg.setLabel("    Blitzkrieg\n(11 Sekunden)");
        blitzkrieg.setDimensions(btnx1, 200, width, 100);
        blitzkrieg.setColor(Color.BLUE);
        gemetzel.setLabel("    Standard\n(33 Sekunden)");
        gemetzel.setDimensions(btnx2, 200, width, 100);
        gemetzel.setColor(Color.ORANGE);
        kaffeklatsch.setLabel(" Kaffeeklatsch\n(55 Sekunden)");
        kaffeklatsch.setDimensions(btnx3, 200, width, 100);
        kaffeklatsch.setColor(Color.BLUE);
    }

    public void fillTutorialText() {
        tutorialtext = "Zwei Brüder wollen sich verkloppen.\nDoch leider sind sie Shift Brothers und\ntauschen alle 33 Sekunden ihre Körper.\n\n Ziel: Setze die HP deines Gegners auf 0.\n In wessen Körper du am Ende überlebst,\n spielt keine Rolle.\n\n Sobald du mit deinem Gegner Körper\n tauscht, übernimmst du alle seine Werte:\n HP, Angriffsstärke, Verteidigung,\n und selbstverständlich sein Geld\n (auf das du natürlich ganz\n liebevoll aufpasst).";
    }

    boolean startscreen = true;
    boolean setNewScreen = false;
    private int maxTime;
    private boolean oneplayer = true;
    public void render(float delta) {
        if (startscreen == true) {
            ex = Gdx.input.getX();
            ey = Gdx.graphics.getHeight() - Gdx.input.getY();
            ScreenUtils.clear(0, 0, 0, 1);
            game.batch.begin();
            if (Gdx.input.isTouched() && btn1P.isButton(ex,ey)) {
                startscreen = false;
            }
            if (Gdx.input.isTouched() && btn2P.isButton(ex,ey)) {
                startscreen = false;
                oneplayer = false;
            }
            game.batch.draw(bg, 0,0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            game.batch.draw(logo, Gdx.graphics.getWidth()/2 - logo.getWidth()/2, 600);
            game.batch.draw(hartex, Gdx.graphics.getWidth()/2 - logo.getWidth()/2 - 180, 600);
            game.batch.draw(gustex, Gdx.graphics.getWidth()/2 + logo.getWidth()/2 + 180 - gustex.getWidth(), 600);
            game.batch.end();
            game.shape = new ShapeRenderer();
            game.shape.begin(ShapeRenderer.ShapeType.Filled);
            btn1P.draw(game.shape);
            btn2P.draw(game.shape);
            game.shape.end();
            game.batch.begin();
            btn1P.getLabel().drawLabel(Gdx.graphics.getWidth()/2 - 30, 310, game.batch);
            btn2P.getLabel().drawLabel(Gdx.graphics.getWidth()/2 - 30, 160, game.batch);
            game.batch.end();
        } else {
            if (setNewScreen==true) {
                if (oneplayer == true) {
                    game.setScreen(new BattleScreen(maxTime, game));
                } else {
                    game.setScreen(new TwoPlayerScreen(maxTime, game));
                }
            }
            ex = Gdx.input.getX();
            ey = Gdx.graphics.getHeight() - Gdx.input.getY();
            ScreenUtils.clear(0, 0, 0, 1);
            game.batch.begin();
            game.batch.draw(bg, 0,0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            game.batch.end();
            game.shape.begin(ShapeRenderer.ShapeType.Filled);
            for (int i=0; i<3; i++) displayButton(i);
            if (Gdx.input.isTouched() && blitzkrieg.isButton(ex,ey)) {
                maxTime = 11;
                setNewScreen = true;
            }
            if (Gdx.input.isTouched() && gemetzel.isButton(ex,ey)) {
                maxTime = 33;
                setNewScreen = true;
            }
            if (Gdx.input.isTouched() && kaffeklatsch.isButton(ex,ey)) {
                maxTime = 55;
                setNewScreen = true;
            }
            tutorial.draw(game.shape);
            //interval.draw(game.shape);
            blitzkrieg.draw(game.shape);
            gemetzel.draw(game.shape);
            kaffeklatsch.draw(game.shape);
            game.shape.end();
            game.batch.begin();
            tutorial.getLabel().drawLabel(Gdx.graphics.getWidth()/2-270, 880, game.batch);
            blitzkrieg.getLabel().drawLabel(btnx1 + width/2-90, 180, game.batch);
            gemetzel.getLabel().drawLabel(btnx2+ width/2-90, 180, game.batch);
            kaffeklatsch.getLabel().drawLabel(btnx3+ width/2-90, 180, game.batch);
            interval.getLabel().drawLabel(btnx1 + 250, 270, game.batch);
            game.batch.end();
        }

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

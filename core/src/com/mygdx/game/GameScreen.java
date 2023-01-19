package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.abilities.Attack;
import com.mygdx.game.interfaces.Constants;

import java.util.ArrayList;

public class GameScreen extends ScreenAdapter implements Constants {

    Attack at = new Attack();
    private float delta;

    private Ability ab;
    private KLabel timeLabel;
    TextField tf;
    General c = new General();
    Texture img;

    boolean drawRect = false;
    boolean hey = false;
    boolean buddy = false;
    KTable tab1;
    KTable tab2;

    private Color color = Color.GREEN;
    private Color alpha = new Color(1,1,1,0.3f);
    private ArrayList<Integer> invisibleRects = new ArrayList<>();
    Rect testrect;
    Label lab;
    KLabel clickedAbility;
    int ex=200;
    int ey=400;
    int n = 12;
    int nx;
    int ny;
    int h = Gdx.graphics.getHeight();

    private Game game;

    public GameScreen(Game game) {
        this.game = game;
        invisibleRects = c.fillInt(n);
        SHAPE.begin(ShapeRenderer.ShapeType.Filled);
        setUp();
    }

    //---------------------------------------
    //---------------------------------------
    //---------------------------------------
    //---------------------------------------

    //1. SETUP

    //---------------------------------------
    //---------------------------------------
    //---------------------------------------
    //---------------------------------------

    private void setUp() {
        addFighters();
        addTables();
    }

    private void addFighters() {}

    private void addTables() {
        for (int i=0; i<5; i++) {
            tab1 = new KTable(game);
            tab2 = new KTable(game);
        }
    }

    //---------------------------------------
    //---------------------------------------
    //---------------------------------------
    //---------------------------------------

    //2. RENDER

    //---------------------------------------
    //---------------------------------------
    //---------------------------------------
    //---------------------------------------

    @Override
    public void render (float delta) {
        this.delta = delta;
        ScreenUtils.clear(0, 0, 0.3f, 1);
        game.shape.begin(ShapeRenderer.ShapeType.Filled);
        drawShapes();
        game.shape.end();
        game.batch.begin();
        drawTextures();
        game.batch.end();

    }

    //2.1 RENDER SHAPES

    //---------------------------------------
    //---------------------------------------
    //---------------------------------------
    //---------------------------------------

    public void drawShapes() {
        testingShapes();
        drawTables();
    }

    public void drawTables() {
            checkMouseActions();
            tab1.setMouseListener(nx, ny);
            tab2.setMouseListener(nx, ny);
            setTableColors();
            tab1.drawTable(T1_X, T_Y, 100, 50, tab1.getRectNumber(), 5, game.shape);
            tab2.drawTable(T2_X, T_Y, 100, 50, 1, 5, game.shape);

    }

    public void setTableColors() {
        tab1.setHighlightColor(new Color(0.5f, 0.5f, 1, 1));
        tab2.setHighlightColor(new Color(1, 0.5f, 0.5f, 1));
        tab1.setColor(new Color(0, 0.1f, 0.6f, 1));
        tab2.setColor(new Color(0.6f, 0f, 0, 1));
        tab2.setSecondaryColor(new Color(0.8f, 0, 0, 1));
        tab1.setHighlightedRects(false);
        if (tab1.isTable() == true) {
            tab1.setHighlightedRects(true);
        }
    }

    //2.2 RENDER TEXTURES AND LABELS

    //---------------------------------------
    //---------------------------------------
    //---------------------------------------
    //---------------------------------------

    public void drawTextures() {
        testingTextures();
        nx = Gdx.input.getX();
        ny = h - Gdx.input.getY();
        drawFighters();
        drawLabels();
        drawClockLabel();
    }

    int frame = 0;
    int seconds = 60;
    public void drawClockLabel() {
        frame++;
        seconds = 10 - frame/60;
        timeLabel = new KLabel("" + seconds);
        timeLabel.drawLabel(Gdx.graphics.getWidth()/2, 800, game.batch);
        if (seconds == -1) {
            frame = 0;
        }
    }

    public void checkMouseActions() {
        mouseIsClicked();
        mouseIsReleased();
    }

    public void drawFighters() {
        game.batch.draw(harald.getTexture(), P1_X, P_Y);
        game.batch.draw(gustav.getTexture(), P2_X, P_Y);
    }
    public void drawLabels() {
        drawTableLabels();

    }

    public void drawTableLabels() {
        tab1.drawLabels(game.batch);
    }

    //2.3 MOUSE EVENTS

    //---------------------------------------
    //---------------------------------------
    //---------------------------------------
    //---------------------------------------

    public void mouseIsClicked() {
        if(Gdx.input.isTouched()) {
            int n = tab1.getRectIndex(nx,ny);
            buddy = true;
            if ((tab1.isTable()==true)) {
                ab = tab1.getAbility(nx,ny);
                tab1.addRect(ab);
                //clickedAbility = new KLabel("" + tabs1[k].getAllRectsSize(), P1_X, HP_Y-25, game.batch);
            }
            at.run(harald,gustav);
            gustav.setLocked(true);
        }
    }

    public void mouseIsReleased() {
        if (!Gdx.input.isTouched()) {
            gustav.setLocked(false);
            tab1.setRectSperre(false);
            tab2.setRectSperre(false);
        }
    }

    @Override
    public void hide() {

    }

    public void testingShapes() {
        testrect = new Rect();
        testrect.setDimensions(300,900,100,300);
        testrect.setColor(Color.BLACK);
        testrect.setLabel("ThisAbility");
        testrect.draw(game.shape);
    }

    public void testingTextures() {
        int x = testrect.getRectX();
        int y = testrect.getRectY();
        testrect.drawLabel(game.batch);
    }

}

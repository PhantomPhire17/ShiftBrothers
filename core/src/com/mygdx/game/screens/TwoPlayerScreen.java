package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.*;
import com.mygdx.game.interfaces.Constants;

import java.util.ArrayList;

public class TwoPlayerScreen extends ScreenAdapter implements Constants {

    private Game game = new Game();
    // private boolean gameIsPaused = false;

    private Animation animaHarald = new Animation();
    private Animation animaGustav = new Animation();
    private Fighter player1 = harald;
    private Fighter player2 = gustav;
    private int btnsX1 = 20;
    private int btnsY = 800;
    private int ex, ey, switchCounter, haraldBarCounter, gustavBarCounter, frame, seconds;
    KLabel abNameHarald = new KLabel();
    KLabel abNameGustav = new KLabel();
    KLabel explanationLabelP1 = new KLabel();
    KLabel explanationLabelP2 = new KLabel();
    int animaCounterHarald, animaCounterGustav = -1;
    int damagedCounterHarald, damagedCounterGustav = 30;
    KLabel damageHarald = new KLabel();
    KLabel damageGustav = new KLabel();
    KLabel p1HP = new KLabel();
    KLabel p2HP = new KLabel();
    ArrayList<Button> btns1 = new ArrayList<Button>();
    ArrayList<Button> btns2 = new ArrayList<Button>();
    private int btnsNumber = 14;
    private int timedBarWidthHarald = 360;
    private int timedBarWidthGustav = 360;
    private int timedBarWidthPlayer = timedBarWidthHarald;
    private TimedBar timedBarHarald = new TimedBar(harald);
    private TimedBar timedBarGustav= new TimedBar(gustav);

    Texture bg;
    boolean gameOver = false;
    private boolean gustavLocked = false;
    private int maxTime;

    public TwoPlayerScreen(int maxTime, Game game) {
        this.game = game;
        this.maxTime = maxTime;
        t.setMaxTime(maxTime);
        setup();
    }

    public void setup() {
        gameStarted = true;
        setBackground();
        setAbilities();
        setButtonsP1();
        setButtonsP2();
        setSpeed();
        setExplanationLabels();
        maxTime = game.b.getMaxTime();
    }

    private void setExplanationLabels() {
        explanationLabelP1.setColor(Color.BLUE);
        explanationLabelP2.setColor(Color.RED);
    }

    private void setSpeed() {
        harald.setSpeed(10);
        gustav.setSpeed(10);
    }

    private void setBackground() {
        bg = new Texture("bg.jpg");
    }

    public void setAbilities() {
    }

    public void setButtonsP1() {
        for (int i = 0; i < btnsNumber; i++) {
            btns1.add(new Button());
            btns1.get(i).setDimensions(btnsX1 + 185 * (i / 7), (btnsY - (i % 7) * 110), 180, 80);
            btns1.get(i).setAbility(allAbilitiesP1[i]);
            btns1.get(i).setLabel(btns1.get(i).getAbility().getName());
            btns1.get(i).setMouseListeners(ex, ey);
            btns1.get(i).setColor(new Color(0, 0, 0.3f, 1));
        }
        timedBarHarald.setColor(Color.GREEN);
    }

    private void setButtonsP2() {
        for (int i = 0; i < btnsNumber; i++) {
            btns2.add(new Button());
            btns2.get(i).setDimensions(btnsX2 + 185 * (i / 7), (btnsY - (i % 7) * 110), 180, 80);
            btns2.get(i).setAbility(allAbilitiesP1[i]);
            btns2.get(i).setLabel(btns1.get(i).getAbility().getName());
            btns2.get(i).setMouseListeners(ex, ey);
            btns2.get(i).setColor(new Color(0.3f, 0, 0, 1));
        }
        timedBarGustav.setColor(Color.GREEN);
    }

    //-----------------------------------------
    //-----------------------------------------
    // -----------------------------------------
    //-----------------------------------------
    //RENDER
    //-----------------------------------------
    //-----------------------------------------
    // -----------------------------------------
    //-----------------------------------------

    KLabel lab;
    @Override
    public void render(float delta) {
        cl.clearColor(0.1f, 0,0,1);
        runGame();
        renderShapes();
        renderBatch();
        revertAnimations();
    }

    //2.1 RUN GAME

    //---------------------------------------
    //---------------------------------------
    //---------------------------------------
    //---------------------------------------

    public void runGame() {
        if ((harald.isDead() == false && gustav.isDead() == false)) {
            runPlayer1();
            if (ai.isActive()) runPlayer2();
        } else {
            gameOver = true;
            if (player1.isDead()) game.setScreen(new GameOverScreen(game, player2, game.shape, false, 2));
            if (player2.isDead()) game.setScreen(new GameOverScreen(game, player1, game.shape, false, 1));
        }
        runPlayer1();
        runPlayer1();
    }

    //2.1.1 RUN HARALD

    //---------------------------------------
    //---------------------------------------
    //---------------------------------------
    //---------------------------------------

    boolean w,a,s,d;
    public void runPlayer1() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.A)) {
            a = true;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.D)) {
            d = true;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
            w = true;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.S)) {
            s = true;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.CAPS_LOCK) || Gdx.input.isKeyJustPressed(Input.Keys.TAB) || Gdx.input.isKeyJustPressed(Input.Keys.SHIFT_LEFT)) {
            if (player1.abIsLocked() == false) {
                player1.setActiveTimer(true);
                player1.setFixedAbility(player1.getTempAbility());
                player1.setABLocked(true);
            }
        }
        if (!Gdx.input.isKeyJustPressed(Input.Keys.S) && s == true) {
            if (selectionP1 < btnsNumber-1) {
                selectionP1++;
                player1.setAbility(allAbilitiesP1[selectionP1]);
            }
            s = false;
        }
        if (!Gdx.input.isKeyJustPressed(Input.Keys.W) && w == true) {
            if (selectionP1 > 0) {
                selectionP1--;
                player1.setAbility(allAbilitiesP1[selectionP1]);
            }
            w = false;
        }
        if (!Gdx.input.isKeyJustPressed(Input.Keys.A) && a == true) {
            if (selectionP1 > 6) {
                selectionP1 = selectionP1 - 7;
                player1.setAbility(allAbilitiesP1[selectionP1]);
            }
            a = false;
        }
        if (!Gdx.input.isKeyJustPressed(Input.Keys.D) && d == true) {
            if (selectionP1 < 7) {
                selectionP1 = selectionP1 + 7;
                player1.setAbility(allAbilitiesP1[selectionP1]);
            }
            d = false;
        }
    }

    private int selectionP1;
    private int selectionP2;

    private boolean buttonIsClicked() {
        if (Gdx.input.isTouched()) {
            for (int i = 0; i < btnsNumber; i++) {
                if (btns1.get(i).isButton(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY())) {
                    player1.setAbility(allAbilitiesP1[i]);
                    if ((allAbilitiesP1[i] == quick || allAbilitiesP1[i] == rand) &&  game.b.lockIsActivated() == true)
                        return false;
                    return true;
                }
            }
        }
        return false;
    }

    //2.1.2 RUN GUSTAV

    //---------------------------------------
    //---------------------------------------
    //---------------------------------------
    //---------------------------------------

    private boolean up,down,left,right;
    private boolean abP1Locked, abP2Locked;
    public void runPlayer2() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            right = true;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            left = true;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            up = true;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            down = true;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER) || Gdx.input.isKeyJustPressed(Input.Keys.SHIFT_RIGHT) || Gdx.input.isKeyJustPressed(Input.Keys.CONTROL_RIGHT)) {
            player2.setActiveTimer(true);
            player2.setABLocked(true);
        }
        if (!Gdx.input.isKeyJustPressed(Input.Keys.DOWN) && down == true) {
            if (selectionP2 < btnsNumber-1) {
                selectionP2++;
                if (player2.abIsLocked() == false) player2.setAbility(allAbilitiesP2[selectionP2]);
            }
            down = false;
        }
        if (!Gdx.input.isKeyJustPressed(Input.Keys.UP) && up == true) {
            if (selectionP2 > 0) {
                selectionP2--;
                if (player2.abIsLocked() == false) player2.setAbility(allAbilitiesP2[selectionP2]);
            }
            up = false;
        }
        if (!Gdx.input.isKeyJustPressed(Input.Keys.LEFT) && left == true) {
            if (selectionP2 > 6) {
                selectionP2 = selectionP2 - 7;
                if (player2.abIsLocked() == false) player2.setAbility(allAbilitiesP2[selectionP2]);
            }
            left = false;
        }
        if (!Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) && right == true) {
            if (selectionP2 < 7) {
                selectionP2 = selectionP2 + 7;
                if (player2.abIsLocked() == false) player2.setAbility(allAbilitiesP2[selectionP2]);
            }
            right = false;
        }
    }

    public void revertAnimations() {
        revertDamageLabels();
        if (animaCounterHarald > -1) {
            if (animaCounterHarald == 0) {
                animaHarald = anima;
            } else {
                animaCounterHarald--;
            }
        }
        if (animaCounterGustav > -1) {
            if (animaCounterGustav == 0) {
                animaGustav = anima;
            } else {
                animaCounterGustav--;
            }
        }
    }

    public void revertDamageLabels() {
        if (harald.isHit()) {
            if (damagedCounterHarald == 0) {
                harald.setHit(false);
                damagedCounterHarald = 30;
            } else {
                damagedCounterHarald--;
            }
        }
        if (gustav.isHit()) {
            if (damagedCounterGustav == 0) {
                gustav.setHit(false);
                damagedCounterGustav = 30;
            } else {
                damagedCounterGustav--;
            }
        }
    }

    //2.2 RENDER SHAPES

    //---------------------------------------
    //---------------------------------------
    //---------------------------------------
    //---------------------------------------

    public void renderShapes() {
        game.shape.begin(ShapeRenderer.ShapeType.Filled);
        for (int i=0; i<btnsNumber; i++) {
            btns1.get(i).draw(game.shape);
            btns2.get(i).draw(game.shape);
            if (game.b.lockIsActivated() == true) {
                btns1.get(1).setColor(Color.GRAY);
                btns1.get(5).setColor(Color.GRAY);
                btns1.get(11).setColor(Color.GRAY);
            } else {
                //btns1.get(1).setColor(new Color(0, 0, 0.3f, 1));
                //btns1.get(5).setColor(new Color(0, 0, 0.3f, 1));
                //btns1.get(11).setColor(new Color(0, 0, 0.3f, 1));
            }
            if (i == selectionP1) {
                btns1.get(i).setColor(new Color(0, 0, 0.8f, 1));
            } else {
                btns1.get(i).setColor(new Color(0, 0, 0.3f, 1));
            }
            if (i == selectionP2) {
                btns2.get(i).setColor(new Color(0.8f, 0, 0, 1));
            } else {
                btns2.get(i).setColor(new Color(0.3f, 0, 0, 1));
            }
            btns1.get(i).setRectX(btnsX1 + 185 * (i / 7));
            btns2.get(i).setRectX(btnsX2 + 185 * (i / 7));
        }
        if ((harald.timerIsActive() == true) && (harald.getTempAbility().enoughGils(harald.getGil()) == true)) {
            timedBarHarald.drawTimedBar(game.shape);
            // renderer.draw(timedBarHarald);
            timedBarHarald.setCounter(30);
            timedBarHaraldCounter = 30;
        }
        if ((gustav.timerIsActive() == true) && (gustav.getTempAbility().enoughGils(gustav.getGil()) == true)) {
            timedBarGustav.drawTimedBar(game.shape);
            //renderer.draw(timedBarGustav);
            timedBarGustav.setCounter(30);
            timedBarGustavCounter = 30;
        }
        game.shape.end();
    }

    //2.3 RENDER TEXTURES AND LABELS

    //---------------------------------------
    //---------------------------------------
    //---------------------------------------
    //---------------------------------------

    public void renderBatch() {
        game.batch.begin();
        drawTimers();
        //lab.drawLabel(200,100,game.batch);
        drawFighters();
        drawLabels();
        game.batch.end();
    }

    boolean gameStarted = true;
    public void drawTimers() {
        t.drawTimer(game.batch);
        if (t.timeIsOver()) initiateSwitch();
    }

    public void drawFighters() {
        if (harald.isDead() == false) game.batch.draw(hartex, P1_X, P_Y);//animaHarald.draw(p1x, P_Y, harald, game.batch);
        if (gustav.isDead() == false) game.batch.draw(gustex, P2_X, P_Y);//animaGustav.draw(p2x, P_Y, gustav, game.batch);
    }

    private Ability[] allAbilities1 = {hrm, lock, cure1, tmp, slow, don1, quick};
    private Ability[] allAbilities2 = {att, trd, curex, prt, haste, don2, rand};
    private MLabel mlabel1 = new MLabel(allAbilities1);
    private MLabel mlabel2 = new MLabel(allAbilities2);
    public void drawLabels() {
        drawPlayerStats() ;
        drawExplanationLabels();
        drawAbilityName();
        mlabel1.drawLabel(btnsX1 + 30, btnsY - 30, game.batch);
        mlabel2.drawLabel(btnsX1 + 190, btnsY - 30, game.batch);
        mlabel2.drawLabel(btnsX2 + 30, btnsY - 30, game.batch);
        mlabel1.drawLabel(btnsX2 + 190, btnsY - 30, game.batch);
        if (harald.isHit()==true && gameOver == false) damageHarald.drawLabel(""+ harald.getDamageAsString(),P1_X+150, P_Y+100, game.batch);
        if (gustav.isHit()==true && gameOver == false) damageGustav.drawLabel("" + gustav.getDamageAsString(), P2_X-50, P_Y+100, game.batch);
        if (switchCounter > 0) {
            game.batch.draw(switchTex, Gdx.graphics.getWidth()/2-switchTex.getWidth()/2, CLOCK_Y-100);
            switchCounter--;
        }
    }

    private int timedBarGustavCounter = 0;
    private int timedBarHaraldCounter = 0;
    public void drawAbilityName() {
        if (timedBarHaraldCounter > 0) {
            timedBarHaraldCounter--;
            abNameHarald.drawLabel(harald.getFixedAbility().getName(), P1_X, P_Y + 300, game.batch);
        }
        if (timedBarGustavCounter > 0) {
            timedBarGustavCounter--;
            abNameGustav.drawLabel(gustav.getFixedAbility().getName(), P2_X, P_Y + 300, game.batch);
        }
    }
    public void drawExplanationLabels() {
        explanationLabelP1.drawLabel("(P1) " + allAbilitiesP1[selectionP1].getExplanation(), P1_X,160,game.batch);
        explanationLabelP2.drawLabel("(P2) " + allAbilitiesP2[selectionP2].getExplanation(), P1_X,90,game.batch);
    }
    public void drawPlayerStats() {
        p1HP.drawLabel("HP: " + harald.getHP() + " / " + harald.getMaxHP() + "\n" + "Attack: " + harald.getAttack() + "\nDefense: " + harald.getDefense() + "\nSpeed: " + harald.getSpeedAsString() + "\n\nGil: " + harald.getGil(), P1_X, P_Y-50, game.batch);
        p2HP.drawLabel("HP: " + gustav.getHP() + " / " + gustav.getMaxHP() + "\n" + "Attack: " + gustav.getAttack() + "\nDefense: " + gustav.getDefense() + "\nSpeed: " + gustav.getSpeedAsString() + "\n\nGil: " + gustav.getGil(), P2_X, P_Y-50, game.batch);
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

    //-------------------------------------------------------------
    //-------------------------------------------------------------
    //-------------------------------------------------------------
    //-------------------------------------------------------------
    //INITIATE SWITCH
    //-------------------------------------------------------------
    //-------------------------------------------------------------
    //-------------------------------------------------------------
    //-------------------------------------------------------------

    private Ability[] temp;
    private int btnsX2 = Gdx.graphics.getWidth() - 380;
    public void initiateSwitch() {
        switchAbilityLabels();
        t.setTimeOver(false);
        game.b.setRandomTime(false);
        game.b.setQuickIsActive(false);
        game.b.setLocked(false);
        if (player1 == harald) {
            switchCounter = 30;
            player1 = gustav;
            player2 = harald;
            for (int i=0; i<btnsNumber; i++) {
                btnsX1 = Gdx.graphics.getWidth() - 380;
                btnsX2 = 20;
            }
        } else {
            switchCounter = 60;
            player1 = harald;
            player2 = gustav;
            for (int i=0; i<btnsNumber; i++) {
                btnsX1 = 20;
                btnsX2 = Gdx.graphics.getWidth() - 380;
            }
        }
    }

    private void switchAbilityLabels() {
        temp = allAbilities1;
        allAbilities1 = allAbilities2;
        allAbilities2 = temp;
    }
}

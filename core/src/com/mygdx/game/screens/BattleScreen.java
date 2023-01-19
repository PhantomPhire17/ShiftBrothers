package com.mygdx.game.screens;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.*;
import com.mygdx.game.interfaces.Constants;

import java.util.ArrayList;

public class BattleScreen extends ScreenAdapter implements Constants {
    private Game game = new Game();
    // private boolean gameIsPaused = false;

    private Animation animaHarald = new Animation();
    private Animation animaGustav = new Animation();
    private Fighter player = harald;
    private Fighter enemy = gustav;
    private int btnsX = 20;
    private int btnsY = 700;
    private int ex, ey, switchCounter, haraldBarCounter, gustavBarCounter, frame, seconds;
    KLabel abNameHarald = new KLabel();
    KLabel abNameGustav = new KLabel();
    KLabel explanationLabel = new KLabel();
    int animaCounterHarald, animaCounterGustav = -1;
    int damagedCounterHarald, damagedCounterGustav = 30;
    KLabel damageHarald = new KLabel();
    KLabel damageGustav = new KLabel();
    KLabel p1HP = new KLabel();
    KLabel p2HP = new KLabel();
    ArrayList<Button> btns = new ArrayList<Button>();
    private int btnsNumber = 12;
    private int timedBarWidthHarald = 360;
    private int timedBarWidthGustav = 360;
    private int timedBarWidthPlayer = timedBarWidthHarald;
    private TimedBar timedBarHarald = new TimedBar(harald);
    private TimedBar timedBarGustav= new TimedBar(gustav);

    Texture bg;
    boolean gameOver = false;
    private boolean gustavLocked = false;

    public BattleScreen(Game game) {
        this.game = game;
        setup();
    }

    public void setup() {
        gameStarted = true;
        setBackground();
        setAbilities();
        setButtons();
    }

    private void setBackground() {
        bg = new Texture("bg.jpg");
    }

    public void setAbilities() {
    }

    public void setButtons() {
        for (int i = 0; i < btnsNumber; i++) {
            btns.add(new Button());
            btns.get(i).setDimensions(btnsX + 185 * (i / 6), (btnsY - (i % 6) * 110), 180, 80);
            btns.get(i).setAbility(allAbilities[i]);
            btns.get(i).setLabel(btns.get(i).getAbility().getName());
            btns.get(i).setMouseListeners(ex, ey);
            btns.get(i).setColor(new Color(0, 0, 0.3f, 1));
        }
        timedBarHarald.setColor(Color.GREEN);
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
        ScreenUtils.clear(0f, 0.1f, 0, 1);
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
            runPlayer();
            if (ai.isActive()) runEnemy();
        } else {
            gameOver = true;
            if (player.isDead()) game.setScreen(new GameOverScreen(game, enemy, game.shape, false));
            if (enemy.isDead()) game.setScreen(new GameOverScreen(game, player, game.shape, true));
        }
        runPlayer();
        runPlayer();
    }

    //2.1.1 RUN HARALD

    //---------------------------------------
    //---------------------------------------
    //---------------------------------------
    //---------------------------------------

    public void runPlayer() {
        if (buttonIsClicked() == true) {
                player.setActiveTimer(true);
        }
    }

    private boolean buttonIsClicked() {
        if (Gdx.input.isTouched()) {
            for (int i = 0; i < btnsNumber; i++) {
                if (btns.get(i).isButton(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY())) {
                    player.setAbility(allAbilities[i]);
                    if ((allAbilities[i] == quick || allAbilities[i] == rand) && b.lockIsActivated() == true)
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

    public void runEnemy() {
        ai.setActionRate(2);
        if (enemy == gustav) {
            if (timedBarWidthGustav == 360) {
                gustav.setActiveTimer(true);
                gustav.setAbility(ai.getNextAbility(player, enemy));
            }
            if (timedBarGustav.getRectWidth() <= 0) {
                att.run(enemy, player);
                animaHarald = damage;
                animaCounterHarald = damage.getTimer();
                gustavLocked = false;
            }
        } else {
            if (timedBarWidthHarald == 360) {
                harald.setActiveTimer(true);
                harald.setAbility(ai.getNextAbility(player, enemy));
            }
            if (timedBarWidthHarald <= 0) {
                animaGustav = damage;
                animaCounterGustav = damage.getTimer();
                gustavLocked = false;
            }
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
        if (harald.isHit() == true) {
                if (damagedCounterHarald == 0) {
                    harald.setHit(false);
                    damagedCounterHarald = 30;
                } else {
                    damagedCounterHarald--;
                }
        }
        if (gustav.isHit() == true) {
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
            btns.get(i).draw(game.shape);
            if (b.lockIsActivated() == true) {
                btns.get(1).setColor(Color.GRAY);
                btns.get(5).setColor(Color.GRAY);
                btns.get(11).setColor(Color.GRAY);
            } else {
                btns.get(1).setColor(new Color(0, 0, 0.3f, 1));
                btns.get(5).setColor(new Color(0, 0, 0.3f, 1));
                btns.get(11).setColor(new Color(0, 0, 0.3f, 1));
            }
            btns.get(i).setRectX(btnsX + 185 * (i / 6));
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

    private Ability[] allAbilities1 = {hrm, lock, cure1, tmp, don1, quick};
    private Ability[] allAbilities2 = {att, trd, curex, prt, don2, rand};
    private MLabel mlabel1 = new MLabel(allAbilities1);
    private MLabel mlabel2 = new MLabel(allAbilities2);
    public void drawLabels() {
        drawPlayerStats() ;
       // drawExplanationLabels();
        drawAbilityName();
        mlabel1.drawLabel(btnsX + 30, btnsY - 30, game.batch);
        mlabel2.drawLabel(btnsX + 190, btnsY - 30, game.batch);
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
            abNameHarald.drawLabel(harald.getTempAbility().getName(), P1_X, P_Y + 300, game.batch);
        }
        if (timedBarGustavCounter > 0) {
            timedBarGustavCounter--;
            abNameGustav.drawLabel(gustav.getTempAbility().getName(), P2_X, P_Y + 300, game.batch);
        }
    }
    public void drawExplanationLabels() {
        if (player.getTempAbility() != null) explanationLabel.drawLabel(player.getTempAbility().getExplanation(), P1_X,80,game.batch);
        for (int i=0; i<btnsNumber; i++) {
            if (btns.get(i).isButton(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY())) explanationLabel.drawLabel(btns.get(i).getAbility().getExplanation(), P1_X,80,game.batch);
        }
    }
    public void drawPlayerStats() {
        p1HP.drawLabel("HP: " + harald.getHP() + " / " + harald.getMaxHP() + "\n" + "Attack: " + harald.getAttack() + "\nDefense: " + harald.getDefense() + "\n\nGil: " + harald.getGil(), P1_X, P_Y-50, game.batch);
        p2HP.drawLabel("HP: " + gustav.getHP() + " / " + gustav.getMaxHP() + "\n" + "Attack: " + gustav.getAttack() + "\nDefense: " + gustav.getDefense() + "\n\nGil: " + gustav.getGil(), P2_X, P_Y-50, game.batch);
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
    public void initiateSwitch() {
        switchAbilityLabels();
        t.setTimeOver(false);
        b.setRandomTime(false);
        b.setQuickIsActive(false);
        b.setLocked(false);
        if (player == harald) {
            switchCounter = 30;
            player = gustav;
            enemy = harald;
            for (int i=0; i<btnsNumber; i++) {
                btnsX = Gdx.graphics.getWidth() - 380;
            }
        } else {
            switchCounter = 60;
            player = harald;
            enemy = gustav;
            for (int i=0; i<btnsNumber; i++) {
                btnsX = 20;
            }
        }
    }

    private void switchAbilityLabels() {
        temp = allAbilities1;
        allAbilities1 = allAbilities2;
        allAbilities2 = temp;
    }
}

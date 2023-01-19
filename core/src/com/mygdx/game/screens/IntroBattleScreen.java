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

public class IntroBattleScreen extends ScreenAdapter implements Constants {

    private Game game = new Game();
    // private boolean gameIsPaused = false;

    private Animation animaHarald = new Animation();
    private Animation animaGustav = new Animation();
    private Fighter player = harald;
    private Fighter enemy = gustav;
    private int btnsX = 20;
    private int btnsY = 700;
    private int ex, ey, p1x, p2x, switchCounter, haraldBarCounter, gustavBarCounter, frame, seconds;
    KLabel abNameHarald, abNameGustav, explanationLabel, timeLabel;
    int animaCounterHarald, animaCounterGustav = -1;
    int damagedCounterHarald, damagedCounterGustav = 30;
    KLabel damageHarald = new KLabel();
    KLabel damageGustav = new KLabel();
    ArrayList<Button> btns = new ArrayList<Button>();
    private int btnsNumber = 12;
    private int timedBarWidthHarald = 360;
    private int timedBarWidthGustav = 360;
    private int widthPerFrameHarald;
    private int widthPerFrameGustav;

    private int timedBarWidthPlayer = timedBarWidthHarald;
    private int widthPerFramePlayer = widthPerFrameHarald;
    private ArrayList<Integer> displayedRects = new ArrayList<>();
    private int gusBarX = P2_X;
    private TimedBar timedBarHarald, timedBarGustav;

    Texture bg;
    boolean gameOver = false;
    String gameOverString;
    private int rint;
    private boolean gustavLocked = false;

    public IntroBattleScreen(Game game) {
        this.game = game;
        setup();
    }

    public void setup() {
        bg = new Texture("bg.jpg");
        explanationLabel = new KLabel();
        p1x = P1_X;
        p2x = P2_X;
        t.setStatic(true);
        setButtons();
    }

    private Button btn = new Button();
    private Button yes = new Button();
    private Button repeat = new Button();
    public void setButtons() {
        for (int i = 0; i < 12; i++) {
            btns.add(new Button());
            btns.get(i).setDimensions(btnsX + 185 * (i / 6), (btnsY - (i % 6) * 110), 180, 80);
            btns.get(i).setAbility(allAbilities[i]);
            btns.get(i).setLabel(btns.get(i).getAbility().getName());
            btns.get(i).setMouseListeners(ex, ey);
            btns.get(i).setColor(new Color(0, 0, 0.3f, 1));
        }
        repeat.setLabel("Repeat Tutorial");
        repeat.setMouseListeners(ex, ey);
        repeat.setColor(Color.BLUE);
        repeat.setDimensions(Gdx.graphics.getWidth()/2 - 150, 150, 300, 100);
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
    private int i = 1;

    private boolean mouseLock = false;
    @Override
    public void render(float delta) {
        ex = Gdx.input.getX();
        ey = Gdx.graphics.getHeight() - Gdx.input.getY();
        ScreenUtils.clear(0, 0, 0, 1);
        lab = new KLabel("FPS: " + Gdx.graphics.getFramesPerSecond());
        if (Gdx.input.isTouched() && mouseLock == false && btn.isButton(ex, ey)) {
            i++;
            mouseLock = true;
        }
        if (Gdx.input.isTouched() && repeat.isButton(ex,ey) && yesButton == true) {
            i = 1;
            yesButton = false;
        }
        if (!Gdx.input.isTouched()) {
            mouseLock = false;
        }
        game.shape.begin(ShapeRenderer.ShapeType.Filled);
        drawShapes();
        game.shape.end();
        game.batch.begin();
        drawBatch();
        game.batch.end();
    }

    private void drawShapes() {
        for (int i=0; i<btns.size(); i++) {
            btns.get(i).draw(game.shape);
        }
        if (yesButton == true) {
            repeat.draw(game.shape);
        }
        drawOKButton();
    }

    private MLabel mlabel;
    private String superString = "";

    private KLabel desc;

    private Ability[] ab1 = {hrm, att};
    private Ability[] ab2 = {hrm, trd, att};
    private Ability[] ab3 = {cure1, curex};
    private Ability[] ab4 = {tmp, prt};
    private Ability[] ab5 = {don1, don2};
    private Ability[] ab6 = {trd};


    private String explanation="Zeit";
    private void drawBatch() {
        btn.getLabel().drawLabel(Gdx.graphics.getWidth()/2 - 100, 270, game.batch);
        if (yesButton == true) repeat.getLabel().drawLabel(Gdx.graphics.getWidth()/2 - 100, 120, game.batch);
        mlabel = new MLabel(superString);
        desc = new KLabel(explanation);
        mlabel.drawLabel(btnsX + 30, btnsY - 30, game.batch);
        desc.drawLabel(WIDTH/4, 700, game.batch);
        if (i==0) drawTimerDescription();
        if (i==1) drawAttackDescription();
        if (i==2) drawGilDescription();
        if (i==3) drawCureDescription();
        if (i==4) drawProtectTemperDescription();
        if (i==5) drawDonateDescription();
        if (i==6) drawQuickRandomDescription();
        if (i==7) drawLockDescription();
        if (i==8) drawStartDescription();
        if (i > 8) game.setScreen(new BattleScreen(game));
    }

    private void drawOKButton() {
        btn.setLabel("           OK");
        btn.setDimensions(Gdx.graphics.getWidth()/2 - 150, 300, 300, 100);
        btn.setColor(Color.BLUE);
        btn.draw(game.shape);
    }

    private void drawTimerDescription() {
    }

    private void drawAttackDescription() {
        explanation = "Wähle HARM, um dich sebst anzugreifen, und\nATTACK, um deinen Gegner anzugreifen.\n\nFür beide Aktionen erhälst du etwas Geld.";
        superString = "Harm               Attack";
    }

    private void drawGilDescription() {
        explanation = "Wähle GETGIL, um 10% deiner Werte (HP, Attack, Defense)\ngegen 2 Gil einzutauschen.";
        superString = "Harm               Attack\n\n\n                       GetGil";
    }

    private void drawCureDescription() {
        explanation = "Wähle CURE, um dich selbst zu heilen, und\nCUREEX, um deinen Gegner zu heilen.\n\nIn Klammern ist der Preis angegeben (hier: 10 Gil).";
        superString = "Harm               Attack\n\n\n                       GetGil\n\n\nCure (10)      CureEx (10)";
    }

    private void drawProtectTemperDescription() {
        explanation = "Wähle TEMPER, um deine Angriffsstärke zu erhöhen, und\n PROTECT, um deine Verteidigung zu erhöhen.";
        superString = "Harm               Attack\n\n\n                       GetGil\n\n\nCure (10)      CureEx (10)\n\n\nTemper (5)   Protect (5)";
    }

    private void drawDonateDescription() {
        explanation = "Wähle DONATE, um deinem Gegner entweder 10 Gil oder 20 Gil zu schenken.";
        superString = "Harm               Attack\n\n\n                       GetGil\n\n\nCure (10)      CureEx (10)\n\n\nTemper (5)   Protect (5)\n\n\nDonate (10)   Donate (20)";
    }

    private void drawQuickRandomDescription() {
        explanation = "Wähle QUICK, um die verbleibende Zeit um 10 Sekunden zu verküren\n(Nicht wirksam, wenn weniger als 10 Sekunden verbleiben.)\n\nMit RANDOM geschieht der nächste Switch komplett zufällig.";
        superString = "Harm               Attack\n\n\n                       GetGil\n\n\nCure (10)      CureEx (10)\n\n\nTemper (5)   Protect (5)\n\n\nDonate (10)   Donate (20)\n\n\nQuick (15)   Random (20)";
    }

    private void drawLockDescription() {
        explanation = "Wenn du nicht willst, das jemand an der Zeit herumspielt,\nkannst du LOCK wählen, um RANDOM und QUICK zu sperren.\n\nNach dem nächsten Switch wird die Sperre aufgehoben.";
        superString = "Harm               Attack\n\n\nLock (10)        GetGil\n\n\nCure (10)      CureEx (10)\n\n\nTemper (5)   Protect (5)\n\n\nDonate (10)   Donate (20)\n\n\nLOCKED       LOCKED";
    }

    private boolean yesButton = false;
    private void drawStartDescription() {
        yesButton = true;
        explanation = "Kampf starten?";
        superString = "Harm               Attack\n\n\nLock (10)        GetGil\n\n\nCure (10)      Curex (10)\n\n\nTemper (5)   Protect (5)\n\n\nDonate (10)   Donate (20)\n\n\nLOCKED       LOCKED";
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
        lab.drawLabel(200,100,game.batch);
        drawFighters();
        drawLabels();
        game.batch.end();
    }

    boolean gameStarted = true;
    public void drawTimers() {
        t.drawTimer(game.batch);
    }

    public void drawFighters() {
        if (harald.isDead() == false) game.batch.draw(hartex, p1x, P_Y);//animaHarald.draw(p1x, P_Y, harald, game.batch);
        if (gustav.isDead() == false) game.batch.draw(gustex, p2x, P_Y);//animaGustav.draw(p2x, P_Y, gustav, game.batch);
    }

    private Ability[] allAbilities1 = {hrm, lock, cure1, tmp, don1, quick};
    private Ability[] allAbilities2 = {att, trd, curex, prt, don2, rand};
    private MLabel mlabel1 = new MLabel(allAbilities1);
    private MLabel mlabel2 = new MLabel(allAbilities2);
    public void drawLabels() {
        drawPlayerStats();
        drawExplanationLabels();
        mlabel1.drawLabel(btnsX + 30, btnsY - 30, game.batch);
        mlabel2.drawLabel(btnsX + 190, btnsY - 30, game.batch);
    }
    KLabel p1HP;
    KLabel p2HP;
    public void drawExplanationLabels() {
        if (player.getTempAbility() != null) explanationLabel.drawLabel(player.getTempAbility().getExplanation(), p1x,80,game.batch);
        for (int i=0; i<btnsNumber; i++) {
            if (btns.get(i).isButton(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY())) explanationLabel.drawLabel(btns.get(i).getAbility().getExplanation(), p1x,80,game.batch);
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
}

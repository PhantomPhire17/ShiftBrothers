package com.mygdx.game.interfaces;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.*;
import com.mygdx.game.abilities.*;
import com.mygdx.game.animations.Damage;
import com.mygdx.game.battleUtilities.AI;
import com.mygdx.game.battleUtilities.BackendFunctions;
import com.mygdx.game.battleUtilities.Timer;
import com.mygdx.game.rendering.Clear;
import com.mygdx.game.rendering.Renderer;

import java.util.Random;

public interface Constants {

    public final Color BG_COLOR = new Color(0,0,0.3f,1);
    public final ShapeRenderer SHAPE = new ShapeRenderer();

    public final Game game = new Game();

    public final int WIDTH = Gdx.graphics.getWidth();
    public final int P1_X = 500;
    public final int P_Y = Gdx.graphics.getHeight() * 5/8 - 50;

    Texture hartex = new Texture("warrior.png");
    //
    Texture gustex = new Texture("redwizard.png");
    public int P2_X = Gdx.graphics.getWidth()-600-gustex.getWidth();
    public final int T1_X = P1_X - 250;
    public final int CLOCK_Y = 900;

    public final int HP_Y = P_Y - 50;
    public final int T2_X = P2_X - 250;
    public final int T_Y = P_Y - 300;
    public final Setup s = new Setup();

    Fighter harald = new Fighter("warrior.png", 100000, 20, 10, 200);
    Fighter gustav = new Fighter("redwizard.png", 100000, 20, 10, 200);
    Texture switchTex = new Texture("switch.png");

    Animation anima = new Animation();
    Damage damage = new Damage();

    public int BTN_X = 20;
    public int BTN_Y = 900;
    public int TIME = 10;

    public int FPS = 60;
    Attack att = new Attack();
    Defend def = new Defend();
    Temper tmp = new Temper();
    Cure cure1 = new Cure(100);
    Cure cure2 = new Cure(200);
    Slow slow = new Slow();
    Haste haste = new Haste();
    Randomize rand = new Randomize();
    LockRandomize lock = new LockRandomize();
    Random rgen = new Random();

    Trade trd = new Trade();
    Donate don1 = new Donate(10);
    Donate don2 = new Donate(20);
    Protect prt = new Protect();

    CureEx curex = new CureEx();
    Quick quick = new Quick();
    Harm hrm = new Harm();

    Ability[] allAbilities = {hrm, lock, cure1, tmp, don1, quick, att, trd, curex, prt, don2, rand};

    Timer t = new Timer();
    BackendFunctions b = new BackendFunctions();
    AI ai = new AI();
    // Renderer renderer = new Renderer();

    Clear cl = new Clear();

}

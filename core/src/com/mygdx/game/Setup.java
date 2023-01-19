package com.mygdx.game;

import com.mygdx.game.interfaces.Constants;

import java.util.ArrayList;

public class Setup implements Constants {

    public Setup() {}

    public ArrayList<KLabel> getBattleLabels() {
        ArrayList<KLabel> labels = new ArrayList<>();
        KLabel p1_hp = new KLabel("HP: " + harald.getHP(), P1_X, HP_Y);
        KLabel p2_hp = new KLabel("HP: " + gustav.getHP(), P2_X, HP_Y);
        KLabel p1_attack = new KLabel("Strength: " + harald.getAttack(), P1_X, HP_Y - 50);
        KLabel p2_attack = new KLabel("Strength: " + gustav.getAttack(), P2_X, HP_Y - 50);
        KLabel p1_defense = new KLabel("Defense: " + harald.getDefense(), P1_X, HP_Y - 100);
        KLabel p2_defense = new KLabel("Defense: " + gustav.getDefense(), P2_X, HP_Y - 100);
        KLabel p1_speed = new KLabel("Speed: " + harald.getSpeed(), P1_X, HP_Y - 150);
        KLabel p2_speed = new KLabel("Speed: " + gustav.getSpeed(), P2_X, HP_Y - 150);
        KLabel test = new KLabel("This is LABEL text", 600, 200);
        labels.add(p1_hp);
        labels.add(p2_hp);
        labels.add(p1_attack);
        labels.add(p2_attack);
        labels.add(p1_defense);
        labels.add(p2_defense);
        labels.add(p1_speed);
        labels.add(p2_speed);
        labels.add(test);
        return labels;
    }
}

package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.interfaces.Constants;

public class Fighter extends KObject implements Constants {

    String name;
    int hp;
    int maxHP;
    int defense;
    int speed;
    int attack;
    private int gil = 10;

    public int timer = 33;
    Texture tex = new Texture("warrior.png");
    String path;

    Ability ab;
    private boolean isLocked = false;


    public Fighter(String path, int hp, int attack, int defense, int gil) {
        this.path = path;
        this.hp = hp;
        this.maxHP = hp;
        this.defense = defense;
        this.attack = attack;
        this.gil = gil;
    }

    private boolean isHit = false;
    public boolean isHit() {
        return isHit;
    }


    public void setHit(boolean b) {
        isHit = b;
    }

    int dir;
    public void setDirectionOffset(int dir) {
        if (dir >= 0) this.dir = 1;
        if (dir < 0) this.dir = -1;
    }

    public int getDirectionOffset() {
        return dir;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setImage(Texture img) {
        tex = img;
    }

    public void setTexture(String path) {
        this.path = path;
    }

    public Texture getTexture() {
        return new Texture(path);
    }

    public String getTexturePath() {
        return path;
    }

    public int getHP() {
        return hp;
    }
    public int getMaxHP() {
        return maxHP;
    }

    public void setHP(int hp) {
        if (isLocked == false) {
            this.hp = hp;
        }
    }

    public void setMaxHP(int hp) {
        if (isLocked == false) {
            this.maxHP = hp;
        }
    }

    public void changeHP(int delta) {
        if (isLocked == false) {
            if (!(hp + delta > maxHP)) {
                hp += delta;
                if (hp <= 0) {
                    hp = 0;
                    isDead = true;
                }
            }
        }
    }

    public void setGil(int gil) {
        if (gil>=0) this.gil = gil;
    }

    public void changeGil(int delta) {
        if (gil+delta>=0) gil += delta ;
    }

    public int getGil() {
        return gil;
    }

    private boolean isDead = false;
    public boolean isDead() {
        return isDead;
    }

    public void revive() {
        isDead = false;
        hp = maxHP;
    }
    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        if (isLocked == false) {
            this.attack = attack;
        }
    }

    int modifiedTime;

    public int getModifiedSwitchTime() {
        return modifiedTime;
    }

    public void setModifiedSwitchTime(int time) {

    }

    public void changeAttack(int delta) {
        if (isLocked == false) {
            this.attack += delta;
        }
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        if (isLocked == false) {
            this.defense = defense;
        }
    }

    public void changeDefense(int delta) {
        if (isLocked == false) {
            this.defense += delta;
        }
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        if (isLocked == false) {
            this.speed = speed;
        }
    }

    public void changeSpeed(int delta) {
        if (isLocked == false) {
            this.speed += delta;
        }
    }

    private int damage;
    private int variance;
    public void setDamage(int strength) {
        int lowerLimit = -5;
        int variance = rgen.nextInt(5);
        if (variance == 5) variance = 10;
        if (variance < -5) variance = -5;
        damage =  strength * 4 - defense * 2 - variance;
        if (damage < 0) damage = 0;
        changeHP(-damage);
        isHit = true;
    }

    public String getDamageAsString() {
        if (variance == 5) {
            return "CRITICAL!" + "\n" + damage;
        }
        return "" + damage;
    }

    public int getDamage() {
        return damage;
    }

    public void setLocked(boolean isLocked) {
        this.isLocked = isLocked;
    }

    public void setAbility(Ability ab) {
        this.ab = ab;
    }

    public Ability getTempAbility() {
        return ab;
    }

    private boolean activeT = false;

    public void setActiveTimer(boolean b) {
        activeT = b;
    }

    public boolean timerIsActive() {
        return activeT;
    }

    private boolean isActive = false;
    public void setActive(boolean b) {
        isActive = true;
    }
    public boolean timedBarDone() {
        return isActive;
    }
}

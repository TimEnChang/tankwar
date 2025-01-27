package com.java.tankwar;

import javax.swing.*;
import java.awt.*;

class Missile {
    private int SPEED = 10;
    private int x;
    private int y;
    private final boolean enemy;
    private final Direction direction;

    public Missile(int x, int y, boolean enemy, Direction direction) {
        this.y = y;
        this.x = x;
        this.enemy = enemy;
        this.direction = direction;
    }

    Image getImage(){
        String prefix = enemy ? "e": "";
        return direction.getImage("missile");
    }

    void move(){

        x = direction.xFactor *SPEED;
        y = direction.yFactor *SPEED;

    }

    public void draw(Graphics g) {
        move();
        if(x<0 || x>800||y<0||y>800){
            return;
        }
        g.drawImage(getImage(), x, y, null);
    }
}

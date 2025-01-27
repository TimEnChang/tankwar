package com.java.tankwar;

import javax.print.attribute.standard.Media;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.File;

public class Tank {
    private int x;
    private int y;
    private boolean stopped;
    private final boolean enemy;
    int movespeed = 5;

    public int getX() {
        return x;
    }

    private Direction direction;
    public Tank(int x, int y, Direction direction) {
        this(x,y,false,direction);
    }

    public Tank(int x, int y, boolean enemy, Direction direction) {
        this.x = x;
        this.y = y;
        this.enemy = enemy;
        this.direction = direction;
    }

    void move() {
        if (this.stopped) return;
        x += movespeed* direction.xFactor;
        y += movespeed* direction.yFactor;

    }

    Image getImage(){
        String prefix = enemy ? "e": "";
        return direction.getImage(prefix+"tank");


    }

    void draw(Graphics g){
        int oldX =x, oldY = y;
        this.determineDirection();
        this.move();

        if(x<0) x=0;
        else if(x>800-getImage().getWidth(null)) x=800 - getImage().getWidth(null);
        if(y<0) y=0;
        else if(y>600-getImage().getHeight(null)) y=600 - getImage().getHeight(null);


        Rectangle rec = this.getRectangle();
        for(Wall wall:gameclient.getInstance().getWalls()){
            if(rec.intersects(wall.getRectangle())){
                x= oldX;
                y= oldY;
                break;
            }
        }

        for(Tank tank:gameclient.getInstance().getEnemyTanks()){
            if(rec.intersects(tank.getRectangle())){
                x = oldX;
                y = oldY;
                break;

            }

        }

        g.drawImage(getImage(), x, y, null);

    }

    public Rectangle getRectangle() {
        return new Rectangle(x, y, getImage().getWidth(null), getImage().getHeight(null));
    }

    private boolean up, down, left, right;


    public void KeyPressed(KeyEvent e) {
    switch (e.getKeyCode()) {
        case KeyEvent.VK_UP: up = true; break;
        case KeyEvent.VK_DOWN: down = true; break;
        case KeyEvent.VK_LEFT: left = true; break;
        case KeyEvent.VK_RIGHT: right = true; break;
        case KeyEvent.VK_SPACE:fire(); break;
        case KeyEvent.VK_A:superfire(); break;

    }

        }


    private void fire(){
        Missile missile = new Missile(x+getImage().getWidth(null)/2 -6,
                y+getImage().getHeight(null)/2-6,enemy, direction);



        gameclient.getInstance().getMissiles().add(missile);

    }

    private void superfire(){
        for(Direction direction:Direction.values() ){
        Missile missile = new Missile(x+getImage().getWidth(null)/2 -6,
                y+getImage().getHeight(null)/2-6,enemy, direction);

        gameclient.getInstance().getMissiles().add(missile);}

    }

    private void determineDirection(){
        if (!up&&!left&&!right&&!down) {this.stopped = true;}
        else {
            if (up&&left&&!right&&!down) this.direction = Direction.UPLEFT;
            else if (up&&!left&&right&&!down) this.direction = Direction.UPRIGHT;
            else if (!up&&!right&&down&&left) this.direction = Direction.DOWNLEFT;
            else if (!up&&!left&&right&&down) this.direction = Direction.DOWNRIGHT;
            else if (!up&&!left&&!right&&down) this.direction = Direction.Down;
            else if (up&&!left&&!right&&!down) this.direction = Direction.Up;
            else if (!up&&!left&&right&&!down) this.direction = Direction.Right;
            else if (!up&&left&&!right&&!down) this.direction = Direction.Left;

            this.stopped = false;
        }
        }


    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP: up = false; break;
            case KeyEvent.VK_DOWN: down = false; break;
            case KeyEvent.VK_LEFT: left = false; break;
            case KeyEvent.VK_RIGHT: right = false; break;
        }

    }
}

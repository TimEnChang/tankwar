package com.java.tankwar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Tank {
    private int x;
    private int y;
    private boolean stopped;
    private final boolean enemy;

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

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
        switch (direction) {
            case Up:
                y -= 5;
                break;
            case Down:
                y += 5;
                break;
            case Left:
                x -= 5;
                break;
            case Right:
                x += 5;
                break;
            case UPLEFT:
                y -= 5;
                x -= 5;
            break;
            case UPRIGHT:
                y -= 5;
                x += 5;
            break;
            case DOWNLEFT:
                x -= 5;
                y += 5;
            break;
            case DOWNRIGHT:
                x += 5;
                y += 5;
            break;

        }
    }

    Image getImage(){
        String prefix = enemy ? "e": "";
        switch (direction){

            case Up: return new ImageIcon("assets/images/"+ prefix+"tankU.gif").getImage();
            case Down: return new ImageIcon("assets/images/"+prefix+"tankD.gif").getImage();
            case Left: return new ImageIcon("assets/images/"+prefix+"tankL.gif").getImage();
            case Right: return new ImageIcon("assets/images/"+prefix+"tankR.gif").getImage();
            case UPLEFT: return new ImageIcon("assets/images/"+prefix+"tankLU.gif").getImage();
            case UPRIGHT: return new ImageIcon("assets/images/"+prefix+"tankRU.gif").getImage();
            case DOWNLEFT: return new ImageIcon("assets/images/"+prefix+"tankLD.gif").getImage();
            case DOWNRIGHT: return new ImageIcon("assets/images/"+prefix+"tankRD.gif").getImage();

        }
    return null;
    }

    void draw(Graphics g){
        this.determineDirection();
        this.move();
        g.drawImage(getImage(), x, y, null);
    }

    private boolean up, down, left, right;


    public void KeyPressed(KeyEvent e) {
    switch (e.getKeyCode()) {
        case KeyEvent.VK_UP: up = true; break;
        case KeyEvent.VK_DOWN: down = true; break;
        case KeyEvent.VK_LEFT: left = true; break;
        case KeyEvent.VK_RIGHT: right = true; break;

    }



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

package com.java.tankwar;

import java.awt.*;

class Missile {
    private int SPEED = 10;
    private int x;
    private int y;
    private final boolean enemy;
    private final Direction direction;

    private boolean live = true;

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public Missile(int x, int y, boolean enemy, Direction direction) {
        this.y = y;
        this.x = x;
        this.enemy = enemy;
        this.direction = direction;
    }

    Image getImage() {
        return direction.getImage("missile");

    }


    void move() {
        x += direction.xFactor * SPEED;
        y += direction.yFactor * SPEED;


    }

    void draw(Graphics g) {

        move();
        if (x < 0 || x > 800 || y < 0 || y > 800) {
            this.live = false;
            return;
        }
        Rectangle rectangle = this.getRectangle();
        for (Wall wall : gameclient.getInstance().getWalls()) {
            if (rectangle.intersects(wall.getRectangle())) {
                this.live = false;
                return;
            }
        }

        if (enemy) {
            Tank playerTank = gameclient.getInstance().getPlayerTank();
            if (rectangle.intersects(playerTank.getRectangle())) {
                addExplosion();
                playerTank.setHp(playerTank.getHp() - 20);
                if (playerTank.getHp() <= 0) {
                    playerTank.setLive(false);
                }
                this.setLive(false);
            }

        } else {
            for (Tank tank : gameclient.getInstance().getEnemyTanks()) {
                if (rectangle.intersects(tank.getRectangle())) {
                    addExplosion();
                    tank.setLive(false);
                    this.setLive(false);
                    break;
                }
            }
        }

        g.drawImage(getImage(), x, y, null);
    }

    private  void addExplosion(){
        gameclient.getInstance().addExplosion(new Explosion(x,y));


    }


    Rectangle getRectangle(){
        return new Rectangle(x, y, getImage().getWidth(null), getImage().getHeight(null));
}

    }


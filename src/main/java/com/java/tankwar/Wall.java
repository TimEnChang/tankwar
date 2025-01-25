package com.java.tankwar;

import javax.swing.*;
import java.awt.*;

class Wall {

    private int x;
    private int y;

    private boolean horizontal;

    private int bricks;

    private final Image bricksImage;

    public Wall(int x, int y, boolean horizontal, int bricks) {
        this.bricksImage = tools.getImage("brick.png");
        this.x = x;
        this.y = y;
        this.horizontal = horizontal;
        this.bricks = bricks;
    }

    public Rectangle getRectangle() {
        return horizontal? new Rectangle(x, y, bricks*bricksImage.getWidth(null), bricks):
        new Rectangle(x,y,bricksImage.getWidth(null),bricksImage.getHeight(null)*bricks);

    }

    public void draw(Graphics g){

        if(horizontal){
            for (int i = 0; i < bricks; i++) {
                g.drawImage(bricksImage,x+i*bricksImage.getWidth(null),y,null);

        }
        }else{
            for (int i = 0; i < bricks; i++) {
                g.drawImage(bricksImage,x,y+i*bricksImage.getHeight(null),null);
            }
        }

    }



}

package com.java.tankwar;

import javax.swing.*;
import java.awt.*;

public class Wall {

    private int x;
    private int y;

    private boolean horizontal;

    private int bricks;

    public Wall(int x, int y, boolean horizontal, int bricks) {
        this.x = x;
        this.y = y;
        this.horizontal = horizontal;
        this.bricks = bricks;
    }

    public void draw(Graphics g){
        Image bricksImage = tools.getImage("brick.png");
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

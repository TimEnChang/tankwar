package com.java.tankwar;

import java.awt.*;

public class Explosion {
    private int x,y;
    private int step=0;
    private boolean live=true;

    public Explosion(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    void draw(Graphics g){
        if(step >=10 ){
            this.setLive(false);
            return;
        }
        g.drawImage(tools.getImage(step++ +".gif"),x,y,null);
    }
}

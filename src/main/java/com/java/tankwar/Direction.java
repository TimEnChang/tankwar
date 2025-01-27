package com.java.tankwar;

import java.awt.*;

public enum Direction {
    Up("U",0,-1),
    Down("D",0,1),
    Left("L",-1,0),
    Right("R",1,0),
    UPLEFT("LU",-1,-1),
    UPRIGHT("RU",1,-1),
    DOWNLEFT("LD",-1,1),
    DOWNRIGHT("RD",1,1),
    ;

    private final String abbrev;

    final int xFactor, yFactor;

    Direction(String abbrev, int xFactor, int yFactor) {
        this.abbrev = abbrev;
        this.xFactor = xFactor;
        this.yFactor = yFactor;
    }

    Image getImage(String preflix){
        return tools.getImage(preflix+abbrev+".gif");

    }
}

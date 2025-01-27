package com.java.tankwar;

import java.awt.*;

public enum Direction {
    Up("U"),
    Down("D"),
    Left("L"),
    Right("R"),
    UPLEFT("LU"),
    UPRIGHT("RU"),
    DOWNLEFT("LD"),
    DOWNRIGHT("RD"),
    ;

    private final String abbrev;

    Direction(String abbrev) {
        this.abbrev = abbrev;
    }

    Image getImage(String preflix){
        return tools.getImage(preflix+abbrev+".gif");

    }
}

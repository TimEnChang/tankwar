package com.java.tankwar;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TankTest {

@Test
    void getImage() {

        for(Direction direction : Direction.values()) {
            Tank tank = new Tank(0,0,false,direction);
            assertTrue(tank.getImage().getWidth(null) >0, direction+"cannot get valid image");
            assertNotNull(tank.getImage());

            Tank enemytank = new Tank(0,0,true,direction);
            assertTrue(enemytank.getImage().getWidth(null) >0, direction+"cannot get valid image");
            assertNotNull(enemytank.getImage());


        }
    }
}
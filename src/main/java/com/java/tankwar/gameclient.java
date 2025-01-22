package com.java.tankwar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class gameclient extends JComponent {
    private Tank playerTank;

    private List<Tank> enemyTanks;

    private List<Wall> Walls;

    public gameclient() {
        this.enemyTanks = enemyTanks;
        this.playerTank = new Tank(375,100,false,Direction.Down);
        this.enemyTanks = new ArrayList<>(12);
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 4; j++){
                this.enemyTanks.add( new Tank(320+60*i,200+40*j,true,Direction.Up));
            }
        }


        this.setPreferredSize(new Dimension(800, 600));

    }
@Override protected void paintComponent(Graphics g) {
       playerTank.draw(g);
       for(Tank tank:enemyTanks){
           tank.draw(g);
       }

}

    public static void main(String[] args) {
    JFrame frame = new JFrame();
    frame.setTitle("坦克大戰");
    frame.setIconImage(new ImageIcon("/Users/zhangtingen/Downloads/tankwar/assets/images/icon.png").getImage());
    System.setProperty("sun.java2d.uiScale", "1.0");
    gameclient client = new gameclient();
    client.repaint();
    frame.add(client);
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.pack();

    frame.addKeyListener(new KeyAdapter() {
        @Override public void keyPressed(KeyEvent e){
           client.playerTank.KeyPressed(e);


        }
        @Override public void keyReleased(KeyEvent e){
            client.playerTank.keyReleased(e);
        }


    });
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
    while(true){
        client.repaint();
        try{
            Thread.sleep(50);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    }
}

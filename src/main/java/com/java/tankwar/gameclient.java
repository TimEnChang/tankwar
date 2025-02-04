package com.java.tankwar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static java.awt.Font.BOLD;
import static java.awt.font.TextAttribute.FONT;

public class gameclient extends JComponent {

    private static final gameclient INSTANCE = new gameclient();

    public static gameclient getInstance(){
        return  INSTANCE;
    }

    private Tank playerTank;

    private List<Tank> enemyTanks;

    public List<Tank> getEnemyTanks() {
        return enemyTanks;
    }

    private final List<Wall> Walls;

    public List<Wall> getWalls() {
        return Walls;
    }

    private  List<Missile> missiles;

    private List<Explosion> explosions;

    void addExplosion(Explosion explosion){
        explosions.add(explosion);
    }

     void add(Missile missile){
       missiles.add(missile);}

    void removeMissile(Missile missile){
      missiles.remove(missile);
    }

    public Tank getPlayerTank() {
        return playerTank;
    }

    public List<Missile> getMissiles() {
        return missiles;
    }

    public gameclient() {
        this.enemyTanks = enemyTanks;
        this.playerTank = new Tank(375,20,false,Direction.Down);

        this.missiles = new CopyOnWriteArrayList<>();
        this.explosions = new ArrayList<>();
        this.Walls = Arrays.asList(
                new Wall(200,120,true,15),
                new Wall(200,510,true,15),
                new Wall(80,80,false,15),
                new Wall(690,80,false,15)
        );

        this.initEnemyTanks();


        this.setPreferredSize(new Dimension(800, 600));

    }

    private void initEnemyTanks() {
        this.enemyTanks = new ArrayList<>(12);
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 4; j++){
                this.enemyTanks.add( new Tank(320+60*i,200+40*j,true,Direction.Up));
            }
        }
    }

    @Override protected void paintComponent(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, 800, 600);
        if(!playerTank.isLive()){
            g.setColor(Color.red);
            g.setFont(new Font(null,Font.BOLD,100));
            g.drawString("GAME OVER",100,200);
            g.setFont(new Font(null,Font.BOLD,60));
            g.drawString("Press ENTER to restart",60,400);
        }else{
        playerTank.draw(g);

        enemyTanks.removeIf(t->!t.isLive());
        if(enemyTanks.isEmpty()){
            this.initEnemyTanks();
        }
        for(Tank tank:enemyTanks){
            tank.draw(g);
        }
        for(Wall wall:Walls){
            wall.draw(g);
        }


        missiles.removeIf(m->!m.isLive());
        for(Missile missile:missiles){
            missile.draw(g);
        }

        explosions.removeIf(e->!e.isLive());
        for(Explosion explosion:explosions){
            explosion.draw(g);
        }}

    }

    public static void main(String[] args) {

        JFrame frame = new JFrame();
        frame.setTitle("坦克大戰");
        frame.setIconImage(new ImageIcon("/Users/zhangtingen/Downloads/tankwar/assets/images/icon.png").getImage());
        System.setProperty("sun.java2d.uiScale", "1.0");
        new gameclient();
        final gameclient client = getInstance();
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
            if(client.getPlayerTank().isLive()){
            for(Tank tank :client.enemyTanks){
                tank.actRandomly();
            }
            try{
                Thread.sleep(50);
            }catch(Exception e){
                e.printStackTrace();}
            }
        }
    }

    public void restart() {
        if(!playerTank.isLive()){
            playerTank = new Tank(375,20,Direction.Down);

        }
        this.initEnemyTanks();
    }

}

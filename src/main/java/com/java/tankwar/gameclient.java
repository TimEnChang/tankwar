package com.java.tankwar;

import javax.swing.*;
import java.awt.*;

public class gameclient extends JComponent {
    public gameclient() {
        this.setPreferredSize(new Dimension(800, 600));
    }
@Override protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(new ImageIcon("assets/images/tankD.gif").getImage(), 400, 100, null );
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
    frame.setLocationRelativeTo(null );
    frame.setVisible(true);
    }
}

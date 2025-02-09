package Tanks.display;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Bullet extends JComponent implements ActionListener{
    public int bulletX;
    public int bulletY;
    private final Color color=Color.red;
    public String direction;
    public Tank tank;
    private final int dX=5;
    private final int dY=5;
    private final Timer timer;

    public Bullet(int bulletX, int bulletY,String direction,Tank tank){
        this.bulletX=bulletX;
        this.bulletY=bulletY;
        this.direction=direction;
        this.tank=tank;
        timer=new Timer(20,this);
        timer.start();
    }

    protected void paintComponent(Graphics g){
        if(g==null){
            return;
        }
        g.setColor(color);
        g.fillOval(bulletX, bulletY, 10, 10);
    }

    public void moveBUP(Graphics g){
        bulletY-=dY;
        paintComponent(g);
    }
    public void moveBLEFT(Graphics g){
        bulletX-=dX;
        paintComponent(g);
    }
    public void moveBDOWN(Graphics g){
        bulletY+=dY;
        paintComponent(g);
    }
    public void moveBRIGHT(Graphics g){
        bulletX+=dX;
        paintComponent(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(direction.equals("UP")){
            moveBUP(getGraphics());
        }
        if(direction.equals("LEFT")){
            moveBLEFT(getGraphics());
        }
        if(direction.equals("DOWN")){
            moveBDOWN(getGraphics());
        }
        if (direction.equals("RIGHT")){
            moveBRIGHT(getGraphics());
        }
    }
}

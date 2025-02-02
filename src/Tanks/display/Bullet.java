package Tanks.display;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Bullet extends JPanel{
    private int bulletX;
    private int bulletY;
    private Color color;
    private boolean moveUP;
    private boolean moveLEFT;
    private boolean moveDOWN;
    private boolean moveRIGHT;
    private int dX=5;
    private int dY=5;
    private Timer timer;
    public Bullet(int bulletX, int bulletY, Color color, boolean moveUP, boolean moveLEFT, boolean moveDOWN, boolean moveRIGHT){
        this.bulletX=bulletX;
        this.bulletY=bulletY;
        this.color=color;
        this.moveUP=moveUP;
        this.moveLEFT=moveLEFT;
        this.moveDOWN=moveDOWN;
        this.moveRIGHT=moveRIGHT;
        timer=new Timer(20, (ActionListener) this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(color);
        g.fillOval(bulletX, bulletY, 10, 10);
    }

    public void movement() {
        if(moveUP){
            bulletY-=dY;
        }
        if(moveLEFT){
            bulletX-=dX;
        }
        if(moveDOWN){
            bulletY+=dY;
        }
        if(moveRIGHT){
            bulletX+=dX;
        }
        repaint();
    }
}

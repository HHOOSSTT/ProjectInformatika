package Tanks.display;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

public class Map extends JPanel implements KeyListener {

    public Tank tank = new Tank(775,525,"UP");
    public Bullet bullet=null;
    public Timer timer;

    public Map() throws IOException {
        setFocusable(true);
        addKeyListener(this);
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Image imageStenka=null;
        try {
            imageStenka = ImageIO.read(new File("stenka.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Image imageStenkavokrug = null;
        try {
            imageStenkavokrug = ImageIO.read(new File("stenkavokrug.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int x=100;
        while(x<=750){
            g.drawImage(imageStenkavokrug, x, 75, 50, 25, null);
            x+=50;
        }
        g.drawImage(imageStenkavokrug,800,75,25,25,null);

        int y=100;
        while(y<=500){
            g.drawImage(imageStenkavokrug, 100, y, 25, 50, null);
            y+=50;
        }

        int w=100;
        while(w<=750){
            g.drawImage(imageStenkavokrug, w, 550, 50, 25, null);
            w+=50;
        }
        g.drawImage(imageStenkavokrug, 800, 550, 25, 25, null);

        int z=100;
        while(z<=500){
            g.drawImage(imageStenkavokrug, 800, z, 25, 50, null);
            z+=50;
        }

        g.setColor(new Color(0xD3D3D3));
        g.fillRect(125, 100, 675, 450);
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(new Color(0x808080));
        for (int i = 125; i < 550; i += 25) {
            g.drawLine(125, i, 800, i);
            ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        }
        for (int j = 150; j < 800; j += 25) {
            g.drawLine(j, 100, j, 550);
            ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        }

        int a = 150;
        int i = 1;
        while (a <= 750) {
            if ((i == 6) || (i == 7) || (i == 8)) {
                g.drawImage(imageStenka, a, 125, 25, 75, null);
                a += 50;
            } else {
                g.drawImage(imageStenka, a, 125, 25, 50, null);
                a += 50;
            }
            i++;
        }
        int b = 200;
        while (b <= 750) {
            g.drawImage(imageStenka, b, 250, 125, 25, null);
            b += 200;
        }
        g.drawImage(imageStenka, 375, 325, 50, 75, null);
        g.drawImage(imageStenka, 425, 350, 75, 25, null);
        g.drawImage(imageStenka, 500, 325, 50, 75, null);
        int c = 150;
        while (c <= 750) {
            g.drawImage(imageStenka, c, 450, 25, 75, null);
            c += 50;
        }
        g.drawImage(imageStenka, 175, 300, 150, 75, null);
        g.drawImage(imageStenka, 600, 300, 150, 75, null);
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        tank.paintComponent(g);
    }

    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyPressed(KeyEvent e) {
        int f=e.getKeyCode();
        if(f==KeyEvent.VK_W){
            tank.moveUp(tank.imagetankNOW.getGraphics());
        }
        if(f==KeyEvent.VK_A){
            tank.moveLEFT(tank.imagetankNOW.getGraphics());
        }
        if(f==KeyEvent.VK_S){
            tank.moveDOWN(tank.imagetankNOW.getGraphics());
        }
        if(f==KeyEvent.VK_D){
            tank.moveRIGHT(tank.imagetankNOW.getGraphics());
        }
        if(f==KeyEvent.VK_SPACE){
            if(tank.getDirection().equals("UP")){
                Bullet bullet = new Bullet(tank.getTankx()+8,tank.getTanky()-5,"UP",tank);
                add(bullet);
            }
            if(tank.getDirection().equals("LEFT")){
                Bullet bullet = new Bullet(tank.getTankx()-5,tank.getTanky()+8,"LEFT",tank);
                add(bullet);
            }
            if(tank.getDirection().equals("DOWN")){
                Bullet bullet = new Bullet(tank.getTankx()+8,tank.getTanky()+30,"DOWN",tank);
                add(bullet);
            }
            if(tank.getDirection().equals("RIGHT")){
                Bullet bullet = new Bullet(tank.getTankx()+30,tank.getTanky()+8,"RIGHT",tank);
                add(bullet);
            }
        }
        repaint();
    }
    @Override
    public void keyReleased(KeyEvent e) {}
}
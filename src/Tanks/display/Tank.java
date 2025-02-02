package Tanks.display;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

public class Tank extends JPanel implements KeyListener {
    int tankx;
    int tanky;
    String direction;
    Image imagetankNOW;
    public Tank(int tankx, int tanky, String direction) throws IOException {
        this.tankx=tankx;
        this.tanky=tanky;
        this.direction=direction;
        setFocusable(true);
        addKeyListener(this);
    }

    Image imageTankUP = ImageIO.read(new File("tankUP.png"));
    Image imageTankDOWN = ImageIO.read(new File("tankDOWN.png"));
    Image imageTankLEFT = ImageIO.read(new File("tankLEFT.png"));
    Image imageTankRIGHT = ImageIO.read(new File("tankRIGHT.png"));

    public void imageTank(String direction) {
        if(direction.equals("UP")){
            imagetankNOW=imageTankUP;
        }
        if(direction.equals("LEFT")){
            imagetankNOW=imageTankLEFT;
        }
        if(direction.equals("DOWN")){
            imagetankNOW=imageTankDOWN;
        }
        if(direction.equals("RIGHT")){
            imagetankNOW=imageTankRIGHT;
        }
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imagetankNOW,tankx,tanky,25,25,null);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int f = e.getKeyCode();
        if (f == KeyEvent.VK_W) {
            if (((((tankx == 775) || (tankx == 125) || (tankx == 325) || (tankx == 575)) && ((tanky >= 125) && (tanky <= 525)) || (((tankx == 150) || (tankx == 350) || (tankx == 550) || (tankx == 750)) && ((tanky > 175) && (tanky <= 425))) || (((tankx == 175) || (tankx == 725)) && (((tanky <= 525) && (tanky >= 400)) || ((tanky >= 125) && (tanky <= 275)))) || (((tankx == 200) || (tankx == 250) || (tankx == 300) || (tankx == 600) || (tankx == 650) || (tankx == 700)) && (((tanky >= 200) && (tanky <= 225)) || ((tanky <= 475) && (tanky >= 400)))) || (((tankx == 225) || (tankx == 275) || (tankx == 625) || (tankx == 675)) && (((tanky >= 125) && (tanky <= 225)) || ((tanky <= 525) && (tanky >= 400)))) || (((tankx == 375) || (tankx == 525)) && (((tanky <= 525) && (tanky >= 425)) || ((tanky >= 125) && (tanky <= 300)))) || (((tankx == 400) || (tankx == 500)) && ((tanky == 225) || (tanky == 300) || (tanky == 425))) || (((tankx == 425) || (tankx == 475)) && ((tanky != 250) && (tanky != 275) && (tanky != 350) && (tanky != 375) && (tanky >= 125))) || ((tankx == 450) && ((tanky == 425) || (tanky == 400) || (tanky == 325) || (tanky == 300) || (tanky == 225)))))) {
                tanky -= 25;
                imagetankNOW = imageTankUP;
                direction = "UP";
            }
        }
        if (f == KeyEvent.VK_A) {
            if (((tanky == 100) && ((tankx >= 150) && (tankx <= 775))) || (((tanky == 175) && (((tankx >= 150) && (tankx <= 375)) || (((tankx >= 550) && (tankx <= 775))))) || (((tanky == 200) || (tanky == 225) || (tanky == 275) || (tanky == 400) || (tanky == 425) || (tanky == 525)) && ((tankx >= 150) && (tankx <= 775)))) || ((tanky == 250) && ((tankx == 150) || (tankx == 175) || (tankx == 350) || (tankx == 375) || (tankx == 550) || (tankx == 575) || (tankx == 750) || (tankx == 775))) || ((tanky == 375) && (((tankx >= 150) && (tankx <= 350)) || ((tankx >= 575) && (tankx <= 775)) || (tankx == 450) || (tankx == 475))) || ((tanky == 300) && ((tankx == 150) || ((tankx >= 350) && (tankx <= 575)) || (tankx == 775))) || ((tanky == 325) && ((tankx == 150) || (tankx == 350) || (tankx == 450) || (tankx == 475) || (tankx == 575) || (tankx == 775))) || ((tanky == 350) && ((tankx == 150) || (tankx == 350) || (tankx == 575) || (tankx == 775)))) {
                tankx -= 25;
                imagetankNOW = imageTankLEFT;
                direction = "LEFT";
            }
        }
        if (f == KeyEvent.VK_S) {
            if ((((tanky == 100) || (tanky == 125) || (tanky == 150) || (tanky == 425) || (tanky == 450) || (tanky == 475) || (tanky == 500)) && ((tankx == 125) || (tankx == 175) || (tankx == 225) || (tankx == 275) || (tankx == 325) || (tankx == 375) || (tankx == 425) || (tankx == 475) || (tankx == 525) || (tankx == 575) || (tankx == 625) || (tankx == 675) || (tankx == 725) || (tankx == 775))) || ((tanky == 175) && ((tankx != 400) && (tankx != 450) && (tankx != 500))) || ((tanky == 200) || (tanky == 400)) || (((tanky == 225) || (tanky == 250)) && ((tankx == 125) || (tankx == 150) || (tankx == 175) || (tankx == 325) || (tankx == 350) || (tankx == 375) || (tankx == 525) || (tankx == 550) || (tankx == 575) || (tankx == 725) || (tankx == 750) || (tankx == 775))) || ((tanky == 275) && ((tankx != 175) && (tankx != 200) && (tankx != 225) && (tankx != 250) && (tankx != 275) && (tankx != 300) && (tankx != 600) && (tankx != 625) && (tankx != 650) && (tankx != 675) && (tankx != 700) && (tankx != 725))) || (((tanky == 300) && ((tankx == 125) || (tankx == 150) || (tankx == 325) || (tankx == 350) || (tankx == 425) || (tankx == 450) || (tankx == 475) || (tankx == 550) || (tankx == 575) || (tankx == 750) || (tankx == 775)))) || (((tanky == 325) || (tanky == 350)) && ((tankx == 125) || (tankx == 150) || (tankx == 325) || (tankx == 350) || (tankx == 550) || (tankx == 575) || (tankx == 750) || (tankx == 775))) || ((tanky == 375) && ((tankx != 375) && (tankx != 400) && (tankx != 500) && (tankx != 525)))) {
                tanky += 25;
                imagetankNOW = imageTankDOWN;
                direction = "RIGHT";
            }
        }
        if (f == KeyEvent.VK_D) {
            if (((tanky == 100) && ((tankx >= 125) && (tankx <= 750))) || (((tanky == 175) && (((tankx >= 125) && (tankx <= 350)) || (((tankx >= 525) && (tankx <= 750))))) || (((tanky == 200) || (tanky == 225) || (tanky == 275) || (tanky == 400) || (tanky == 425) || (tanky == 525)) && ((tankx >= 125) && (tankx <= 750)))) || ((tanky == 250) && ((tankx == 125) || (tankx == 150) || (tankx == 325) || (tankx == 350) || (tankx == 525) || (tankx == 550) || (tankx == 725) || (tankx == 750))) || ((tanky == 375) && (((tankx >= 125) && (tankx <= 300)) || ((tankx >= 550) && (tankx <= 750)) || (tankx == 425) || (tankx == 450))) || ((tanky == 300) && ((tankx == 125) || ((tankx >= 325) && (tankx <= 550)) || (tankx == 750))) || ((tanky == 325) && ((tankx == 125) || (tankx == 325) || (tankx == 425) || (tankx == 450) || (tankx == 550) || (tankx == 750))) || ((tanky == 350) && ((tankx == 125) || (tankx == 325) || (tankx == 550) || (tankx == 750)))) {
                tankx += 25;
                imagetankNOW = imageTankRIGHT;
                direction = "DOWN";
            }
        }
        repaint();
    }
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}
}
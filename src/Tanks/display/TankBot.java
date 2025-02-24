package Tanks.display;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class TankBot {
    private int tankbotX;
    private int tankbotY;
    private final String directionbot;
    Image imagetankbotNOW;
    private boolean isAlive = true;
    private final Timer timertankbot;
    private final Timer shoottankbot;
    private final BotBullets botBullets;

    public void setImagetankbotNOW(String directionbot) throws IOException {
        if(directionbot.equals("UP")){
            this.imagetankbotNOW=ImageIO.read(new File("tankbotUP.png"));
        }
        if(directionbot.equals("LEFT")){
            this.imagetankbotNOW=ImageIO.read(new File("tankbotLEFT.png"));
        }
        if(directionbot.equals("DOWN")){
            this.imagetankbotNOW=ImageIO.read(new File("tankbotDOWN.png"));
        }
        if(directionbot.equals("RIGHT")){
            this.imagetankbotNOW=ImageIO.read(new File("tankbotRIGHT.png"));
        }
    }

    public TankBot(int tankbotX, int tankbotY, String directionbot) throws IOException {
        this.tankbotX=tankbotX;
        this.tankbotY=tankbotY;
        this.directionbot=directionbot;
        setImagetankbotNOW(directionbot);
        timertankbot = new Timer(2500, e -> move());
        timertankbot.start();
        this.botBullets = new BotBullets(this);
        shoottankbot = new Timer(3500, e -> botBullets.shoot());
        shoottankbot.start();
    }

    public void paintComponent(Graphics g){
        if(isAlive){
            g.drawImage(imagetankbotNOW, tankbotX, tankbotY, 25, 25, null);
        }
        botBullets.paintComponent(g);
    }

    public boolean isHit(int bulletX, int bulletY) {
        boolean hit=false;
        if(!isAlive) {
            return false;
        }
        if(((bulletX>=tankbotX)&&(bulletX<=tankbotX+25))&&((bulletY<=tankbotY+25)&&(bulletY>=tankbotY))) {
            hit=true;
        }
        if(hit) {
            isAlive=false;
        }
        return hit;
    }

    private void move() {
        if(isAlive){
            int dX=25;
            int dY=25;
            if(directionbot.equals("UP")){
                if(((((tankbotX == 775) || (tankbotX == 125) || (tankbotX == 325) || (tankbotX == 575)) && ((tankbotY >= 125) && (tankbotY <= 525)) || (((tankbotX == 150) || (tankbotX == 350) || (tankbotX == 550) || (tankbotX == 750)) && ((tankbotY > 175) && (tankbotY <= 425))) || (((tankbotX == 175) || (tankbotX == 725)) && (((tankbotY <= 525) && (tankbotY >= 400)) || ((tankbotY >= 125) && (tankbotY <= 275)))) || (((tankbotX == 200) || (tankbotX == 250) || (tankbotX == 300) || (tankbotX == 600) || (tankbotX == 650) || (tankbotX == 700)) && (((tankbotY >= 200) && (tankbotY <= 225)) || ((tankbotY <= 475) && (tankbotY >= 400)))) || (((tankbotX == 225) || (tankbotX == 275) || (tankbotX == 625) || (tankbotX == 675)) && (((tankbotY >= 125) && (tankbotY <= 225)) || ((tankbotY <= 525) && (tankbotY >= 400)))) || (((tankbotX == 375) || (tankbotX == 525)) && (((tankbotY <= 525) && (tankbotY >= 425)) || ((tankbotY >= 125) && (tankbotY <= 300)))) || (((tankbotX == 400) || (tankbotX == 500)) && ((tankbotY == 225) || (tankbotY == 300) || (tankbotY == 425))) || (((tankbotX == 425) || (tankbotX == 475)) && ((tankbotY != 250) && (tankbotY != 275) && (tankbotY != 350) && (tankbotY != 375) && (tankbotY >= 125))) || ((tankbotX == 450) && ((tankbotY == 425) || (tankbotY == 400) || (tankbotY == 325) || (tankbotY == 300) || (tankbotY == 225)))))) {
                    tankbotY-=dY;
                }
            }
            if(directionbot.equals("LEFT")){
                if(((tankbotY == 100) && ((tankbotX >= 150) && (tankbotX <= 775))) || (((tankbotY == 175) && (((tankbotX >= 150) && (tankbotX <= 375)) || (((tankbotX >= 550) && (tankbotX <= 775))))) || (((tankbotY == 200) || (tankbotY == 225) || (tankbotY == 275) || (tankbotY == 400) || (tankbotY == 425) || (tankbotY == 525)) && ((tankbotX >= 150) && (tankbotX <= 775)))) || ((tankbotY == 250) && ((tankbotX == 150) || (tankbotX == 175) || (tankbotX == 350) || (tankbotX == 375) || (tankbotX == 550) || (tankbotX == 575) || (tankbotX == 750) || (tankbotX == 775))) || ((tankbotY == 375) && (((tankbotX >= 150) && (tankbotX <= 350)) || ((tankbotX >= 575) && (tankbotX <= 775)) || (tankbotX == 450) || (tankbotX == 475))) || ((tankbotY == 300) && ((tankbotX == 150) || ((tankbotX >= 350) && (tankbotX <= 575)) || (tankbotX == 775))) || ((tankbotY == 325) && ((tankbotX == 150) || (tankbotX == 350) || (tankbotX == 450) || (tankbotX == 475) || (tankbotX == 575) || (tankbotX == 775))) || ((tankbotY == 350) && ((tankbotX == 150) || (tankbotX == 350) || (tankbotX == 575) || (tankbotX == 775)))) {
                    tankbotX-=dX;
                }
            }
            if(directionbot.equals("DOWN")){
                if((((tankbotY == 100) || (tankbotY == 125) || (tankbotY == 150) || (tankbotY == 425) || (tankbotY == 450) || (tankbotY == 475) || (tankbotY == 500)) && ((tankbotX == 125) || (tankbotX == 175) || (tankbotX == 225) || (tankbotX == 275) || (tankbotX == 325) || (tankbotX == 375) || (tankbotX == 425) || (tankbotX == 475) || (tankbotX == 525) || (tankbotX == 575) || (tankbotX == 625) || (tankbotX == 675) || (tankbotX == 725) || (tankbotX == 775))) || ((tankbotY == 175) && ((tankbotX != 400) && (tankbotX != 450) && (tankbotX != 500))) || ((tankbotY == 200) || (tankbotY == 400)) || (((tankbotY == 225) || (tankbotY == 250)) && ((tankbotX == 125) || (tankbotX == 150) || (tankbotX == 175) || (tankbotX == 325) || (tankbotX == 350) || (tankbotX == 375) || (tankbotX == 525) || (tankbotX == 550) || (tankbotX == 575) || (tankbotX == 725) || (tankbotX == 750) || (tankbotX == 775))) || ((tankbotY == 275) && ((tankbotX != 175) && (tankbotX != 200) && (tankbotX != 225) && (tankbotX != 250) && (tankbotX != 275) && (tankbotX != 300) && (tankbotX != 600) && (tankbotX != 625) && (tankbotX != 650) && (tankbotX != 675) && (tankbotX != 700) && (tankbotX != 725))) || (((tankbotY == 300) && ((tankbotX == 125) || (tankbotX == 150) || (tankbotX == 325) || (tankbotX == 350) || (tankbotX == 425) || (tankbotX == 450) || (tankbotX == 475) || (tankbotX == 550) || (tankbotX == 575) || (tankbotX == 750) || (tankbotX == 775)))) || (((tankbotY == 325) || (tankbotY == 350)) && ((tankbotX == 125) || (tankbotX == 150) || (tankbotX == 325) || (tankbotX == 350) || (tankbotX == 550) || (tankbotX == 575) || (tankbotX == 750) || (tankbotX == 775))) || ((tankbotY == 375) && ((tankbotX != 375) && (tankbotX != 400) && (tankbotX != 500) && (tankbotX != 525)))) {
                    tankbotY+=dY;
                }
            }
            if(directionbot.equals("RIGHT")){
                if(((tankbotY == 100) && ((tankbotX >= 125) && (tankbotX <= 750))) || (((tankbotY == 175) && (((tankbotX >= 125) && (tankbotX <= 350)) || (((tankbotX >= 525) && (tankbotX <= 750))))) || (((tankbotY == 200) || (tankbotY == 225) || (tankbotY == 275) || (tankbotY == 400) || (tankbotY == 425) || (tankbotY == 525)) && ((tankbotX >= 125) && (tankbotX <= 750)))) || ((tankbotY == 250) && ((tankbotX == 125) || (tankbotX == 150) || (tankbotX == 325) || (tankbotX == 350) || (tankbotX == 525) || (tankbotX == 550) || (tankbotX == 725) || (tankbotX == 750))) || ((tankbotY == 375) && (((tankbotX >= 125) && (tankbotX <= 300)) || ((tankbotX >= 550) && (tankbotX <= 750)) || (tankbotX == 425) || (tankbotX == 450))) || ((tankbotY == 300) && ((tankbotX == 125) || ((tankbotX >= 325) && (tankbotX <= 550)) || (tankbotX == 750))) || ((tankbotY == 325) && ((tankbotX == 125) || (tankbotX == 325) || (tankbotX == 425) || (tankbotX == 450) || (tankbotX == 550) || (tankbotX == 750))) || ((tankbotY == 350) && ((tankbotX == 125) || (tankbotX == 325) || (tankbotX == 550) || (tankbotX == 750)))) {
                    tankbotX+=dX;
                }
            }
        }
    }

    public void updateBullets() {
        botBullets.update();
    }

    public boolean isAlive() {
        return isAlive;
    }

    public int getTankbotX() {
        return tankbotX;
    }

    public int getTankbotY() {
        return tankbotY;
    }

    public String getDirectionbot() {
        return directionbot;
    }

    public BotBullets getBotBullets() {
        return botBullets;
    }
}

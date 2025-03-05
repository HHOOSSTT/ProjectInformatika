package Tanks.display;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Tank {
    int tankx;
    int tanky;
    String direction;
    Image imagetankNOW;
    private final ArrayList<Bullet> bullets = new ArrayList<>();
    public TankBot tankbot1 = new TankBot(775, 100, "LEFT");
    public TankBot tankbot2 = new TankBot(125, 200, "DOWN");
    public TankBot tankbot3 = new TankBot(350, 275, "RIGHT");
    private final ArrayList<TankBot> tankBots = new ArrayList<>(Arrays.asList(tankbot1, tankbot2, tankbot3));

    Image imageTankUP = ImageIO.read(new File("tankUP.png"));
    Image imageTankDOWN = ImageIO.read(new File("tankDOWN.png"));
    Image imageTankLEFT = ImageIO.read(new File("tankLEFT.png"));
    Image imageTankRIGHT = ImageIO.read(new File("tankRIGHT.png"));

    public void setimageTank(String direction) {
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

    public Tank(int tankx, int tanky, String direction) throws IOException {
        this.tankx = tankx;
        this.tanky = tanky;
        this.direction = direction;
        setimageTank(direction);
    }

    public int getTankx(){
        return this.tankx;
    }

    public int getTanky(){
        return this.tanky;
    }

    public String getDirection(){
        return this.direction;
    }

    protected void paintComponent(Graphics g) {
        g.drawImage(imagetankNOW,tankx,tanky,25,25,null);
    }

    public boolean botisUP(){
        boolean botisUP = false;
        for(TankBot tankBot : tankBots){
            if (((direction.equals("UP")) && (tankx == tankBot.getTankbotX()) && (tanky == tankBot.getTankbotY() + 25))) {
                botisUP = true;
                break;
            }
        }
        return botisUP;
    }

    public boolean botisLEFT(){
        boolean botisLEFT = false;
        for(TankBot tankBot : tankBots){
            if (((direction.equals("LEFT")) && (tankx == tankBot.getTankbotX() + 25) && (tanky == tankBot.getTankbotY()))) {
                botisLEFT = true;
                break;
            }
        }
        return botisLEFT;
    }

    public boolean botisDOWN(){
        boolean botisDOWN = false;
        for(TankBot tankBot : tankBots){
            if (((direction.equals("DOWN")) && (tankx == tankBot.getTankbotX()) && (tanky == tankBot.getTankbotY() - 25))) {
                botisDOWN = true;
                break;
            }
        }
        return botisDOWN;
    }

    public boolean botisRIGHT(){
        boolean botisRIGHT = false;
        for(TankBot tankBot : tankBots){
            if (((direction.equals("RIGHT")) && (tankx == tankBot.getTankbotX() - 25) && (tanky == tankBot.getTankbotY()))) {
                botisRIGHT = true;
                break;
            }
        }
        return botisRIGHT;
    }

    public void moveUp(Graphics h) {
        if (((((tankx == 775) || (tankx == 125) || (tankx == 325) || (tankx == 575)) && ((tanky >= 125) && (tanky <= 525)) || (((tankx == 150) || (tankx == 350) || (tankx == 550) || (tankx == 750)) && ((tanky > 175) && (tanky <= 425))) || (((tankx == 175) || (tankx == 725)) && (((tanky <= 525) && (tanky >= 400)) || ((tanky >= 125) && (tanky <= 275)))) || (((tankx == 200) || (tankx == 250) || (tankx == 300) || (tankx == 600) || (tankx == 650) || (tankx == 700)) && (((tanky >= 200) && (tanky <= 225)) || ((tanky <= 475) && (tanky >= 400)))) || (((tankx == 225) || (tankx == 275) || (tankx == 625) || (tankx == 675)) && (((tanky >= 125) && (tanky <= 225)) || ((tanky <= 525) && (tanky >= 400)))) || (((tankx == 375) || (tankx == 525)) && (((tanky <= 525) && (tanky >= 425)) || ((tanky >= 125) && (tanky <= 300)))) || (((tankx == 400) || (tankx == 500)) && ((tanky == 225) || (tanky == 300) || (tanky == 425))) || (((tankx == 425) || (tankx == 475)) && ((tanky != 250) && (tanky != 275) && (tanky != 350) && (tanky != 375) && (tanky >= 125))) || ((tankx == 450) && ((tanky == 425) || (tanky == 400) || (tanky == 325) || (tanky == 300) || (tanky == 225))))) && (!botisUP())) {
            tanky -= 25;
            direction = "UP";
            setimageTank(direction);
            paintComponent(h);
        }
    }
    public void moveLEFT(Graphics h){
        if ((((tanky == 100) && ((tankx >= 150) && (tankx <= 775))) || (((tanky == 175) && (((tankx >= 150) && (tankx <= 375)) || (((tankx >= 550) && (tankx <= 775))))) || (((tanky == 200) || (tanky == 225) || (tanky == 275) || (tanky == 400) || (tanky == 425) || (tanky == 525)) && ((tankx >= 150) && (tankx <= 775)))) || ((tanky == 250) && ((tankx == 150) || (tankx == 175) || (tankx == 350) || (tankx == 375) || (tankx == 550) || (tankx == 575) || (tankx == 750) || (tankx == 775))) || ((tanky == 375) && (((tankx >= 150) && (tankx <= 350)) || ((tankx >= 575) && (tankx <= 775)) || (tankx == 450) || (tankx == 475))) || ((tanky == 300) && ((tankx == 150) || ((tankx >= 350) && (tankx <= 575)) || (tankx == 775))) || ((tanky == 325) && ((tankx == 150) || (tankx == 350) || (tankx == 450) || (tankx == 475) || (tankx == 575) || (tankx == 775))) || ((tanky == 350) && ((tankx == 150) || (tankx == 350) || (tankx == 575) || (tankx == 775)))) && (!botisLEFT())) {
            tankx -= 25;
            direction = "LEFT";
            setimageTank(direction);
            paintComponent(h);
        }
    }
    public void moveRIGHT(Graphics h){
        if ((((tanky == 100) && ((tankx >= 125) && (tankx <= 750))) || (((tanky == 175) && (((tankx >= 125) && (tankx <= 350)) || (((tankx >= 525) && (tankx <= 750))))) || (((tanky == 200) || (tanky == 225) || (tanky == 275) || (tanky == 400) || (tanky == 425) || (tanky == 525)) && ((tankx >= 125) && (tankx <= 750)))) || ((tanky == 250) && ((tankx == 125) || (tankx == 150) || (tankx == 325) || (tankx == 350) || (tankx == 525) || (tankx == 550) || (tankx == 725) || (tankx == 750))) || ((tanky == 375) && (((tankx >= 125) && (tankx <= 300)) || ((tankx >= 550) && (tankx <= 750)) || (tankx == 425) || (tankx == 450))) || ((tanky == 300) && ((tankx == 125) || ((tankx >= 325) && (tankx <= 550)) || (tankx == 750))) || ((tanky == 325) && ((tankx == 125) || (tankx == 325) || (tankx == 425) || (tankx == 450) || (tankx == 550) || (tankx == 750))) || ((tanky == 350) && ((tankx == 125) || (tankx == 325) || (tankx == 550) || (tankx == 750)))) && (!botisRIGHT())) {
            tankx += 25;
            direction = "RIGHT";
            setimageTank(direction);
            paintComponent(h);
        }
    }
    public void moveDOWN(Graphics h){
        if (((((tanky == 100) || (tanky == 125) || (tanky == 150) || (tanky == 425) || (tanky == 450) || (tanky == 475) || (tanky == 500)) && ((tankx == 125) || (tankx == 175) || (tankx == 225) || (tankx == 275) || (tankx == 325) || (tankx == 375) || (tankx == 425) || (tankx == 475) || (tankx == 525) || (tankx == 575) || (tankx == 625) || (tankx == 675) || (tankx == 725) || (tankx == 775))) || ((tanky == 175) && ((tankx != 400) && (tankx != 450) && (tankx != 500))) || ((tanky == 200) || (tanky == 400)) || (((tanky == 225) || (tanky == 250)) && ((tankx == 125) || (tankx == 150) || (tankx == 175) || (tankx == 325) || (tankx == 350) || (tankx == 375) || (tankx == 525) || (tankx == 550) || (tankx == 575) || (tankx == 725) || (tankx == 750) || (tankx == 775))) || ((tanky == 275) && ((tankx != 175) && (tankx != 200) && (tankx != 225) && (tankx != 250) && (tankx != 275) && (tankx != 300) && (tankx != 600) && (tankx != 625) && (tankx != 650) && (tankx != 675) && (tankx != 700) && (tankx != 725))) || (((tanky == 300) && ((tankx == 125) || (tankx == 150) || (tankx == 325) || (tankx == 350) || (tankx == 425) || (tankx == 450) || (tankx == 475) || (tankx == 550) || (tankx == 575) || (tankx == 750) || (tankx == 775)))) || (((tanky == 325) || (tanky == 350)) && ((tankx == 125) || (tankx == 150) || (tankx == 325) || (tankx == 350) || (tankx == 550) || (tankx == 575) || (tankx == 750) || (tankx == 775))) || ((tanky == 375) && ((tankx != 375) && (tankx != 400) && (tankx != 500) && (tankx != 525)))) && (!botisDOWN())) {
            tanky += 25;
            direction = "DOWN";
            setimageTank(direction);
            paintComponent(h);
        }
    }

    public void returnAfterHit(){
        tankx = 775;
        tanky = 525;
        direction = "UP";
        setimageTank(direction);
    }
}
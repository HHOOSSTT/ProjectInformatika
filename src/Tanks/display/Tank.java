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
    private final ArrayList<Bullet> botbullets = new ArrayList<>();
    public TankBot tankbot1 = new TankBot(775, 100, "LEFT", botbullets);
    public TankBot tankbot2 = new TankBot(125, 200, "DOWN", botbullets);
    public TankBot tankbot3 = new TankBot(350, 275, "RIGHT", botbullets);
    private final ArrayList<TankBot> tankBots = new ArrayList<>(Arrays.asList(tankbot1, tankbot2, tankbot3));
    private final int SPEED = 25;
    private final int SIZE_OF_TANK = 25;
    public int[][] walls = new int[21][30];
    public final int IS_WALL_AROUND_MAP = 1;
    public final int IS_WALL_IN_MAP = 2;
    public final int Initial_OffsetX = 4;
    public final int Initial_OffsetY = 3;
    public final int Nearby_Cell = 1;

    Image imageTankUP = ImageIO.read(new File("tankUP.png"));
    Image imageTankDOWN = ImageIO.read(new File("tankDOWN.png"));
    Image imageTankLEFT = ImageIO.read(new File("tankLEFT.png"));
    Image imageTankRIGHT = ImageIO.read(new File("tankRIGHT.png"));

    public void setimageTank(String direction) {
        if(direction.equals("UP")){
            imagetankNOW = imageTankUP;
        }
        if(direction.equals("LEFT")){
            imagetankNOW = imageTankLEFT;
        }
        if(direction.equals("DOWN")){
            imagetankNOW = imageTankDOWN;
        }
        if(direction.equals("RIGHT")){
            imagetankNOW = imageTankRIGHT;
        }
    }

    public void fillWallsAroundMap(){
        int IS_WALL_AROUND_MAP = 1;
        for (int x = 0; x < 29; x++) {
            walls[0][x] = IS_WALL_AROUND_MAP;
        }
        for (int y = 0; y < 20; y++) {
            walls[y][28] = IS_WALL_AROUND_MAP;
        }
        for (int x = 0; x < 28; x++) {
            walls[19][x] = IS_WALL_AROUND_MAP;
        }
        for (int y = 0; y < 20; y++) {
            walls[y][0] = IS_WALL_AROUND_MAP;
        }
    }

    public void fillWallsInMap(){
        int IS_WALL_IN_MAP = 2;
        for (int x = 2; x < 27; x += 2) {
            if (x != 12 && x != 14 && x != 16) {
                walls[2][x] = IS_WALL_IN_MAP;
                walls[3][x] = IS_WALL_IN_MAP;
            } else {
                walls[2][x] = IS_WALL_IN_MAP;
                walls[3][x] = IS_WALL_IN_MAP;
                walls[4][x] = IS_WALL_IN_MAP;
            }
        }
        for (int x = 4; x < 25; x += 8) {
            for (int i = 0; i < 5; i++) {
                walls[7][x + i] = IS_WALL_IN_MAP;
            }
        }
        for (int y = 9; y < 12; y++) {
            for (int x = 3; x < 9; x++) {
                walls[y][x] = IS_WALL_IN_MAP;
            }
        }
        for (int x = 2; x <= 27; x += 2) {
            walls[15][x] = IS_WALL_IN_MAP;
            walls[16][x] = IS_WALL_IN_MAP;
            walls[17][x] = IS_WALL_IN_MAP;
        }
        for (int y = 10; y < 13; y++) {
            for (int x = 11; x < 13; x++) {
                walls[y][x] = IS_WALL_IN_MAP;
            }
        }
        for (int x = 12; x < 16; x++) {
            walls[11][x] = IS_WALL_IN_MAP;
        }
        for (int y = 10; y < 13; y++) {
            for (int x = 16; x < 18; x++) {
                walls[y][x] = IS_WALL_IN_MAP;
            }
        }
        for (int y = 9; y < 12; y++) {
            for (int x = 20; x < 26; x++) {
                walls[y][x] = IS_WALL_IN_MAP;
            }
        }
    }

    public void fillMap() {
        fillWallsAroundMap();
        fillWallsInMap();
    }

    public Tank(int tankx, int tanky, String direction) throws IOException {
        this.tankx = tankx;
        this.tanky = tanky;
        this.direction = direction;
        setimageTank(direction);
        fillMap();
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
        g.drawImage(imagetankNOW,tankx,tanky,SIZE_OF_TANK,SIZE_OF_TANK,null);
    }

    public boolean botisUP(){
        for(TankBot tankBot : tankBots){
            if ((tankx == tankBot.getTankbotX()) && (tanky == tankBot.getTankbotY() + SIZE_OF_TANK) && (tankBot.isAlive())) {
                return true;
            }
        }
        return false;
    }

    public boolean botisLEFT(){;
        for(TankBot tankBot : tankBots){
            if ((tankx == tankBot.getTankbotX() + SIZE_OF_TANK) && (tanky == tankBot.getTankbotY()) && (tankBot.isAlive())) {
                return true;
            }
        }
        return false;
    }

    public boolean botisDOWN(){
        for(TankBot tankBot : tankBots){
            if ((tankx == tankBot.getTankbotX()) && (tanky == tankBot.getTankbotY() - SIZE_OF_TANK) && (tankBot.isAlive())) {
                return true;
            }
        }
        return false;
    }

    public boolean botisRIGHT(){
        for(TankBot tankBot : tankBots){
            if ((tankx == tankBot.getTankbotX() - SIZE_OF_TANK) && (tanky == tankBot.getTankbotY()) && (tankBot.isAlive())) {
                return true;
            }
        }
        return false;
    }

    public boolean checkNearbyCellUP(){
        if((walls[tanky/25-Initial_OffsetY-Nearby_Cell][tankx/25-Initial_OffsetX] != IS_WALL_AROUND_MAP) && (walls[tanky/25-Initial_OffsetY-Nearby_Cell][tankx/25-Initial_OffsetX] != IS_WALL_IN_MAP)){
            return true;
        }
        return false;
    }

    public boolean checkNearbyCellLEFT(){
        if((walls[tanky/25-Initial_OffsetY][tankx/25-Initial_OffsetX-Nearby_Cell] != IS_WALL_AROUND_MAP) && (walls[tanky/25-Initial_OffsetY][tankx/25-Initial_OffsetX-Nearby_Cell] != IS_WALL_IN_MAP)){
            return true;
        }
        return false;
    }

    public boolean checkNearbyCellDOWN(){
        if((walls[tanky/25-Initial_OffsetY+Nearby_Cell][tankx/25-Initial_OffsetX] != IS_WALL_AROUND_MAP) && (walls[tanky/25-Initial_OffsetY+Nearby_Cell][tankx/25-Initial_OffsetX] != IS_WALL_IN_MAP)){
            return true;
        }
        return false;
    }

    public boolean checkNearbyCellRIGHT(){
        if((walls[tanky/25-Initial_OffsetY][tankx/25-Initial_OffsetX+Nearby_Cell] != IS_WALL_AROUND_MAP) && (walls[tanky/25-Initial_OffsetY][tankx/25-Initial_OffsetX+Nearby_Cell] != IS_WALL_IN_MAP)){
            return true;
        }
        return false;
    }

    public void moveUp(Graphics h) {
        if(checkNearbyCellUP()){
            tanky -= SPEED;
            direction = "UP";
            setimageTank(direction);
            paintComponent(h);
        }
    }

    public void moveLEFT(Graphics h){
        if(checkNearbyCellLEFT()){
            tankx -= SPEED;
            direction = "LEFT";
            setimageTank(direction);
            paintComponent(h);
        }
    }

    public void moveRIGHT(Graphics h){
        if(checkNearbyCellRIGHT()){
            tankx += SPEED;
            direction = "RIGHT";
            setimageTank(direction);
            paintComponent(h);
        }
    }

    public void moveDOWN(Graphics h){
        if(checkNearbyCellDOWN()){
            tanky += SPEED;
            direction = "DOWN";
            setimageTank(direction);
            paintComponent(h);
        }
    }

    public void returnAfterHit1(){
        tankx = 175;
        tanky = 525;
        direction = "UP";
        setimageTank(direction);
    }

    public void returnAfterHit2(){
        tankx = 775;
        tanky = 525;
        direction = "UP";
        setimageTank(direction);
    }

    public void remove(){
        tankx = -1000;
        tanky = -1000;
        imagetankNOW = null;
        direction = "";
    }
}
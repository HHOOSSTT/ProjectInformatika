package Tanks.display;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class TankBot {
    private int tankbotX;
    private int tankbotY;
    private String directionbot;
    private Image imagetankbotNOW;
    private boolean isAlive = true;
    private final Timer timertankbot;
    private final ArrayList<Bullet> botbullets;
    private final Timer shoottankbot;
    public int[][] walls = new int[21][30];
    public final int IS_WALL_AROUND_MAP = 1;
    public final int IS_WALL_IN_MAP = 2;
    public final int Initial_OffsetX = 4;
    public final int Initial_OffsetY = 3;
    public final int Nearby_Cell = 1;

    public void setImagetankbotNOW(String directionbot) throws IOException {
        if(directionbot.equals("UP")) {
            this.imagetankbotNOW=ImageIO.read(new File("tankbotUP.png"));
        }
        if(directionbot.equals("LEFT")) {
            this.imagetankbotNOW=ImageIO.read(new File("tankbotLEFT.png"));
        }
        if(directionbot.equals("DOWN")) {
            this.imagetankbotNOW=ImageIO.read(new File("tankbotDOWN.png"));
        }
        if(directionbot.equals("RIGHT")) {
            this.imagetankbotNOW=ImageIO.read(new File("tankbotRIGHT.png"));
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

    public TankBot(int tankbotX, int tankbotY, String directionbot, ArrayList<Bullet> botbullets) throws IOException {
        this.tankbotX=tankbotX;
        this.tankbotY=tankbotY;
        this.directionbot=directionbot;
        setImagetankbotNOW(directionbot);
        timertankbot = new Timer(2500, e -> move());
        timertankbot.start();
        this.botbullets = botbullets;
        shoottankbot = new Timer(3500, e->shoot());
        shoottankbot.start();
        fillMap();
    }

    public void paintComponent(Graphics g) {
        int SIZE_OF_SQUARE = 25;
        g.drawImage(imagetankbotNOW, tankbotX, tankbotY, SIZE_OF_SQUARE, SIZE_OF_SQUARE, null);
    }

    public void shoot(){
        int bulletX = tankbotX;
        int bulletY = tankbotY;
        String direction = directionbot;
        if(direction.equals("UP")) {
            bulletX += 8;
            bulletY -= 5;
        }
        if(direction.equals("LEFT")) {
            bulletX -= 5;
            bulletY += 8;
        }
        if(direction.equals("DOWN")) {
            bulletX += 8;
            bulletY += 20;
        }
        if(direction.equals("RIGHT")) {
            bulletX += 20;
            bulletY += 8;
        }
        botbullets.add(new Bullet(bulletX, bulletY, direction));
    }

    public boolean checkNearbyCellUP(){
        if((walls[tankbotY/25-Initial_OffsetY-Nearby_Cell][tankbotX/25-Initial_OffsetX] != IS_WALL_AROUND_MAP) && (walls[tankbotY/25-Initial_OffsetY-Nearby_Cell][tankbotX/25-Initial_OffsetX] != IS_WALL_IN_MAP)){
            return true;
        }
        return false;
    }

    public boolean checkNearbyCellLEFT(){
        if((walls[tankbotY/25-Initial_OffsetY][tankbotX/25-Initial_OffsetX-Nearby_Cell] != IS_WALL_AROUND_MAP) && (walls[tankbotY/25-Initial_OffsetY][tankbotX/25-Initial_OffsetX-Nearby_Cell] != IS_WALL_IN_MAP)){
            return true;
        }
        return false;
    }

    public boolean checkNearbyCellDOWN(){
        if((walls[tankbotY/25-Initial_OffsetY+Nearby_Cell][tankbotX/25-Initial_OffsetX] != IS_WALL_AROUND_MAP) && (walls[tankbotY/25-Initial_OffsetY+Nearby_Cell][tankbotX/25-Initial_OffsetX] != IS_WALL_IN_MAP)){
            return true;
        }
        return false;
    }

    public boolean checkNearbyCellRIGHT(){
        if((walls[tankbotY/25-Initial_OffsetY][tankbotX/25-Initial_OffsetX+Nearby_Cell] != IS_WALL_AROUND_MAP) && (walls[tankbotY/25-Initial_OffsetY][tankbotX/25-Initial_OffsetX+Nearby_Cell] != IS_WALL_IN_MAP)){
            return true;
        }
        return false;
    }

    public void move() {
        if(isAlive){
            int dX=25;
            int dY=25;
            if(directionbot.equals("UP")) {
                if(checkNearbyCellUP()){
                    tankbotY -= dY;
                }
            }
            if(directionbot.equals("LEFT")) {
                if(checkNearbyCellLEFT()){
                    tankbotX -= dX;
                }
            }
            if(directionbot.equals("DOWN")) {
                if(checkNearbyCellDOWN()){
                    tankbotY += dY;
                }
            }
            if(directionbot.equals("RIGHT")) {
                if(checkNearbyCellRIGHT()){
                    tankbotX += dX;
                }
            }
        }
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

    public void stopBotTimer(){
        timertankbot.stop();
    }

    public void rotate() throws IOException {
        if((tankbotX==125) && (tankbotY==375)){
            setImagetankbotNOW("RIGHT");
            this.directionbot = "RIGHT";
        }
        if((tankbotX==725) && (tankbotY==100)){
            setImagetankbotNOW("DOWN");
            this.directionbot = "DOWN";
        }
        if((tankbotX==725) && (tankbotY==225)){
            setImagetankbotNOW("LEFT");
            this.directionbot = "LEFT";
        }
        if((tankbotX==550) && (tankbotY==275)){
            setImagetankbotNOW("DOWN");
            this.directionbot = "DOWN";
        }
        if((tankbotX==550) && (tankbotY==425)){
            setImagetankbotNOW("RIGHT");
            this.directionbot = "RIGHT";
        }
        if((tankbotX==425) && (tankbotY==225)){
            setImagetankbotNOW("UP");
            this.directionbot = "UP";
        }
        if((tankbotX==350) && (tankbotY==375)){
            setImagetankbotNOW("UP");
            this.directionbot = "UP";
        }
        if((tankbotX==775) && (tankbotY==400)){
            setImagetankbotNOW("UP");
            this.directionbot = "UP";
        }
        if((tankbotX==425) && (tankbotY==100)){
            setImagetankbotNOW("RIGHT");
            this.directionbot = "RIGHT";
        }
        if((tankbotX==350) && (tankbotY==175)){
            setImagetankbotNOW("LEFT");
            this.directionbot = "LEFT";
        }
        if((tankbotX==775) && (tankbotY==425)){
            setImagetankbotNOW("UP");
            this.directionbot = "UP";
        }
        if((tankbotX==775) && (tankbotY==275)){
            setImagetankbotNOW("LEFT");
            this.directionbot = "LEFT";
        }
        if((tankbotX==125) && (tankbotY==175)){
            setImagetankbotNOW("DOWN");
            this.directionbot = "DOWN";
        }
    }

    public void returnAfterHit(int tankbotX, int tankbotY, String directionbot) throws IOException {
        this.tankbotX = tankbotX;
        this.tankbotY = tankbotY;
        this.directionbot = directionbot;
        setImagetankbotNOW(directionbot);
        isAlive = true;
    }

    public void setisAlive(boolean isAlive){
        this.isAlive = isAlive;
    }
}
package Tanks.display;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TankBot {

    private int tankbotX;
    private int tankbotY;
    private String directionbot;
    private Image imagetankbotNOW;
    private boolean isAlive = true;
    private final Timer timertankbot;
    private final ArrayList<Bullet> botbullets;
    private final Timer shoottankbot;
    public final int IS_WALL_AROUND_MAP = 1;
    public final int IS_WALL_IN_MAP = 2;
    public final int Initial_OffsetX = 75;
    public final int Initial_OffsetY = 50;
    File mapFile = new File("Map.txt");
    private final ArrayList<String> mapLines = new ArrayList<>();
    public int NEARBY_CELL = 1;
    public int STARTING_ARRAY_FROM_ZERO = 1;
    private final int SIZE_OF_TANKBOT = 25;

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

    public TankBot(int tankbotX, int tankbotY, String directionbot, ArrayList<Bullet> botbullets) throws IOException {
        this.tankbotX=tankbotX;
        this.tankbotY=tankbotY;
        this.directionbot=directionbot;
        setImagetankbotNOW(directionbot);
        timertankbot = new Timer(2500, e -> move());
        timertankbot.start();
        this.botbullets = botbullets;
        shoottankbot = new Timer(3500, e-> {
            try {
                shoot();
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
        shoottankbot.start();
        Scanner scanner = new Scanner(mapFile);
        while(scanner.hasNextLine()){
            mapLines.add(scanner.nextLine());
        }
    }

    public void paintComponent(Graphics g) {
        int SIZE_OF_SQUARE = 25;
        g.drawImage(imagetankbotNOW, tankbotX, tankbotY, SIZE_OF_SQUARE, SIZE_OF_SQUARE, null);
    }

    public void shoot() throws FileNotFoundException {
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

    public boolean isWall(int cellX, int cellY){
        String str = mapLines.get(cellY - STARTING_ARRAY_FROM_ZERO);
        int cellCode = str.charAt(cellX - STARTING_ARRAY_FROM_ZERO) - '0';
        return (cellCode == IS_WALL_AROUND_MAP) || (cellCode == IS_WALL_IN_MAP);
    }

    public boolean checkNearbyCellUP(){
        int cellX = (tankbotX - Initial_OffsetX)/SIZE_OF_TANKBOT;
        int cellY = (tankbotY - Initial_OffsetY)/SIZE_OF_TANKBOT - NEARBY_CELL;
        return !isWall(cellX, cellY);
    }

    public boolean checkNearbyCellLEFT(){
        int cellX = (tankbotX - Initial_OffsetX)/SIZE_OF_TANKBOT - NEARBY_CELL;
        int cellY = (tankbotY - Initial_OffsetY)/SIZE_OF_TANKBOT;
        return !isWall(cellX, cellY);
    }

    public boolean checkNearbyCellDOWN(){
        int cellX = (tankbotX - Initial_OffsetX)/SIZE_OF_TANKBOT;
        int cellY = (tankbotY - Initial_OffsetY)/SIZE_OF_TANKBOT + NEARBY_CELL;
        return !isWall(cellX, cellY);
    }

    public boolean checkNearbyCellRIGHT() {
        int cellX = (tankbotX - Initial_OffsetX) / SIZE_OF_TANKBOT + NEARBY_CELL;
        int cellY = (tankbotY - Initial_OffsetY) / SIZE_OF_TANKBOT;
        return !isWall(cellX, cellY);
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
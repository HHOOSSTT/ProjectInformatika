package Tanks.display;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Tank {

    int tankx;
    int tanky;
    String direction;
    Image imagetankNOW;
    private final int SPEED = 25;
    private final int SIZE_OF_TANK = 25;
    public final int IS_WALL_AROUND_MAP = 1;
    public final int IS_WALL_IN_MAP = 2;
    public final int Initial_OffsetX = 75;
    public final int Initial_OffsetY = 50;
    File mapFile = new File("Map.txt");
    private final ArrayList<String> mapLines = new ArrayList<>();
    public int NEARBY_CELL = 1;
    public int STARTING_ARRAY_FROM_ZERO = 1;
    public boolean isAlive;

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


    public Tank(int tankx, int tanky, String direction) throws IOException {
        this.tankx = tankx;
        this.tanky = tanky;
        this.direction = direction;
        setimageTank(direction);
        isAlive = true;
        Scanner scanner = new Scanner(mapFile);
        while(scanner.hasNextLine()){
            mapLines.add(scanner.nextLine());
        }
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
        g.drawImage(imagetankNOW, tankx, tanky, SIZE_OF_TANK, SIZE_OF_TANK, null);
    }

    public boolean isWall(int cellX, int cellY){
        String str = mapLines.get(cellY - STARTING_ARRAY_FROM_ZERO);
        int cellCode = str.charAt(cellX - STARTING_ARRAY_FROM_ZERO) - '0';
        return (cellCode == IS_WALL_AROUND_MAP) || (cellCode == IS_WALL_IN_MAP);
    }

    public boolean checkNearbyCellUP(){
        int cellX = (tankx - Initial_OffsetX)/SIZE_OF_TANK;
        int cellY = (tanky - Initial_OffsetY)/SIZE_OF_TANK - NEARBY_CELL;
        return !isWall(cellX, cellY);
    }

    public boolean checkNearbyCellLEFT(){
        int cellX = (tankx - Initial_OffsetX)/SIZE_OF_TANK - NEARBY_CELL;
        int cellY = (tanky - Initial_OffsetY)/SIZE_OF_TANK;
        return !isWall(cellX, cellY);
    }

    public boolean checkNearbyCellDOWN(){
        int cellX = (tankx - Initial_OffsetX)/SIZE_OF_TANK;
        int cellY = (tanky - Initial_OffsetY)/SIZE_OF_TANK + NEARBY_CELL;
        return !isWall(cellX, cellY);
    }

    public boolean checkNearbyCellRIGHT(){
        int cellX = (tankx - Initial_OffsetX)/SIZE_OF_TANK + NEARBY_CELL;
        int cellY = (tanky - Initial_OffsetY)/SIZE_OF_TANK;
        return !isWall(cellX, cellY);
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

    public void returnAfterHitTank1(){
        int INITIAL_TANK1X = 175;
        int INITIAL_TANK1Y = 525;
        tankx = INITIAL_TANK1X;
        tanky = INITIAL_TANK1Y;
        direction = "UP";
        setimageTank(direction);
    }

    public void returnAfterHitTank2(){
        int INITIAL_TANK2X = 775;
        int INITIAL_TANK2Y = 525;
        tankx = INITIAL_TANK2X;
        tanky = INITIAL_TANK2Y;
        direction = "UP";
        setimageTank(direction);
    }

    public void remove(){
        int COORDINATE_OF_TANK_OUT_OF_MAP = -1000;
        tankx = COORDINATE_OF_TANK_OUT_OF_MAP;
        tanky = COORDINATE_OF_TANK_OUT_OF_MAP;
        imagetankNOW = null;
        direction = "";
    }

    public void setIsAlive(boolean isAlive){
        this.isAlive = isAlive;
    }

    public boolean getIsAlive(){
        return this.isAlive;
    }
}
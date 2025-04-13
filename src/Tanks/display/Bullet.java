package Tanks.display;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Bullet extends JComponent implements ActionListener {

    public int bulletX;
    public int bulletY;
    private final Color color = Color.red;
    public String direction;
    private boolean canMove = true;
    public final int IS_WALL_AROUND_MAP = 1;
    public final int IS_WALL_IN_MAP = 2;
    public final int Initial_OffsetX = 75;
    public final int Initial_OffsetY = 50;
    public final int Nearby_Cell = 1;
    public final int BULLET_SIZE = 10;
    private ArrayList<String> mapLines = new ArrayList<>();
    public int STARTING_ARRAY_FROM_ZERO = 1;
    private final int SIZE_OF_CELL = 25;
    File mapFile = new File("Map.txt");

    public Bullet(int bulletX, int bulletY, String direction) throws FileNotFoundException {
        this.bulletX = bulletX;
        this.bulletY = bulletY;
        this.direction = direction;
        Scanner scanner = new Scanner(mapFile);
        while(scanner.hasNextLine()){
            mapLines.add(scanner.nextLine());
        }
    }

    public int getBulletX() {
        return bulletX;
    }

    public int getBulletY() {
        return bulletY;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(!canMove){
            return;
        }
        g.setColor(color);
        g.fillOval(bulletX,bulletY, BULLET_SIZE, BULLET_SIZE);
    }

    private boolean isWall(int x, int y) {
        int cellX = (x - Initial_OffsetX) / SIZE_OF_CELL;
        int cellY = (y - Initial_OffsetY) / SIZE_OF_CELL;
        int cellCode = mapLines.get(cellY - STARTING_ARRAY_FROM_ZERO).charAt(cellX - STARTING_ARRAY_FROM_ZERO) - '0';
        return (cellCode == IS_WALL_AROUND_MAP) ||  (cellCode == IS_WALL_IN_MAP);
    }

    private boolean checkCollision() {
        int HALF = 2;
        int centerX = bulletX + BULLET_SIZE / HALF;
        int centerY = bulletY + BULLET_SIZE / HALF;
        if(direction.equals("UP")){
            return isWall(centerX, bulletY - Nearby_Cell);
        }
        if(direction.equals("LEFT")){
            return isWall(bulletX - Nearby_Cell, centerY);
        }
        if(direction.equals("DOWN")){
            return isWall(centerX, bulletY + BULLET_SIZE + Nearby_Cell);
        }
        if(direction.equals("RIGHT")){
            return isWall(bulletX + BULLET_SIZE + Nearby_Cell, centerY);
        }else{
            return false;
        }
    }

    public void move() {
        if((checkCollision())){
            canMove = false;
            return;
        }
        int speed = 4;
        if((canMove) && (!checkCollision())){
            if(direction.equals("UP")) {
                bulletY -= speed;
            }
            if(direction.equals("DOWN")) {
                bulletY += speed;
            }
            if(direction.equals("LEFT")) {
                bulletX -= speed;
            }
            if(direction.equals("RIGHT")) {
                bulletX += speed;
            }
        }
    }

    public boolean ishitTank(int bulletX, int bulletY, int tankX, int tankY) {
        int SIZE_OF_TANK = 25;
        if(((bulletX>=tankX) && (bulletX<=tankX+SIZE_OF_TANK)) && ((bulletY<=tankY+SIZE_OF_TANK) && (bulletY>=tankY))){
            return true;
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
    }

    public boolean isCanMove(){
        return canMove;
    }

    public void setcanMove(boolean canMove){
        this.canMove = canMove;
    }
}
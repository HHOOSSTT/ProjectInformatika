package Tanks.display;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Bullet extends JComponent implements ActionListener {
    public int bulletX;
    public int bulletY;
    private final Color color = Color.red;
    public String direction;
    private boolean canMove = true;
    public int[][] walls = new int[21][30];
    public final int IS_WALL_AROUND_MAP = 1;
    public final int IS_WALL_IN_MAP = 2;
    public final int Initial_OffsetX = 4;
    public final int Initial_OffsetY = 3;
    public final int Nearby_Cell = 1;

    public Bullet(int bulletX, int bulletY, String direction) {
        this.bulletX = bulletX;
        this.bulletY = bulletY;
        this.direction = direction;
        fillMap();
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
        int RADIUS_OF_BULLET = 10;
        g.setColor(color);
        g.fillOval(bulletX,bulletY, RADIUS_OF_BULLET, RADIUS_OF_BULLET);
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

    public boolean checkNearbyCellUP(){
        return false;
    }

    public boolean checkNearbyCellLEFT(){
        if((walls[bulletY/25-Initial_OffsetY][bulletX/25-Initial_OffsetX-Nearby_Cell] != IS_WALL_AROUND_MAP) && (walls[bulletY/25-Initial_OffsetY][bulletX/25-Initial_OffsetX-Nearby_Cell] != IS_WALL_IN_MAP)){
            return true;
        }
        return false;
    }

    public boolean checkNearbyCellDOWN(){
        if((walls[bulletY/25-Initial_OffsetY+Nearby_Cell][bulletX/25-Initial_OffsetX] != IS_WALL_AROUND_MAP) && (walls[bulletY/25-Initial_OffsetY+Nearby_Cell][bulletX/25-Initial_OffsetX] != IS_WALL_IN_MAP)){
            return true;
        }
        return false;
    }

    public boolean checkNearbyCellRIGHT(){
        if((walls[bulletY/25-Initial_OffsetY][bulletX/25-Initial_OffsetX+Nearby_Cell] != IS_WALL_AROUND_MAP) && (walls[bulletY/25-Initial_OffsetY][bulletX/25-Initial_OffsetX+Nearby_Cell] != IS_WALL_IN_MAP)){
            return true;
        }
        return false;
    }

    public void move() {
        int speed = 4;
        if(canMove){
            if(direction.equals("UP")) {
                if(checkNearbyCellUP()){
                    bulletY -=speed;
                }else{
                    canMove = false;
                }
            }
            if(direction.equals("DOWN")) {
                if(checkNearbyCellDOWN()){
                    bulletY +=speed;
                }else{
                    canMove = false;
                }
            }
            if(direction.equals("LEFT")) {
                if(checkNearbyCellLEFT()){
                    bulletX -=speed;
                }else{
                    canMove = false;
                }
            }
            if(direction.equals("RIGHT")) {
                if(checkNearbyCellRIGHT()){
                    bulletX +=speed;
                }else{
                    canMove = false;
                }
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
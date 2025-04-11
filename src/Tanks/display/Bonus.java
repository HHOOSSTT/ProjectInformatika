package Tanks.display;

import javax.swing.*;
import java.awt.*;

public class Bonus extends JComponent {

    public int bonusX;
    public int bonusY;
    public Image imagebonus;
    public int bonuscode;
    public boolean isVisible;

    public Bonus(int bonusX, int bonusY, Image imagebonus, int bonuscode){
        this.bonusX = bonusX;
        this.bonusY = bonusY;
        this.imagebonus = imagebonus;
        this.isVisible = true;
        this.bonuscode = bonuscode;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(isVisible){
            int SIZE_OF_SQUARE = 25;
            g.drawImage(imagebonus, bonusX, bonusY, SIZE_OF_SQUARE, SIZE_OF_SQUARE, null);
        }
    }

    public void setImage(Image imagebonus){
        this.imagebonus = imagebonus;
        this.isVisible = false;
    }

    public void respawn(int bonusX, int bonusY, Image imagebonus){
        if(!isVisible()){
            this.bonusX = bonusX;
            this.bonusY = bonusY;
            this.isVisible = true;
            this.imagebonus = imagebonus;
        }
    }

    public int getBonusX(){
        return bonusX;
    }

    public int getBonusY(){
        return bonusY;
    }

    public boolean isVisible(){
        return isVisible;
    }

    public int getBonuscode(){
        return bonuscode;
    }
}

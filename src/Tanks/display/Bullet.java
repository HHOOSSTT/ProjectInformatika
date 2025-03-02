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
    private boolean canMove = true;;

    public Bullet(int bulletX, int bulletY, String direction) {
        this.bulletX = bulletX;
        this.bulletY = bulletY;
        this.direction = direction;
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
        g.fillOval(bulletX,bulletY, 10, 10);
    }

    public void move(){
        int d = 4;
        if(canMove){
            if(direction.equals("UP")){
                bulletY -= d;
            }
            if(direction.equals("DOWN")) {
                bulletY += d;
            }
            if(direction.equals("LEFT")) {
                bulletX -= d;
            }
            if(direction.equals("RIGHT")) {
                bulletX += d;
            }
            if(direction.equals("UP") || direction.equals("DOWN")) {
                if((((bulletY<=96)||(bulletY>=541))&&((bulletX==783)||(bulletX==583)||(bulletX==333)||(bulletX==133)))||(((bulletX==158)||(bulletX==758))&&((bulletY>=442)||(bulletY<=172)))||(((bulletX==233)||(bulletX==283)||(bulletX==633)||(bulletX==683))&&((bulletY>=541)||((bulletY<=374)&&(bulletY>=299))||((bulletY<=274)&&(bulletY>=244))||(bulletY<=96)))||(((bulletX==183)||(bulletX==733))&&((bulletY>=541)||((bulletY<=374)&&(bulletY>=299))||(bulletY<=97)))||(((bulletX==358)||(bulletX==558))&&((bulletY>=442)||(bulletY<=171)))||(((bulletX==383)||(bulletX==533))&&(((bulletY>=317)&&(bulletY<=396))||(bulletY<=96)||(bulletY>=541)))||(((bulletX==433)||(bulletX==483))&&((bulletY<=96)||((bulletY<=374)&&(bulletY>=346))||((bulletY<=274)&&(bulletY>=242))||(bulletY>=541)))||((bulletX==458)&&((bulletY>=443)||((bulletY<=373)&&(bulletY>=343))||(bulletY<=273)))){
                    canMove=false;
                }
            }
            if(direction.equals("LEFT") || direction.equals("RIGHT")) {
                if((((bulletX<=122)||(bulletX>=792))&&((bulletY==533)||(bulletY==433)||(bulletY==408)||(bulletY==283)||(bulletY==233)||(bulletY==208)||(bulletY==108)))||((bulletY==383)&&(((bulletX<=548)&&(bulletX>=492))||((bulletX<=421)&&(bulletX>=367))||(bulletX<=122)))||((bulletY==183)&&((bulletX<=122)||((bulletX>=391)&&(bulletX<=521))||(bulletX>=792)))||((bulletY==308)&&((bulletX>=594)||(bulletX<=323)))||((bulletY==258)&&(((bulletX<=723)&&(bulletX>=592))||((bulletX<=523)&&(bulletX>=392))||((bulletX<=323)&&(bulletX>=192))||(bulletX>=792)||(bulletX<=122)))){
                    canMove=false;
                }
            }
        }
    }

    public boolean ishitTank(int bulletX, int bulletY, int tankX, int tankY){
        boolean ishitTank=false;
        if(((bulletX>=tankX) && (bulletX<=tankX+25)) && ((bulletY<=tankY+25) && (bulletY>=tankY))){
            ishitTank = true;
        }
        return ishitTank;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        move();
    }

    public boolean isCanMove(){
        return canMove;
    }
}

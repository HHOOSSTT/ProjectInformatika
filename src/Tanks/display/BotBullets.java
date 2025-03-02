package Tanks.display;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BotBullets {
    private final ArrayList<Bullet> bullets;
    private final TankBot tankBot;

    public BotBullets(TankBot tankBot) {
        this.bullets = new ArrayList<>();
        this.tankBot = tankBot;
    }

    public void shoot() {
        int bulletX = tankBot.getTankbotX();
        int bulletY = tankBot.getTankbotY();
        String direction = tankBot.getDirectionbot();
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
        bullets.add(new Bullet(bulletX, bulletY, direction));
    }

    public void update() {
        for (Bullet bullet : bullets) {
            bullet.move();
        }
        bullets.removeIf(bullet -> !bullet.isCanMove());
    }

    public void paintComponent(Graphics g) {
        for (Bullet bullet : bullets) {
            bullet.paintComponent(g);
        }
    }

    public List<Bullet> getBullets() {
        return bullets;
    }
}

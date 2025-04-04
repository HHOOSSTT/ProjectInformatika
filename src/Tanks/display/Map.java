package Tanks.display;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Map extends JPanel implements KeyListener {

    public Tank tank1 = new Tank(175, 525, "UP");
    public Tank tank2 = new Tank(775, 525, "UP");
    private final ArrayList<Bullet> bullets = new ArrayList<>();
    private final ArrayList<Bullet> botbullets = new ArrayList<>();
    public TankBot tankbot1 = new TankBot(775, 100, "LEFT", botbullets);
    public TankBot tankbot2 = new TankBot(125, 175, "DOWN", botbullets);
    public TankBot tankbot3 = new TankBot(350, 275, "RIGHT", botbullets);
    private boolean spacePressed = false;
    private boolean wPressed = false;
    private boolean aPressed = false;
    private boolean sPressed = false;
    private boolean dPressed = false;
    private boolean upPressed = false;
    private boolean leftPressed = false;
    private boolean downPressed = false;
    private boolean rightPressed = false;
    private boolean pPressed = false;
    private final ArrayList<TankBot> tankBots = new ArrayList<>(Arrays.asList(tankbot1, tankbot2, tankbot3));
    private int score = 0;
    private int lifes1 = 3;
    public int countbullets1 = 5;
    public int countbullets2 = 5;
    private int lifes2 = 3;
    private boolean isGame = true;
    private final Timer game;
    private final Timer bulletrecovery;
    Random random = new Random();
    public Bonus bonus1 = new Bonus(500, 400, ImageIO.read(new File("bonus.png")), random.nextInt(1, 4));
    public Bonus bonus2 = new Bonus(200, 200, ImageIO.read(new File("bonus.png")), random.nextInt(1, 4));
    private final ArrayList<Bonus> bonuses = new ArrayList<>(Arrays.asList(bonus1, bonus2));
    private Timer respawnbonus;

    public Map() throws IOException {
        setFocusable(true);
        addKeyListener(this);
        game = new Timer(16, e -> {
            try {
                updateGame();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            repaint();
        });
        game.start();
        bulletrecovery = new Timer(1500, e->{
            if(countbullets1 < 5){
                countbullets1++;
            }
            if(countbullets2 < 5){
                countbullets2++;
            }
            repaint();
        });
        bulletrecovery.start();
        for(Bonus bonus : bonuses){
            respawnbonus = new Timer(23000, e-> {
                try {
                    bonus.respawn(bonus.getBonusX(), bonus.getBonusY(), ImageIO.read(new File("bonus.png")));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                repaint();
            });
            respawnbonus.start();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        String s1 = "Score:" + score;
        g.drawString(s1, 100, 50);
        String s2 = "Player1 lifes:" + lifes1;
        g.drawString(s2, 320, 50);
        String s3 = "Player2 lifes:" + lifes2;
        g.drawString(s3, 610, 50);
        String s4 = "Player1 bullets:" + countbullets1;
        g.drawString(s4, 20, 620);
        String s5 = "Player2 bullets:" + countbullets2;
        g.drawString(s5, 670, 620);
        Image imageStenka = null;
        try {
            imageStenka = ImageIO.read(new File("stenka.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Image imageStenkavokrug = null;
        try {
            imageStenkavokrug = ImageIO.read(new File("stenkavokrug.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int x = 100;
        while (x <= 750) {
            g.drawImage(imageStenkavokrug, x, 75, 50, 25, null);
            x += 50;
        }
        g.drawImage(imageStenkavokrug, 800, 75, 25, 25, null);

        int y = 100;
        while (y <= 500) {
            g.drawImage(imageStenkavokrug, 100, y, 25, 50, null);
            y += 50;
        }

        int w = 100;
        while (w <= 750) {
            g.drawImage(imageStenkavokrug, w, 550, 50, 25, null);
            w += 50;
        }
        g.drawImage(imageStenkavokrug, 800, 550, 25, 25, null);

        int z = 100;
        while (z <= 500) {
            g.drawImage(imageStenkavokrug, 800, z, 25, 50, null);
            z += 50;
        }

        g.setColor(new Color(0xD3D3D3));
        g.fillRect(125, 100, 675, 450);
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(new Color(0x808080));
        for (int i = 125; i < 550; i += 25) {
            g.drawLine(125, i, 800, i);
            ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        }
        for (int j = 150; j < 800; j += 25) {
            g.drawLine(j, 100, j, 550);
            ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        }
        int a = 150;
        int i = 1;
        while (a <= 750) {
            if ((i == 6) || (i == 7) || (i == 8)) {
                g.drawImage(imageStenka, a, 125, 25, 75, null);
                a += 50;
            } else {
                g.drawImage(imageStenka, a, 125, 25, 50, null);
                a += 50;
            }
            i++;
        }
        int b = 200;
        while (b <= 750) {
            g.drawImage(imageStenka, b, 250, 125, 25, null);
            b += 200;
        }
        g.drawImage(imageStenka, 375, 325, 50, 75, null);
        g.drawImage(imageStenka, 425, 350, 75, 25, null);
        g.drawImage(imageStenka, 500, 325, 50, 75, null);
        int c = 150;
        while (c <= 750) {
            g.drawImage(imageStenka, c, 450, 25, 75, null);
            c += 50;
        }
        g.drawImage(imageStenka, 175, 300, 150, 75, null);
        g.drawImage(imageStenka, 600, 300, 150, 75, null);
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        tank1.paintComponent(g);
        tank2.paintComponent(g);
        for (Bullet bullet : bullets) {
            bullet.paintComponent(g);
        }
        for (Bullet bullet : botbullets) {
            bullet.paintComponent(g);
        }
        for (TankBot tankbot : tankBots){
            tankbot.paintComponent(g);
        }
        for(Bonus bonus : bonuses){
            bonus.paintComponent(g);
        }
    }

    public void stopGame(){
        isGame = false;
        tankbot1.stopBotTimer();
        tankbot2.stopBotTimer();
        tankbot3.stopBotTimer();
        game.stop();
        bulletrecovery.stop();
    }

    private void Victory() {
        SwingUtilities.invokeLater(() -> {
            JOptionPane.showMessageDialog(
                    this,
                    "Игроки победили!",
                    "Победа",
                    JOptionPane.INFORMATION_MESSAGE
            );
        });
    }

    private void Lose() {
        SwingUtilities.invokeLater(() -> {
            JOptionPane.showMessageDialog(
                    this,
                    "Игроки проиграли!",
                    "Поражение",
                    JOptionPane.INFORMATION_MESSAGE
            );
        });
    }

    private void updateGame() throws IOException {
        if((score == 10) && (isGame)){
            Victory();
            stopGame();
        }
        if((lifes1 <= 0) && (lifes2 <= 0)) {
            Lose();
            stopGame();
        }
        if(lifes1 == 0){
            tank1.remove();
        }
        if(lifes2 == 0){
            tank2.remove();
        }
        for (Bullet bullet : bullets) {
            bullet.move();
        }
        bullets.removeIf(b -> !b.isCanMove());
        botbullets.removeIf(b -> !b.isCanMove());
        for (Bullet bullet : botbullets) {
            bullet.move();
        }
        botbullets.removeIf(b -> !b.isCanMove());
        for(TankBot tankbot : tankBots){
            for(Bullet bullet : bullets){
                if ((bullet.ishitTank(bullet.getBulletX(), bullet.getBulletY(), tankbot.getTankbotX(), tankbot.getTankbotY())) && (tankbot.isAlive())){
                    score ++;
                    tankbot.setisAlive(false);
                    bullet.setcanMove(false);
                }
            }
        }
        for (TankBot tankbot : tankBots){
            if (!tankbot.isAlive()) continue;
            for (Bullet bullet : botbullets) {
                if ((bullet.ishitTank(bullet.getBulletX(), bullet.getBulletY(), tank1.getTankx(), tank1.getTanky())) && (!tank1.getInvisible())){
                    lifes1--;
                    botbullets.remove(bullet);
                    tank1.returnAfterHit1();
                    break;
                }
                if ((bullet.ishitTank(bullet.getBulletX(), bullet.getBulletY(), tank2.getTankx(), tank2.getTanky())) && (!tank2.getInvisible())){
                    lifes2--;
                    botbullets.remove(bullet);
                    tank2.returnAfterHit2();
                    break;
                }
            }
        }
        for(TankBot tankBot : tankBots){
            tankBot.rotate();
        }
        if(!tankbot1.isAlive()){
            tankbot1.returnAfterHit(775, 100, "LEFT");
        }
        if(!tankbot2.isAlive()){
            tankbot2.returnAfterHit(125, 200, "DOWN");
        }
        if(!tankbot3.isAlive()){
            tankbot3.returnAfterHit(350, 275, "RIGHT");
        }
        for(Bonus bonus : bonuses){
            if((tank1.getTankx() == bonus.getBonusX()) &&  (tank1.getTanky() == bonus.getBonusY()) && (bonus.isVisible())){
                if(bonus.getBonuscode() == 1){
                    lifes1++;
                }
                if(bonus.getBonuscode() == 2){
                    countbullets1+=5;
                }
                if(bonus.getBonuscode() == 3){
                    tank1.setInvisible(true);
                }
                bonus.setImage(null);
            }
            if((tank2.getTankx() == bonus.getBonusX()) &&  (tank2.getTanky() == bonus.getBonusY()) && (bonus.isVisible())){
                if(bonus.getBonuscode() == 1){
                    lifes2++;
                }
                if(bonus.getBonuscode() == 2){
                    countbullets2+=5;
                }
                if(bonus.getBonuscode() == 3){
                    tank2.setInvisible(true);
                }
                bonus.setImage(null);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyPressed(KeyEvent e) {
        int f = e.getKeyCode();
        if ((f == KeyEvent.VK_W) && (!wPressed) && (isGame) && (lifes1 !=0)) {
            tank1.moveUp(tank1.imagetankNOW.getGraphics());
            wPressed = true;
        }
        if ((f == KeyEvent.VK_UP) && (!upPressed) && (isGame) && (lifes2 != 0)) {
            tank2.moveUp(tank2.imagetankNOW.getGraphics());
            upPressed = true;
        }
        if ((f == KeyEvent.VK_A) && (!aPressed) && (isGame) && (lifes1 != 0)) {
            tank1.moveLEFT(tank1.imagetankNOW.getGraphics());
            aPressed = true;
        }
        if ((f == KeyEvent.VK_LEFT) && (!leftPressed) && (isGame) && (lifes2 != 0)) {
            tank2.moveLEFT(tank2.imagetankNOW.getGraphics());
            leftPressed = true;
        }
        if ((f == KeyEvent.VK_S) && (!sPressed) && (isGame) && (lifes1 != 0)) {
            tank1.moveDOWN(tank1.imagetankNOW.getGraphics());
            sPressed = true;
        }
        if ((f == KeyEvent.VK_DOWN) && (!downPressed) && (isGame) && (lifes2 != 0)) {
            tank2.moveDOWN(tank2.imagetankNOW.getGraphics());
            downPressed = true;
        }
        if ((f == KeyEvent.VK_D) && (!dPressed) && (isGame) && (lifes1 != 0)) {
            tank1.moveRIGHT(tank1.imagetankNOW.getGraphics());
            dPressed = true;
        }
        if ((f == KeyEvent.VK_RIGHT) && (!rightPressed) && (isGame) && (lifes2 != 0)) {
            tank2.moveRIGHT(tank2.imagetankNOW.getGraphics());
            rightPressed = true;
        }
        if ((f == KeyEvent.VK_SPACE) && (!spacePressed) && (isGame) && (lifes1 != 0)) {
            if ((tank1.getDirection().equals("UP")) && (countbullets1 > 0)) {
                Bullet bullet = new Bullet(tank1.getTankx() + 8, tank1.getTanky() - 5, "UP");
                bullets.add(bullet);
                countbullets1--;
            }
            if ((tank1.getDirection().equals("LEFT")) && (countbullets1 > 0)) {
                Bullet bullet = new Bullet(tank1.getTankx() - 5, tank1.getTanky() + 8, "LEFT");
                bullets.add(bullet);
                countbullets1--;
            }
            if ((tank1.getDirection().equals("DOWN")) && (countbullets1 > 0)) {
                Bullet bullet = new Bullet(tank1.getTankx() + 8, tank1.getTanky() + 20, "DOWN");
                bullets.add(bullet);
                countbullets1--;
            }
            if ((tank1.getDirection().equals("RIGHT")) && (countbullets1 > 0)) {
                Bullet bullet = new Bullet(tank1.getTankx() + 20, tank1.getTanky() + 8, "RIGHT");
                bullets.add(bullet);
                countbullets1--;
            }
            spacePressed = true;
        }
        if ((f == KeyEvent.VK_P) && (!pPressed) && (isGame) && (lifes2 != 0)) {
            if ((tank2.getDirection().equals("UP")) && (countbullets2 > 0)) {
                Bullet bullet = new Bullet(tank2.getTankx() + 8, tank2.getTanky() - 5, "UP");
                bullets.add(bullet);
                countbullets2--;
            }
            if ((tank2.getDirection().equals("LEFT")) && (countbullets2 > 0)) {
                Bullet bullet = new Bullet(tank2.getTankx() - 5, tank2.getTanky() + 8, "LEFT");
                bullets.add(bullet);
                countbullets2--;
            }
            if ((tank2.getDirection().equals("DOWN")) && (countbullets2 > 0)) {
                Bullet bullet = new Bullet(tank2.getTankx() + 8, tank2.getTanky() + 20, "DOWN");
                bullets.add(bullet);
                countbullets2--;
            }
            if ((tank2.getDirection().equals("RIGHT")) && (countbullets2 > 0)) {
                Bullet bullet = new Bullet(tank2.getTankx() + 20, tank2.getTanky() + 8, "RIGHT");
                bullets.add(bullet);
                countbullets2--;
            }
            pPressed = true;
        }
        repaint();
    }
    @Override
    public void keyReleased(KeyEvent e) {
        spacePressed = false;
        wPressed = false;
        aPressed = false;
        sPressed = false;
        dPressed = false;
        upPressed = false;
        leftPressed = false;
        downPressed = false;
        rightPressed = false;
        pPressed = false;
    }
}
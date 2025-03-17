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

public class Map extends JPanel implements KeyListener {

    public Tank tank1 = new Tank(175, 525, "UP");
    public Tank tank2 = new Tank(775, 525, "UP");
    public TankBot tankbot1 = new TankBot(775, 100, "LEFT");
    public TankBot tankbot2 = new TankBot(125, 200, "DOWN");
    public TankBot tankbot3 = new TankBot(350, 275, "RIGHT");
    private final ArrayList<Bullet> bullets = new ArrayList<>();
    private boolean spacePressed = false;
    private boolean wPressed = false;
    private boolean aPressed = false;
    private boolean sPressed = false;
    private boolean dPressed = false;
    private boolean upPressed = false;
    private boolean leftPressed = false;
    private boolean downPressed = false;
    private boolean rightPressed = false;
    private boolean shiftPressed = false;
    private final ArrayList<TankBot> tankBots = new ArrayList<>(Arrays.asList(tankbot1, tankbot2, tankbot3));
    private int score = 0;
    private int lifes1 = 3;
    private int lifes2 = 3;
    private boolean isGame = true;
    private final Timer game;

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
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        String s1 = "Score:" + score;
        g.drawString(s1, 100, 50);
        String s2 = "Lifes player1:" + lifes1;
        g.drawString(s2, 320, 50);
        String s3 = "Lifes player2:" + lifes2;
        g.drawString(s3, 610, 50);
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
        for (TankBot tankbot : tankBots){
            tankbot.paintComponent(g);
        }
    }

    public void stopGame(){
        isGame = false;
        tankbot1.stopBotTimer();
        tankbot2.stopBotTimer();
        tankbot3.stopBotTimer();
        game.stop();
    }

    private void updateGame() throws IOException {
        if((lifes1 <= 0) && (lifes2 <= 0)) {
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
        for (TankBot tankbot : tankBots){
            if (!tankbot.isAlive()) continue;
            tankbot.updateBullets();
            bullets.removeIf(bullet -> {
                if(tankbot.isHit(bullet.getBulletX(), bullet.getBulletY())) {
                    if(!tankbot.isAlive()) {
                        score ++;
                    }
                    return true;
                }
                return !bullet.isCanMove();
            });
        }
        for (TankBot tankbot : tankBots){
            if (!tankbot.isAlive()) continue;
            for (Bullet bullet : tankbot.getBotBullets().getBullets()) {
                if (bullet.ishitTank(bullet.getBulletX(), bullet.getBulletY(), tank1.getTankx(), tank1.getTanky())) {
                    lifes1--;
                    tankbot.getBotBullets().getBullets().remove(bullet);
                    tank1.returnAfterHit1();
                    break;
                }
                if (bullet.ishitTank(bullet.getBulletX(), bullet.getBulletY(), tank2.getTankx(), tank2.getTanky())) {
                    lifes2--;
                    tankbot.getBotBullets().getBullets().remove(bullet);
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
    }

    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyPressed(KeyEvent e) {
        int f = e.getKeyCode();
        if ((f == KeyEvent.VK_W) && (!wPressed) && (isGame) && (lifes1 !=0)) {
            if(!tank1.botisUP()){
                tank1.moveUp(tank1.imagetankNOW.getGraphics());
            }
            wPressed = true;
        }
        if ((f == KeyEvent.VK_UP) && (!upPressed) && (isGame) && (lifes2 != 0)) {
            if(!tank2.botisUP()){
                tank2.moveUp(tank2.imagetankNOW.getGraphics());
            }
            upPressed = true;
        }
        if ((f == KeyEvent.VK_A) && (!aPressed) && (isGame) && (lifes1 != 0)) {
            if(!tank1.botisLEFT()){
                tank1.moveLEFT(tank1.imagetankNOW.getGraphics());
            }
            aPressed = true;
        }
        if ((f == KeyEvent.VK_LEFT) && (!leftPressed) && (isGame) && (lifes2 != 0)) {
            if(!tank2.botisLEFT()){
                tank2.moveLEFT(tank2.imagetankNOW.getGraphics());
            }
            leftPressed = true;
        }
        if ((f == KeyEvent.VK_S) && (!sPressed) && (isGame) && (lifes1 != 0)) {
            if(!tank1.botisDOWN()){
                tank1.moveDOWN(tank1.imagetankNOW.getGraphics());
            }
            sPressed = true;
        }
        if ((f == KeyEvent.VK_DOWN) && (!downPressed) && (isGame) && (lifes2 != 0)) {
            if(!tank2.botisDOWN()){
                tank2.moveDOWN(tank2.imagetankNOW.getGraphics());
            }
            downPressed = true;
        }
        if ((f == KeyEvent.VK_D) && (!dPressed) && (isGame) && (lifes1 != 0)) {
            if(!tank1.botisRIGHT()){
                tank1.moveRIGHT(tank1.imagetankNOW.getGraphics());
            }
            dPressed = true;
        }
        if ((f == KeyEvent.VK_RIGHT) && (!rightPressed) && (isGame) && (lifes2 != 0)) {
            if(!tank2.botisRIGHT()){
                tank2.moveRIGHT(tank2.imagetankNOW.getGraphics());
            }
            rightPressed = true;
        }
        if ((f == KeyEvent.VK_SPACE) && (!spacePressed) && (isGame) && (lifes1 != 0)) {
            if (tank1.getDirection().equals("UP")) {
                Bullet bullet = new Bullet(tank1.getTankx() + 8, tank1.getTanky() - 5, "UP");
                bullets.add(bullet);
            }
            if (tank1.getDirection().equals("LEFT")) {
                Bullet bullet = new Bullet(tank1.getTankx() - 5, tank1.getTanky() + 8, "LEFT");
                bullets.add(bullet);
            }
            if (tank1.getDirection().equals("DOWN")) {
                Bullet bullet = new Bullet(tank1.getTankx() + 8, tank1.getTanky() + 20, "DOWN");
                bullets.add(bullet);
            }
            if (tank1.getDirection().equals("RIGHT")) {
                Bullet bullet = new Bullet(tank1.getTankx() + 20, tank1.getTanky() + 8, "RIGHT");
                bullets.add(bullet);
            }
            spacePressed = true;
        }
        if ((f == KeyEvent.VK_SHIFT) && (!shiftPressed) && (isGame) && (lifes2 != 0)) {
            if (tank2.getDirection().equals("UP")) {
                Bullet bullet = new Bullet(tank2.getTankx() + 8, tank2.getTanky() - 5, "UP");
                bullets.add(bullet);
            }
            if (tank2.getDirection().equals("LEFT")) {
                Bullet bullet = new Bullet(tank2.getTankx() - 5, tank2.getTanky() + 8, "LEFT");
                bullets.add(bullet);
            }
            if (tank2.getDirection().equals("DOWN")) {
                Bullet bullet = new Bullet(tank2.getTankx() + 8, tank2.getTanky() + 20, "DOWN");
                bullets.add(bullet);
            }
            if (tank2.getDirection().equals("RIGHT")) {
                Bullet bullet = new Bullet(tank2.getTankx() + 20, tank2.getTanky() + 8, "RIGHT");
                bullets.add(bullet);
            }
            shiftPressed = true;
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
        shiftPressed = false;
    }
}
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

    public Tank tank = new Tank(775, 525, "UP");
    public TankBot tankbot1 = new TankBot(775, 100, "LEFT");
    public TankBot tankbot2 = new TankBot(125, 200, "DOWN");
    public TankBot tankbot3 = new TankBot(350, 275, "RIGHT");
    private final ArrayList<Bullet> bullets = new ArrayList<>();
    private boolean spacePressed = false;
    private boolean wPressed = false;
    private boolean aPressed = false;
    private boolean sPressed = false;
    private boolean dPressed = false;
    private final ArrayList<TankBot> tankBots = new ArrayList<>(Arrays.asList(tankbot1, tankbot2, tankbot3));
    private int score = 0;
    private int lifes = 3;
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
        g.setFont(new Font("Arial", Font.BOLD, 40));
        String s1 = "Score " + score;
        g.drawString(s1, 50, 50);
        String s2 = "Lifes " + lifes;
        g.drawString(s2, 750, 50);
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
        tank.paintComponent(g);
        for (Bullet bullet : bullets) {
            bullet.paintComponent(g);
        }
        for (TankBot tankbot : tankBots){
            if(tankbot.isAlive()){
                tankbot.paintComponent(g);
            }
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
        if(lifes == 0){
            stopGame();
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
                if (bullet.ishitTank(bullet.getBulletX(), bullet.getBulletY(), tank.getTankx(), tank.getTanky())) {
                    lifes--;
                    tankbot.getBotBullets().getBullets().remove(bullet);
                    tank.returnAfterHit();
                    break;
                }
            }
        }
        tankBots.removeIf(tankBot -> !tankBot.isAlive());
        for(TankBot tankBot : tankBots){
            tankBot.rotate();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyPressed(KeyEvent e) {
        int f = e.getKeyCode();
        if (((f == KeyEvent.VK_UP) || (f == KeyEvent.VK_W)) && (!wPressed) &&(isGame)) {
            if(!tank.botisUP()){
                tank.moveUp(tank.imagetankNOW.getGraphics());
            }
            wPressed = true;
        }
        if (((f == KeyEvent.VK_LEFT) || (f == KeyEvent.VK_A)) && (!aPressed) &&(isGame)) {
            if(!tank.botisLEFT()){
                tank.moveLEFT(tank.imagetankNOW.getGraphics());
            }
            aPressed = true;
        }
        if (((f == KeyEvent.VK_DOWN) || (f == KeyEvent.VK_S)) && (!sPressed) &&(isGame)) {
            if(!tank.botisDOWN()){
                tank.moveDOWN(tank.imagetankNOW.getGraphics());
            }
            sPressed = true;
        }
        if (((f == KeyEvent.VK_RIGHT) || (f == KeyEvent.VK_D)) && (!dPressed) &&(isGame)) {
            if(!tank.botisRIGHT()){
                tank.moveRIGHT(tank.imagetankNOW.getGraphics());
            }
            dPressed = true;
        }
        if ((f == KeyEvent.VK_SPACE)&&(!spacePressed) &&(isGame)) {
            if (tank.getDirection().equals("UP")) {
                Bullet bullet = new Bullet(tank.getTankx() + 8, tank.getTanky() - 5, "UP");
                bullets.add(bullet);
            }
            if (tank.getDirection().equals("LEFT")) {
                Bullet bullet = new Bullet(tank.getTankx() - 5, tank.getTanky() + 8, "LEFT");
                bullets.add(bullet);
            }
            if (tank.getDirection().equals("DOWN")) {
                Bullet bullet = new Bullet(tank.getTankx() + 8, tank.getTanky() + 20, "DOWN");
                bullets.add(bullet);
            }
            if (tank.getDirection().equals("RIGHT")) {
                Bullet bullet = new Bullet(tank.getTankx() + 20, tank.getTanky() + 8, "RIGHT");
                bullets.add(bullet);
            }
            spacePressed = true;
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
    }
}
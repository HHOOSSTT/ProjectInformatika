package Tanks.display;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

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
    private Timer game;
    private Timer respawnbonus;
    private Timer bulletrecovery;
    Random random = new Random();
    public Bonus bonus1 = new Bonus(500, 400, ImageIO.read(new File("bonus.png")), random.nextInt(1, 3));
    public Bonus bonus2 = new Bonus(200, 200, ImageIO.read(new File("bonus.png")), random.nextInt(1, 3));
    private final ArrayList<Bonus> bonuses = new ArrayList<>(Arrays.asList(bonus1, bonus2));
    public final int FULL_COUNT_OF_PLAYERS_BULLETS = 5;
    public BufferedImage staticBackground = null;
    File mapFile = new File("Map.txt");
    public final int IS_WALL_AROUND_MAP = 1;
    public final int IS_WALL_IN_MAP = 2;
    public final int INITIAL_OFFSETX = 75;
    public final int INITIAL_OFFSETY = 50;
    public final int SIZE_OF_CELL = 25;
    private static final int SIZE_OF_TANK = 25;

    public Map() throws IOException {
        setFocusable(true);
        addKeyListener(this);
        updatingGame();
        bulletRecovery();
        respawnBonus();
        createStaticBackground();
    }

    private void updatingGame() {
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

    private void respawnBonus() {
        int TIME_RESPAWN_OF_BONUS = 23000;
        for (Bonus bonus : bonuses) {
            respawnbonus = new Timer(TIME_RESPAWN_OF_BONUS, e -> {
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

    private void bulletRecovery() {
        int TIME_BULLET_RECOVERY = 1500;
        bulletrecovery = new Timer(TIME_BULLET_RECOVERY, e -> {
            if (countbullets1 < FULL_COUNT_OF_PLAYERS_BULLETS) {
                countbullets1++;
            }
            if (countbullets2 < FULL_COUNT_OF_PLAYERS_BULLETS) {
                countbullets2++;
            }
            repaint();
        });
        bulletrecovery.start();
    }

    public void paintWalls(Graphics g) throws IOException {
        Scanner scanner = new Scanner(mapFile);
        int y=0;
        while (scanner.hasNext()) {
            String str = scanner.nextLine();
            for (int i = 0; i < str.length(); ++i) {
                int a = str.charAt(i) - '0';
                if(a == IS_WALL_AROUND_MAP){
                    g.drawImage(ImageIO.read(new File("stenkavokrug.png")), (i+1)*SIZE_OF_CELL+INITIAL_OFFSETX, (y+1)*SIZE_OF_CELL+INITIAL_OFFSETY,
                            SIZE_OF_CELL, SIZE_OF_CELL, null);
                }
                if(a == IS_WALL_IN_MAP){
                    g.drawImage(ImageIO.read(new File("stenka.png")), (i+1)*SIZE_OF_CELL+INITIAL_OFFSETX, (y+1)*SIZE_OF_CELL+INITIAL_OFFSETY,
                            SIZE_OF_CELL, SIZE_OF_CELL, null);
                }
            }
            y++;
        }
    }

    private void createStaticBackground() throws IOException {
        staticBackground = new BufferedImage(950, 700, BufferedImage.TYPE_INT_ARGB);
        paintLinesAndBackground(staticBackground.createGraphics());
        paintWalls(staticBackground.createGraphics());
        staticBackground.createGraphics().dispose();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(staticBackground, 0, 0, null);
        paintCounters(g);
        tank1.paintComponent(g);
        tank2.paintComponent(g);
        for (Bullet bullet : bullets) {
            bullet.paintComponent(g);
        }
        for (Bullet bullet : botbullets) {
            bullet.paintComponent(g);
        }
        for (TankBot tankbot : tankBots) {
            tankbot.paintComponent(g);
        }
        for (Bonus bonus : bonuses) {
            bonus.paintComponent(g);
        }
    }

    private void paintCounters(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        String playersscore = "Score:" + score;
        g.drawString(playersscore, 100, 50);
        String player1lifes = "Player1 lifes:" + lifes1;
        g.drawString(player1lifes, 320, 50);
        String player2lifes = "Player2 lifes:" + lifes2;
        g.drawString(player2lifes, 610, 50);
        String players1bullets = "Player1 bullets:" + countbullets1;
        g.drawString(players1bullets, 20, 620);
        String player2bullets = "Player2 bullets:" + countbullets2;
        g.drawString(player2bullets, 670, 620);
    }


    private void paintLinesAndBackground(Graphics g) {
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
    }

    public void stopGame() {
        isGame = false;
        tankbot1.stopBotTimer();
        tankbot2.stopBotTimer();
        tankbot3.stopBotTimer();
        game.stop();
        bulletrecovery.stop();
        respawnbonus.stop();
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
        moveBullets();
        playersVictory();
        playersLose();
        deathTank();
        bulletsRemove();
        playersHitTankbot();
        hitPlayersTank();
        tankbotRotate();
        tankbotReturnAfterHit();
        playersGetBonus();
    }

    private void playersVictory() {
        if ((score == 10) && (isGame)) {
            Victory();
            stopGame();
        }
    }

    private void playersLose() {
        if ((lifes1 <= 0) && (lifes2 <= 0)) {
            Lose();
            stopGame();
        }
    }

    private void tankbotReturnAfterHit() throws IOException {
        if (!tankbot1.isAlive()) {
            tankbot1.returnAfterHit(775, 100, "LEFT");
        }
        if (!tankbot2.isAlive()) {
            tankbot2.returnAfterHit(125, 200, "DOWN");
        }
        if (!tankbot3.isAlive()) {
            tankbot3.returnAfterHit(350, 275, "RIGHT");
        }
    }

    private void tankbotRotate() throws IOException {
        for (TankBot tankBot : tankBots) {
            tankBot.rotate();
        }
    }

    private void deathTank() {
        if (lifes1 == 0) {
            tank1.remove();
        }
        if (lifes2 == 0) {
            tank2.remove();
        }
    }

    private void hitPlayersTank() {
        for (TankBot tankbot : tankBots) {
            if (!tankbot.isAlive()) continue;
            for (Bullet bullet : botbullets) {
                if ((bullet.ishitTank(bullet.getBulletX(), bullet.getBulletY(), tank1.getTankx(), tank1.getTanky()))) {
                    lifes1--;
                    botbullets.remove(bullet);
                    tank1.returnAfterHitTank1();
                    break;
                }
                if ((bullet.ishitTank(bullet.getBulletX(), bullet.getBulletY(), tank2.getTankx(), tank2.getTanky()))) {
                    lifes2--;
                    botbullets.remove(bullet);
                    tank2.returnAfterHitTank2();
                    break;
                }
            }
        }
    }

    private void playersGetBonus() {
        for (Bonus bonus : bonuses) {
            int BONUS_OF_LIFES = 1;
            int BONUS_OF_BULLETS = 2;
            if ((tank1.getTankx() == bonus.getBonusX()) && (tank1.getTanky() == bonus.getBonusY()) && (bonus.isVisible())) {
                if (bonus.getBonuscode() == BONUS_OF_LIFES) {
                    lifes1++;
                }
                if (bonus.getBonuscode() == BONUS_OF_BULLETS) {
                    countbullets1 += FULL_COUNT_OF_PLAYERS_BULLETS;
                }
                bonus.setImage(null);
            }
            if ((tank2.getTankx() == bonus.getBonusX()) && (tank2.getTanky() == bonus.getBonusY()) && (bonus.isVisible())) {
                if (bonus.getBonuscode() == BONUS_OF_LIFES) {
                    lifes2++;
                }
                if (bonus.getBonuscode() == BONUS_OF_BULLETS) {
                    countbullets2 += FULL_COUNT_OF_PLAYERS_BULLETS;
                }
                bonus.setImage(null);
            }
        }
    }

    private void playersHitTankbot() {
        for (TankBot tankbot : tankBots) {
            for (Bullet bullet : bullets) {
                if ((bullet.ishitTank(bullet.getBulletX(), bullet.getBulletY(), tankbot.getTankbotX(), tankbot.getTankbotY())) && (tankbot.isAlive())) {
                    score++;
                    tankbot.setisAlive(false);
                    bullet.setcanMove(false);
                }
            }
        }
    }

    private void moveBullets() {
        for (Bullet bullet : botbullets) {
            bullet.move();
        }
        for (Bullet bullet : bullets) {
            bullet.move();
        }
    }

    private void bulletsRemove() {
        bullets.removeIf(b -> !b.isCanMove());
        botbullets.removeIf(b -> !b.isCanMove());
    }

    public boolean botisUP(Tank tank){
        for(TankBot tankBot : tankBots){
            if ((tank.getTankx() == tankBot.getTankbotX()) && (tank.getTanky() == tankBot.getTankbotY() + SIZE_OF_TANK) && (tankBot.isAlive())) {
                return true;
            }
        }
        return false;
    }

    public boolean botisLEFT(Tank tank){;
        for(TankBot tankBot : tankBots){
            if ((tank.getTankx() == tankBot.getTankbotX() + SIZE_OF_TANK) && (tank.getTanky() == tankBot.getTankbotY()) && (tankBot.isAlive())) {
                return true;
            }
        }
        return false;
    }

    public boolean botisDOWN(Tank tank){
        for(TankBot tankBot : tankBots){
            if ((tank.getTankx() == tankBot.getTankbotX()) && (tank.getTanky() == tankBot.getTankbotY() - SIZE_OF_TANK) && (tankBot.isAlive())) {
                return true;
            }
        }
        return false;
    }

    public boolean botisRIGHT(Tank tank){
        for(TankBot tankBot : tankBots){
            if ((tank.getTankx() == tankBot.getTankbotX() - SIZE_OF_TANK) && (tank.getTanky() == tankBot.getTankbotY()) && (tankBot.isAlive())) {
                return true;
            }
        }
        return false;
    }

    private void moveTankPlayer1(KeyEvent e) {
        int f = e.getKeyCode();
        if ((f == KeyEvent.VK_W) && (!wPressed) && (isGame) && (lifes1 != 0) &&(!botisUP(tank1))) {
            tank1.moveUp(tank1.imagetankNOW.getGraphics());
            wPressed = true;
        }
        if ((f == KeyEvent.VK_A) && (!aPressed) && (isGame) && (lifes1 != 0) && (!botisLEFT(tank1))) {
            tank1.moveLEFT(tank1.imagetankNOW.getGraphics());
            aPressed = true;
        }
        if ((f == KeyEvent.VK_S) && (!sPressed) && (isGame) && (lifes1 != 0) && (!botisDOWN(tank1))) {
            tank1.moveDOWN(tank1.imagetankNOW.getGraphics());
            sPressed = true;
        }
        if ((f == KeyEvent.VK_D) && (!dPressed) && (isGame) && (lifes1 != 0) && (!botisRIGHT(tank1))) {
            tank1.moveRIGHT(tank1.imagetankNOW.getGraphics());
            dPressed = true;
        }
    }

    private void moveTankPlayer2(KeyEvent e) {
        int f = e.getKeyCode();
        if ((f == KeyEvent.VK_UP) && (!upPressed) && (isGame) && (lifes2 != 0) && (!botisUP(tank2))) {
            tank2.moveUp(tank2.imagetankNOW.getGraphics());
            upPressed = true;
        }
        if ((f == KeyEvent.VK_LEFT) && (!leftPressed) && (isGame) && (lifes2 != 0) && (!botisLEFT(tank2))) {
            tank2.moveLEFT(tank2.imagetankNOW.getGraphics());
            leftPressed = true;
        }
        if ((f == KeyEvent.VK_DOWN) && (!downPressed) && (isGame) && (lifes2 != 0) && (!botisDOWN(tank2))) {
            tank2.moveDOWN(tank2.imagetankNOW.getGraphics());
            downPressed = true;
        }
        if ((f == KeyEvent.VK_RIGHT) && (!rightPressed) && (isGame) && (lifes2 != 0) && (!botisRIGHT(tank2))) {
            tank2.moveRIGHT(tank2.imagetankNOW.getGraphics());
            rightPressed = true;
        }
    }

    private void createBulletPlayer1(KeyEvent e) throws FileNotFoundException {
        int f = e.getKeyCode();
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
    }

    private void createBulletPlayer2(KeyEvent e) throws FileNotFoundException {
        int f = e.getKeyCode();
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
    }


    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        moveTankPlayer1(e);
        moveTankPlayer2(e);
        try {
            createBulletPlayer1(e);
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }
        try {
            createBulletPlayer2(e);
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
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
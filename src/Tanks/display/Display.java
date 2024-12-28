package Tanks.display;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
public abstract class Display {

    private static Image imageTankUP;
    private static Image imageTankDOWN;
    private static Image imageTankLEFT;
    private static Image imageTankRIGHT;
    public static Image imageTankNOW;
    private static Image imageStenka;

    private static boolean created = false;
    private static JFrame window;
    private static Canvas content;
    private static BufferedImage buffer;
    private static int[] bufferData;
    private static Graphics bufferGraphics;
    private static int clearColor;
    private static BufferStrategy bufferStrategy;

    public static void create(int length, int width, String title, int _clearColor, int numBuffers) throws IOException {
        imageTankUP = ImageIO.read(new File("C:\\Users\\User\\Documents\\ProjectInformatika\\tankUP.png"));
        imageTankDOWN = ImageIO.read(new File("C:\\Users\\User\\Documents\\ProjectInformatika\\tankDOWN.png"));
        imageTankLEFT = ImageIO.read(new File("C:\\Users\\User\\Documents\\ProjectInformatika\\tankLEFT.png"));
        imageTankRIGHT = ImageIO.read(new File("C:\\Users\\User\\Documents\\ProjectInformatika\\tankRIGHT.png"));
        imageTankNOW = imageTankUP;
        imageStenka = ImageIO.read(new File("C:\\Users\\User\\Documents\\ProjectInformatika\\stenka.png"));

        if (created) {
            return;
        }
        window = new JFrame(title);
        content = new Canvas();
        Dimension size = new Dimension(length, width);
        content.setPreferredSize(size);
        window.setResizable(false);
        window.getContentPane().add(content);
        window.pack();
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        MyKeyListener myKeyListener = new MyKeyListener();
        window.addKeyListener(myKeyListener);
        buffer = new BufferedImage(length, width, BufferedImage.TYPE_INT_ARGB);
        bufferData = ((DataBufferInt) buffer.getRaster().getDataBuffer()).getData();
        bufferGraphics = buffer.getGraphics();
        clearColor = _clearColor;
        content.createBufferStrategy(numBuffers);
        bufferStrategy = content.getBufferStrategy();
        created = true;
    }

    public static void clear() {
        Arrays.fill(bufferData, clearColor);
    }

    public static void paintComponent3() {
        bufferGraphics.setColor(new Color(0x808080));
        bufferGraphics.fillRect(100, 75, 725, 25);
        bufferGraphics.fillRect(100, 100, 25, 450);
        bufferGraphics.fillRect(100, 550, 725, 25);
        bufferGraphics.fillRect(800, 100, 25, 450);
        ((Graphics2D) bufferGraphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    public static void paintComponent1() {
        bufferGraphics.setColor(new Color(0xD3D3D3));
        bufferGraphics.fillRect(125, 100, 675, 450);
        ((Graphics2D) bufferGraphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    public static void paintComponent4() {
        bufferGraphics.setColor(new Color(0x808080));
        for (int i = 125; i < 550; i += 25) {
            bufferGraphics.drawLine(125, i, 800, i);
            ((Graphics2D) bufferGraphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        }
        for (int j = 150; j < 800; j += 25) {
            bufferGraphics.drawLine(j, 100, j, 550);
            ((Graphics2D) bufferGraphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        }
        ((Graphics2D) bufferGraphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    public static void paintComponent2() {
        bufferGraphics.setColor(new Color(0x654321));
        int a = 150;
        int i = 1;
        while (a <= 750) {
            if ((i == 6) || (i == 7) || (i == 8)) {
                bufferGraphics.drawImage(imageStenka, a, 125, 25, 75,  null);
                a += 50;
            } else {
                bufferGraphics.drawImage(imageStenka, a, 125, 25, 50,  null);
                a += 50;
            }
            i++;
        }
        int b = 200;
        while (b <= 750) {
            bufferGraphics.drawImage(imageStenka, b, 250, 125, 25,  null);
            b += 200;
        }
        bufferGraphics.drawImage(imageStenka, 375, 325, 50, 75,  null);
        bufferGraphics.drawImage(imageStenka, 425, 350, 75, 25,  null);
        bufferGraphics.drawImage(imageStenka, 500, 325, 50, 75,  null);
        int c = 150;
        while (c <= 750) {
            bufferGraphics.drawImage(imageStenka, c, 450, 25, 75,  null);
            c += 50;
        }
        bufferGraphics.drawImage(imageStenka, 175, 300, 150, 75,  null);
        bufferGraphics.drawImage(imageStenka, 600, 300, 150, 75,  null);
        ((Graphics2D) bufferGraphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    public static int tankx = 775;
    public static int tanky = 525;
    public static void paintComponent5() {
        bufferGraphics.drawImage(imageTankNOW, tankx, tanky, 25, 25,  null);
        ((Graphics2D) bufferGraphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    private static int lifes = 3;

    public static boolean game = true;
    static class MyKeyListener implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {

        }
        @Override
        public void keyPressed(KeyEvent e) {
            int f = e.getKeyCode();
            if((f == KeyEvent.VK_W) && (game)) {
                if(((((tankx==775)||(tankx==125)||(tankx==325)||(tankx==575))&&((tanky>=125)&&(tanky<=525))||(((tankx==150)||(tankx==350)||(tankx==550)||(tankx==750))&&((tanky>175)&&(tanky<=425)))||(((tankx==175)||(tankx==725))&&(((tanky<=525)&&(tanky>=400))||((tanky>=125)&&(tanky<=275))))||(((tankx==200)||(tankx==250)||(tankx==300)||(tankx==600)||(tankx==650)||(tankx==700))&&(((tanky>=200)&&(tanky<=225))||((tanky<=475)&&(tanky>=400))))||(((tankx==225)||(tankx==275)||(tankx==625)||(tankx==675))&&(((tanky>=125)&&(tanky<=225))||((tanky<=525)&&(tanky>=400))))||(((tankx==375)||(tankx==525))&&(((tanky<=525)&&(tanky>=425))||((tanky>=125)&&(tanky<=300))))||(((tankx==400)||(tankx==500))&&((tanky==225)||(tanky==300)||(tanky==425)))||(((tankx==425)||(tankx==475))&&((tanky!=250)&&(tanky!=275)&&(tanky!=350)&&(tanky!=375)&&(tanky>=125)))||((tankx==450)&&((tanky==425)||(tanky==400)||(tanky==325)||(tanky==300)||(tanky==225)))))) {
                    tanky -= 25;
                    imageTankNOW = imageTankUP;
                    bufferGraphics.drawImage(imageTankNOW, tankx, tanky, 25, 25,  null);
                    ((Graphics2D) bufferGraphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                }
            }
            if((f == KeyEvent.VK_A) && (game)) {
                if(((tanky==100)&&((tankx>=150)&&(tankx<=775)))||(((tanky==175)&&(((tankx>=150)&&(tankx<=375))||(((tankx>=550)&&(tankx<=775)))))||(((tanky==200)||(tanky==225)||(tanky==275)||(tanky==400)||(tanky==425)||(tanky==525))&&((tankx>=150)&&(tankx<=775))))||((tanky==250)&&((tankx==150)||(tankx==175)||(tankx==350)||(tankx==375)||(tankx==550)||(tankx==575)||(tankx==750)||(tankx==775)))||((tanky==375)&&(((tankx>=150)&&(tankx<=350))||((tankx>=575)&&(tankx<=775))||(tankx==450)||(tankx==475)))||((tanky==300)&&((tankx==150)||((tankx>=350)&&(tankx<=575))||(tankx==775)))||((tanky==325)&&((tankx==150)||(tankx==350)||(tankx==450)||(tankx==475)||(tankx==575)||(tankx==775)))||((tanky==350)&&((tankx==150)||(tankx==350)||(tankx==575)||(tankx==775)))) {
                    tankx -= 25;
                    imageTankNOW = imageTankLEFT;
                    bufferGraphics.drawImage(imageTankNOW, tankx, tanky, 25, 25,  null);
                    ((Graphics2D) bufferGraphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                }
            }
            if((f == KeyEvent.VK_S) && (game)) {
                if((((tanky==100)||(tanky==125)||(tanky==150)||(tanky==425)||(tanky==450)||(tanky==475)||(tanky==500))&&((tankx==125)||(tankx==175)||(tankx==225)||(tankx==275)||(tankx==325)||(tankx==375)||(tankx==425)||(tankx==475)||(tankx==525)||(tankx==575)||(tankx==625)||(tankx==675)||(tankx==725)||(tankx==775)))||((tanky==175)&&((tankx!=400)&&(tankx!=450)&&(tankx!=500)))||((tanky==200)||(tanky==400))||(((tanky==225)||(tanky==250))&&((tankx==125)||(tankx==150)||(tankx==175)||(tankx==325)||(tankx==350)||(tankx==375)||(tankx==525)||(tankx==550)||(tankx==575)||(tankx==725)||(tankx==750)||(tankx==775)))||((tanky==275)&&((tankx!=175)&&(tankx!=200)&&(tankx!=225)&&(tankx!=250)&&(tankx!=275)&&(tankx!=300)&&(tankx!=600)&&(tankx!=625)&&(tankx!=650)&&(tankx!=675)&&(tankx!=700)&&(tankx!=725)))||(((tanky==300)&&((tankx==125)||(tankx==150)||(tankx==325)||(tankx==350)||(tankx==425)||(tankx==450)||(tankx==475)||(tankx==550)||(tankx==575)||(tankx==750)||(tankx==775))))||(((tanky==325)||(tanky==350))&&((tankx==125)||(tankx==150)||(tankx==325)||(tankx==350)||(tankx==550)||(tankx==575)||(tankx==750)||(tankx==775)))||((tanky==375)&&((tankx!=375)&&(tankx!=400)&&(tankx!=500)&&(tankx!=525)))) {
                    tanky += 25;
                    imageTankNOW = imageTankDOWN;
                    bufferGraphics.drawImage(imageTankNOW, tankx, tanky, 25, 25,  null);
                    ((Graphics2D) bufferGraphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                }
            }
            if((f == KeyEvent.VK_D) && (game)) {
                if(((tanky==100)&&((tankx>=125)&&(tankx<=750)))||(((tanky==175)&&(((tankx>=125)&&(tankx<=350))||(((tankx>=525)&&(tankx<=750)))))||(((tanky==200)||(tanky==225)||(tanky==275)||(tanky==400)||(tanky==425)||(tanky==525))&&((tankx>=125)&&(tankx<=750))))||((tanky==250)&&((tankx==125)||(tankx==150)||(tankx==325)||(tankx==350)||(tankx==525)||(tankx==550)||(tankx==725)||(tankx==750)))||((tanky==375)&&(((tankx>=125)&&(tankx<=300))||((tankx>=550)&&(tankx<=750))||(tankx==425)||(tankx==450)))||((tanky==300)&&((tankx==125)||((tankx>=325)&&(tankx<=550))||(tankx==750)))||((tanky==325)&&((tankx==125)||(tankx==325)||(tankx==425)||(tankx==450)||(tankx==550)||(tankx==750)))||((tanky==350)&&((tankx==125)||(tankx==325)||(tankx==550)||(tankx==750)))) {
                    tankx += 25;
                    imageTankNOW = imageTankRIGHT;
                    bufferGraphics.drawImage(imageTankNOW, tankx, tanky, 25, 25,  null);
                    ((Graphics2D) bufferGraphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }

    public static void swapBuffers() {
        Graphics g = bufferStrategy.getDrawGraphics();
        g.drawImage(buffer, 0, 0, null);
        bufferStrategy.show();
    }
}

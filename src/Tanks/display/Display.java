package Tanks.display;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Arrays;
public abstract class Display implements ActionListener, KeyListener {
    private static boolean created = false;
    private static JFrame window;
    private static Canvas content;
    private static BufferedImage buffer;
    private static int[] bufferData;
    private static Graphics bufferGraphics;
    private static int clearColor;
    private static BufferStrategy bufferStrategy;
    public static void create(int length, int width, String title, int _clearColor, int numBuffers) {
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
            if ((i == 8) || (i == 6) || (i == 7)) {
                bufferGraphics.fillRect(a, 125, 25, 25);
                bufferGraphics.fillRect(a, 150, 25, 25);
                bufferGraphics.fillRect(a, 175, 25, 25);
                a += 50;
            } else {
                bufferGraphics.fillRect(a, 125, 25, 25);
                bufferGraphics.fillRect(a, 150, 25, 25);
                a += 50;
            }
            i++;
        }
        int b = 200;
        while (b <= 750) {
            bufferGraphics.fillRect(b, 250, 25, 25);
            bufferGraphics.fillRect(b + 25, 250, 25, 25);
            bufferGraphics.fillRect(b + 50, 250, 25, 25);
            bufferGraphics.fillRect(b + 75, 250, 25, 25);
            bufferGraphics.fillRect(b + 100, 250, 25, 25);
            b += 200;
        }
        bufferGraphics.fillRect(400, 325, 25, 25);
        bufferGraphics.fillRect(375, 325, 25, 25);
        bufferGraphics.fillRect(400, 350, 25, 25);
        bufferGraphics.fillRect(375, 350, 25, 25);
        bufferGraphics.fillRect(400, 375, 25, 25);
        bufferGraphics.fillRect(375, 375, 25, 25);
        bufferGraphics.fillRect(425, 350, 25, 25);
        bufferGraphics.fillRect(450, 350, 25, 25);
        bufferGraphics.fillRect(475, 350, 25, 25);
        bufferGraphics.fillRect(525, 325, 25, 25);
        bufferGraphics.fillRect(500, 325, 25, 25);
        bufferGraphics.fillRect(500, 350, 25, 25);
        bufferGraphics.fillRect(525, 350, 25, 25);
        bufferGraphics.fillRect(500, 375, 25, 25);
        bufferGraphics.fillRect(525, 375, 25, 25);
        int c = 150;
        while (c <= 750) {
            bufferGraphics.fillRect(c, 450, 25, 25);
            bufferGraphics.fillRect(c, 475, 25, 25);
            bufferGraphics.fillRect(c, 500, 25, 25);
            c += 50;
        }
        bufferGraphics.fillRect(175, 300, 150, 75);
        bufferGraphics.fillRect(600, 300, 150, 75);
        ((Graphics2D) bufferGraphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }
    public static void paintComponent5() {
        bufferGraphics.setColor(new Color(0x000000));
        bufferGraphics.fillRect(775, 525, 25, 25);
        ((Graphics2D) bufferGraphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }
    static int tankx = 775;
    static int tanky = 525;
    static int lifes = 3;
    static boolean game = true;

    public void movement() {

    }
    public static void swapBuffers() {
        Graphics g = bufferStrategy.getDrawGraphics();
        g.drawImage(buffer, 0, 0, null);
        bufferStrategy.show();
    }
    static class MyKeyListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {

        }
        @Override
        public void keyPressed(KeyEvent e) {
            int f = e.getKeyCode();
            if((f==KeyEvent.VK_W)&&(game)){
                tanky -= 25;
                swapBuffers();
            }
            if((f==KeyEvent.VK_A)&&(game)){
                tankx -= 25;
                swapBuffers();
            }
            if((f == KeyEvent.VK_S)&&(game)){
                tanky += 25;
                swapBuffers();
            }
            if((f == KeyEvent.VK_D)&&(game)){
                tankx += 25;
                swapBuffers();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }
}
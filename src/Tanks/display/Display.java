package Tanks.display;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Arrays;
public abstract class Display{
    private static boolean created=false;
    private static JFrame window;
    private static Canvas content;
    private static BufferedImage buffer;
    private static int[] bufferData;
    private static Graphics bufferGraphics;
    private static int clearColor;
    private static BufferStrategy bufferStrategy;
    public static void create(int length,int width,String title,int _clearColor,int numBuffers){
        if(created){
            return;
        }
        window = new JFrame(title);
        content = new Canvas();
        Dimension size=new Dimension(length,width);
        content.setPreferredSize(size);
        window.setResizable(false);
        window.getContentPane().add(content);
        window.pack();
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        buffer=new BufferedImage(length,width,BufferedImage.TYPE_INT_ARGB);
        bufferData=((DataBufferInt)buffer.getRaster().getDataBuffer()).getData();
        bufferGraphics=buffer.getGraphics();
        clearColor=_clearColor;
        content.createBufferStrategy(numBuffers);
        bufferStrategy=content.getBufferStrategy();
        created=true;
    }
    public static void clear(){
        Arrays.fill(bufferData,clearColor);
    }
    public static void render(){
        bufferGraphics.setColor(new Color(0xff0000ff));
        bufferGraphics.fillOval(400,250,100,100);
        ((Graphics2D)bufferGraphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
    }
    public static void swapBuffers(){
        Graphics g= bufferStrategy.getDrawGraphics();
        g.drawImage(buffer,0,0,null);
        bufferStrategy.show();
    }
}

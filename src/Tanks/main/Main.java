package Tanks.main;
import Tanks.display.Display;
import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.swing.Timer;
import javax.swing.AbstractAction;
public class Main{
    public static void main(String[] args) throws IOException {
        Display.create(900,700,"Tanks",0xffffffff,3);
        Timer t=new Timer(1000 / 60, new AbstractAction(){
            public void actionPerformed(ActionEvent e){
                Display.clear();
                Display.paintComponent1();
                Display.paintComponent4();
                Display.paintComponent2();
                Display.paintComponent3();
                Display.paintComponent5();
                Display.swapBuffers();
            }
        });
        t.setRepeats(true);
        t.start();
    }
}


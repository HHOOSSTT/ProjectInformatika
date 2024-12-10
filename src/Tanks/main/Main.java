package Tanks.main;
import Tanks.display.Display;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Main {
    public static void main(String[] args) {
        Display.create(900, 700, "Tanks", 0xffffffff, 3);
        Timer t = new Timer(1000 / 60, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
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
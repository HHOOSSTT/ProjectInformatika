package Tanks.main;

import Tanks.display.Map;
import java.io.IOException;
import javax.swing.*;

public class Main{
    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame("Tanks");
        Map map = null;
        try{
            map = new Map();
        }catch(IOException e){
            System.err.println("Ошибка при создании карты");
            return;
        }
        frame.add(map);
        frame.setSize(950, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
   }
}


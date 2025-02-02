package Tanks.main;
import Tanks.display.Map;
import Tanks.display.Tank;

import java.awt.*;
import java.io.IOException;
import javax.swing.*;

public class Main{
    public static void main(String[] args) throws IOException {
        JFrame jframe=new JFrame("Tanks");
        jframe.setSize(950,700);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setBackground(Color.GRAY);
        jframe.setLocationRelativeTo(null);
        jframe.setVisible(true);
        Map map=new Map();
        jframe.add(map);
        Tank tank=new Tank(775,550,"UP");
        jframe.add(tank);
    }
}


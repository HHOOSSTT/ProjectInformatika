package Tanks.main;

import Tanks.display.Map;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.*;

public class Main{
    public static void main(String[] args) throws IOException {
        Scanner sc=new Scanner(System.in);
        JFrame frame = new JFrame("Tanks");
        Map map=null;
        try{
            map=new Map();
        }catch(IOException e){
            System.err.println("Ошибка при создании карты: ");
            return;
        }
        frame.add(map);
        frame.setSize(950, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
   }
}


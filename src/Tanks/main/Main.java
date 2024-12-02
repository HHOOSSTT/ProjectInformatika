package Tanks.main;
import Tanks.display.Display;
import java.awt.event.ActionEvent;
import javax.swing.Timer;
import javax.swing.AbstractAction;
public class Main{
    public static void main(String[] args){
        Display.create(900,700,"Tanks",0xff00ff00,3);
        Timer t=new Timer(1000 / 60, new AbstractAction(){
            public void actionPerformed(ActionEvent e){
                Display.clear();
                Display.render();
                Display.swapBuffers();
            }
        });
        t.setRepeats(true);
        t.start();
    }
}

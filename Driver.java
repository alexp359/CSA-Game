import java.awt.*;
import javax.swing.*;

public class Driver{
    public static void main(String[] args){
        JFrame frame = new JFrame();
        frame.setVisible(true);
		frame.setContentPane(new Game(frame));
        frame.setSize(1920, 1080);
        
        System.out.println("Compiled");
    }
}
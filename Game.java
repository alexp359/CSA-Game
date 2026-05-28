import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Game extends JPanel implements ActionListener, KeyListener{
    Frame frame;
    int y, frameRotation;
    boolean movingRight, movingLeft;
    Timer carFPS, backgroundFPS;
    Toolkit tk;
    Image bg;

    public Game(Frame frame){
        this.frame = frame;
        frame.addKeyListener(this);
        frame.setFocusable(true);

        frameRotation = 1;
        y = 466;

        carFPS = new Timer(7, this);
        backgroundFPS = new Timer(80, this);

        carFPS.start();
        backgroundFPS.start();

        tk = Toolkit.getDefaultToolkit();
        
    }

    public void actionPerformed(ActionEvent e){
		if(e.getSource() == carFPS){
			if(movingRight) y += 8;
			if(movingLeft) y -= 8;

            if(y > 730) y = 730;
            if(y < 198) y = 198;

			repaint();
		}
        if(e.getSource() == backgroundFPS){
            frameRotation++;
            repaint();
            if(frameRotation >= 32) frameRotation = 1;
        }
    }

    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			movingRight = true;
        }else if(e.getKeyCode() == KeyEvent.VK_LEFT){
			movingLeft = true;
        }
    }

    public void keyReleased(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            movingRight = false;
        }else if(e.getKeyCode() == KeyEvent.VK_LEFT){
            movingLeft = false;
        }
    }
    public void keyTyped(KeyEvent e){}

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Image currFrame = tk.getImage("D:\\Java\\Programs\\CSA Game\\Frames(32)\\frame_" + frameRotation + ".png");
        g.drawImage(currFrame,0,0,this);

        g.setColor(Color.BLUE);
        g.drawRect(100,y,300,150);
    }
}
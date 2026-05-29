import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Game extends JPanel implements ActionListener, KeyListener{
    Frame frame;
    int y, x, frameRotation;
    boolean movingRight, movingLeft;
    Timer carFPS, backgroundFPS;
    Toolkit tk;
    Image[] bgArr;
    Image carImage;
    TrafficCar car;
    Rectangle carRect;

    public Game(Frame frame){
        this.frame = frame;
        frame.addKeyListener(this);
        frame.setFocusable(true);

        y = 466;
        x = 100;

        carRect = new Rectangle(x,y,270,122);

        carFPS = new Timer(7, this);
        backgroundFPS = new Timer(80, this);

        carFPS.start();
        backgroundFPS.start();

        frameRotation = 1;

        tk = Toolkit.getDefaultToolkit();
        
        bgArr = new Image[32];

        for(int i = 0; i < 32; i++){
            bgArr[i] = tk.getImage("D:\\Java\\Programs\\CSA Game\\Frames (32)\\frame_" + (i + 1) + ".png");
        }

        carImage = tk.getImage("D:\\Java\\Programs\\CSA Game\\car.png");

        car = new TrafficCar();
    }

    public void actionPerformed(ActionEvent e){
		if(e.getSource() == carFPS){
			if(movingRight) y += 8;
			if(movingLeft) y -= 8;

            if(y > 730) y = 730;
            if(y < 198) y = 198;

            carRect.setLocation(x,y);

			repaint();
		}
        if(e.getSource() == backgroundFPS){
            frameRotation++;
            repaint();
            if(frameRotation >= 32) frameRotation = 1;
        }

        if(carRect.intersects(car.getRect())){
            carFPS.stop();
            backgroundFPS.stop();
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

        g.drawImage(bgArr[frameRotation - 1],0,0,this);

        g.drawImage(carImage,x,y,this);

        g.setColor(Color.BLUE);
        g.drawRect(carRect.x,carRect.y,carRect.width,carRect.height);

        car.drawCar(g);
    }
}
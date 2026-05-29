import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class Game extends JPanel implements ActionListener, KeyListener{
    Frame frame;
    int y, x, frameRotation;
    boolean movingRight, movingLeft;
    Timer carFPS, backgroundFPS, trafficSpawner;
    Toolkit tk;
    Image[] bgArr;
    Image carImage;
    TrafficCar car1,car2;
    Rectangle carRect;
    ArrayList<TrafficCar> traffic;

    public Game(Frame frame){
        this.frame = frame;
        frame.addKeyListener(this);
        frame.setFocusable(true);

        y = 466;
        x = 100;

        carRect = new Rectangle(x,y,270,122);

        carFPS = new Timer(7, this);
        backgroundFPS = new Timer(80, this);
        trafficSpawner = new Timer(700,this);

        carFPS.start();
        backgroundFPS.start();
        trafficSpawner.start();

        frameRotation = 1;

        tk = Toolkit.getDefaultToolkit();
        
        bgArr = new Image[32];

        for(int i = 0; i < 32; i++){
            bgArr[i] = tk.getImage("C:\\Java\\Programs\\CSA Game\\Frames (32)\\frame_" + (i + 1) + ".png");
        }

        carImage = tk.getImage("C:\\Java\\Programs\\CSA Game\\car.png");

        car1 = new TrafficCar();
        car2 = new TrafficCar();

        traffic = new ArrayList<>();
        traffic.add(car1);
        traffic.add(car2);
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

        if(e.getSource() == trafficSpawner){
            traffic.add(new TrafficCar());
        }

        for(TrafficCar trafficCar : traffic){
            if(carRect.intersects(trafficCar.getRect())){
                carFPS.stop();
                backgroundFPS.stop();
            }
        }

        if(traffic.get(traffic.size() - 1).isOffScreen()) traffic.remove(traffic.size() - 1);
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

        for(TrafficCar trafficCar : traffic){
            trafficCar.drawCar(g);
        }
    }
}
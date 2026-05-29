import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TrafficCar implements ActionListener{
    int x, y, lane, speed;
    Rectangle rect;
    Toolkit tk;
    Image carImg;
    Timer time;

    public TrafficCar(){ //Car dimensions: 114 x 56
        speed = 7;
        lane = (int)(Math.random() * 3) + 1;
        x = 2200;

        if(lane == 1) y = 250;
        else if(lane == 2) y = 480;
        else if(lane == 3) y = 700;

        rect = new Rectangle(x,y,250,125);

        tk = Toolkit.getDefaultToolkit();
        carImg = tk.getImage("D:\\Java\\Programs\\CSA Game\\Cars (12)\\car_" + ((int)(Math.random()  * 12) + 1) + ".png");

        time = new Timer(20,this);
        time.start();
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == time){
            x -= speed;
            rect.setLocation(x,y);
            System.out.println(x);
        }
    }

    public Rectangle getRect(){
        return rect;
    }

    public int getLane(){
        return lane;
    }

    public void drawCar(Graphics g){
        g.drawImage(carImg,x,y,null);
        g.setColor(Color.RED);
        g.drawRect(rect.x,rect.y,rect.width,rect.height);
    }
}
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TrafficCar{
    int x, y, lane, speed;
    Rectangle rect;

    public TrafficCar(){ //Car dimensions: 114 x 56
        speed = 4;
        lane = (Math.random() * 3) + 1;
        x = 1200;
        
        if(lane == 1) y = 204;
        else if(lane == 2) y = 380;
        else if(lane == 3) y = 556;

        rect = new Rectangle(x,y,114,56);
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getLane(){
        return lane;
    }
}
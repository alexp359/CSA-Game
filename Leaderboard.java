import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Leaderboard extends JPanel 
{
    int time = Game.time();
    int[] topPlayers = new int[3];
    for (int i = 0; i< topPlayers.length; i++)
    {
        if(topPlayers[i] > time)
        {
            topPlayers[i] = time;
        }
    }
    public void paintComponent(Graphics g)
    {
        g.drawRect(200,200,200,200)
        g.
    }

    
    
    
}
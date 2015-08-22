package states;

import app.Engine;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class StateInit extends State
{
    public StateInit()
    {
        //
    }

    public void inputKeyPress(String key)
    {
        //
    }

    public void inputKeyRelease(String key)
    {
        //
    }

    public void inputKeyType(String key)
    {
        //
    }

    public void inputMouseClickL(MouseEvent e)
    {
        //
    }

    public void inputMouseClickR(MouseEvent e)
    {
        //
    }

    public void inputMouseMove(MouseEvent e)
    {
        //
    }
    
    public void render(Graphics g)
    {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 1366, 768);
    }
    
    public void tick()
    {
        Engine.setState(new StateLoad());
    }

}
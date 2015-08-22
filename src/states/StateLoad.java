package states;

import app.Engine;
import app.Game;
import gfx.Drawing;
import gfx.Text;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

public class StateLoad extends State
{
    private Rectangle loadRect;
    private String loadDetail;
    private int loadValue;
    
    public StateLoad()
    {
        this.loadRect = new Rectangle(483, 370, 400, 25);
        this.setLoad(0, "INITIALISING APPLICATION");
    }
    
    public void init()
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
        // Background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 1366, 768);
        
        // Title
        Text.write(g, "LOADING", 683, 320, "CENTER", new Font("Times New Roman", Font.PLAIN, 56), Color.WHITE);
        
        // Logo
        //g.drawImage(Drawing.getImage("logo/title2.png"), 333, 140, null);
        
        // Detail
        Text.write(g, this.loadDetail, 683, 450, "CENTER", new Font("Times New Roman", Font.PLAIN, 26), Color.WHITE);
        
        // Progress Bar
        Drawing.fillRect(g, this.loadRect, Color.BLACK);
        Drawing.fillRect(g, this.loadRect.x, this.loadRect.y, (this.loadRect.width / 100) * this.loadValue, this.loadRect.height, Color.WHITE);
        Drawing.drawRect(g, this.loadRect, Color.WHITE);
    }
    
    private void setLoad(int value, String detail)
    {
        this.loadValue = value;
        this.loadDetail = detail;
    }
    
    public void tick()
    {
        Engine.setState(Game.getStateInitial());
    }

}
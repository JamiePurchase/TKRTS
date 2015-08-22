package display;

import gfx.Colour;
import gfx.Drawing;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import skirmish.Skirmish;

public class Map
{
    private Skirmish mapSkirmish;
    private Rectangle mapArea;
    
    public Map(Skirmish skirmish, Rectangle area)
    {
        this.mapSkirmish = skirmish;
        this.mapArea = area;
    }
    
    public Rectangle getArea()
    {
        return this.mapArea;
    }
    
    public void inputClick(MouseEvent e)
    {
        //
    }
    
    public void render(Graphics g)
    {
        // Background
        //Drawing.fillRect(g, this.getArea(), Color.WHITE);
        Drawing.fadeRect(g, this.getArea(), Colour.getColourRGB(0, 100, 0), 0.3f);
        
        // Border
        Drawing.drawRect(g, this.getArea(), Color.BLACK);
    }
    
}
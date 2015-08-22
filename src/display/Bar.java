package display;

import gfx.Colour;
import gfx.Drawing;
import gfx.Text;
import graphics.GFX;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import players.Player;
import resources.Resource;
import skirmish.Skirmish;

public class Bar
{
    private Skirmish barSkirmish;
    private Rectangle barArea;
    
    public Bar(Skirmish skirmish, Rectangle area)
    {
        this.barSkirmish = skirmish;
        this.barArea = area;
    }
    
    public Rectangle getArea()
    {
        return this.barArea;
    }
    
    private String getCapacity(Resource resource)
    {
        return "" + this.barSkirmish.getPlayerUser().getCapacity(resource);
    }
    
    private String getStock(Resource resource)
    {
        return "" + this.barSkirmish.getPlayerUser().getStock(resource);
    }
    
    public void inputClick(MouseEvent e)
    {
        //
    }
    
    public void render(Graphics g)
    {
        // Background
        //Drawing.fillRect(g, this.getArea(), "STEEL");
        Drawing.fadeRect(g, this.getArea(), Colour.getColourRGB(0, 100, 0), 0.3f);
        
        // Resources
        Text.write(g, "FOOD", this.getArea().x + 25, this.getArea().y + 27, "LEFT", GFX.font("STANDARD"), Color.BLACK);
        Text.write(g, this.getStock(Resource.FOOD), this.getArea().x + 170, this.getArea().y + 27, "RIGHT", GFX.font("STANDARD"), Color.BLACK);
        //Text.write(g, "/" + this.getCapacity(Resource.FOOD), this.getArea().x + 180, this.getArea().y + 27, "LEFT", GFX.font("STANDARD"), Color.BLACK);
        
        // Border
        Drawing.drawRect(g, this.getArea(), "BLACK", false, false, true, false);
    }
    
}
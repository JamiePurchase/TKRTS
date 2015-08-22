package states;

import display.Bar;
import display.Info;
import display.Map;
import gfx.Drawing;
import gfx.Text;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import skirmish.Skirmish;

public class StateSkirmish extends State
{
    // Skirmish
    private Skirmish skirmish;
    
    // World
    private final Rectangle areaWorld = new Rectangle(0, 38, 1366, 730);
    
    // Display
    private Bar displayToolbar;
    private Info displayInfobar;
    private Map displayMinimap;
    
    public StateSkirmish()
    {
        // Skirmish
        this.skirmish = new Skirmish(this);
        
        // Display
        this.displayToolbar = new Bar(this.skirmish, new Rectangle(0, 0, 1366, 38));
        this.displayInfobar = new Info(this.skirmish);
        this.displayMinimap = new Map(this.skirmish, new Rectangle(1151, 53, 200, 200));
        
        // NOTE: displays should have opaque backgrounds
    }
    
    public Rectangle getAreaWorld()
    {
        return this.areaWorld;
    }
    
    public Point getPointWorld(Point render)
    {
        return this.skirmish.getWorldPoint(render);
    }

    public void inputKeyPress(String key)
    {
        this.skirmish.inputKey(key);
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
        // Toolbar
        if(this.displayToolbar.getArea().contains(e.getPoint()))
        {
            this.displayToolbar.inputClick(e);
            return;
        }
        
        // Infobar
        if(this.skirmish.getEntitySelect() != null && this.displayInfobar.getArea().contains(e.getPoint()))
        {
            this.displayInfobar.inputClick(e);
            return;
        }
        
        // Minimap
        if(this.displayMinimap.getArea().contains(e.getPoint()))
        {
            this.displayMinimap.inputClick(e);
            return;
        }
        
        // World
        this.skirmish.inputClickL(this.getPointWorld(e.getPoint()));
    }

    public void inputMouseClickR(MouseEvent e)
    {
        // Toolbar
        if(this.displayToolbar.getArea().contains(e.getPoint())) {return;}
        
        // Infobar
        if(this.skirmish.getEntitySelect() != null && this.displayInfobar.getArea().contains(e.getPoint())) {return;}
        
        // Minimap
        if(this.displayMinimap.getArea().contains(e.getPoint())) {return;}
        
        // World
        this.skirmish.inputClickR(this.getPointWorld(e.getPoint()));
    }

    public void inputMouseMove(MouseEvent e)
    {
        //
    }
    
    public void render(Graphics g)
    {
        // Skirmish
        this.skirmish.render(g);
        
        // Display
        if(this.skirmish.getEntitySelect() != null) {this.displayInfobar.render(g);}
        this.displayToolbar.render(g);
        this.displayMinimap.render(g);
    }
    
    public void tick()
    {
        this.skirmish.tick();
    }
    
}
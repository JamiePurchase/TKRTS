package entities;

import commands.Command;
import gfx.Drawing;
import graphics.GFX;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.EnumMap;
import players.Player;
import resources.Resource;
import resources.Stock;
import skirmish.Skirmish;
import world.Zoom;

public class EntityConstruct extends Entity
{
    private BufferedImage image;
    private EnumMap<StatType, StatValue> stats;
    private Point rallyPoint;
    private ArrayList<Command> actionCommands;
    private ArrayList<ConstructAction> actionQueue;
    
    public EntityConstruct(String ref, Skirmish skirmish, Player player, String name, Point pos, BufferedImage image, int offsetX, int offsetY, Polygon interact, Polygon boundary)
    {
        super(ref, skirmish, EntityType.CONSTRUCT, player, name, pos, offsetX, offsetY, interact, boundary, true);
        this.image = image;
        this.stats = new EnumMap<StatType, StatValue>(StatType.class);
        this.stats.put(StatType.CONDITION, new StatValue(1000, 1000));
        this.rallyPoint = null;
        this.actionCommands = new ArrayList();
        this.actionQueue = new ArrayList();
    }
    
    public void actionAdd(ConstructAction action)
    {
        this.actionQueue.add(action);
    }
    
    public void actionDone()
    {
        this.actionQueue.remove(0);
    }
    
    public void click()
    {
        //
    }
    
    private BufferedImage getRenderImageConstruct()
    {
        // NOTE: develop this later
        return GFX.zoom(this.image, this.getZoom());
    }
    
    private BufferedImage getRenderImageSelect()
    {
        // NOTE: this depends on the size of the constuct
        return GFX.image("Graphics/select_construct1_green.png", this.getZoom());
    }
    
    public StatValue getStat(StatType type)
    {
        return stats.get(type);
    }
    
    public void render(Graphics g)
    {
        if(this.isSelected())
        {
            this.renderSelect(g);
            if(this.rallyPoint != null) {this.renderRally(g);}
        }
        this.renderConstruct(g);
    }
    
    private void renderConstruct(Graphics g)
    {
        Drawing.drawImage(g, this.getRenderImageConstruct(), this.getRenderCorner().x, this.getRenderCorner().y);
    }
    
    private void renderRally(Graphics g)
    {
        //
    }
    
    private void renderSelect(Graphics g)
    {
        Drawing.drawImage(g, this.getRenderImageSelect(), this.getRenderCorner().x, this.getRenderCorner().y + 100);
    }
    
    public void setRallyPoint(Point point)
    {
        this.rallyPoint = point;
    }
    
    public void update()
    {
        if(this.actionQueue.size() > 0) {this.updateAction();}
    }
    
    private void updateAction()
    {
        this.actionQueue.get(0).tick();
    }
    
}
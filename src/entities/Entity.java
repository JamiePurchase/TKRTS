package entities;

import graphics.GFX;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import players.Player;
import shapes.PolygonTools;
import skirmish.Skirmish;
import tkrts.URT;
import world.Zoom;

public abstract class Entity
{
    private String ref;
    private Skirmish skirmish;
    private EntityType type;
    private Player player;
    private String name;
    private Point position;
    private int offsetX, offsetY;
    private Polygon interact, boundary;
    private boolean selectAble, selectActive;
    
    public Entity(String ref, Skirmish skirmish, EntityType type, Player player, String name, Point pos, int offsetX, int offsetY, Polygon interact, Polygon boundary, boolean selectable)
    {
        this.ref = ref;
        this.skirmish = skirmish;
        this.type = type;
        this.player = player;
        this.name = name;
        this.position = pos;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.interact = interact;
        this.boundary = boundary;
        this.selectAble = selectable;
        this.selectActive = false;
    }
    
    public abstract void click();
    
    public Polygon getBoundary()
    {
        return PolygonTools.translate(this.boundary, this.position.x - this.offsetX, this.position.y - this.offsetY);
    }
    
    public Point getCorner()
    {
        return new Point(this.position.x - this.offsetX, this.position.y - this.offsetY);
    }
    
    public Polygon getInteract()
    {
        return PolygonTools.translate(this.interact, this.position.x - this.offsetX, this.position.y - this.offsetY);
    }
    
    public BufferedImage getInterfaceImage()
    {
        if(this.getType() == EntityType.CONSTRUCT) {return GFX.image("Constructs/Portrait/" + "temp1" + ".png");}
        if(this.getType() == EntityType.NATURE) {return GFX.image("Nature/Portrait/" + "temp1" + ".png");}
        if(this.getType() == EntityType.UNIT) {return GFX.image("Units/Portrait/" + "temp1" + ".png");}
        return null;
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public Player getPlayer()
    {
        return this.player;
    }
    
    public Point getPosition()
    {
        return this.position;
    }
    
    public String getRef()
    {
        return this.ref;
    }
    
    public Point getRenderCorner()
    {
        return new Point(this.getCorner().x + this.getWorldX(), this.getCorner().y + this.getWorldY());
    }
    
    public Polygon getRenderInteract()
    {
        return PolygonTools.translate(this.getInteract(), this.getWorldX(), this.getWorldY());
    }
    
    public Point getRenderPosition()
    {
        return new Point(this.getPosition().x + this.getWorldX(), this.getPosition().y + this.getWorldY());
    }
    
    public Skirmish getSkirmish()
    {
        return this.skirmish;
    }
    
    public EntityType getType()
    {
        return this.type;
    }
    
    public int getWorldX()
    {
        return this.skirmish.getWorldOffsetX();
    }
    
    public int getWorldY()
    {
        return this.skirmish.getWorldOffsetY();
    }
    
    public Zoom getZoom()
    {
        return this.skirmish.getWorldZoom();
    }
    
    public boolean isSelected()
    {
        return this.selectActive;
    }
    
    public abstract void render(Graphics gfx);
    
    public void setPosition(int posX, int posY)
    {
        this.position = new Point(posX, posY);
    }
    
    public void setSelected(boolean value)
    {
        if(this.selectAble) {this.selectActive = value;}
    }
    
    public abstract void update();

}
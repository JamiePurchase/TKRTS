package entities;

import gfx.Drawing;
import graphics.GFX;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import resources.Resource;
import skirmish.Skirmish;

public class EntityNature extends Entity
{
    private BufferedImage image;
    private Select select;
    private Resource resourceType;
    private int resourceAmount;
    
    public EntityNature(String ref, Skirmish skirmish, String name, Point pos, BufferedImage image, int offsetX, int offsetY, Select select, Polygon interact, Polygon boundary, Resource resourceType, int resourceAmount)
    {
        super(ref, skirmish, EntityType.NATURE, null, name, pos, offsetX, offsetY, interact, boundary, true);
        this.image = image;
        this.select = select;
        this.resourceType = resourceType;
        this.resourceAmount = resourceAmount;
    }
    
    public void click()
    {
        //
    }
    
    private BufferedImage getRenderImageNature()
    {
        // NOTE: may need to transform according to board zoom
        return GFX.zoom(this.image, this.getZoom());
    }
    
    private BufferedImage getRenderImageSelect()
    {
        if(this.select == Select.CIRCLE) {return GFX.image("Graphics/select_unit1_grey.png", this.getZoom());}
        if(this.select == Select.DIAMOND) {return GFX.image("Graphics/select_diamond1.png", this.getZoom());}
        return null;
    }
    
    private Point getRenderPointSelect()
    {
        if(this.select == Select.CIRCLE) {return new Point(this.getRenderPosition().x - 35, this.getRenderPosition().y - 30);}
        if(this.select == Select.DIAMOND) {return new Point(this.getRenderCorner().x - 40, this.getRenderCorner().y - 20);}
        return null;
    }
    
    public boolean isResource()
    {
        if(this.resourceType != null) {return true;}
        return false;
    }
    
    public void render(Graphics g)
    {
        if(this.isSelected()) {this.renderSelect(g);}
        this.renderNature(g);
    }
    
    private void renderNature(Graphics g)
    {
        Drawing.drawImage(g, this.getRenderImageNature(), this.getRenderCorner().x, this.getRenderCorner().y);
    }
    
    private void renderSelect(Graphics g)
    {
        Drawing.drawImage(g, this.getRenderImageSelect(), this.getRenderPointSelect());
        //Drawing.drawImage(g, this.getRenderImageSelect(), this.getRenderCorner().x - 40, this.getRenderCorner().y - 20);
    }
    
    public void update()
    {
        //
    }
    
}
package entities;

import app.Engine;
import gfx.Drawing;
import graphics.GFX;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import maths.Maths;
import players.Player;
import skirmish.Skirmish;

public class EntityUnit extends Entity
{
    private BufferedImage animImage;
    private AnimUnit animSequence;
    private int animTickNow, animTickMax;
    private int animFrameNow, animFrameMax;
    private Face posFace;
    private Action action;
    private boolean actionBuild, actionGather, actionRepair;
    
    public EntityUnit(String ref, Skirmish skirmish, Player player, String name, Point pos, ClothUnit clothes, Polygon interact, Polygon boundary, Face face, boolean build, boolean gather, boolean repair)
    {
        super(ref, skirmish, EntityType.UNIT, player, name, pos, 24, 86, interact, boundary, true);
        this.animImage = clothes.getImage();
        this.setAnimation(AnimUnit.IDLE);
        this.posFace = face;
        this.action = null;
        this.actionBuild = build;
        this.actionGather = gather;
        this.actionRepair = repair;
    }
    
    public void click()
    {
        //
    }
    
    public Face getFace()
    {
        return this.posFace;
    }
    
    private BufferedImage getRenderImageSelect()
    {
        // NOTE: consider different colours
        return GFX.image("Graphics/select_unit1_green.png", this.getZoom());
    }
    
    private BufferedImage getRenderImageTarget()
    {
        // NOTE: consider different colours
        return GFX.image("Graphics/flag1_green.png", this.getZoom());
    }
    
    private BufferedImage getRenderImageUnit()
    {
        int grabX = 0;
        if(this.animSequence == AnimUnit.WALK)
        {
            grabX = this.animFrameNow * 48;
        }
        int grabY = 0;
        if(this.posFace == Face.SW) {grabY = 96;}
        if(this.posFace == Face.NE) {grabY = 192;}
        if(this.posFace == Face.NW) {grabY = 288;}
        return GFX.zoom(this.animImage.getSubimage(grabX, grabY, 48, 96), this.getZoom());
    }
    
    public boolean isBuilder()
    {
        return this.actionBuild;
    }
    
    public boolean isGatherer()
    {
        return this.actionGather;
    }
    
    public boolean isRepairman()
    {
        return this.actionRepair;
    }
    
    public void render(Graphics g)
    {
        if(this.isSelected())
        {
            this.renderSelect(g);
            if(this.action != null)
            {
                if(this.action.getType() == ActionType.WALK) {this.renderTarget(g);}
            }
        }
        this.renderUnit(g);
        
        // TEMP
        /*if(this.getInteract().contains(Engine.getMousePoint()))
        {
            g.setColor(Color.BLUE);
            g.drawPolygon(this.getInteract());
        }*/
        
        // TEMP
        /*if(this.isSelected())
        {
            g.setColor(Color.BLUE);
            g.drawPolygon(this.getInteract());
        }*/
        
        // TEMP
        /*if(this.action != null)
        {
            Graphics2D g2D = (Graphics2D) g;
            g2D.setColor(Color.BLUE);
            g2D.draw(this.action.getTargetPointArea());
        }*/
    }
    
    private void renderSelect(Graphics g)
    {
        Drawing.drawImage(g, this.getRenderImageSelect(), this.getRenderPosition().x - 35, this.getRenderPosition().y - 30);
    }
    
    private void renderTarget(Graphics g)
    {
        // NOTE: need to check if this is currently visible on the world (depending on scrolling)
        // consider building a method that returns true/false if a point is visible
        int drawX = this.action.getTargetPoint().x + this.getSkirmish().getWorldOffsetX();
        int drawY = this.action.getTargetPoint().y + this.getSkirmish().getWorldOffsetY();
        Drawing.drawImage(g, this.getRenderImageTarget(), drawX - 24, drawY - 90);
    }
    
    private void renderUnit(Graphics g)
    {
        Drawing.drawImage(g, this.getRenderImageUnit(), this.getRenderCorner().x, this.getRenderCorner().y);
    }
    
    public void setAction(Action action)
    {
        this.action = action;
        if(action == null) {this.setAnimation(AnimUnit.IDLE);}
        else {this.setAnimation(action.getAnimation());}
    }
    
    public void setAnimation(AnimUnit anim)
    {
        this.animSequence = anim;
        this.animTickNow = 0;
        this.animTickMax = 0;
        this.animFrameNow = 0;
        this.animFrameMax = 0;
        if(this.animSequence == AnimUnit.WALK)
        {
            this.animTickMax = 16;
            this.animFrameMax = 3;
        }
    }
    
    private void setFace(Face face)
    {
        this.posFace = face;
    }
    
    private void setFacePoint(Point target)
    {
        Face face = Face.SE;
        if(target.x < this.getRenderPosition().x)
        {
            if(target.y < this.getRenderPosition().y) {face = Face.NW;}
            else {face = Face.SW;}
        }
        else if(target.y < this.getRenderPosition().y) {face = Face.NE;}
        this.setFace(face);
    }
    
    public void setPosition(int posX, int posY, Face posF)
    {
        this.setPosition(posX, posY);
        this.setFace(posF);
    }
    
    public void update()
    {
        // Action
        if(this.action != null) {this.updateAction();}
        
        // Anim
        this.updateAnim();
    }
    
    private void updateAction()
    {
        if(this.action.getType() == ActionType.WALK)
        {
            if(this.action.getTargetPointArea().contains(this.getPosition()))
            {
                // NOTE: consider how units are deemed as idle
                // NOTE: consider automatically attacking / retreating
                this.setAction(this.action.getNext());
            }
            else {this.walkTowards(this.action.getTargetPoint());}
        }
    }
    
    private void updateAnim()
    {
        this.animTickNow ++;
        if(this.animTickNow >= this.animTickMax)
        {
            this.animTickNow = 0;
            this.animFrameNow ++;
            if(this.animFrameNow > this.animFrameMax)
            {
                this.animFrameNow = 0;
            }
        }
    }
    
    private void walkTowards(Point target)
    {
        double speed = 3;
        double deltaX = target.x - this.getPosition().x;
        double deltaY = target.y - this.getPosition().y;
        double direction = Math.atan(deltaY / deltaX);
        int moveX = target.x;
        int moveY = target.y;
        //if(Maths.difference(this.getPosition().x, target.x) > 10)
        //{
            if(this.getPosition().x < target.x) {moveX = new Double(this.getPosition().x + (speed * Math.cos(direction))).intValue();}
            else {moveX = new Double(this.getPosition().x - (speed * Math.cos(direction))).intValue();}
        //}
        //if(Maths.difference(this.getPosition().y, target.y) > 10)
        //{
            if(this.getPosition().y < target.y) {moveY = new Double(this.getPosition().y + (speed * Math.cos(direction))).intValue();}
            else {moveY = new Double(this.getPosition().y - (speed * Math.cos(direction))).intValue();}
        //}
        this.setPosition(moveX, moveY);
        this.setFacePoint(target);
    }
    
}
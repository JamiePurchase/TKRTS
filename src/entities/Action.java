package entities;

import java.awt.Point;
import java.awt.geom.Ellipse2D;

public abstract class Action
{
    private ActionType type;
    private Point targetPoint;
    private Entity targetEntity;
    private Action complete;
    
    public Action(ActionType type, Point point, Action complete)
    {
        this.type = type;
        this.targetEntity = null;
        this.targetPoint = point;
        this.complete = complete;
    }
    
    public Action(ActionType type, Entity entity, Action complete)
    {
        this.type = type;
        this.targetEntity = entity;
        this.targetPoint = null;
        this.complete = complete;
    }
    
    public abstract AnimUnit getAnimation();
    
    public Action getNext()
    {
        return this.complete;
    }
    
    public Entity getTargetEntity()
    {
        return this.targetEntity;
    }
    
    public Point getTargetPoint()
    {
        return this.targetPoint;
    }
    
    public Ellipse2D getTargetPointArea()
    {
        return new Ellipse2D.Double(this.targetPoint.x - 20, this.targetPoint.y - 20, 40, 40);
    }
    
    public ActionType getType()
    {
        return this.type;
    }
    
}
package entities;

import java.awt.Point;

public class ActionWalk extends Action
{
    public ActionWalk(Point target, Action complete)
    {
        // NOTE: the target is an actual render point
        // this needs converting into the data point (using board pos and scroll info)
        super(ActionType.WALK, target, complete);
    }
    
    public ActionWalk(Entity target, Action complete)
    {
        super(ActionType.WALK, target.getPosition(), complete);
    }
    
    public AnimUnit getAnimation()
    {
        return AnimUnit.WALK;
    }
    
}
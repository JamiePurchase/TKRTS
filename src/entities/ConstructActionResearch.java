package entities;

import technology.Technology;

public class ConstructActionResearch extends ConstructAction
{
    private Technology actionTech;
    
    public ConstructActionResearch(EntityConstruct entity, Technology tech, int time)
    {
        super(entity, ConstructType.TRAIN, time);
        this.actionTech = tech;
    }
    
    public void complete()
    {
        // NOTE: create the new tech for this player
        // remove this action from the queue
        this.remove();
    }
    
    public void tick()
    {
        super.tick();
    }
    
}
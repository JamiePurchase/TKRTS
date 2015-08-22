package entities;

public class ConstructActionTrain extends ConstructAction
{
    private EntityUnit actionUnit;
    
    public ConstructActionTrain(EntityConstruct entity, EntityUnit unit, int time)
    {
        super(entity, ConstructType.TRAIN, time);
        this.actionUnit = unit;
    }
    
    public void complete()
    {
        // NOTE: create a new unit at (or near) the rally point of the building
        // remove this action from the queue
        this.remove();
    }
    
    public void tick()
    {
        super.tick();
    }
    
}
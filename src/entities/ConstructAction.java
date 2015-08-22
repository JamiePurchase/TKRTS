package entities;

public abstract class ConstructAction
{
    private EntityConstruct actionEntity;
    private ConstructType actionType;
    private int actionTickNow, actionTickMax;
    
    public ConstructAction(EntityConstruct entity, ConstructType type, int time)
    {
        this.actionEntity = entity;
        this.actionType = type;
        this.actionTickNow = 0;
        this.actionTickMax = time;
    }
    
    public abstract void complete();
    
    public void remove()
    {
        this.actionEntity.actionDone();
    }
    
    public void tick()
    {
        this.actionTickNow ++;
        if(this.actionTickNow >= this.actionTickMax) {this.complete();}
    }
    
}
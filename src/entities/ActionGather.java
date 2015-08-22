package entities;

public class ActionGather extends Action
{
    
    public ActionGather(EntityNature target)
    {
        super(ActionType.GATHER, target, null);
    }
    
    public AnimUnit getAnimation()
    {
        return AnimUnit.GATHER;
    }
    
}
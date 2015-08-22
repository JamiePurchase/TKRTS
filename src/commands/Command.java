package commands;

import entities.Entity;

public class Command
{
    private Entity entity;
    
    public Command(Entity entity)
    {
        this.entity = entity;
        // NOTE: consider subclasses for different types (eg: train, research, build, abilities, etc...)
    }
    
}
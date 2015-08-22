package app;

import states.State;
import states.StateInit;

public class Game
{
    private static State gameStateInitial;
    
    public Game(String name, String author, String version, String resource, State initial)
    {
        gameStateInitial = initial;
        new Engine(name, author, version, resource, new StateInit()).start(false);
    }
    
    public static State getStateInitial()
    {
        return gameStateInitial;
    }
    
    public static void setState(State state)
    {
        Engine.setState(state);
    }
    
}
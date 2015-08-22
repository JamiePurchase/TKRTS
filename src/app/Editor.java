package app;

import states.StateInit;

public class Editor
{

    public static void main(String[] args)
    {
        String name = "TKRTS";
        String author = "Jamie Purchase";
        String version = "0.1.0";
        String path = "C:/Users/Jamie/Documents/NetBeansProjects/GameEngine/TKRTS/src/res/";
        new Engine(name, author, version, path, new StateInit()).start(false);
    }
    
}
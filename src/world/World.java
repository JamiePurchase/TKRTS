package world;

import entities.Entity;
import java.util.ArrayList;
import tkrts.URT;

public class World
{
    private URT uRefTag;
    private Terrain landPaint;
    private ArrayList<Entity> entList;

    public World(URT ref)
    {
        this.uRefTag = ref;
        this.landPaint = null;
        this.entList = new ArrayList();
    }
    
}
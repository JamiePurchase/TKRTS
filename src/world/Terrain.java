package world;

import java.awt.image.BufferedImage;
import tkrts.URT;

public class Terrain
{
    private URT uRefTag;
    private String name;
    private BufferedImage image;
    
    public Terrain(URT ref, String name, BufferedImage image)
    {
        this.uRefTag = ref;
        this.name = name;
        this.image = image;
    }

}
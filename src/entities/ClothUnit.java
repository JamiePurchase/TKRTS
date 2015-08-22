package entities;

import gfx.Drawing;
import graphics.GFX;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ClothUnit
{
    int head, shirt, legs, boots;
    
    public ClothUnit(int head, int shirt, int legs, int boots)
    {
        this.head = head;
        this.shirt = shirt;
        this.legs = legs;
        this.boots = boots;
    }
    
    public BufferedImage getImage()
    {
        ArrayList<BufferedImage> layers = new ArrayList();
        layers.add(this.getImageFile("head" + this.head));
        layers.add(this.getImageFile("shirts" + this.shirt));
        layers.add(this.getImageFile("legs" + this.legs));
        layers.add(this.getImageFile("boots" + this.boots));
        BufferedImage image = Drawing.getImageNew(192, 384, layers);
        return image;
    }
    
    private BufferedImage getImageFile(String item)
    {
        return GFX.image("Units/Clothes/" + item + ".png");
    }
    
}
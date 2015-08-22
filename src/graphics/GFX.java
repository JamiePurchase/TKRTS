package graphics;

import app.Engine;
import gfx.Drawing;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import world.Zoom;

public class GFX
{
    
    public static Color colour(String colour)
    {
        return Color.BLACK;
    }
    
    public static Font font(String font)
    {
        if(font.equals("INFO_DETAIL")) {return new Font("Times New Roman", Font.PLAIN, 12);}
        if(font.equals("INFO_HEADER")) {return new Font("Times New Roman", Font.PLAIN, 26);}
        if(font.equals("STANDARD")) {return new Font("Times New Roman", Font.PLAIN, 14);}
        return new Font("Times New Roman", Font.PLAIN, 14);
    }
    
    public static BufferedImage image(String file)
    {
        return Drawing.getImageFile(Engine.getResourcePath() + "/" + file);
    }
    
    public static BufferedImage image(String file, Zoom zoom)
    {
        return zoom(image(file), zoom);
    }
    
    public static BufferedImage zoom(BufferedImage image, Zoom zoom)
    {
        int scale = 1;
        if(zoom == Zoom.MID) {scale = 2;}
        if(zoom == Zoom.OUT) {scale = 4;}
        return Drawing.resize(image, image.getWidth() / scale, image.getHeight() / scale);
    }
    
}
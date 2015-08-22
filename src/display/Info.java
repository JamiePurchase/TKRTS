package display;

import gfx.Colour;
import gfx.Drawing;
import gfx.Text;
import graphics.GFX;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import skirmish.Skirmish;

public class Info
{
    private Skirmish infoSkirmish;
    private final Rectangle infoArea = new Rectangle(258, 543, 850, 200);
    
    public Info(Skirmish skirmish)
    {
        this.infoSkirmish = skirmish;
    }
    
    public Rectangle getArea()
    {
        return this.infoArea;
    }
    
    private Rectangle getAreaCommand(int col, int row)
    {
        return new Rectangle(783 + (col * 50), this.infoArea.y + 25 + (row * 50), 50, 50);
    }
    
    public void inputClick(MouseEvent e)
    {
        //
    }
    
    public void render(Graphics g)
    {
        this.renderPane(g);
        this.renderPortrait(g);
        this.renderStats(g);
        this.renderCommands(g);
        this.renderName(g);
    }
    
    private void renderCommands(Graphics g)
    {
        for(int col = 0; col < 6; col ++)
        {
            for(int row = 0; row < 3; row ++)
            {
                // Background
                Drawing.fillRect(g, this.getAreaCommand(col, row), Colour.getColourRGB(50, 50, 50));
                
                // TEMP
                if(this.infoSkirmish.getEntitySelect().getRef().equals("TOWNC1") && row == 0 && col == 0)
                {
                    Drawing.drawImage(g, Drawing.resize(GFX.image("units/portrait/temp1.png"), 50, 50), this.getAreaCommand(col, row).x, this.getAreaCommand(col, row).y);
                }
                
                // Border
                Drawing.drawRect(g, this.getAreaCommand(col, row), "BLACK");
            }
        }
    }
    
    private void renderName(Graphics g)
    {
        Drawing.drawImage(g, GFX.image("Graphics/panel_info2_green.png"), 533, 510);
        Text.write(g, this.infoSkirmish.getEntitySelect().getName(), 683, 545, "CENTER", GFX.font("INFO_HEADER"), Colour.getColourRGB(150, 150, 150));
    }
    
    private void renderPane(Graphics g)
    {
        Drawing.fadeRect(g, this.infoArea.x + 5, this.infoArea.y + 5, this.infoArea.width - 10, this.infoArea.height - 10, Colour.getColourRGB(0, 100, 0), 0.5f);
        Drawing.drawImage(g, GFX.image("Graphics/panel_info_green.png"), this.infoArea.x, this.infoArea.y);
    }
    
    private void renderPortrait(Graphics g)
    {
        Drawing.drawImage(g, this.infoSkirmish.getEntitySelect().getInterfaceImage(), 633, this.infoArea.y + 40);
        Drawing.drawRect(g, new Rectangle(633, this.infoArea.y + 40, 100, 100), Color.BLACK);
    }
    
    private void renderStats(Graphics g)
    {
        Text.write(g, "CONDITION", this.infoArea.x + 15, this.infoArea.y + 45, "LEFT", GFX.font("INFO_DETAIL"), Color.BLACK);
        Text.write(g, "100", this.infoArea.x + 300, this.infoArea.y + 45, "RIGHT", GFX.font("INFO_DETAIL"), Color.BLACK);
        Text.write(g, "POPULATION", this.infoArea.x + 15, this.infoArea.y + 75, "LEFT", GFX.font("INFO_DETAIL"), Color.BLACK);
        Text.write(g, "+10", this.infoArea.x + 300, this.infoArea.y + 75, "RIGHT", GFX.font("INFO_DETAIL"), Color.BLACK);
    }
    
}
package skirmish;

import entities.ActionGather;
import entities.ActionWalk;
import entities.ClothUnit;
import entities.Entity;
import entities.EntityConstruct;
import entities.EntityNature;
import entities.EntityType;
import entities.EntityUnit;
import entities.Face;
import entities.Select;
import entities.StatType;
import gfx.Colour;
import gfx.Drawing;
import graphics.GFX;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import players.Banner;
import players.Player;
import resources.Resource;
import states.StateSkirmish;
import world.World;
import world.Zoom;

public class Skirmish
{
    // State
    private StateSkirmish state;
    
    // Game
    // gameType?
    private boolean gamePause;
    
    // World
    private World worldTerrain;
    private int worldOffsetX, worldOffsetY;
    private Zoom worldZoom;
    
    // Players
    private ArrayList<Player> playerArray;
    private Player playerUser;
    
    // Entities
    private ArrayList<Entity> entityArray;
    private Entity entitySelect;
    
    public Skirmish(StateSkirmish state)
    {
        // State
        this.state = state;
        
        // Game
        this.gamePause = false;
        // NOTE: we need to specify how the game is won, when times expires, what actions are allowed, etc...
        
        // World
        this.worldTerrain = null;
        this.worldOffsetX = 100;
        this.worldOffsetY = 100;
        this.worldZoom = Zoom.IN;
        
        // Players
        this.playerArray = new ArrayList();
        this.playerUser = null;
        
        // Entities
        this.entityArray = new ArrayList();
        this.entitySelect = null;
        
        // ---------- TEMPORARY DATA ---------- //
        
        // TEMP
        Colour.addColour("GRASS", 211, 216, 152);
        
        // TEMP
        this.addPlayer("JAMIE", Banner.GREEN, true);
        
        // TEMP
        this.addEntity(new EntityUnit("UNIT1", this, this.playerArray.get(0), "SETTLER", new Point(600, 600), new ClothUnit(1, 1, 1, 1), new Polygon(new int[]{0, 48, 48, 0}, new int[]{0, 0, 96, 96}, 4), null, Face.SE, true, true, true));
        this.addEntity(new EntityUnit("UNIT2", this, this.playerArray.get(0), "SETTLER", new Point(900, 620), new ClothUnit(1, 2, 1, 2), new Polygon(new int[]{0, 48, 48, 0}, new int[]{0, 0, 96, 96}, 4), null, Face.SE, true, true, true));
        
        // TEMP
        this.addEntity(new EntityNature("GOLD1", this, "GOLD MINE", new Point(700, 800), GFX.image("Nature/mine_gold1.png"), 100, 85, Select.DIAMOND, new Polygon(new int[]{0, 100, 200, 100}, new int[]{75, 0, 75, 150}, 4), null, Resource.GOLD, 500));
        this.addEntity(new EntityNature("TREE1", this, "TREE", new Point(200, 300), GFX.image("Nature/tree1.png").getSubimage(0, 0, 100, 200), 50, 185, Select.CIRCLE, new Polygon(new int[]{0, 100, 100, 0}, new int[]{0, 0, 200, 200}, 4), null, Resource.WOOD, 100));
        this.addEntity(new EntityNature("TREE2", this, "TREE", new Point(400, 900), GFX.image("Nature/tree1.png").getSubimage(100, 0, 100, 200), 50, 185, Select.CIRCLE, new Polygon(new int[]{0, 100, 100, 0}, new int[]{0, 0, 200, 200}, 4), null, Resource.WOOD, 100));
        
        // TEMP
        this.addEntity(new EntityConstruct("HOUSE1", this, this.playerArray.get(0), "HOUSE", new Point(800, 400), GFX.image("Constructs/house1.png"), 140, 200, new Polygon(new int[]{0, 140, 280, 280, 140, 0}, new int[]{100, 20, 100, 200, 280, 200}, 6), null));
        this.addEntity(new EntityConstruct("TOWNC1", this, this.playerArray.get(0), "TOWN CENTRE", new Point(1300, 600), GFX.image("Constructs/town_centre1.png"), 250, 550, new Polygon(new int[]{0, 120, 120, 250, 385, 400, 500, 500, 260, 0}, new int[]{300, 210, 130, 50, 130, 210, 300, 410, 555, 400}, 10), null));
    }
    
    public void addEntity(Entity entity)
    {
        this.entityArray.add(entity);
    }
    
    public void addPlayer(String name, Banner banner, boolean user)
    {
        Player player = new Player(this.playerArray.size(), name, banner);
        this.playerArray.add(player);
        if(user) {this.playerUser = player;}
    }
    
    private void commandEntity(Entity entity)
    {
        if(this.entitySelect.getType() == EntityType.CONSTRUCT)
        {
            //
        }
        if(this.entitySelect.getType() == EntityType.UNIT)
        {
            EntityUnit sourceUnit = (EntityUnit) this.entitySelect;
            if(entity.getType() == EntityType.CONSTRUCT)
            {
                EntityConstruct targetConstruct = (EntityConstruct) entity;
                if(targetConstruct.getPlayer() == sourceUnit.getPlayer())
                {
                    /*if(sourceUnit.isRepairmain() && !targetConstruct.getStat(StatType.CONDITION).isMax())
                    {
                        // NOTE: repair construct
                    }*/
                    /*if(targetConstruct.isGarrisonable())
                    {
                        // NOTE: garrison inside this construct
                    }*/
                }
            }
            else if(entity.getType() == EntityType.NATURE)
            {
                EntityNature targetNature = (EntityNature) entity;
                if(targetNature.isResource() && sourceUnit.isGatherer())
                {
                    System.out.println("SKIRMISH -> COMMAND ENTITY (GATHER)");
                    // NOTE: gather from the resource
                    sourceUnit.setAction(new ActionWalk(targetNature.getPosition(), new ActionGather(targetNature)));
                }
            }
            else if(entity.getType() == EntityType.UNIT)
            {
                EntityUnit targetUnit = (EntityUnit) entity;
                if(targetUnit.getPlayer() == sourceUnit.getPlayer())
                {
                    // NOTE: does the source unit have any abilities to use on an ally?
                }
            }
        }
    }
    
    private void commandPoint(Point point)
    {
        if(this.entitySelect.getType() == EntityType.CONSTRUCT)
        {
            EntityConstruct entity = (EntityConstruct) entitySelect;
            entity.setRallyPoint(point);
        }
        if(this.entitySelect.getType() == EntityType.UNIT)
        {
            EntityUnit entity = (EntityUnit) entitySelect;
            entity.setAction(new ActionWalk(point, null));
        }
    }
    
    private Entity getEntityAtPoint(Point point)
    {
        for(int x = 0; x < this.entityArray.size(); x++)
        {
            if(this.entityArray.get(x).getInteract().contains(point)) {return this.entityArray.get(x);}
        }
        return null;
    }
    
    public Entity getEntitySelect()
    {
        return this.entitySelect;
    }
    
    public Player getPlayerUser()
    {
        return this.playerUser;
    }
    
    public int getWorldOffsetX()
    {
        return this.state.getAreaWorld().x - this.worldOffsetX;
    }
    
    public int getWorldOffsetY()
    {
        return this.state.getAreaWorld().y - this.worldOffsetY;
    }
    
    public Point getWorldPoint(Point render)
    {
        return new Point(render.x - this.getWorldOffsetX(), render.y - this.getWorldOffsetY());
    }
    
    public Zoom getWorldZoom()
    {
        return this.worldZoom;
    }
    
    public void inputClickL(Point click)
    {
        Entity entity = getEntityAtPoint(click);
        if(entity != null) {this.selectEntity(entity);}
        else {this.selectClear();}
    }
    
    public void inputClickR(Point click)
    {
        if(this.entitySelect != null && this.entitySelect.getType() != EntityType.NATURE)
        {
            Entity entity = getEntityAtPoint(click);
            if(entity != null) {this.commandEntity(entity);}
            else {this.commandPoint(click);}
        }
    }
    
    public void inputKey(String key)
    {
        if(key.equals("DOWN"))
        {
            if(this.worldOffsetY < 1000)
            {
                this.worldOffsetY += 50;
            }
        }
        if(key.equals("LEFT"))
        {
            if(this.worldOffsetX > 0)
            {
                this.worldOffsetX -= 50;
            }
        }
        if(key.equals("RIGHT"))
        {
            if(this.worldOffsetX < 1000)
            {
                this.worldOffsetX += 50;
            }
        }
        if(key.equals("UP"))
        {
            if(this.worldOffsetY > 0)
            {
                this.worldOffsetY -= 50;
            }
        }
    }
    
    public void render(Graphics g)
    {
        this.renderTerrain(g);
        this.renderEntities(g);
        
        // TEMP
        //g.drawImage(Drawing.getImageFile("C:/Users/Jamie/Documents/My Workshop/Java/TKRTS/Projects/Kazaki/Nature/berry_bush1.png").getSubimage(0, 0, 50, 50), 300, 300, null);
    }
    
    private void renderEntities(Graphics g)
    {
        // NOTE: need to get an array of entities sorted with those highest up the board at the top
        for(int x = 0; x < this.entityArray.size(); x++)
        {
            this.entityArray.get(x).render(g);
        }
    }
    
    private void renderTerrain(Graphics g)
    {
        // NOTE: build a massive BufferedImage based on the background colour and all terrain images
        // draw a part of the image as the background
        // this.world ??
        
        // TEMP
        g.setColor(Colour.getColour("GRASS"));
        g.fillRect(0, 0, 1366, 768);
    }
    
    private void selectClear()
    {
        if(this.entitySelect != null) {this.entitySelect.setSelected(false);}
        this.entitySelect = null;
    }
    
    private void selectEntity(Entity entity)
    {
        this.selectClear();
        entity.setSelected(true);
        this.entitySelect = entity;
    }
    
    public void tick()
    {
        if(this.gamePause == false)
        {
            this.tickGame();
            this.tickEntities();
        }
    }
    
    private void tickEntities()
    {
        for(int x = 0; x < this.entityArray.size(); x++)
        {
            this.entityArray.get(x).update();
        }
    }
    
    private void tickGame()
    {
        // NOTE: consider game timer and suchlike
    }
    
}
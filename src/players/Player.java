package players;

import java.util.EnumMap;
import resources.Resource;
import resources.Stock;

public class Player
{
    // Player
    private int playerID;
    private String playerName;
    private Banner playerFlag;
    
    // Resources
    private EnumMap<Resource, Stock> stockpile;
    
    public Player(int id, String name, Banner flag)
    {
        // Player
        this.playerID = id;
        this.playerName = name;
        this.playerFlag = flag;
        
        // Resources
        this.stockpile = new EnumMap<Resource, Stock>(Resource.class);
        this.stockpile.put(Resource.ALLY, new Stock(0));
        this.stockpile.put(Resource.FOOD, new Stock(0));
        this.stockpile.put(Resource.GOLD, new Stock(0));
        this.stockpile.put(Resource.ROCK, new Stock(0));
        this.stockpile.put(Resource.WINE, new Stock(0));
        this.stockpile.put(Resource.WOOD, new Stock(0));
    }
    
    public int getCapacity(Resource resource)
    {
        // NOTE: calculate capacity based on cumulative construct storage capabiities
        return 1000;
    }
    
    public int getID()
    {
        return this.playerID;
    }
    
    public int getStock(Resource resource)
    {
        return this.stockpile.get(resource).value;
    }
    
}
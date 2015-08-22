package resources;

public class Stock
{
    public int value;
    
    public Stock(int amount)
    {
        this.value = amount;
    }
    
    public void add(int amount)
    {
        this.value += amount;
    }
    
    public void reduce(int amount)
    {
        this.value -= amount;
    }
    
}
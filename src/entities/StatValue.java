package entities;

public class StatValue
{
    public int value;
    private int max;
    
    public StatValue(int amount, int max)
    {
        this.value = amount;
        this.max = max;
    }
    
    public void add(int amount)
    {
        this.value += amount;
        if(this.value > this.max) {this.value = this.max;}
    }
    
    public boolean isMax()
    {
        if(this.value == this.max) {return true;}
        return false;
    }
    
    public void reduce(int amount)
    {
        this.value -= amount;
        if(this.value < 1) {this.value = 0;}
    }
    
}
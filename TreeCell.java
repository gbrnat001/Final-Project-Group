//Author: Jasiah Odell, Worked on by: 
public class TreeCell extends Cell{
    private static final int BURN_TIME = 15;

    //constructor 
    public TreeCell(int x, int y, Grid grid) {
        super(x, y, grid);
    }

    //update method 
    public void update() {
        
    }

    // returns the burn time for trees 
    public int getBurnTime() {
        return BURN_TIME;
    }
}

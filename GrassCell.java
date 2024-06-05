//Author: Jasiah Odell, Worked on by: 
//A cell with grass
public class GrassCell extends Cell{
    private static final int GROW_TIME = 10;
    private static final int BURN_TIME = 5;
    private int growTime;

    //constructor 
    public GrassCell(int x, int y, Grid grid){
        super(x, y, grid);
        growTime = GROW_TIME;
    }

    //updates the amount of time left before grass will become a bush
    public void update(){
        if (growTime > 0) {
            growTime--;
        } else {
            grid.setCell(new BushCell(x, y, grid));
        }
    }

    // returns the burn time for grass
    public int getBurnTime(){
        return BURN_TIME;
    }
}

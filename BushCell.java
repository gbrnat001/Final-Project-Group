//Author: Jasiah Odell, Worked on by: 
public class BushCell extends Cell {
    private static final int GROW_TIME = 20;
    private static final int BURN_TIME = 10;
    private int growTime;

    //constructor 
    public BushCell(int x, int y, Grid grid){
        super(x, y, grid);
        growTime = GROW_TIME;
    }

    //updates the amount of time left before a bush will become a tree 
    public void update(){
        if (growTime > 0) {
            growTime--;
        } else {
            grid.setCell(new TreeCell(x, y, grid));
        }
    }


    // returns the burn time for bushes
    public int getBurnTime(){
        return BURN_TIME;
    }
}

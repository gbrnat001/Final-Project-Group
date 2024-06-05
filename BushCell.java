
public class BushCell extends Cell {
    private static final int GROW_TIME = 25;
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
            grid.setCell(x, y, new TreeCell(x, y, grid));
        }
    }

    // returns the burn time for bushes
    public int getBurnTime(){
        return BURN_TIME;
    }

    // if plant catches fire 
    public void catchFire() {
        grid.setCell(x, y,new FireCell(x, y, grid, BURN_TIME));
    }
}

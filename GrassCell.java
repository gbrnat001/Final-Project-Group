
//A cell with grass
public class GrassCell extends Cell{
    private static final int GROW_TIME = 15;
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
            grid.setCell(x, y, new BushCell(x, y, grid));
        }
    }

    // returns the burn time for grass
    public int getBurnTime(){
        return BURN_TIME;
    }

    // if plant catches fire 
    public void catchFire() {
        grid.setCell(x, y,new FireCell(x, y, grid, BURN_TIME));
    }
}

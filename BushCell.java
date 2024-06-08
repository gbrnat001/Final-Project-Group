
public class BushCell extends Cell {
    private static final int GROW_TIME = 200;
    private static final int BURN_TIME = 10;
    private int growTime;

    //constructor 
    public BushCell(int x, int y, SimulationState simulation){
        super(x, y, simulation);
        growTime = GROW_TIME;
    }

    //updates the amount of time left before a bush will become a tree 
    public void update(){
        if (growTime > 0) {
            growTime--;
        } else {
            simulation.setCell(x, y, new TreeCell(x, y, simulation));
        }
    }

    // returns the burn time for bushes
    public int getBurnTime(){
        return BURN_TIME;
    }

    // if plant catches fire 
    public void catchFire() {
        simulation.setCell(x, y,new FireCell(x, y, simulation, BURN_TIME));
    }
}

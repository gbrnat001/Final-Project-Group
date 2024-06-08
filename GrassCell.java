
//A cell with grass
public class GrassCell extends Cell{
    private static final int GROW_TIME = 25;
    private static final int BURN_TIME = 2;
    private int growTime;

    //constructor 
    public GrassCell(int x, int y, SimulationState simulation){
        super(x, y, simulation);
        growTime = GROW_TIME;
    }

    //updates the amount of time left before grass will become a bush
    public void update(){
        if (growTime > 0) {
            growTime--;
        } else {
            simulation.setCell(x, y, new BushCell(x, y, simulation));
        }
    }

    // returns the burn time for grass
    public int getBurnTime(){
        return BURN_TIME;
    }

    // if plant catches fire 
    public void catchFire() {
        simulation.setCell(x, y,new FireCell(x, y, simulation, BURN_TIME));
    }
}

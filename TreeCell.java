
public class TreeCell extends Cell{
    private static final int BURN_TIME = 15;

    //constructor 
    public TreeCell(int x, int y, SimulationState simulation) {
        super(x, y, simulation);
    }

    //update method 
    public void update() {
        
    }

    // returns the burn time for trees 
    public int getBurnTime() {
        return BURN_TIME;
    }

    // if plant catches fire 
    public void catchFire() {
        simulation.setCell(x, y,new FireCell(x, y, simulation, BURN_TIME));
    }
}

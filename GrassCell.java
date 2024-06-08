//Jasiah, Nathan, Alex, Final Project 
//Class: GrassCell
//Purpose: Acts as grass in the GUI, has a burn time and the basic cell variables
/**
 * The GrassCell class represents a cell in the simulation that contains grass.
 */
public class GrassCell extends Cell{
    private static final int BURN_TIME = 7;

    /**
     * Constructs a new GrassCell object.
     *
     * @param x the x-coordinate of the cell
     * @param y the y-coordinate of the cell
     * @param simulation the simulation state
     */
    public GrassCell(int x, int y, SimulationState simulation){
        super(x, y, simulation);
    }

    /**
     * Returns the burn time for grass.
     *
     * @return the burn time for grass
     */
    public int getBurnTime(){
        return BURN_TIME;
    }

    /**
     * Sets the grass cell on fire.
     */
    public void catchFire() {
        simulation.setCell(x, y,new FireCell(x, y, simulation, BURN_TIME));
    }
}

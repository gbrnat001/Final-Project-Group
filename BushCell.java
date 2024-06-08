//Jasiah, Nathan, Alex, Final Project 
//Class: BushCell
//Purpose: Acts as a bush in the GUI, has a burn time and the basic cell variables
/**
 * the bushCell class represents a cell in the simulation that has a bush
 */
public class BushCell extends Cell {
    private static final int BURN_TIME = 10;
    /**
     * Constructs a new bushCell object
     * @param x the x coordinate of the cell
     * @param y the y coordinate of the cell
     * @param simulation the simulation state
     */
    public BushCell(int x, int y, SimulationState simulation){
        super(x, y, simulation);
    }

    /**
     * updates the amount of time left before a bush will become a tree
     * @return the burn Time
     */
    public int getBurnTime(){
        return BURN_TIME;
    }

    /**
     * Sets the bush cell on fire
     */
    public void catchFire() {
        simulation.setCell(x, y,new FireCell(x, y, simulation, BURN_TIME));
    }
}

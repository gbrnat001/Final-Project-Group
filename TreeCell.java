//Jasiah, Nathan, Alex, Final Project 
//Class: TreeCell
//Purpose: Acts as a tree in the GUI, has a burn time and the basic cell variables
/**
 * The TreeCell class represents a cell in the simulation that contains a tree
 */
public class TreeCell extends Cell{
    private static final int BURN_TIME = 12;

    /**
     * Constructs a new TreeCell object.
     *
     * @param x the x coordinate of the cell
     * @param y the y coordinate of the cell
     * @param simulation the simulation state
     */
    public TreeCell(int x, int y, SimulationState simulation) {
        super(x, y, simulation);
    }

    /**
     * Returns the burn time for trees
     * @return the burn time for trees
     */
    public int getBurnTime() {
        return BURN_TIME;
    }

    /**
     * Sets the cell on fire
     */
    public void catchFire() {
        simulation.setCell(x, y,new FireCell(x, y, simulation, BURN_TIME));
    }
}

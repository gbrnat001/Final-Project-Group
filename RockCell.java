//Jasiah, Nathan, Alex, Final Project 
//Class: RockCell
//Purpose: Acts as a rock in the GUI, has the basic cell variables
/**
 * the RockCell class represents a rock within the simulation that is not flammable
 */
public class RockCell extends Cell{
    /**
     * Constructs a rock object
     * @param x the x coordinate
     * @param y the y coordinate
     * @param simulation the simulation state
     */
    public RockCell(int x, int y, SimulationState simulation){
        super(x, y, simulation);
    }
}

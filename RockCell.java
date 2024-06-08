//Jasiah, Nathan, Alex, Final Project 
//Class: RockCell
//Puropse: Acts as a rock in the GUI, has the basic cell variables

/**
 * the RockCell class represents a rock within the simulation that is not flammable
 */
public class RockCell extends Cell{
    /**
     * Constructs a rock object
     * @param x
     * @param y
     * @param simulation
     */
    public RockCell(int x, int y, SimulationState simulation){
        super(x, y, simulation);
    }
}

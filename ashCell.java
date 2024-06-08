//Authors: Jasiah, Nathan, Alex; Final Project 
//Class: ashCell
/**
 * the ashCell represents a cell in the simulation made out of ash
 */
public class ashCell extends FireCell{
    private int recoverTime;

    /**
     * Constructs a new ashCell object.
     * @param x the x coordinate of the cell
     * @param y the y coordinate of the cell
     * @param simulation the simulation state
     */
    public ashCell(int x, int y, SimulationState simulation){
        super(x, y, simulation, 0);
        recoverTime = 1000;
    }

    /**
     * Updates the state of the ash cell.
     * Checks to see if it should become grass again
     */
    public void update(){
        if(recoverTime > 0){
            recoverTime--;
        } else {
            simulation.setCell(x, y, new GrassCell(x, y, simulation));
        }
    }
}

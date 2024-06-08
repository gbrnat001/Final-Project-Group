//Jasiah, Nathan, Alex, Final Project 
//Class: TreeCell
//Puropse: Acts as a tree in the GUI, has a burn time and the basic cell variables

public class TreeCell extends Cell{
    private static final int BURN_TIME = 5;

    //constructor 
    public TreeCell(int x, int y, SimulationState simulation) {
        super(x, y, simulation);
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

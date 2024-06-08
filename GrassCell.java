//Jasiah, Nathan, Alex, Final Project 
//Class: GrassCell
//Puropse: Acts as grass in the GUI, has a burn time and the basic cell variables

public class GrassCell extends Cell{
    private static final int BURN_TIME = 7;

    //constructor 
    public GrassCell(int x, int y, SimulationState simulation){
        super(x, y, simulation);
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

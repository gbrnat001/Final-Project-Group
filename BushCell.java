//Jasiah, Nathan, Alex, Final Project 
//Class: BushCell
//Puropse: Acts as a bush in the GUI, has a burn time and the basic cell variables 

public class BushCell extends Cell {
    private static final int BURN_TIME = 10;
    //constructor 
    public BushCell(int x, int y, SimulationState simulation){
        super(x, y, simulation);
    }

    // returns the burn time for bushes
    public int getBurnTime(){
        return BURN_TIME;
    }

    // if plant catches fire 
    public void catchFire() {
        simulation.setCell(x, y,new FireCell(x, y, simulation, BURN_TIME));
    }
}

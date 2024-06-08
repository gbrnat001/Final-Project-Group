//Authors: Jasiah, Nathan, Alex; Final Project 
//Class: ashCell
//Purpose: makes the up cell left over from the vegetation cell set on fire 
public class ashCell extends FireCell{
    private int recoverTime;

    //constructor
    public ashCell(int x, int y, SimulationState simulation){
        super(x, y, simulation, 0);
        recoverTime = 200;
    }

    //checks if the time it takes for the ash to recover into grass has passed
    public void update(){
        if(recoverTime > 0){
            recoverTime--;
        } else {
            simulation.setCell(x, y, new GrassCell(x, y, simulation));
        }
    }
}

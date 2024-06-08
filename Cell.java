//Authors: Jasiah, Nathan, Alex, Final Project 
//Class: Cell
//Puropse: Creates the basic cell in the grid, with x and y coordinates

public class Cell{
  protected int x;
  protected int y;
  protected SimulationState simulation;

  //default empty constructor 
  public Cell(){
  }

  //constructor with the coordinates and grid reference
  public Cell(int x, int y, SimulationState simulation){
    this.x = x;
    this.y = y;
    this.simulation = simulation;
  }

  //gets x coordinates 
  public int getX(){
    return x;
  }

  //gets y coordinates 
  public int getY(){
    return y;
  }

  //empty update method that will be overridden the subclasses
  public void update(){

  }
}

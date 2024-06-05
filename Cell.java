//Author: , Worked on by: Jasiah Odell
//Creates the basic cell in the grid, with x and y coordinates 

public class Cell{
  protected int x;
  protected int y;
  protected Grid grid;

  //default empty constructor 
  public Cell(){
  }

  //construsctor with the coordinates and grid reference 
  public Cell(int x, int y, Grid grid){
    this.x = x;
    this.y = y;
    this.grid = grid;
  }

  //gets x coordinates 
  public int getX(){
    return x;
  }

  //gets y coordinates 
  public int getY(){
    return y;
  }

  //empty update method that will be overriden the the subclasses 
  public void update(){

  }
}

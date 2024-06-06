
//Creates the basic cell in the grid, with x and y coordinates 

public class Cell{
  protected int x;
  protected int y;
  protected Grid grid;
  protected boolean isBurning;
  protected boolean isBurned;
  public int dryness;

  //default empty constructor 
  public Cell(){
  }

  //constructor with the coordinates and grid reference
  public Cell(int x, int y, Grid grid){
    this.x = x;
    this.y = y;
    this.grid = grid;
    this.isBurning = false;
    this.isBurned = false;
    this.dryness = 50;
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

  public int getDryness(){
    return dryness;
  }

  public boolean isBurning() {
    return isBurning;
  }

  public boolean isBurned(){
    return  isBurned;
  }
}

//Authors: Jasiah, Nathan, Alex, Final Project 
//Class: Cell
//Purpose: Creates the basic cell in the grid, with x and y coordinates

/**
 * The Cell class represents a basic cell in the grid, with x and y coordinates.
 */
public class Cell{
  protected int x;
  protected int y;
  protected SimulationState simulation;

  /**
   * Default empty constructor.
   */
  public Cell(){
  }

  /**
   * Constructs a new Cell object with the specified coordinates and simulation state.
   *
   * @param x the x-coordinate of the cell
   * @param y the y-coordinate of the cell
   * @param simulation the simulation state
   */
  public Cell(int x, int y, SimulationState simulation){
    this.x = x;
    this.y = y;
    this.simulation = simulation;
  }

  /**
   * Returns the x-coordinate of the cell.
   *
   * @return the x-coordinate of the cell
   */
  public int getX(){
    return x;
  }

  /**
   * Returns the y-coordinate of the cell.
   *
   * @return the y-coordinate of the cell
   */
  public int getY(){
    return y;
  }

  /**
   * Updates the state of the cell. This method is supposed to be overridden by subclasses.
   */
  public void update(){

  }
}

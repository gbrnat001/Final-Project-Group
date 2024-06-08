//Jasiah, Nathan, Alex, Final Project 
//Class: EmptyCell
//Purpose: An empty cell, has basic cell variables and function
/**
 * The Cell class represents a basic cell in the grid, with x and y coordinates.
 * It is the superclass for various types of cells in the simulation
 */
public class emptyCell extends Cell {
    /**
     * Constructs a new emptyCell object
     * @param x  the x coordinate of the cell
     * @param y the y coordinate of the cell
     * @param simulation the simulation state
     */
    public emptyCell(int x, int y, SimulationState simulation) {
        super(x, y, simulation);
    }
}
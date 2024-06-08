//Jasiah, Nathan, Alex, Final Project 
//Class: SimulationState
//Puropse: Initalizes the GUI, and contains all the get methods for the entered values
// Adds the vegetations, fires, and checks the bounds of the grid

import java.util.Random;
/**
 * The SimulationState class represents the state of the fire simulation.
 * It maintains and changed the grid structure of cells.
 */
public class SimulationState {
    private final int gridSize;
    private final int windSpeed;
    private final String windDirection;
    private final int dryness;
    private final int humidity;
    private final int numFires;
    private Cell[][] grid;
    private final Random rand;

    /** constructor for SimulationState, initializes grid properties
     *
     * @param gridSize the size of the grid
     * @param windSpeed the speed of the wind
     * @param windDirection the direction of the wind in the simulation
     * @param dryness the dryness level within the environment
     * @param humidity the humidity percent of the environment
     * @param numFires the initial number of fires
     */
    public SimulationState(int gridSize, int windSpeed, String windDirection, int dryness, int humidity, int numFires) {
        this.gridSize = gridSize;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection.toUpperCase();
        this.dryness = dryness;
        this.humidity = humidity;
        this.numFires = numFires;
        this.rand = new Random();
        initializeGrid();
    }

    /**
     * Initializes the grid and adds grass and fires to it
     */
    private void initializeGrid() {
        grid = new Cell[gridSize][gridSize];
        addVegetation();
        addFire();
    }

    /**
     * Fills the grid with random amounts of grass, bushes and trees cells
     */
    private void addVegetation() {
        // Initialize the grid with grass, trees, and bushes randomly placed
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                double randVal = rand.nextDouble();
                if (randVal < 0.85) {
                    grid[i][j] = new GrassCell(i, j, this);
                } else if (randVal < 0.92) {
                    grid[i][j] = new BushCell(i, j, this);
                } else if (randVal < 0.97){
                    grid[i][j] = new TreeCell(i, j, this);
                } else {
                    grid[i][j] = new RockCell(i, j, this);
                }
            }
        }
    }

    /**
     * Fills the grid with the specified number of fires in random cells
     */
    private void addFire() {
        for (int i = 0; i < numFires; i++) {
            int x = rand.nextInt(gridSize);
            int y = rand.nextInt(gridSize);
            grid[x][y] = new FireCell(x, y, this, 10);
        }
    }

    /**
     * Checks if the given coordinates for the cell are within
     * The bounds of the grid
     * @param x the x coordinate of the cell
     * @param y the y coordinate of the cell
     * @return true if its in bounds, false if it's not
     */
    public boolean inBounds(int x, int y) {
        return x >= 0 && x < gridSize && y >= 0 && y < gridSize;
    }

    /**
     * Returns the cell at the specified coordinate
     * @param x the x coordinate of the cell
     * @param y the y coordinate of the cell
     * @return the Cell at the specified coordinates or null if out of bounds
     */
    public Cell getCell(int x, int y) {
        if (inBounds(x, y)) {
            return grid[x][y];
        }
        return null;
    }

    /**
     * Sets the cell at the specified coordinate to become
     * The given parameter
     * @param x the x coordinate of the cell
     * @param y the y coordinate of the cell
     * @param cell the cell that cell at the coordinate will be set to
     */
    public void setCell(int x, int y, Cell cell) {
        grid[x][y] = cell;
    }

    /**
     * Sets the entire grid to become the given parameter
     * @param newGrid the grid that is being copied
     */
    public void setGrid(Cell[][] newGrid){
        this.grid = newGrid;
    }
    /**
     * Returns the humidity percent
     * @return humidity percent
     */
    public int getHumidity() {
        return humidity;
    }

    /**
     * Returns the dryness level
     * @return the dryness level
     */
    public int getDryness() {
        return dryness;
    }

    /**
     * Returns the wind speed
     * @return the wind speed
     */
    public int getWindSpeed() {
        return windSpeed;
    }

    /**
     * Returns the wind direction
     * @return wind direction
     */
    public String getWindDirection() {
        return windDirection;
    }

    /**
     * Returns the grid
     * @return the grid
     */
    public Cell[][] getGrid() {
        return grid;
    }

    /**
     * The random digit in the random class's list
     * @return random seed
     */
    public Random getRand() {
        return rand;
    }
}


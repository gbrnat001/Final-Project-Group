//Jasiah, Nathan, Alex, Final Project 
//Class: SimulationState
//Puropse: Initalizes the GUI, and contains all the get methods for the entered values
// Adds the vegetations, fires, and checks the bounds of the grid

import java.util.Random;

public class SimulationState {
    private final int gridSize;
    private final int windSpeed;
    private final String windDirection;
    private final int dryness;
    private final int humidity;
    private final int numFires;
    private Cell[][] grid;
    private final Random rand;

    public SimulationState(int gridSize, int windSpeed, String windDirection, int dryness, int humidity, int numFires) {
        this.gridSize = gridSize;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection.toUpperCase(); // Ensure wind direction is uppercase
        this.dryness = dryness;
        this.humidity = humidity;
        this.numFires = numFires;
        this.rand = new Random();
        initializeGrid();
    }

    private void initializeGrid() {
        grid = new Cell[gridSize][gridSize];
        addVegetation();
        addFire();
    }

    private void addVegetation() {
        // Initialize the grid with grass, trees, and bushes randomly placed
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                double randVal = rand.nextDouble();
                if (randVal < 0.7) {
                    grid[i][j] = new GrassCell(i, j, this);
                } else if (randVal < 0.85) {
                    grid[i][j] = new BushCell(i, j, this);
                } else {
                    grid[i][j] = new TreeCell(i, j, this);
                }
            }
        }
    }

    private void addFire() {
        // Randomly place initial fires
        for (int i = 0; i < numFires; i++) {
            int x = rand.nextInt(gridSize);
            int y = rand.nextInt(gridSize);
            grid[x][y] = new FireCell(x, y, this, 10);
        }
    }

    public boolean inBounds(int x, int y) {
        return x >= 0 && x < gridSize && y >= 0 && y < gridSize;
    }

    public Cell getCell(int x, int y) {
        if (inBounds(x, y)) {
            return grid[x][y];
        }
        return null;
    }

    public void setCell(int x, int y, Cell cell) {
        grid[x][y] = cell;
    }

    public int getHumidity() {
        return humidity;
    }

    public int getDryness() {
        return dryness;
    }

    public int getWindSpeed() {
        return windSpeed;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public Cell[][] getGrid() {
        return grid;
    }

    public Random getRand() {
        return rand;
    }
}


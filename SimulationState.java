import javax.swing.*;
import java.util.Random;

public class SimulationState {
    private final int gridSize;
    private int windSpeed;
    private final int dryness;
    private final int humidity;
    private final int numFires;
    private JFrame window;
    private Cell[][] grid;
    private JPanel[][] gridPanel;
    private final Random rand;

    //constructor for Grid, initializes grid properties and creates the grid
    public SimulationState(int gridSize, int windSpeed, int dryness, int humidity, int numFires) {
        this.gridSize = gridSize;
        this.windSpeed = windSpeed;
        this.dryness = dryness;
        this.humidity = humidity;
        this.numFires = numFires;
        this.rand = new Random();
        initializeGrid();
    }

    private void initializeGrid(){
        grid = new Cell[gridSize][gridSize];
        addGrass();
        addFire();
    }

    //fill the grid with empty cells and set the panel background colors
    private void addGrass(){
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                grid[i][j] = new GrassCell(i, j, this);
            }
        }
    }

    //Randomly place initial fires
    private void addFire(){
        for (int i = 0; i < numFires; i++) {
            int x = rand.nextInt(gridSize);
            int y = rand.nextInt(gridSize);
            grid[x][y] = new FireCell(x, y, this, 10);
        }
    }

    public boolean inBounds(int x, int y){
        if(x >= 0 && x < gridSize && y >= 0 &&  y < gridSize){
            return true;
        }
        return false;
    }

    public Cell getCell(int x, int y){
        if(inBounds(x, y)){
            return grid[x][y];
        }
        return null;
    }

    public void setCell(int x, int y, Cell cell){
        grid[x][y] = cell;
    }

    //get the humidity
    public int getHumidity() {

        return humidity;
    }

    // get the dryness
    public int dryness(){
        return dryness;
    }

    //get the wind speed
    public int getWindSpeed() {

        return windSpeed;
    }

    public Cell[][] getGrid(){
        return grid;
    }
}


//Jasiah, Nathan, Alex. Project 2
import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Grid {
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
    public Grid(int gridSize, int windSpeed, int dryness, int humidity, int numFires) {
        this.gridSize = gridSize;
        this.windSpeed = windSpeed;
        this.dryness = dryness;
        this.humidity = humidity;
        this.numFires = numFires;
        this.rand = new Random();
        makeGrid();
        startSimulation();
    }

    //sets up the grid with empty cells and some initial fires and plants 
    private void makeGrid() {
        int gap = 2;

        window = new JFrame();
        window.setSize(gridSize * 50, gridSize * 50);
        window.setLayout(new GridLayout(gridSize, gridSize, gap, gap));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        grid = new Cell[gridSize][gridSize];
        gridPanel = new JPanel[gridSize][gridSize];

        addGrass();
        addFire();
        window.setVisible(true);
    }

    //fill the grid with empty cells and set the panel background colors
    private void addGrass(){
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                grid[i][j] = new GrassCell(i, j, this); // Grass cell creation
                gridPanel[i][j] = new JPanel();
                window.add(gridPanel[i][j]);
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

    //update on each cell and refresh the display colors
    public void update() {
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                grid[i][j].update();
                gridPanel[i][j].setBackground(getCellColor(grid[i][j]));
            }
        }
    }

    //starts the simulation with a timer to update the grid
    private void startSimulation() {
        Timer timer = new Timer();
        timer.schedule(new MyTimer(), 0, 500);
    }

    //color for each cell type
    private Color getCellColor(Cell cell) {
        if (cell instanceof emptyCell) {
            return Color.LIGHT_GRAY;
        } else if (cell instanceof GrassCell) {
            return new Color(144, 238, 144);
        } else if (cell instanceof BushCell) {
            return new Color(34, 139, 34);
        } else if (cell instanceof TreeCell) {
            return new Color(0, 100, 0);
        } else if (cell instanceof ashCell) {
            return new Color(105, 105, 105);
        } else if (cell instanceof FireCell) {
            return new Color(255, 69, 9);
        } else {
            return Color.WHITE;
        }
    }

    //gets cell at the specified coordinates, returns null if out of bounds
    public Cell getCell(int x, int y) {
        if (x >= 0 && x < gridSize && y >= 0 && y < gridSize) {
            return grid[x][y];
        } else {
            return null;
        }
    }

    //sets the cell at the specified coordinates
    public void setCell(int x, int y, Cell cell) {
        grid[x][y] = cell;
        gridPanel[x][y].setBackground(getCellColor(cell));
    }

    private double calculateIgnitionChance(int humidity, int windSpeed, int dryness) {
        double humidityDouble = (100.0 - humidity) / 100.0;
        double windDouble = windSpeed / 10.0;
        double drynessDouble = dryness / 100.0;

        return humidityDouble * windDouble * drynessDouble;
    }

    //get the burn time 
    public int getBurnTime() {
        return 1;
    }

    //get the wind speed 
    public int getWindSpeed() {

        return windSpeed;
    }

    //get the humidity 
    public int getHumidity() {

        return humidity;
    }

    // get the dryness
    public int dryness(){
        return dryness;
    }

    //timer tasks to periodically update the grid
    private class MyTimer extends TimerTask {
        public void run() {
            if (window.isVisible()) {
                update();
            }
        }
    }

    //main method for testing the Grid class
    public static void main(String[] args) {
        Grid grid = new Grid(30, 1, 3, 40, 3);
        grid.update();
    }

}



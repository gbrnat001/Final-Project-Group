
import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Grid {
    private int gridSize;
    private int windSpeed;
    private int windDirection;
    private int humidity;
    private int numFires;
    private JFrame window;
    private Cell[][] grid;
    private JPanel[][] gridPanel;
    private final int gap = 2;
    private Timer timer;
    private Random rand;

    //constructor for Grid, initializes grid properties and creates the grid
    public Grid(int gridSize, int windSpeed, int windDirection, int humidity, int numFires) {
        this.gridSize = gridSize;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.humidity = humidity;
        this.numFires = numFires;
        this.rand = new Random();
        makeGrid();
        startSimulation();
    }

    //sets up the grid with empty cells and some initial fires and plants 
    private void makeGrid() {
        window = new JFrame();
        window.setSize(gridSize * 50, gridSize * 50);
        window.setLayout(new GridLayout(gridSize, gridSize, gap, gap));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        grid = new Cell[gridSize][gridSize];
        gridPanel = new JPanel[gridSize][gridSize];

        //fill the grid with empty cells and set the panel background colors
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                grid[i][j] = new GrassCell(i, j, this); // Grass cell creation
                gridPanel[i][j] = new JPanel();
                window.add(gridPanel[i][j]);
            }
        }

        //randomly place initial fires
        for (int i = 0; i < numFires; i++) {
            int x = rand.nextInt(gridSize);
            int y = rand.nextInt(gridSize);
            grid[x][y] = new FireCell(x, y, this, 10);
        }

        window.setVisible(true);
    }

    //uupdate on each cell and refresh the display colors
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
        timer = new Timer();
        timer.schedule(new MyTimer(), 0, 500);
    }

    //color for each cell type
    private Color getCellColor(Cell cell) {
        if (cell instanceof emptyCell) {
            return Color.LIGHT_GRAY;
        } else if (cell instanceof GrassCell) {
            return Color.GREEN;
        } else if (cell instanceof BushCell) {
            return Color.ORANGE;
        } else if (cell instanceof TreeCell) {
            return Color.PINK;
        } else if (cell instanceof ashCell) {
            return Color.BLACK;
        } else if (cell instanceof FireCell) {
            return Color.RED;
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

    //get the burn time 
    public int getBurnTime() {
        return 1;
    }

    //get the wind direction 
    public int windDirection() {

        return 1;
    }

    //get the wind speed 
    public int getWindSpeed() {

        return 1;
    }

    //get the humidity 
    public int getHumidity() {

        return 100;
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
        Grid grid = new Grid(30, 5, 3, 40, 3);
        grid.update();
    }

}



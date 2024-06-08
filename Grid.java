//Jasiah, Nathan, Alex, Final Project 
//Class: Grid
//Puropse: The container for all of the cell classes, makes the grid for the GUI,
//updates the grid with the timer, and intializes all of the cells with the right color
import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Grid {
    private final int gridSize;
    private SimulationState simulation;
    private JPanel gridContainer;
    private JPanel[][] gridPanel;

    //constructor
    public Grid(int gridSize, int windSpeed, String windDirection, int dryness, int humidity, int numFires) {
        this.gridSize = gridSize;
        this.simulation = new SimulationState(gridSize, windSpeed, windDirection, dryness, humidity, numFires);
        makeGrid();
        startSimulation();
    }

    //makes the grid, and enters the correct colors
    private void makeGrid() {
        int gap = 0;

        gridContainer = new JPanel(new GridLayout(gridSize, gridSize, gap, gap));
        gridPanel = new JPanel[gridSize][gridSize];
        Cell[][] grid = simulation.getGrid();

        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                gridPanel[i][j] = new JPanel();
                gridPanel[i][j].setBackground(getCellColor(grid[i][j]));
                gridContainer.add(gridPanel[i][j]);
            }
        }
    }

    //gets the container 
    public JPanel getGridContainer() {
        return gridContainer;
    }

    //updates each cell accoriding to the timer
    public void update() {
        Cell[][] grid = simulation.getGrid();
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                grid[i][j].update();
                gridPanel[i][j].setBackground(getCellColor(grid[i][j]));
            }
        }
    }

    //starts the simulation
    private void startSimulation() {
        Timer timer = new Timer();
        timer.schedule(new MyTimer(), 0, 500);
    }

    //returns the right color for each cell 
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
            return new Color(139, 69, 19);
        } else if (cell instanceof FireCell) {
            return new Color(255, 69, 0);
        } else if (cell instanceof RockCell) {
            return Color.LIGHT_GRAY;
        } else {
            return Color.WHITE;
        }
    }

    //the timer that calls the update 
    private class MyTimer extends TimerTask {
        public void run() {
            update();
        }
    }
}
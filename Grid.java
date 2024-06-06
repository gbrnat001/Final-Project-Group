//Jasiah, Nathan, Alex. Project 2
import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Grid {
    private final int gridSize;
    private SimulationState simulation;
    private JFrame window;
    private JPanel[][] gridPanel;

    //constructor for Grid, initializes grid properties and creates the grid
    public Grid(int gridSize, int windSpeed, int dryness, int humidity, int numFires) {
        this.gridSize = gridSize;
        this.simulation = new SimulationState(gridSize, windSpeed, dryness, humidity, numFires);
        makeGrid();
        startSimulation();
    }

    //sets up the grid with empty cells and some initial fires and plants 
    private void makeGrid() {
        int gap = 2;

        window = new JFrame();
        window.setSize(gridSize * 50, gridSize * 50);
        window.setLayout(new BorderLayout());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel gridContainer = new JPanel(new GridLayout(gridSize, gridSize, gap, gap));
        window.add(gridContainer, BorderLayout.CENTER);

        gridPanel = new JPanel[gridSize][gridSize];
        Cell[][] grid = simulation.getGrid();

        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                gridPanel[i][j] = new JPanel();
                gridPanel[i][j].setBackground(getCellColor(grid[i][j]));
                gridContainer.add(gridPanel[i][j]);
            }
        }

        window.setVisible(true);
    }

    //update on each cell and refresh the display colors
    public void update() {
        Cell[][] grid = simulation.getGrid();
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


    //get the burn time 
    public int getBurnTime() {
        return 1;
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



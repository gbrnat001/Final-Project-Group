//Jasiah, Nathan, Alex. Project 2
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

public class Grid {
    private final int gridSize;
    private final SimulationState simulation;
    private JFrame window;
    private BufferedImage img;
    private JLabel label;
    private Graphics g;
    private final int cellSize = 10;
    private Cell[][] grid;

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
        window.setSize(gridSize*cellSize, gridSize*cellSize);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        img = new BufferedImage(gridSize*cellSize, gridSize*cellSize, BufferedImage.TYPE_INT_BGR);
        label = new JLabel(new ImageIcon(img));
        window.add(label);

        Cell[][] grid = simulation.getGrid();
        g = img.getGraphics();

        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                g.setColor(getCellColor(grid[i][j]));
                g.fillRect(i*cellSize, j*cellSize, 1, 1);
            }
        }

        window.setVisible(true);
    }

    //update on each cell and refresh the display colors
    public void update() {
        grid = simulation.getGrid();
        g = img.getGraphics();

        Cell[][] newGrid = new Cell[gridSize][gridSize];

        for(int i = 0; i < gridSize; i++){
            for(int j = 0; j < gridSize; j++){
                grid[i][j].update();
                newGrid[i][j] = grid[i][j];
            }
        }
        //Replace the old grid with the new updated Grid
        simulation.setGrid(newGrid);

        //Go through and get the colors and color the canvas
        replaceUpdatedGrid();
        label.repaint();
    }

    private void replaceUpdatedGrid(){
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                grid[i][j].update();
                g.setColor(getCellColor(grid[i][j]));
                g.fillRect(i*cellSize, j*cellSize, cellSize, cellSize);
            }
        }
    }

    //starts the simulation with a timer to update the grid
    private void startSimulation() {
        Timer timer = new Timer();
        timer.schedule(new MyTimer(), 0, 100);
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
        Grid grid = new Grid(100, 3, 3, 40, 3);
    }

}



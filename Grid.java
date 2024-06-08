//Jasiah, Nathan, Alex, Final Project 
//Class: Grid
// Purpose: The container for all of the cell classes, makes the grid for the GUI,
// updates the grid with the timer, and initializes  the cells with the right color
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The Grid class represents the container for all the cell classes.
 * It initializes and updates the grid for the GUI.
 */
public class Grid {
    private final int gridSize;
    private final SimulationState simulation;
    private BufferedImage img;
    private JLabel label;
    private Graphics g;
    private final int cellSize = 10;
    private Cell[][] grid;
    private JPanel gridContainer;

    /**
     * Constructs a new Grid object with some parameters
     * @param gridSize the size of the grid
     * @param windSpeed, the speed of the wind
     * @param windDirection the direction of the wind in the simulation
     * @param dryness the dryness level within the environment
     * @param humidity the humidity percent of the environment
     * @param numFires the initial number of fires
     */
    public Grid(int gridSize, int windSpeed, String windDirection, int dryness, int humidity, int numFires) {
        this.gridSize = gridSize;
        this.simulation = new SimulationState(gridSize, windSpeed, windDirection, dryness, humidity, numFires);
        makeGrid();
        startSimulation();
    }

    /**
     * Makes the grid and enters the correct colors.
     */
    private void makeGrid() {
        gridContainer = new JPanel();
        gridContainer.setPreferredSize(new Dimension(gridSize * cellSize, gridSize * cellSize));

        img = new BufferedImage(gridSize * cellSize, gridSize * cellSize, BufferedImage.TYPE_INT_BGR);
        label = new JLabel(new ImageIcon(img));
        gridContainer.add(label);

        Cell[][] grid = simulation.getGrid();
        g = img.getGraphics();

        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                g.setColor(getCellColor(grid[i][j]));
                g.fillRect(i * cellSize, j * cellSize, cellSize, cellSize);
            }
        }
    }

    /**
     * Gets the container.
     *
     * @return the JPanel containing the grid
     */
    public JPanel getGridContainer() {
        return gridContainer;
    }

    /**
     * Updates each cell according to the timer.
     */
    public void update() {
        grid = simulation.getGrid();
        g = img.getGraphics();

        Cell[][] newGrid = new Cell[gridSize][gridSize];

        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                grid[i][j].update();
                newGrid[i][j] = grid[i][j];
            }
        }
        // Replace the old grid with the new updated Grid
        simulation.setGrid(newGrid);

        // Go through and get the colors and color the canvas
        replaceUpdatedGrid();
        label.repaint();
    }

    /**
     * Replaces the updated grid with the new colors.
     */
    private void replaceUpdatedGrid() {
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                grid[i][j].update();
                g.setColor(getCellColor(grid[i][j]));
                g.fillRect(i * cellSize, j * cellSize, cellSize, cellSize);
            }
        }
    }

    /**
     * Starts the simulation.
     */
    private void startSimulation() {
        Timer timer = new Timer();
        timer.schedule(new MyTimer(), 0, 100);
    }

    /**
     * Returns the right color for each cell.
     *
     * @param cell the cell to get the color for
     * @return the color of the cell
     */
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

    /**
     * The timer that calls the update.
     */
    private class MyTimer extends TimerTask {
        public void run() {
            update();
        }
    }
}

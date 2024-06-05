import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.Timer;


public class Grid {

    private int gridSize;
    private int windSpeed;
    private int windDirection;
    private int humidity;
    private int numFires;
    private int numVegetation;
    private JFrame window;
    private Cell[][] grid;
    private JPanel gridPanel[][];
    private final int gap = 2;

    public Grid(int gridSize, int windSpeed, int windDirection, int humidity, int numFires, int numVegetation){
        this.gridSize = gridSize;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.humidity = humidity;
        this.numFires = numFires;
        this.numVegetation = numVegetation;
        makeGrid();
    }
    private void makeGrid(){
        window = new JFrame();

        window.setSize(gridSize*50, gridSize*50);
        window.setLayout(new GridLayout(gridSize, gridSize, gap, gap));

        grid = new Cell[gridSize][gridSize];
        gridPanel = new JPanel[gridSize][gridSize];

        for(int i = 0; i < gridSize; i++){
            for(int j = 0; j < gridSize; j++){
                grid[i][j] = new emptyCell(i, j);
                gridPanel[i][j] = new JPanel();
                gridPanel[i][j].setBackground(new Color(65, 152, 10));
                window.add(gridPanel[i][j]);
            }
        }

        Random rand = new Random();
        for(int i = 0; i < numFires; i++){
            int x = rand.nextInt(gridSize);
            int y = rand.nextInt(gridSize);
            grid[x][y] = new fireCell(x, y, windSpeed, 10);
            gridPanel[x][y].setBackground(new Color(230, 0, 0, 255));
        }
        window.setVisible(true);
    }

    public void update(){

    }

    private void readGrid(){
        window.setVisible(true);
    }
    public Cell getCell(int x, int y){
        return grid[x][y];
    }

    //We can use instances of a class to call this function as a parameter
    public void setCell(int x, int y, Cell cell){

    }

    public int getBurnTime(){

        return 1;
    }

    public int windDirection(){

        return 1;
    }

    public int getWindSpeed(){

        return 1;
    }

    public int getHumidity(){

        return 100;
    }

    private class MyTimer extends TimerTask{
        public void run() {
            if(window.isVisible()){
                update();
            }
        }
    }

    //Testing ground
    public static void main(String[] args) {
        Grid grid = new Grid(15, 5, 3, 40, 4, 5);
        grid.update();
    }
}


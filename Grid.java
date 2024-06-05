//Author: , Worked on by: Jasiah Odell
public class Grid {
    private Cell[][] grid;
    private int width;
    private int height;

    //constructor to initialize the grid
    public Grid(int width, int height) {
        this.width = width;
        this.height = height;
        grid = new Cell[width][height];
    }

    //create the initial grid 
    public void makeGrid() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                grid[x][y] = new emptyCell(x, y, this); 
            }
        }
    }

    //update the grid 
    public void update() {
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[x].length; y++) {
                if (grid[x][y] != null) {
                    grid[x][y].update();
                }
            }
        }
    }

    //get the cell at the specified coordinates
    public Cell getCell(int x, int y) {
        if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length) {
            return grid[x][y];
        } else {
            return null; 
        }
    }

    //set a cell at the specified coordinates
    public void setCell(Cell cell) {
        grid[cell.getX()][cell.getY()] = cell;
    }

    //get width
    public int getWidth() {
        return width;
    }

    //get height 
    public int getHeight() {
        return height;
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
}



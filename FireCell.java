
// Fire cell that 
public class FireCell extends Cell{
    private int burnTime;
    private int spreadDelay;

    //constructor
    public FireCell(int x, int y, Grid grid, int burnTime) {
        super(x, y, grid);
        this.burnTime = burnTime;
        this.spreadDelay = burnTime / 2;
    }

    //updates to check if the fire should still burn or become ash 
    public void update() {
        if (burnTime > 0) {
            burnTime--;
        } else {
            grid.setCell(x, y, new ashCell(x, y, grid));
        }

        //spread fire to adjacent cells
        if (burnTime == spreadDelay) {
            spreadFire(x + 1, y);
            spreadFire(x - 1, y);
            spreadFire(x, y + 1);
            spreadFire(x, y - 1);
        }
    }

    //spreads the the fire to nearby plant cells, basing burn time on the passed variable 
    private void spreadFire(int x, int y) {
        Cell adjacentCell = grid.getCell(x, y);
        if (adjacentCell instanceof GrassCell) {
            grid.setCell(x, y, new FireCell(x, y, grid, ((GrassCell) adjacentCell).getBurnTime()));
        } else if (adjacentCell instanceof BushCell) {
            grid.setCell(x, y, new FireCell(x, y, grid, ((BushCell) adjacentCell).getBurnTime()));
        } else if (adjacentCell instanceof TreeCell) {
            grid.setCell(x, y, new FireCell(x, y, grid, ((TreeCell) adjacentCell).getBurnTime()));
        }
    }
}

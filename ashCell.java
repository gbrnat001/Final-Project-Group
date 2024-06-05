
//makes the up cell left over from the vegetation cell set on fire 
public class ashCell extends Cell{
    private int recoverTime;

    //Constructor
    public ashCell(int x, int y, Grid grid){
        super(x, y, grid);
        recoverTime = 40;
    }

    //checks if the time it takes for the ash to recover into grass has passed
    public void update(){
        if(recoverTime > 0){
            recoverTime--;
        } else {
            grid.setCell(x, y, new GrassCell(x, y, grid));
        }
    }
}

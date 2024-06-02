public class Cell{
    private int x;
    private int y;
    protected boolean isEmpty;
    protected boolean isBurning;
    protected boolean isBurned;

    public Cell(){}
    public Cell(int x, int y){
        this.x = x;
        this.y = y;
        this.isEmpty = false;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void update(){

    }
}

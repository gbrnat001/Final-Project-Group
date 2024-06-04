public class fireCell extends Cell{
    private int wind;
    private int time;

    public fireCell(int x, int y, int windSpeed, int time){
        super(x, y);
        this.wind = windSpeed;
        this.time = time;
        this.isBurning = true;
    }

    public fireCell(int x, int y){
        super(x, y);
    }

    public void update(){

    }

    private void spreadFire(){

    }

    private int windEffect(){
        return wind;
    }

    private int burnTime(){
        return time;
    }
}

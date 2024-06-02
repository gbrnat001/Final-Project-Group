public class recoveredCell extends fireCell{

    private int material;
    private boolean burn;

    public recoveredCell(int x, int y, int material, boolean burn){
        super(x, y);
        this.material = material;
        this.burn = burn;
    }
    public void update(){

    }

    private int getBurnableMaterial(){
        return material;
    }

    private boolean checkBurn(){
        return burn;
    }
}

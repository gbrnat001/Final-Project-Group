//Jasiah, Nathan, Alex, Final Project 
//Class: FireCell
//Puropse: Acts as the fire for the GUI and handles the spread logic

public class FireCell extends Cell {
    private int burnTime;
    private int spreadTime;

    //constructor
    public FireCell(int x, int y, SimulationState simulation, int burnTime) {
        super(x, y, simulation);
        this.burnTime = burnTime;
        this.spreadTime = 10;
    }

    //updates to check if the fire should still burn or become ash
    public void update() {
        if (burnTime > 0) {
            burnTime--;
        } else {
            simulation.setCell(x, y, new ashCell(x, y, simulation));
        }

        //spread fire to adjacent cells based on the windSpeed
        if (fireSpreadTimer()){
            spreadFire(x + 1, y);
            spreadFire(x - 1, y);
            spreadFire(x, y + 1);
            spreadFire(x, y - 1);
        }
    }

    //uses the wind speed to deterime how fast fire should spread
    private boolean fireSpreadTimer(){
        int windSpeed = simulation.getWindSpeed();
        if (spreadTime - windSpeed <= 0){
            return true;
        } else {
            spreadTime -= 2; 
            return false;
        }
    }

    // checks nearby cells for burnable material, calls the ignite chance
    // and gets burntime from those cells
    private void spreadFire(int newX, int newY) {
        if (simulation.inBounds(newX, newY)) {
            Cell targetCell = simulation.getCell(newX, newY);

            if (targetCell instanceof GrassCell || targetCell instanceof BushCell || targetCell instanceof TreeCell) {
                int spreadChance = calculateSpreadChance(newX, newY);
                if (simulation.getRand().nextInt(100) < spreadChance) {
                    int burnTime = 0;

                    //set specific burn time based on cell type
                    if (targetCell instanceof GrassCell) {
                        burnTime = ((GrassCell) targetCell).getBurnTime();
                    } else if (targetCell instanceof BushCell) {
                        burnTime = ((BushCell) targetCell).getBurnTime();
                    } else if (targetCell instanceof TreeCell) {
                        burnTime = ((TreeCell) targetCell).getBurnTime();
                    }

                    simulation.setCell(newX, newY, new FireCell(newX, newY, simulation, burnTime));
                }
            }
        }
    }

    //calculates the chance the fire will spread based on the entered values 
    private int calculateSpreadChance(int newX, int newY) {
        int baseChance = 40;
        int humidity = simulation.getHumidity();
        int dryness = simulation.getDryness();
        int windSpeed = simulation.getWindSpeed();
        String windDirection = simulation.getWindDirection();

        //adjusts chance based on dryness and humidity
        baseChance += dryness * 3;
        baseChance -= humidity / 3;

        //adjust chance based on wind direction and wind speed combined
        int dx = newX - x;
        int dy = newY - y;
        int windEffect = 0;

        if (windDirection.equals("N")) {
            if (dy < 0) { //increase chance fire spreads north
                windEffect = 10 + windSpeed * 5;
            } else if (dy > 0) { //massivley damper chance fire spreads south
                windEffect = -10 - windSpeed * 5;
            } else { //slighty lower chance fire spreads east or west
                windEffect -= windSpeed * 3;
            }
        } else if (windDirection.equals("S")) {
            if (dy > 0) { // increase chance fire spreads south
                windEffect = 10 + windSpeed * 5;
            } else if (dy < 0) { //massivley damper chance fire spreads north
                windEffect = -10 - windSpeed * 5;
            } else { //slighty lower chance fire spreads east or west
                windEffect -= windSpeed * 3;
            }
        } else if (windDirection.equals("E")) {
            if (dx > 0) { // increase chance fire spreads east
                windEffect = 10 + windSpeed * 5;
            } else if (dx < 0) { //massivley damper chance fire spreads west
                windEffect = -10 - windSpeed * 5;
            } else { //slighty lower chance fire spreads north or south
                windEffect -= windSpeed * 3;
            }
        } else if (windDirection.equals("W")) {
            if (dx < 0) { // increase chance fire spreads west
                windEffect = 10 + windSpeed * 5;
            } else if (dx > 0) { //massivley damper chance fire spreads east
                windEffect = -10 - windSpeed * 5;
            } else { //slighty lower chance fire spreads north or south
                windEffect -= windSpeed * 3;
            }
        }

        //add the wind effect to the chance
        baseChance += windEffect;

        //make sure chance is within bounds
        if (baseChance < 0) {
            baseChance = 0;
        } else if (baseChance > 100) {
            baseChance = 100;
        }

        return baseChance;
    }
}


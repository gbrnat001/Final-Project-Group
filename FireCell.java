//

public class FireCell extends Cell {
    private int burnTime;

    // Constructor
    public FireCell(int x, int y, SimulationState simulation, int burnTime) {
        super(x, y, simulation);
        this.burnTime = burnTime;
    }

    // Updates to check if the fire should still burn or become ash
    public void update() {
        if (burnTime > 0) {
            burnTime--;
        } else {
            simulation.setCell(x, y, new ashCell(x, y, simulation));
        }

        // Spread fire to adjacent cells
        spreadFire(x + 1, y);
        spreadFire(x - 1, y);
        spreadFire(x, y + 1);
        spreadFire(x, y - 1);
    }

    private void spreadFire(int newX, int newY) {
        if (simulation.inBounds(newX, newY)) {
            Cell targetCell = simulation.getCell(newX, newY);

            if (targetCell instanceof GrassCell || targetCell instanceof BushCell || targetCell instanceof TreeCell) {
                int spreadChance = calculateSpreadChance(newX, newY);
                if (simulation.getRand().nextInt(100) < spreadChance) {
                    int burnTime = 0;

                    // Set specific burn time based on cell type
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

    private int calculateSpreadChance(int newX, int newY) {
        int baseChance = 10; // Base chance to spread
        int humidity = simulation.getHumidity();
        int dryness = simulation.getDryness();
        int windSpeed = simulation.getWindSpeed();
        String windDirection = simulation.getWindDirection();

        // Adjust chance based on dryness and humidity
        baseChance += dryness * 2;
        baseChance -= humidity / 10;

        // Adjust chance based on wind speed
        baseChance += windSpeed * 2;

        // Adjust chance based on wind direction
        if (windDirection.equals("N") && newY < y) { // Fire spreads north
            baseChance += 30;
        } else if (windDirection.equals("S") && newY > y) { // Fire spreads south
            baseChance += 30;
        } else if (windDirection.equals("E") && newX > x) { // Fire spreads east
            baseChance += 30;
        } else if (windDirection.equals("W") && newX < x) { // Fire spreads west
            baseChance += 30;
        } else {
            baseChance -= 10; // Less likely to spread in other directions
        }

        // Ensure chance is within bounds
        if (baseChance < 0) {
            baseChance = 0;
        } else if (baseChance > 100) {
            baseChance = 100;
        }

        return baseChance;
    }
}

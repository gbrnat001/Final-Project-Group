
// Fire cell that
public class FireCell extends Cell{
    private int burnTime;
    private int chance;

    //constructor
    public FireCell(int x, int y, SimulationState simulation, int burnTime) {
        super(x, y, simulation);
        this.burnTime = burnTime;
        this.chance = chance;
    }

    //updates to check if the fire should still burn or become ash
    public void update() {
        if (burnTime > 0) {
            burnTime--;
        } else {
            simulation.setCell(x, y, new ashCell(x, y, simulation));
        }

        //spread fire to adjacent cells
        spreadFire(x + 1, y);
        spreadFire(x - 1, y);
        spreadFire(x, y + 1);
        spreadFire(x, y - 1);
    }

    //spreads the fire to nearby plant cells, basing burn time on the passed variable
    private void spreadFire(int x, int y) {
        Cell adjacentCell = simulation.getCell(x, y);
        if (adjacentCell != null && !adjacentCell.isBurning() && !adjacentCell.isBurned()) {
            int humidity = simulation.getHumidity();
            int windSpeed = simulation.getWindSpeed();
            int dryness = adjacentCell.getDryness();

            //Based off the chance, the cell might turn
            double ignitionChance = calculateIgnitionChance(humidity, windSpeed, dryness, simulation.getCell(x, y));
            if (Math.random() < ignitionChance) {
                int burnTime = 10; //Arbitrary value needed for the setCell
                if (adjacentCell instanceof GrassCell) {
                    burnTime = ((GrassCell) adjacentCell).getBurnTime();
                } else if (adjacentCell instanceof BushCell) {
                    burnTime = ((BushCell) adjacentCell).getBurnTime();
                } else if (adjacentCell instanceof TreeCell) {
                    burnTime = ((TreeCell) adjacentCell).getBurnTime();
                }
                simulation.setCell(x, y, new FireCell(x, y, simulation, burnTime));
            }
        }
    }

    //Based on the humidity, windSpeed, and dryness, the method calculates the chance that the cell
    //will be burned in the next turn. For the 3 values the range of low-high is
    // 0-100 percent humidity
    // 1-10 level of windSpeed
    // 1-10 level of dryness
    private double calculateIgnitionChance(int humidity, int windSpeed, int dryness, Cell cell) {
        double humidityFactor = (100.0 - humidity) / 100.0;
        double windFactor = windSpeed / 10.0;
        double drynessFactor = dryness / 100.0;

        double chance = humidityFactor * windFactor * drynessFactor;

        // Changes the chance depending on the amount of vegetation
        //Easier for tree, a little
        if (cell instanceof TreeCell) {
            return chance * 2; // Easier to burn because more vegetation
        } else if (cell instanceof BushCell) {
            return chance * 1.25; // A little because more vegetation
        } else if (cell instanceof GrassCell) {
            return chance * 0.5; // Even harder to burn
        } else if (cell instanceof ashCell) {
            return 0.0; // Impossible to burn
        } else {
            return chance;
        }
    }

}

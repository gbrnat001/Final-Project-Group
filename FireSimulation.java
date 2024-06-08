import java.util.Scanner;

public class FireSimulation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the grid size (e.g., 100 for a 100x100 grid):");
        int gridSize = scanner.nextInt();

        System.out.println("Enter the wind speed (1-10, where 1 is low and 10 is high):");
        int windSpeed = scanner.nextInt();

        System.out.println("Enter the dryness level (1-10, where 1 is low and 10 is high):");
        int dryness = scanner.nextInt();

        System.out.println("Enter the humidity percent (0-100, where 0 is dry and 100 is very humid):");
        int humidity = scanner.nextInt();

        System.out.println("Enter the number of initial fires:");
        int numFires = scanner.nextInt();

        Grid grid = new Grid(gridSize, windSpeed, dryness, humidity, numFires);
        grid.update();
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FireSimulation {
    public static void main(String[] args) {
        // Create and set up the window
        JFrame frame = new JFrame("Fire Simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Create a panel for input fields with FlowLayout for horizontal alignment
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        frame.add(inputPanel, BorderLayout.NORTH);

        // Add input fields and labels to the panel
        inputPanel.add(new JLabel("Grid Size:"));
        JTextField gridSizeField = new JTextField("10", 5);
        inputPanel.add(gridSizeField);

        inputPanel.add(new JLabel("Wind Speed:"));
        JTextField windSpeedField = new JTextField("5", 5);
        inputPanel.add(windSpeedField);

        inputPanel.add(new JLabel("Wind Direction (N, S, E, W):"));
        JTextField windDirectionField = new JTextField("N", 5);
        inputPanel.add(windDirectionField);

        inputPanel.add(new JLabel("Dryness Level:"));
        JTextField drynessField = new JTextField("5", 5);
        inputPanel.add(drynessField);

        inputPanel.add(new JLabel("Humidity (%):"));
        JTextField humidityField = new JTextField("50", 5);
        inputPanel.add(humidityField);

        inputPanel.add(new JLabel("Number of Initial Fires:"));
        JTextField numFiresField = new JTextField("1", 5);
        inputPanel.add(numFiresField);

        JButton runButton = new JButton("Run");
        inputPanel.add(runButton);

        // Create a panel for the simulation grid
        JPanel gridPanel = new JPanel();
        frame.add(gridPanel, BorderLayout.CENTER);

        // Set the frame size and make it visible
        frame.setSize(1200, 800);
        frame.setVisible(true);

        // Add action listener to the run button
        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the input values
                int gridSize = Integer.parseInt(gridSizeField.getText());
                int windSpeed = Integer.parseInt(windSpeedField.getText());
                String windDirection = windDirectionField.getText().trim().toUpperCase();
                int dryness = Integer.parseInt(drynessField.getText());
                int humidity = Integer.parseInt(humidityField.getText());
                int numFires = Integer.parseInt(numFiresField.getText());

                // Clear the previous grid panel
                frame.remove(gridPanel);

                // Create and add the new grid panel
                Grid newGrid = new Grid(gridSize, windSpeed, windDirection, dryness, humidity, numFires);                frame.add(newGrid.getGridContainer(), BorderLayout.CENTER);

                // Refresh the frame
                frame.revalidate();
                frame.repaint();

                // Start the simulation
                newGrid.update();
            }
        });
    }
}
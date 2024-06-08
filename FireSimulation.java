//Jasiah, Nathan, Alex, Final Project 
//Class: FireSimulation
//Puropse: The main interaction with the GUI, holds all buttons and actionListener
//calls the methods to update the grid constantly

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FireSimulation {
    private static Grid currentGrid;
    public static void main(String[] args) {
        //set up the window
        JFrame frame = new JFrame("Fire Simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        //panel for input fields with FlowLayout for horizontal alignment
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        frame.add(inputPanel, BorderLayout.NORTH);

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

        JButton runButton = new JButton("Run/Reset");
        inputPanel.add(runButton);

        //panel for the simulation grid
        JPanel gridPanel = new JPanel();
        frame.add(gridPanel, BorderLayout.CENTER);

        //set the frame size and make it visible
        frame.setSize(1100, 1100);
        frame.setVisible(true);

        //add action listener to the run button to start new sim
        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int gridSize = 100;
                int windSpeed = Integer.parseInt(windSpeedField.getText());
                String windDirection = windDirectionField.getText().trim().toUpperCase();
                int dryness = Integer.parseInt(drynessField.getText());
                int humidity = Integer.parseInt(humidityField.getText());
                int numFires = Integer.parseInt(numFiresField.getText());

                //clear the grid panel
                gridPanel.removeAll();
                gridPanel.revalidate();
                gridPanel.repaint();

                //create a new grid
                currentGrid = new Grid(gridSize, windSpeed, windDirection, dryness, humidity, numFires);
                gridPanel.add(currentGrid.getGridContainer());
                currentGrid.update();
            }
        });
    }
}

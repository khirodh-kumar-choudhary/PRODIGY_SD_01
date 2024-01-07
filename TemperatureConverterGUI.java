import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TemperatureConverterGUI extends JFrame {

    private JLabel inputLabel, resultLabel;
    private JTextField inputField, resultField;
    private JComboBox<String> unitSelector;
    private JButton convertButton;

    public TemperatureConverterGUI() {
        setTitle("Temperature Converter");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        inputLabel = new JLabel("Enter temperature:");
        inputLabel.setBounds(30, 30, 150, 25);
        add(inputLabel);

        inputField = new JTextField();
        inputField.setBounds(180, 30, 150, 25);
        add(inputField);

        unitSelector = new JComboBox<>(new String[]{"Celsius", "Fahrenheit", "Kelvin"});
        unitSelector.setBounds(180, 65, 150, 25);
        add(unitSelector);

        convertButton = new JButton("Convert");
        convertButton.setBounds(150, 100, 100, 30);
        add(convertButton);

        resultLabel = new JLabel("Converted temperatures:");
        resultLabel.setBounds(30, 140, 180, 25);
        add(resultLabel);

        resultField = new JTextField();
        resultField.setBounds(30, 170, 350, 25);
        resultField.setEditable(false);
        add(resultField);

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertTemperature();
            }
        });
    }

    private void convertTemperature() {
        try {
            double temperature = Double.parseDouble(inputField.getText());
            String selectedUnit = (String) unitSelector.getSelectedItem();
            String result = "";

            if (selectedUnit.equals("Celsius")) {
                result = String.format("%.2f Fahrenheit, %.2f Kelvin",
                        celsiusToFahrenheit(temperature),
                        celsiusToKelvin(temperature));
            } else if (selectedUnit.equals("Fahrenheit")) {
                result = String.format("%.2f Celsius, %.2f Kelvin",
                        fahrenheitToCelsius(temperature),
                        fahrenheitToKelvin(temperature));
            } else if (selectedUnit.equals("Kelvin")) {
                result = String.format("%.2f Celsius, %.2f Fahrenheit",
                        kelvinToCelsius(temperature),
                        kelvinToFahrenheit(temperature));
            }

            resultField.setText(result);
        } catch (NumberFormatException ex) {
            resultField.setText("Invalid input. Please enter a valid number.");
        }
    }

    private double celsiusToFahrenheit(double celsius) {
        return (celsius * 9 / 5) + 32;
    }

    private double celsiusToKelvin(double celsius) {
        return celsius + 273.15;
    }

    private double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5 / 9;
    }

    private double fahrenheitToKelvin(double fahrenheit) {
        return (fahrenheit + 459.67) * 5 / 9;
    }

    private double kelvinToCelsius(double kelvin) {
        return kelvin - 273.15;
    }

    private double kelvinToFahrenheit(double kelvin) {
        return (kelvin * 9 / 5) - 459.67;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                TemperatureConverterGUI converter = new TemperatureConverterGUI();
                converter.setVisible(true);
                converter.setLocationRelativeTo(null); // Center the window on the screen
            }
        });
    }
}

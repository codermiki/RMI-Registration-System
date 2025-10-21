package client;

import javax.swing.*;
import java.awt.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import shared.RegistrationService;

public class RegistrationClient extends JFrame {
    private JTextField usernameField, emailField;
    private JPasswordField passwordField;
    private JButton registerButton;
    private RegistrationService service;

    public RegistrationClient(String serverIP) {
        setTitle("User Registration");
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Main panel with proper spacing
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Username row
        JPanel usernamePanel = new JPanel(new BorderLayout(10, 0));
        usernamePanel.add(new JLabel("Username:"), BorderLayout.WEST);
        usernameField = new JTextField(30);
        usernamePanel.add(usernameField, BorderLayout.EAST);
        mainPanel.add(usernamePanel);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Spacing

        // Email row
        JPanel emailPanel = new JPanel(new BorderLayout(10, 0));
        emailPanel.add(new JLabel("Email:"), BorderLayout.WEST);
        emailField = new JTextField(30);
        emailPanel.add(emailField, BorderLayout.EAST);
        mainPanel.add(emailPanel);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Spacing

        // Password row
        JPanel passwordPanel = new JPanel(new BorderLayout(10, 0));
        passwordPanel.add(new JLabel("Password:"), BorderLayout.WEST);
        passwordField = new JPasswordField(30);
        passwordPanel.add(passwordField, BorderLayout.EAST);
        mainPanel.add(passwordPanel);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Larger spacing before button

        // Centered register button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        registerButton = new JButton("Register");
        buttonPanel.add(registerButton);
        mainPanel.add(buttonPanel);

        add(mainPanel);

        try {
            Registry registry = LocateRegistry.getRegistry(serverIP, 1099);
            service = (RegistrationService) registry.lookup("RegistrationService");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error connecting to server: " + e.getMessage());
            return;
        }

        registerButton.addActionListener(e -> {
            try {
                String response = service.registerUser(
                        usernameField.getText(),
                        new String(passwordField.getPassword()),
                        emailField.getText());
                JOptionPane.showMessageDialog(this, response);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });
    }

    public static void main(String[] args) {
        String serverIP = JOptionPane.showInputDialog("Enter server IP:");
        SwingUtilities.invokeLater(() -> new RegistrationClient(serverIP).setVisible(true));
    }
}
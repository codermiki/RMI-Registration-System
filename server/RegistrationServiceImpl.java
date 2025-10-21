package server;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import shared.RegistrationService;

public class RegistrationServiceImpl implements RegistrationService {

    // Database credentials
    private static final String DB_URL = "jdbc:mysql://localhost:3306/rmi_registration_db";
    private static final String DB_USER = "rmi_registration_db";
    private static final String DB_PASS = "rmi_registration_db@2025";

    @Override
    public String registerUser(String username, String password, String email) throws RemoteException {
        String sql = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, email);
            stmt.setString(3, password);
            stmt.executeUpdate();

            System.out.println("User registered successfully: " + username);
            return "Registration successful!";

        } catch (SQLException e) {
            e.printStackTrace();
            return "Database error: " + e.getMessage();
        }
    }
}

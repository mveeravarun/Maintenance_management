import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/maintenance_management";
    private static final String USER = "root";
    private static final String PASSWORD = "Veera@21022002";

    static {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError("Failed to load MySQL JDBC driver");
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void main(String[] args) {
        try (Connection conn = getConnection()) {
            if (conn != null) {
                System.out.println("Connection established successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error connecting to the database: " + e.getMessage());
        }
    }
}

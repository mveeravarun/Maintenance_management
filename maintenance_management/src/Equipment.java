import java.sql.*;

public class Equipment {
    private int equipmentId;
    private String name;
    private String description;
    private Date purchaseDate;
    private String status;

    public Equipment(int equipmentId, String name, String description, Date purchaseDate, String status) {
        this.equipmentId = equipmentId;
        this.name = name;
        this.description = description;
        this.purchaseDate = purchaseDate;
        this.status = status;
    }

    // Add equipment to the database
    public static void addEquipment(Connection conn, String name, String description, Date purchaseDate, String status) throws SQLException {
        String sql = "INSERT INTO Equipment (name, description, purchase_date, status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, description);
            stmt.setDate(3, purchaseDate);
            stmt.setString(4, status);
            stmt.executeUpdate();
        }
    }

    // View equipment details from the database
    public static void viewEquipment(Connection conn) throws SQLException {
        String sql = "SELECT * FROM Equipment";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println("equipmentID: " + rs.getInt("equipment_id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Description: " + rs.getString("description"));
                System.out.println("Purchase Date: " + rs.getDate("purchase_date"));
                System.out.println("Status: " + rs.getString("status"));
                System.out.println();
            }
        }
    }

    // Update equipment information in the database
    public static void updateEquipment(Connection conn, int equipmentId, String name, String description, Date purchaseDate, String status) throws SQLException {
        String sql = "UPDATE Equipment SET name = ?, description = ?, purchase_date = ?, status = ? WHERE equipment_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, description);
            stmt.setDate(3, purchaseDate);
            stmt.setString(4, status);
            stmt.setInt(5, equipmentId);
            stmt.executeUpdate();
        }
    }

    // Delete equipment from the database
    public static void deleteEquipment(Connection conn, int equipmentId) throws SQLException {
        String sql = "DELETE FROM Equipment WHERE equipment_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, equipmentId);
            stmt.executeUpdate();
        }
    }
}

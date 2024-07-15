import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MaintenanceLog {
    public static void logMaintenanceActivity(Connection conn, int equipmentId, Date maintenanceDate, String description, String technicianName) throws SQLException {
        String sql = "INSERT INTO MaintenanceLog (equipment_Id, maintenance_Date, description, technician_Name) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, equipmentId);
            stmt.setDate(2, maintenanceDate);
            stmt.setString(3, description);
            stmt.setString(4, technicianName);
            stmt.executeUpdate();
        }
    }

    public static void viewMaintenanceLogs(Connection conn) throws SQLException {
        String sql = "SELECT * FROM MaintenanceLog";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("Log ID: " + rs.getInt("log_id"));
                System.out.println("Equipment ID: " + rs.getInt("equipment_Id"));
                System.out.println("Maintenance Date: " + rs.getDate("maintenance_Date"));
                System.out.println("Description: " + rs.getString("description"));
                System.out.println("Technician Name: " + rs.getString("technician_Name"));
                System.out.println("---------------");
            }
        }
    }

    public static void updateMaintenanceLog(Connection conn, int logId, Date maintenanceDate, String description, String technicianName) throws SQLException {
        String sql = "UPDATE MaintenanceLog SET maintenance_Date = ?, description = ?, technician_Name = ? WHERE log_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, maintenanceDate);
            stmt.setString(2, description);
            stmt.setString(3, technicianName);
            stmt.setInt(4, logId);
            stmt.executeUpdate();
        }
    }

    public static void deleteMaintenanceLog(Connection conn, int logId) throws SQLException {
        String sql = "DELETE FROM MaintenanceLog WHERE log_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, logId);
            stmt.executeUpdate();
        }
    }
}

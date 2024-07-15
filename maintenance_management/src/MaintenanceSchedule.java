import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MaintenanceSchedule {
    public static void scheduleMaintenance(Connection conn, int equipmentId, Date scheduledDate, String status) throws SQLException {
        String sql = "INSERT INTO MaintenanceSchedule (equipment_Id, scheduled_Date, status) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, equipmentId);
            stmt.setDate(2, scheduledDate);
            stmt.setString(3, status);
            stmt.executeUpdate();
        }
    }

    public static void viewMaintenanceSchedules(Connection conn) throws SQLException {
        String sql = "SELECT * FROM MaintenanceSchedule";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("Schedule ID: " + rs.getInt("schedule_id"));
                System.out.println("Equipment ID: " + rs.getInt("equipment_Id"));
                System.out.println("Scheduled Date: " + rs.getDate("scheduled_Date"));
                System.out.println("Status:(scheduled/completed/cancelled) " + rs.getString("status"));
                System.out.println("---------------");
            }
        }
    }

    public static void updateMaintenanceSchedule(Connection conn, int scheduleId, Date scheduledDate, String status) throws SQLException {
        String sql = "UPDATE MaintenanceSchedule SET scheduled_Date = ?, status = ? WHERE schedule_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, scheduledDate);
            stmt.setString(2, status);
            stmt.setInt(3, scheduleId);
            stmt.executeUpdate();
        }
    }

    public static void cancelMaintenanceSchedule(Connection conn, int scheduleId) throws SQLException {
        String sql = "DELETE FROM MaintenanceSchedule WHERE schedule_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, scheduleId);
            stmt.executeUpdate();
        }
    }
}

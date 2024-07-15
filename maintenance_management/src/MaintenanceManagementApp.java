import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Scanner;

public class MaintenanceManagementApp {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            if (connection != null) {
                while (true) {
                    showMenu();
                    int choice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    switch (choice) {
                        case 1:
                            addEquipment(connection);
                            break;
                        case 2:
                            viewEquipment(connection);
                            break;
                        case 3:
                            updateEquipment(connection);
                            break;
                        case 4:
                            deleteEquipment(connection);
                            break;
                        case 5:
                            manageMaintenanceSchedules(connection);
                            break;
                        case 6:
                            manageMaintenanceLogs(connection);
                            break;
                        case 0:
                            System.out.println("Exiting...");
                            return;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error connecting to the database: " + e.getMessage());
        }
    }

    private static void showMenu() {
        System.out.println("\nMaintenance Management System");
        System.out.println("1. Add Equipment");
        System.out.println("2. View Equipment");
        System.out.println("3. Update Equipment");
        System.out.println("4. Delete Equipment");
        System.out.println("5. Manage Maintenance Schedules");
        System.out.println("6. Manage Maintenance Logs");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addEquipment(Connection conn) {
        try {
            System.out.print("Enter name: ");
            String name = scanner.nextLine();
            System.out.print("Enter description: ");
            String description = scanner.nextLine();
            Date purchaseDate = readDate("Enter purchase date (YYYY-MM-DD): ");
            System.out.print("Enter status (active/inactive): ");
            String status = scanner.nextLine();

            Equipment.addEquipment(conn, name, description, purchaseDate, status);
            System.out.println("Equipment added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding equipment: " + e.getMessage());
        }
    }

    private static void viewEquipment(Connection conn) {
        try {
            Equipment.viewEquipment(conn);
        } catch (SQLException e) {
            System.out.println("Error viewing equipment: " + e.getMessage());
        }
    }

    private static void updateEquipment(Connection conn) {
        try {
            System.out.print("Enter equipment ID to update: ");
            int equipmentId = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            System.out.print("Enter new name: ");
            String name = scanner.nextLine();
            System.out.print("Enter new description: ");
            String description = scanner.nextLine();
            Date purchaseDate = readDate("Enter new purchase date (YYYY-MM-DD): ");
            System.out.print("Enter new status (active/inactive): ");
            String status = scanner.nextLine();

            Equipment.updateEquipment(conn, equipmentId, name, description, purchaseDate, status);
            System.out.println("Equipment updated successfully.");
        } catch (SQLException e) {
            System.out.println("Error updating equipment: " + e.getMessage());
        }
    }

    private static void deleteEquipment(Connection conn) {
        try {
            System.out.print("Enter equipment ID to delete: ");
            int equipmentId = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            Equipment.deleteEquipment(conn, equipmentId);
            System.out.println("Equipment deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Error deleting equipment: " + e.getMessage());
        }
    }

    private static void manageMaintenanceSchedules(Connection conn) {
        while (true) {
            showScheduleMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    scheduleMaintenance(conn);
                    break;
                case 2:
                    viewMaintenanceSchedules(conn);
                    break;
                case 3:
                    updateMaintenanceSchedule(conn);
                    break;
                case 4:
                    cancelMaintenanceSchedule(conn);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void showScheduleMenu() {
        System.out.println("\nMaintenance Schedule Management");
        System.out.println("1. Schedule Maintenance");
        System.out.println("2. View Maintenance Schedules");
        System.out.println("3. Update Maintenance Schedule");
        System.out.println("4. Cancel Maintenance Schedule");
        System.out.println("0. Back to Main Menu");
        System.out.print("Enter your choice: ");
    }

    private static void scheduleMaintenance(Connection conn) {
        try {
            System.out.print("Enter equipment ID: ");
            int equipmentId = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            Date scheduledDate = readDate("Enter scheduled date (YYYY-MM-DD): ");
            System.out.print("Enter status(scheduled/completed/cancelled): ");
            String status = scanner.nextLine();

            MaintenanceSchedule.scheduleMaintenance(conn, equipmentId, scheduledDate, status);
            System.out.println("Maintenance scheduled successfully.");
        } catch (SQLException e) {
            System.out.println("Error scheduling maintenance: " + e.getMessage());
        }
    }

    private static void viewMaintenanceSchedules(Connection conn) {
        try {
            MaintenanceSchedule.viewMaintenanceSchedules(conn);
        } catch (SQLException e) {
            System.out.println("Error viewing maintenance schedules: " + e.getMessage());
        }
    }

    private static void updateMaintenanceSchedule(Connection conn) {
        try {
            System.out.print("Enter schedule ID to update: ");
            int scheduleId = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            Date scheduledDate = readDate("Enter new scheduled date (YYYY-MM-DD): ");
            System.out.print("Enter new status: ");
            String status = scanner.nextLine();

            MaintenanceSchedule.updateMaintenanceSchedule(conn, scheduleId, scheduledDate, status);
            System.out.println("Maintenance schedule updated successfully.");
        } catch (SQLException e) {
            System.out.println("Error updating maintenance schedule: " + e.getMessage());
        }
    }

    private static void cancelMaintenanceSchedule(Connection conn) {
        try {
            System.out.print("Enter schedule ID to cancel: ");
            int scheduleId = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            MaintenanceSchedule.cancelMaintenanceSchedule(conn, scheduleId);
            System.out.println("Maintenance schedule cancelled successfully.");
        } catch (SQLException e) {
            System.out.println("Error cancelling maintenance schedule: " + e.getMessage());
        }
    }

    private static void manageMaintenanceLogs(Connection conn) {
        while (true) {
            showLogMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    logMaintenanceActivity(conn);
                    break;
                case 2:
                    viewMaintenanceLogs(conn);
                    break;
                case 3:
                    updateMaintenanceLog(conn);
                    break;
                case 4:
                    deleteMaintenanceLog(conn);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void showLogMenu() {
        System.out.println("\nMaintenance Log Management");
        System.out.println("1. Log Maintenance Activity");
        System.out.println("2. View Maintenance Logs");
        System.out.println("3. Update Maintenance Log");
        System.out.println("4. Delete Maintenance Log");
        System.out.println("0. Back to Main Menu");
        System.out.print("Enter your choice: ");
    }

    private static void logMaintenanceActivity(Connection conn) {
        try {
            System.out.print("Enter equipment ID: ");
            int equipmentId = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            Date maintenanceDate = readDate("Enter maintenance date (YYYY-MM-DD): ");
            System.out.print("Enter description: ");
            String description = scanner.nextLine();
            System.out.print("Enter technician name: ");
            String technicianName = scanner.nextLine();

            MaintenanceLog.logMaintenanceActivity(conn, equipmentId, maintenanceDate, description, technicianName);
            System.out.println("Maintenance activity logged successfully.");
        } catch (SQLException e) {
            System.out.println("Error logging maintenance activity: " + e.getMessage());
        }
    }

    private static void viewMaintenanceLogs(Connection conn) {
        try {
            MaintenanceLog.viewMaintenanceLogs(conn);
        } catch (SQLException e) {
            System.out.println("Error viewing maintenance logs: " + e.getMessage());
        }
    }

    private static void updateMaintenanceLog(Connection conn) {
        try {
            System.out.print("Enter log ID to update: ");
            int logId = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            Date maintenanceDate = readDate("Enter new maintenance date (YYYY-MM-DD): ");
            System.out.print("Enter new description: ");
            String description = scanner.nextLine();
            System.out.print("Enter new technician name: ");
            String technicianName = scanner.nextLine();

            MaintenanceLog.updateMaintenanceLog(conn, logId, maintenanceDate, description, technicianName);
            System.out.println("Maintenance log updated successfully.");
        } catch (SQLException e) {
            System.out.println("Error updating maintenance log: " + e.getMessage());
        }
    }

    private static void deleteMaintenanceLog(Connection conn) {
        try {
            System.out.print("Enter log ID to delete: ");
            int logId = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            MaintenanceLog.deleteMaintenanceLog(conn, logId);
            System.out.println("Maintenance log deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Error deleting maintenance log: " + e.getMessage());
        }
    }

    private static Date readDate(String message) {
        while (true) {
            try {
                System.out.print(message);
                String input = scanner.nextLine();
                return Date.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid date format. Please enter date in YYYY-MM-DD format.");
            }
        }
    }
}

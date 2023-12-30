import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
	public static void main(String[] args) {
		createDatabase();
		JFrame_StudentLogin frame = new JFrame_StudentLogin();
		frame.setVisible(true);
	}
	
	public static void createDatabase() {
        // JDBC URL, username, and password of MySQL server
        String url = "jdbc:mysql://localhost/";
        String username = "root";
        String password = "";

        // SQL statements to create the database and tables
        String createDatabaseSQL = "CREATE DATABASE IF NOT EXISTS spotter_db";
        String useDatabaseSQL = "USE spotter_db";
        String createStudentAccountsTableSQL = "CREATE TABLE IF NOT EXISTS student_accounts ("
                + "account_id INT AUTO_INCREMENT PRIMARY KEY,"
                + "lastname VARCHAR(255) NOT NULL,"
                + "firstname VARCHAR(255) NOT NULL,"
                + "middlename VARCHAR(255),"
                + "student_number VARCHAR(20) NOT NULL,"
                + "program VARCHAR(255),"
                + "email VARCHAR(255) NOT NULL,"
                + "password VARCHAR(255) NOT NULL,"
                + "isVerified TINYINT(1) NOT NULL)";
        String createAdminAccountsTableSQL = "CREATE TABLE IF NOT EXISTS admin_accounts ("
                + "account_id INT AUTO_INCREMENT PRIMARY KEY,"
                + "lastname VARCHAR(255) NOT NULL,"
                + "firstname VARCHAR(255) NOT NULL,"
                + "middlename VARCHAR(255),"
                + "email VARCHAR(255) NOT NULL,"
                + "password VARCHAR(255) NOT NULL,"
                + "isVerified TINYINT(1) NOT NULL)";
        String createReservationTableSQL = "CREATE TABLE IF NOT EXISTS reservation ("
                + "reservation_id INT AUTO_INCREMENT PRIMARY KEY,"
                + "room_number INT NOT NULL,"
                + "target_date DATE NOT NULL,"
                + "time_range ENUM('8am - 9am', '9am - 10am', '10am - 11am', '11am - 12pm', '12pm - 1pm', '1pm - 2pm', '2pm - 3pm', '3pm - 4pm', '4pm - 5pm') NOT NULL,"
                + "requester_id INT,"
                + "attendees TEXT,"
                + "status ENUM('Pending', 'Declined', 'Scheduled', 'Unattended', 'Attended', 'Expired') NOT NULL,"
                + "FOREIGN KEY (requester_id) REFERENCES student_accounts(account_id))";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement()) {

            // Create the database
            stmt.executeUpdate(createDatabaseSQL);

            // Use the database
            stmt.executeUpdate(useDatabaseSQL);

            // Create the tables
            stmt.executeUpdate(createStudentAccountsTableSQL);
            stmt.executeUpdate(createAdminAccountsTableSQL);
            stmt.executeUpdate(createReservationTableSQL);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
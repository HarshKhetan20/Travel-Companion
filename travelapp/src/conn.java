import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class conn {
    public Connection con;
    public Statement st;

    public conn() {
        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/travel_database",  // Database URL
                "root",  // MySQL username
                "root@harsh.1."  // MySQL password (replace with your actual password)
            );

            // Create a statement object
            st = con.createStatement();

            System.out.println("Connected to the database!");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}

import java.sql.*;

public class ViewAccountDetails {
    public static void main(String[] args) {
        // Database connection parameters
        String url = "jdbc:mysql://localhost:3306/travel_database"; // Update with your database URL
        String user = "root"; // Update with your MySQL username
        String password = "theanimesh2005"; // Update with your MySQL password

        // Connection and Statement
        Connection conn = null;
        Statement stmt = null;

        try {
            // Establishing the connection
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();

            // SQL query to fetch data
            String sql = "SELECT * FROM Account"; // Make sure to use the correct table name
            ResultSet rs = stmt.executeQuery(sql);

            // Displaying the results
            System.out.println("Account Details:");
            while (rs.next()) {
                String username = rs.getString("username");
                String name = rs.getString("name");
                String passwordDb = rs.getString("password"); // Note: Be cautious with passwords!
                String security = rs.getString("security");
                String answer = rs.getString("answer");

                System.out.println("Username: " + username + ", Name: " + name + ", Password: " + passwordDb + ", Security Question: " + security + ", Answer: " + answer);
            }

            // Close the ResultSet
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

import java.sql.*;

public class ViewCustomerDetails {
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
            String sql = "SELECT * FROM customer";
            ResultSet rs = stmt.executeQuery(sql);

            // Displaying the results
            System.out.println("Customer Details:");
            while (rs.next()) {
                String username = rs.getString("username");
                String id = rs.getString("id");
                @SuppressWarnings("unused")
                String idNumber = rs.getString("id_number");
                String name = rs.getString("name");
                String gender = rs.getString("gender");
                String country = rs.getString("country");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                String email = rs.getString("email");

                System.out.println("Username: " + username + ", ID: " + id + ", Name: " + name + ", Gender: " + gender + ", Country: " + country + ", Address: " + address + ", Phone: " + phone + ", Email: " + email);
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

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import java.io.IOException; // For handling IOException
import java.awt.Desktop; // For opening the default browser

public class Destination extends JFrame {
    private JPanel contentPane;
    private Choice c1, c2; // For states and places
    private JTextField t1;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            System.out.println("Look and Feel not set");
        }
        new Destination();
    }

    public Destination() {
        setBounds(420, 220, 1100, 600);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        Font f1 = new Font("Gill Sans Ultra Bold Condensed", Font.PLAIN, 30);
        Font f2 = new Font("Tahoma Bold", Font.BOLD, 15);

        // Add Image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/travel.gif")); // Ensure the path is correct
        Image i3 = i1.getImage().getScaledInstance(500, 300, Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(i3);
        JLabel imgLabel = new JLabel(i2);
        imgLabel.setBounds(450, 100, 700, 300);
        contentPane.add(imgLabel);

        JLabel lblName = new JLabel("DESTINATION SELECTION");
        lblName.setFont(f1);
        lblName.setBounds(118, 11, 400, 53);
        contentPane.add(lblName);

        JLabel lblState = new JLabel("Select State:");
        lblState.setBounds(35, 70, 200, 14);
        lblState.setFont(f2);
        contentPane.add(lblState);

        c1 = new Choice();
        loadStates(); // Load states from the database
        c1.setBounds(271, 70, 150, 30);
        c1.setFont(f2);
        contentPane.add(c1);

        JLabel lblPlace = new JLabel("Select Place:");
        lblPlace.setBounds(35, 110, 200, 14);
        lblPlace.setFont(f2);
        contentPane.add(lblPlace);

        c2 = new Choice();
        c2.setBounds(271, 110, 150, 30);
        c2.setFont(f2);
        contentPane.add(c2);

        c1.addItemListener(e -> loadPlaces(c1.getSelectedItem())); // Load places when state is selected

        JLabel lblPersons = new JLabel("Total Persons:");
        lblPersons.setBounds(35, 150, 200, 14);
        lblPersons.setFont(f2);
        contentPane.add(lblPersons);

        t1 = new JTextField();
        t1.setText("0");
        t1.setBounds(271, 150, 150, 30);
        t1.setFont(f2);
        contentPane.add(t1);

        JButton btnDone = new JButton("Done");
        btnDone.setFont(f2);
        btnDone.setBounds(200, 470, 120, 30);
        btnDone.setBackground(Color.BLACK);
        btnDone.setForeground(Color.WHITE);
        contentPane.add(btnDone);

        JButton btnLocation = new JButton("Location");
        btnLocation.setFont(f2);
        btnLocation.setBounds(350, 470, 120, 30);
        btnLocation.setBackground(Color.BLACK);
        btnLocation.setForeground(Color.WHITE);
        contentPane.add(btnLocation);

        btnDone.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedState = c1.getSelectedItem();
                String selectedPlace = c2.getSelectedItem();
                String totalPersons = t1.getText();

                // Insert data into user_destination table
                try {
                    conn c = new conn(); // Make sure you have this connection class
                    String query = "INSERT INTO user_destination (state, place, persons) VALUES (?, ?, ?)";
                    PreparedStatement pstmt = c.con.prepareStatement(query);
                    pstmt.setString(1, selectedState);
                    pstmt.setString(2, selectedPlace);
                    pstmt.setInt(3, Integer.parseInt(totalPersons));

                    int result = pstmt.executeUpdate();
                    if (result > 0) {
                        JOptionPane.showMessageDialog(null, "Destination Stored Successfully!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to Store Destination.");
                    }
                    pstmt.close();
                    c.con.close(); // Close the connection
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Database Error: " + ex.getMessage());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number for persons.");
                }
            }
        });

        btnLocation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedState = c1.getSelectedItem();
                String selectedPlace = c2.getSelectedItem();
                // Construct the Google Maps URL
                String mapsUrl = "https://www.google.com/maps/search/?api=1&query=" + selectedPlace + ",+" + selectedState;
                
                // Open the URL in the default browser
                try {
                    Desktop.getDesktop().browse(new java.net.URI(mapsUrl));
                } catch (IOException | java.net.URISyntaxException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error opening Google Maps: " + ex.getMessage());
                }
            }
        });

        // Align everything to the center
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
    }

    private void loadStates() {
        try {
            conn c = new conn(); // Assuming you have a connection class
            String query = "SELECT DISTINCT state FROM destinations"; // Modify this query according to your table structure
            ResultSet rs = c.st.executeQuery(query);
            while (rs.next()) {
                c1.add(rs.getString("state"));
            }
            rs.close();
            c.con.close(); // Close the connection
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading states: " + e.getMessage());
        }
    }

    private void loadPlaces(String state) {
        c2.removeAll(); // Clear previous places
        try {
            conn c = new conn(); // Assuming you have a connection class
            String query = "SELECT place FROM destinations WHERE state = ?"; // Adjust the query as needed
            PreparedStatement pstmt = c.con.prepareStatement(query);
            pstmt.setString(1, state);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                c2.add(rs.getString("place"));
            }
            rs.close();
            pstmt.close();
            c.con.close(); // Close the connection
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading places: " + e.getMessage());
        }
    }
}

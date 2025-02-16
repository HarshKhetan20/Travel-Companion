import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

public class Payment extends JFrame {
    private JPanel contentPane;
    private JTextField cardNumberField, expiryField, cvvField, hotelCostField, packageCostField, amountField;
    private String username;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            System.out.println("Look and Feel not set");
        }
        new Payment("actualUsername"); // Replace "actualUsername" with the variable that holds the current username
    }

    public Payment(String username) {
        this.username = username;
        setBounds(420, 220, 1100, 600);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        Font f1 = new Font("Gill Sans Ultra Bold Condensed", Font.PLAIN, 30);
        Font f2 = new Font("Tahoma Bold", Font.BOLD, 15);

        // Add GIF Image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/travel.gif"));
        Image i3 = i1.getImage().getScaledInstance(500, 300, Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(i3);
        JLabel imgLabel = new JLabel(i2);
        imgLabel.setBounds(450, 100, 700, 300);
        contentPane.add(imgLabel);

        JLabel lblTitle = new JLabel("PAYMENT PROCESS");
        lblTitle.setFont(f1);
        lblTitle.setBounds(118, 11, 400, 53);
        contentPane.add(lblTitle);

        // Fields for Hotel and Package Costs
        JLabel lblHotelCost = new JLabel("Hotel Cost:");
        lblHotelCost.setBounds(35, 70, 200, 20);
        lblHotelCost.setFont(f2);
        contentPane.add(lblHotelCost);

        hotelCostField = new JTextField();
        hotelCostField.setBounds(250, 70, 150, 30);
        hotelCostField.setFont(f2);
        hotelCostField.setEditable(false);
        contentPane.add(hotelCostField);

        JLabel lblPackageCost = new JLabel("Package Cost:");
        lblPackageCost.setBounds(35, 110, 200, 20);
        lblPackageCost.setFont(f2);
        contentPane.add(lblPackageCost);

        packageCostField = new JTextField();
        packageCostField.setBounds(250, 110, 150, 30);
        packageCostField.setFont(f2);
        packageCostField.setEditable(false);
        contentPane.add(packageCostField);

        JLabel lblCardNumber = new JLabel("Card Number:");
        lblCardNumber.setBounds(35, 150, 200, 20);
        lblCardNumber.setFont(f2);
        contentPane.add(lblCardNumber);

        cardNumberField = new JTextField();
        cardNumberField.setBounds(250, 150, 150, 30);
        cardNumberField.setFont(f2);
        contentPane.add(cardNumberField);

        JLabel lblExpiry = new JLabel("Expiry Date (MM/YY):");
        lblExpiry.setBounds(35, 190, 200, 20);
        lblExpiry.setFont(f2);
        contentPane.add(lblExpiry);

        expiryField = new JTextField();
        expiryField.setBounds(250, 190, 150, 30);
        expiryField.setFont(f2);
        contentPane.add(expiryField);

        JLabel lblCvv = new JLabel("CVV:");
        lblCvv.setBounds(35, 230, 200, 20);
        lblCvv.setFont(f2);
        contentPane.add(lblCvv);

        cvvField = new JTextField();
        cvvField.setBounds(250, 230, 150, 30);
        cvvField.setFont(f2);
        contentPane.add(cvvField);

        JLabel lblAmount = new JLabel("Total Amount:");
        lblAmount.setBounds(35, 270, 200, 20);
        lblAmount.setFont(f2);
        contentPane.add(lblAmount);

        amountField = new JTextField();
        amountField.setBounds(250, 270, 150, 30);
        amountField.setFont(f2);
        amountField.setEditable(false);
        contentPane.add(amountField);

        JButton btnPay = new JButton("Pay Now");
        btnPay.setFont(f2);
        btnPay.setBounds(35, 320, 150, 30);
        btnPay.setBackground(Color.BLACK);
        btnPay.setForeground(Color.WHITE);
        contentPane.add(btnPay);

        btnPay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String cardNumber = cardNumberField.getText();
                String expiry = expiryField.getText();
                String cvv = cvvField.getText();
                String amount = amountField.getText();

                if (validatePayment(cardNumber, expiry, cvv, amount)) {
                    if (storePaymentDetails(cardNumber, expiry, cvv, amount)) {
                        JOptionPane.showMessageDialog(null, "Payment Successful!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to save payment details.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill all fields correctly, check card number (16) and expiry date (greater than year 2027 and month between 1 and 12)");
                }
            }
        });

        getContentPane().setBackground(Color.WHITE);
        setVisible(true);

        // Fetch and display hotel and package costs
        fetchCosts();
    }

    private void fetchCosts() {
        String url = "jdbc:mysql://localhost:3306/travel_database";
        String user = "root";
        String password = "theanimesh2005";

        String query = "SELECT b.price AS hotel_price, p.price AS package_price FROM bookhotel b, bookpackage p WHERE b.username = ? AND p.username = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, username);
            pstmt.setString(2, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String hotelPrice = rs.getString("hotel_price").replace("Rs ", "").replace(",", "");
                String packagePrice = rs.getString("package_price");
                hotelCostField.setText(hotelPrice);
                packageCostField.setText(packagePrice);
                
                double totalAmount = Double.parseDouble(hotelPrice) + Double.parseDouble(packagePrice);
                amountField.setText(String.format("%.2f", totalAmount));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean validatePayment(String cardNumber, String expiry, String cvv, String amount) {
        if (cardNumber.isEmpty() || expiry.isEmpty() || cvv.isEmpty() || amount.isEmpty() 
            || cardNumber.length() != 16 || !expiry.matches("\\d{2}/\\d{2}") || cvv.length() != 3) {
            return false;
        }

        // Extract the month and year from the expiry date
        String[] expiryParts = expiry.split("/");
        int month = Integer.parseInt(expiryParts[0]);
        int year = Integer.parseInt(expiryParts[1]) + 2000;

        // Check if expiry is valid (greater than 2027) and month is valid
        if (year <= 2027 || month < 1 || month > 12) {
            return false;
        }

        return true;
    }

    private boolean storePaymentDetails(String cardNumber, String expiry, String cvv, String amount) {
        String url = "jdbc:mysql://localhost:3306/travel_database";
        String user = "root";
        String password = "theanimesh2005";

        String query = "INSERT INTO payments (username, card_number, expiry_date, cvv, amount) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, username);
            pstmt.setString(2, cardNumber);
            pstmt.setString(3, expiry);
            pstmt.setString(4, cvv);
            pstmt.setString(5, amount);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

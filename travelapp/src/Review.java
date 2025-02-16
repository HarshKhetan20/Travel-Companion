import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Review extends JFrame {
    private JPanel contentPane;
    private JTextArea reviewTextArea;
    private String username;
    private int rating = 0; // Store rating

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            System.out.println("Look and Feel not set");
        }
        new Review("actualUsername"); // Replace with actual username variable
    }

    public Review(String username) {
        this.username = username;  // Store the username
        setBounds(420, 220, 800, 400); // Adjusted size to make the box smaller
        contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS)); // Use BoxLayout for vertical alignment
        setContentPane(contentPane);
        
        Font f1 = new Font("Gill Sans Ultra Bold Condensed", Font.PLAIN, 30);
        Font f2 = new Font("Tahoma Bold", Font.BOLD, 15);

        // Centered Title
        JLabel lblTitle = new JLabel("SHARE YOUR REVIEW");
        lblTitle.setFont(f1);
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align
        contentPane.add(lblTitle);
        contentPane.add(Box.createRigidArea(new Dimension(0, 20))); // Add space

        // Text Area for Review
        reviewTextArea = new JTextArea();
        reviewTextArea.setFont(f2);
        reviewTextArea.setLineWrap(true);
        reviewTextArea.setWrapStyleWord(true);
        reviewTextArea.setPreferredSize(new Dimension(400, 100)); // Set preferred size to make it smaller
        reviewTextArea.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align
        contentPane.add(reviewTextArea);
        contentPane.add(Box.createRigidArea(new Dimension(0, 20))); // Add space

        // Rating Buttons with Emojis
        JPanel emojiPanel = new JPanel();
        emojiPanel.setLayout(new FlowLayout()); // Align buttons nicely
        emojiPanel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align
        contentPane.add(emojiPanel);

        String[] emojis = {"😡", "😞", "😐", "😊", "😍"};

        for (int i = 0; i < emojis.length; i++) {
            JButton emojiButton = new JButton(emojis[i]);
            emojiButton.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 40)); // Use a font that supports colored emojis
            emojiButton.setFocusPainted(false); // Remove focus highlight
            emojiButton.setContentAreaFilled(false); // Make button transparent
            emojiButton.setBorderPainted(false); // Remove border
            emojiButton.setOpaque(false); // Make button transparent

            int ratingValue = i + 1; // Rating from 1 to 5
            emojiButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    rating = ratingValue; // Set rating based on button clicked
                    JOptionPane.showMessageDialog(null, "You selected a rating of " + ratingValue);
                }
            });
            emojiPanel.add(emojiButton);
        }

        contentPane.add(Box.createRigidArea(new Dimension(0, 20))); // Add space

        // Submit Button
        JButton btnSubmit = new JButton("Submit Review");
        btnSubmit.setFont(f2);
        btnSubmit.setBackground(Color.BLACK);
        btnSubmit.setForeground(Color.WHITE);
        btnSubmit.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align
        contentPane.add(btnSubmit);

        btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String review = reviewTextArea.getText();
                if (rating == 0 || review.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please select a rating and enter your review.");
                } else {
                    if (storeReview(review, rating)) {
                        JOptionPane.showMessageDialog(null, "Review submitted successfully!");
                        reviewTextArea.setText(""); // Clear text area after submission
                        rating = 0; // Reset rating after submission
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to submit review.");
                    }
                }
            }
        });

        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
    }

    private boolean storeReview(String review, int rating) {
        // Database connection parameters
        String url = "jdbc:mysql://localhost:3306/travel_database"; // Update with your database name
        String user = "root"; // Update with your database username
        String password = "theanimesh2005"; // Update with your database password

        String query = "INSERT INTO reviews (username, review_text, rating) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, username); // Store username
            pstmt.setString(2, review);
            pstmt.setInt(3, rating);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

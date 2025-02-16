import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class forgotPassword extends JFrame {
    JLabel usernameLabel = new JLabel("USERNAME");
    JLabel nameLabel = new JLabel("NAME");
    JLabel securityLabel = new JLabel("SECURITY QUESTION");
    JLabel answerLabel = new JLabel("ANSWER");
    
    JTextField usernameTextField = new JTextField();
    JTextField nameTextField = new JTextField();
    JTextField answerTextField = new JTextField();
    
    JComboBox<String> securityComboBox = new JComboBox<>(new String[] {
        "Your pet name", 
        "Your favourite character in avengers", 
        "Your Best Friends Name"
    });
    
    JPanel p1 = new JPanel();
    JPanel p2 = new JPanel();
    
    JButton searchButton = new JButton("SEARCH");
    JButton retrieveButton = new JButton("RETRIEVE");
    JButton goback = new JButton("GO BACK");
    
    forgotPassword() {
        setBounds(300, 150, 975, 350); // Adjust height
        Container c = getContentPane();
        c.setBackground(Color.white);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        
        Border br2 = BorderFactory.createLineBorder(Color.BLACK, 2);
        Font f1 = new Font("Ariel", Font.BOLD, 13);
        
        p1.setLayout(null);
        p1.setBounds(0, 10, 550, 300); // Adjust height
        p1.setBackground(new Color(200, 200, 240));
        
        p2.setLayout(null);
        p2.setBounds(555, 10, 400, 300); // Adjust height
        
        ImageIcon image = new ImageIcon(ClassLoader.getSystemResource("icons/forgotpassword1.gif"));
        ImageIcon image_0 = new ImageIcon(image.getImage().getScaledInstance(530, 500, Image.SCALE_DEFAULT));
        JLabel l0 = new JLabel(image_0);
        l0.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        l0.setBounds(15, 20, 370, 250); // Adjust height
        p2.add(l0);
        
        usernameTextField.setFont(f1);
        nameTextField.setFont(f1);
        answerTextField.setFont(f1);
        
        usernameLabel.setBounds(30, 50, 150, 30);
        nameLabel.setBounds(30, 100, 150, 30);
        securityLabel.setBounds(30, 150, 150, 30);
        answerLabel.setBounds(30, 200, 150, 30);
        
        usernameTextField.setBounds(170, 50, 200, 30);
        nameTextField.setBounds(170, 100, 200, 30);
        securityComboBox.setBounds(170, 150, 200, 30);
        answerTextField.setBounds(170, 200, 200, 30);
        
        searchButton.setBounds(400, 50, 100, 30);
        retrieveButton.setBounds(400, 200, 100, 30);
        goback.setBounds(170, 250, 100, 30); // Adjust height
        
        searchButton.setBackground(new Color(240, 150, 200));
        searchButton.setForeground(Color.BLACK);
        searchButton.setFont(f1);
        searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        retrieveButton.setBackground(new Color(200, 230, 100));
        retrieveButton.setForeground(Color.BLACK);
        retrieveButton.setFont(f1);
        retrieveButton.setEnabled(false);
        retrieveButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        goback.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new login();
            }
        });
        
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (usernameTextField.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter your username", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    conn c = new conn();
                    String username = usernameTextField.getText();
                    String query = "SELECT * FROM account WHERE username='" + username + "'";
                    ResultSet res = c.st.executeQuery(query);
                    if (res.next()) {
                        String name = res.getString("name");
                        String security = res.getString("security");
                        nameTextField.setText(name);
                        securityComboBox.setSelectedItem(security); // Set selected item
                        retrieveButton.setEnabled(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Username not available", "Error", JOptionPane.ERROR_MESSAGE);
                        usernameTextField.setText("");
                        nameTextField.setText("");
                        securityComboBox.setSelectedIndex(0); // Reset combo box
                        retrieveButton.setEnabled(false);
                    }
                } catch (SQLException eg) {
                    eg.printStackTrace();
                }
            }
        });
        
        retrieveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (answerTextField.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in the answer field", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    conn c = new conn();
                    String username = usernameTextField.getText();
                    String query2 = "SELECT * FROM account WHERE username='" + username + "'";
                    ResultSet res = c.st.executeQuery(query2);
                    
                    if (res.next()) {
                        String expectedAnswer = res.getString("answer");
                        String selectedSecurityQuestion = (String) securityComboBox.getSelectedItem();

                        // Verify the security question and answer
                        if (selectedSecurityQuestion.equals(res.getString("security")) && expectedAnswer.equalsIgnoreCase(answerTextField.getText().trim())) {
                            String password = res.getString("password"); // Retrieve the password from the database
                            JOptionPane.showMessageDialog(null, "Your password is: " + password, "Password Retrieved", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "Security answer not correct or question mismatch", "Error", JOptionPane.ERROR_MESSAGE);
                            answerTextField.setText("");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "No user found with this username", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException ef) {
                    ef.printStackTrace();
                }
            }
        });
        
        p1.add(goback);
        p1.add(retrieveButton);
        p1.add(searchButton);
        p1.add(securityComboBox); // Add JComboBox instead of JTextField
        p1.add(answerTextField);
        p1.add(nameTextField);
        p1.add(usernameTextField);
        p1.add(answerLabel);
        p1.add(securityLabel);
        p1.add(nameLabel);
        p1.add(usernameLabel);
        
        p2.setBorder(br2);
        c.add(p2);
        c.add(p1);
        
        setVisible(true);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            System.out.println("Look and Feel not set");
        }
        
        new forgotPassword();
    }
}

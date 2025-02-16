import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.Border;

public class login extends JFrame {
    JPanel p1 = new JPanel();
    JPanel p2 = new JPanel();

    login() {
        // Set the title and layout
        setTitle("LOGIN PAGE");
        setLayout(null);

        // Set the frame to full screen
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);  // Remove window decorations for true fullscreen

        Container c = getContentPane();
        c.setBackground(Color.white);

        // Define border
        Border br = BorderFactory.createLineBorder(Color.GRAY);

        // Set default close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Get the screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;

        // Panel p1 configuration
        p1.setLayout(null);
        p1.setBounds(0, 0, width / 2, height);
        p1.setBackground(Color.white);
        p1.setBorder(br);
        c.add(p1);

        // Add image to p1
        ImageIcon image_1 = new ImageIcon(ClassLoader.getSystemResource("icons/login23.gif"));
        ImageIcon image_2 = new ImageIcon(image_1.getImage().getScaledInstance(p1.getWidth(), p1.getHeight() - 100, Image.SCALE_DEFAULT));
        JLabel l1 = new JLabel(image_2);
        l1.setBounds(50, 10, p1.getWidth() - 100, p1.getHeight() - 100);
        p1.add(l1);

        // Panel p2 configuration
        p2.setLayout(null);
        p2.setBounds(width / 2, 0, width / 2, height);
        p2.setBackground(Color.LIGHT_GRAY);

        // Add username and password fields
        JLabel username = new JLabel("USERNAME");
        JLabel password = new JLabel("PASSWORD");
        JTextField user = new JTextField("");
        JPasswordField pass = new JPasswordField("");
        Font f1 = new Font("Arial", Font.BOLD, 25);
        Font f2 = new Font(Font.DIALOG, Font.BOLD, 20);

        // Set bounds for labels and fields
        int centerX = p2.getWidth() / 2;
        int fieldWidth = 400;
        int fieldHeight = 40;
        int labelHeight = 50;
        int verticalGap = 20;

        username.setBounds(centerX - fieldWidth/2, height/4, fieldWidth, labelHeight);
        username.setFont(f1);
        user.setBounds(centerX - fieldWidth/2, username.getY() + labelHeight + verticalGap, fieldWidth, fieldHeight);
        user.setFont(f2);
        user.setBorder(BorderFactory.createEmptyBorder());

        password.setBounds(centerX - fieldWidth/2, user.getY() + fieldHeight + verticalGap * 2, fieldWidth, labelHeight);
        password.setFont(f1);
        pass.setBounds(centerX - fieldWidth/2, password.getY() + labelHeight + verticalGap, fieldWidth, fieldHeight);
        pass.setFont(f2);
        pass.setBorder(BorderFactory.createEmptyBorder());

        // Add components to panel p2
        p2.add(username);
        p2.add(user);
        p2.add(password);
        p2.add(pass);

        // Login button configuration
        JButton login = new JButton("LOGIN");
        login.setBounds(centerX - fieldWidth/2, pass.getY() + fieldHeight + verticalGap * 2, fieldWidth/2 - 10, 50);
        login.setFont(f2);
        login.setBackground(new Color(111, 191, 233));
        login.setForeground(Color.white);
        login.setBorder(BorderFactory.createEmptyBorder());
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    conn c = new conn();
                    String users = user.getText();
                    char ch[] = pass.getPassword();
                    String PASSWORD = new String(ch);

                    String query = "Select username,password from account where username='" + users + "' and password='" + PASSWORD + "'";
                    ResultSet res = c.st.executeQuery(query);
                    if (res.next()) {
                        JOptionPane.showMessageDialog(null, " ✌ Press OK To LOGIN ✌", "Message", JOptionPane.INFORMATION_MESSAGE);
                        setVisible(false);
                        new loading(users);
                    } else {
                        JOptionPane.showMessageDialog(null, " ✌ NAME OR PASSWORD INVALID TRY AGAIN , if not signed up then SIGN UP ✌", "Message", JOptionPane.ERROR_MESSAGE);
                    }
                    user.setText("");
                    pass.setText("");
                } catch (Exception ef) {
                    ef.printStackTrace();
                }
            }
        });

        // Signup button configuration
        JButton signup = new JButton("SIGN UP");
        signup.setBounds(centerX + 10, login.getY(), fieldWidth/2 - 10, 50);
        signup.setFont(f2);
        signup.setBackground(Color.white);
        signup.setForeground(new Color(111, 191, 233));
        signup.setBorder(BorderFactory.createEmptyBorder());
        signup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new signup();
            }
        });

        // Forgot password button configuration
        JButton forgotpassword = new JButton("FORGOT PASSWORD");
        forgotpassword.setBounds(centerX - fieldWidth/2, login.getY() + 70, fieldWidth/1, 40);
        forgotpassword.setFont(f2);
        forgotpassword.setBackground(Color.white);
        forgotpassword.setForeground(new Color(111, 191, 233));
        forgotpassword.setBorder(BorderFactory.createEmptyBorder());
        forgotpassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new forgotPassword();
            }
        });

        // Add buttons to panel p2
        p2.add(login);
        p2.add(signup);
        p2.add(forgotpassword);

        // Add panel p2 to container
        c.add(p2);

        // Make frame visible
        setVisible(true);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            System.out.println("Look and Feel not set");
        }
        new login();
    }
}
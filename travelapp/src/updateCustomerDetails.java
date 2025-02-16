import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.sql.*;

public class updateCustomerDetails extends JFrame {
    Container c;
    JLabel l1 = new JLabel("USERNAME : ");
    JLabel l2 = new JLabel("ID :");
    JLabel l3 = new JLabel("NUMBER : ");
    JLabel l4 = new JLabel("NAME : ");
    JLabel l5 = new JLabel("GENDER : ");
    JLabel l6 = new JLabel("COUNTRY : ");
    JLabel l7 = new JLabel("ADDRESS : ");
    JLabel l8 = new JLabel("PHONE : ");
    JLabel l9 = new JLabel("E-MAIL : ");
    JLabel l10 = new JLabel("UPDATE CUSTOMER DETAILS");

    JTextField t1 = new JTextField();
    JTextField t2 = new JTextField();
    JTextField t3 = new JTextField();
    JTextField t4 = new JTextField();
    JTextField t5 = new JTextField();
    JTextField t6 = new JTextField();
    JTextField t7 = new JTextField();
    JTextField t8 = new JTextField();
    JTextField t9 = new JTextField();
    JRadioButton r1, r2;
    ButtonGroup g1 = new ButtonGroup();

    JButton b1 = new JButton("UPDATE");
    JButton b2 = new JButton("BACK");

    JPanel img = new JPanel();
    String k = "";

    updateCustomerDetails(String user) {
        k = user;
        setUndecorated(true);
        setBounds(400, 170, 850, 580);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        c = getContentPane();
        c.setBackground(Color.white);
        setLayout(null);
        Border br2 = BorderFactory.createLineBorder(Color.BLACK, 2);

        l10.setBounds(50, 10, 300, 40);
        l10.setFont(new Font("Agency FB", Font.BOLD, 30));
        c.add(l10);
        l1.setBounds(30, 60, 100, 40);
        l1.setFont(new Font("Ariel", Font.BOLD, 13));
        t1.setBounds(180, 63, 190, 30);
        t1.setBorder(br2);

        l2.setBounds(30, 110, 100, 40);
        l2.setFont(new Font("Ariel", Font.BOLD, 14));
        t2.setBounds(180, 113, 190, 30);
        t2.setBorder(br2);

        l3.setBounds(30, 160, 100, 40);
        l3.setFont(new Font("Ariel", Font.BOLD, 13));
        t3.setBounds(180, 163, 190, 30);
        t3.setBorder(br2);

        l4.setBounds(30, 210, 100, 40);
        l4.setFont(new Font("Ariel", Font.BOLD, 13));
        t4.setBounds(180, 213, 190, 30);
        t4.setBorder(br2);

        l5.setBounds(30, 260, 100, 40);
        l5.setFont(new Font("Ariel", Font.BOLD, 13));
        t5.setBounds(180, 260, 100, 30);
        t5.setBorder(br2);

        l6.setBounds(30, 310, 100, 40);
        l6.setFont(new Font("Ariel", Font.BOLD, 13));
        t6.setBounds(180, 310, 190, 30);
        t6.setBorder(br2);

        l7.setBounds(30, 360, 100, 40);
        l7.setFont(new Font("Ariel", Font.BOLD, 13));
        t7.setBounds(180, 360, 190, 30);
        t7.setBorder(br2);

        l8.setBounds(30, 410, 100, 40);
        l8.setFont(new Font("Ariel", Font.BOLD, 13));
        t8.setBounds(180, 410, 190, 30);
        t8.setBorder(br2);

        l9.setBounds(30, 460, 100, 40);
        l9.setFont(new Font("Ariel", Font.BOLD, 13));
        t9.setBounds(180, 460, 190, 30);
        t9.setBorder(br2);

        b1.setBounds(50, 510, 100, 40);
        b1.setBackground(Color.black);
        b1.setForeground(Color.white);
        b1.setFont(new Font("Lucida Sans", Font.BOLD, 13));

        try {
            conn c = new conn();
            t1.setText(k);
            String name = "";
            ResultSet res = c.st.executeQuery("Select * from customer where username='" + k + "'");
            if (res.next() == true) {
                name = res.getString("name");
                t4.setText(name);
                t2.setText(res.getString("id"));
                t3.setText(res.getString("id_number"));
                t5.setText(res.getString("gender"));
                t6.setText(res.getString("country"));
                t7.setText(res.getString("address"));
                t8.setText(res.getString("phone"));
                t9.setText(res.getString("email"));
            }
        } catch (Exception f) {
            f.printStackTrace();
        }

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    conn c = new conn();
                    int count = 0;

                    String id = t2.getText();
                    String username = k;
                    String number = t3.getText();
                    String name = t4.getText();
                    String gender = t5.getText();
                    String country = t6.getText();
                    String address = t7.getText();
                    String phone = t8.getText();
                    String e_mail = t9.getText();

                    // Validate fields
                    count = (username.length() == 0) ? count + 1 : count;
                    count = (number.length() == 0) ? count + 1 : count;
                    count = (gender == null) ? count + 1 : count;
                    count = (id.length() == 0) ? count + 1 : count;
                    count = (country.length() == 0) ? count + 1 : count;
                    count = (address.length() == 0) ? count + 1 : count;
                    count = (phone.length() == 0) ? count + 1 : count;
                    count = (e_mail.length() == 0) ? count + 1 : count;

                    // Phone number validation
                    if (phone.length() != 10) {
                        JOptionPane.showMessageDialog(null, "Phone number must be exactly 10 digits.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Email validation
                    if (!e_mail.contains("@gmail.com")) {
                        JOptionPane.showMessageDialog(null, "Email must be a valid Gmail address.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    String query = "update customer set username ='" + username + "', id='" + id + "', id_number='" + number + "', name='" + name + "', gender='" + gender + "', country='" + country + "', address='" + address + "', phone='" + phone + "', email='" + e_mail + "' WHERE username='" + username + "'";

                    if (count > 0) {
                        JOptionPane.showMessageDialog(null, "Please update the details properly.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        c.st.executeUpdate(query);
                        JOptionPane.showMessageDialog(null, " ✌ PERSONAL DETAILS UPDATED ✌", "Message", JOptionPane.INFORMATION_MESSAGE);
                        setVisible(false);
                    }
                } catch (Exception j) {
                    j.printStackTrace();
                }
            }
        });

        b2.setBounds(200, 510, 100, 40);
        b2.setBackground(Color.black);
        b2.setForeground(Color.white);
        b2.setFont(new Font("Lucida Sans", Font.BOLD, 13));
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        img.setLayout(null);
        img.setBounds(400, 10, 430, 510);
        img.setBackground(Color.white);
        ImageIcon image_3 = new ImageIcon(ClassLoader.getSystemResource("icons/updatepage.gif"));
        ImageIcon image_4 = new ImageIcon(image_3.getImage().getScaledInstance(400, 450, Image.SCALE_DEFAULT));
        JLabel lk = new JLabel(image_4);
        lk.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lk.setBounds(0, 0, 430, 500);
        img.add(lk);

        c.add(img);
        c.add(b1);
        c.add(b2);
        c.add(l9);
        c.add(t9);
        c.add(l8);
        c.add(t8);
        c.add(l7);
        c.add(t7);
        c.add(l6);
        c.add(t6);
        c.add(l5);
        c.add(t5);
        c.add(l4);
        c.add(t4);
        c.add(l3);
        c.add(t3);
        c.add(l2);
        c.add(t2);
        c.add(l1);
        c.add(t1);
        setVisible(true);
    }

    public static void main(String[] args) {
        new updateCustomerDetails("");
    }
}

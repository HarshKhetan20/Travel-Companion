import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.sql.*;

public class addPersonalDetails extends JFrame {
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

    JTextField t1 = new JTextField();
    JTextField t3 = new JTextField();
    JTextField t4 = new JTextField();
    JTextField t6 = new JTextField();
    JTextField t7 = new JTextField();
    JTextField t8 = new JTextField();
    JTextField t9 = new JTextField();
    JRadioButton r1, r2;
    ButtonGroup g1 = new ButtonGroup();
    @SuppressWarnings({ "rawtypes", "unchecked" })
    JComboBox c1 = new JComboBox(new String[]{"Passport", "Adhaar Card", "Pan Card", "Ration Card"});

    JButton b1 = new JButton("ADD");
    JButton b2 = new JButton("BACK");

    JPanel img = new JPanel();
    String k = "";

    addPersonalDetails(String user) {
        k = user;
        setUndecorated(true);
        setBounds(400, 170, 850, 570);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        c = getContentPane();
        c.setBackground(Color.white);
        setLayout(null);
        Border br2 = BorderFactory.createLineBorder(Color.BLACK, 2);
        @SuppressWarnings("unused")
        Border br = BorderFactory.createLineBorder(Color.BLACK, 3);

        l1.setBounds(30, 30, 100, 40);
        l1.setFont(new Font("Ariel", Font.BOLD, 13));
        t1.setBounds(180, 33, 190, 30);
        t1.setBorder(br2);

        l2.setBounds(30, 80, 100, 40);
        l2.setFont(new Font("Ariel", Font.BOLD, 14));
        c1.setBounds(180, 83, 190, 30);

        l3.setBounds(30, 130, 100, 40);
        l3.setFont(new Font("Ariel", Font.BOLD, 13));
        t3.setBounds(180, 133, 190, 30);
        t3.setBorder(br2);

        l4.setBounds(30, 180, 100, 40);
        l4.setFont(new Font("Ariel", Font.BOLD, 13));
        t4.setBounds(180, 183, 190, 30);
        t4.setBorder(br2);

        l5.setBounds(30, 230, 100, 40);
        l5.setFont(new Font("Ariel", Font.BOLD, 13));
        r1 = new JRadioButton("MALE");
        r1.setBounds(180, 230, 80, 40);
        r1.setBackground(Color.white);
        r2 = new JRadioButton("FEMALE");
        r2.setBounds(260, 230, 80, 40);
        r2.setBackground(Color.white);
        g1.add(r1);
        g1.add(r2);

        l6.setBounds(30, 280, 100, 40);
        l6.setFont(new Font("Ariel", Font.BOLD, 13));
        t6.setBounds(180, 280, 190, 30);
        t6.setBorder(br2);

        l7.setBounds(30, 330, 100, 40);
        l7.setFont(new Font("Ariel", Font.BOLD, 13));
        t7.setBounds(180, 330, 190, 30);
        t7.setBorder(br2);

        l8.setBounds(30, 380, 100, 40);
        l8.setFont(new Font("Ariel", Font.BOLD, 13));
        t8.setBounds(180, 380, 190, 30);
        t8.setBorder(br2);

        l9.setBounds(30, 430, 100, 40);
        l9.setFont(new Font("Ariel", Font.BOLD, 13));
        t9.setBounds(180, 430, 190, 30);
        t9.setBorder(br2);

        b1.setBounds(50, 480, 100, 40);
        b1.setBackground(Color.black);
        b1.setForeground(Color.white);
        b1.setFont(new Font("Lucida Sans", Font.BOLD, 13));

        try {
            conn c = new conn();
            t1.setText(k);
            String name = "";
            ResultSet res = c.st.executeQuery("Select name from Account where username='" + k + "'");
            if (res.next() == true) {
                name = res.getString("name");
                t4.setText(name);
            }
            t1.setEditable(false);
            t4.setEditable(false);

        } catch (Exception f) {
            f.printStackTrace();

        }
        b1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    conn c = new conn();
                    int count = 0;
                    String username = k;
                    String id = c1.getSelectedItem().toString();
                    String number = t3.getText();
                    String name = t4.getText();
                    String gender = null;
                    if (r1.isSelected())
                        gender = "MALE";
                    else if (r2.isSelected())
                        gender = "FEMALE";

                    String country = t6.getText();
                    String address = t7.getText();
                    String phone = t8.getText();
                    String e_mail = t9.getText();

                    // Validation checks
                    if (username.length() == 0) count++;
                    if (number.length() == 0) count++;
                    if (id.length() == 0) count++;
                    if (gender == null) count++;
                    if (country.length() == 0) count++;
                    if (address.length() == 0) count++;
                    if (phone.length() == 0) count++;
                    if (e_mail.length() == 0) count++;

                    // Specific validation for phone and email
                    if (phone.length() != 10) {
                        JOptionPane.showMessageDialog(null, "PHONE NUMBER MUST BE 10 DIGITS", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (!e_mail.contains("@gmail")) {
                        JOptionPane.showMessageDialog(null, "E-MAIL MUST CONTAIN '@gmail'", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    String query = "insert into customer(username,id,id_number,name,gender,country,address,phone,email) values('" + username + "','" + id + "','" + number + "','" + name + "','" + gender + "','" + country + "','" + address + "','" + phone + "','" + e_mail + "')";

                    if (count > 0) {
                        JOptionPane.showMessageDialog(null, "FILL THE DETAILS PROPERLY", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        c.st.executeUpdate(query);
                        JOptionPane.showMessageDialog(null, " ✌ PERSONAL DETAILS ADDED ✌", "Message", JOptionPane.INFORMATION_MESSAGE);
                        setVisible(false);
                    }

                } catch (Exception j) {
                    j.printStackTrace();
                }

            }

        });

        b2.setBounds(200, 480, 100, 40);
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
        ImageIcon image_3 = new ImageIcon(ClassLoader.getSystemResource("icons/travelnew.png"));
        ImageIcon image_4 = new ImageIcon(image_3.getImage().getScaledInstance(400, 450, Image.SCALE_DEFAULT));
        JLabel lk = new JLabel(image_4);
        @SuppressWarnings("unused")
        Cursor cursor2 = l2.getCursor();
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
        c.add(r1);
        c.add(r2);
        c.add(l4);
        c.add(t4);
        c.add(l3);
        c.add(t3);
        c.add(l2);
        c.add(c1);
        c.add(l1);
        c.add(t1);

        setVisible(true);
    }

    public static void main(String[] args) {
        new addPersonalDetails("");
    }
}

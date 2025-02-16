import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class viewBookedHotel extends JFrame {

    JLabel l1 = new JLabel("VIEW BOOKED HOTEL");
    JLabel l2 = new JLabel("USERNAME :");
    JLabel l3 = new JLabel("SELECT HOTEL :");
    JLabel l4 = new JLabel("TOTAL PERSON :");
    JLabel l5 = new JLabel("ID :");
    JLabel l6 = new JLabel("NUMBER :");
    JLabel l7 = new JLabel("PHONE :");
    JLabel l8 = new JLabel("TOTAL PRICE :");
    JLabel l9 = new JLabel("");
    JLabel l10 = new JLabel("");
    JLabel l11 = new JLabel("");
    JLabel l12 = new JLabel("");
    JLabel l13 = new JLabel("");
    JLabel selectHotel = new JLabel("");
    JLabel t1 = new JLabel("");
    JButton b3 = new JButton("Back");

    viewBookedHotel(String user) {
        setBounds(330, 170, 1000, 530);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        Container c = getContentPane();
        c.setBackground(Color.white);
        Font f1 = new Font("Gill Sans Ultra Bold Condensed", Font.PLAIN, 30);
        Font f2 = new Font("Tahoma Bold", Font.BOLD, 15);
        c.setLayout(null);
        
        // Image for background (if needed)
        ImageIcon image = new ImageIcon(ClassLoader.getSystemResource("icons/bookedDetails.jpg"));
        ImageIcon image_0 = new ImageIcon(image.getImage().getScaledInstance(500, 300, Image.SCALE_DEFAULT));
        JLabel l0 = new JLabel(image_0);
        l0.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        l0.setBounds(480, 30, 500, 300);
    
        c.add(l0);

        // Set bounds and fonts for labels
        l1.setBounds(120, 10, 500, 40);
        l1.setFont(f1);
        l1.setForeground(Color.BLACK);
        
        l2.setBounds(25, 90, 500, 40);
        l2.setFont(f2);
        l9.setBounds(285, 90, 500, 40);
        l9.setFont(f2);

        l3.setBounds(25, 140, 500, 40);
        l3.setFont(f2);
        selectHotel.setBounds(285, 140, 160, 30);
        selectHotel.setFont(new Font("Tahoma", Font.BOLD, 15));

        l4.setBounds(25, 190, 500, 40);
        l4.setFont(f2);
        t1.setBounds(285, 190, 160, 30);
        t1.setFont(f2);
        t1.setBackground(new Color(200, 250, 100));

        l5.setBounds(25, 230, 500, 40);
        l5.setFont(f2);
        l10.setBounds(285, 230, 300, 40);
        l10.setFont(f2);

        l6.setBounds(25, 280, 500, 40);
        l6.setFont(f2);
        l11.setBounds(285, 280, 300, 40);
        l11.setFont(f2);

        l7.setBounds(25, 330, 500, 40);
        l7.setFont(f2);
        l12.setBounds(285, 330, 300, 40);
        l12.setFont(f2);
       
        l8.setBounds(25, 380, 500, 40);
        l8.setFont(f2);
        l13.setBounds(285, 380, 300, 40);
        l13.setFont(f2);
        l13.setForeground(Color.red);

        try {
            conn ck = new conn();
            String query = "SELECT * FROM bookhotel WHERE username='" + user + "'";
            l9.setText(user);
            ResultSet res = ck.st.executeQuery(query);
            if (res.next()) {
                t1.setText(res.getString("persons"));
                selectHotel.setText(res.getString("hotel"));
                l10.setText(res.getString("id"));
                l11.setText(res.getString("id_number"));
                l12.setText(res.getString("phone"));
                l13.setText(res.getString("price"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        b3.setBounds(180, 450, 140, 40);
        b3.setFont(f2);
        b3.setBackground(Color.DARK_GRAY);
        b3.setForeground(Color.white);
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        // Adding components to the container
        c.add(b3);
        c.add(t1);
        c.add(selectHotel);
        c.add(l13);
        c.add(l12);
        c.add(l11);
        c.add(l10);
        c.add(l9);
        c.add(l8);
        c.add(l7);
        c.add(l6);
        c.add(l5);
        c.add(l4);
        c.add(l3);
        c.add(l2);
        c.add(l1);

        setVisible(true);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            System.out.println("Look and Feel not set");
        }
        new viewBookedHotel("").setVisible(true);
    }
}

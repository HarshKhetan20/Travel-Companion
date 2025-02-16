import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class bookPackage extends JFrame{

    JLabel l1=new JLabel("BOOK PACKAGE");
    JLabel l2=new JLabel("USERNAME :");
    JLabel l3=new JLabel("SELECT PACKAGE :");
    JLabel l4=new JLabel("TOTAL PERSON :");
    JLabel l5=new JLabel("ID :");
    JLabel l6=new JLabel("NUMBER :");
    JLabel l7=new JLabel("PHONE :");
    JLabel l8=new JLabel("TOTAL PRICE :");
    JLabel l9=new JLabel("");
    JLabel l10=new JLabel("");
    JLabel l11=new JLabel("");
    JLabel l12=new JLabel("");
    JLabel l13=new JLabel("");
    String arr[]={"Gold Package","Silver Package","Bronze Package"};
    @SuppressWarnings({ "rawtypes", "unchecked" })
    JComboBox selectPackage =new JComboBox(arr);
    JTextField t1=new JTextField();

    JButton b1=new JButton("Check Price");
    JButton b2=new JButton("Book Package");
    JButton b3=new JButton("Back");


    bookPackage(String user){

        setBounds(330,170,1000,530);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        Container c=getContentPane();
        c.setBackground(Color.white);
        Font f1=new Font("Gill Sans Ultra Bold Condensed",Font.PLAIN,30);
        Font f2=new Font("Tahoma Bold",Font.BOLD,15);
        c.setLayout(null);
        ImageIcon image=new ImageIcon(ClassLoader.getSystemResource("icons/bookpackage.jpg"));
        ImageIcon image_0=new ImageIcon(image.getImage().getScaledInstance(500,300,Image.SCALE_DEFAULT));
        JLabel l0=new JLabel(image_0);
        l0.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        l0.setBounds(480,30,500,300);

        ImageIcon image1=new ImageIcon(ClassLoader.getSystemResource("icons/spinner1.gif"));
        ImageIcon image_1=new ImageIcon(image1.getImage().getScaledInstance(150,150,Image.SCALE_DEFAULT));
        JLabel l01=new JLabel(image_1);
        l01.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        l01.setBounds(480,270,500,300);
        c.add(l0);
        c.add(l01);

        l1.setBounds(120,10,500,40);
        l1.setFont(f1);
        l1.setForeground(Color.BLACK);

        l2.setBounds(25,90,500,40);
        l2.setFont(f2);
        l9.setBounds(285,90,500,40);
        l9.setFont(f2);

        l3.setBounds(25,140,500,40);
        l3.setFont(f2);
        selectPackage.setBounds(285,140,160,30);
        selectPackage.setFont(new Font("Tahoma",Font.BOLD,15));

        l4.setBounds(25,190,500,40);
        l4.setFont(f2);
        t1.setBounds(285,190,160,30);
        t1.setFont(f2);
        t1.setBackground(new Color(200,250,100));

        l5.setBounds(25,230,500,40);
        l5.setFont(f2);
        l10.setBounds(285,230,300,40);
        l10.setFont(f2);

        l6.setBounds(25,280,500,40);
        l6.setFont(f2);
        l11.setBounds(285,280,300,40);
        l11.setFont(f2);

        l7.setBounds(25,330,500,40);
        l7.setFont(f2);
        l12.setBounds(285,330,300,40);
        l12.setFont(f2);
       

        l8.setBounds(25,380,500,40);
        l8.setFont(f2);
        l13.setBounds(285,380,300,40);
        l13.setFont(f2);
        l13.setForeground(Color.red);
        try{
            conn ck=new conn();
            String query="Select * from customer where username='"+user+"'";
            l9.setText(user);
            ResultSet res= ck.st.executeQuery(query);
            if(res.next()==true){
                l10.setText(res.getString("id"));
                l11.setText(res.getString("id_number"));
                l12.setText(res.getString("phone"));
            }

            
        }catch(Exception e){
            e.printStackTrace();
        }

        b1.setBounds(40,450,130,40);
        b1.setFont(f2);
        b1.setBackground(Color.DARK_GRAY);
        b1.setForeground(Color.white);
        b1.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                
                String Pack=selectPackage.getSelectedItem().toString();
                int num=0;
                boolean ans=isNumeric(t1.getText());
                if(ans==false){
                    JOptionPane.showMessageDialog(null,"Entered value is empty or not a number","Error",JOptionPane.ERROR_MESSAGE);
                }
                else{
                    num=Integer.parseInt(t1.getText());
                    switch(Pack){
                        case "Gold Package":
                        num=num*12000;
                        break;
                        case "Silver Package":
                        num=num*25000;
                        break;
                        case "Bronze Package":
                        num=num*32000;
                        break;
    
                    }
                    l13.setText(Integer.toString(num));

                }
                
            }
         
            /**
             * @param string
             * @return
             */
            public boolean isNumeric(String string) {
                @SuppressWarnings("unused")
                int intValue;
                    
                System.out.println(String.format("Parsing string: \"%s\"", string));
                    
                if(string == null || string.equals("") || string.equals("0")) {
                    System.out.println("String cannot be parsed, it is null or empty.");
                    return false;
                }
                
                try {
                    intValue = Integer.parseInt(string);
                    return true;
                } catch (NumberFormatException e) {
                    System.out.println("Input String cannot be parsed to Integer.");
                }
                return false;
            }
            
        });

        b2.setBounds(180,450,140,40);
        b2.setFont(f2);
        b2.setBackground(Color.DARK_GRAY);
        b2.setForeground(Color.white);
        b2.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                try{

                    conn c=new conn();
                    String username=l9.getText();
                    String pack=selectPackage.getSelectedItem().toString();
                    String persons=t1.getText();
                    String id=l10.getText();
                    String id_number=l11.getText();
                    String phone=l12.getText();
                    String total_price=l13.getText();
                    String query="insert into bookpackage(username,package,persons,id,id_number,price,phone) values('"+username+"','"+pack+"','"+persons+"','"+id+"','"+id_number+"','"+total_price+"','"+phone+"')";
                    String query2="Select * from bookpackage where username='"+username+"'";
                    ResultSet res = c.st.executeQuery(query2);
                    if (res.next() == true) {
                        String package2 = res.getString("package");
                        int a = JOptionPane.showConfirmDialog(null, package2 + " has already been booked with this username\nDo you want to Update your Package?");
                        if (a == JOptionPane.YES_OPTION) {
                            // Update the package if the user clicks YES
                            String updatequery = "UPDATE bookpackage SET package='" + pack + "', persons='" + persons + "', price='" + total_price + "' WHERE username='" + username + "'";
                            c.st.executeUpdate(updatequery);
                            JOptionPane.showMessageDialog(null, "Package updated successfully.", "Message", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            // If the user clicks NO, add a new booking
                            String insertQuery = "INSERT INTO bookpackage (username, package, persons, price) VALUES ('" + username + "', '" + pack + "', '" + persons + "', '" + total_price + "')";
                            c.st.executeUpdate(insertQuery);
                            JOptionPane.showMessageDialog(null, "New booking added.", "Message", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else {
                        // Proceed with the new booking if no previous record exists
                        c.st.executeUpdate(query);
                        JOptionPane.showMessageDialog(null, " ✌ Package Booked successfully ✌", "Message", JOptionPane.INFORMATION_MESSAGE);
                    }
                    
                   
                    
                    setVisible(false);

                    


                }
                catch(Exception ef){
                    ef.printStackTrace();
                }
                
            }
            
        });

        b3.setBounds(330,450,100,40);
        b3.setFont(f2);
        b3.setBackground(Color.DARK_GRAY);
        b3.setForeground(Color.white);
        b3.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                
            }
            
        });

        c.add(b3);
        c.add(b1);
        c.add(b2);
        c.add(t1);
        c.add(selectPackage);
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
        }
        catch (Exception e) {
            System.out.println("Look and Feel not set");
        }
        new bookPackage("").setVisible(true);
    }

    
}

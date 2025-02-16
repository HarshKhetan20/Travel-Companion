import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
public class dashboard extends JFrame{
    JPanel p1=new JPanel();
    JPanel p2=new JPanel();
    JLabel d1=new JLabel("DASHBOARD");
    JLabel d2=new JLabel();
    JButton b1=new JButton();
    JButton b2=new JButton();
    JButton b3=new JButton();
    JButton b4=new JButton();
    JButton b5=new JButton();
    JButton b6=new JButton();
    JButton b7=new JButton();
    JButton b8=new JButton();
    JButton b9=new JButton();
    JButton b10=new JButton();
    JButton b11=new JButton();
    JButton b12=new JButton();
    JButton b13=new JButton();
    JButton b14=new JButton();
    JButton b15=new JButton();
    String x="";

    dashboard(String user){
        x=user;
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(null);
        // setUndecorated(true);
        Container c=getContentPane();
        p1.setLayout(null);
        p1.setBounds(0,0,1950,80);
        p1.setBackground(new Color(120,150,189));
        c.add(p1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon image1=new ImageIcon(ClassLoader.getSystemResource("icons/home.jpg"));
        ImageIcon image_1=new ImageIcon(image1.getImage().getScaledInstance(1950, 1000,Image.SCALE_DEFAULT));
        JLabel l1=new JLabel(image_1);
        l1.setBounds(0,0,1950,1000);
        ImageIcon image2=new ImageIcon(ClassLoader.getSystemResource("icons/dashboard2.png"));
        ImageIcon image_2=new ImageIcon(image2.getImage().getScaledInstance(60, 65,Image.SCALE_DEFAULT));
        JLabel l2=new JLabel(image_2);
        JLabel ll=new JLabel("TRAVEL AND TOURISM MANAGEMENT SYSTEM");
        ll.setBounds(500,100,800,40);
        ll.setFont(new Font("Algerian",Font.BOLD,30));
        ll.setForeground(Color.white);
        c.add(ll);
        l2.setBounds(10,10,70,60);
        d1.setBounds(90,10,400,60);
        d1.setFont(new Font("Algerian",Font.BOLD,40));
        d1.setForeground(Color.white);
        d2.setBounds(1200,10,400,60);
        d2.setText("WELCOME "+x);
        d2.setFont(new Font("Tahoma",Font.BOLD,25));
        p2.setBounds(0,82,250,1000);
        p2.setLayout(null);
        p2.setBackground(new Color(230,230,220));
        c.add(p2);
        p1.add(d2);
        p1.add(d1);
        p1.add(l2);
        c.add(l1);
        // Buttons
        Font f=new Font("Monotype Corsiva",Font.BOLD,18);
        b1.setBounds(5,6,240,40);
        b1.setText("Add Personal Details");
        b1.setFont(f);
        b1.setMargin(new Insets(0,0,0,40));
        b1.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                try{

                    conn c=new conn();
                    String query2="Select username,name from customer where username='"+x+"'";
                    ResultSet res=c.st.executeQuery(query2);
                    if(res.next()==true){
                        JOptionPane.showMessageDialog(null," ✌ PERONAL DETAILS ALREADY ADDED ✌","Message",JOptionPane.INFORMATION_MESSAGE);                        
                    }
                    else{
                        new addPersonalDetails(user);
                    }
                }
                catch(Exception ef){
                    ef.printStackTrace();

                }
                
                
            }
            
        });
        b2.setBounds(5,47,240,40);
        b2.setText("Update Personal Details");
        b2.setFont(f);
        b2.setMargin(new Insets(0,0,0,20));
        b2.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                new updateCustomerDetails(x);
                
            }
            
        });
        b3.setBounds(5,87,240,40);
        b3.setText("View Details");
        b3.setFont(f);
        b3.setMargin(new Insets(0,0,0,100));
        b3.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                new viewDetails(x);                
            }
            
        });
        b4.setBounds(5,127,240,40);
        b4.setText("Delete Personal Details");
        b4.setFont(f);
        b4.setMargin(new Insets(0,0,0,30));
        b4.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
               new deleteCustomer(x).setVisible(true);;
                
                
            }
            
        });
        b5.setBounds(5,167,240,40);
        b5.setText("Check Package");
        b5.setFont(f);
        b5.setMargin(new Insets(0,0,0,90));
        b5.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                new checkPackage();
                
            }
            
        });
        b6.setBounds(5,207,240,40);
        b6.setText("Book Package");
        b6.setFont(f);
        b6.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                new bookPackage(x);
                
            }
            
        });
        
        b6.setMargin(new Insets(0,0,0,95));
        b7.setBounds(5,247,240,40);
        b7.setText("View Package");
        b7.setFont(f);
        b7.setMargin(new Insets(0,0,0,100));
        b7.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                new viewPackage(x);
                
            }
            
        });

        b8.setBounds(5,287,240,40);
        b8.setText("View Hotels");
        b8.setFont(f);
        b8.setMargin(new Insets(0,0,0,110));
        b8.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                new checkHotels();
                
            }
            
        });
        b9.setBounds(5,327,240,40);
        b9.setText("Book Hotel");
        b9.setFont(f);
        b9.setMargin(new Insets(0,0,0,115));
        b9.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                new bookHotel(x);
                
            }
            
        });
        b10.setBounds(5, 367, 240, 40);
        b10.setText("View Booked Hotel");
        b10.setFont(f);
        b10.setMargin(new Insets(0, 0, 0, 60));
        b10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new viewBookedHotel(x); // Create an instance of viewBookedHotel
            }
        });

       // Assuming this code is inside your JFrame or JPanel setup method
    b11.setBounds(5, 407, 240, 40);
    b11.setText("Destinations");
    b11.setFont(f);
    b11.setMargin(new Insets(0, 0, 0, 105));

// Add action listener to the button
    b11.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        new Destination(); // Create an instance of the Destination class
    }
    });

        // Example of your button b12 setup in the Dashboard class
        b12.setBounds(5, 447, 240, 40);
        b12.setText("Payment");
        b12.setFont(f);
        b12.setMargin(new Insets(0, 0, 0, 130));
        
        // Add action listener to open the Payment window
        b12.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the Payment window with default values (0.0 for hotel and package costs)
                new Payment(user);
            }
        });
        

        b13.setBounds(5,487,240,40);
        b13.setText("Calculator");
        b13.setFont(f);
        b13.setMargin(new Insets(0,0,0,120));
        b13.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
               try{
                   Runtime.getRuntime().exec("calc.exe");

               }
               catch(Exception ef){

               }
            }

        });
        b14.setBounds(5,527,240,40);
        b14.setText("NotePad");
        b14.setFont(f);
        b14.setMargin(new Insets(0,0,0,130));
        b14.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
               try{
                   Runtime.getRuntime().exec("notepad.exe");

               }
               catch(Exception ef){

               }
            }

        });
        b15.setBounds(5,567,240,40);
        b15.setText("About");
        b15.setFont(f);
        b15.setMargin(new Insets(0,0,0,150));
        b15.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
               new About();
            }

        });
        JButton b16 = new JButton(); // Create the Review button
b16.setBounds(5, 620, 240, 40); // Set the position and size
b16.setText("Review"); // Set the text on the button
b16.setFont(f); // Set the font as you have done for other buttons
b16.setMargin(new Insets(0, 0, 0, 150)); // Set margins like the About button

// Add action listener to handle button clicks
b16.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        new Review(user); // Open the Review class when the button is clicked
    }
});

// Add the Review button to your panel or frame
p2.add(b16); // Assuming p2 is your panel where buttons are added
        



        

        
        p2.add(b1);
        p2.add(b2);
        p2.add(b3);
        p2.add(b4);
        p2.add(b5);
        p2.add(b6);
        p2.add(b7);
        p2.add(b8);
        p2.add(b9);
        p2.add(b10);
        p2.add(b11);
        p2.add(b12);
        p2.add(b13);
        p2.add(b14);
        p2.add(b15);
        setVisible(true);
    }
    public static void main(String[] args) {
        try {
  
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        }
        catch (Exception e) {
            System.out.println("Look and Feel not set");
        }
        
        
        new dashboard("N/A");
    }
}

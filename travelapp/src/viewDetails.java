import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
// import javax.swing.border.Border;
import java.sql.*;
public class viewDetails extends JFrame{
    Container c;
    JLabel l1=new JLabel("USERNAME : ");
    JLabel l2=new JLabel("ID :");
    JLabel l3=new JLabel("NUMBER : ");
    JLabel l4=new JLabel("NAME : ");
    JLabel l5=new JLabel("GENDER : ");
    JLabel l6=new JLabel("COUNTRY : ");
    JLabel l7=new JLabel("ADDRESS : ");
    JLabel l8=new JLabel("PHONE : ");
    JLabel l9=new JLabel("E-MAIL : ");
    JLabel l10=new JLabel("VIEW CUSTOMER DETAILS");

    JLabel t1=new JLabel();
    JLabel t2=new JLabel();
    JLabel t3=new JLabel();
    JLabel t4=new JLabel();
    JLabel t5=new JLabel();
    JLabel t6=new JLabel();
    JLabel t7=new JLabel();
    JLabel t8=new JLabel();
    JLabel t9=new JLabel();
   
    ButtonGroup g1=new ButtonGroup();
    JButton b1=new JButton("BACK");

    JPanel img=new JPanel();
    String k="";
    viewDetails(String user){
        k=user;
        setUndecorated(true);
        setBounds(400,170,880,580);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        c=getContentPane();
        c.setBackground(Color.white);
        setLayout(null);
        // Border br2=BorderFactory.createLineBorder(Color.BLACK,2);

        Font ft=new Font("Trebuchet MS",Font.CENTER_BASELINE,15);

         l10.setBounds(300,10,300,40);
         l10.setFont(new Font("Agency FB",Font.BOLD,30));
         c.add(l10);
         l1.setBounds(80,60,200,40);
         l1.setFont(new Font("Ariel",Font.BOLD,13));
         t1.setBounds(250,63,250,30);
         t1.setFont(ft);
         

         l2.setBounds(85,110,200,40);
         l2.setFont(new Font("Ariel",Font.BOLD,14));
         t2.setBounds(250,113,250,30);
         t2.setFont(ft);

         

         l3.setBounds(80,160,200,40);
         l3.setFont(new Font("Ariel",Font.BOLD,13));
         t3.setBounds(250,163,250,30);
         t3.setFont(ft);
         

         l4.setBounds(80,210,200,40);
         l4.setFont(new Font("Ariel",Font.BOLD,13));
         t4.setBounds(250,213,250,30);
         t4.setFont(ft);
         

         l5.setBounds(80,260,200,40);
         l5.setFont(new Font("Ariel",Font.BOLD,13));
         t5.setBounds(250,260,250,30);
         t5.setFont(ft);
         


         l6.setBounds(450,60,200,40);
         l6.setFont(new Font("Ariel",Font.BOLD,13));
         t6.setBounds(590,60,250,30);
         t6.setFont(ft);
         

         l7.setBounds(450,110,200,40);
         l7.setFont(new Font("Ariel",Font.BOLD,13));
         t7.setBounds(590,110,250,30);
         t7.setFont(ft);
         

         l8.setBounds(450,160,200,40);
         l8.setFont(new Font("Ariel",Font.BOLD,13));
         t8.setBounds(590,160,250,30);
         t8.setFont(ft);
         

         l9.setBounds(450,210,200,40);
         l9.setFont(new Font("Ariel",Font.BOLD,13));
         t9.setBounds(590,210,250,30);
         t9.setFont(ft);
         

         b1.setBounds(350,300,100,40);
         b1.setBackground(Color.black);
         b1.setForeground(Color.white);
         b1.setFont(new Font("Lucida Sans",Font.BOLD,13));
         try{
             conn c=new conn();
             t1.setText(k);
             String name="";
             ResultSet res=c.st.executeQuery("Select * from customer where username='"+k+"'");
             if(res.next()==true){
                name=res.getString("name");
                t4.setText(name);
                t2.setText(res.getString("id"));
                t3.setText(res.getString("id_number"));
                t5.setText(res.getString("gender"));
                t6.setText(res.getString("country"));
                t7.setText(res.getString("address"));
                t8.setText(res.getString("phone"));
                t9.setText(res.getString("email"));
            }
            

         }
         catch(Exception f){
             f.printStackTrace();

         }
         b1.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                 setVisible(false);
                
                }
         });


         img.setLayout(null);
         img.setBounds(50,400,800,510);
         img.setBackground(Color.white);
        
         ImageIcon image_3=new ImageIcon(ClassLoader.getSystemResource("icons/viewall.jpg"));
         ImageIcon image_4=new ImageIcon(image_3.getImage().getScaledInstance(600,200,Image.SCALE_DEFAULT));
         JLabel lk=new JLabel(image_4);
         lk.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
         lk.setBounds(0,0,750,200);
         img.add(lk);
         
         c.add(img);
         c.add(b1);
         
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
        try {
  
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        }
        catch (Exception e) {
            System.out.println("Look and Feel not set");
        }
        
        
        new viewDetails("N/A");
    }

}


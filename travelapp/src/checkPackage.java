import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
class checkPackage extends JFrame{

    checkPackage(){
        setBounds(360,170,1000,620);
        JTabbedPane pane=new JTabbedPane();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        String[] package1 = new String[]{"icons/package11.gif","icons/gold.gif","6 days and 7 Nights", "Airport Assistance at Aiport", "Half Day City Tour", "Welcome drinks on Arrival", "Daily Buffet", "Full Day 3 Island Cruise", "English Speaking Guide", "BOOK NOW", "icons/summerspecial.gif", "Rs 12000/-"};
        String[] package2 = new String[]{"icons/package12.gif","icons/silver.gif","4 days and 3 Nights", "Toll Free and Entrance Free Tickets", "Meet and Greet at Aiport", "Welcome drinks on Arrival", "Night Safari", "Full Day 3 Island Cruise", "Cruise with Dinner", "BOOK NOW", "icons/winterspecial.gif", "Rs 25000/-"};
        String[] package3 = new String[]{"icons/package13.gif","icons/bronze.gif","6 days and 5 Nights", "Return Airfare", "Daily Buffet", "Welcome drinks on Arrival", "Free Clubbing, Horse Riding & other games", "Stay in 5 Star Hotel", "BBQ Dinner", "BOOK NOW", "icons/winterspecial.gif", "Rs 32000/-"};
        JPanel p1=createPackage(package1);
        JLabel l1=new JLabel("Heeloo");
        p1.add(l1);
        JPanel p2=createPackage(package2);
        JLabel l2=new JLabel("Subhojeet");
        p2.add(l2);
        JPanel p3=createPackage(package3);
        JLabel l3=new JLabel("Ghosh");
        p3.add(l3);
        pane.addTab("Package 1",null,p1);
        pane.addTab("Package 2",null,p2);
        pane.addTab("Package 3",null,p3);

        add(pane,BorderLayout.CENTER);
        setVisible(true);


    }
    private JPanel createPackage(String [] pack) {
        JPanel p1=new JPanel();
        p1.setLayout(null);
        p1.setBackground(Color.WHITE);
        Font f1=new Font("Gill Sans Ultra Bold Condensed",Font.PLAIN,20);
        Font f2=new Font("Cooper Black",Font.BOLD,25);
        ImageIcon image_1=new ImageIcon(ClassLoader.getSystemResource(pack[1]));
        ImageIcon image_2=new ImageIcon(image_1.getImage().getScaledInstance(270,40,Image.SCALE_DEFAULT));
        JLabel l1=new JLabel(image_2);
        l1.setBounds(10,0,300,100);
        p1.add(l1);
        JLabel l2=new JLabel(pack[2]);
        l2.setBounds(30,100,300,40);
        l2.setForeground(Color.RED);
        l2.setFont(f1);
        JLabel l3=new JLabel(pack[3]);
        l3.setBounds(30,160,400,40);
        l3.setForeground(Color.BLUE);
        l3.setFont(f1);
        JLabel l4=new JLabel(pack[4]);
        l4.setBounds(30,220,300,40);
        l4.setForeground(Color.RED);
        l4.setFont(f1);
        JLabel l5=new JLabel(pack[5]);
        l5.setBounds(30,280,300,40);
        l5.setForeground(Color.BLUE);
        l5.setFont(f1);
        JLabel l6=new JLabel(pack[6]);
        l6.setBounds(30,340,400,40);
        l6.setForeground(Color.RED);
        l6.setFont(f1);
        JLabel l7=new JLabel(pack[7]);
        l7.setBounds(30,400,300,40);
        l7.setForeground(Color.BLUE);
        l7.setFont(f1);
        JLabel l8=new JLabel(pack[8]);
        l8.setBounds(30,460,300,40);
        l8.setForeground(Color.RED);
        l8.setFont(f1);
        JLabel l9=new JLabel(pack[9]);
        l9.setBounds(30,530,300,40);
        l9.setForeground(Color.BLUE);
        l9.setFont(f2);
        ImageIcon image_3=new ImageIcon(ClassLoader.getSystemResource(pack[10]));
        ImageIcon image_4=new ImageIcon(image_3.getImage().getScaledInstance(240,40,Image.SCALE_DEFAULT));
        JLabel l10=new JLabel(image_4);
        l10.setBounds(350,530,300,40);
        l10.setForeground(Color.GREEN);
        l10.setFont(f2);
        JLabel l11=new JLabel(pack[11]);
        l11.setBounds(800,530,300,40);
        l11.setForeground(Color.RED);
        l11.setFont(f2);
        ImageIcon image_5=new ImageIcon(ClassLoader.getSystemResource(pack[0]));
        ImageIcon image_6=new ImageIcon(image_5.getImage().getScaledInstance(600,350,Image.SCALE_DEFAULT));
        JLabel l12=new JLabel(image_6);
        l12.setBounds(380,20,600,300);
        JButton b1=new JButton("CLOSE");
        b1.setBackground(new Color(200,230,100));
        b1.setForeground(Color.BLACK);
        b1.setFont(f1);
        b1.setBounds(870,350,100,40);
        b1.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
             setVisible(false);
                
            }
            
        });;


        p1.add(b1);
        p1.add(l2);
        p1.add(l3);
        p1.add(l4);
        p1.add(l5);
        p1.add(l6);
        p1.add(l7);
        p1.add(l8);
        p1.add(l9);
        p1.add(l10);
        p1.add(l11);
        p1.add(l12);
        
        return p1;
    }
    public static void main(String[] args) {
        try {
  
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        }
        catch (Exception e) {
            System.out.println("Look and Feel not set");
        }
        new checkPackage();
    }
}
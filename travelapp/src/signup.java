import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
public class signup extends JFrame{
    JPanel signPanel=new JPanel();
    JPanel p2=new JPanel();
    JLabel usernameJLabel=new JLabel("USERNAME");
    JLabel nameJLabel=new JLabel("NAME");
    JLabel answerJLabel=new JLabel("ANSWER");
    JLabel passwordLabel=new JLabel("PASSWORD");
    JLabel securityLabel=new JLabel("SECURITY QUESTION");
    JButton Register = new JButton("REGISTER");
    JButton Back=new JButton("BACK");
    JTextField userTextField=new JTextField();
    JTextField nameTextField= new JTextField();
    JTextField ansTextField=new JTextField();
    JPasswordField passwordField =new JPasswordField();
    String arr[]={"Your pet name","Your favourite character in avengers","Your Best Friends Name"};
    @SuppressWarnings({ "rawtypes", "unchecked" })
    JComboBox securityTextField =new JComboBox(arr);
    
    
    signup(){
        setBounds(300,150,955,440);
        Container c=getContentPane();
        c.setBackground(Color.white);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        // Border br=BorderFactory.createLineBorder(Color.GRAY,2);
        // Border br1=BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
        Border br2=BorderFactory.createLineBorder(Color.BLACK,3);
        Font f1=new Font("Ariel",Font.BOLD,13);
        signPanel.setLayout(null);
        signPanel.setBounds(0,0,500,400);
        signPanel.setBackground(new Color(131,193,233));
        usernameJLabel.setBounds(30,60,100,50);
        usernameJLabel.setFont(f1);
        userTextField.setBounds(180,70,200,30);
        userTextField.setFont(f1);
        nameJLabel.setBounds(30,110,100,50);
        nameJLabel.setFont(f1);
        nameTextField.setBounds(180,120,200,30);
        nameTextField.setFont(f1);
        passwordLabel.setBounds(30,160,100,50);
        passwordLabel.setFont(f1);
        passwordField.setBounds(180,170,150,30);
        passwordField.setFont(f1);
        securityLabel.setBounds(30,210,140,50);
        securityLabel.setFont(f1);
        securityTextField.setBounds(180,220,300,30);
        answerJLabel.setBounds(30,260,100,50);
        answerJLabel.setFont(f1);
        ansTextField.setBounds(180,270,200,30);
        ansTextField.setFont(f1);
        Back.setBounds(260,330,100,30);
        Back.setForeground( Color.BLUE);
        Back.setBackground(Color.white);
        Back.setBorder(new BevelBorder(BevelBorder.RAISED));
        userTextField.setBorder(new BevelBorder(BevelBorder.LOWERED));
        nameTextField.setBorder(new BevelBorder(BevelBorder.LOWERED));
        passwordField.setBorder(new BevelBorder(BevelBorder.LOWERED));
        ansTextField.setBorder(new BevelBorder(BevelBorder.LOWERED));
        securityTextField.setBorder(new BevelBorder(BevelBorder.LOWERED));
        Back.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new login();
                
            }
            
        });
        Register.setBounds(100,330,100,30);
        Register.setForeground(Color.BLUE);
        Register.setBackground(Color.white);
        Register.setBorder(new BevelBorder(BevelBorder.RAISED));
        Register.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    conn c=new conn();
                    String username=userTextField.getText();
                    String name=nameTextField.getText();
                    char ch[]=passwordField.getPassword();
                    String PASSWORD="";
                    for(int i=0;i<ch.length;i++){PASSWORD=PASSWORD+ch[i];}
                    String security=securityTextField.getSelectedItem().toString();
                    String ans=ansTextField.getText();
                    int count=0;
                    count=(username.length()==0)?count+1:count;
                    count=(name.length()==0)?count+1:count;
                    count=(PASSWORD.length()==0)?count+1:count;
                    count=(security.length()==0)?count+1:count;
                    count=(ans.length()==0)?count+1:count;

                    ResultSet res=c.st.executeQuery("select username,name from Account where username='"+username+"' or name='"+name+"'");
                    if(res.next()==true){
                        JOptionPane.showMessageDialog(null,"Already Registered Try Again","Error",JOptionPane.ERROR_MESSAGE);
                    }
                    else if(count>0){
                        JOptionPane.showMessageDialog(null,"FILL THE FORM  PROPERLY","Error",JOptionPane.ERROR_MESSAGE);
                    }
                    else{
                        String x="insert into Account(username,name,password,security,answer) values('"+username+"','"+name+"','"+PASSWORD+"','"+security+"','"+ans+"')";
                        c.st.executeUpdate(x);
                        JOptionPane.showMessageDialog(null," ✌ User Registered ✌","Message",JOptionPane.INFORMATION_MESSAGE);
                       
                    }


                }
                catch(Exception j){
                    j.printStackTrace();
                }
                
                
            }
            
        });
        signPanel.add(Register);
        signPanel.add(Back);
        signPanel.add(answerJLabel);
        signPanel.add(securityLabel);
        signPanel.add(passwordLabel);
        signPanel.add(nameJLabel);
        signPanel.add(usernameJLabel);
        signPanel.add(userTextField);
        signPanel.add(nameTextField);
        signPanel.add(passwordField);
        signPanel.add(securityTextField);
        signPanel.add(ansTextField);
        c.add(signPanel);
        //end of signPanel
        p2.setBounds(506,0,430,400);
        p2.setLayout(null);
        p2.setBackground(Color.white);
        c.add(p2);
        ImageIcon image=new ImageIcon(ClassLoader.getSystemResource("icons/luggage.gif"));
        ImageIcon image_0=new ImageIcon(image.getImage().getScaledInstance(130,90,Image.SCALE_DEFAULT));
        JLabel l0=new JLabel(image_0);
        // Cursor cursor0 = l0.getCursor();
        l0.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        l0.setBounds(10,20,90,70);
        p2.add(l0);

        ImageIcon image_1=new ImageIcon(ClassLoader.getSystemResource("icons/signup.gif"));
        ImageIcon image_2=new ImageIcon(image_1.getImage().getScaledInstance(200, 200,Image.SCALE_DEFAULT));
        JLabel l1=new JLabel(image_2);
        // Cursor cursor = l1.getCursor();
        l1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        l1.setBounds(120,90,200,200);
        p2.add(l1);
        ImageIcon image_3=new ImageIcon(ClassLoader.getSystemResource("icons/compass.gif"));
        ImageIcon image_4=new ImageIcon(image_3.getImage().getScaledInstance(130,100,Image.SCALE_DEFAULT));
        JLabel l2=new JLabel(image_4);
        // Cursor cursor2 = l2.getCursor();
        l2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        l2.setBounds(20,315,70,65);
        l2.setToolTipText("Whatsapp number");
        p2.add(l2);
        ImageIcon image_5=new ImageIcon(ClassLoader.getSystemResource("icons/plane.gif"));
        ImageIcon image_6=new ImageIcon(image_5.getImage().getScaledInstance(190,170,Image.SCALE_DEFAULT));
        JLabel l3=new JLabel(image_6);
        // Cursor cursor3 = l3.getCursor();
        l3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        l3.setBounds(330,5,90,110);
        p2.add(l3);
        ImageIcon image_9=new ImageIcon(ClassLoader.getSystemResource("icons/allinone.gif"));
        ImageIcon image_10=new ImageIcon(image_9.getImage().getScaledInstance(200,160,Image.SCALE_DEFAULT));
        JLabel l5=new JLabel(image_10);
        // Cursor cursor5 = l5.getCursor();
        l5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        l5.setBounds(330,315,70,65);
        p2.add(l5);
        p2.setBorder(br2);
        setVisible(true);
    }
    public static void main(String[] args) {
        try {
  
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        }
        catch (Exception e) {
            System.out.println("Look and Feel not set");
        }
        new signup();
        
    }
}
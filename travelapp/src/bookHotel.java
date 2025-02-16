
import java.awt.*;
import java.awt.Font;
import java.awt.Image;
import java.sql.*;	
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class bookHotel extends JFrame {
	private JPanel contentPane;
        JTextField t1,t2;
        Choice c1,c2,c3;
	
	public static void main(String[] args) {
        try {
  
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        }
        catch (Exception e) {
            System.out.println("Look and Feel not set");
        }
		new bookHotel("");
	}

	public bookHotel(String username) {
		setBounds(420, 220, 1100, 600);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
        Font f1=new Font("Gill Sans Ultra Bold Condensed",Font.PLAIN,30);
        Font f2=new Font("Tahoma Bold",Font.BOLD,15);
                
                ImageIcon i1  = new ImageIcon(ClassLoader.getSystemResource("icons/book.jpg"));
                Image i3 = i1.getImage().getScaledInstance(500, 300,Image.SCALE_DEFAULT);
                ImageIcon i2 = new ImageIcon(i3);
                JLabel la1 = new JLabel(i2);
                la1.setBounds(450,100,700,300);
                add(la1);
		
		JLabel lblName = new JLabel("BOOK HOTEL");
		lblName.setFont(f1);
		lblName.setBounds(118, 11, 300, 53);
		contentPane.add(lblName);
                
        JLabel la2 = new JLabel("Username :");
		la2.setBounds(35, 70, 200, 14);
		contentPane.add(la2);
        la2.setFont(f2);
                
        JLabel l1 = new JLabel(username);
		l1.setBounds(271, 70, 200, 14);
		contentPane.add(l1);
        l1.setFont(f2);
                
        JLabel lblId = new JLabel("Select Hotel :");
		lblId.setBounds(35, 110, 200, 14);
		contentPane.add(lblId);
        lblId.setFont(f2);
                
                c1 = new Choice();
                conn c = new conn();
                try{

                    ResultSet rs = c.st.executeQuery("select * from hotel");
                    while(rs.next()){
                        c1.add(rs.getString("hotel"));
                    }

                    rs.close();
                }catch(SQLException e){}

                c1.setBounds(271, 110, 150, 30);
                add(c1);
                
        JLabel la3 = new JLabel("Total Persons :");
		la3.setBounds(35, 150, 200, 14);
		contentPane.add(la3);
        la3.setFont(f2);
                
        t1 = new JTextField();
        t1.setText("0");
		t1.setBounds(271, 150, 150, 30);
		contentPane.add(t1);
		t1.setColumns(10);
        t1.setFont(f2);
                
        JLabel la4 = new JLabel("Number of Days :");
        la4.setBounds(35, 190, 200, 14);
		contentPane.add(la4);
        la4.setFont(f2);
		
		t2 = new JTextField();
		t2.setText("0");
		t2.setBounds(271, 190, 150, 30);
		contentPane.add(t2);
		t2.setColumns(10);
        t2.setFont(f2);
		
		JLabel la5 = new JLabel("AC / Non-AC : ");
		la5.setBounds(35, 230, 200, 14);
		contentPane.add(la5);
        la5.setFont(f2);
                
                c2 = new Choice();
                c2.add("AC");
                c2.add("Non-AC");
                c2.setBounds(271, 230, 150, 30);
                add(c2);
                c2.setFont(f2);

                
		JLabel la6 = new JLabel("Food Included :");
		la6.setBounds(35, 270, 200, 14);
		contentPane.add(la6);
        la6.setFont(f2);
                
                c3 = new Choice();
                c3.add("Yes");
                c3.add("No");
                c3.setBounds(271, 270, 150, 30);
                add(c3);
                c3.setFont(f2);
                
		JLabel lbl1 = new JLabel("ID :");
		lbl1.setBounds(35, 310, 200, 14);
		contentPane.add(lbl1);
        lbl1.setFont(f2);
                
        JLabel l2 = new JLabel();
		l2.setBounds(271, 310, 200, 14);
		contentPane.add(l2);
        l2.setFont(f2);
		
		JLabel lbl2 = new JLabel("Number :");
		lbl2.setBounds(35, 350, 200, 14);
		contentPane.add(lbl2);
        lbl2.setFont(f2);
                
        JLabel l3 = new JLabel();
		l3.setBounds(271, 350, 200, 14);
		contentPane.add(l3);
        l3.setFont(f2);
           	
		JLabel lbl3 = new JLabel("Phone :");
		lbl3.setBounds(35, 390, 200, 14);
		contentPane.add(lbl3);
        lbl3.setFont(f2);
                
        JLabel l4 = new JLabel();
		l4.setBounds(271, 390, 200, 14);
		contentPane.add(l4);
        l4.setFont(f2);

		
		JLabel lblDeposite = new JLabel("Total Price :");
		lblDeposite.setBounds(35, 430, 200, 14);
		contentPane.add(lblDeposite);
        lblDeposite.setFont(f2);
		
		JLabel l5 = new JLabel();
		l5.setBounds(271, 430, 200, 14);
                l5.setForeground(Color.RED);
		contentPane.add(l5);
        l5.setFont(f2);
                
                try{

                    ResultSet rs = c.st.executeQuery("select * from customer where username = '"+username+"'");
                    while(rs.next()){
                        l2.setText(rs.getString("id"));
                        l3.setText(rs.getString("id_number"));
                        l4.setText(rs.getString("phone"));
                        
                    }

                    rs.close();
                }catch(SQLException e){}
                
                JButton b1 = new JButton("Check Price");
                b1.setFont(f2);
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                            conn c = new conn();
                           
                            try{
	    			@SuppressWarnings("unused")
                    String s1 = c1.getSelectedItem(); 
                                
                                String q1 = "select * from hotel where hotel = '"+c1.getSelectedItem()+"'";
                                ResultSet rs = c.st.executeQuery(q1);
                                if(rs.next()){
                                    
                                    int cost = Integer.parseInt(rs.getString("cost_per_day"));
                                    int food = Integer.parseInt(rs.getString("food_charges"));
                                    int ac = Integer.parseInt(rs.getString("ac_charges"));
                                    
                                    int persons = Integer.parseInt(t1.getText());
                                    int days = Integer.parseInt(t2.getText());
                                    
                                    String acprice = c2.getSelectedItem();
                                    String foodprice = c3.getSelectedItem();
                                    
                                    
                                    if(persons * days > 0) {
                                        int total = 0;
                                        total += acprice.equals("AC") ? ac : 1;
                                        total += foodprice.equals("Yes") ? food : 1;
                                        total += cost;
                                        total = total * persons * days;
                                        l5.setText("Rs "+total);
                                    }
                                    
                                    
                                }
                                
	    		}catch(Exception ee){
	    			System.out.println(ee.getMessage());
	    		}
                        }
		});
		b1.setBounds(50, 470, 120, 30);
                b1.setBackground(Color.BLACK);
                b1.setForeground(Color.WHITE);
		contentPane.add(b1);
		

		JButton btnNewButton = new JButton("Book");
        btnNewButton.setFont(f2);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                    
                            try{
                                conn c = new conn();
                    String query2="Select * from bookhotel where username='"+username+"'";
                    ResultSet res=c.st.executeQuery(query2);
                    if(res.next()==true){
                       String package2=res.getString("hotel");
                       int a=JOptionPane.showConfirmDialog(null,package2+" has already been booked with this username\nDo you want to Update your Booking");  
                       if(a==JOptionPane.YES_OPTION){  
                            
                        String updatequery="update bookhotel set hotel='"+c1.getSelectedItem()+"' ,persons='"+t1.getText()+"',days='"+t2.getText()+"', Ac='"+c2.getSelectedItem()+"' ,food='"+c2.getSelectedItem()+"',price='"+l5.getText()+"' where username='"+username+"'";
                        c.st.executeUpdate(updatequery);
                       }
                    }
                    else{
                                
                    String q1 = "insert into bookhotel values('"+l1.getText()+"', '"+c1.getSelectedItem()+"', '"+t1.getText()+"', '"+t2.getText()+"', '"+c2.getSelectedItem()+"', '"+c3.getSelectedItem()+"', '"+l2.getText()+"', '"+l3.getText()+"', '"+l4.getText()+"', '"+l5.getText()+"')";
                    c.st.executeUpdate(q1);
                                
	    			JOptionPane.showMessageDialog(null, "Hotel Booked Successfully");
                    }
                                setVisible(false);
                            }catch(Exception ee){
	    			System.out.println(ee.getMessage());
                            }
                            
			}
		});
		btnNewButton.setBounds(200, 470, 120, 30);
                btnNewButton.setBackground(Color.BLACK);
                btnNewButton.setForeground(Color.WHITE);
		contentPane.add(btnNewButton);
		
		JButton btnExit = new JButton("Back");
        btnExit.setFont(f2);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                            setVisible(false);
			}
		}); 
		btnExit.setBounds(350, 470, 120, 30);
                btnExit.setBackground(Color.BLACK);
                btnExit.setForeground(Color.WHITE);
		contentPane.add(btnExit);
                
                getContentPane().setBackground(Color.WHITE);

                setVisible(true);
	}
}

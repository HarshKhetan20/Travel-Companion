/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.*;
import java.awt.Font;
import java.awt.Image;
import java.sql.*;	
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class deleteCustomer extends JFrame {
	private JPanel contentPane;
        Choice c1;
	public static void main(String[] args) {
        try {
  
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        }
        catch (Exception e) {
            System.out.println("Look and Feel not set");
        }
		new deleteCustomer("");
	}

	public deleteCustomer(String str){
		setBounds(580, 220, 850, 550);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
        Font f2=new Font("Tahoma Bold",Font.BOLD,15);
        Font f1=new Font("Gill Sans Ultra Bold Condensed",Font.PLAIN,20);
                
        
        ImageIcon i1  = new ImageIcon(ClassLoader.getSystemResource("icons/deleteaccount.jfif"));
        Image i3 = i1.getImage().getScaledInstance(300, 300,Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(i3);
        JLabel l1 = new JLabel(i2);
        l1.setBounds(500,100,300,300);
        add(l1);
		
		JLabel lblName = new JLabel("DELETE CUSTOMER DETAILS");
		lblName.setFont(new Font("Gill Sans Ultra Bold Condensed", Font.PLAIN, 25));
        lblName.setForeground(Color.RED);
		lblName.setBounds(118, 5, 300, 53);
		contentPane.add(lblName);
        
                
        JLabel lb3 = new JLabel("Username :");
		lb3.setBounds(35, 70, 200, 14);
		contentPane.add(lb3);
        lb3.setFont(f2);
                
        c1 = new Choice();
        conn c = new conn();
        try{

            ResultSet rs = c.st.executeQuery("select * from customer where username='"+str+"'");
            while(rs.next()){
                c1.add(rs.getString("username"));
            }

            rs.close();
        }catch(SQLException e){}

        c1.setBounds(271, 70, 150, 30);
        c1.setFont(f2);
        add(c1);
                
        JLabel lblId = new JLabel("ID :");
		lblId.setBounds(35, 110, 200, 14);
		contentPane.add(lblId);
        lblId.setFont(f2);
                
        JLabel l2 = new JLabel();
		l2.setBounds(271, 110, 200, 14);
		contentPane.add(l2);
        l2.setFont(f2);
                
        JLabel lb2 = new JLabel("Number :");
		lb2.setBounds(35, 150, 200, 14);
		contentPane.add(lb2);
        lb2.setFont(f2);
                
        JLabel l3 = new JLabel();
		l3.setBounds(271, 150, 200, 14);
		contentPane.add(l3);
        l3.setFont(f2);
		
		JLabel lblName_1 = new JLabel("Name :");
		lblName_1.setBounds(35, 190, 200, 14);
		contentPane.add(lblName_1);
        lblName_1.setFont(f2);
		
		JLabel l4 = new JLabel();
		l4.setBounds(271, 190, 200, 14);
		contentPane.add(l4);
        l4.setFont(f2);

                
		JLabel lblGender = new JLabel("Gender :");
		lblGender.setBounds(35, 230, 200, 14);
		contentPane.add(lblGender);
        lblGender.setFont(f2);
                
        JLabel l5 = new JLabel();
		l5.setBounds(271, 230, 200, 14);
		contentPane.add(l5);
        l5.setFont(f2);
                
		JLabel lblCountry = new JLabel("Country :");
		lblCountry.setBounds(35, 270, 200, 14);
		contentPane.add(lblCountry);
        lblCountry.setFont(f2);
                
        JLabel l6 = new JLabel();
		l6.setBounds(271, 270, 200, 14);
		contentPane.add(l6);
        l6.setFont(f2);
		
		JLabel lblReserveRoomNumber = new JLabel("Permanent Address :");
		lblReserveRoomNumber.setBounds(35, 310, 200, 14);
		contentPane.add(lblReserveRoomNumber);
        lblReserveRoomNumber.setFont(f2);
                
        JLabel l7 = new JLabel();
		l7.setBounds(271, 310, 200, 14);
		contentPane.add(l7);
        l7.setFont(f2);
           	
		JLabel lblCheckInStatus = new JLabel("Phone :");
		lblCheckInStatus.setBounds(35, 350, 200, 14);
		contentPane.add(lblCheckInStatus);
        lblCheckInStatus.setFont(f2);
                
        JLabel l8 = new JLabel();
		l8.setBounds(271, 350, 200, 14);
		contentPane.add(l8);
        l8.setFont(f2);

		
		JLabel lblDeposite = new JLabel("Email :");
		lblDeposite.setBounds(35, 390, 200, 14);
		contentPane.add(lblDeposite);
        lblDeposite.setFont(f2);
		
		JLabel l9 = new JLabel();
		l9.setBounds(271, 390, 200, 14);
		contentPane.add(l9);
        l9.setFont(f2);

		
		JButton b1 = new JButton("Check");
        b1.setFont(f1);
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                            conn c = new conn();
                           
                            try{
                                ResultSet rs = c.st.executeQuery("select * from customer where username ='"+str+"'");
                                if(rs.next()){
                                    l2.setText(rs.getString(2));  
                                    l3.setText(rs.getString(3));
                                    l4.setText(rs.getString(4));  
                                    l5.setText(rs.getString(5));
                                    l6.setText(rs.getString(6));  
                                    l7.setText(rs.getString(7));
                                    l8.setText(rs.getString(8));  
                                    l9.setText(rs.getString(9));
                                }
                            }catch(Exception ee){ }
                        }
		});
		b1.setBounds(427, 65, 80, 30);
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
		contentPane.add(b1);
		

		JButton btnNewButton = new JButton("Delete");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                            conn c = new conn();
                           
                          
                            try{
	    			String s1 = c1.getSelectedItem(); 
                                
                                String q1 = "delete from customer where username = '"+s1+"'";
                                c.st.executeUpdate(q1);
                                
	    			JOptionPane.showMessageDialog(null, "Customer Detail Deleted Successfully");
                                setVisible(false);
	    		}catch(SQLException e1){
	    			System.out.println(e1.getMessage());
	    		}
		    		catch(NumberFormatException s){
		    			JOptionPane.showMessageDialog(null, "Please enter a valid Number");
			}
			}
		});
		btnNewButton.setBounds(100, 430, 120, 40);
        btnNewButton.setFont(f1);
            btnNewButton.setBackground(Color.BLACK);
            btnNewButton.setForeground(Color.WHITE);
		contentPane.add(btnNewButton);
		
		JButton btnExit = new JButton("Back");
        btnExit.setFont(f1);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                            setVisible(false);
			}
		}); 
		btnExit.setBounds(260, 430, 120, 40);
                btnExit.setBackground(Color.BLACK);
                btnExit.setForeground(Color.WHITE);
		contentPane.add(btnExit);
                
                getContentPane().setBackground(Color.WHITE);
	}
}

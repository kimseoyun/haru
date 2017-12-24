package haru;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.Statement;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.TextArea;
import javax.swing.JScrollBar;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;


public class Set_pw extends JFrame {
	CardLayout card;
	private JPanel contentPane;
	private JPasswordField old_pw;
	private JPasswordField new_pw;


	/**
	 * Launch the application.
	 */
public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
		public void run() {
				try {
					Set_pw frame = new Set_pw();
					frame.setVisible(true);
					frame.setLocation(600, 200); // 윈도우 창 뜨는 위치
					frame.setResizable(false); // 창 크기 고정
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Set_pw() {
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 426, 261);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 250, 240));
		contentPane.setForeground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setPreferredSize(new Dimension(10, 10));
		JLabel old_label = new JLabel("기존 비밀번호 입력");
		old_label.setFont(new Font("a엄마의편지L", Font.PLAIN, 18));
		old_label.setBounds(51, 67, 152, 21);
		contentPane.add(old_label);
		//새 비번
		new_pw = new JPasswordField();
		new_pw.setBounds(220, 102, 110, 21);
		contentPane.add(new_pw);
		
		JLabel new_label = new JLabel("새로운 비밀번호 입력");
		new_label.setFont(new Font("a엄마의편지L", Font.PLAIN, 18));
		new_label.setBounds(36, 103, 186, 21);
		
		contentPane.add(new_label);
		//기존 비번
		old_pw = new JPasswordField();
		old_pw.setBounds(220, 66, 110, 21);
		contentPane.add(old_pw);

		JButton set = new JButton("설정");
		
		set.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String psd=old_pw.getText(); //기존 비밀번호
				String new_password = new_pw.getText(); //새 비밀번호
				try{
					db d = new db();
					 String sql = " SELECT * from user WHERE num = 1";
					 d.pstmt = (Statement) d.con.createStatement();	
					// pstmt.setInt(1, 1);
					  d.rs = d.pstmt.executeQuery(sql);
					  ResultSetMetaData resultSetMetaData = (ResultSetMetaData) d.rs.getMetaData();
					  d.rs.next(); 
					  String db_psd = d.rs.getString("pw");
							if(psd.equals(db_psd))
							{
								
								 d.pstmt = (Statement) d.con.createStatement();	
								 String sql2 = "UPDATE user SET pw='"+new_password+"' WHERE num = '1'";
								 PreparedStatement statement = d.con.prepareStatement(sql);
								// statement.setString(1,pw);
								 int i=statement.executeUpdate(sql2);
								 if(i>0){
									 JOptionPane.showMessageDialog(null, "설정이 완료되었습니다.");
									 dispose();
									 Login login = new Login();
									 login.setVisible(true);
									 login.setLocation(600, 200); // 윈도우 창 뜨는 위치
									 login.setResizable(false); // 창 크기 고정
								 }
								 else System.out.println("실패");
							}
							else {
								JOptionPane.showMessageDialog(null, "비밀번호가 맞지 않습니다.");
							}
				  
		
					  
				
				  } catch(Exception e1) {
			            System.out.println("SQLException: " + e1.getMessage());
			        } 
			           
			}
			
		});
		
		set.setBackground(Color.WHITE);
		set.setForeground(Color.BLACK);
		set.setFont(new Font("a엄마의편지L", Font.PLAIN, 18));
		set.setBounds(160, 146, 83, 29);
		contentPane.add(set);
		
		
	}
}
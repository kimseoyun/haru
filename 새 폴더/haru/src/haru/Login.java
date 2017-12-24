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
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

class db {
	Connection con = null;
	Statement pstmt = null;
	ResultSet rs = null;
	String url = "jdbc:mysql://localhost:3306/haru?useSSL=false";
	String jdbc = "com.mysql.jdbc.Driver";
	String user = "root";
	String pass = "1234";

	public db() throws ClassNotFoundException, SQLException {
		Class.forName(jdbc);
		con = DriverManager.getConnection(url, user, pass);
		if (con != null) {
			System.out.println("연결됨");
		}
	}

}

public class Login extends JFrame {
	CardLayout card;
	private JPanel contentPane;
	private JPasswordField passwordField;
	private JButton login_btn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		new File("D:/haru").mkdir();
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
		JLabel lblNewLabel = new JLabel("\uD558\uB8E8");
		lblNewLabel.setFont(new Font("a엄마의편지L", Font.PLAIN, 18));
		lblNewLabel.setBounds(186, 27, 99, 21);
		contentPane.add(lblNewLabel);

		passwordField = new JPasswordField();
		passwordField.setBounds(151, 82, 108, 21);
		contentPane.add(passwordField);

		JButton set_pw_go = new JButton("비밀번호 설정");
		set_pw_go.setBackground(Color.WHITE);
		set_pw_go.setForeground(Color.BLACK);
		set_pw_go.setFont(new Font("a엄마의편지L", Font.PLAIN, 18));
		set_pw_go.setBounds(126, 150, 149, 29);
		set_pw_go.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Set_pw p = new Set_pw();
				dispose();
				p.setVisible(true);
				p.setLocation(600, 200); // 윈도우 창 뜨는 위치
				p.setResizable(false); // 창 크기 고정
			}

		});
		contentPane.add(set_pw_go);

		login_btn = new JButton("Login");
		login_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String psd = passwordField.getText();
				
				try {
					db d = new db();
					String sql = " SELECT pw from user WHERE num = 1";
					d.pstmt = (Statement) d.con.createStatement();
					d.rs = d.pstmt.executeQuery(sql);
					d.rs.next();
					String db_psd = d.rs.getString("pw");
					
					if (psd.equals(db_psd)) {
						Main m = new Main();
						m.setVisible(true);
						setVisible(false);
						JOptionPane.showMessageDialog(null, "로그인성공", null, JOptionPane.PLAIN_MESSAGE);
						
					} else {
						JOptionPane.showMessageDialog(null, "비밀번호를 확인해주세요.", null, JOptionPane.PLAIN_MESSAGE);
					}

				} catch (Exception e1) {
					System.out.println("SQLException: " + e1.getMessage());
				}
			}

		});
		login_btn.setForeground(Color.BLACK);
		login_btn.setFont(new Font("a엄마의편지L", Font.PLAIN, 18));
		login_btn.setBackground(Color.WHITE);
		login_btn.setBounds(161, 118, 84, 29);
		contentPane.add(login_btn);
	}
}
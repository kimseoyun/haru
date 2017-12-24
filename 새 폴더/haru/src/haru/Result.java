package haru;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SwingConstants;

public class Result extends JFrame {

	private JPanel contentPane;
	static int rowCnt;

	static String imgpath;
	public int num;
	public String numstr;
	static JButton[] btn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param year 
	 * @param month 
	 * 
	 * @throws SQLException
	 */
	public Result(String month, String year) throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1307, 825);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 250, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel(new BorderLayout());
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setBackground(new Color(255, 250, 240));

		JLabel haru = new JLabel("하루");
		haru.setHorizontalAlignment(SwingConstants.CENTER);
		haru.setFont(new Font("a엄마의편지L", Font.PLAIN, 30));
		panel.setLayout(new BorderLayout());
		panel.add(haru, BorderLayout.CENTER);

		JButton write_button = new JButton("");
		write_button.setBackground(new Color(255, 250, 240));
		write_button.setBorderPainted(false);
		write_button.setIcon(new ImageIcon("write_icon.png"));
		write_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				 Write frame = new Write();
				 frame.setVisible(true);
				 setVisible(false);
			}
		});
		panel.add(write_button, BorderLayout.EAST);

		JButton menuicon = new JButton("");
		menuicon.setBackground(new Color(255, 250, 240));
		menuicon.setBorderPainted(false);
		menuicon.setIcon(new ImageIcon("menu_icon.png"));
		menuicon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 Date frame = new Date();
				 frame.setVisible(true);
				 setVisible(false);
			}
		});
		panel.add(menuicon, BorderLayout.WEST);

		//////////////////////////////////// 기본설정

		JPanel btn_panel = new JPanel();
		btn_panel.setBackground(new Color(255, 250, 240));
		contentPane.add(btn_panel, BorderLayout.CENTER);
		btn_panel.setLayout(new FlowLayout());
		// JButton[] btn = new JButton[5];
		// for(int i=0;i<5;i++){
		// btn_panel.add(btn[i]);
		// }
		try {
			db_conn d = new db_conn();
			String query = "select count(*),num,img from diary;";
			d.stmt = d.con.createStatement();
			d.rs = d.stmt.executeQuery(query);
			
			while (d.rs.next()) {
				rowCnt = d.rs.getInt(1);
			}

			String img_query = "select img,num from diary where year(writedate) ='"+year+"' and month(writedate) = '"+month+"' order by writedate desc;";
			d.stmt = d.con.createStatement();
			d.rs = d.stmt.executeQuery(img_query);
			btn = new JButton[rowCnt];
			int i = 0;
			String[] imgpath_arr = null;
			while (d.rs.next()) {
				imgpath = d.rs.getString("img");
				num = d.rs.getInt("num");
				numstr = String.valueOf(num);

				// 사진을 버튼에
				ImageIcon or_image = new ImageIcon(imgpath);
				Image nore_image = or_image.getImage();
				Image reimage = nore_image.getScaledInstance(250, 250, java.awt.Image.SCALE_SMOOTH);
				ImageIcon resize_imgicon = new ImageIcon(reimage);
				btn[i] = new JButton(resize_imgicon);
				btn[i].setBackground(new Color(255, 250, 240));
				btn[i].setBorderPainted(false);
				btn[i].setContentAreaFilled(false);
				btn[i].setActionCommand(numstr);
				btn[i].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						for (int k = 0; k < rowCnt; k++) {
							if (e.getSource() == btn[k]) {
								int rs_num = Integer.parseInt(btn[k].getActionCommand());
								try {
									String num_query = "select num from diary where num ='"+rs_num+"';";
									setVisible(false);
									
									Update upd = new Update(rs_num);
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (ClassNotFoundException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								
							}
						}
					}
				});

				btn_panel.add(btn[i]);
				btn[i].setSize(250, 250);
				i++;

			} // end of while
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	}
	/////////////////////////////////////////////////////////////////
//제목이나 내용 검색
	public Result(String look) throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1307, 825);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 250, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel(new BorderLayout());
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setBackground(new Color(255, 250, 240));

		JLabel haru = new JLabel("하루");
		haru.setHorizontalAlignment(SwingConstants.CENTER);
		haru.setFont(new Font("a엄마의편지L", Font.PLAIN, 30));
		panel.setLayout(new BorderLayout());
		panel.add(haru, BorderLayout.CENTER);

		JButton write_button = new JButton("");
		write_button.setBackground(new Color(255, 250, 240));
		write_button.setBorderPainted(false);
		write_button.setIcon(new ImageIcon("write_icon.png"));
		write_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				 Write frame = new Write();
				 frame.setVisible(true);
				 setVisible(false);
			}
		});
		panel.add(write_button, BorderLayout.EAST);

		JButton menuicon = new JButton("");
		menuicon.setBackground(new Color(255, 250, 240));
		menuicon.setBorderPainted(false);
		menuicon.setIcon(new ImageIcon("menu_icon.png"));
		menuicon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				 Date frame = new Date();
				 frame.setVisible(true);
				 setVisible(false);
			}
		});
		panel.add(menuicon, BorderLayout.WEST);

		//////////////////////////////////// 기본설정

		JPanel btn_panel = new JPanel();
		btn_panel.setBackground(new Color(255, 250, 240));
		contentPane.add(btn_panel, BorderLayout.CENTER);
		btn_panel.setLayout(new FlowLayout());
		// JButton[] btn = new JButton[5];
		// for(int i=0;i<5;i++){
		// btn_panel.add(btn[i]);
		// }
		try {
			db_conn d = new db_conn();
			String query = "select count(*),num,img from diary;";
			d.stmt = d.con.createStatement();
			d.rs = d.stmt.executeQuery(query);
			while (d.rs.next()) {
				rowCnt = d.rs.getInt(1);
			}

			String img_query = "select img,num from diary where title like '%"+look+"%' or content like '%"+look+"%' order by writedate desc;";
			d.stmt = d.con.createStatement();
			d.rs = d.stmt.executeQuery(img_query);
			btn = new JButton[rowCnt];
			int i = 0;
			String[] imgpath_arr = null;
			while (d.rs.next()) {
				imgpath = d.rs.getString("img");
				num = d.rs.getInt("num");
				numstr = String.valueOf(num);

				// 사진을 버튼에
				ImageIcon or_image = new ImageIcon(imgpath);
				Image nore_image = or_image.getImage();
				Image reimage = nore_image.getScaledInstance(250, 250, java.awt.Image.SCALE_SMOOTH);
				ImageIcon resize_imgicon = new ImageIcon(reimage);
				btn[i] = new JButton(resize_imgicon);
				btn[i].setBackground(new Color(255, 250, 240));
				btn[i].setBorderPainted(false);
				btn[i].setContentAreaFilled(false);
				btn[i].setActionCommand(numstr);
				btn[i].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						for (int k = 0; k < rowCnt; k++) {
							if (e.getSource() == btn[k]) {
							
								int rs_num = Integer.parseInt(btn[k].getActionCommand());
								try {
									String num_query = "select num from diary where num ='"+rs_num+"';";
									setVisible(false);
									
									Update upd = new Update(rs_num);
									upd.setVisible(true);
									setVisible(false);
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (ClassNotFoundException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								
							}
						}
					}
				});

				btn_panel.add(btn[i]);
				btn[i].setSize(250, 250);
				i++;

			} // end of while
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	}

	//기분검색
	public Result(String feel_sel, int j) throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1307, 825);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 250, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel(new BorderLayout());
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setBackground(new Color(255, 250, 240));

		JLabel haru = new JLabel("하루");
		haru.setHorizontalAlignment(SwingConstants.CENTER);
		haru.setFont(new Font("a엄마의편지L", Font.PLAIN, 30));
		panel.setLayout(new BorderLayout());
		panel.add(haru, BorderLayout.CENTER);

		JButton write_button = new JButton("");
		write_button.setBackground(new Color(255, 250, 240));
		write_button.setBorderPainted(false);
		write_button.setIcon(new ImageIcon("write_icon.png"));
		write_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 Write frame = new Write();
				 frame.setVisible(true);
				 setVisible(false);
			}
		});
		panel.add(write_button, BorderLayout.EAST);

		JButton menuicon = new JButton("");
		menuicon.setBackground(new Color(255, 250, 240));
		menuicon.setBorderPainted(false);
		menuicon.setIcon(new ImageIcon("menu_icon.png"));
		menuicon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 Date frame = new Date();
				 frame.setVisible(true);
				 setVisible(false);
			}
		});
		panel.add(menuicon, BorderLayout.WEST);

		//////////////////////////////////// 기본설정

		JPanel btn_panel = new JPanel();
		btn_panel.setBackground(new Color(255, 250, 240));
		contentPane.add(btn_panel, BorderLayout.CENTER);
		btn_panel.setLayout(new FlowLayout());
		// JButton[] btn = new JButton[5];
		// for(int i=0;i<5;i++){
		// btn_panel.add(btn[i]);
		// }
		try {
			db_conn d = new db_conn();
			String query = "select count(*),num,img from diary;";
			d.stmt = d.con.createStatement();
			d.rs = d.stmt.executeQuery(query);
			
			while (d.rs.next()) {
				rowCnt = d.rs.getInt(1);
			}
		

			String img_query = "select img,num from diary where feel = '"+feel_sel+"' order by writedate desc;";
			d.stmt = d.con.createStatement();
			d.rs = d.stmt.executeQuery(img_query);
		
			btn = new JButton[rowCnt];
			int i = 0;
			String[] imgpath_arr = null;
			while (d.rs.next()) {
				imgpath = d.rs.getString("img");
				num = d.rs.getInt("num");
				numstr = String.valueOf(num);
				

				// 사진을 버튼에
				ImageIcon or_image = new ImageIcon(imgpath);
				Image nore_image = or_image.getImage();
				Image reimage = nore_image.getScaledInstance(250, 250, java.awt.Image.SCALE_SMOOTH);
				ImageIcon resize_imgicon = new ImageIcon(reimage);
				btn[i] = new JButton(resize_imgicon);
				btn[i].setBackground(new Color(255, 250, 240));
				btn[i].setBorderPainted(false);
				btn[i].setContentAreaFilled(false);
				btn[i].setActionCommand(numstr);
				btn[i].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						for (int k = 0; k < rowCnt; k++) {
							if (e.getSource() == btn[k]) {
						
								int rs_num = Integer.parseInt(btn[k].getActionCommand());
								try {
									String num_query = "select num from diary where num ='"+rs_num+"';";
									setVisible(false);
									
									Update upd = new Update(rs_num);
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (ClassNotFoundException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								
							}
						}
					}
				});

				btn_panel.add(btn[i]);
		
				btn[i].setSize(250, 250);
				i++;

			} // end of while
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	}
}

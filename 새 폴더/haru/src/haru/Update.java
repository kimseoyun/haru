package haru;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.mysql.jdbc.Statement;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.ScrollPaneConstants;
import javax.swing.JRadioButton;
import javax.swing.JList;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;


public class Update extends JFrame implements MouseListener, MouseMotionListener {
	int drag_status = 0, c1, c2, c3, c4;
	private JPanel contentPane;

	static JButton MainButton; // 메인홈버튼

	static JTextArea label_text; // 일기를 입력하세
	static JFileChooser chooser;
	static String filePath;
	static String content;
	static String feel;
	static String title;

	static JTextArea content_text; // 내용 입력 칸
	static java.util.Date utilDate;
	static String dateString;
	static java.sql.Date sqlDate;
	static Choice Jyear;
	static Choice Jmonth;
	static Choice Jday;
	static JTextArea real_title;
	static ButtonGroup bg;
	static JRadioButton ra1;
	static JRadioButton ra2;
	static JRadioButton ra3;
	static JRadioButton ra4;
	static JRadioButton ra5;
	static JRadioButton ra6;
	static JRadioButton ra7;
	static String imgfile;
	static String imgname; 
	static int ret;
	@SuppressWarnings("unchecked")
	public Update(int num) throws SQLException, ClassNotFoundException {
		getContentPane().setSize(400, 400);
		setVisible(true);
		addMouseListener(this);
		addMouseMotionListener(this);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBackground(new Color(255, 250, 240));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1307, 825);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 250, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		MainButton = new JButton(""); // 메인홈버튼
		MainButton.setBackground(new Color(255, 250, 240));
		MainButton.setBorderPainted(false);
		MainButton.setIcon(new ImageIcon("img/home_icon.png"));
		MainButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main main;
				try {
					main = new Main();
					main.setVisible(true);
					setVisible(false);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		MainButton.setBounds(1223, 23, 40, 40);
		contentPane.add(MainButton);

		JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(651, 176, 557, 458);
		contentPane.add(scrollPane);

		content_text = new JTextArea();
		scrollPane.setViewportView(content_text);
		content_text.setFont(new Font("a엄마의편지L", Font.PLAIN, 20));
		content_text.setBackground(new Color(255, 250, 240));
		content_text.setLineWrap(true);
		
		
		try {
			db_conn d = new db_conn();
			System.out.println(num);
			String content_query = "select content from diary where num = '" + num + "'";
			d.stmt = d.con.createStatement();
			d.rs = d.stmt.executeQuery(content_query);
			d.rs.next();
			String content = d.rs.getString("content");
			content_text.setText(content);

		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		JLabel label = new JLabel("하루");
		label.setFont(new Font("a엄마의편지L", Font.PLAIN, 30));
		label.setBounds(616, 31, 52, 32);
		contentPane.add(label);

		
		db_conn d = new db_conn();
		JLabel image_screen = new JLabel("");
		image_screen.setBounds(44, 100, 550, 550);
		contentPane.add(image_screen);
		String img = "select img from diary where num = '" + num + "'";
		d.stmt = d.con.createStatement();
		d.rs = d.stmt.executeQuery(img);
		d.rs.next();
		//imgname = db에 있던 img 경로명
		imgname= d.rs.getString("img");
		
		//db에 있던 사진 화면에
		ImageIcon orimage = new ImageIcon(imgname);
		Image noreimage = orimage.getImage();
		Image reimage = noreimage.getScaledInstance(550, 560, java.awt.Image.SCALE_SMOOTH);
		ImageIcon reimageicon = new ImageIcon(reimage);
		image_screen.setIcon(reimageicon);
		
		JButton FileButton = new JButton("사진 선택");
		FileButton.setFont(new Font("a엄마의편지L", Font.PLAIN, 20));
		FileButton.setBackground(new Color(255, 250, 240));
		FileButton.setBorderPainted(false);
		filePath = imgname;
		FileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif"); // 함
				chooser.setFileFilter(filter); // 파일 다이얼로그에 파일 필터 설정
				System.out.println("사진선택:"+chooser.isFileSelectionEnabled());
				ret = chooser.showOpenDialog(null);
				if (ret != JFileChooser.APPROVE_OPTION) { // 사용자가 창을 강제로 닫았거나 취소
					filePath=imgname;// 버튼을 누른 경우
					System.out.println("사용자가 창 강제로 "+filePath);
					JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다", "경고", JOptionPane.WARNING_MESSAGE);
					return;
				}
				else{
					filePath = chooser.getSelectedFile().getPath(); // 파일
					System.out.println("사진 선택되어서 filePath"+filePath);
				}
					// 여기 사진 크기 줄이는 거!!!!!!!!!
				ImageIcon orimage = new ImageIcon(filePath);
				Image noreimage = orimage.getImage();
				Image reimage = noreimage.getScaledInstance(550, 560, java.awt.Image.SCALE_SMOOTH);
				ImageIcon reimageicon = new ImageIcon(reimage);
				image_screen.setIcon(reimageicon);
			}
		});
		real_title = new JTextArea();
		try {
			db_conn d1 = new db_conn();
			System.out.println(num);
			String title_query = "select title from diary where num = '" + num + "'";
			d1.stmt = d1.con.createStatement();
			d1.rs = d1.stmt.executeQuery(title_query);
			d1.rs.next();
			String title = d1.rs.getString("title");
			real_title.setText(title);

		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		real_title.setFont(new Font("a엄마의편지L", Font.PLAIN, 20));
		real_title.setBackground(new Color(255, 250, 240));
		real_title.setBounds(648, 129, 591, 32);
		contentPane.add(real_title);

		FileButton.setBounds(260, 666, 129, 27);
		contentPane.add(FileButton);

		bg = new ButtonGroup();

		ra1 = new JRadioButton("기쁨");
		ra1.setBackground(new Color(255, 250, 240));
		ra1.setFont(new Font("a엄마의편지L", Font.PLAIN, 20));
		ra1.setBounds(685, 658, 74, 27);
		contentPane.add(ra1);
		ra1.setActionCommand("기쁨");

		ra2 = new JRadioButton("슬픔");
		ra2.setFont(new Font("a엄마의편지L", Font.PLAIN, 20));
		ra2.setBackground(new Color(255, 250, 240));
		ra2.setBounds(765, 658, 74, 27);
		contentPane.add(ra2);
		ra2.setActionCommand("기쁨");

		ra3 = new JRadioButton("화남");
		ra3.setFont(new Font("a엄마의편지L", Font.PLAIN, 20));
		ra3.setBackground(new Color(255, 250, 240));
		ra3.setBounds(845, 658, 74, 27);
		contentPane.add(ra3);
		ra3.setActionCommand("기쁨");

		ra4 = new JRadioButton("행복");
		ra4.setFont(new Font("a엄마의편지L", Font.PLAIN, 20));
		ra4.setBackground(new Color(255, 250, 240));
		ra4.setBounds(925, 658, 74, 27);
		contentPane.add(ra4);
		ra4.setActionCommand("기쁨");

		ra5 = new JRadioButton("짜증");
		ra5.setFont(new Font("a엄마의편지L", Font.PLAIN, 20));
		ra5.setBackground(new Color(255, 250, 240));
		ra5.setBounds(1005, 658, 74, 27);
		contentPane.add(ra5);
		ra5.setActionCommand("기쁨");

		ra6 = new JRadioButton("피곤");
		ra6.setFont(new Font("a엄마의편지L", Font.PLAIN, 20));
		ra6.setBackground(new Color(255, 250, 240));
		ra6.setBounds(1085, 658, 74, 27);
		contentPane.add(ra6);
		ra6.setActionCommand("기쁨");

		ra7 = new JRadioButton("예민");
		ra7.setFont(new Font("a엄마의편지L", Font.PLAIN, 20));
		ra7.setBackground(new Color(255, 250, 240));
		ra7.setBounds(1165, 658, 74, 27);
		contentPane.add(ra7);
		ra7.setActionCommand("기쁨");

		bg.add(ra1);
		bg.add(ra2);
		bg.add(ra3);
		bg.add(ra4);
		bg.add(ra5);
		bg.add(ra6);
		bg.add(ra7);

//		JButton FinishButton = new JButton("쓰기");
//		FinishButton.setBackground(new Color(255, 250, 240));
//		FinishButton.setBorderPainted(false);
//		FinishButton.setFont(new Font("a엄마의편지L", Font.PLAIN, 24));

		Calendar oCalendar = Calendar.getInstance();

		int toyear = oCalendar.get(Calendar.YEAR);
		int tomonth = oCalendar.get(Calendar.MONTH) + 1;
		int today = oCalendar.get(Calendar.DAY_OF_MONTH);

		try {
			db_conn d11 = new db_conn();
			String date = "select writedate from diary where num = '" + num + "'";
			d11.stmt = d11.con.createStatement();
			d11.rs = d11.stmt.executeQuery(date);
			d11.rs.next();
			String writedate = d11.rs.getString("writedate");
			String[] year_db = writedate.split("-");
		

			Jyear = new Choice();
			Jmonth = new Choice();
			Jday = new Choice();

			for (int i = toyear + 5; i >= toyear - 5; i--) {
				Jyear.add(String.valueOf(i));
			}
			for (int i = 1; i <= 12; i++) {
				if (i < 10) {
					Jmonth.add(String.valueOf("0" + i));
				} else {
					Jmonth.add(String.valueOf(i));
				}
			}
			for (int i = 1; i <= 31; i++) {
				if (i < 10) {
					Jday.add(String.valueOf("0" + i));
				} else {
					Jday.add(String.valueOf(i));
				}
			}

			Jyear.setBackground(new Color(255, 250, 240));
			Jyear.setFont(new Font("a엄마의편지L", Font.PLAIN, 20));
			Jyear.setBounds(648, 93, 103, 24);
			Jyear.select(String.valueOf(year_db[0]));
			contentPane.add(Jyear);

			Jmonth.setBackground(new Color(255, 250, 240));
			Jmonth.setFont(new Font("a엄마의편지L", Font.PLAIN, 20));
			Jmonth.setBounds(765, 93, 52, 24);
			Jmonth.select(String.valueOf(year_db[1]));

			contentPane.add(Jmonth);

			Jday.setFont(new Font("a엄마의편지L", Font.PLAIN, 20));
			Jday.setBackground(new Color(255, 250, 240));
			Jday.setBounds(831, 93, 74, 24);
			Jday.select(year_db[2]);
			contentPane.add(Jday);

		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		FinishButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("확인");
				//System.out.println("chooser"+chooser);

				Enumeration elements = bg.getElements();
				while (elements.hasMoreElements()) {
					AbstractButton button = (AbstractButton) elements.nextElement();
					if (button.isSelected()) {
						feel = button.getText();
					}
				}
				if (feel == null) {
					JOptionPane.showMessageDialog(null, "기분을 선택해주세요.", " ", JOptionPane.PLAIN_MESSAGE);
					return;
				}

				if (real_title.getText() == null) {
					JOptionPane.showMessageDialog(null, "제목을 입력해주세요.", " ", JOptionPane.PLAIN_MESSAGE);
					return;
				}
				if (content_text.getText() == null) {
					JOptionPane.showMessageDialog(null, "내용을 입력해주세요.", " ", JOptionPane.PLAIN_MESSAGE);
					return;
				}
				
				
			
				try {
					db_conn d = new db_conn();

					// db에 넣을 값 가져오기
					title = real_title.getText();
					content = content_text.getText();
					String date = Jyear.getSelectedItem() + "-" + Jmonth.getSelectedItem() + "-"
							+ Jday.getSelectedItem();
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					java.util.Date parsed = format.parse(date);
					java.sql.Date sql_date = new java.sql.Date(parsed.getTime());
					
				
					// 이미지 haru 폴더에 저장
					
					//File savelocation = new File(filePath);
					System.out.println("Date"+date);
					System.out.println("쓰기버튼에서 filePath"+filePath);
					File f = new File(filePath);
					BufferedImage bimage = ImageIO.read(f);
					System.out.println("bimage"+bimage);
					String equal = "D:/haru/"+date+".jpg";
					ImageIO.write(bimage, "jpg",new File(equal));
				
					String del_file = filePath;
					
					File del = new File(del_file);
					System.out.println("비교"+filePath+"    "+equal);
					if(del_file==equal){
						System.out.println("같음");
						if(del.exists()){
							if( del.delete()){
								System.out.println("파일삭제성공");
							}
							else{
								System.out.println(del.getName()+"파일삭제실패한 파일이름");
								System.out.println("파일삭제실패");
							}
						}else{
							System.out.println("파일없음");
						}
					}
					else{
						System.out.println("다름	");
					}
				
					String sql = "update diary set title = ?, content=?,feel =?, writedate=?,img =? where num = '" + num
							+ "'";
					d.pstmt = d.con.prepareStatement(sql);
					d.pstmt.setString(1, title);
					d.pstmt.setString(2, content);
					d.pstmt.setString(3, feel);
					d.pstmt.setDate(4, sql_date);
					d.pstmt.setString(5, equal);
					
					
				
					int i = d.pstmt.executeUpdate();
					
					if (i > 0) {
						
						JOptionPane.showMessageDialog(null, "매 순간이 행복하길, 당신의 하루", " ", JOptionPane.PLAIN_MESSAGE);
						del.delete();
						
						d.pstmt.close();
						d.con.close();
						// Main m = new Main();
						// m.setVisible(true);
						setVisible(false);
						System.out.println("성공");
						Main m = new Main();
						m.setVisible(true);

					} else {
						JOptionPane.showMessageDialog(null, "일기 다 씀", " ", JOptionPane.PLAIN_MESSAGE);

					}
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		FinishButton.setBounds(646, 727, 105, 27);
		contentPane.add(FinishButton);
	
		JButton delete = new JButton("삭제");
		delete.setFont(new Font("a엄마의편지L", Font.PLAIN, 24));
		delete.setBorderPainted(false);
		delete.setBackground(new Color(255, 250, 240));
		delete.setBounds(524, 727, 105, 27);

		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("삭제");
				
				try {
					String del_query = "delete from diary where num ='"+num+"'";
					d.pstmt = d.con.prepareStatement(del_query);
					int i = d.pstmt.executeUpdate();
					if(i>0){
						File del_img = new File(filePath);
						del_img.delete();
						JOptionPane.showMessageDialog(null, "삭제가 완료되었습니다.", " ", JOptionPane.PLAIN_MESSAGE);
						setVisible(false);
						Main wr = new Main();
						wr.setVisible(true);
					}
					else{
						System.out.println("삭제 실패");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		contentPane.add(delete);

	}

	JList month1 = new JList();

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

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
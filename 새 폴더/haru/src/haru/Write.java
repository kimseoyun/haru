package haru;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Enumeration;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.JList;
import javax.swing.JScrollPane;

class db_conn {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String url = "jdbc:mysql://localhost:3306/haru?useSSL=false";
	String jdbc = "com.mysql.jdbc.Driver";
	String user = "root";
	String pass = "1234";
	public java.sql.Statement stmt=null;

	public db_conn() throws ClassNotFoundException, SQLException {
		Class.forName(jdbc);
		con = DriverManager.getConnection(url, user, pass);
		if (con != null) {
			System.out.println("�����");
		}
	}
}


public class Write extends JFrame implements MouseListener, MouseMotionListener {
	int drag_status = 0, c1, c2, c3, c4;
	private JPanel contentPane;

	static JButton MainButton; // ����Ȩ��ư

	static JTextArea label_text; // �ϱ⸦ �Է��ϼ�
	static JFileChooser chooser;
	static String filePath;
	static String content;
	static String feel;
	static String title;
	static JTextArea textAreaName; // ���� �Է� ĭ
	static JTextArea content_text; // ���� �Է� ĭ
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
	@SuppressWarnings("unchecked")
	public Write() {
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
		MainButton = new JButton(""); // ����Ȩ��ư
		MainButton.setBackground(new Color(255, 250, 240));
		MainButton.setBorderPainted(false);
		MainButton.setIcon(new ImageIcon("img/home_icon.png"));
		MainButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main main;
				try {
					main = new Main();
					main.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				setVisible(false);
			}
		});
		textAreaName = new JTextArea("������ �Է��ϼ���.");
		textAreaName.addMouseListener(new MouseAdapter() { // Ŭ�� �� �ȳ���Ʈ ����� �޼ҵ�

			public void mouseClicked(MouseEvent e) {
				textAreaName.setVisible(false);

			}
		});
		

		
		textAreaName.setFont(new Font("a����������L", Font.PLAIN, 20));
		textAreaName.setBackground(new Color(255, 250, 240));
		textAreaName.setBounds(658, 129, 591, 32);
		contentPane.add(textAreaName);
		MainButton.setBounds(1223, 23, 40, 40);
		contentPane.add(MainButton);

		label_text = new JTextArea();
		label_text.setText("�ϱ⸦ �Է��ϼ���.");
		label_text.addMouseListener(new MouseAdapter() { // Ŭ�� �� �ȳ���Ʈ ����� �޼ҵ�

			public void mouseClicked(MouseEvent e) {
				label_text.setVisible(false);

			}
		});
		label_text.setFont(new Font("a����������L", Font.PLAIN, 20));
		label_text.setBackground(new Color(255, 250, 240));
		label_text.setBounds(651, 176, 570, 458);
		contentPane.add(label_text);
		
		JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
	            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(651, 176, 557, 458);
		contentPane.add(scrollPane);

		content_text = new JTextArea();
		scrollPane.setViewportView(content_text);
		content_text.setFont(new Font("a����������L", Font.PLAIN, 20));
		content_text.setBackground(new Color(255, 250, 240));
		content_text.setLineWrap(true);
		
		JLabel label = new JLabel("�Ϸ�");
		label.setFont(new Font("a����������L", Font.PLAIN, 30));
		label.setBounds(616, 31, 52, 32);
		contentPane.add(label);

		JLabel image_screen = new JLabel("");
		image_screen.setBounds(44, 100, 550, 550);
		contentPane.add(image_screen);

		JButton FileButton = new JButton("���� ����");
		FileButton.setFont(new Font("a����������L", Font.PLAIN, 20));
		FileButton.setBackground(new Color(255, 250, 240));
		FileButton.setBorderPainted(false);
		FileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif"); // ��
				chooser.setFileFilter(filter); // ���� ���̾�α׿� ���� ���� ����

				int ret = chooser.showOpenDialog(null);
				if (ret != JFileChooser.APPROVE_OPTION) { // ����ڰ� â�� ������ �ݾҰų� ���
															// ��ư�� ���� ���
					JOptionPane.showMessageDialog(null, "������ �������� �ʾҽ��ϴ�", "���", JOptionPane.WARNING_MESSAGE);
					return;
				}
				filePath = chooser.getSelectedFile().getPath(); // ���ϰ�θ����˾ƿ´�.
				// ���� ���� ũ�� ���̴� ��!!!!!!!!!
				ImageIcon orimage = new ImageIcon(filePath);
				Image noreimage = orimage.getImage();
				Image reimage = noreimage.getScaledInstance(550, 560, java.awt.Image.SCALE_SMOOTH);
				ImageIcon reimageicon = new ImageIcon(reimage);
				image_screen.setIcon(reimageicon); 
			}
		});
		real_title = new JTextArea("");
		real_title.setFont(new Font("a����������L", Font.PLAIN, 20));
		real_title.setBackground(new Color(255, 250, 240));
		real_title.setBounds(648, 129, 591, 32);
		contentPane.add(real_title);

		FileButton.setBounds(260, 666, 129, 27);
		contentPane.add(FileButton);
		bg = new ButtonGroup();
		
		ra1 = new JRadioButton("���");
		ra1.setBackground(new Color(255, 250, 240));
		ra1.setFont(new Font("a����������L", Font.PLAIN, 20));
		ra1.setBounds(685, 658, 74, 27);
		contentPane.add(ra1);
		ra1.setActionCommand("���");
		
		ra2 = new JRadioButton("����");
		ra2.setFont(new Font("a����������L", Font.PLAIN, 20));
		ra2.setBackground(new Color(255, 250, 240));
		ra2.setBounds(765, 658, 74, 27);
		contentPane.add(ra2);
		ra2.setActionCommand("���");
		
		ra3 = new JRadioButton("ȭ��");
		ra3.setFont(new Font("a����������L", Font.PLAIN, 20));
		ra3.setBackground(new Color(255, 250, 240));
		ra3.setBounds(845, 658, 74, 27);
		contentPane.add(ra3);
		ra3.setActionCommand("���");
		
		ra4 = new JRadioButton("�ູ");
		ra4.setFont(new Font("a����������L", Font.PLAIN, 20));
		ra4.setBackground(new Color(255, 250, 240));
		ra4.setBounds(925, 658, 74, 27);
		contentPane.add(ra4);
		ra4.setActionCommand("���");
		
		ra5 = new JRadioButton("¥��");
		ra5.setFont(new Font("a����������L", Font.PLAIN, 20));
		ra5.setBackground(new Color(255, 250, 240));
		ra5.setBounds(1005, 658, 74, 27);
		contentPane.add(ra5);
		ra5.setActionCommand("���");
		
		ra6 = new JRadioButton("�ǰ�");
		ra6.setFont(new Font("a����������L", Font.PLAIN, 20));
		ra6.setBackground(new Color(255, 250, 240));
		ra6.setBounds(1085, 658, 74, 27);
		contentPane.add(ra6);
		ra6.setActionCommand("���");
		
		ra7 = new JRadioButton("����");
		ra7.setFont(new Font("a����������L", Font.PLAIN, 20));
		ra7.setBackground(new Color(255, 250, 240));
		ra7.setBounds(1165, 658, 74, 27);
		contentPane.add(ra7);
		ra7.setActionCommand("���");
		
		bg.add(ra1);
		bg.add(ra2);
		bg.add(ra3);
		bg.add(ra4);
		bg.add(ra5);
		bg.add(ra6);
		bg.add(ra7);
		
		JButton FinishButton = new JButton("����");
		FinishButton.setBackground(new Color(255, 250, 240));
		FinishButton.setBorderPainted(false);
		FinishButton.setFont(new Font("a����������L", Font.PLAIN, 24));
		
		Calendar oCalendar = Calendar.getInstance();

		int toyear = oCalendar.get(Calendar.YEAR);
		int tomonth = oCalendar.get(Calendar.MONTH) + 1;
		int today = oCalendar.get(Calendar.DAY_OF_MONTH);
		
		Jyear = new Choice();
		Jyear.setBackground(new Color(255, 250, 240));
		Jyear.setFont(new Font("a����������L", Font.PLAIN, 20));
		Jyear.setBounds(648, 93, 103, 24);
		Jyear.select(String.valueOf(toyear));
		contentPane.add(Jyear);

		Jmonth = new Choice();
		Jmonth.setBackground(new Color(255, 250, 240));
		Jmonth.setFont(new Font("a����������L", Font.PLAIN, 20));
		Jmonth.setBounds(765, 93, 52, 24);
		Jmonth.setName(String.valueOf(tomonth));
		contentPane.add(Jmonth);
		
		Jday = new Choice();
		Jday.setFont(new Font("a����������L", Font.PLAIN, 20));
		Jday.setBackground(new Color(255, 250, 240));
		Jday.setBounds(831, 93, 74, 24);
		Jday.select(String.valueOf(today));
		contentPane.add(Jday);
	
		for(int i = toyear + 5; i >= toyear - 5; i--) {
			Jyear.add(String.valueOf(i));
		}
		for(int i = 1; i <= 12; i++) {
			Jmonth.add(String.valueOf(i));
		}
		for(int i = 1; i <= 31; i++) {
			Jday.add(String.valueOf(i));
		}
		
		FinishButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			chooser = new JFileChooser();
			content = content_text.getText();
			// filePath = chooser.getSelectedFile().getPath(); // ����
			String newline = System.getProperty("line.separator");
			boolean hasNewline = content.contains(newline);
			if (filePath == null) {
				JOptionPane.showMessageDialog(null, "������ �������ּ���.", " ", JOptionPane.PLAIN_MESSAGE);
				return;
			} else if ((content == null) && (content.trim().length() < 0) && (hasNewline)) {
				JOptionPane.showMessageDialog(null, "�ϱ� ������ �ۼ����ּ���.", " ", JOptionPane.PLAIN_MESSAGE);
				return;
			}
			
			
		    Enumeration elements = bg.getElements();
		    while (elements.hasMoreElements()) {
		      AbstractButton button = (AbstractButton)elements.nextElement();
		      if (button.isSelected()) {
		    	  feel = button.getText();
		      }
		    }
		    if(feel == null){
		    	 JOptionPane.showMessageDialog(null, "����� �������ּ���.", " ", JOptionPane.PLAIN_MESSAGE); return;
		    }
			
			if(real_title.getText()==null){
				JOptionPane.showMessageDialog(null, "������ �Է����ּ���.", " ", JOptionPane.PLAIN_MESSAGE); return;
			}
			if(content_text.getText()==null){
				JOptionPane.showMessageDialog(null, "������ �Է����ּ���.", " ", JOptionPane.PLAIN_MESSAGE); return;
			}
			 	
			File f = new File(filePath);
		
			try {
				db_conn d = new db_conn();
				
				title = real_title.getText();
				content = content_text.getText();
				String date = Jyear.getSelectedItem()+"-"+Jmonth.getSelectedItem()+"-"+Jday.getSelectedItem();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date parsed = format.parse(date);
				java.sql.Date sql_date = new java.sql.Date(parsed.getTime());
				
				//�̹��� haru ������ ����
				
				File savelocation = new File("D:/haru/"+date+".jpg");
				if(savelocation.exists()==true){
					JOptionPane.showMessageDialog(null, "���� ��¥�� �ϱⰡ �ֽ��ϴ�.", " ", JOptionPane.PLAIN_MESSAGE); return;
				}
				else{
					try {
						BufferedImage bimage = ImageIO.read(f);
						ImageIO.write(bimage, "jpg", savelocation);
						imgfile = savelocation.toString();
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
				}
				
				
				
				String sql = "insert into diary(title,content,feel,writedate,img)"+"VALUES (?,?,?,?,?)";
				d.pstmt = d.con.prepareStatement(sql);	
				d.pstmt.setString(1, title);
				d.pstmt.setString(2, content);
				d.pstmt.setString(3, feel);
				d.pstmt.setDate(4, sql_date);
				d.pstmt.setString(5, imgfile);

				int i = d.pstmt.executeUpdate();

				if (i > 0) {
					JOptionPane.showMessageDialog(null, "�� ������ �ູ�ϱ�, ����� �Ϸ�", " ", JOptionPane.PLAIN_MESSAGE);
					d.pstmt.close();
					d.con.close();
					Main m = new Main();
					m.setVisible(true);
					setVisible(false);
					
				} else{
					JOptionPane.showMessageDialog(null, "���� �߻�", " ", JOptionPane.PLAIN_MESSAGE);
					
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
				} 

			}
		});
		FinishButton.setBounds(588, 727, 105, 27);
		contentPane.add(FinishButton);
		
		
		
	}

	JList month1 = new JList();

	

	public void content_chk(String content, JTextArea diary) {

		if (content.equals("�ϱ⸦ �Է��ϼ���.") == true) {
			diary.setText("");

		} else {
			return;
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Write frame = new Write();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
}
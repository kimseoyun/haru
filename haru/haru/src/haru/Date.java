package haru;

import java.awt.BorderLayout;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import javax.swing.JButton;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.Enumeration;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;

public class Date extends JFrame {


	private JPanel contentPane;
	private JTextField textField;
	static String month;
	static String year;
	public String look;
	public String feel_sel;

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
	 */
	public Date() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1307, 825);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 250, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton MainButton = new JButton(""); // 메인홈버튼
		MainButton.setBackground(new Color(255, 250, 240));
		MainButton.setBorderPainted(false);
		MainButton.setIcon(new ImageIcon("img/home.png"));
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
		MainButton.setBounds(25, 31, 40, 40);
		contentPane.add(MainButton);
		
		JButton WriteButton = new JButton(""); //쓰기버튼
		WriteButton.setBackground(new Color(255, 250, 240));
		WriteButton.setBorderPainted(false);
		WriteButton.setIcon(new ImageIcon("img/write_icon.png"));
		WriteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Write write=new Write();
				write.setVisible(true);
				setVisible(false);
			}
		});
		WriteButton.setBounds(1224, 31, 40, 40);
		contentPane.add(WriteButton);
		
		JLabel label = new JLabel("하루");
		label.setFont(new Font("a엄마의편지L", Font.PLAIN, 30));
		label.setBounds(616, 31, 52, 32);
		contentPane.add(label);
		
		
		ButtonGroup mg=new ButtonGroup();
		
		JRadioButton m1 = new JRadioButton("1월 ");
		m1.setBackground(new Color(255, 250, 240));
		m1.setFont(new Font("a엄마의편지L", Font.PLAIN, 22));
		m1.setBounds(173, 126, 71, 40);
		contentPane.add(m1);
		
		JRadioButton m2 = new JRadioButton("2월");
		m2.setBackground(new Color(255, 250, 240));
		m2.setFont(new Font("a엄마의편지L", Font.PLAIN, 22));
		m2.setBounds(253, 126, 63, 40);
		contentPane.add(m2);
		
		JRadioButton m3 = new JRadioButton("3월 ");
		m3.setBackground(new Color(255, 250, 240));
		m3.setFont(new Font("a엄마의편지L", Font.PLAIN, 22));
		m3.setBounds(322, 126, 73, 40);
		contentPane.add(m3);
		
		JRadioButton m4 = new JRadioButton("4월 ");
		m4.setBackground(new Color(255, 250, 240));
		m4.setFont(new Font("a엄마의편지L", Font.PLAIN, 22));
		m4.setBounds(398, 126, 73, 40);
		contentPane.add(m4);
		
		JRadioButton m5 = new JRadioButton("5월 ");
		m5.setBackground(new Color(255, 250, 240));
		m5.setFont(new Font("a엄마의편지L", Font.PLAIN, 22));
		m5.setBounds(476, 126, 73, 40);
		contentPane.add(m5);
		
		JRadioButton m6 = new JRadioButton("6월 ");
		m6.setBackground(new Color(255, 250, 240));
		m6.setFont(new Font("a엄마의편지L", Font.PLAIN, 22));
		m6.setBounds(555, 126, 73, 40);
		contentPane.add(m6);
		
		JRadioButton m7 = new JRadioButton("7월 ");
		m7.setBackground(new Color(255, 250, 240));
		m7.setFont(new Font("a엄마의편지L", Font.PLAIN, 22));
		m7.setBounds(634, 126, 73, 40);
		contentPane.add(m7);
		
		JRadioButton m8 = new JRadioButton("8월 ");
		m8.setBackground(new Color(255, 250, 240));
		m8.setFont(new Font("a엄마의편지L", Font.PLAIN, 22));
		m8.setBounds(713, 126, 73, 40);
		contentPane.add(m8);
		
		JRadioButton m9 = new JRadioButton("9월 ");
		m9.setBackground(new Color(255, 250, 240));
		m9.setFont(new Font("a엄마의편지L", Font.PLAIN, 22));
		m9.setBounds(792, 126, 71, 40);
		contentPane.add(m9);
		
		JRadioButton m10 = new JRadioButton("10월 ");
		m10.setBackground(new Color(255, 250, 240));
		m10.setFont(new Font("a엄마의편지L", Font.PLAIN, 22));
		m10.setBounds(869, 126, 78, 40);
		contentPane.add(m10);
		
		JRadioButton m11 = new JRadioButton("11월 ");
		m11.setBackground(new Color(255, 250, 240));
		m11.setFont(new Font("a엄마의편지L", Font.PLAIN, 22));
		m11.setBounds(953, 126, 73, 40);
		contentPane.add(m11);
		
		JRadioButton m12 = new JRadioButton("12월 ");
		m12.setBackground(new Color(255, 250, 240));
		m12.setFont(new Font("a엄마의편지L", Font.PLAIN, 22));
		m12.setBounds(1032, 126, 78, 40);
		contentPane.add(m12);
		
		mg.add(m1);
		mg.add(m2);
		mg.add(m3);
		mg.add(m4);
		mg.add(m5);
		mg.add(m6);
		mg.add(m7);
		mg.add(m8);
		mg.add(m9);
		mg.add(m10);
		mg.add(m11);
		mg.add(m12);
		
		ButtonGroup yg = new ButtonGroup();
		
		JRadioButton year12 = new JRadioButton("2012년");
		year12.setBackground(new Color(255, 250, 240));
		year12.setFont(new Font("a엄마의편지L", Font.PLAIN, 22));
		year12.setBounds(114, 203, 91, 40);
		contentPane.add(year12);
		
		JRadioButton year13 = new JRadioButton("2013년");
		year13.setBackground(new Color(255, 250, 240));
		year13.setFont(new Font("a엄마의편지L", Font.PLAIN, 22));
		year13.setBounds(211, 203, 91, 40);
		contentPane.add(year13);
		
		JRadioButton year14 = new JRadioButton("2014년");
		year14.setBackground(new Color(255, 250, 240));
		year14.setFont(new Font("a엄마의편지L", Font.PLAIN, 22));
		year14.setBounds(308, 203, 91, 40);
		contentPane.add(year14);
		
		JRadioButton year15 = new JRadioButton("2015년");
		year15.setBackground(new Color(255, 250, 240));
		year15.setFont(new Font("a엄마의편지L", Font.PLAIN, 22));
		year15.setBounds(405, 203, 91, 40);
		contentPane.add(year15);
		
		JRadioButton year16 = new JRadioButton("2016년");
		year16.setBackground(new Color(255, 250, 240));
		year16.setFont(new Font("a엄마의편지L", Font.PLAIN, 22));
		year16.setBounds(502, 203, 91, 40);
		contentPane.add(year16);
		
		JRadioButton year17 = new JRadioButton("2017년");
		year17.setBackground(new Color(255, 250, 240));
		year17.setFont(new Font("a엄마의편지L", Font.PLAIN, 22));
		year17.setBounds(599, 203, 91, 40);
		contentPane.add(year17);
		
		JRadioButton year18 = new JRadioButton("2018년");
		year18.setBackground(new Color(255, 250, 240));
		year18.setFont(new Font("a엄마의편지L", Font.PLAIN, 22));
		year18.setBounds(696, 203, 91, 40);
		contentPane.add(year18);

		JRadioButton year19 = new JRadioButton("2019년");
		year19.setFont(new Font("a엄마의편지L", Font.PLAIN, 22));
		year19.setBackground(new Color(255, 250, 240));
		year19.setBounds(792, 203, 91, 40);
		contentPane.add(year19);
		
		JRadioButton year20 = new JRadioButton("2020년");
		year20.setFont(new Font("a엄마의편지L", Font.PLAIN, 22));
		year20.setBackground(new Color(255, 250, 240));
		year20.setBounds(887, 203, 91, 40);
		contentPane.add(year20);
		
		JRadioButton year21 = new JRadioButton("2021년");
		year21.setFont(new Font("a엄마의편지L", Font.PLAIN, 22));
		year21.setBackground(new Color(255, 250, 240));
		year21.setBounds(987, 203, 91, 40);
		contentPane.add(year21);
		
		JRadioButton year22 = new JRadioButton("2022년");
		year22.setFont(new Font("a엄마의편지L", Font.PLAIN, 22));
		year22.setBackground(new Color(255, 250, 240));
		year22.setBounds(1086, 203, 91, 40);
		contentPane.add(year22);
		
		yg.add(year12);
		yg.add(year13);
		yg.add(year14);
		yg.add(year15);
		yg.add(year16);
		yg.add(year17);
		yg.add(year18);
		yg.add(year19);
		yg.add(year20);
		yg.add(year21);
		yg.add(year22);
		
		JTextArea search = new JTextArea("검색할 내용을 입력하세요.");
		search.addMouseListener(new MouseAdapter() { // 클릭 시 안내멘트 지우는 메소드
			@Override
			public void mouseClicked(MouseEvent e) {
				search.setText("");
			}
		});
		search.setFont(new Font("a엄마의편지L", Font.PLAIN, 20));
		search.setBackground(new Color(255, 250, 240));
		search.setBounds(341, 584, 573, 40);
		contentPane.add(search);
		
		
		JButton SearchButton = new JButton(""); //검색버튼
		SearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Result res;
				try {
					look=search.getText();
					res = new Result(look);
					res.setVisible(true);
					setVisible(false);	
				
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		SearchButton.setIcon(new ImageIcon("img/search.png"));
		SearchButton.setBorderPainted(false);
		SearchButton.setBackground(new Color(255, 250, 240));
		SearchButton.setBounds(917, 584, 40, 40);
		contentPane.add(SearchButton);
		
		JButton myButton = new JButton("당신의 하루를 찾아보세요.");
		myButton.setFont(new Font("a엄마의편지L", Font.PLAIN, 20));
		myButton.setBackground(Color.WHITE);
		myButton.setBounds(517, 287, 253, 34);
		contentPane.add(myButton);
		
		JRadioButton feel1 = new JRadioButton("기쁨");
		feel1.setFont(new Font("a엄마의편지L", Font.PLAIN, 22));
		feel1.setBackground(new Color(255, 250, 240));
		feel1.setBounds(263, 410, 91, 40);
		contentPane.add(feel1);
		
		JRadioButton feel2 = new JRadioButton("슬픔");
		feel2.setFont(new Font("a엄마의편지L", Font.PLAIN, 22));
		feel2.setBackground(new Color(255, 250, 240));
		feel2.setBounds(380, 410, 91, 40);
		contentPane.add(feel2);
		
		JRadioButton feel3 = new JRadioButton("화남");
		feel3.setFont(new Font("a엄마의편지L", Font.PLAIN, 22));
		feel3.setBackground(new Color(255, 250, 240));
		feel3.setBounds(502, 410, 91, 40);
		contentPane.add(feel3);
		
		JRadioButton feel4 = new JRadioButton("행복");
		feel4.setFont(new Font("a엄마의편지L", Font.PLAIN, 22));
		feel4.setBackground(new Color(255, 250, 240));
		feel4.setBounds(616, 410, 91, 40);
		contentPane.add(feel4);
		
		JRadioButton feel5 = new JRadioButton("짜증");
		feel5.setFont(new Font("a엄마의편지L", Font.PLAIN, 22));
		feel5.setBackground(new Color(255, 250, 240));
		feel5.setBounds(723, 410, 91, 40);
		contentPane.add(feel5);
		
		JRadioButton feel6 = new JRadioButton("피곤");
		feel6.setFont(new Font("a엄마의편지L", Font.PLAIN, 22));
		feel6.setBackground(new Color(255, 250, 240));
		feel6.setBounds(823, 410, 91, 40);
		contentPane.add(feel6);
		
		JRadioButton feel7 = new JRadioButton("예민");
		feel7.setFont(new Font("a엄마의편지L", Font.PLAIN, 22));
		feel7.setBackground(new Color(255, 250, 240));
		feel7.setBounds(935, 410, 91, 40);
		contentPane.add(feel7);
		
		ButtonGroup feel_gr = new ButtonGroup();
		feel_gr.add(feel1);
		feel_gr.add(feel2);
		feel_gr.add(feel3);
		feel_gr.add(feel4);
		feel_gr.add(feel5);
		feel_gr.add(feel6);
		feel_gr.add(feel7);
		
		JButton button_feel = new JButton("당신의 하루를 찾아보세요.");
		button_feel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (feel1.isSelected()) feel_sel = feel1.getText() ;
				if (feel2.isSelected()) feel_sel = feel2.getText() ;
				if (feel3.isSelected()) feel_sel = feel3.getText() ;
				if (feel4.isSelected()) feel_sel = feel4.getText() ;
				if (feel5.isSelected()) feel_sel = feel5.getText() ;
				if (feel6.isSelected()) feel_sel = feel6.getText() ;
				if (feel7.isSelected()) feel_sel = feel7.getText() ;
				
				if(feel_gr.isSelected(null)) {
					JOptionPane.showMessageDialog(null, "기분을 선택해주세요.", "경고", JOptionPane.WARNING_MESSAGE);
					return;
				}
				else if (feel_sel != null){
					//결과창
					Result result;
					try {
						result = new Result(feel_sel,0);
						result.setVisible(true);
						setVisible(false);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			}
		});
		button_feel.setFont(new Font("a엄마의편지L", Font.PLAIN, 20));
		button_feel.setBackground(Color.WHITE);
		button_feel.setBounds(517, 461, 253, 34);
		contentPane.add(button_feel);
		
		myButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (m1.isSelected()) month = m1.getText() ;
				if (m2.isSelected()) month =m2.getText() ;
				if (m3.isSelected())month = m3.getText() ;
				if (m4.isSelected()) month =m4.getText() ;
				if (m5.isSelected())month = m5.getText() ;
				if (m6.isSelected())month = m6.getText() ;
				if (m7.isSelected())month = m7.getText() ;
				if (m8.isSelected())month = m8.getText() ;
				if (m9.isSelected())month = m9.getText() ;
				if (m10.isSelected())month = m10.getText() ;
				if (m11.isSelected())month = m11.getText() ;
				if (m12.isSelected())month = m12.getText() ;
				
				if(year12.isSelected()) year =  year12.getText(); 
				if(year13.isSelected()) year = year13.getText();
				if(year14.isSelected()) year = year14.getText();
				if(year15.isSelected()) year = year15.getText();
				if(year16.isSelected())year =  year16.getText();
				if(year17.isSelected()) year = year17.getText();
				if(year18.isSelected()) year = year18.getText();
				if(year19.isSelected()) year = year19.getText();
				if(year20.isSelected()) year = year20.getText();
				if(year21.isSelected()) year = year21.getText();
				if(year22.isSelected()) year = year22.getText();
				
				if(mg.isSelected(null) || yg.isSelected(null)) {
					JOptionPane.showMessageDialog(null, "월과 연도 둘다 선택해주세요.", "경고", JOptionPane.WARNING_MESSAGE);
					return;
				}
				else if (year != null && month != null){
					//결과창
					month = month.replace("월", "");
					year = year.replace("년", "");
				
					Result result;
					try {
						result = new Result(month,year);
						result.setVisible(true);
						setVisible(false);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				else{
					Result result;
					try {
						result = new Result(look);
						result.setVisible(true);
						setVisible(false);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			}
		});
	}
}
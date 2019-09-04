package ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import util.checkUI;

import javax.swing.JButton;
import javax.swing.JComboBox;
//该界面为打卡时间设置界面
public class SetAttTimeUI extends JFrame{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String[] hourList=new String[24];
	private String[] minuteList=new String[12];
	
	private String hour1="08",hour2="17",minute1="30",minute2="00";
	
	private void setStr() {
		String check;
		for (int i = 0; i < 24; i++) {
			check=(i<10?"0"+i:String.valueOf(i));
			hourList[i]=check;
		}
		for (int i = 0; i < 12; i++) {
			check=(i*5<10?"0"+i*5:String.valueOf(i*5));
			minuteList[i]=check;
		}
	}

	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the application.
	 */
	public SetAttTimeUI(UserUI ui) {
		setTitle("打卡设置");
		setStr();
		initialize(ui);
	}
	
	private void setCheckUi(String str) {
		checkUI cUi=new checkUI(str);
		cUi.setLocationRelativeTo(this);
		cUi.setResizable(false);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(UserUI ui) {
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GREEN);
		panel.setBounds(74, 41, 278, 45);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("早打卡时间");
		lblNewLabel.setBounds(10, 10, 77, 22);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("：");
		lblNewLabel_1.setBounds(158, 12, 17, 20);
		panel.add(lblNewLabel_1);
		
		
		JComboBox<String> comboBox = new JComboBox<>(hourList);
		//JComboBox comboBox=new JComboBox();
		comboBox.setBounds(97, 10, 51, 24);
		panel.add(comboBox);
		comboBox.setSelectedIndex(9);
		comboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				hour1= (String) comboBox.getSelectedItem();
			}
		});
		
		JComboBox<String> comboBox_1 = new JComboBox<>(minuteList);
		//JComboBox comboBox_1=new JComboBox();
		comboBox_1.setBounds(185, 10, 51, 24);
		panel.add(comboBox_1);
		comboBox_1.setSelectedIndex(7);
		comboBox_1.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				minute1= (String) comboBox_1.getSelectedItem();
			}
		});
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.GREEN);
		panel_1.setBounds(74, 129, 278, 45);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("晚退勤时间");
		lblNewLabel_2.setBounds(10, 12, 78, 23);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("：");
		lblNewLabel_3.setBounds(158, 12, 17, 20);
		panel_1.add(lblNewLabel_3);
		
		JComboBox<String> comboBox_2 = new JComboBox<String>(hourList);
		//JComboBox comboBox_2=new JComboBox();
		comboBox_2.setBounds(98, 10, 50, 24);
		panel_1.add(comboBox_2);
		comboBox_2.setSelectedIndex(18);
		comboBox_2.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				hour2= (String) comboBox_2.getSelectedItem();
			}
		});
		
		JComboBox<String> comboBox_3 = new JComboBox<String>(minuteList);
		//JComboBox comboBox_3=new JComboBox();
		comboBox_3.setBounds(185, 10, 50, 24);
		panel_1.add(comboBox_3);
		comboBox_3.setSelectedIndex(0);
		comboBox_3.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				minute2= (String) comboBox_3.getSelectedItem();
			}
		});
		
		JButton button = new JButton("确定");
		button.setBounds(160, 202, 93, 23);
		getContentPane().add(button);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ui.setAtt(hour1+minute1,hour2+minute2);
					setCheckUi("设置成功！");
				} catch (Exception e2) {
					setCheckUi("设置失败！");
				}
			}
		});
		setVisible(true);
		setResizable(false);
	}
	
}

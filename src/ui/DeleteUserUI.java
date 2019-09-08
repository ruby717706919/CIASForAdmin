package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import util.SqlConnect;
import util.checkUI;

import javax.swing.JLabel;
import javax.swing.JButton;
//该ui为用户删除界面
public class DeleteUserUI {
	private JFrame frame;
	private JTextField textField;
	private SqlConnect sConnect=null;//必要组件


	/**
	 * Launch the application.
	 */
	/**
	 * Create the application.
	 */
	public DeleteUserUI() {
		sConnect=new SqlConnect();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame=new JFrame();
		frame.getContentPane().setEnabled(false);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("数据删除");
		frame.setVisible(true);
		frame.setResizable(false);//主frame
		
		;
		
		textField = new JTextField();
		textField.setBounds(167, 123, 66, 21);
		frame.getContentPane().add(textField);
		textField.setColumns(10);//输入要删除员工的id
		
		JLabel lblNewLabel = new JLabel("请输入要删除数据的员工ID");
		lblNewLabel.setBounds(107, 70, 206, 21);
		frame.getContentPane().add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton button = new JButton("删除");
		button.setBounds(167, 170, 66, 21);
		frame.getContentPane().add(button);
		button.addActionListener(new ActionListener() {//实现删除
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (sConnect.deleteUser(textField.getText())) {
					checkUI ui=new checkUI("删除成功！");
					ui.setLocationRelativeTo(frame);
				}else {
					checkUI ui=new checkUI("删除失败！");
					ui.setLocationRelativeTo(frame);
				}
				
			}
		});
	}
	
	public void setlocation(JFrame fr) {
		frame.setLocationRelativeTo(fr);
	}

}

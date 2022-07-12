package SalaryManagement;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class S_change extends JFrame{
	public JComboBox jcb;
	public JLabel jLabel1, jLabel2;
	public JTextField jtf;
	public JButton jbtn, jbtn1;

	S_change(String s) {
		super(s);
		getContentPane().setLayout(null);
		setBounds(200, 200, 329, 200);
		jLabel1 = new JLabel("请选择职位:");
		jLabel2 = new JLabel("基本工资(元):");
		jbtn = new JButton("提交");
		jbtn1 = new JButton("退出");
		jbtn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		jcb = new JComboBox();
		jcb.addItem("管理");
		jcb.addItem("财务");
		jcb.addItem("技术");
		jcb.addItem("销售");
		
		jtf = new JTextField(10);
		jLabel1.setBounds(30, 15, 110, 30);
		jcb.setBounds(145, 20, 100, 20);
		jLabel2.setBounds(40, 59, 100, 30);
		jtf.setBounds(145, 64, 100, 20);
		jbtn.setBounds(96, 110, 60, 30);
		jbtn1.setBounds(185, 110, 60, 30);
		Container con = getContentPane();
		con.add(jLabel1);
		con.add(jcb);
		con.add(jLabel2);
		con.add(jtf);
		con.add(jbtn);
		con.add(jbtn1);
		jbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				change();
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	public void change() {  
		String str1, str3;
		float str2;
		str1 = (String) jcb.getSelectedItem();
		str3 = jtf.getText();
		if (str3.equals(""))
			JOptionPane.showMessageDialog(this, "请输入基本工资", "提示",JOptionPane.INFORMATION_MESSAGE);
		else {
			str2 = Float.parseFloat(str3);
			Conn conn = new Conn();
				try {
					Connection con = conn.Getdata();
					PreparedStatement psmt = con.prepareStatement("update pos set basic_salary=? where post='" + str1 + "'");
					psmt.setFloat(1, str2);
					psmt.executeUpdate();
					JOptionPane.showMessageDialog(this, "修改成功", "提示",JOptionPane.INFORMATION_MESSAGE);
					dispose();
					psmt.close();
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}
	}


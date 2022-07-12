package SalaryManagement;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

class Delete extends JFrame{
	public JLabel jLabel;
	public JTextField jtf;
	public JButton jbtn, jbtn1;

	Delete(String s) {
		super(s);
		getContentPane().setLayout(null);
		setBounds(200, 200, 300, 200);
		jLabel = new JLabel("请输入删除员工员工号:");
		jtf = new JTextField(10);
		jbtn = new JButton("提交");
		jbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		jbtn1 = new JButton("退出");
		jbtn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		jLabel.setBounds(60, 24, 200, 30);
		jtf.setBounds(70, 64, 148, 30);
		jbtn.setBounds(70, 107, 60, 30);
		jbtn1.setBounds(158, 107, 60, 30);
		Container con = getContentPane();
		con.add(jLabel);
		con.add(jtf);
		con.add(jbtn);
		con.add(jbtn1);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	public void delete() {
		String str;
		str = jtf.getText().trim();
		Conn conn = new Conn();

		try {
			Connection con = conn.Getdata();
			PreparedStatement psmt1 = con.prepareStatement("select name from employee where e_num=?");
			psmt1.setString(1, str);
			ResultSet rs = psmt1.executeQuery();
			if (rs.next()) {
				Connection con1 = conn.Getdata();
				PreparedStatement psmt = con1.prepareStatement("delete from sals where e_num=?");
				psmt.setString(1, str);
				psmt.executeUpdate();
				psmt = con1.prepareStatement("delete from employee where e_num=?");
				psmt.setString(1, str);
				psmt.executeUpdate();
				JOptionPane.showMessageDialog(this, "删除成功", "删除",JOptionPane.INFORMATION_MESSAGE);
				psmt.close();
				con1.close();
			} else
				JOptionPane.showMessageDialog(this, "无此员工", "错误",JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

package SalaryManagement;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Alter extends JFrame{
	public JLabel jLabelid, jLabelname, jLabelpassw, jLabelsex;
	public JLabel jLabeljob, jLabeldepartment;
	public JTextField jtfid, jtfname, jtfpassw;
	public JComboBox jcbsex, jcbjob, jcbkeshi;
	public JButton jbtn, jbtn1;

	Alter(String s) {
		super(s);
		getContentPane().setLayout(null);
		setBounds(200, 200, 321, 350);
		
		jLabelid = new JLabel("员工编号:");
		jLabelname = new JLabel("姓名:");
		jLabelpassw = new JLabel("密码:");
		jLabelsex = new JLabel("性别:");
		jLabeljob = new JLabel("职务:");
		jLabeldepartment = new JLabel("部门:");

		jtfid = new JTextField(10);
		jtfname = new JTextField(5);
		jtfpassw = new JTextField(10);
		
		jcbjob = new JComboBox();
		jcbjob.addItem("管理");
		jcbjob.addItem("财务");
		jcbjob.addItem("技术");
		jcbjob.addItem("销售");
		
		jcbsex = new JComboBox();
		jcbsex.addItem("男");
		jcbsex.addItem("女");

		jcbkeshi = new JComboBox();
		jcbkeshi.addItem("管理部");
		jcbkeshi.addItem("财务部");
		jcbkeshi.addItem("技术部");
		jcbkeshi.addItem("销售部");

		jbtn = new JButton("提交");
		jbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alter();
			}
		});
		jbtn1 = new JButton("退出");
		jbtn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		jLabelid.setBounds(58, 33, 50, 20);
		jtfid.setBounds(118, 33, 125, 20);
		jLabelname.setBounds(68, 63, 50, 20);
		jtfname.setBounds(118, 63, 125, 20);
		jLabelpassw.setBounds(68, 98, 50, 20);
		jtfpassw.setBounds(118, 98, 125, 20);
		jLabelsex.setBounds(68, 133, 50, 20);
		jcbsex.setBounds(116, 133, 127, 20);
		jLabeljob.setBounds(68, 171, 50, 20);
		jcbjob.setBounds(115, 171, 128, 20);
		jLabeldepartment.setBounds(68, 211, 50, 20);
		jcbkeshi.setBounds(115, 211, 128, 20);
		jbtn.setBounds(113, 255, 60, 30);
		jbtn1.setBounds(183, 255, 60, 30);
		
		Container con = getContentPane();
		con.add(jLabelid);
		con.add(jtfid);
		con.add(jLabelname);
		con.add(jtfname);
		con.add(jLabelpassw);
		con.add(jtfpassw);
		con.add(jLabelsex);
		con.add(jcbsex);
		con.add(jLabeljob);
		con.add(jcbjob);
		con.add(jLabeldepartment);
		con.add(jcbkeshi);
		con.add(jbtn);
		con.add(jbtn1);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	public void alter() {
		String e_num1, name1, password1, sex1, stafftype, department;
		e_num1 = jtfid.getText().trim();
		name1 = jtfname.getText();
		password1 = jtfpassw.getText();
		sex1 = (String) jcbsex.getSelectedItem();
		stafftype = (String) jcbjob.getSelectedItem();
		department = (String) jcbkeshi.getSelectedItem();
		if (e_num1.equals("")) {
			JOptionPane.showMessageDialog(this, "请输入员工号", "提示",JOptionPane.INFORMATION_MESSAGE);} 
		else {
			Conn conn = new Conn();
			try {
				Connection con = conn.Getdata();
				PreparedStatement psmt1 = con.prepareStatement("select name,password,sex,post,department from employee where e_num LIKE '"+ e_num1 + "'");
				ResultSet rs = psmt1.executeQuery();
				boolean bl = rs.next();
				if (bl) {
					if (name1.equals(""))
						name1 = rs.getString("name");
					if (password1.equals(""))
						password1 = rs.getString("password");
					if (stafftype.equals(""))
						stafftype = rs.getString("sex");
					if (department.equals(""))
						department = rs.getString("depatment");
					psmt1.close();
					con.close();
					try {
						Connection con1 = conn.Getdata();
						PreparedStatement psmt2 = con1.prepareStatement("update employee set name=?,password=?,sex=?,post=?,department=? where e_num='"+ e_num1+ "'");
						psmt2.setString(1, name1);
						psmt2.setString(2, password1);
						psmt2.setString(3, sex1);
						psmt2.setString(4, stafftype);
						psmt2.setString(5, department);
						psmt2.executeUpdate();
						JOptionPane.showMessageDialog(this, "修改成功", "修改",JOptionPane.INFORMATION_MESSAGE);
						dispose();
						psmt2.close();
						con1.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else
					JOptionPane.showMessageDialog(this, "请输入正确的员工号", "错误",JOptionPane.ERROR_MESSAGE);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

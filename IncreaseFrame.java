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

public class IncreaseFrame extends JFrame{
	public JLabel jLabelid, jLabelname, jLabelpassw, jLabelsex;
	public JLabel jLabeljob, jLabeldepartment;
	public JTextField jtfid, jtfname, jtfpassw, jtfsex, jtf7;
	public JComboBox jcbsex, jcbjob,jcbkeshi;
	public JButton jbtn, jbtn1;

	IncreaseFrame(String s) {
		super(s);
		getContentPane().setLayout(null);
		setBounds(200, 200, 300, 350);
		
		jLabelid = new JLabel("员工编号:");
		jLabelname = new JLabel("姓名:");
		jLabelpassw = new JLabel("密码:");
		jLabelsex = new JLabel("性别:");
		jLabeljob = new JLabel("职务:");
		jLabeldepartment = new JLabel("部门:");
		jtfid = new JTextField();
		jtfname = new JTextField();
		jtfpassw = new JTextField();
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
		
		jbtn = new JButton("增加");
		jbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				add();
			}
		});
		
		jbtn1 = new JButton("退出");
		jbtn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		jLabelid.setBounds(49, 35, 50, 20);
		jtfid.setBounds(115, 35, 125, 20);
		jLabelname.setBounds(60, 69, 50, 20);
		jtfname.setBounds(115, 69, 125, 20);
		jLabelpassw.setBounds(60, 99, 50, 20);
		jtfpassw.setBounds(115, 99, 125, 20);
		jLabelsex.setBounds(60, 135, 50, 20);
		jcbsex.setBounds(115, 135, 125, 20);
		jLabeljob.setBounds(60, 172, 50, 20);
		jcbjob.setBounds(115, 172, 125, 20);
		jLabeldepartment.setBounds(60, 213, 60, 20);
		jcbkeshi.setBounds(115, 213, 125, 20);
		jbtn.setBounds(60, 259, 60, 30);
		jbtn1.setBounds(180, 259, 60, 30);
		
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

	public void add() {
		try {
			String str1, str2, str3, str4, str5, str6;
			str1 = jtfid.getText();
			str2 = jtfname.getText();
			str3 = jtfpassw.getText();
			//System.out.println(str3.length());
			str4 = (String) jcbsex.getSelectedItem();
			str5 = (String) jcbjob.getSelectedItem();
			str6 = (String)jcbkeshi.getSelectedItem();

			if (str1.equals("") || str2.equals("") || str3.equals("")|| str6.equals("")) {
				JOptionPane.showMessageDialog(this, "请完整输入", "错误",JOptionPane.ERROR_MESSAGE);}
			else {
				Conn conn=new Conn();
				Connection con = conn.Getdata();
				PreparedStatement psmt = con.prepareStatement("insert into employee values(?,?,?,?,?,?)");
				psmt.setString(1, str1);
				psmt.setString(3, str2);
				psmt.setString(4, str3);
				psmt.setString(5, str4);
				psmt.setString(2, str5);
				psmt.setString(6, str6);
				psmt.executeUpdate();
				JOptionPane.showMessageDialog(this, "添加成功！", "添加",JOptionPane.INFORMATION_MESSAGE);
				dispose();
				psmt.close();
				con.close();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "员工号重复", "错误",JOptionPane.ERROR_MESSAGE);
			
		}
	}

}

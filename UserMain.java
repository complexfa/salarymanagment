package SalaryManagement;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class UserMain extends JFrame {
	public JTable table;
	public JLabel jLabel;
	public JButton jbtn1, jbtn2, jbtn3;
	public JTextField jtf1;
	
	public JComboBox jcb;
	public Object a[][];
	public Object name1[] = { "员工号", "奖金差补", "罚款", "基本工资(元)", "实发工资(元)", "月份" };
	
	private static String empNum;
	
	public UserMain(String s,String eNum) {
		super(s);
		empNum = eNum;
		setBounds(100, 100, 387, 295);
		getContentPane().setLayout(null);

		jLabel = new JLabel("请选择您要查询的月份:");
		jbtn1 = new JButton("查询");
		jbtn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				user_query();
			}
		});
		jbtn2 = new JButton("退出");
		jbtn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		jbtn3 = new JButton("修改密码");
		jbtn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				change(empNum);
			}
		});

		jcb = new JComboBox();
		for(int i =1;i<13;i++){
			jcb.addItem(i);
		}
		jLabel.setBounds(33, 20, 150, 23);
		jcb.setBounds(188, 20, 91, 23);
		jbtn1.setBounds(56, 90, 91, 45);
		jbtn2.setBounds(188, 90, 91, 45);
		jbtn3.setBounds(120, 169, 91, 45);
		
		jtf1 = new JTextField(10);
		jtf1.setBounds(210, 150, 100, 25);
		
		Container con = getContentPane();
		con.add(jLabel);
		con.add(jcb);
		con.add(jbtn1);
		con.add(jbtn2);
		con.add(jbtn3);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	public void change(String empNum) {
		PwdFrame pc = new PwdFrame("修改密码",empNum);
	}

	public void user_query() {
		String str2;
		final JFrame jf=new JFrame();
		JButton jbtn;
		jbtn = new JButton("关闭");
		jbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jf.dispose();
			}
		});
		jf.getContentPane().setLayout(new BoxLayout(jf.getContentPane(), BoxLayout.Y_AXIS));
		jf.setBounds(100, 100, 400, 200);
		str2 = String.valueOf(jcb.getSelectedItem());
		Conn conn = new Conn();
		try {
			Connection con = conn.Getdata();
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery("select e_num,bonus,deduct,s_basicsalary,fact_salary,month"+ " from sals where e_num='"+ empNum+ "'and month='" + str2 + "'");
			if (!(rs.next()))
				JOptionPane.showMessageDialog(this, "该月暂未工资结算", "提示",JOptionPane.INFORMATION_MESSAGE);
			else {
				Object name[] = { "员工编号", "奖金", "扣除工资", "基本工资（元）", "实际工资（元）", "月份" };
				a = new Object[1][6];
				String num = rs.getString(1);
				String bonus = rs.getString(2);
				String deduct = rs.getString(3);
				String basicsalary = rs.getString(4);
				String factsalary = rs.getString(5);
				String month = rs.getString(6);
				a[0][0] = num;
				a[0][1] = bonus;
				a[0][2] = deduct;
				a[0][3] = basicsalary;
				a[0][4] = factsalary;
				a[0][5] = month;
				table = new JTable(a, name);
				Container con1 = jf.getContentPane();
				con1.add(new JScrollPane(table));
				con1.add(jbtn);
				jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				jf.setVisible(true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}

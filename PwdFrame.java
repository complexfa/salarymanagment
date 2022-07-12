package SalaryManagement;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class PwdFrame extends JFrame{
	public JLabel jLabel1, jLabel2, jLabel3;
	public JPasswordField jpwf1, jpwf2, jpwf3;
	public JButton jbtn, jbtn1;
	public JTextField jtf1;
	private static String empNum;

	public PwdFrame(String s,String eNum) {
		super(s);
		empNum = eNum;
		setBounds(100, 100, 330, 250);
		setLayout(null);
		jLabel1 = new JLabel("�����������:");
		jLabel2 = new JLabel("������������:");
		jLabel3 = new JLabel("���ٴ�����������:");
		jbtn = new JButton("ȷ��");
		jbtn1 = new JButton("�˳�");
		jbtn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		jpwf1 = new JPasswordField();
		jpwf2 = new JPasswordField();
		jpwf3 = new JPasswordField();
		
		jLabel1.setBounds(60, 20, 90, 30);
		jLabel2.setBounds(60, 60, 90, 30);
		jLabel3.setBounds(40, 100, 110, 30);
		jpwf1.setBounds(155, 25, 110, 20);
		jpwf2.setBounds(155, 65, 110, 20);
		jpwf3.setBounds(155, 105, 110, 20);
		jbtn.setBounds(70, 160, 60, 30);
		jbtn1.setBounds(190, 160, 60, 30);
		
		jtf1 = new JTextField(10);
		jtf1.setBounds(210, 150, 100, 25);
		jbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				check(empNum);
			}
		});
		Container con = getContentPane();

		con.add(jLabel1);
		con.add(jLabel2);
		con.add(jLabel3);
		con.add(jpwf1);
		con.add(jpwf2);
		con.add(jpwf3);
		con.add(jbtn);
		con.add(jbtn1);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	public void check(String empNum) {
		String str1, str2, str3;
		str1 = new String(jpwf1.getPassword());
		str2 = new String(jpwf2.getPassword());
		str3 = new String(jpwf3.getPassword());
		Conn conn = new Conn();
		try {

			Connection con = conn.Getdata();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select password from employee where e_num='"+ empNum + "'");
			rs.next();
			String password = rs.getString(1);
			if (password.trim().equals(str1)) {
				if (!(str2.trim().equals(""))) {
					if (str2.trim().equals(str3)) {
						stmt.executeUpdate("update employee set password='"+ str2+ "' where e_num='"+ empNum + "'");
						JOptionPane. showMessageDialog(this, "�����޸ĳɹ�","��ʾ", JOptionPane.INFORMATION_MESSAGE);
						dispose();
						stmt.close();
						con.close();
					} else {
						JOptionPane.showMessageDialog(this,"�����������벻һ�£�����������", "��ʾ",JOptionPane.INFORMATION_MESSAGE);}
				} else {
					JOptionPane.showMessageDialog(this, "������������", "��ʾ",JOptionPane.INFORMATION_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(this, "�������������", "����",JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

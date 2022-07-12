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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

 class MainFrame extends JFrame{
	public JComboBox jComboBox;
	public JLabel jLabel1;
	public JLabel jLabel2;
	public JButton jButton1;
	public JTextField jTextField;
	public JPasswordField jPasswordField;
	
	MainFrame(String username){
		super(username);
		setLayout(null);
		setBounds(100,100,500,400);
		jComboBox = new JComboBox();
		jComboBox.addItem("用户");
		jComboBox.addItem("管理员");
		jComboBox.setBounds(175,50,150,50);
		jLabel1 = new JLabel("用户名: ");
		jLabel2 = new JLabel("密码: ");
		jButton1 = new JButton("登录");
		jButton1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				testify();
			}
		});
		jTextField = new JTextField(10);
		jPasswordField = new JPasswordField(10);
		
		jLabel1.setBounds(155,150,50,30);
		jLabel2.setBounds(155,190,50,30);
		jTextField.setBounds(210,150,100,25);
		jPasswordField.setBounds(210,190,100,25);
		jButton1.setBounds(290,250,80,40);
		
		Container container = getContentPane();
		container.add(jComboBox);
		container.add(jLabel1);
		container.add(jTextField);
		container.add(jLabel2);
		container.add(jPasswordField);
		container.add(jButton1);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		}
	
	public void testify() {
		String string;
		string=(String)jComboBox.getSelectedItem();
		if(string == "管理员") {
			manager();
		}
		else {
			employee();
		}
	}
	
	public void manager() {
		String s1,s2;
		s1 = jTextField.getText();
		s2 = new String(jPasswordField.getPassword());
		Connection conn = null;
		Conn con = new Conn();
		conn = con.Getdata();
		
		try {
			PreparedStatement preparedStatement = conn.prepareStatement("select m_password from manage where m_num=' "+ s1 +" '");
			ResultSet res = preparedStatement.executeQuery();
			
			if(!res.next()) {
				JOptionPane.showMessageDialog(this, "查无此人","错误",JOptionPane.ERROR_MESSAGE);
			}
			else {
				String pwd = res.getString(1);
				//System.out.println(pwd);
				if (!pwd.equals(s2)) {
					JOptionPane.showMessageDialog(this, "密码错误", "错误",JOptionPane.ERROR_MESSAGE);
				} else {
					loader1();
					dispose();
				}
				preparedStatement.close();
				conn.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public void employee() {//用户登录
		String s1, s2;
		s1 = jTextField.getText();
		s2 = new String(jPasswordField.getPassword());
		Conn conn = new Conn();

		try {
			Connection con;
			con = conn.Getdata();
			PreparedStatement psmt = con.prepareStatement("select password from employee where  e_num='"+ s1 + "'");
			ResultSet res = psmt.executeQuery();
			if ((!res.next())) {
				JOptionPane.showMessageDialog(this, "查无此人", "错误",JOptionPane.ERROR_MESSAGE);
			} else {
				String pwd = res.getString(1);
				String pw = pwd.trim();
				if (!(pw.equals(s2))) {
					JOptionPane.showMessageDialog(this, "密码错误", "错误",JOptionPane.ERROR_MESSAGE);
				} else {
					loader2(s1);
					dispose();
				}
				psmt.close();
				con.close();
			}

		} catch (Exception e) {
		}

	}

	public void loader1() {
		// TODO Auto-generated method stub
		try {
			ManageFrame2 me = new ManageFrame2();
			me.setVisible(true);
		} catch (Exception ew) {
			//System.out.println(ew.getMessage());
		}
	}
	public void loader2(String empNum) {
			UserMain ld = new UserMain("用户查询",empNum);
		}
}

public class Login {
	public static void main(String[] args) {
		MainFrame lf = new MainFrame("用户登录");
		lf.jButton1.setSize(72, 40);
		lf.jButton1.setLocation(200, 279);
		lf.jComboBox.setSize(174, 30);
		lf.jPasswordField.setSize(174, 25);
		lf.jTextField.setSize(174, 25);
		lf.jLabel2.setSize(53, 30);
		lf.jLabel1.setSize(53, 30);
		lf.jPasswordField.setLocation(152, 144);
		lf.jTextField.setLocation(152, 75);
		lf.jComboBox.setLocation(152, 207);
		lf.jLabel2.setLocation(89, 141);
		lf.jLabel1.setLocation(89, 72);
		
		JLabel label1 = new JLabel();
		label1.setBounds(88, 211, 54, 22);
		lf.getContentPane().add(label1);
		
		JLabel label2= new JLabel();
		label2.setBounds(178, 24, 104, 30);
		lf.getContentPane().add(label2);
	}
}
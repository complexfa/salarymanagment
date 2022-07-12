package SalaryManagement;

import java.awt.Container;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class ManageFrame2 extends JFrame {
	private JPanel jContentPane = null;
	private JLabel jLabelid = null;
	private JTextField jTextFieldid = null;
	private JLabel jLabelmonth = null;
	private JLabel jLworkday = null;
	private JTextField jTfworkday = null;
	private JLabel jLabsentday = null;
	private JTextField jTfabsentday = null;
	private JComboBox jComboBoxmonth = null;
	private JButton jButtonOK = null;
	private JLabel jLsurchmonth = null;
	private JComboBox jCbsurchmonth = null;
	private JButton jButtonsurch = null;
	private JButton jButtonchangwage = null;
	private JButton jButtonchanginfo = null;
	private JButton jButtonaddworker = null;
	private JButton jButtondelworker = null;
	private JButton jButtonlookall = null;
	private JButton jButtonclose = null;

	private JTextField getJTextFieldid() {
		if (jTextFieldid == null) {
			jTextFieldid = new JTextField();
			jTextFieldid.setBounds(new Rectangle(110, 15, 100, 22));
		}
		return jTextFieldid;
	}//��ȡԱ���������


	private JTextField getJTfworkday() {
		if (jTfworkday == null) {
			jTfworkday = new JTextField();
			jTfworkday.setBounds(new Rectangle(110, 57, 100, 24));
		}
		return jTfworkday;
	}//��ȡ����


	private JTextField getJTfabsentday() {
		if (jTfabsentday == null) {
			jTfabsentday = new JTextField();
			jTfabsentday.setBounds(new Rectangle(380, 57, 86, 24));
		}
		return jTfabsentday;
	}//��ȡ�۳�н��


	private JComboBox getJComboBox1() {
		if (jComboBoxmonth == null) {
			jComboBoxmonth = new JComboBox();
			jComboBoxmonth.setBounds(new Rectangle(380, 14, 45, 22));
			for(int i=1;i<13;i++){
				jComboBoxmonth.addItem(i);
			}
		}
		return jComboBoxmonth;
	}//��ȡ�·�

	private JButton getJButtonOK() {
		if (jButtonOK == null) {
			jButtonOK = new JButton();
			jButtonOK.setBounds(new Rectangle(220,95, 62, 24));
			jButtonOK.setText("ȷ��");
			jButtonOK.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					count();
				}
			});
		}
		return jButtonOK;
	}//ȷ����

	private JComboBox getJCbsurchmonth() {
		if (jCbsurchmonth == null) {
			jCbsurchmonth = new JComboBox();
			jCbsurchmonth.setBounds(new Rectangle(260, 135, 45, 22));
			for(int i=1;i<13;i++){
				jCbsurchmonth.addItem(i);
			}
		}
		return jCbsurchmonth;
	}//����Ա������


	private JButton getJButtonsurch() {
		if (jButtonsurch == null) {
			jButtonsurch = new JButton();
			jButtonsurch.setBounds(new Rectangle(350, 135, 62, 27));
			jButtonsurch.setText("��ѯ");
			jButtonsurch.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					query();
				}
			});
		}
		return jButtonsurch;
	}//��ѯ��

	private JButton getJButtonchangwage() {
		if (jButtonchangwage == null) {
			jButtonchangwage = new JButton();
			jButtonchangwage.setBounds(new Rectangle(110, 170, 114, 39));
			jButtonchangwage.setText("�޸Ĺ���");
			jButtonchangwage.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					S_change sc = new S_change("�޸Ĺ���");
				}
			});
		}
		return jButtonchangwage;
	}//�޸Ĺ���


	private JButton getJButtonchanginfo() {
		if (jButtonchanginfo == null) {
			jButtonchanginfo = new JButton();
			jButtonchanginfo.setBounds(new Rectangle(311, 170, 114, 39));
			jButtonchanginfo.setText("�޸�Ա����Ϣ");
			jButtonchanginfo.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					Alter al = new Alter("�޸�Ա����Ϣ");
				}
			});
		}
		return jButtonchanginfo;
	}//�޸�Ա����Ϣ


	private JButton getJButtonaddworker() {
		if (jButtonaddworker == null) {
			jButtonaddworker = new JButton();
			jButtonaddworker.setBounds(new Rectangle(110, 230, 114, 39));
			jButtonaddworker.setText("����Ա����Ϣ");
			jButtonaddworker.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					IncreaseFrame inf = new IncreaseFrame("����Ա����Ϣ");
				}
			});
		}
		return jButtonaddworker;
	}//����Ա����Ϣ

	private JButton getJButtondelworker() {
		if (jButtondelworker == null) {
			jButtondelworker = new JButton();
			jButtondelworker.setBounds(new Rectangle(311, 230, 114, 40));
			jButtondelworker.setText("ɾ��Ա����Ϣ");
			jButtondelworker.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					Delete dl = new Delete("ɾ��Ա����Ϣ");
				}
			});
		}
		return jButtondelworker;
	}//ɾ��Ա����Ϣ

	private JButton getJButtonlookall() {
		if (jButtonlookall == null) {
			jButtonlookall = new JButton();
			jButtonlookall.setBounds(new Rectangle(110, 290, 114, 39));
			jButtonlookall.setText("�鿴����Ա��");
			jButtonlookall.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					lookover();
				}
			});
		}
		return jButtonlookall;
	}//�鿴����Ա����Ϣ

	private JButton getJButtonclose() {
		if (jButtonclose == null) {
			jButtonclose = new JButton();
			jButtonclose.setBounds(new Rectangle(311, 290, 114, 38));
			jButtonclose.setText("�˳�");
			jButtonclose.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					dispose();
				}
			});
		}
		return jButtonclose;
	}//�˳���


	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				ManageFrame2 thisClass = new ManageFrame2();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
		
	}

	public ManageFrame2() {
		super();
		initialize();//��ʼ��
	}

	
	private void initialize() {
		this.setSize(568, 449);
		this.setContentPane(getJContentPane());
		this.setTitle("���ʹ���ϵͳ");
	}

	private JPanel getJContentPane() {
		
		if (jContentPane == null) {	
			jLsurchmonth = new JLabel();
			jLsurchmonth.setBounds(new Rectangle(140, 135, 163, 22));
			jLsurchmonth.setText("����Ա�����ʣ�");
			jLabsentday = new JLabel();
			jLabsentday.setBounds(new Rectangle(280, 56, 100, 24));
			jLabsentday.setText("�۳�н�ʣ�");
			jLworkday = new JLabel();
			jLworkday.setBounds(new Rectangle(32, 56, 93, 24));
			jLworkday.setText("����");
			jLabelmonth = new JLabel();
			jLabelmonth.setBounds(new Rectangle(280, 14, 51, 22));
			jLabelmonth.setText("�·ݣ�");
			jLabelid = new JLabel();
			jLabelid.setBounds(new Rectangle(32, 14, 75, 22));
			jLabelid.setText("Ա����ţ�");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(jLabelid, null);
			jContentPane.add(getJTextFieldid(), null);
			jContentPane.add(jLabelmonth, null);
			jContentPane.add(jLworkday, null);
			jContentPane.add(getJTfworkday(), null);
			jContentPane.add(jLabsentday, null);
			jContentPane.add(getJTfabsentday(), null);
			jContentPane.add(getJComboBox1(), null);
			jContentPane.add(getJButtonOK(), null);
			jContentPane.add(jLsurchmonth, null);
			jContentPane.add(getJCbsurchmonth(), null);
			jContentPane.add(getJButtonsurch(), null);
			jContentPane.add(getJButtonchangwage(), null);
			jContentPane.add(getJButtonchanginfo(), null);
			jContentPane.add(getJButtonaddworker(), null);
			jContentPane.add(getJButtondelworker(), null);
			jContentPane.add(getJButtonlookall(), null);
			jContentPane.add(getJButtonclose(), null);	
		}
		return jContentPane;
	}
   
	
	public void lookover() {//�鿴����Ա����Ϣ
		final JFrame jf = new JFrame();
		jf.getContentPane().setLayout(new BoxLayout(jf.getContentPane(), BoxLayout.Y_AXIS));
		JButton jbtn = new JButton("�ر�");
		jbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jf.dispose();
			}
		});
		jf.setBounds(100, 100, 700, 500);
		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Container con1 = jf.getContentPane();
		JTable table;

		Object a[][];
		Object name[] = { "Ա�����",  "ְ��","����", "����", "�Ա�", "����" };
		Conn conn = new Conn();
		try {
			Connection con = conn.Getdata();
			Statement sql = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = sql.executeQuery("select e_num,post,name,password,sex,department from employee");
			rs.last();
			int number = rs.getRow();
			a = new Object[number][8];
			rs.beforeFirst();
			int a_num = 0;
			while (rs.next()) {
				String num1 = rs.getString(1);
				String post = rs.getString(2);
				String name1= rs.getString(3);
				String password1 = rs.getString(4);
				String sex1 = rs.getString(5);
				String department = rs.getString(6);
				a[a_num][0] = num1;
				a[a_num][1] = post;
				a[a_num][2] = name1;
				a[a_num][3] = password1;
				a[a_num][4] = sex1;
				a[a_num][5] = department;
				a_num++;
			}
			table = new JTable(a, name);
			con1.add(new JScrollPane(table));
			con1.add(jbtn);
			jf.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void count() {
		String str1, str2, str3, str4;
		str1 = jTextFieldid.getText();
		str2 = String.valueOf(jComboBoxmonth.getSelectedItem());
		str3 = jTfworkday.getText();
		str4 = jTfabsentday.getText();
		Conn conn = new Conn();
		try {
			Connection con = conn.Getdata();
			PreparedStatement  psmt = con.prepareStatement("select post from employee where e_num=?");
			psmt.setString(1, str1);
			ResultSet rs = psmt.executeQuery();
			rs.next();
			String post1 = rs.getString("post");
			psmt = con.prepareStatement("select basic_salary from pos where post like'"+ post1 + "'");
			ResultSet rs1 = psmt.executeQuery();
			rs1.next();
			float bonus1 = Float.parseFloat(str3) ;
			float deduct1 = Float.parseFloat(str4) ;
			float basic_salary1 = rs1.getFloat("basic_salary");
			float fact_salary1 = basic_salary1 + bonus1 - deduct1;
			psmt = con.prepareStatement("insert into sals values(?,?,?,?,?,?)");
			psmt.setString(1, str1);
			psmt.setFloat(2, bonus1);
			psmt.setFloat(3, deduct1);
			psmt.setFloat(4, basic_salary1);
			psmt.setFloat(5, fact_salary1);
			psmt.setString(6, str2);
			JOptionPane.showMessageDialog(this, "�ɹ�����", "��ʾ",JOptionPane.INFORMATION_MESSAGE);
			psmt.executeUpdate();
			psmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "�����ѽ���", "����",JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public void query() {
		final JFrame jf = new JFrame();
		JButton jbtn = new JButton("�ر�");
		jbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jf.dispose();
			}
		});
		jf.setBounds(100, 100, 700, 500);
		jf.getContentPane().setLayout(new BoxLayout(jf.getContentPane(), BoxLayout.Y_AXIS));
		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Container con = jf.getContentPane();
		String str;
		str = String.valueOf(jCbsurchmonth.getSelectedItem());
		Object name1[] = { "Ա�����", "����", "�۳�����", "��������(Ԫ)", "ʵ�ʹ���(Ԫ)", "�·�" };
		Object b[][];
		Conn conn = new Conn();

		try {
			Connection con1 = conn.Getdata();
			Statement sql = con1.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = sql.executeQuery("select e_num,bonus,deduct,s_basicsalary,fact_salary,month"+ " from sals where month like '" + str + "'");
			rs.last();
			int num = rs.getRow();
			b = new Object[num][6];
			int b_num = 0;
			rs.beforeFirst();
			while (rs.next()) {
				String s_num = rs.getString(1);
				String bonus = rs.getString(2);
				String deduct = rs.getString(3);
				String s_basicsalary = rs.getString(4);
				String fact_salary = rs.getString(5);
				String month = rs.getString(6);
				b[b_num][0] = s_num;
				b[b_num][1] = (Object) bonus;
				b[b_num][2] = deduct;
				b[b_num][3] = s_basicsalary;
				b[b_num][4] = fact_salary;
				b[b_num][5] = month;
				b_num++;
			}
			JTable table = new JTable(b, name1);
			con.add(new JScrollPane(table));
			con.add(jbtn);
			jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			jf.setVisible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}  

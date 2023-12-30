import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class JFrame_AdminLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField EmailtextField;
	private JPasswordField passwordField;
	public static String AdminName = "";
	public static String AdminEmail = "";
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame_AdminLogin frame = new JFrame_AdminLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public JFrame_AdminLogin() {
		setTitle("Admin Login");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1440, 900);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel adminPanel = new JPanel();
		adminPanel.setBackground(Color.WHITE);
		adminPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		adminPanel.setBounds(478, 120, 450, 513);
		contentPane.add(adminPanel);
		adminPanel.setLayout(null);
		
		JLabel AdminIconLabel = new JLabel("");
		AdminIconLabel.setIcon(new ImageIcon(JFrame_AdminLogin.class.getResource("/resources_LoginAndRegistration/Icon_Admin.png")));
		AdminIconLabel.setBounds(158, 26, 120, 109);
		adminPanel.add(AdminIconLabel);
		
		JLabel lblHelloAdmin = new JLabel("Admin Login");
		lblHelloAdmin.setHorizontalAlignment(SwingConstants.CENTER);
		lblHelloAdmin.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblHelloAdmin.setBounds(148, 130, 154, 32);
		adminPanel.add(lblHelloAdmin);
		
		JLabel EmailLabel = new JLabel("Email");
		EmailLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		EmailLabel.setBounds(51, 173, 58, 32);
		adminPanel.add(EmailLabel);
		
		EmailtextField = new JTextField();
		EmailtextField.setBounds(51, 200, 351, 32);
		adminPanel.add(EmailtextField);
		EmailtextField.setColumns(10);
		
		JLabel PasswordLabel = new JLabel("Password");
		PasswordLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		PasswordLabel.setBounds(51, 243, 84, 32);
		adminPanel.add(PasswordLabel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(51, 271, 351, 32);
		adminPanel.add(passwordField);
		
		JButton ContinueButton = new JButton("Login");
		ContinueButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            Class.forName("com.mysql.cj.jdbc.Driver");
		            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:/spotter_db", "root", "");
		            Statement stmt = con.createStatement();
		            String sql = "SELECT * FROM admin_accounts WHERE email='" + EmailtextField.getText() + "' and password='" + passwordField.getText().toString() + "'";
		            ResultSet rs = stmt.executeQuery(sql);

		            if (rs.next()) {
		            	String middlename = rs.getString("middlename");
		            	
		                if (rs.getInt("isVerified") == 1) {
		                    JOptionPane.showMessageDialog(null, "Login successful!");
		                    if (middlename != null && !middlename.isEmpty()) {
		                        AdminName = rs.getString("lastname") + ", " + rs.getString("firstname") + " " + middlename.charAt(0) + ".";
		                    }
		                    else {
		                        AdminName = rs.getString("lastname") + ", " + rs.getString("firstname");
		                    }
		                    AdminEmail = rs.getString("email");
		                    JFrame_AdminRequests pop = new JFrame_AdminRequests();
		                    pop.setVisible(true);
		                    pop.setLocationRelativeTo(null);
		                    dispose();
		                } else {
		                    JOptionPane.showMessageDialog(null, "Login failed. Your account is not verified.");
		                    EmailtextField.setText("");
		                    passwordField.setText("");
		                }
		            } else {
		                JOptionPane.showMessageDialog(null, "Login failed. Please check your email or password.");
		                EmailtextField.setText("");
		                passwordField.setText("");
		            }
		            con.close();
		        } catch (Exception ex) {
		            JOptionPane.showMessageDialog(null, "An error occurred. Please try again later.");
		            System.out.print(ex);
		        }
		    }
		});

		ContinueButton.setForeground(new Color(255, 255, 255));
		ContinueButton.setBackground(new Color(0, 204, 0));
		ContinueButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		ContinueButton.setBounds(51, 362, 351, 32);
		adminPanel.add(ContinueButton);
		
		JLabel dhaayLabel = new JLabel("Don't have an account yet?");
		dhaayLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		dhaayLabel.setForeground(Color.LIGHT_GRAY);
		dhaayLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		dhaayLabel.setBounds(81, 453, 186, 32);
		adminPanel.add(dhaayLabel);
		
		JLabel RegisterLabel = new JLabel("Register");
		RegisterLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFrame_AdminRegistration pop = new JFrame_AdminRegistration();
				pop.setVisible(true);
				pop.setLocationRelativeTo(null);
           	 	dispose();
			}
		});
		RegisterLabel.setForeground(Color.RED);
		RegisterLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		RegisterLabel.setBounds(272, 449, 98, 36);
		adminPanel.add(RegisterLabel);
		
		JLabel LineLabel = new JLabel("___________________________________________\r\n");
		LineLabel.setHorizontalAlignment(SwingConstants.CENTER);
		LineLabel.setForeground(Color.LIGHT_GRAY);
		LineLabel.setBounds(69, 417, 312, 14);
		adminPanel.add(LineLabel);
		
		JLabel egLabel = new JLabel("e.g username@kitty");
		egLabel.setBounds(61, 209, 330, 14);
		adminPanel.add(egLabel);
		
		JLabel LibraryIconLabel = new JLabel("");
		LibraryIconLabel.setIcon(new ImageIcon(JFrame_AdminLogin.class.getResource("/resources_LoginAndRegistration/Icon_QCULibrariesLogo.png")));
		LibraryIconLabel.setBounds(10, 11, 90, 77);
		contentPane.add(LibraryIconLabel);
		
		JLabel bookLabel = new JLabel("Book");
		bookLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		bookLabel.setBounds(103, 37, 83, 27);
		contentPane.add(bookLabel);
		
		JLabel lblNewLabel = new JLabel("Login as");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel.setBounds(609, 659, 205, 40);
		contentPane.add(lblNewLabel);
		
		JButton btnStudent = new JButton("Student");
		btnStudent.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					JFrame_StudentLogin frame = new JFrame_StudentLogin();
					frame.setVisible(true);
					dispose();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnStudent.setForeground(new Color(0, 0, 0));
		btnStudent.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnStudent.setBackground(new Color(192, 192, 192));
		btnStudent.setBounds(642, 710, 139, 32);
		contentPane.add(btnStudent);
		
		JButton btnAdmin = new JButton("Admin");
		btnAdmin.setEnabled(false);
		btnAdmin.setForeground(Color.BLACK);
		btnAdmin.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnAdmin.setBackground(Color.LIGHT_GRAY);
		btnAdmin.setBounds(642, 755, 139, 32);
		contentPane.add(btnAdmin);
	
	}
}
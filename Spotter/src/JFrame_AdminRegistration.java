import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.DropMode;
import java.awt.TextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;
import javax.swing.JCheckBox;
import java.awt.SystemColor;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.AbstractListModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Component;
import java.awt.Cursor;
import javax.swing.border.LineBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import java.awt.ComponentOrientation;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JFrame_AdminRegistration extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField middle_textfield;
	private JTextField email_textfield;
	private JButton Register_Button;
	private JTextField lastname_textfield;
	private JTextField firstname_textfield;
	private JTextField password_textfield;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame_AdminRegistration frame = new JFrame_AdminRegistration();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public JFrame_AdminRegistration() {
		setBackground(new Color(255, 255, 255));
		setTitle("Admin Registration");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1440, 900);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setFont(new Font("Arial", Font.BOLD, 20));
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel QCU_LOGO = new JPanel();
		QCU_LOGO.setBackground(new Color(255, 255, 255));
		QCU_LOGO.setBounds(0, 0, 75, 75);
		contentPane.add(QCU_LOGO);
		
		JLabel NavItem_QCULibrariesLogo = new JLabel("");
		QCU_LOGO.add(NavItem_QCULibrariesLogo);
		NavItem_QCULibrariesLogo.setIcon(new ImageIcon(JFrame_StudentRegistration.class.getResource("/resources_NavigationBar/QCULibrariesLogo.png")));
		
		JLabel BOOK = new JLabel("Book");
		BOOK.setFont(new Font("Arial", Font.BOLD, 18));
		BOOK.setBackground(new Color(255, 255, 255));
		BOOK.setBounds(82, 31, 62, 16);
		contentPane.add(BOOK);
		
		JPanel panel = new JPanel();
		panel.setRequestFocusEnabled(false);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(462, 103, 526, 633);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel Student_Icon = new JLabel("");
		Student_Icon.setHorizontalAlignment(SwingConstants.RIGHT);
		Student_Icon.setIcon(new ImageIcon(JFrame_StudentRegistration.class.getResource("/resources_LoginAndRegistration/Icon_Student.png")));
		Student_Icon.setBounds(69, 32, 143, 102);
		panel.add(Student_Icon);
		
		JLabel admin_reg = new JLabel("Admin Registration");
		admin_reg.setFont(new Font("Arial", Font.BOLD, 22));
		admin_reg.setBounds(222, 61, 235, 41);
		panel.add(admin_reg);
		
		JLabel lastname_lbl = new JLabel("Lastname");
		lastname_lbl.setFont(new Font("Dialog", Font.BOLD, 13));
		lastname_lbl.setBounds(50, 166, 224, 16);
		panel.add(lastname_lbl);
		
		lastname_textfield = new JTextField();
		lastname_textfield.setForeground(Color.BLACK);
		lastname_textfield.setFont(new Font("Dialog", Font.PLAIN, 13));
		lastname_textfield.setColumns(10);
		lastname_textfield.setBounds(69, 192, 388, 30);
		panel.add(lastname_textfield);
		
		JLabel firstname_lbl = new JLabel("Firstname");
		firstname_lbl.setFont(new Font("Dialog", Font.BOLD, 13));
		firstname_lbl.setBounds(50, 227, 224, 16);
		panel.add(firstname_lbl);
		
		firstname_textfield = new JTextField();
		firstname_textfield.setToolTipText("awdawdwada");
		firstname_textfield.setForeground(Color.BLACK);
		firstname_textfield.setFont(new Font("Dialog", Font.PLAIN, 13));
		firstname_textfield.setColumns(10);
		firstname_textfield.setBounds(69, 253, 388, 30);
		panel.add(firstname_textfield);
		
		JLabel middle_lbl = new JLabel("Middle Name");
		middle_lbl.setFont(new Font("Dialog", Font.BOLD, 13));
		middle_lbl.setBounds(50, 293, 224, 16);
		panel.add(middle_lbl);
		
		middle_textfield = new JTextField();
		middle_textfield.setFont(new Font("Dialog", Font.PLAIN, 13));
		middle_textfield.setForeground(Color.BLACK);
		middle_textfield.setBounds(69, 319, 388, 30);
		panel.add(middle_textfield);
		middle_textfield.setColumns(10);
		
		JLabel email_lbl = new JLabel("Email");
		email_lbl.setFont(new Font("Dialog", Font.BOLD, 13));
		email_lbl.setBounds(50, 359, 224, 16);
		panel.add(email_lbl);
		
		email_textfield = new JTextField();
		email_textfield.setForeground(Color.BLACK);
		email_textfield.setFont(new Font("Dialog", Font.PLAIN, 13));
		email_textfield.setColumns(10);
		email_textfield.setBounds(69, 385, 388, 30);
		panel.add(email_textfield);
		
		JLabel password_lbl = new JLabel("Password");
		password_lbl.setFont(new Font("Dialog", Font.BOLD, 13));
		password_lbl.setBounds(50, 420, 224, 16);
		panel.add(password_lbl);
		
		password_textfield = new JTextField();
		password_textfield.setForeground(Color.BLACK);
		password_textfield.setFont(new Font("Dialog", Font.PLAIN, 13));
		password_textfield.setColumns(10);
		password_textfield.setBounds(69, 446, 388, 30);
		panel.add(password_textfield);
		
		Register_Button = new JButton("Register");
		Register_Button.setBorder(new EmptyBorder(0, 0, 0, 0));
		Register_Button.setOpaque(true);
		Register_Button.setFocusTraversalPolicyProvider(true);
		Register_Button.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		Register_Button.setBounds(50, 497, 422, 41);
		panel.add(Register_Button);
		Register_Button.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String lastname = lastname_textfield.getText();
		        String firstname = firstname_textfield.getText();
		        String middleinitial = middle_textfield.getText();
		        String email = email_textfield.getText();
		        String password = password_textfield.getText();

		        try {
		            Class.forName("com.mysql.cj.jdbc.Driver");
		            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/spotter_db", "root", "");

		            // Check if there are any existing accounts
		            if (getAccountCount(conn) == 0) {
		                createVerifiedAccount(conn, lastname, firstname, middleinitial, email, password);
		            } else {
		                if (isEmailAlreadyExists(email, conn)) {
		                    JOptionPane.showMessageDialog(null, "Registration failed. Email already exists.");
		                } else {
		                    String query = "INSERT INTO admin_accounts (lastname, firstname, middlename, email, password, isVerified) VALUES (?, ?, ?, ?, ?, 0)";
		                    PreparedStatement preparedStatement = conn.prepareStatement(query);
		                    preparedStatement.setString(1, lastname);
		                    preparedStatement.setString(2, firstname);
		                    preparedStatement.setString(3, middleinitial);
		                    preparedStatement.setString(4, email);
		                    preparedStatement.setString(5, password);
		                    preparedStatement.executeUpdate();
		                    preparedStatement.close();
		                    conn.close();

		                    JOptionPane.showMessageDialog(null, "Your account is being verified. Kindly check your email for updates.");
		                    lastname_textfield.setText("");
		                    firstname_textfield.setText("");
		                    middle_textfield.setText("");
		                    email_textfield.setText("");
		                    password_textfield.setText("");
		                }
		            }
		        } catch (Exception ex) {
		            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
		            ex.printStackTrace();
		        }
		    }

		    private int getAccountCount(Connection conn) throws SQLException {
		        String countQuery = "SELECT COUNT(*) FROM admin_accounts";
		        try (PreparedStatement countStatement = conn.prepareStatement(countQuery);
		             ResultSet resultSet = countStatement.executeQuery()) {
		            if (resultSet.next()) {
		                return resultSet.getInt(1);
		            }
		            return 0;
		        }
		    }

		    private void createVerifiedAccount(Connection conn, String lastname, String firstname, String middleinitial, String email, String password) throws SQLException {
		        String query = "INSERT INTO admin_accounts (lastname, firstname, middlename, email, password, isVerified) VALUES (?, ?, ?, ?, ?, 1)";
		        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
		            preparedStatement.setString(1, lastname);
		            preparedStatement.setString(2, firstname);
		            preparedStatement.setString(3, middleinitial);
		            preparedStatement.setString(4, email);
		            preparedStatement.setString(5, password);
		            preparedStatement.executeUpdate();
		        }
		        conn.close();

		        JOptionPane.showMessageDialog(null, "Registration successful! (Verified account created)");
		        lastname_textfield.setText("");
		        firstname_textfield.setText("");
		        middle_textfield.setText("");
		        email_textfield.setText("");
		        password_textfield.setText("");
		    }
		});

		Register_Button.setFont(new Font("Arial", Font.PLAIN, 13));
		Register_Button.setForeground(Color.WHITE);
		Register_Button.setBackground(new Color(0, 255, 0));
		
		JLabel Donothaveanaccount = new JLabel("Don't have an account yet?");
		Donothaveanaccount.setForeground(Color.DARK_GRAY);
		Donothaveanaccount.setFont(new Font("Arial", Font.PLAIN, 12));
		Donothaveanaccount.setBounds(157, 561, 153, 25);
		panel.add(Donothaveanaccount);
		
		JButton login_lbl = new JButton("Log in");
		login_lbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFrame_AdminLogin pop = new JFrame_AdminLogin();
				pop.setVisible(true);
				pop.setLocationRelativeTo(null);
           	 	dispose();
			}
		});
		login_lbl.setFocusCycleRoot(true);
		login_lbl.setBorder(null);
		login_lbl.setBackground(new Color(255, 255, 255));
		login_lbl.setFont(new Font("Arial", Font.PLAIN, 12));
		login_lbl.setForeground(new Color(0, 255, 0));
		login_lbl.setBounds(302, 559, 51, 29);
		panel.add(login_lbl);
	}
	public Color getRegister_ButtonBackground() {
		return Register_Button.getBackground();
	}
	public void setRegister_ButtonBackground(Color background) {
		Register_Button.setBackground(background);	
	}
	private boolean isEmailAlreadyExists(String email, Connection conn) throws SQLException {
	    String query = "SELECT email FROM admin_accounts WHERE email = ?";
	    PreparedStatement preparedStatement = conn.prepareStatement(query);
	    preparedStatement.setString(1, email);
	    ResultSet rs = preparedStatement.executeQuery();
	    return rs.next();
	}
}
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JFrame_AdminAbout extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static boolean UserAccountDetails = false;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame_AdminAbout frame = new JFrame_AdminAbout();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public JFrame_AdminAbout() throws SQLException {
		setTitle("About");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1440, 900);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel NavBar = new JPanel();
		NavBar.setBackground(new Color(255, 255, 255));
		NavBar.setBounds(0, 0, 1424, 65);
		contentPane.add(NavBar);
		NavBar.setLayout(null);
		
		JLabel NavItem_QCULibrariesLogo = new JLabel("");
		NavItem_QCULibrariesLogo.setIcon(new ImageIcon(JFrame_AdminRequests.class.getResource("/resources_NavigationBar/QCULibrariesLogo.png")));
		NavItem_QCULibrariesLogo.setHorizontalAlignment(SwingConstants.CENTER);
		NavItem_QCULibrariesLogo.setBounds(0, 0, 65, 65);
		NavBar.add(NavItem_QCULibrariesLogo);
		
		JPanel Container_NavItem = new JPanel();
		Container_NavItem.setBackground(new Color(255, 255, 255));
		Container_NavItem.setBounds(286, 0, 852, 65);
		NavBar.add(Container_NavItem);
		Container_NavItem.setLayout(null);
		
		JLabel NavItem_Requests = new JLabel("");
		NavItem_Requests.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					JFrame_AdminRequests frame = new JFrame_AdminRequests();
					frame.setVisible(true);
					dispose();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		NavItem_Requests.setBounds(63, 17, 99, 31);
		Container_NavItem.add(NavItem_Requests);
		NavItem_Requests.setIcon(new ImageIcon(JFrame_AdminAbout.class.getResource("/resources_NavigationBar/RequestsInactive.png")));
		NavItem_Requests.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel NavItem_Reserved = new JLabel("");
		NavItem_Reserved.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					JFrame_AdminReserved frame = new JFrame_AdminReserved();
					frame.setVisible(true);
					dispose();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		NavItem_Reserved.setBounds(225, 17, 101, 31);
		Container_NavItem.add(NavItem_Reserved);
		NavItem_Reserved.setIcon(new ImageIcon(JFrame_AdminRequests.class.getResource("/resources_NavigationBar/ReservedInactive.png")));
		NavItem_Reserved.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel NavItem_Accounts = new JLabel("");
		NavItem_Accounts.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					JFrame_AdminAccounts frame = new JFrame_AdminAccounts();
					frame.setVisible(true);
					dispose();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		NavItem_Accounts.setBounds(542, 17, 103, 31);
		Container_NavItem.add(NavItem_Accounts);
		NavItem_Accounts.setIcon(new ImageIcon(JFrame_AdminRequests.class.getResource("/resources_NavigationBar/AccountsInactive.png")));
		NavItem_Accounts.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel NavItem_About = new JLabel("");
		NavItem_About.setBounds(708, 17, 80, 31);
		Container_NavItem.add(NavItem_About);
		NavItem_About.setIcon(new ImageIcon(JFrame_AdminAbout.class.getResource("/resources_NavigationBar/AboutActive.png")));
		NavItem_About.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel Indicator_ActiveNavItem = new JPanel();
		Indicator_ActiveNavItem.setBackground(new Color(33, 232, 23));
		Indicator_ActiveNavItem.setBounds(708, 59, 80, 5);
		Container_NavItem.add(Indicator_ActiveNavItem);
		
		JLabel NavItem_History = new JLabel("New label");
		NavItem_History.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					JFrame_AdminHistory frame = new JFrame_AdminHistory();
					frame.setVisible(true);
					dispose();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		NavItem_History.setIcon(new ImageIcon(JFrame_AdminRequests.class.getResource("/resources_NavigationBar/HistoryInactive.png")));
		NavItem_History.setBounds(389, 17, 90, 31);
		Container_NavItem.add(NavItem_History);
		
		JLabel NavItem_AdminLogo = new JLabel("");
		NavItem_AdminLogo.setIcon(new ImageIcon(JFrame_AdminRequests.class.getResource("/resources_NavigationBar/AdminProfileIcon.png")));
		NavItem_AdminLogo.setHorizontalAlignment(SwingConstants.CENTER);
		NavItem_AdminLogo.setBounds(1378, 14, 36, 36);
		NavBar.add(NavItem_AdminLogo);
		
		JPanel Container_AdminAbout = new JPanel();
		Container_AdminAbout.setBackground(new Color(222, 222, 222));
		Container_AdminAbout.setBounds(0, 63, 1424, 797);
		contentPane.add(Container_AdminAbout);
		Container_AdminAbout.setLayout(null);
		
		JPanel Container_UserDetails = new JPanel();
		Container_UserDetails.setBackground(Color.WHITE);
		Container_UserDetails.setBounds(1232, 11, 182, 197);
		Container_AdminAbout.add(Container_UserDetails);
		Container_UserDetails.setLayout(null);
		Container_UserDetails.setVisible(false);
		
		JFrame_AdminLogin JFrame_AdminLogin = new JFrame_AdminLogin();
		
		String AdminName = JFrame_AdminLogin.AdminName;
		String AdminEmail = JFrame_AdminLogin.AdminEmail;
		
		JLabel Account_Text = new JLabel("<html>\r\n<h3>" + AdminName + "</h3>\r\n<p>" + AdminEmail + "</p></html>");
		
		Account_Text.setVerticalAlignment(SwingConstants.TOP);
		Account_Text.setForeground(new Color(40, 40, 40));
		Account_Text.setFont(new Font("Arial", Font.PLAIN, 15));
		Account_Text.setBounds(10, 11, 162, 118);
		Container_UserDetails.add(Account_Text);
		
		JPanel Logout_Button = new JPanel();
		Logout_Button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFrame_StudentLogin frame = new JFrame_StudentLogin();
				frame.setVisible(true);
				dispose();
			}
		});
		Logout_Button.setBackground(new Color(220, 20, 60));
		Logout_Button.setBounds(10, 151, 162, 35);
		Container_UserDetails.add(Logout_Button);
		Logout_Button.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Logout");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(10, 0, 142, 35);
		Logout_Button.add(lblNewLabel_3);
		
		JLabel lblNewLabel_1 = new JLabel("a sophisticated solution that empowers students and faculty to effortlessly reserve study spaces,");
		lblNewLabel_1.setForeground(new Color(105, 105, 105));
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1.setBounds(395, 69, 633, 19);
		Container_AdminAbout.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("exemplifying commitment to delivering an organized and conducive learning environment.");
		lblNewLabel_2.setForeground(new Color(105, 105, 105));
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel_2.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_2.setBounds(419, 92, 586, 19);
		Container_AdminAbout.add(lblNewLabel_2);
		
		JLabel lblNewLabel_4 = new JLabel("\"Spotter\"");
		lblNewLabel_4.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_4.setForeground(new Color(255, 0, 0));
		lblNewLabel_4.setBounds(488, 51, 67, 18);
		Container_AdminAbout.add(lblNewLabel_4);
		
		JLabel lblNewLabel = new JLabel(" is Quezon City University's Library Room Booking System,");
		lblNewLabel.setForeground(new Color(105, 105, 105));
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel.setBounds(559, 51, 389, 18);
		Container_AdminAbout.add(lblNewLabel);
		
		JLabel lblNewLabel_6 = new JLabel("Developers");
		lblNewLabel_6.setBackground(new Color(240, 240, 240));
		lblNewLabel_6.setForeground(new Color(69, 69, 69));
		lblNewLabel_6.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_6.setFont(new Font("Arial", Font.BOLD, 30));
		lblNewLabel_6.setBounds(655, 134, 174, 41);
		Container_AdminAbout.add(lblNewLabel_6);
		
		JPanel Container_Cards = new JPanel();
		Container_Cards.setBackground(new Color(222, 222, 222));
		Container_Cards.setLayout(null);
		Container_Cards.setBounds(141, 183, 1202, 571);
		Container_AdminAbout.add(Container_Cards);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setBounds(19, 19, 217, 257);
		Container_Cards.add(lblNewLabel_5);
		lblNewLabel_5.setIcon(new ImageIcon(JFrame_AdminAbout.class.getResource("/resources_About/Card_Rollan.png")));
		
		JLabel lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.setBounds(255, 19, 217, 257);
		Container_Cards.add(lblNewLabel_7);
		lblNewLabel_7.setIcon(new ImageIcon(JFrame_AdminAbout.class.getResource("/resources_About/Card_Obelidor.png")));
		
		JLabel lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.setBounds(491, 19, 217, 257);
		Container_Cards.add(lblNewLabel_8);
		lblNewLabel_8.setIcon(new ImageIcon(JFrame_AdminAbout.class.getResource("/resources_About/Card_Ramos.png")));
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.LEFT);
		
		JLabel lblNewLabel_9 = new JLabel("");
		lblNewLabel_9.setBounds(727, 19, 217, 257);
		Container_Cards.add(lblNewLabel_9);
		lblNewLabel_9.setIcon(new ImageIcon(JFrame_AdminAbout.class.getResource("/resources_About/Card_Molina.png")));
		
		JLabel lblNewLabel_10 = new JLabel("");
		lblNewLabel_10.setBounds(963, 19, 217, 257);
		Container_Cards.add(lblNewLabel_10);
		lblNewLabel_10.setIcon(new ImageIcon(JFrame_AdminAbout.class.getResource("/resources_About/Card_Milloren.png")));
		
		JLabel lblNewLabel_11 = new JLabel("");
		lblNewLabel_11.setBounds(19, 295, 217, 257);
		Container_Cards.add(lblNewLabel_11);
		lblNewLabel_11.setIcon(new ImageIcon(JFrame_AdminAbout.class.getResource("/resources_About/Card_Rualo.png")));
		
		JLabel lblNewLabel_12 = new JLabel("");
		lblNewLabel_12.setBounds(255, 295, 217, 257);
		Container_Cards.add(lblNewLabel_12);
		lblNewLabel_12.setIcon(new ImageIcon(JFrame_AdminAbout.class.getResource("/resources_About/Card_Corpuz.png")));
		
		JLabel lblNewLabel_13 = new JLabel("");
		lblNewLabel_13.setBounds(491, 295, 217, 257);
		Container_Cards.add(lblNewLabel_13);
		lblNewLabel_13.setIcon(new ImageIcon(JFrame_AdminAbout.class.getResource("/resources_About/Card_Masagca.png")));
		
		JLabel lblNewLabel_14 = new JLabel("");
		lblNewLabel_14.setBounds(727, 295, 217, 257);
		Container_Cards.add(lblNewLabel_14);
		lblNewLabel_14.setIcon(new ImageIcon(JFrame_AdminAbout.class.getResource("/resources_About/Card_Hernandez.png")));
		
		JLabel lblNewLabel_15 = new JLabel("");
		lblNewLabel_15.setBounds(963, 295, 217, 257);
		Container_Cards.add(lblNewLabel_15);
		lblNewLabel_15.setIcon(new ImageIcon(JFrame_AdminAbout.class.getResource("/resources_About/Card_Raborar.png")));
		
		NavItem_AdminLogo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(UserAccountDetails == false) {
					Container_UserDetails.setVisible(true);
					UserAccountDetails = true;
				}
				else {
					Container_UserDetails.setVisible(false);
					UserAccountDetails = false;
				}
			}
		});
	}
}

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Cursor;
import java.awt.Dimension;
import java.sql.*;
import javax.swing.border.MatteBorder;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;

public class JFrame_AdminHistory extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static boolean UserAccountDetails = false;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame_AdminHistory frame = new JFrame_AdminHistory();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public JFrame_AdminHistory() throws SQLException {
		setTitle("History");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1440, 900);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel Container_UserDetails = new JPanel();
		Container_UserDetails.setBackground(Color.WHITE);
		Container_UserDetails.setBounds(1232, 80, 182, 197);
		contentPane.add(Container_UserDetails);
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
		
		JPanel NavBar = new JPanel();
		NavBar.setBackground(new Color(255, 255, 255));
		NavBar.setBounds(0, 0, 1424, 65);
		contentPane.add(NavBar);
		NavBar.setLayout(null);
		
		JLabel NavItem_QCULibrariesLogo = new JLabel("");
		NavItem_QCULibrariesLogo.setIcon(new ImageIcon(JFrame_AdminHistory.class.getResource("/resources_NavigationBar/QCULibrariesLogo.png")));
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
				JFrame_AdminRequests frame;
				try {
					frame = new JFrame_AdminRequests();
					frame.setVisible(true);
					dispose();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		NavItem_Requests.setBounds(63, 17, 99, 31);
		Container_NavItem.add(NavItem_Requests);
		NavItem_Requests.setIcon(new ImageIcon(JFrame_AdminHistory.class.getResource("/resources_NavigationBar/RequestsInactive.png")));
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
		NavItem_Reserved.setIcon(new ImageIcon(JFrame_AdminHistory.class.getResource("/resources_NavigationBar/ReservedInactive.png")));
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
		NavItem_Accounts.setIcon(new ImageIcon(JFrame_AdminHistory.class.getResource("/resources_NavigationBar/AccountsInactive.png")));
		NavItem_Accounts.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel NavItem_About = new JLabel("");
		NavItem_About.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					JFrame_AdminAbout frame = new JFrame_AdminAbout();
					frame.setVisible(true);
					dispose();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		NavItem_About.setBounds(708, 17, 80, 31);
		Container_NavItem.add(NavItem_About);
		NavItem_About.setIcon(new ImageIcon(JFrame_AdminHistory.class.getResource("/resources_NavigationBar/AboutInactive.png")));
		NavItem_About.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel Indicator_ActiveNavItem = new JPanel();
		Indicator_ActiveNavItem.setBackground(new Color(33, 232, 23));
		Indicator_ActiveNavItem.setBounds(389, 59, 90, 5);
		Container_NavItem.add(Indicator_ActiveNavItem);
		
		JLabel NavItem_History = new JLabel("New label");
		NavItem_History.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		NavItem_History.setIcon(new ImageIcon(JFrame_AdminHistory.class.getResource("/resources_NavigationBar/HistoryActive.png")));
		NavItem_History.setBounds(389, 17, 90, 31);
		Container_NavItem.add(NavItem_History);
		
		JLabel NavItem_AdminLogo = new JLabel("");
		NavItem_AdminLogo.setIcon(new ImageIcon(JFrame_AdminHistory.class.getResource("/resources_NavigationBar/AdminProfileIcon.png")));
		NavItem_AdminLogo.setHorizontalAlignment(SwingConstants.CENTER);
		NavItem_AdminLogo.setBounds(1378, 14, 36, 36);
		NavBar.add(NavItem_AdminLogo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(-5, 64, 1434, 796);
		contentPane.add(scrollPane);
		
		JPanel Container_AdminHistory = new JPanel();
		scrollPane.setViewportView(Container_AdminHistory);
		Container_AdminHistory.setBackground(new Color(222, 222, 222));
		
		UpdateHistory(Container_AdminHistory, 10);
		Container_AdminHistory.setLayout(new BoxLayout(Container_AdminHistory, BoxLayout.Y_AXIS));
		
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
	
	// Method to create a single tile.
	private static void CreateTile(JPanel Container, ResultSet rs, List<Date> processedDates) throws SQLException {
		if (rs.next()) {
			Date reservationDate = rs.getDate("target_date");
			
			if (!processedDates.contains(reservationDate)) {
	            CreateDateTile(Container, reservationDate);
	            processedDates.add(reservationDate);
	        }
			
			JPanel Tile = new JPanel();
			Tile.setBorder(new MatteBorder(0, 5, 0, 0, (Color) new Color(0, 255, 0)));
			Tile.setPreferredSize(new Dimension(1266, 160));
			Tile.setMaximumSize(new Dimension(1266, 160));
			Tile.setMinimumSize(new Dimension(1266, 160));
			Tile.setLayout(null);
			
			Tile.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseEntered(MouseEvent e) {
	                Tile.setBackground(new Color(230, 230, 230));
	            }

	            @Override
	            public void mouseExited(MouseEvent e) {
	                Tile.setBackground(new Color(240, 240, 240));
	            }
	        });
			String middlename = rs.getString("middlename");
			String name = "";
            if (middlename != null && !middlename.isEmpty()) {
            	name = rs.getString("lastname") + " " + rs.getString("firstname") + ", " + rs.getString("middlename").charAt(0) + ".";
            }
            else {
                name = rs.getString("lastname") + ", " + rs.getString("firstname");
            }
			JLabel ReserverNameLabel = new JLabel(name);
			ReserverNameLabel.setFont(new Font("Arial", Font.BOLD, 15));
			ReserverNameLabel.setBounds(25, 25, 150, 26);
			Tile.add(ReserverNameLabel);
			
			JLabel TimeLabel = new JLabel(" " + rs.getString("time_range"));
			TimeLabel.setIcon(new ImageIcon(JFrame_AdminHistory.class.getResource("/resources_AdminContent/Icon_Clock.png")));
			TimeLabel.setFont(new Font("Arial", Font.PLAIN, 12));
			TimeLabel.setBounds(35, 62, 130, 31);
			Tile.add(TimeLabel);
			
			JLabel RoomLabel = new JLabel(" Room " + rs.getString("room_number"));
			RoomLabel.setIcon(new ImageIcon(JFrame_AdminHistory.class.getResource("/resources_AdminContent/Icon_Door.png")));
			RoomLabel.setFont(new Font("Arial", Font.PLAIN, 12));
			RoomLabel.setBounds(150, 62, 91, 31);
			Tile.add(RoomLabel);
			
			String[] attendees = rs.getString("attendees").split(",");
			
			JLabel ALabel = new JLabel(" " + attendees.length + " Attendees");
			ALabel.setIcon(new ImageIcon(JFrame_AdminHistory.class.getResource("/resources_AdminContent/Icon_People.png")));
			ALabel.setFont(new Font("Arial", Font.PLAIN, 12));
			ALabel.setBounds(250, 62, 180, 31);
			Tile.add(ALabel);
			
			JLabel LineLabel = new JLabel("____________________________________________________________________________________________________________________________________________________________________________________________________");
			LineLabel.setForeground(Color.LIGHT_GRAY);
			LineLabel.setBounds(25, 93, 1230, 14);
			Tile.add(LineLabel);
			
			String status = rs.getString("status");
			JLabel StatusLabel = new JLabel(status);
			StatusLabel.setFont(new Font("Arial", Font.BOLD, 18));
			StatusLabel.setBounds(25, 118, 121, 27);
			if ("Pending".equals(status) || "Scheduled".equals(status)) {
			    StatusLabel.setForeground(Color.ORANGE);
			} else if ("Attended".equals(status)) {
			    StatusLabel.setForeground(new Color(0, 160, 0));
			} else {
			    StatusLabel.setForeground(new Color(160, 0, 0));
			}
			Tile.add(StatusLabel);
			
			Container.add(Box.createRigidArea(new Dimension(0, 15)));
			Container.add(Tile);
		}
	}
	
	// Method to create a date label for tiles.
	private static void CreateDateTile(JPanel Container, Date date) {
	    JPanel DateTile = new JPanel();
		DateTile.setPreferredSize(new Dimension(1266, 60));
		DateTile.setMaximumSize(new Dimension(1266, 60));
		DateTile.setMinimumSize(new Dimension(1266, 60));
		DateTile.setBackground(new Color(222, 222, 222));
		DateTile.setLayout(null);
		
		JLabel DateLabel = new JLabel(DateToWordFormat(date + ""));
		DateLabel.setFont(new Font("Arial", Font.BOLD, 20));
		DateLabel.setBounds(10, 2, 475, 58);
		DateTile.add(DateLabel);
	    
	    Container.add(DateTile);
	}
	
	// Method to populate Container_AdminHistory with tiles and dates.
	private static void UpdateHistory(JPanel Container, int NumberOfTiles) throws SQLException {
		ResultSet rs = GetReservationsData();
		List<Date> processedDates = new ArrayList<>();
		
		for (int i = 0; i < NumberOfTiles; i++) {
			CreateTile(Container, rs, processedDates);
	    }
    }
	
	// Method to retrieve reservation data with corresponding requester information
    private static ResultSet GetReservationsData() throws SQLException {
        String jdbcURL = "jdbc:mysql://localhost:3306/spotter_db";
        String username = "root";
        String password = "";
        
        Connection connection = DriverManager.getConnection(jdbcURL, username, password);
        Statement statement = connection.createStatement();
        
        String query = "SELECT r.*, sa.*" +
                "FROM reservation r " +
                "JOIN student_accounts sa ON r.requester_id = sa.account_id";
        
        ResultSet resultSet = statement.executeQuery(query);

        return resultSet;
    }
    
    // Method to convert Date format
    public static String DateToWordFormat(String dateString) {
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat outputFormat = new SimpleDateFormat("MMMM d, yyyy");

            Date date = inputFormat.parse(dateString);
            return outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
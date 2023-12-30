import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class JFrame_StudentHome extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel Calendar_Header;
	private static int globalMonth = 0;
	private static int globalDay = 0;
	private static int globalYear = 0;
	private static JPanel selectedCell = null;
	private static JPanel Calendar_Days;
	private static boolean UserAccountDetails = false;
	public static String DateDBFormat;
	public static String DateWordFormat;
	private static JFrame_StudentHome frame = null;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new JFrame_StudentHome();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private static void setFrame() throws SQLException {
		frame = new JFrame_StudentHome();
	}
	
	public JFrame_StudentHome() throws SQLException {
		setTitle("Home");
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
		Container_NavItem.setBounds(580, 0, 264, 65);
		NavBar.add(Container_NavItem);
		Container_NavItem.setLayout(null);
		
		JLabel NavItem_About = new JLabel("");
		NavItem_About.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFrame JFrame_StudentAbout = new JFrame_StudentAbout();
				JFrame_StudentAbout.setVisible(true);
				dispose();
			}
		});
		NavItem_About.setBounds(162, 17, 80, 31);
		Container_NavItem.add(NavItem_About);
		NavItem_About.setIcon(new ImageIcon(JFrame_AdminRequests.class.getResource("/resources_NavigationBar/AboutInactive.png")));
		NavItem_About.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel Indicator_ActiveNavItem = new JPanel();
		Indicator_ActiveNavItem.setBackground(new Color(33, 232, 23));
		Indicator_ActiveNavItem.setBounds(22, 59, 80, 5);
		Container_NavItem.add(Indicator_ActiveNavItem);
		
		JLabel NavItem_Home = new JLabel("New label");
		NavItem_Home.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		NavItem_Home.setIcon(new ImageIcon(JFrame_StudentHome.class.getResource("/resources_NavigationBar/HomeActive.png")));
		NavItem_Home.setBounds(22, 17, 80, 31);
		Container_NavItem.add(NavItem_Home);
		
		JLabel NavItem_StudentLogo = new JLabel("");
		NavItem_StudentLogo.setIcon(new ImageIcon(JFrame_StudentHome.class.getResource("/resources_NavigationBar/StudentProfileIcon.png")));
		NavItem_StudentLogo.setHorizontalAlignment(SwingConstants.CENTER);
		NavItem_StudentLogo.setBounds(1378, 14, 36, 36);
		NavBar.add(NavItem_StudentLogo);
		
		JPanel Container_StudentHome = new JPanel();
		Container_StudentHome.setBackground(new Color(222, 222, 222));
		Container_StudentHome.setBounds(0, 63, 1424, 797);
		contentPane.add(Container_StudentHome);
		Container_StudentHome.setLayout(null);
		
		JPanel Container_UserDetails = new JPanel();
		Container_UserDetails.setBackground(Color.WHITE);
		Container_UserDetails.setBounds(1232, 11, 182, 197);
		Container_StudentHome.add(Container_UserDetails);
		Container_UserDetails.setLayout(null);
		Container_UserDetails.setVisible(false);
		
		JFrame_StudentLogin JFrame_StudentLogin = new JFrame_StudentLogin();
		
		String StudentName = JFrame_StudentLogin.StudentName;
		String StudentEmail = JFrame_StudentLogin.StudentEmail;
		
		JLabel Account_Text = new JLabel("<html>\r\n<h3>" + StudentName + "</h3>\r\n<p>" + StudentEmail + "</p></html>");
		
		Account_Text.setVerticalAlignment(SwingConstants.TOP);
		Account_Text.setForeground(new Color(40, 40, 40));
		Account_Text.setFont(new Font("Arial", Font.PLAIN, 15));
		Account_Text.setBounds(10, 11, 162, 118);
		Container_UserDetails.add(Account_Text);
		
		JPanel Logout_Button = new JPanel();
		Logout_Button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFrame_StudentLogin fr = new JFrame_StudentLogin();
				fr.setVisible(true);
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
		
		JPanel Calendar_Container = new JPanel();
		Calendar_Container.setBackground(new Color(243, 244, 246));
		Calendar_Container.setBounds(26, 43, 842, 711);
		Container_StudentHome.add(Calendar_Container);
		Calendar_Container.setLayout(null);
		
		JPanel Calendar_Body = new JPanel();
		Calendar_Body.setBackground(new Color(255, 255, 255));
		Calendar_Body.setBounds(25, 76, 792, 610);
		Calendar_Container.add(Calendar_Body);
		Calendar_Body.setLayout(null);
		
		JPanel Calendar_Heading = new JPanel();
		Calendar_Heading.setBounds(0, 0, 792, 65);
		Calendar_Body.add(Calendar_Heading);
		Calendar_Heading.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel Header_Sunday = new JLabel("Sun");
		Header_Sunday.setHorizontalAlignment(SwingConstants.CENTER);
		Header_Sunday.setFont(new Font("Arial", Font.BOLD, 16));
		Calendar_Heading.add(Header_Sunday);
		
		JLabel Header_Monday = new JLabel("Mon");
		Header_Monday.setHorizontalAlignment(SwingConstants.CENTER);
		Header_Monday.setFont(new Font("Arial", Font.BOLD, 16));
		Calendar_Heading.add(Header_Monday);
		
		JLabel Header_Tuesday = new JLabel("Tue");
		Header_Tuesday.setHorizontalAlignment(SwingConstants.CENTER);
		Header_Tuesday.setFont(new Font("Arial", Font.BOLD, 16));
		Calendar_Heading.add(Header_Tuesday);
		
		JLabel Header_Wednesday = new JLabel("Wed");
		Header_Wednesday.setHorizontalAlignment(SwingConstants.CENTER);
		Header_Wednesday.setFont(new Font("Arial", Font.BOLD, 16));
		Calendar_Heading.add(Header_Wednesday);
		
		JLabel Header_Thursday = new JLabel("Thu");
		Header_Thursday.setHorizontalAlignment(SwingConstants.CENTER);
		Header_Thursday.setFont(new Font("Arial", Font.BOLD, 16));
		Calendar_Heading.add(Header_Thursday);
		
		JLabel Header_Friday = new JLabel("Fri");
		Header_Friday.setHorizontalAlignment(SwingConstants.CENTER);
		Header_Friday.setFont(new Font("Arial", Font.BOLD, 16));
		Calendar_Heading.add(Header_Friday);
		
		JLabel Header_Saturday = new JLabel("Sat");
		Header_Saturday.setHorizontalAlignment(SwingConstants.CENTER);
		Header_Saturday.setFont(new Font("Arial", Font.BOLD, 16));
		Calendar_Heading.add(Header_Saturday);
		
		Calendar_Days = new JPanel();
		Calendar_Days.setBackground(new Color(255, 255, 255));
		Calendar_Days.setBounds(0, 67, 792, 543);
		Calendar_Body.add(Calendar_Days);
		Calendar_Days.setLayout(new GridLayout(0, 7, 5, 0));
		
		Calendar_Header = new JLabel("November 2000");
		Calendar_Header.setFont(new Font("Arial", Font.BOLD, 25));
		Calendar_Header.setBounds(25, 0, 617, 78);
		Calendar_Container.add(Calendar_Header);
		
		JLabel PreviousMonth = new JLabel("");
		PreviousMonth.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		PreviousMonth.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					UpdateCalendar(Calendar_Body, Calendar_Header, false);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		PreviousMonth.setIcon(new ImageIcon(JFrame_AdminRequests.class.getResource("/resources_AdminContent/LeftArrowDisabled.png")));
		PreviousMonth.setHorizontalAlignment(SwingConstants.CENTER);
		PreviousMonth.setBounds(767, 31, 20, 20);
		Calendar_Container.add(PreviousMonth);
		
		JLabel NextMonth = new JLabel("");
		NextMonth.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		NextMonth.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					UpdateCalendar(Calendar_Body, Calendar_Header, true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		NextMonth.setIcon(new ImageIcon(JFrame_AdminRequests.class.getResource("/resources_AdminContent/RightArrowDisabled.png")));
		NextMonth.setHorizontalAlignment(SwingConstants.CENTER);
		NextMonth.setBounds(797, 31, 20, 20);
		Calendar_Container.add(NextMonth);
		
	    InitializeCalendarAndTiles(Calendar_Body, Calendar_Header);
	    
	    JLabel lblNewLabel = new JLabel("<html>\r\n  <h2>Meeting Room Reservation Steps</h2>\r\n  <ol>\r\n    <li>Pick your desired date.</li>\r\n    <li>Answer the reservation form.</li>\r\n    <li>Wait for the confirmation email that will be sent to your registered email account.</li>\r\n    <li>Reservation done! You and other attendees can now use the meeting room at your requested date and time.</li>\r\n  </ol>\r\n  \r\n  <h2>Meeting Room Guidelines</h2>\r\n    <ul>\r\n      <li>Unattended belongings will be removed by the library staff and turned over to the lost and found section.</li>\r\n      <li>No eating is allowed.</li>\r\n      <li>Please treat the room and its equipment with care and respect.</li>\r\n      <li>Ensure that the room is left in the same condition as you found it.</li>\r\n    </ul>\r\n</html>");
	    lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 18));
	    lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
	    lblNewLabel.setBounds(898, 43, 498, 711);
	    Container_StudentHome.add(lblNewLabel);
	    
	    JLabel lblNewLabel_1 = new JLabel("");
	    lblNewLabel_1.setIcon(new ImageIcon(JFrame_StudentHome.class.getResource("/resources_AdminContent/CalendarLegend.png")));
	    lblNewLabel_1.setBounds(33, 0, 302, 44);
	    Container_StudentHome.add(lblNewLabel_1);
	    
	    NavItem_StudentLogo.addMouseListener(new MouseAdapter() {
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
	
	public static int[][] getDaysWithReserved(int year, int month) throws SQLException {
	    String jdbcURL = "jdbc:mysql://localhost:3306/spotter_db";
	    String username = "root";
	    String password = "";

	    List<int[]> dayList = new ArrayList<>();

	    try (Connection connection = DriverManager.getConnection(jdbcURL, username, password)) {
	    	String sql = "SELECT DAY(target_date), COUNT(*) FROM reservation WHERE YEAR(target_date) = ? " +
                    "AND MONTH(target_date) = ? AND (status = 'Pending' OR status = 'Scheduled') GROUP BY DAY(target_date)";

	        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	            preparedStatement.setInt(1, year);
	            preparedStatement.setInt(2, month);
	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                while (resultSet.next()) {
	                    int day = resultSet.getInt(1);
	                    int count = resultSet.getInt(2);
	                    dayList.add(new int[]{day, count});
	                }
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw e;
	    }

	    int[][] dayArray = new int[dayList.size()][2];
	    for (int i = 0; i < dayList.size(); i++) {
	        dayArray[i] = dayList.get(i);
	    }

	    return dayArray;
	}

	
	public static void DrawCalendarDays(int year, int month, int initDay) throws SQLException {
		int[][] DaysRequests = getDaysWithReserved(year, month);

	    Component[] components = Calendar_Days.getComponents();
	    for (Component component : components) {
	        Calendar_Days.remove(component);
	    }
	    Calendar_Days.revalidate();
	    Calendar_Days.repaint();

	    Calendar currentCalendar = Calendar.getInstance();
	    int currentYear = currentCalendar.get(Calendar.YEAR);
	    int currentMonth = currentCalendar.get(Calendar.MONTH);
	    int currentDay = currentCalendar.get(Calendar.DAY_OF_MONTH);

	    Calendar calendar = new GregorianCalendar(year, month - 1, 1);
	    int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	    int startDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

	    int offset = (startDayOfWeek + 7 - Calendar.SUNDAY) % 7;

	    for (int i = 0; i < offset; i++) {
	        JPanel emptyCell = new JPanel();
	        emptyCell.setBackground(new Color(255, 255, 255));
	        Calendar_Days.add(emptyCell);
	    }

	    for (int day = 1; day <= maxDay; day++) {
	        JPanel cell = new JPanel();
	        if (day == initDay) {
	            cell.setBackground(new Color(210, 210, 210));
	            selectedCell = cell;
	        } else {
	            cell.setBackground(new Color(255, 255, 255));
	        }
	        cell.setBorder(null);
	        cell.setLayout(new BorderLayout());

	        JLabel dateLabel = new JLabel(Integer.toString(day));
	        dateLabel.setHorizontalAlignment(SwingConstants.CENTER);
	        dateLabel.setFont(new Font("Arial", Font.BOLD, 24));

	        if (year < currentYear || (year == currentYear && month - 1 < currentMonth) || (year == currentYear && month - 1 == currentMonth && day < currentDay)) {
	            dateLabel.setForeground(new Color(200, 200, 200));
	        } else {
	            for (int[] dayRequest : DaysRequests) {
	                if (day == dayRequest[0]) {
	                    if (dayRequest[1] >= 9) {
	                        dateLabel.setForeground(Color.RED);
	                    } else if (dayRequest[1] < 9) {
	                        dateLabel.setForeground(new Color(0, 180, 0));
	                    }
	                    break;
	                }
	            }

	            cell.addMouseListener(new MouseAdapter() {
	                @Override
	                public void mouseClicked(MouseEvent e) {
	                    if (selectedCell != null) {
	                        selectedCell.setBackground(new Color(255, 255, 255));
	                    }
	                    JPanel clickedCell = (JPanel) e.getSource();
	                    selectedCell = clickedCell;
	                    cell.setBackground(new Color(210, 210, 210));

	                    Component[] components = clickedCell.getComponents();
	                    for (Component component : components) {
	                        if (component instanceof JLabel) {
	                            JLabel clickedLabel = (JLabel) component;
	                            int day = Integer.parseInt(clickedLabel.getText());
	                            try {
	                                if (dateLabel.getForeground() != Color.RED) {
	                                    handleDateClick(month, day, year);
	                                }
	                            } catch (SQLException e1) {
	                                e1.printStackTrace();
	                            }
	                        }
	                    }
	                }

	                @Override
	                public void mouseEntered(MouseEvent e) {
	                    if (cell != selectedCell) {
	                        if (dateLabel.getForeground() != Color.RED) {
	                            cell.setBackground(new Color(240, 240, 240));
	                        }
	                    }
	                }

	                @Override
	                public void mouseExited(MouseEvent e) {
	                    if (cell != selectedCell) {
	                        if (dateLabel.getForeground() != Color.RED) {
	                            cell.setBackground(new Color(255, 255, 255));
	                        }
	                    }
	                }
	            });
	        }

	        cell.add(dateLabel, BorderLayout.CENTER);
	        Calendar_Days.add(cell);
	    }
	}

	public static void handleDateClick(int month, int day, int year) throws SQLException {
		globalDay = day;
		globalYear = year;
        globalMonth = month;
        
        DateDBFormat = year + "-" + month + "-" + day;
        DateWordFormat = DateToWordFormat(year + "-" + month + "-" + day);
        
        setFrame();
        
        try {
			JFrame_StudentReservationForm fr = new JFrame_StudentReservationForm();
			fr.setVisible(true);
			frame.dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void InitializeCalendarAndTiles(JPanel Calendar_Body, JLabel Header_CalendarMonthYear) throws SQLException {
		Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH) + 1;
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        
        globalYear = currentYear;
        globalMonth = currentMonth;
        
        Header_CalendarMonthYear.setText(IntegerToMonth(currentMonth) + " " + currentYear);
        DrawCalendarDays(currentYear, currentMonth, currentDay);
	}
	
	public static void UpdateCalendar(JPanel Calendar_Body, JLabel Header_CalendarMonthYear, boolean direction) throws SQLException {
	    if (direction) {
	        if (globalMonth == 12) {
	            globalYear++;
	            globalMonth = 1;
	        } else {
	            globalMonth++;
	        }
	    } else {
	        if (globalMonth == 1) {
	            globalYear--;
	            globalMonth = 12;
	        } else {
	            globalMonth--;
	        }
	    }

	    DrawCalendarDays(globalYear, globalMonth, 0);
	    Header_CalendarMonthYear.setText(IntegerToMonth(globalMonth) + " " + globalYear);
	}
	
	public static String IntegerToMonth(int month) {
	    if (month < 1 || month > 12) {
	        return "Invalid Month";
	    }

	    String[] months = {
	        "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"
	    };
	    
	    return months[month - 1];
	}
}

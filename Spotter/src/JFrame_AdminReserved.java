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
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class JFrame_AdminReserved extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel Header_CalendarMonthYear;
	private static int globalMonth = 0;
	private static int globalDay = 0;
	private static int globalYear = 0;
	private static JPanel Tiles_AdminReserved;
	private static JPanel selectedCell = null;
	private static JPanel Calendar_Body;
	private static String targetDate = "";
	private static boolean UserAccountDetails = false;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame_AdminReserved frame = new JFrame_AdminReserved();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void MarkAsExpired() throws SQLException {
	    Calendar calendar = Calendar.getInstance();
	    calendar.add(Calendar.DAY_OF_MONTH, -1);
	    Date yesterdayDate = calendar.getTime();

	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    String yesterdayDateString = sdf.format(yesterdayDate);

	    String jdbcURL = "jdbc:mysql://localhost:3306/spotter_db";
	    String username = "root";
	    String password = "";

	    Connection connection = DriverManager.getConnection(jdbcURL, username, password);
	    Statement statement = connection.createStatement();

	    String query = "UPDATE Reservation SET status = 'Expired' " +
	                   "WHERE target_date < '" + yesterdayDateString + "' AND status = 'Pending'";
	    statement.executeUpdate(query);
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
	
	public static void UpdateTiles(int month, int day, int year) throws SQLException {
	    Tiles_AdminReserved.removeAll();
	    Tiles_AdminReserved.revalidate();
	    Tiles_AdminReserved.repaint();

	    String jdbcURL = "jdbc:mysql://localhost:3306/spotter_db";
	    String username = "root";
	    String password = "";

	    Connection connection = DriverManager.getConnection(jdbcURL, username, password);
	    Statement statement = connection.createStatement();

	    String query = 
	        "SELECT " +
	        "    r.reservation_id, " +
	        "    r.room_number, " +
	        "    r.target_date, " +
	        "    r.time_range, " +
	        "    r.attendees, " +
	        "    r.status, " +
	        "    sa.lastname, " +
	        "    sa.firstname, " +
	        "    sa.middlename " +
	        "FROM Reservation r " +
	        "INNER JOIN Student_Accounts sa ON r.requester_id = sa.account_id " +
	        "WHERE r.target_date = '" + year + "-" + month + "-" + day + "' " +
	        "AND r.status = 'Scheduled';";

	    ResultSet rs = statement.executeQuery(query);
	    boolean dataFound = false;

	    while (rs.next()) {
	        dataFound = true;
	        String reservation_id = rs.getString("reservation_id");
	        String requester = "";
	        
	        String middlename = rs.getString("middlename");
            if (middlename != null && !middlename.isEmpty()) {
            	requester = rs.getString("lastname") + " " + rs.getString("firstname") + ", " + rs.getString("middlename").charAt(0) + ".";
            }
            else {
                requester = rs.getString("lastname") + ", " + rs.getString("firstname");
            }
	        
	        String time = rs.getString("time_range");
	        String room = rs.getString("room_number");
	        String[] attendees = rs.getString("attendees").split(", ");
	        
	        addRequestTile(reservation_id, requester, time, room, attendees);
	    }

	    rs.close();
	    statement.close();
	    connection.close();
	    
	    JPanel Container_noDataFound = new JPanel();
		Container_noDataFound.setBounds(281, 0, 461, 78);
		Container_noDataFound.setLayout(null);
		
		JLabel Label_noDataFound = new JLabel("<html><p>No scheduled requests found for this day.</p></html>");
		Label_noDataFound.setHorizontalAlignment(SwingConstants.CENTER);
		Label_noDataFound.setFont(new Font("Arial", Font.PLAIN, 20));
		Label_noDataFound.setBounds(57, 22, 347, 100);
		Container_noDataFound.add(Label_noDataFound);
	    
	    if (!dataFound) {
	    	Tiles_AdminReserved.removeAll();
	        Tiles_AdminReserved.add(Container_noDataFound);
	        Tiles_AdminReserved.revalidate();
	        Tiles_AdminReserved.repaint();
	    }
	}

	
	public static void addRequestTile(String reservation_id, String requester, String time, String room, String[] attendees) {
		JPanel Tile = new JPanel();
		Tile.setBackground(new Color(255, 255, 255));
		Tile.setPreferredSize(new Dimension(455, 171));
		Tile.setMaximumSize(new Dimension(455, 171));
		Tile.setMinimumSize(new Dimension(455, 171));
		Tile.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		    	try {
					showReservationDetails(reservation_id);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		    }
		    
		    @Override
            public void mouseEntered(MouseEvent e) {
		    	Tile.setBackground(new Color(230, 230, 230));
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	Tile.setBackground(new Color(255, 255, 255));
            }
		});
		Tiles_AdminReserved.add(Tile);
		Tile.setLayout(null);
		
		JLabel Heading_Requester = new JLabel(requester);
		Heading_Requester.setForeground(Color.DARK_GRAY);
		Heading_Requester.setFont(new Font("Arial", Font.BOLD, 18));
		Heading_Requester.setBounds(20, 11, 297, 24);
		Tile.add(Heading_Requester);
		
		JLabel Label_Time = new JLabel(" " + time);
		Label_Time.setIcon(new ImageIcon(JFrame_AdminRequests.class.getResource("/resources_AdminContent/Icon_Clock.png")));
		Label_Time.setForeground(new Color(160, 160, 160));
		Label_Time.setFont(new Font("Arial", Font.PLAIN, 14));
		Label_Time.setBounds(20, 46, 175, 20);
		Tile.add(Label_Time);
		
		JLabel Label_Room = new JLabel(" Room " + room);
		Label_Room.setIcon(new ImageIcon(JFrame_AdminRequests.class.getResource("/resources_AdminContent/Icon_Door.png")));
		Label_Room.setForeground(new Color(160, 160, 160));
		Label_Room.setFont(new Font("Arial", Font.PLAIN, 14));
		Label_Room.setBounds(205, 46, 112, 20);
		Tile.add(Label_Room);
		
		JLabel TIle_Line = new JLabel("__________________________________________________");
		TIle_Line.setForeground(UIManager.getColor("Button.shadow"));
		TIle_Line.setFont(new Font("Arial", Font.PLAIN, 14));
		TIle_Line.setBounds(20, 94, 425, 20);
		Tile.add(TIle_Line);
		
		JLabel Button_Attendees = new JLabel(" " + attendees.length + " Attendees");
		Button_Attendees.setIcon(new ImageIcon(JFrame_AdminRequests.class.getResource("/resources_AdminContent/Icon_People.png")));
		Button_Attendees.setForeground(new Color(160, 160, 160));
		Button_Attendees.setFont(new Font("Arial", Font.PLAIN, 14));
		Button_Attendees.setBounds(20, 77, 231, 20);
		Tile.add(Button_Attendees);
		
		JPanel ApproveButton = new JPanel();
		ApproveButton.setBounds(20, 125, 108, 32);
		ApproveButton.setBackground(new Color(34, 187, 85));
		ApproveButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		ApproveButton.setLayout(new BorderLayout(0, 0));
		
		JLabel Label_ApproveButton = new JLabel("Attended");
		Label_ApproveButton.setForeground(new Color(222, 222, 222));
		ApproveButton.add(Label_ApproveButton);
		Label_ApproveButton.setHorizontalAlignment(SwingConstants.CENTER);
		Label_ApproveButton.setFont(new Font("Arial", Font.PLAIN, 15));
		
		JPanel DeclineButton = new JPanel();
		DeclineButton.setBackground(new Color(222, 60, 60));
		DeclineButton.setBounds(140, 125, 108, 32);
		DeclineButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		DeclineButton.setLayout(new BorderLayout(0, 0));
		
		JLabel Label_DeclineButton = new JLabel("Unattended");
		Label_DeclineButton.setHorizontalAlignment(SwingConstants.CENTER);
		Label_DeclineButton.setForeground(new Color(222, 222, 222));
		Label_DeclineButton.setFont(new Font("Arial", Font.PLAIN, 15));
		DeclineButton.add(Label_DeclineButton);
		
		JPanel Design_Tile = new JPanel();
		Design_Tile.setBounds(0, 0, 10, 171);
		Tile.add(Design_Tile);
		Design_Tile.setBackground(new Color(51, 51, 51));
		
		DeclineButton.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		    	try {
		    		OnDeclineClick(reservation_id);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		    }
		});
		
		ApproveButton.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        try {
					OnApproveClick(reservation_id);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		    }
		});
		
		Tile.add(ApproveButton);
		Tile.add(DeclineButton);
		
		Tiles_AdminReserved.add(Box.createRigidArea(new Dimension(0, 15)));
	    Tiles_AdminReserved.add(Tile);
	    Tiles_AdminReserved.revalidate();
	}
	
	public static void showReservationDetails(String reservation_id) throws SQLException {
        String jdbcURL = "jdbc:mysql://localhost:3306/spotter_db";
        String username = "root";
        String password = "";

        Connection connection = DriverManager.getConnection(jdbcURL, username, password);
        Statement statement = connection.createStatement();

        String query =
            "SELECT " +
            "    r.room_number, " +
            "    r.target_date, " +
            "    r.time_range, " +
            "    r.attendees, " +
            "    r.status, " +
            "    sa.lastname, " +
            "    sa.firstname, " +
            "    sa.middlename, " +
            "    sa.student_number, " +
            "    sa.program, " +
            "    sa.email " +
            "FROM Reservation r " +
            "INNER JOIN Student_Accounts sa ON r.requester_id = sa.account_id " +
            "WHERE r.reservation_id = " + reservation_id + ";";

        ResultSet rs = statement.executeQuery(query);

        String target_date = "";
        String time_range = "";
        String status = "";
        String name = "";
        String student_number = "";
        String program = "";
        String email = "";
        String attendeesStr = "";

        if (rs.next()) {
            target_date = rs.getString("target_date");
            time_range = rs.getString("time_range");
            status = rs.getString("status");
            String middlename = rs.getString("middlename");
            if (middlename != null && !middlename.isEmpty()) {
            	name = rs.getString("lastname") + " " + rs.getString("firstname") + ", " + rs.getString("middlename").charAt(0) + ".";
            }
            else {
                name = rs.getString("lastname") + ", " + rs.getString("firstname");
            }
            student_number = rs.getString("student_number");
            program = rs.getString("program");
            email = rs.getString("email");
            attendeesStr = rs.getString("attendees");
        }

        String[] attendees = attendeesStr.split(", ");

        rs.close();
        statement.close();
        connection.close();

        JDialog dialog = new JDialog();
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setModalityType(JDialog.ModalityType.APPLICATION_MODAL); // Make it modal
        dialog.setSize(1024, 720);
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(null);

        JPanel contentPane = new JPanel();
        dialog.setContentPane(contentPane);
        contentPane.setLayout(null);

        String reservationDetailsHTML = "<html>"
            + "<h2>Reservation Details</h2>"
            + "<ul>"
            + "<li>ID: <strong>" + reservation_id + "</strong></li>"
            + "<li>Date: <strong>" + DateToWordFormat(target_date) + "</strong></li>"
            + "<li>Time: <strong>" + time_range + "</strong></li>"
            + "<li>Status: <strong>" + status + "</strong></li>"
            + "</ul>"
            + "<br>"
            + "<h2>Requester Information</h2>"
            + "<ul>"
            + "<li>Name: <strong>" + name + "</strong></li>"
            + "<li>Student Number: <strong>" + student_number + "</strong></li>"
            + "<li>Program: <strong>" + program + "</strong></li>"
            + "<li>Email: <strong>" + email + "</strong></li>"
            + "</ul>"
            + "<br>"
            + "<h2>Attendees</h2>"
            + "<ol>";

        for (String tao : attendees) {
            reservationDetailsHTML += "<li>" + tao + "</li>";
        }

        reservationDetailsHTML += "</ol></html";

        JLabel lblNewLabel_1 = new JLabel(reservationDetailsHTML);
        lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 16));
        lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
        lblNewLabel_1.setBounds(16, 36, 976, 607);
        contentPane.add(lblNewLabel_1);

        dialog.setVisible(true);
    }
	
	public static void OnDeclineClick(String reservation_id) throws SQLException {
	    String jdbcURL = "jdbc:mysql://localhost:3306/spotter_db";
	    String username = "root";
	    String password = "";

	    Connection connection = DriverManager.getConnection(jdbcURL, username, password);
	    Statement statement = connection.createStatement();

	    String query = "UPDATE Reservation SET status = 'Unattended' WHERE reservation_id = " + reservation_id;
	    statement.executeUpdate(query);

	    JDialog dialog = new JDialog();
	    dialog.setModal(true);  // Set the dialog to be modal
	    dialog.setResizable(false);
	    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);  // You can use DISPOSE_ON_CLOSE to close the dialog
	    dialog.setSize(1024, 720);
	    dialog.setLocationRelativeTo(null);
	    dialog.setUndecorated(true);

	    JPanel contentPane = new JPanel();
	    contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
	    dialog.setContentPane(contentPane);
	    contentPane.setLayout(null);

	    JPanel Wrapper = new JPanel();
	    Wrapper.setBounds(275, 236, 473, 248);
	    contentPane.add(Wrapper);
	    Wrapper.setLayout(null);

	    JPanel Button_OK = new JPanel();
	    Button_OK.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	    Button_OK.setLayout(null);
	    Button_OK.setBackground(new Color(0, 128, 0));
	    Button_OK.setBounds(28, 156, 423, 53);
	    Button_OK.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseClicked(MouseEvent e) {
	            try {
	                DrawCalendarDays(Calendar_Body, globalYear, globalMonth, globalDay);
	                UpdateTiles(globalMonth, globalDay, globalYear);
	            } catch (SQLException e1) {
	                e1.printStackTrace();
	            }

	            dialog.dispose();
	        }
	    });
	    Wrapper.add(Button_OK);

	    JLabel lblOk = new JLabel("OK");
	    lblOk.setHorizontalAlignment(SwingConstants.CENTER);
	    lblOk.setForeground(Color.WHITE);
	    lblOk.setFont(new Font("Arial", Font.PLAIN, 20));
	    lblOk.setBounds(98, 11, 227, 31);
	    Button_OK.add(lblOk);

	    JLabel SubText_Decline = new JLabel("The reservation was marked as 'Unattended'.");
	    SubText_Decline.setHorizontalAlignment(SwingConstants.CENTER);
	    SubText_Decline.setFont(new Font("Arial", Font.PLAIN, 16));
	    SubText_Decline.setBounds(28, 68, 423, 59);
	    Wrapper.add(SubText_Decline);

	    JLabel Heading_Declined = new JLabel("Unattended");
	    Heading_Declined.setHorizontalAlignment(SwingConstants.CENTER);
	    Heading_Declined.setFont(new Font("Arial", Font.BOLD, 16));
	    Heading_Declined.setForeground(Color.RED);
	    Heading_Declined.setBounds(28, 11, 423, 59);
	    Wrapper.add(Heading_Declined);

	    dialog.setVisible(true);
	}


	public static void OnApproveClick(String reservation_id) throws SQLException {
	    String jdbcURL = "jdbc:mysql://localhost:3306/spotter_db";
	    String username = "root";
	    String password = "";

	    Connection connection = DriverManager.getConnection(jdbcURL, username, password);
	    Statement statement = connection.createStatement();

	    String query = "UPDATE Reservation SET status = 'Attended' WHERE reservation_id = " + reservation_id;
	    statement.executeUpdate(query);

	    JDialog dialog = new JDialog();
	    dialog.setModal(true);  // Set the dialog to be modal
	    dialog.setResizable(false);
	    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);  // You can use DISPOSE_ON_CLOSE to close the dialog
	    dialog.setSize(1024, 720);
	    dialog.setLocationRelativeTo(null);
	    dialog.setUndecorated(true);

	    JPanel contentPane = new JPanel();
	    contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
	    dialog.setContentPane(contentPane);
	    contentPane.setLayout(null);

	    JPanel Wrapper = new JPanel();
	    Wrapper.setBounds(275, 236, 473, 248);
	    contentPane.add(Wrapper);
	    Wrapper.setLayout(null);

	    JPanel Button_OK = new JPanel();
	    Button_OK.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	    Button_OK.setLayout(null);
	    Button_OK.setBackground(new Color(0, 128, 0));
	    Button_OK.setBounds(28, 156, 423, 53);
	    Button_OK.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseClicked(MouseEvent e) {
	            try {
	                DrawCalendarDays(Calendar_Body, globalYear, globalMonth, globalDay);
	                UpdateTiles(globalMonth, globalDay, globalYear);
	            } catch (SQLException e1) {
	                e1.printStackTrace();
	            }

	            dialog.dispose();
	        }
	    });
	    Wrapper.add(Button_OK);

	    JLabel lblOk = new JLabel("OK");
	    lblOk.setHorizontalAlignment(SwingConstants.CENTER);
	    lblOk.setForeground(Color.WHITE);
	    lblOk.setFont(new Font("Arial", Font.PLAIN, 20));
	    lblOk.setBounds(98, 11, 227, 31);
	    Button_OK.add(lblOk);

	    JLabel SubText_Approve = new JLabel("The reservation was marked as 'Attended'.");
	    SubText_Approve.setHorizontalAlignment(SwingConstants.CENTER);
	    SubText_Approve.setFont(new Font("Arial", Font.PLAIN, 16));
	    SubText_Approve.setBounds(28, 68, 423, 59);
	    Wrapper.add(SubText_Approve);

	    JLabel Heading_Approved = new JLabel("Attended");
	    Heading_Approved.setHorizontalAlignment(SwingConstants.CENTER);
	    Heading_Approved.setFont(new Font("Arial", Font.BOLD, 16));
	    Heading_Approved.setForeground(new Color(0, 128, 0));
	    Heading_Approved.setBounds(28, 11, 423, 59);
	    Wrapper.add(Heading_Approved);

	    dialog.setVisible(true);
	}

	
	public static int[][] getDaysWithReserved(int year, int month) throws SQLException {
	    String jdbcURL = "jdbc:mysql://localhost:3306/spotter_db";
	    String username = "root";
	    String password = "";

	    List<int[]> dayList = new ArrayList<>();

	    try (Connection connection = DriverManager.getConnection(jdbcURL, username, password)) {
	        String sql = "SELECT DAY(target_date), COUNT(*) FROM reservation WHERE YEAR(target_date) = ? " +
	                     "AND MONTH(target_date) = ? AND status = 'Scheduled' GROUP BY DAY(target_date)";
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

	
	public static void DrawCalendarDays(JPanel container_calendar_body, int year, int month, int initDay) throws SQLException {
		int[][] DaysRequests = getDaysWithReserved(year, month);

	    Component[] components = container_calendar_body.getComponents();
	    for (Component component : components) {
	        container_calendar_body.remove(component);
	    }
	    container_calendar_body.revalidate();
	    container_calendar_body.repaint();

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
	        container_calendar_body.add(emptyCell);
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
	                    if (dayRequest[1] == 9) {
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
	                                handleDateClick(month, day, year);
	                            } catch (SQLException e1) {
	                                e1.printStackTrace();
	                            }
	                        }
	                    }
	                }

	                @Override
	                public void mouseEntered(MouseEvent e) {
	                    if (cell != selectedCell) {
	                        cell.setBackground(new Color(240, 240, 240));
	                    }
	                }

	                @Override
	                public void mouseExited(MouseEvent e) {
	                    if (cell != selectedCell) {
	                        cell.setBackground(new Color(255, 255, 255));
	                    }
	                }
	            });
	        }

	        cell.add(dateLabel, BorderLayout.CENTER);
	        container_calendar_body.add(cell);
	    }
	}



	
	
	public static void handleDateClick(int month, int day, int year) throws SQLException {
		globalDay = day;
		globalYear = year;
        globalMonth = month;
		UpdateTiles(month, day, year);
	}
	
	public static void InitializeCalendarAndTiles(JPanel Calendar_Body, JLabel Header_CalendarMonthYear) throws SQLException {
		Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH) + 1;
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        
        globalYear = currentYear;
        globalMonth = currentMonth;
        
        Header_CalendarMonthYear.setText(IntegerToMonth(currentMonth) + " " + currentYear);
        DrawCalendarDays(Calendar_Body, currentYear, currentMonth, currentDay);
		UpdateTiles(currentMonth, currentDay, currentYear);
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

	    DrawCalendarDays(Calendar_Body, globalYear, globalMonth, 0);
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
	
	public JFrame_AdminReserved() throws SQLException {
		setTitle("Reserved");
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
		NavItem_Requests.setIcon(new ImageIcon(JFrame_AdminReserved.class.getResource("/resources_NavigationBar/RequestsInactive.png")));
		NavItem_Requests.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel NavItem_Reserved = new JLabel("");
		NavItem_Reserved.setBounds(225, 17, 101, 31);
		Container_NavItem.add(NavItem_Reserved);
		NavItem_Reserved.setIcon(new ImageIcon(JFrame_AdminReserved.class.getResource("/resources_NavigationBar/ReservedActive.png")));
		NavItem_Reserved.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel NavItem_Accounts = new JLabel("");
		NavItem_Accounts.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFrame_AdminAccounts frame;
				try {
					frame = new JFrame_AdminAccounts();
					frame.setVisible(true);
					dispose();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		NavItem_Accounts.setBounds(542, 17, 103, 31);
		Container_NavItem.add(NavItem_Accounts);
		NavItem_Accounts.setIcon(new ImageIcon(JFrame_AdminRequests.class.getResource("/resources_NavigationBar/AccountsInactive.png")));
		NavItem_Accounts.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel NavItem_About = new JLabel("");
		NavItem_About.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFrame_AdminAbout frame;
				try {
					frame = new JFrame_AdminAbout();
					frame.setVisible(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		NavItem_About.setBounds(708, 17, 80, 31);
		Container_NavItem.add(NavItem_About);
		NavItem_About.setIcon(new ImageIcon(JFrame_AdminRequests.class.getResource("/resources_NavigationBar/AboutInactive.png")));
		NavItem_About.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel Indicator_ActiveNavItem = new JPanel();
		Indicator_ActiveNavItem.setBackground(new Color(33, 232, 23));
		Indicator_ActiveNavItem.setBounds(227, 59, 99, 5);
		Container_NavItem.add(Indicator_ActiveNavItem);
		
		JLabel NavItem_History = new JLabel("New label");
		NavItem_History.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFrame_AdminHistory frame;
				try {
					frame = new JFrame_AdminHistory();
					frame.setVisible(true);
					dispose();
				} catch (SQLException e1) {
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
		
		JPanel Container_AdminRequests = new JPanel();
		Container_AdminRequests.setBackground(new Color(222, 222, 222));
		Container_AdminRequests.setBounds(0, 63, 1424, 797);
		contentPane.add(Container_AdminRequests);
		Container_AdminRequests.setLayout(null);
		
		JPanel Container_UserDetails = new JPanel();
		Container_UserDetails.setBackground(Color.WHITE);
		Container_UserDetails.setBounds(1232, 11, 182, 197);
		Container_AdminRequests.add(Container_UserDetails);
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
		
		JPanel Calendar_AdminReserved = new JPanel();
		Calendar_AdminReserved.setBackground(new Color(243, 244, 246));
		Calendar_AdminReserved.setBounds(26, 43, 842, 711);
		Container_AdminRequests.add(Calendar_AdminReserved);
		Calendar_AdminReserved.setLayout(null);
		
		JPanel Container_Calendar = new JPanel();
		Container_Calendar.setBackground(new Color(255, 255, 255));
		Container_Calendar.setBounds(25, 76, 792, 610);
		Calendar_AdminReserved.add(Container_Calendar);
		Container_Calendar.setLayout(null);
		
		JPanel Calendar_Days = new JPanel();
		Calendar_Days.setBounds(0, 0, 792, 65);
		Container_Calendar.add(Calendar_Days);
		Calendar_Days.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel Header_Sunday = new JLabel("Sun");
		Header_Sunday.setHorizontalAlignment(SwingConstants.CENTER);
		Header_Sunday.setFont(new Font("Arial", Font.BOLD, 16));
		Calendar_Days.add(Header_Sunday);
		
		JLabel Header_Monday = new JLabel("Mon");
		Header_Monday.setHorizontalAlignment(SwingConstants.CENTER);
		Header_Monday.setFont(new Font("Arial", Font.BOLD, 16));
		Calendar_Days.add(Header_Monday);
		
		JLabel Header_Tuesday = new JLabel("Tue");
		Header_Tuesday.setHorizontalAlignment(SwingConstants.CENTER);
		Header_Tuesday.setFont(new Font("Arial", Font.BOLD, 16));
		Calendar_Days.add(Header_Tuesday);
		
		JLabel Header_Wednesday = new JLabel("Wed");
		Header_Wednesday.setHorizontalAlignment(SwingConstants.CENTER);
		Header_Wednesday.setFont(new Font("Arial", Font.BOLD, 16));
		Calendar_Days.add(Header_Wednesday);
		
		JLabel Header_Thursday = new JLabel("Thu");
		Header_Thursday.setHorizontalAlignment(SwingConstants.CENTER);
		Header_Thursday.setFont(new Font("Arial", Font.BOLD, 16));
		Calendar_Days.add(Header_Thursday);
		
		JLabel Header_Friday = new JLabel("Fri");
		Header_Friday.setHorizontalAlignment(SwingConstants.CENTER);
		Header_Friday.setFont(new Font("Arial", Font.BOLD, 16));
		Calendar_Days.add(Header_Friday);
		
		JLabel Header_Saturday = new JLabel("Sat");
		Header_Saturday.setHorizontalAlignment(SwingConstants.CENTER);
		Header_Saturday.setFont(new Font("Arial", Font.BOLD, 16));
		Calendar_Days.add(Header_Saturday);
		
		Calendar_Body = new JPanel();
		Calendar_Body.setBackground(new Color(255, 255, 255));
		Calendar_Body.setBounds(0, 67, 792, 543);
		Container_Calendar.add(Calendar_Body);
		Calendar_Body.setLayout(new GridLayout(0, 7, 5, 0));
		
		Header_CalendarMonthYear = new JLabel("November 2000");
		Header_CalendarMonthYear.setFont(new Font("Arial", Font.BOLD, 25));
		Header_CalendarMonthYear.setBounds(25, 0, 617, 78);
		Calendar_AdminReserved.add(Header_CalendarMonthYear);
		
		JLabel PreviousMonth = new JLabel("");
		PreviousMonth.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		PreviousMonth.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					UpdateCalendar(Calendar_Body, Header_CalendarMonthYear, false);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		PreviousMonth.setIcon(new ImageIcon(JFrame_AdminRequests.class.getResource("/resources_AdminContent/LeftArrowDisabled.png")));
		PreviousMonth.setHorizontalAlignment(SwingConstants.CENTER);
		PreviousMonth.setBounds(767, 31, 20, 20);
		Calendar_AdminReserved.add(PreviousMonth);
		
		JLabel NextMonth = new JLabel("");
		NextMonth.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		NextMonth.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					UpdateCalendar(Calendar_Body, Header_CalendarMonthYear, true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		NextMonth.setIcon(new ImageIcon(JFrame_AdminRequests.class.getResource("/resources_AdminContent/RightArrowDisabled.png")));
		NextMonth.setHorizontalAlignment(SwingConstants.CENTER);
		NextMonth.setBounds(797, 31, 20, 20);
		Calendar_AdminReserved.add(NextMonth);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(JFrame_AdminRequests.class.getResource("/resources_AdminContent/CalendarLegend.png")));
		lblNewLabel.setBounds(33, 0, 302, 44);
		Container_AdminRequests.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(895, 43, 499, 711);
		Container_AdminRequests.add(scrollPane);
		
		Tiles_AdminReserved = new JPanel();
		scrollPane.setColumnHeaderView(Tiles_AdminReserved);
		Tiles_AdminReserved.setBackground(new Color(243, 244, 246));
		Tiles_AdminReserved.setLayout(new BoxLayout(Tiles_AdminReserved, BoxLayout.Y_AXIS));
		
		InitializeCalendarAndTiles(Calendar_Body, Header_CalendarMonthYear);
		
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

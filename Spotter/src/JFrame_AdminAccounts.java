import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import java.awt.LayoutManager;

public class JFrame_AdminAccounts extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Component panel;
	private Component panel1;
	private static boolean UserAccountDetails = false;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame_AdminAccounts frame = new JFrame_AdminAccounts();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	public JFrame_AdminAccounts() throws SQLException {
		setTitle("Accounts");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1440, 900);
		setLocationRelativeTo(null);
		contentPane = new JPanel();

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
		NavItem_Requests.setIcon(new ImageIcon(JFrame_AdminAccounts.class.getResource("/resources_NavigationBar/RequestsInactive.png")));
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
		NavItem_Accounts.setBounds(542, 17, 103, 31);
		Container_NavItem.add(NavItem_Accounts);
		NavItem_Accounts.setIcon(new ImageIcon(JFrame_AdminAccounts.class.getResource("/resources_NavigationBar/AccountsActive.png")));
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
		NavItem_About.setIcon(new ImageIcon(JFrame_AdminRequests.class.getResource("/resources_NavigationBar/AboutInactive.png")));
		NavItem_About.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel Indicator_ActiveNavItem = new JPanel();
		Indicator_ActiveNavItem.setBackground(new Color(33, 232, 23));
		Indicator_ActiveNavItem.setBounds(542, 60, 103, 5);
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
		
		JPanel Container_AdminAccounts = new JPanel();
		Container_AdminAccounts.setBackground(new Color(222, 222, 222));
		Container_AdminAccounts.setBounds(0, 65, 1424, 808);
		contentPane.add(Container_AdminAccounts);
		Container_AdminAccounts.setLayout(null);
		
		JPanel Container_UserDetails = new JPanel();
		Container_UserDetails.setBackground(Color.WHITE);
		Container_UserDetails.setBounds(1232, 11, 182, 197);
		Container_AdminAccounts.add(Container_UserDetails);
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
		
		JPanel accounts = new JPanel();
		accounts.setBackground(new Color(222, 222, 222));
		accounts .setBounds(32, 0, 1359, 766);
		Container_AdminAccounts.add(accounts );
		
		
		

		
		accounts.setLayout(null);
		
		JScrollPane student = new JScrollPane(panel);
		JScrollBar verticalScrollBar1 = student.getVerticalScrollBar();
		verticalScrollBar1.setUnitIncrement(20); // You can adjust this value as needed
		

		
		// Get the horizontal scrollbar
		JScrollBar horizontalScrollBar1 = student.getHorizontalScrollBar();
		
		// Set the unit increment for horizontal scrollbar
		horizontalScrollBar1.setUnitIncrement(20); // You can adjust this value as needed
		student.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		student.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		student.setBackground(Color.WHITE);
		student.setBounds(120, 80, 500, 600);
		accounts.add(student);
		
		JPanel panel = new JPanel(new GridLayout(0, 1)); // 
		panel.setBackground(Color.WHITE);
		 panel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
		 panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		student.setViewportView(panel);
		
		JLabel stud = new JLabel("Student Accounts");
		stud.setFont(new Font("Arial", Font.BOLD, 20));
		stud.setHorizontalAlignment(SwingConstants.CENTER);
		stud.setBounds(271, 31, 203, 38);
		accounts.add(stud);
		
		JScrollPane admin = new JScrollPane(panel1);
		admin.setBounds(700, 80, 498, 600);
		JScrollBar verticalScrollBar = admin.getVerticalScrollBar();
		verticalScrollBar.setUnitIncrement(20); // You can adjust this value as needed
		// Get the horizontal scrollbar
		JScrollBar horizontalScrollBar = admin.getHorizontalScrollBar();
		// Set the unit increment for horizontal scrollbar
		horizontalScrollBar.setUnitIncrement(20); // You can adjust this value as needed
		admin.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		admin.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		accounts.add(admin);
		
		JPanel panel1 = new JPanel(new GridLayout(0, 1)); // 
		admin.setViewportView(panel1);
		panel1.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
		panel1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		panel1.setBackground(Color.WHITE);
		
		
		JLabel adm = new JLabel("Admin Accounts");
		adm.setHorizontalAlignment(SwingConstants.CENTER);
		adm.setFont(new Font("Arial", Font.BOLD, 20));
		adm.setBounds(851, 31, 203, 38);
		accounts.add(adm);
		
		List<JPanel> tileList = new ArrayList<>();
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/spotter_db", "root", "")) {
		    String query = "SELECT email, student_number, program, isVerified FROM Student_Accounts WHERE isVerified = 0";
		    try (PreparedStatement statement = connection.prepareStatement(query)) {
		        ResultSet resultSet = statement.executeQuery();

		        while (resultSet.next()) {
		            String email = resultSet.getString("email");
		            String studentNumber = resultSet.getString("student_number"); // Retrieve as String
		            String program = resultSet.getString("program");
		            int isVerified = resultSet.getInt("isVerified");

		            if (isTileUnique(email, studentNumber, program, isVerified, tileList)) {
		                JPanel tile = createTile(email, studentNumber, program, isVerified);
		                panel.add(tile);
		                tileList.add(tile);
		            }
		        }
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		}


		List<JPanel> tileList1 = new ArrayList<>();
	        try {
	            // Replace with your database connection details
	        	Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/spotter_db", "root", "");
	        	String query = "SELECT email, lastname, firstname, middlename, isVerified FROM Admin_Accounts WHERE isVerified = 0";
	        	 PreparedStatement statement = connection.prepareStatement(query);
	            ResultSet resultSet = statement.executeQuery();

	            while (resultSet.next()) {
	            	String email = resultSet.getString("email");
	            	String lastname = resultSet.getString("lastname");
	            	String firstname =resultSet.getString("firstname");
	            	String middlename = resultSet.getString("middlename");
	            	int isVerified = resultSet.getInt("isVerified");
	            	 
	            	if (isTileUnique1(email, lastname, firstname, middlename, isVerified, tileList1)) {
	                      JPanel tile = createTile1(email, lastname, firstname, middlename, isVerified);
	                      panel1.add(tile);
	                      tileList1.add(tile);
	                }
	            }

	            connection.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        
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

	
	    private static JPanel createTile(String email, String studentNumber, String program, int isVerified) {
	    	JPanel tile = new JPanel();
	    	tile.setBackground(Color.WHITE);
	        tile.setPreferredSize(new Dimension(455, 171));
	        tile.setSize(455, 171);
			tile.setMaximumSize(new Dimension(455, 171));
			tile.setMinimumSize(new Dimension(455, 171));
			tile.setBorder(BorderFactory.createCompoundBorder(
				    BorderFactory.createLineBorder(Color.WHITE, 10),
				    BorderFactory.createLineBorder(Color.BLUE, 3)
				));
	        
			JLabel emailLabel = new JLabel("Email: " + email);
	        emailLabel.setFont(new Font("Arial", Font.BOLD, 15));

	        JLabel studentNumberLabel = new JLabel("Student Number: " + studentNumber);
	        studentNumberLabel.setFont(new Font("Arial", Font.BOLD, 15));

	        JLabel programLabel = new JLabel("Program: " + program);
	        programLabel.setFont(new Font("Arial", Font.BOLD, 15));

	        JLabel isVerifiedLabel = new JLabel("Is Verified: " + (isVerified == 1 ? "Yes" : "No"));
	        isVerifiedLabel.setFont(new Font("Arial", Font.BOLD, 15));
	        isVerifiedLabel.setForeground(Color.RED);
	        
	        emailLabel.setHorizontalAlignment(SwingConstants.CENTER);
	        studentNumberLabel.setHorizontalAlignment(SwingConstants.CENTER);
	        programLabel.setHorizontalAlignment(SwingConstants.CENTER);
	        isVerifiedLabel.setHorizontalAlignment(SwingConstants.CENTER);

	        tile.setLayout(new BorderLayout());
	     
	        
	        JPanel infoPanel = new JPanel(new GridLayout(4, 2, 5, 5)); // Rows, columns, horizontal gap, vertical gap
	        infoPanel.setBackground(Color.WHITE);
	        infoPanel.add(emailLabel);
	        infoPanel.add(studentNumberLabel);
	        infoPanel.add(programLabel);
	        infoPanel.add(isVerifiedLabel);
	        			
	        tile.add(infoPanel, BorderLayout.CENTER);

	      
	        
	        JButton acceptButton = new JButton("Accept");
	        acceptButton.setBackground(new Color(189, 183, 107));
	        acceptButton.addMouseListener(new MouseAdapter() {
	        	@Override
	        	public void mouseEntered(MouseEvent e) {
	        		acceptButton.setBackground(new Color(0, 191, 255));
	        	}
	        	@Override
	        	public void mouseExited(MouseEvent e) {
	        		acceptButton.setBackground(new Color(189, 183, 107));
	        	}
	        });
	        JButton declineButton = new JButton("Decline");
	        declineButton.setBackground(new Color(255, 0, 0));
	        declineButton.addMouseListener(new MouseAdapter() {
	        	@Override
	        	public void mouseEntered(MouseEvent e) {
	        		declineButton.setBackground(new Color(0, 191, 255));
	        	}
	        	@Override
	        	public void mouseExited(MouseEvent e) {
	        		declineButton.setBackground(new Color(255, 0, 0));
	        	}
	        });

	        acceptButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	 
	            	// Your accept button logic here

	                // Assuming you have access to the email of the student to verify
	                String studentEmail = email; // Replace with the actual email

	                // Update the isVerified field to 1 for the student with the specified email
	                try {
	                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/spotter_db", "root", "");

	                    // Update the isVerified field to 1 for the student with the specified email
	                    String updateQuery = "UPDATE Student_Accounts SET isVerified = 1 WHERE email = ?";
	                    PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
	                    updateStatement.setString(1, studentEmail);
	                    int rowsUpdated = updateStatement.executeUpdate();
	                    updateStatement.close();

	                    if (rowsUpdated > 0) {
	                        // Display a success message
	                        JOptionPane.showMessageDialog(null, "Student account verified successfully!");

	                        // Remove the tile from the panel to reflect the change
	                        updateTileAfterAction(tile);

	                        // Email notification code
	                        final String senderEmail = "sigmundfiller02@gmail.com";
	                        final String senderPassword = "ruxshcrztrzdnobx";
	                        String subject = "Account Verification Notification";
	                        String messageText = "Your account has been verified.";

	                        Properties properties = new Properties();
	                        properties.put("mail.smtp.host", "smtp.gmail.com");
	                        properties.put("mail.smtp.port", "587");
	                        properties.put("mail.smtp.auth", "true");
	                        properties.put("mail.smtp.starttls.enable", "true");

	                        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
	                            protected PasswordAuthentication getPasswordAuthentication() {
	                                return new PasswordAuthentication(senderEmail, senderPassword);
	                            }
	                        });

	                        try {
	                            MimeMessage msg = new MimeMessage(session);
	                            msg.setFrom(new InternetAddress(senderEmail));
	                            msg.setRecipients(
	                                    Message.RecipientType.TO,
	                                    InternetAddress.parse(studentEmail)
	                            );
	                            msg.setSubject(subject);
	                            msg.setText("Hello " + studentEmail + ". " + messageText);

	                            JOptionPane.showMessageDialog(null, "Notification sent to " + studentEmail);

	                            Transport.send(msg);
	                        } catch (MessagingException mEx) {
	                            mEx.printStackTrace();
	                            JOptionPane.showMessageDialog(null, "There was an error sending the notification. Please try again.");
	                        }
	                    } else {
	                        // Display an error message if no rows were updated
	                        JOptionPane.showMessageDialog(null, "Failed to verify the student account.", "Error", JOptionPane.ERROR_MESSAGE);
	                    }

	                    connection.close();
	                } catch (SQLException ex) {
	                    ex.printStackTrace();
	                    // Display an error message in case of a database error
	                    JOptionPane.showMessageDialog(null, "Database error. Unable to verify the student account.", "Error", JOptionPane.ERROR_MESSAGE);
	                }
		               			
	            }
	            
	        });

	        declineButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {

	            	// Your decline button logic here

	                // Assuming you have access to the email of the student to decline
	                String studentEmail = email; // Replace with the actual email

	                // Delete the student record with the specified email from the database
	                try {
	                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/spotter_db", "root", "");
	                    String deleteQuery = "DELETE FROM Student_Accounts WHERE email = ?";
	                    PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
	                    deleteStatement.setString(1, studentEmail);
	                    int rowsDeleted = deleteStatement.executeUpdate();
	                    deleteStatement.close();
	                    connection.close();

	                    if (rowsDeleted > 0) {
	                        // Display a success message
	                        JOptionPane.showMessageDialog(null, "Student account declined and deleted successfully!");

	                        // Remove the tile from the panel to reflect the decline action
	                        updateTileAfterAction(tile);

	                        // Email notification code
	                        sendDeclineNotification(studentEmail);
	                    } else {
	                        // Display an error message if no rows were deleted
	                        JOptionPane.showMessageDialog(null, "Failed to decline the student account.", "Error", JOptionPane.ERROR_MESSAGE);
	                    }
	                } catch (SQLException ex) {
	                    ex.printStackTrace();
	                    // Display an error message in case of a database error
	                    JOptionPane.showMessageDialog(null, "Database error. Unable to decline the student account.", "Error", JOptionPane.ERROR_MESSAGE);
	                }
	            }

	            // Method to send email notification for student decline
	            private static void sendDeclineNotification(String studentEmail) {
	                final String senderEmail = "sigmundfiller02@gmail.com";
	                final String senderPassword = "ruxshcrztrzdnobx";

	                Properties properties = new Properties();
	                properties.put("mail.smtp.host", "smtp.gmail.com");
	                properties.put("mail.smtp.port", "587");
	                properties.put("mail.smtp.auth", "true");
	                properties.put("mail.smtp.starttls.enable", "true");

	                Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
	                    protected PasswordAuthentication getPasswordAuthentication() {
	                        return new PasswordAuthentication(senderEmail, senderPassword);
	                    }
	                });

	                try {
	                    MimeMessage msg = new MimeMessage(session);
	                    msg.setFrom(new InternetAddress(senderEmail));
	                    msg.setRecipients(
	                            Message.RecipientType.TO,
	                            InternetAddress.parse(studentEmail)
	                    );
	                    msg.setSubject("Account Declined Notification");
	                    msg.setText("Hello " + studentEmail +  ". Your account has been declined.");

	                    JOptionPane.showMessageDialog(null, "Student account for " + studentEmail + " has been declined.");

	                    Transport.send(msg);
	                } catch (MessagingException mEx) {
	                    mEx.printStackTrace();
	                    JOptionPane.showMessageDialog(null, "There was an error sending the decline notification. Please try again.");
	                }
	            }
	        });

	        JPanel buttonPanel = new JPanel();
	        buttonPanel.setBackground(Color.WHITE);
	        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20)); // Add spacing and center buttons
	        buttonPanel.add(acceptButton);
	        buttonPanel.add(declineButton);
	        tile.add(buttonPanel, BorderLayout.SOUTH);

	     return tile;
	    }

	    private static boolean isTileUnique(String email, String studentNumber, String program, int isVerified, List<JPanel> tileList) {
	        for (JPanel tile : tileList) {
	            // Compare the data with the existing tiles
	            // Modify this part according to your specific data structure
	            if (tile.getComponentCount() >= 4) {
	                JLabel emailLabel = (JLabel) ((JPanel) tile.getComponent(0)).getComponent(0);
	                JLabel studentNumberLabel = (JLabel) ((JPanel) tile.getComponent(0)).getComponent(1);
	                JLabel programLabel = (JLabel) ((JPanel) tile.getComponent(0)).getComponent(2);
	                JLabel isVerifiedLabel = (JLabel) ((JPanel) tile.getComponent(0)).getComponent(3);

	                if (emailLabel.getText().equals("Email: " + email) &&
	                    studentNumberLabel.getText().equals("Student Number: " + studentNumber) &&
	                    programLabel.getText().equals("Program: " + program) &&
	                    isVerifiedLabel.getText().equals("Is Verified: " + (isVerified == 1 ? "Yes" : "No"))) {
	                    return false;
	                }
	            }
	        }
	        return true;
	        
	    }
	    
	    private static boolean isTileUnique1(String email, String lastname, String firstname, String middlename, int isVerified, List<JPanel> tileList) {
	        for (JPanel tile : tileList) {
	            // Compare the data with the existing tiles
	            // Modify this part according to your specific data structure
	            if (tile.getComponentCount() >= 5) {
	                JLabel emailLabel = (JLabel) ((JPanel) tile.getComponent(0)).getComponent(0);
	                JLabel lastnameLabel = (JLabel) ((JPanel) tile.getComponent(0)).getComponent(1);
	                JLabel firstnameLabel = (JLabel) ((JPanel) tile.getComponent(0)).getComponent(2);
	                JLabel middlenameLabel = (JLabel) ((JPanel) tile.getComponent(0)).getComponent(3);
	                JLabel isVerifiedLabel = (JLabel) ((JPanel) tile.getComponent(0)).getComponent(4);

	                if (emailLabel.getText().equals("Email: " + email) &&
	                    lastnameLabel.getText().equals("Last Name: " + lastname) &&
	                    firstnameLabel.getText().equals("First Name: " + firstname) &&
	                    middlenameLabel.getText().equals("Middle Name: " + (middlename != null ? middlename : "N/A")) &&
	                    isVerifiedLabel.getText().equals("Is Verified: " + (isVerified == 1 ? "Yes" : "No"))) {
	                    return false;
	                }
	            }
	        }
	        return true;
	    }

	    private static JPanel createTile1(String email, String lastname, String firstname, String middlename, int isVerified) {
	        JPanel tile = new JPanel();
	        tile.setBackground(Color.WHITE);
	        tile.setPreferredSize(new Dimension(455, 171));
	        tile.setSize(455, 171);
	        tile.setMaximumSize(new Dimension(455, 171));
			tile.setMinimumSize(new Dimension(455, 171));
			tile.setBorder(BorderFactory.createCompoundBorder(
				    BorderFactory.createLineBorder(Color.WHITE, 10),
				    BorderFactory.createLineBorder(Color.BLUE, 3)
				));

	       
	        JLabel emailLabel = new JLabel("Email: " + email);
	        emailLabel.setFont(new Font("Arial", Font.BOLD, 15));

	        JLabel lastnameLabel = new JLabel("Last Name: " + lastname);
	        lastnameLabel.setFont(new Font("Arial", Font.BOLD, 15));

	        JLabel firstnameLabel = new JLabel("First Name: " + firstname);
	        firstnameLabel.setFont(new Font("Arial", Font.BOLD, 15));

	        JLabel middlenameLabel = new JLabel("Middle Name: " + (middlename != null ? middlename : "N/A"));
	        middlenameLabel.setFont(new Font("Arial", Font.BOLD, 15));

	        JLabel isVerifiedLabel = new JLabel("Is Verified: " + (isVerified == 1 ? "Yes" : "No"));
	        isVerifiedLabel.setFont(new Font("Arial", Font.BOLD, 15));
	        isVerifiedLabel.setForeground(Color.RED);


	        emailLabel.setHorizontalAlignment(SwingConstants.CENTER);
	        lastnameLabel.setHorizontalAlignment(SwingConstants.CENTER);
	        firstnameLabel.setHorizontalAlignment(SwingConstants.CENTER);
	        middlenameLabel.setHorizontalAlignment(SwingConstants.CENTER);
	        isVerifiedLabel.setHorizontalAlignment(SwingConstants.CENTER);
	        
	        tile.setLayout(new BorderLayout());
	        
	        JPanel infoPanel = new JPanel(new GridLayout(5, 2, 5, 2)); // Rows, columns, horizontal gap, vertical gap
	        infoPanel.setBackground(Color.WHITE);
	        infoPanel.add(emailLabel);
	        infoPanel.add(lastnameLabel);
	        infoPanel.add(firstnameLabel);
	        infoPanel.add(middlenameLabel);
	        infoPanel.add(isVerifiedLabel);

	        tile.add(infoPanel, BorderLayout.CENTER);
	        
	        JButton acceptButton = new JButton("Accept");
	        acceptButton.setBackground(new Color(189, 183, 107));
	        acceptButton.addMouseListener(new MouseAdapter() {
	        @Override
	            public void mouseEntered(MouseEvent e) {
                acceptButton.setBackground(new Color(0, 191, 255));
	            }
	            @Override
	            public void mouseExited(MouseEvent e) {
	    	        acceptButton.setBackground(new Color(189, 183, 107));


	            }
	        });
	        
	        acceptButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {

	                // Assuming you have access to the email of the student to verify
	                String adminEmail = email; // Replace with the actual email

	                // Update the isVerified field to 1 for the student with the specified email
	                try {
	                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/spotter_db", "root", "");
	                    String updateQuery = "UPDATE admin_accounts SET isVerified = 1 WHERE email = ?";
	                    PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
	                    updateStatement.setString(1, adminEmail);
	                    int rowsUpdated = updateStatement.executeUpdate();
	                    updateStatement.close();
	                    connection.close();

	                    if (rowsUpdated > 0) {
	                        // Display a success message
	                        JOptionPane.showMessageDialog(null, "Admin account verified successfully!");

	                        // Remove the tile from the panel to reflect the change
	                        updateTileAfterAction(tile);

	                        // Email notification code
	                        sendVerificationNotification(adminEmail);
	                    } else {
	                        // Display an error message if no rows were updated
	                        JOptionPane.showMessageDialog(null, "Failed to verify the admin account.", "Error", JOptionPane.ERROR_MESSAGE);
	                    }
	                } catch (SQLException ex) {
	                    ex.printStackTrace();
	                    // Display an error message in case of a database error
	                    JOptionPane.showMessageDialog(null, "Database error. Unable to verify the admin account.", "Error", JOptionPane.ERROR_MESSAGE);
	                }
	            }

	            // Method to send email notification for admin verification
	            private static void sendVerificationNotification(String adminEmail) {
	                final String senderEmail = "sigmundfiller02@gmail.com";
	                final String senderPassword = "ruxshcrztrzdnobx";

	                Properties properties = new Properties();
	                properties.put("mail.smtp.host", "smtp.gmail.com");
	                properties.put("mail.smtp.port", "587");
	                properties.put("mail.smtp.auth", "true");
	                properties.put("mail.smtp.starttls.enable", "true");

	                Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
	                    protected PasswordAuthentication getPasswordAuthentication() {
	                        return new PasswordAuthentication(senderEmail, senderPassword);
	                    }
	                });

	                try {
	                    MimeMessage msg = new MimeMessage(session);
	                    msg.setFrom(new InternetAddress(senderEmail));
	                    msg.setRecipients(
	                            Message.RecipientType.TO,
	                            InternetAddress.parse(adminEmail)
	                    );
	                    msg.setSubject("Admin Account Verification Notification");
	                    msg.setText("Hello " + adminEmail +  ". Your admin account has been verified.");

	                    JOptionPane.showMessageDialog(null, "Admin account for " + adminEmail + " has been verified.");

	                    Transport.send(msg);
	                } catch (MessagingException mEx) {
	                    mEx.printStackTrace();
	                    JOptionPane.showMessageDialog(null, "There was an error sending the verification notification. Please try again.");
	                }
	            }
	            
	        });
	        
	        JButton declineButton = new JButton("Decline");
	        declineButton.setBackground(new Color(255, 0, 0));
	        declineButton.addMouseListener(new MouseAdapter() {
	        	
	            @Override
	            public void mouseEntered(MouseEvent e) {
	            	declineButton.setBackground(new Color(0, 191, 255));
	            }
	            @Override
	            public void mouseExited(MouseEvent e) {
	    	        declineButton.setBackground(new Color(255, 0, 0));
	            }
	        });
	        
	        
	        
	       declineButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {

	                // Your decline button logic here

	                // Assuming you have access to the email of the admin to decline
	                String adminEmail = email; // Replace with the actual email

	                // Delete the admin record with the specified email from the database
	                try {
	                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/spotter_db", "root", "");
	                    String deleteQuery = "DELETE FROM admin_accounts WHERE email = ?";
	                    PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
	                    deleteStatement.setString(1, adminEmail);
	                    int rowsDeleted = deleteStatement.executeUpdate();
	                    deleteStatement.close();
	                    connection.close();

	                    // Check the number of rows deleted to determine the outcome
	                    if (rowsDeleted > 0) {
	                        // Display a success message
	                        JOptionPane.showMessageDialog(null, "Admin account declined and deleted successfully!");

	                        // Remove the tile from the panel to reflect the decline action
	                        updateTileAfterAction(tile);

	                        // Email notification code
	                        sendDeclineNotification(adminEmail);
	                    } else {
	                        // Display an error message if no rows were deleted
	                        JOptionPane.showMessageDialog(null, "Failed to decline the admin account.", "Error", JOptionPane.ERROR_MESSAGE);
	                    }
	                } catch (SQLException ex) {
	                    ex.printStackTrace();
	                    // Display an error message in case of a database error
	                    JOptionPane.showMessageDialog(null, "Database error. Unable to decline the admin account.", "Error", JOptionPane.ERROR_MESSAGE);
	                }
	            }

	            // Method to send email notification for admin decline
	            private static void sendDeclineNotification(String adminEmail) {
	                final String email = "sigmundfiller02@gmail.com";
	                final String password = "ruxshcrztrzdnobx";

	                Properties properties = new Properties();
	                properties.put("mail.smtp.host", "smtp.gmail.com");
	                properties.put("mail.smtp.port", "587");
	                properties.put("mail.smtp.auth", "true");
	                properties.put("mail.smtp.starttls.enable", "true");

	                Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
	                    protected PasswordAuthentication getPasswordAuthentication() {
	                        return new PasswordAuthentication(email, password);
	                    }
	                });

	                try {
	                    MimeMessage msg = new MimeMessage(session);
	                    msg.setFrom(new InternetAddress("sigmundfiller02@gmail.com"));
	                    msg.setRecipients(
	                            Message.RecipientType.TO,
	                            InternetAddress.parse(adminEmail)
	                    );
	                    msg.setSubject("Admin Account Declined Notification");
	                    msg.setText("Hello " + adminEmail +  ". Your admin account has been declined.");

	                    JOptionPane.showMessageDialog(null, "Admin account for " + adminEmail + " has been declined.");

	                    Transport.send(msg);
	                } catch (MessagingException mEx) {
	                    mEx.printStackTrace();
	                    JOptionPane.showMessageDialog(null, "There was an error sending the decline notification. Please try again.");
	                }
	            }
	        });
	        
	        JPanel buttonPanel = new JPanel();
	        buttonPanel.setBackground(Color.WHITE);
	        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20)); // Add spacing and center buttons
	        buttonPanel.add(acceptButton);
	        buttonPanel.add(declineButton);
	        tile.add(buttonPanel, BorderLayout.SOUTH);
	        
	        
	        
	        
	       return tile;
	    

	    }
	   
	    private static void updateTileAfterAction(JPanel tile) {
	        Container parent = tile.getParent();
	        parent.remove(tile);
	        parent.revalidate();
	        parent.repaint();
	    }
	   




	   	}
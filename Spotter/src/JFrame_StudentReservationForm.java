import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JComboBox;
import java.awt.Choice;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.security.Timestamp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JFrame_StudentReservationForm extends JFrame {
	

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private int centerX;
	private int centerY;
	private JTextField person1;
	private JTextField person2;
	private JTextField person3;
	private JTextField person4;
	private JTextField person5;
	private JTextField person6;
	private JTextField person7;
	private JTextField person8;
	private JTextField person9;
	private JTextField person10;
	private int maxLength;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame_StudentReservationForm frame = new JFrame_StudentReservationForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public JFrame_StudentReservationForm() throws SQLException {
		JFrame_StudentHome StudentHome_Window = new JFrame_StudentHome();
		String dateWord = StudentHome_Window.DateWordFormat;
		String dateDBFormat = StudentHome_Window.DateDBFormat;
		
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
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					JFrame_StudentHome fr = new JFrame_StudentHome();
					fr.setVisible(true);
					dispose();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		lblNewLabel.setIcon(new ImageIcon(JFrame_StudentReservationForm.class.getResource("/resources_NavigationBar/CancelButton.png")));
		lblNewLabel.setBounds(10, 17, 85, 30);
		NavBar.add(lblNewLabel);
		
		JPanel Container_AdminHome = new JPanel();
		Container_AdminHome.setBackground(new Color(222, 222, 222));
		Container_AdminHome.setBounds(0, 63, 1424, 797);
		contentPane.add(Container_AdminHome);
		Container_AdminHome.setLayout(null);
		
		JPanel form = new JPanel();
		form.setBackground(new Color(255, 255, 255));
		form.setBounds(114, 63, 1200, 600); // Set bounds relative to the top-left corner of Container_AdminHome
		Container_AdminHome.add(form);
		form.setLayout(null);
		// Calculate the center position within Container_AdminHome
        int centerX = (Container_AdminHome.getWidth() - form.getWidth()) / 2;
        int centerY = (Container_AdminHome.getHeight() - form.getHeight()) / 2;

        // Set the location of the form JPanel to center it within Container_AdminHome
        form.setLocation(centerX, centerY);
        
        JLabel booklabel = new JLabel("Book for " + dateWord);
        booklabel.setForeground(new Color(189, 183, 107));
        booklabel.setFont(new Font("Segoe Print", Font.BOLD, 25));  
        booklabel.setBounds(20, 25, 623, 36);
        form.add(booklabel);
        
        JLabel timelimt = new JLabel("Time (1 Hour Limit)");
        timelimt.setFont(new Font("Segoe Print", Font.BOLD, 20));
        timelimt.setBounds(20, 83, 208, 36);
        form.add(timelimt);
        
        JComboBox time = new JComboBox();
        time.setBackground(Color.WHITE);
        time.setModel(new DefaultComboBoxModel(new String[] {"8am - 9am", "9am - 10am", "10am - 11am", "11am - 12pm", "12pm - 1pm", "1pm - 2pm", "2pm - 3pm", "3pm - 4pm", "4pm - 5pm"}));
        time.setFont(new Font("Segoe Print", Font.BOLD, 20));
        time.setBounds(44, 130, 325, 33);
        form.add(time);
        
        JComboBox roomnum = new JComboBox();
        roomnum.setModel(new DefaultComboBoxModel(new String[] {"001", "002", "003", "004", "005", "006"}));
        roomnum.setFont(new Font("Segoe Print", Font.BOLD, 20));
        roomnum.setBackground(Color.WHITE);
        roomnum.setBounds(624, 130, 325, 33);
        form.add(roomnum);
        
        JLabel rmnum = new JLabel("Room Number");
        rmnum.setFont(new Font("Segoe Print", Font.BOLD, 20));
        rmnum.setBounds(624, 83, 208, 36);
        form.add(rmnum);
        
        JLabel list = new JLabel("List of People Inside");
        list.setFont(new Font("Segoe Print", Font.BOLD, 20));
        list.setBounds(20, 200, 208, 36);
        form.add(list);
        
        person1 = new JTextField();
        person1.setFont(new Font("Segoe Print", Font.BOLD, 20));
        person1.setBounds(44, 265, 424, 30);
        form.add(person1);
        person1.setColumns(10);
        
        person2 = new JTextField();
        person2.setFont(new Font("Segoe Print", Font.BOLD, 20));
        person2.setColumns(10);
        person2.setBounds(44, 320, 424, 30);
        form.add(person2);
        
        person3 = new JTextField();
        person3.setFont(new Font("Segoe Print", Font.BOLD, 20));
        person3.setColumns(10);
        person3.setBounds(44, 371, 424, 26);
        form.add(person3);
        
        person4 = new JTextField();
        person4.setFont(new Font("Segoe Print", Font.BOLD, 20));
        person4.setColumns(10);
        person4.setBounds(44, 419, 424, 31);
        form.add(person4);
        
        person5 = new JTextField();
        person5.setFont(new Font("Segoe Print", Font.BOLD, 20));
        person5.setColumns(10);
        person5.setBounds(44, 469, 424, 30);
        form.add(person5);
        
        person6 = new JTextField();
        person6.setFont(new Font("Segoe Print", Font.BOLD, 20));
        person6.setColumns(10);
        person6.setBounds(624, 265, 424, 30);
        form.add(person6);
        
        person7 = new JTextField();
        person7.setFont(new Font("Segoe Print", Font.BOLD, 20));
        person7.setColumns(10);
        person7.setBounds(624, 319, 424, 30);
        form.add(person7);
        
        person8 = new JTextField();
        person8.setFont(new Font("Segoe Print", Font.BOLD, 20));
        person8.setColumns(10);
        person8.setBounds(624, 371, 424, 30);
        form.add(person8);
        
        person9 = new JTextField();
        person9.setFont(new Font("Segoe Print", Font.BOLD, 20));
        person9.setColumns(10);
        person9.setBounds(624, 419, 424, 30);
        form.add(person9);
        
        person10 = new JTextField();
        person10.setFont(new Font("Segoe Print", Font.BOLD, 20));
        person10.setColumns(10);
        person10.setBounds(624, 469, 424, 30);
        form.add(person10);
        //dynamicDateLabel = new DynamicDateLabel();
        
        JButton reserveBtn = new JButton("Reserve");
        reserveBtn.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		reserveBtn.setBackground(new Color(0, 191, 255));
        	}
        	@Override
        	public void mouseExited(MouseEvent e) {
        		reserveBtn.setBackground(new Color(189, 183, 107));
        	}
        });
       
        
        //ito yung ilalagay para masa ma yung request id 
        //preparedStatement.setString(4, "");
        //(room_number, target_date, time_range, request_id,  attendees, status) (?, ?, ?, ?, ?, ?)
        
        
        reserveBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String[] attendees = {
            		    person1.getText(), person2.getText(), person3.getText(), person4.getText(),
            		    person5.getText(), person6.getText(), person7.getText(), person8.getText(),
            		    person9.getText(), person10.getText()
            		};
            		
            	
				int nonEmptyCount = 0;
				for (String attendee : attendees) {
					if (!attendee.trim().isEmpty()) {
							nonEmptyCount++;
					}
				}

				List<String> nonEmptyAttendeesList = new ArrayList<>();
				for (String attendee : attendees) {
				    if (!attendee.trim().isEmpty()) {
				        nonEmptyAttendeesList.add(attendee);
				    }
				}

            	String nonEmptyAttendeesString = String.join(",", nonEmptyAttendeesList);

                if (nonEmptyCount < 5) {
                    JOptionPane.showMessageDialog(null, "Please provide at least 5 attendees.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/spotter_db", "root", "");

                        // Check if a record with the same room_number, target_date, and time_range exists
                        String selectQuery = "SELECT * FROM reservation WHERE room_number = ? AND target_date = ? AND time_range = ?";
                        PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
                        selectStatement.setString(1, (String) roomnum.getSelectedItem());
                        selectStatement.setString(2, dateDBFormat); // You should use the actual target_date/palitan nalang yung sinasabi mo puking ina mo
                        selectStatement.setString(3, (String) time.getSelectedItem());

                        ResultSet resultSet = selectStatement.executeQuery();

                        if (resultSet.next()) {
                            JOptionPane.showMessageDialog(null, "Record with the same room_number, target_date, and time_range already exists.", "Duplicate Record", JOptionPane.WARNING_MESSAGE);
                        } else {
                        	JFrame_StudentLogin JFrame_StudentLogin = new JFrame_StudentLogin();
                        	
                            String insertQuery = "INSERT INTO reservation (room_number, target_date, time_range, attendees, status, requester_id) VALUES (?, ?, ?, ?, ?, ?)";
                            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

                            preparedStatement.setString(1, (String) roomnum.getSelectedItem());
                            preparedStatement.setString(2, dateDBFormat);
                            preparedStatement.setString(3, (String) time.getSelectedItem());
                            preparedStatement.setString(4, nonEmptyAttendeesString);
                            preparedStatement.setString(5, "Pending");
                            preparedStatement.setString(6, JFrame_StudentLogin.StudentID);

                            int result = preparedStatement.executeUpdate();

                            if (result > 0) {
                                JOptionPane.showMessageDialog(null, "Data inserted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                                // Clear the text fields
                                person1.setText("");
                                person2.setText("");
                                person3.setText("");
                                person4.setText("");
                                person5.setText("");
                                person6.setText("");
                                person7.setText("");
                                person8.setText("");
                                person9.setText("");
                                person10.setText("");
                                time.setSelectedIndex(0);
                                roomnum.setSelectedIndex(0);
                            } else {
                                JOptionPane.showMessageDialog(null, "Error in data insertion.", "Error", JOptionPane.ERROR_MESSAGE);
                            }

                            preparedStatement.close();
                        }

                        selectStatement.close();
                        resultSet.close();
                        connection.close();

                    } catch (SQLException ex) {
                        System.out.println("Error in database connection: " + ex.getMessage());
                    }
                }
            }
        });


        reserveBtn.setBackground(new Color(189, 183, 107));
        reserveBtn.setFont(new Font("Segoe Print", Font.BOLD, 20));
        reserveBtn.setBounds(44, 523, 258, 44);
        form.add(reserveBtn);
       
    
        
	}
	
}

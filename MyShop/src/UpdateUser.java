import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class UpdateUser extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField untextField;
	private JTextField infotextField;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateUser frame = new UpdateUser();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UpdateUser() {
		setUndecorated(true);
		setFont(new Font("Segoe UI", Font.PLAIN, 12));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 600);
		contentPane = new JPanel();
		contentPane.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new LineBorder(Color.BLUE));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
		
		JLabel exitLabel = new JLabel("X");
		exitLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exitLabel.setForeground(Color.white);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				exitLabel.setForeground(Color.black);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(EXIT_ON_CLOSE);
			}
		});
		exitLabel.setHorizontalAlignment(SwingConstants.CENTER);
		exitLabel.setFont(new Font("Segoe UI", Font.ITALIC, 18));
		exitLabel.setBounds(528, 0, 22, 24);
		contentPane.add(exitLabel);
		
		JLabel lblNewLabel = new JLabel("Update User Information");
		lblNewLabel.setBorder(new LineBorder(Color.BLUE, 2));
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 550, 60);
		contentPane.add(lblNewLabel);
		
		JButton uptabButton = new JButton("Update Table");
		uptabButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					String empid, fname, lname,pass,user;
					Class.forName("com.mysql.cj.jdbc.Driver");
					
					Connection con = Connect.getConnection();
					Statement st = con.createStatement();
					String query = "select * from login_page;";
					
					table.setModel(new DefaultTableModel());
					ResultSet rs = st.executeQuery(query);
					ResultSetMetaData rsmd = rs.getMetaData();
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					
					int cols = rsmd.getColumnCount();
					String [] colName = new String[cols];
					for(int i =0;i<cols;i++) {
						colName[i] = rsmd.getColumnName(i+1);
					model.setColumnIdentifiers(colName);
					//read all the rows 1 at a time and model them into the table
					while(rs.next()) {
						empid = rs.getString(1);
						fname = rs.getString(2);
						lname = rs.getString(3);
						pass = rs.getString(4);
						user = rs.getString(5);
						String [] row = {empid,fname,lname,pass,user};
						model.addRow(row);
					}
					}
					
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		uptabButton.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 13));
		uptabButton.setBounds(210, 218, 131, 23);
		contentPane.add(uptabButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 71, 530, 136);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_1 = new JLabel("Enter a User Name to Update Information");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_1.setBounds(64, 251, 430, 25);
		contentPane.add(lblNewLabel_1);
		
		untextField = new JTextField();
		untextField.setFont(new Font("Segoe UI", Font.ITALIC, 13));
		untextField.setHorizontalAlignment(SwingConstants.CENTER);
		untextField.setBounds(74, 287, 400, 30);
		contentPane.add(untextField);
		untextField.setColumns(10);
		
		JRadioButton epidRB = new JRadioButton("Employee ID");
		buttonGroup.add(epidRB);
		epidRB.setFont(new Font("Segoe UI", Font.ITALIC, 13));
		epidRB.setBounds(44, 373, 109, 23);
		contentPane.add(epidRB);
		
		JRadioButton lnameRB = new JRadioButton("Last Name");
		buttonGroup.add(lnameRB);
		lnameRB.setFont(new Font("Segoe UI", Font.ITALIC, 13));
		lnameRB.setBounds(232, 373, 109, 23);
		contentPane.add(lnameRB);
		
		JRadioButton fnameRB = new JRadioButton("First Name");
		buttonGroup.add(fnameRB);
		fnameRB.setFont(new Font("Segoe UI", Font.ITALIC, 13));
		fnameRB.setBounds(385, 373, 109, 23);
		contentPane.add(fnameRB);
		
		JRadioButton pcRB = new JRadioButton("Pass Code");
		buttonGroup.add(pcRB);
		pcRB.setFont(new Font("Segoe UI", Font.ITALIC, 13));
		pcRB.setBounds(132, 412, 109, 23);
		contentPane.add(pcRB);
		
		JRadioButton unameRB = new JRadioButton("User Name");
		buttonGroup.add(unameRB);
		unameRB.setFont(new Font("Segoe UI", Font.ITALIC, 13));
		unameRB.setBounds(308, 412, 109, 23);
		contentPane.add(unameRB);
		
		JButton clearButton = new JButton("Clear");
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				infotextField.setText("");
				untextField.setText("");
				
			}
		});
		clearButton.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		clearButton.setBounds(451, 564, 89, 23);
		contentPane.add(clearButton);
		
		JLabel backLabel = new JLabel("< Back");
		backLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				backLabel.setForeground(Color.black);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				backLabel.setForeground(Color.BLUE);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				UserPage page;
				page = new UserPage();
				 page.setVisible(true);
				 
				 page.setLocationRelativeTo(null);
				 JComponent comp = (JComponent) e.getSource();
				  Window win = SwingUtilities.getWindowAncestor(comp);
				  win.dispose();
			}
		});
		backLabel.setForeground(Color.BLUE);
		backLabel.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 13));
		backLabel.setBounds(10, 565, 69, 21);
		contentPane.add(backLabel);
		
		infotextField = new JTextField();
		infotextField.setHorizontalAlignment(SwingConstants.CENTER);
		infotextField.setFont(new Font("Segoe UI", Font.ITALIC, 13));
		infotextField.setBounds(84, 491, 400, 30);
		contentPane.add(infotextField);
		infotextField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Enter new Information");
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(64, 455, 430, 25);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Select an Item to Update");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_3.setBounds(64, 328, 430, 25);
		contentPane.add(lblNewLabel_3);
		
		JButton upButton = new JButton("Update");
		upButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/myshop","root","Champ0311");
					
					 if(epidRB.isSelected()) {
						 
						 
						String query = "update login_page set Employee_ID = ? where User_Name = ? ";
						PreparedStatement st = con.prepareStatement(query);
						st.setInt(1, Integer.parseInt(infotextField.getText()));
						st.setString(2, untextField.getText().trim().toLowerCase());
						st.executeUpdate();

						int i = st.executeUpdate();
						JOptionPane.showMessageDialog( upButton, untextField.getText().trim().toUpperCase()+"'s Employee Id has been changed to "+Integer.parseInt(infotextField.getText()) );
						st.close();
						con.close();
					 						}
					 else if(lnameRB.isSelected()) {
						 
						String query = "update login_page set last_name = ? where User_Name = ? ";
						PreparedStatement st = con.prepareStatement(query);
						st.setString(1,infotextField.getText() );
						st.setString(2, untextField.getText().trim().toLowerCase());
						st.executeUpdate();

						int i = st.executeUpdate();
						JOptionPane.showMessageDialog( upButton, untextField.getText().trim().toUpperCase()+"'s Last Name has been change to "+infotextField.getText());
						st.close();
						con.close();
					 						}
					 else if(fnameRB.isSelected()) {
						 
						String query = "update login_page set first_name = ? where User_Name = ? ";
						PreparedStatement st = con.prepareStatement(query);
						st.setString(1,infotextField.getText() );
						st.setString(2, untextField.getText().trim().toLowerCase());
						st.executeUpdate();

						int i = st.executeUpdate();
						JOptionPane.showMessageDialog( upButton, untextField.getText().trim().toUpperCase()+"'s First Name has been changed to "+infotextField.getText() );
						st.close();
						con.close();
					 						}
					 else if(pcRB.isSelected()) {
						 
						String query = "update login_page set Pass_code = ? where User_Name = ? ";
						PreparedStatement st = con.prepareStatement(query);
						st.setString(1, infotextField.getText() );
						st.setString(2, untextField.getText().trim().toLowerCase());
						st.executeUpdate();

						int i = st.executeUpdate();
						JOptionPane.showMessageDialog( upButton, untextField.getText().trim().toUpperCase()+"'s Pass Code has been changed to "+infotextField.getText());
						st.close();
						con.close();
					 						}
					 else if(unameRB.isSelected()) {
						 
						String query = "update login_page set User_Name = ? where User_Name = ? ";
						PreparedStatement st = con.prepareStatement(query);
						st.setString(1,infotextField.getText() );
						st.setString(2, untextField.getText().trim().toLowerCase());
						st.executeUpdate();

						int i = st.executeUpdate();
						JOptionPane.showMessageDialog( upButton, untextField.getText().trim().toUpperCase()+"'s User Name has been changed to "+infotextField.getText());
						st.close();
						con.close();
					 						}
					} catch (ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
					}
		
				}
	
		});
		buttonGroup.add(upButton);
		upButton.setFont(new Font("Segoe UI", Font.BOLD, 13));
		upButton.setBounds(340, 564, 89, 23);
		contentPane.add(upButton);
		
	}
}

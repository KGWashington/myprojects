import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;


public class AdminDashBoard extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {  
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminDashBoard frame = new AdminDashBoard();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection conn = null;
	private JTable table_1;
	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	
	public AdminDashBoard() throws SQLException {
		setUndecorated(true);
		conn = Connect.getConnection();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 780, 520);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new LineBorder(Color.BLUE));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_1.setBounds(110, 271, 241, 147);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		
		JButton uinfoButton = new JButton("Update");
		uinfoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserPage user;
				user = new UserPage();
				user.setVisible(true);
				user.setLocationRelativeTo(null);
				
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
				
			}
		});
		uinfoButton.setBounds(76, 111, 83, 25);
		panel_1.add(uinfoButton);
		uinfoButton.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 12));
		
		JLabel lblNewLabel_3 = new JLabel("-Register/Remove Users");
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.ITALIC, 15));
		lblNewLabel_3.setBounds(25, 23, 171, 25);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_10 = new JLabel("-Update Existing Users");
		lblNewLabel_10.setFont(new Font("Segoe UI", Font.ITALIC, 15));
		lblNewLabel_10.setBounds(25, 59, 171, 30);
		panel_1.add(lblNewLabel_10);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.setBounds(390, 271, 241, 147);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_7 = new JLabel("- Update Price/Quantity");
		lblNewLabel_7.setBounds(26, 79, 154, 21);
		panel_3.add(lblNewLabel_7);
		lblNewLabel_7.setFont(new Font("Segoe UI", Font.ITALIC, 15));
		
		JLabel lblNewLabel_8 = new JLabel("- Add/Remove Products");
		lblNewLabel_8.setBounds(26, 11, 152, 21);
		panel_3.add(lblNewLabel_8);
		lblNewLabel_8.setFont(new Font("Segoe UI", Font.ITALIC, 15));
		
		JButton ustockButton = new JButton("Update");
		ustockButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InvenPage inven;
				try {
					inven = new InvenPage();
					inven.setVisible(true);
					inven.setLocationRelativeTo(null);
					
					JComponent comp = (JComponent) e.getSource();
					Window win = SwingUtilities.getWindowAncestor(comp);
					win.dispose();
				
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				 
				
			}
		});
		ustockButton.setBounds(76, 111, 83, 25);
		panel_3.add(ustockButton);
		ustockButton.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 12));
		
		JLabel lblNewLabel_6 = new JLabel("- Update Inventory");
		lblNewLabel_6.setBounds(26, 43, 119, 21);
		panel_3.add(lblNewLabel_6);
		lblNewLabel_6.setFont(new Font("Segoe UI", Font.ITALIC, 15));
		lblNewLabel_6.setBackground(Color.WHITE);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.BLUE);
		panel_4.setBounds(0, 0, 780, 55);
		contentPane.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("DashBoard Home");
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 28));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(38, 11, 238, 33);
		panel_4.add(lblNewLabel);
		
		JLabel miniLabel = new JLabel("-");
		miniLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				miniLabel.setForeground(Color.white);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				miniLabel.setForeground(Color.black);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				
				setState(JFrame.ICONIFIED);
			}
		});
		miniLabel.setBorder(null);
		miniLabel.setForeground(Color.BLACK);
		miniLabel.setBackground(Color.WHITE);
		miniLabel.setHorizontalAlignment(SwingConstants.CENTER);
		miniLabel.setFont(new Font("Segoe UI", Font.ITALIC, 18));
		miniLabel.setBounds(726, 0, 22, 24);
		panel_4.add(miniLabel);
		
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
		exitLabel.setForeground(Color.BLACK);
		exitLabel.setHorizontalAlignment(SwingConstants.CENTER);
		exitLabel.setFont(new Font("Segoe UI", Font.ITALIC, 18));
		exitLabel.setBounds(748, 0, 22, 24);
		panel_4.add(exitLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.BLUE, null));
		scrollPane.setBounds(390, 100, 241, 120);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		scrollPane.setViewportView(table);
		table.setFillsViewportHeight(true);
		
		JLabel lblNewLabel_4 = new JLabel("Stock Information");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBorder(new LineBorder(Color.BLUE, 1, true));
		lblNewLabel_4.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_4.setBounds(390, 66, 241, 26);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("User Information");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_5.setBorder(new LineBorder(Color.BLUE, 1, true));
		lblNewLabel_5.setBounds(110, 66, 241, 26);
		contentPane.add(lblNewLabel_5);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBackground(Color.BLUE);
		scrollPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 0, 255), Color.BLACK));
		scrollPane_1.setBounds(110, 100, 241, 120);
		contentPane.add(scrollPane_1);
		
		table_1 = new JTable();
		table_1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		scrollPane_1.setViewportView(table_1);
			String query1 = "select first_name, last_name from login_page";
			PreparedStatement st1 = conn.prepareStatement(query1);
			ResultSet rs1 = st1.executeQuery();
			
			
			JLabel lblNewLabel_9 = new JLabel("*Below Par Items*");
			lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_9.setForeground(Color.RED);
			lblNewLabel_9.setFont(new Font("Segoe UI", Font.BOLD, 17));
			lblNewLabel_9.setBounds(390, 220, 241, 26);
			contentPane.add(lblNewLabel_9);
			
			JButton parButton = new JButton("Par Info");
			parButton.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 12));
			parButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						String pro, stock, par;
						Class.forName("com.mysql.cj.jdbc.Driver");
						
						conn = Connect.getConnection();
						Statement st = conn.createStatement();
						String query = "select Product, Stock, Par from stock where Par > Stock;";
						
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
							pro = rs.getString(1);
							stock = rs.getString(2);
							par = rs.getString(3);
							String [] row = {pro, stock,par	};
							model.addRow(row);
						}
						}
						
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			parButton.setBounds(653, 100, 89, 23);
			contentPane.add(parButton);
			
			JButton userButton = new JButton("Get Users");
			userButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
					String id, lnam, fnam,unam;
					
					
					Statement st1 = conn.createStatement();
					String query1 = "select Employee_ID, last_name, first_name, User_Name from login_page;";
					
					table_1.setModel(new DefaultTableModel());
					ResultSet rs1 = st1.executeQuery(query1);
					ResultSetMetaData rsmd1 = rs1.getMetaData();
					DefaultTableModel model1 = (DefaultTableModel) table_1.getModel();
					
					int cols1 = rsmd1.getColumnCount();
					String [] colName1 = new String[cols1];
					for(int i =0;i<cols1;i++) {
						colName1[i] = rsmd1.getColumnName(i+1);
					model1.setColumnIdentifiers(colName1);
					//read all the rows 1 at a time and model them into the table
					while(rs1.next()) {
						id = rs1.getString(1);
						lnam = rs1.getString(2);
						fnam = rs1.getString(3);
						unam = rs1.getString(4);
						String [] row1 = {id, lnam,fnam,unam	};
						model1.addRow(row1);
							}
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			
			);
			userButton.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 12));
			userButton.setBounds(11, 99, 89, 23);
			contentPane.add(userButton);
			
			JButton logoutButton = new JButton("LOGOUT");
			logoutButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					LoginPage page = new LoginPage();
					 page.setVisible(true);
					 
					 page.setLocationRelativeTo(null);
					 JComponent comp = (JComponent) e.getSource();
					  Window win = SwingUtilities.getWindowAncestor(comp);
					  win.dispose();
				}
			});
			logoutButton.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 15));
			logoutButton.setBounds(10, 486, 137, 23);
			contentPane.add(logoutButton);
			
			}
	}


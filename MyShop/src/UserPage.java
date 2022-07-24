import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class UserPage extends JFrame {

	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserPage frame = new UserPage();
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
	Connection conn = null;
	private JTable table;
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	public UserPage() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 780, 520);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 780, 65);
		panel.setBackground(Color.BLUE);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel headLabel = new JLabel("MyShop Users");
		headLabel.setForeground(Color.WHITE);
		headLabel.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 38));
		headLabel.setBounds(10, 11, 345, 40);
		panel.add(headLabel);
		
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
		miniLabel.setHorizontalAlignment(SwingConstants.CENTER);
		miniLabel.setFont(new Font("Segoe UI", Font.ITALIC, 18));
		miniLabel.setBounds(726, 0, 22, 24);
		panel.add(miniLabel);
		
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
		exitLabel.setBounds(758, 0, 22, 24);
		panel.add(exitLabel);
		
		JLabel backLabel = new JLabel("< Back");
		backLabel.setBounds(10, 487, 65, 22);
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
			public void mouseClicked(MouseEvent e) {
				AdminDashBoard dash = null;
				try {
					dash = new AdminDashBoard();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				 dash.setVisible(true);
				 
				 dash.setLocationRelativeTo(null);
				 JComponent comp = (JComponent) e.getSource();
				  Window win = SwingUtilities.getWindowAncestor(comp);
				  win.dispose();
			}
		});
		backLabel.setForeground(Color.BLUE);
		backLabel.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 15));
		contentPane.add(backLabel);
		
		JLabel updateLabel = new JLabel("User Options");
		updateLabel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		updateLabel.setBackground(Color.BLUE);
		updateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		updateLabel.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 13));
		updateLabel.setBounds(626, 114, 128, 44);
		contentPane.add(updateLabel);
		
		JButton updateButton = new JButton("Update");
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String id, lname, fname, pass, uname;
					conn = Connect.getConnection();
				
				Statement st = conn.createStatement();
				String query = "select Employee_ID,last_name,first_name,Pass_code,User_Name from login_page;";
				
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
									id = rs.getString(1);
									lname = rs.getString(2);
									fname = rs.getString(3);
									pass = rs.getString(4);
									uname = rs.getString(5);
									String [] row = {id,lname,fname,pass,uname};
									model.addRow(row);
								}
						}
				}
				catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		updateButton.setFont(new Font("Tahoma", Font.ITALIC, 15));
		updateButton.setBounds(282, 443, 89, 23);
		contentPane.add(updateButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 87, 606, 345);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 606, 345);
		panel_1.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JRadioButton registerRB = new JRadioButton("Register Users");
		registerRB.setBackground(Color.LIGHT_GRAY);
		buttonGroup_1.add(registerRB);
		registerRB.setFont(new Font("Segoe UI", Font.ITALIC, 13));
		registerRB.setBounds(633, 187, 110, 25);
		contentPane.add(registerRB);
		
		JRadioButton deleteRB = new JRadioButton("Delete User");
		deleteRB.setBackground(Color.LIGHT_GRAY);
		buttonGroup_1.add(deleteRB);
		deleteRB.setFont(new Font("Segoe UI", Font.ITALIC, 13));
		deleteRB.setBounds(633, 231, 109, 23);
		contentPane.add(deleteRB);
		
		JRadioButton upRb = new JRadioButton("Update User");
		upRb.setBackground(Color.LIGHT_GRAY);
		buttonGroup_1.add(upRb);
		upRb.setFont(new Font("Segoe UI", Font.ITALIC, 13));
		upRb.setBounds(633, 273, 109, 23);
		contentPane.add(upRb);
		
		JButton goButton = new JButton("GO");
		goButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(registerRB.isSelected()) {
					Register reg = new Register();
					reg.setVisible(true);
					reg.setLocationRelativeTo(null);
					
					JComponent comp = (JComponent) e.getSource();
					Window win = SwingUtilities.getWindowAncestor(comp);
					win.dispose();
				}
				else if(deleteRB.isSelected()) {
					DeleteUser del = new DeleteUser();
					del.setVisible(true);
					del.setLocationRelativeTo(null);
					
					JComponent comp = (JComponent) e.getSource();
					Window win = SwingUtilities.getWindowAncestor(comp);
					win.dispose();
					
				}
				else if(upRb.isSelected()) {
					UpdateUser up = new UpdateUser();
					up.setVisible(true);
					up.setLocationRelativeTo(null);
					
					JComponent comp = (JComponent) e.getSource();
					Window win = SwingUtilities.getWindowAncestor(comp);
					win.dispose();
					
				}
			}
		});
		buttonGroup_1.add(goButton);
		goButton.setFont(new Font("Segoe UI", Font.BOLD, 15));
		goButton.setBounds(640, 318, 89, 23);
		contentPane.add(goButton);
	}

}

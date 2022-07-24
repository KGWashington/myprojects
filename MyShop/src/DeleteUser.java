import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class DeleteUser extends JFrame {

	private JPanel contentPane;
	private JTextField eidtextField;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteUser frame = new DeleteUser();
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
	public DeleteUser() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
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
		exitLabel.setBounds(478, 0, 22, 24);
		contentPane.add(exitLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Enter Employee ID");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(16, 300, 459, 38);
		contentPane.add(lblNewLabel_1);
		
		eidtextField = new JTextField();
		eidtextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(!(Character.isDigit(c)|| (c == KeyEvent.VK_BACK_SPACE)|| c == KeyEvent.VK_DELETE)) {
					e.consume();
				}
			}
		});
		
		JLabel lblNewLabel = new JLabel("Delete User");
		lblNewLabel.setBorder(new LineBorder(Color.BLUE, 2));
		lblNewLabel.setVerticalAlignment(SwingConstants.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 25));
		lblNewLabel.setBounds(0, 0, 500, 50);
		contentPane.add(lblNewLabel);
		eidtextField.setHorizontalAlignment(SwingConstants.CENTER);
		eidtextField.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		eidtextField.setBounds(118, 349, 273, 25);
		contentPane.add(eidtextField);
		eidtextField.setColumns(10);
		
		JButton clearButton = new JButton("Clear");
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.setModel(new DefaultTableModel());
				eidtextField.setText("");
				
			}
		});
		clearButton.setFont(new Font("Segoe UI", Font.ITALIC, 15));
		clearButton.setBounds(401, 466, 89, 23);
		contentPane.add(clearButton);
		
		JButton deleteButton = new JButton("Delete");
		deleteButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = Connect.getConnection();
					
					String query = "delete from login_page where Employee_ID = ?;";
					PreparedStatement st = con.prepareStatement(query);
					st.setInt(1, Integer.parseInt(eidtextField.getText()));
					st.executeUpdate();
				    
					JOptionPane.showMessageDialog(deleteButton, "User has been Deleted.");
					table.setModel(new DefaultTableModel());
					eidtextField.setText("");
					
				} catch (SQLException |ClassNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				
			}
		});
		
		deleteButton.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 15));
		deleteButton.setBounds(302, 466, 89, 23);
		contentPane.add(deleteButton);
		
		JLabel backLabel = new JLabel("< Back");
		backLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				backLabel.setForeground(Color.black);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				backLabel.setForeground(Color.blue);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
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
		backLabel.setBounds(10, 469, 69, 21);
		contentPane.add(backLabel);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String id, lname, fname, pass, uname;
					Class.forName("com.mysql.cj.jdbc.Driver");
					
					Connection con = Connect.getConnection();
					String query = "select * from login_page";
					Statement st = con.createStatement();
					
					
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
						fname = rs.getString(2);
						lname = rs.getString(3);
						pass = rs.getString(4);
						uname = rs.getString(5);
						String [] row = {id,fname,lname,pass,uname};
						model.addRow(row);
								}
						}
					st.close();
					con.close();
					} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Segoe UI", Font.ITALIC, 15));
		btnNewButton.setBounds(197, 266, 89, 23);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(16, 73, 457, 182);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}

}

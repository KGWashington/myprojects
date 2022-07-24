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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class DeletePro extends JFrame {

	private JPanel contentPane;
	private JTextField prdtextField;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeletePro frame = new DeletePro();
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
	public DeletePro() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new LineBorder(Color.BLUE));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
		
		JLabel lblDeleteProductFrom = new JLabel("Delete Product from Inventory");
		lblDeleteProductFrom.setVerticalAlignment(SwingConstants.CENTER);
		lblDeleteProductFrom.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeleteProductFrom.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 25));
		lblDeleteProductFrom.setBorder(new LineBorder(Color.BLUE, 2));
		lblDeleteProductFrom.setBounds(0, 0, 500, 50);
		contentPane.add(lblDeleteProductFrom);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 61, 457, 182);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton searchButton = new JButton("Search");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String pr, stock, price, par;
					Class.forName("com.mysql.cj.jdbc.Driver");
					
					Connection con = Connect.getConnection();
					String query = "select * from stock";
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
						pr = rs.getString(1);
						stock = rs.getString(2);
						price = rs.getString(3);
						par= rs.getString(4);
						
						String [] row = {pr,stock,price,par};
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
		searchButton.setFont(new Font("Segoe UI", Font.ITALIC, 15));
		searchButton.setBounds(208, 253, 89, 23);
		contentPane.add(searchButton);
		
		JLabel lblNewLabel_1 = new JLabel("Enter Product by Name");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_1.setBounds(21, 299, 459, 38);
		contentPane.add(lblNewLabel_1);
		
		prdtextField = new JTextField();
		prdtextField.setHorizontalAlignment(SwingConstants.CENTER);
		prdtextField.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		prdtextField.setColumns(10);
		prdtextField.setBounds(114, 349, 285, 25);
		contentPane.add(prdtextField);
		
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
				InvenPage page;
				try {
					page = new InvenPage();
				
				 page.setVisible(true);
				 
				 page.setLocationRelativeTo(null);
				 JComponent comp = (JComponent) e.getSource();
				  Window win = SwingUtilities.getWindowAncestor(comp);
				  win.dispose();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
					});
		backLabel.setForeground(Color.BLUE);
		backLabel.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 13));
		backLabel.setBounds(10, 468, 69, 21);
		contentPane.add(backLabel);
		
		JButton deleteButton = new JButton("Delete");
		deleteButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = Connect.getConnection();
					
					String query = "delete from stock where Product = ?;";
					PreparedStatement st = con.prepareStatement(query);
					st.setString(1, prdtextField.getText() );
					st.executeUpdate();
				    
					JOptionPane.showMessageDialog(deleteButton, prdtextField.getText().toUpperCase() +" has been deleted from the inventory.");
					table.setModel(new DefaultTableModel());
					prdtextField.setText("");
					
				} catch (SQLException |ClassNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				
			}
		});
		deleteButton.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 15));
		deleteButton.setBounds(297, 465, 89, 23);
		contentPane.add(deleteButton);
		
		JButton clearButton = new JButton("Clear");
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.setModel(new DefaultTableModel());
				prdtextField.setText("");
				
			}
		});
		clearButton.setFont(new Font("Segoe UI", Font.ITALIC, 15));
		clearButton.setBounds(401, 465, 89, 23);
		contentPane.add(clearButton);
		this.setLocationRelativeTo(null);
	}
}

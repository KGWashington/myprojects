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

public class UpdatePro extends JFrame {

	private JPanel contentPane;
	private JTextField producttextField;
	private JTextField updateTF;
	private JTable table;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdatePro frame = new UpdatePro();
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
	public UpdatePro() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 600);
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
			public void mousePressed(MouseEvent e) {
				System.exit(EXIT_ON_CLOSE);
			}
		});
		exitLabel.setHorizontalAlignment(SwingConstants.CENTER);
		exitLabel.setFont(new Font("Segoe UI", Font.ITALIC, 18));
		exitLabel.setBounds(528, 0, 22, 24);
		contentPane.add(exitLabel);
		
		JLabel lblNewLabel = new JLabel("Update User Information");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 25));
		lblNewLabel.setBorder(new LineBorder(Color.BLUE, 2));
		lblNewLabel.setBounds(0, 0, 550, 60);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 71, 530, 136);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton uptabButton = new JButton("Update Table");
		uptabButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					String product, stock, price, par;
					Class.forName("com.mysql.cj.jdbc.Driver");
					
					Connection con = Connect.getConnection();
					Statement st = con.createStatement();
					String query = "select * from stock;";
					
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
						product = rs.getString(1);
						stock = rs.getString(2);
						price = rs.getString(3);
						par = rs.getString(4);
			
						String [] row = {product,stock,price,par};
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
		uptabButton.setBounds(208, 218, 131, 23);
		contentPane.add(uptabButton);
		
		JLabel lblNewLabel_1 = new JLabel("Enter a Product to Update ");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_1.setBounds(56, 252, 430, 25);
		contentPane.add(lblNewLabel_1);
		
		producttextField = new JTextField();
		producttextField.setHorizontalAlignment(SwingConstants.CENTER);
		producttextField.setFont(new Font("Segoe UI", Font.ITALIC, 13));
		producttextField.setColumns(10);
		producttextField.setBounds(56, 288, 420, 30);
		contentPane.add(producttextField);
		
		JLabel lblNewLabel_3 = new JLabel("Select an Item to Update");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_3.setBounds(46, 329, 430, 25);
		contentPane.add(lblNewLabel_3);
		
		JRadioButton proRB = new JRadioButton("Product Name");
		buttonGroup.add(proRB);
		proRB.setFont(new Font("Segoe UI", Font.ITALIC, 13));
		proRB.setBounds(56, 361, 109, 23);
		contentPane.add(proRB);
		
		JRadioButton rdbtnPrice = new JRadioButton("Price");
		buttonGroup.add(rdbtnPrice);
		rdbtnPrice.setFont(new Font("Segoe UI", Font.ITALIC, 13));
		rdbtnPrice.setBounds(128, 401, 109, 23);
		contentPane.add(rdbtnPrice);
		
		JRadioButton rdbtnStockAmount = new JRadioButton("Stock Amount");
		rdbtnStockAmount.setFont(new Font("Segoe UI", Font.ITALIC, 13));
		buttonGroup.add(rdbtnStockAmount);
		proRB.setFont(new Font("Segoe UI", Font.ITALIC, 13));
		rdbtnStockAmount.setBounds(367, 361, 109, 23);
		contentPane.add(rdbtnStockAmount);
		
		JRadioButton rdbtnParAmount = new JRadioButton("Par Amount");
		buttonGroup.add(rdbtnParAmount);
		rdbtnParAmount.setFont(new Font("Segoe UI", Font.ITALIC, 13));
		rdbtnParAmount.setBounds(331, 401, 109, 23);
		contentPane.add(rdbtnParAmount);
		
		JLabel lblNewLabel_2 = new JLabel("Enter new Information");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_2.setBounds(56, 441, 430, 25);
		contentPane.add(lblNewLabel_2);
		
		updateTF = new JTextField();
		updateTF.setHorizontalAlignment(SwingConstants.CENTER);
		updateTF.setFont(new Font("Segoe UI", Font.ITALIC, 13));
		updateTF.setColumns(10);
		updateTF.setBounds(76, 477, 400, 30);
		contentPane.add(updateTF);
		
		JButton upButton = new JButton("Update");
		upButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
try {
					
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/myshop","root","Champ0311");
					
					 if(proRB.isSelected()) {
						 
						 
						String query = "update stock set Product = ? where Product = ? ";
						PreparedStatement st = con.prepareStatement(query);
						st.setString(1, updateTF.getText());
						st.setString(2, producttextField.getText().trim().toLowerCase());
						st.executeUpdate();

						int i = st.executeUpdate();
						JOptionPane.showMessageDialog( upButton, producttextField.getText().trim().toUpperCase()+" product name has been changed to "+ updateTF.getText().trim().toUpperCase());
						st.close();
						con.close();
					 						}
					 else if(rdbtnStockAmount.isSelected()) {
						 
						String query = "update stock set Stock = ? where Product = ? ";
						PreparedStatement st = con.prepareStatement(query);
						st.setInt(1,Integer.parseInt(updateTF.getText()) );
						st.setString(2, producttextField.getText().trim().toLowerCase());
						st.executeUpdate();

						int i = st.executeUpdate();
						JOptionPane.showMessageDialog( upButton, producttextField.getText().trim().toUpperCase()+"'s stock amount has been updated ");
						st.close();
						con.close();
					 						}
					 else if(rdbtnPrice.isSelected()) {
						 
						String query = "update stock set Price = ? where Product = ? ";
						PreparedStatement st = con.prepareStatement(query);
						st.setDouble(1,Double.parseDouble(updateTF.getText()));
						st.setString(2, producttextField.getText().trim().toLowerCase());
						st.executeUpdate();

						int i = st.executeUpdate();
						JOptionPane.showMessageDialog( upButton, producttextField.getText().trim().toUpperCase()+"'s price has been updated");
						st.close();
						con.close();
					 						}
					 else if(rdbtnParAmount.isSelected()) {
						 
						String query = "update stock set Par = ? where Product = ? ";
						PreparedStatement st = con.prepareStatement(query);
						st.setInt(1,Integer.parseInt(updateTF.getText()));
						st.setString(2, producttextField.getText().trim().toLowerCase());
						st.executeUpdate();

						int i = st.executeUpdate();
						JOptionPane.showMessageDialog( upButton, producttextField.getText().trim().toUpperCase()+"'s par amount has been updated");
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
		upButton.setBounds(329, 566, 89, 23);
		contentPane.add(upButton);
		
		JButton clearButton = new JButton("Clear");
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				updateTF.setText("");
				producttextField.setText("");
				
			}
		});
		clearButton.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		clearButton.setBounds(451, 566, 89, 23);
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
		backLabel.setBounds(10, 567, 69, 21);
		contentPane.add(backLabel);
	}
}

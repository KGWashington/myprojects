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
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class InvenPage extends JFrame {

	private JPanel contentPane;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InvenPage frame = new InvenPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection conn = null;
	private JTable table;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	/**
	 * Create the frame.
	 * @throws ClassNotFoundException 
	 */
	public InvenPage() throws ClassNotFoundException {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 780, 520);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new LineBorder(Color.BLUE));
		setContentPane(contentPane);
		this.setLocationRelativeTo(null);
		
		
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 780, 65);
		panel.setBackground(Color.BLUE);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("MyShop Inventory");
		lblNewLabel.setBounds(10, 11, 345, 40);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 38));
		panel.add(lblNewLabel);
		
		JLabel minLabel = new JLabel("-");
		minLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				minLabel.setForeground(Color.white);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				minLabel.setForeground(Color.black);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				setState(JFrame.ICONIFIED);  
			}
		});
		minLabel.setFont(new Font("Segoe UI", Font.ITALIC, 18));
		minLabel.setHorizontalAlignment(SwingConstants.CENTER);
		minLabel.setBounds(720, 0, 22, 24);
		panel.add(minLabel);
		
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
		exitLabel.setBorder(null);
		exitLabel.setFont(new Font("Segoe UI", Font.ITALIC, 18));
		exitLabel.setHorizontalAlignment(SwingConstants.CENTER);
		exitLabel.setBounds(744, 0, 22, 24);
		panel.add(exitLabel);
		
		JLabel bckLabel = new JLabel("< Back");
		bckLabel.setBounds(10, 487, 65, 22);
		bckLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				bckLabel.setForeground(Color.black);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				bckLabel.setForeground(Color.BLUE);
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
		bckLabel.setForeground(Color.BLUE);
		bckLabel.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 15));
		contentPane.add(bckLabel);
		
		
		
		JLabel lblNewLabel_4 = new JLabel("Inventory Options");
		lblNewLabel_4.setBounds(629, 127, 128, 44);
		lblNewLabel_4.setBackground(Color.BLUE);
		lblNewLabel_4.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 13));
		contentPane.add(lblNewLabel_4);
		
		JButton updateButton = new JButton("Update");
		updateButton.setBounds(268, 419, 89, 23);
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					String pro, stock, price, par;
					conn = Connect.getConnection();
				
				Statement st = conn.createStatement();
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
									pro = rs.getString(1);
									stock = rs.getString(2);
									price = rs.getString(3);
									par = rs.getString(4);
									String [] row = {pro, stock,price,par};
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
		updateButton.setFont(new Font("Segoe UI", Font.ITALIC, 15));
		contentPane.add(updateButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 86, 609, 330);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JRadioButton addRB = new JRadioButton("Add Products");
		addRB.setFont(new Font("Segoe UI", Font.ITALIC, 13));
		buttonGroup.add(addRB);
		addRB.setBounds(629, 178, 128, 23);
		contentPane.add(addRB);
		
		JRadioButton deleteRB = new JRadioButton("Delete Products");
		deleteRB.setFont(new Font("Segoe UI", Font.ITALIC, 13));
		buttonGroup.add(deleteRB);
		deleteRB.setBounds(629, 214, 128, 23);
		contentPane.add(deleteRB);
		
		JRadioButton updateRB = new JRadioButton("Update Products");
		updateRB.setFont(new Font("Segoe UI", Font.ITALIC, 13));
		buttonGroup.add(updateRB);
		updateRB.setBounds(629, 248, 128, 23);
		contentPane.add(updateRB);
		
		JButton gotoButton = new JButton("Go to...");
		gotoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(addRB.isSelected()== true) {
					AddProducts add;
					add = new AddProducts();
					add.setVisible(true);
					add.setLocationRelativeTo(null);
					
					JComponent comp = (JComponent) e.getSource();
					Window win = SwingUtilities.getWindowAncestor(comp);
					win.dispose();
				}
				else if(deleteRB.isSelected()==true) {
					DeletePro delete;
					delete = new DeletePro();
					delete.setVisible(true);
					delete.setLocationRelativeTo(null);
					
					JComponent comp = (JComponent) e.getSource();
					Window win = SwingUtilities.getWindowAncestor(comp);
					win.dispose();
					
				}
				else if(updateRB.isSelected()==true) {
					UpdatePro up;
					up = new UpdatePro();
					up.setVisible(true);
					up.setLocationRelativeTo(null);
					
					JComponent comp = (JComponent) e.getSource();
					Window win = SwingUtilities.getWindowAncestor(comp);
					win.dispose();
					
				}
				
			}
		});
		gotoButton.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 13));
		buttonGroup.add(gotoButton);
		gotoButton.setBounds(650, 298, 89, 23);
		contentPane.add(gotoButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 86, 609, 322);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}
}


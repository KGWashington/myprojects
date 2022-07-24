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
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

public class AddProducts extends JFrame {

	private JPanel contentPane;
	private JTextField parTF;
	private JTextField prceTF;
	private JTextField stkTF;
	private JTextField prdTF;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddProducts frame = new AddProducts();
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
	public AddProducts() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 440);
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
		exitLabel.setBounds(528, 0, 22, 24);
		contentPane.add(exitLabel);
		
		JLabel lblNewLabel = new JLabel("Add New Products");
		lblNewLabel.setBorder(new LineBorder(Color.BLUE, 2));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel.setBounds(0, 0, 550, 44);
		contentPane.add(lblNewLabel);
		
		JLabel prdLabel = new JLabel(" Product Name");
		prdLabel.setHorizontalAlignment(SwingConstants.CENTER);
		prdLabel.setFont(new Font("Segoe UI", Font.ITALIC, 13));
		prdLabel.setBounds(10, 71, 101, 30);
		contentPane.add(prdLabel);
		
		JLabel stkLabel = new JLabel("Initial Stock");
		stkLabel.setHorizontalAlignment(SwingConstants.CENTER);
		stkLabel.setFont(new Font("Segoe UI", Font.ITALIC, 13));
		stkLabel.setBounds(10, 142, 101, 30);
		contentPane.add(stkLabel);
		
		JLabel prceLabel = new JLabel("Initial Price");
		prceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		prceLabel.setFont(new Font("Segoe UI", Font.ITALIC, 13));
		prceLabel.setBounds(10, 211, 101, 30);
		contentPane.add(prceLabel);
		
		JLabel parLabel = new JLabel("Initial Par");
		parLabel.setHorizontalAlignment(SwingConstants.CENTER);
		parLabel.setFont(new Font("Segoe UI", Font.ITALIC, 13));
		parLabel.setBounds(10, 281, 101, 30);
		contentPane.add(parLabel);
		
		parTF = new JTextField();
		parTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(!(Character.isDigit(c)|| (c == KeyEvent.VK_BACK_SPACE)|| c == KeyEvent.VK_DELETE)) {
					e.consume();
				}
			}
		});
		parTF.setBounds(135, 285, 270, 25);
		contentPane.add(parTF);
		parTF.setColumns(10);
		
		prceTF = new JTextField();
		prceTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(!(Character.isDigit(c)|| (c == KeyEvent.VK_BACK_SPACE)|| (c == KeyEvent.VK_DELETE)|| c == KeyEvent.VK_PERIOD)) {
					e.consume();
				}
			}
		});
		prceTF.setBounds(135, 215, 270, 25);
		contentPane.add(prceTF);
		prceTF.setColumns(10);
		
		stkTF = new JTextField();
		stkTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(!(Character.isDigit(c)|| (c == KeyEvent.VK_BACK_SPACE)|| c == KeyEvent.VK_DELETE)) {
					e.consume();
				}
			}
		});
		stkTF.setBounds(135, 146, 270, 25);
		contentPane.add(stkTF);
		stkTF.setColumns(10);
		
		prdTF = new JTextField();
		prdTF.setBounds(135, 75, 270, 25);
		contentPane.add(prdTF);
		prdTF.setColumns(10);
		
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
		backLabel.setBounds(10, 408, 69, 21);
		contentPane.add(backLabel);
		
		JButton addButton = new JButton("Add Product");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/myshop","root","Champ0311");
					
					
					String query = "insert into stock values(?,?,?,?)";
					PreparedStatement st = con.prepareStatement(query);
					
					st.setString(1, prdTF.getText());
					st.setInt(2, Integer.parseInt(stkTF.getText()));
					st.setDouble(3, Double.parseDouble(prceTF.getText()));
					st.setInt(4, Integer.parseInt(parTF.getText()));
					
					int i = st.executeUpdate();
					JOptionPane.showMessageDialog(addButton, i+" Product has been added to the system");
					st.close();
					con.close();
					
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}
		});
		addButton.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 15));
		addButton.setBounds(254, 395, 130, 23);
		contentPane.add(addButton);
		
		JButton resetButton = new JButton("Reset");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prdTF.setText("");
				stkTF.setText("");
				prceTF.setText("");
				parTF.setText("");
			}
		});
		resetButton.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 15));
		resetButton.setBounds(410, 395, 130, 23);
		contentPane.add(resetButton);
	}
}

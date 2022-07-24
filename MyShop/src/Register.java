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

public class Register extends JFrame {

	private JPanel contentPane;
	private JTextField fnametextField;
	private JTextField lnametextField;
	private JTextField pctextField;
	private JTextField unametextField;
	private JTextField eidtextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register frame = new Register();
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
	public Register() {
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
		
		JLabel fnameLabel = new JLabel("First Name");
		fnameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		fnameLabel.setFont(new Font("Segoe UI", Font.ITALIC, 13));
		fnameLabel.setBounds(11, 60, 75, 30);
		contentPane.add(fnameLabel);
		
		fnametextField = new JTextField();
		fnametextField.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		fnametextField.setBounds(106, 60, 375, 25);
		contentPane.add(fnametextField);
		fnametextField.setColumns(10);
		
		JLabel lnameLabel = new JLabel("Last Name");
		lnameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lnameLabel.setFont(new Font("Segoe UI", Font.ITALIC, 13));
		lnameLabel.setBounds(11, 120, 75, 30);
		contentPane.add(lnameLabel);
		
		lnametextField = new JTextField();
		lnametextField.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lnametextField.setBounds(106, 120, 375, 25);
		contentPane.add(lnametextField);
		lnametextField.setColumns(10);
		
		JLabel pcLabel = new JLabel("Pass Code");
		pcLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pcLabel.setFont(new Font("Segoe UI", Font.ITALIC, 13));
		pcLabel.setBounds(11, 180, 75, 30);
		contentPane.add(pcLabel);
		
		pctextField = new JTextField();
		pctextField.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		pctextField.setBounds(106, 180, 375, 25);
		contentPane.add(pctextField);
		pctextField.setColumns(10);
		
		JLabel unameLabel = new JLabel("User Name");
		unameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		unameLabel.setFont(new Font("Segoe UI", Font.ITALIC, 13));
		unameLabel.setBounds(11, 240, 75, 30);
		contentPane.add(unameLabel);
		
		unametextField = new JTextField();
		unametextField.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		unametextField.setBounds(106, 240, 375, 25);
		contentPane.add(unametextField);
		unametextField.setColumns(10);
		
		JLabel eidLabel = new JLabel("Employee ID");
		eidLabel.setHorizontalAlignment(SwingConstants.CENTER);
		eidLabel.setFont(new Font("Segoe UI", Font.ITALIC, 13));
		eidLabel.setBounds(11, 300, 75, 30);
		contentPane.add(eidLabel);
		
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
		eidtextField.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		eidtextField.setBounds(106, 300, 375, 25);
		contentPane.add(eidtextField);
		eidtextField.setColumns(10);
		
		JButton btnNewButton = new JButton("Register");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/myshop","root","Champ0311");
					
					
					String query = "insert into login_page values(?,?,?,?,?)";
					PreparedStatement st = con.prepareStatement(query);
					
					st.setString(3, fnametextField.getText());
					st.setString(2, lnametextField.getText());
					st.setString(4, pctextField.getText());
					st.setString(5, unametextField.getText());
					st.setInt(1, Integer.parseInt(eidtextField.getText()));
					
					int i = st.executeUpdate();
					JOptionPane.showMessageDialog(btnNewButton, i+"User has been added to the system");
					st.close();
					con.close();
					
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}
		});
		btnNewButton.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 15));
		btnNewButton.setBounds(256, 405, 130, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Reset");
		btnNewButton_1.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 15));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fnametextField.setText("");
				lnametextField.setText("");
				unametextField.setText("");
				pctextField.setText("");
				eidtextField.setText("");
			}
		});
		btnNewButton_1.setBounds(404, 405, 130, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("New User Registration");
		lblNewLabel.setBorder(new LineBorder(Color.BLUE, 2));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel.setBounds(0, 0, 550, 44);
		contentPane.add(lblNewLabel);
		
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
		backLabel.setBounds(11, 408, 69, 21);
		contentPane.add(backLabel);
	}

}

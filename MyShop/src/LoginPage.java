import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;

public class LoginPage extends JFrame {

	ImageIcon key = new ImageIcon("key.png");
	ImageIcon user = new ImageIcon("user.png");
	private JPanel contentPane;
	private JTextField user_name;
	private JPasswordField passWord;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage frame = new LoginPage();
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
	public LoginPage() {
		setUndecorated(true);
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 521, 420);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel_1.setBackground(new Color(0, 0, 255));
		panel_1.setBounds(10, 11, 501, 100);
		contentPane.add(panel_1);
		
		JLabel lblNewLabel_1 = new JLabel("LOGIN");
		lblNewLabel_1.setBounds(136, 11, 265, 78);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 33));
		
		JLabel mini_label = new JLabel("-");
		mini_label.setBounds(446, 3, 23, 25);
		mini_label.setBorder(null);
		mini_label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				mini_label.setForeground(Color.white);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				mini_label.setForeground(Color.BLACK);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				
				setState(JFrame.ICONIFIED);
				
			}
		});
		mini_label.setHorizontalAlignment(SwingConstants.CENTER);
		mini_label.setFont(new Font("Segoe UI", Font.ITALIC, 18));
		
		JLabel exit_label = new JLabel("X");
		exit_label.setBounds(475, 2, 22, 27);
		exit_label.setBorder(null);
		exit_label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exit_label.setForeground(Color.white);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				exit_label.setForeground(Color.BLACK);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(EXIT_ON_CLOSE);
			}
		});
		exit_label.setFont(new Font("Segoe UI", Font.ITALIC, 18));
		exit_label.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.setLayout(null);
		panel_1.add(lblNewLabel_1);
		panel_1.add(mini_label);
		panel_1.add(exit_label);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(395, 265, -145, -119);
		contentPane.add(layeredPane);
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(Color.BLUE);
		lblNewLabel.setBounds(70, 150, 45, 45);
		lblNewLabel.setIcon(user);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel();
		lblNewLabel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBackground(Color.BLUE);
		lblNewLabel_2.setOpaque(true);
		lblNewLabel_2.setBounds(70, 210, 45, 45);
		lblNewLabel_2.setIcon(key);
		contentPane.add(lblNewLabel_2);
		
		user_name = new JTextField();
		user_name.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		user_name.setForeground(Color.GRAY);
		user_name.addFocusListener(new FocusAdapter() {
			@Override
			// removes text when focus is gained for Id#
			public void focusGained(FocusEvent e) {
				if(user_name.getText().trim().toLowerCase().equals("employee user name")) {
					user_name.setText("");
					user_name.setForeground(Color.black);
				}
			}
			@Override
			// returns text to textfield
			public void focusLost(FocusEvent e) {
				if(user_name.getText().trim().equals("")||
						user_name.getText().trim().toLowerCase().equals("employee user name")) {
					user_name.setText("Employee User Name");
					user_name.setForeground(Color.gray);
				}
				
			}
		});
		user_name.setHorizontalAlignment(SwingConstants.CENTER);
		
		user_name.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		user_name.setText("Employee User Name");
		user_name.setBounds(137, 150, 330, 45);
		contentPane.add(user_name);
		user_name.setColumns(10);
		
		passWord = new JPasswordField();
		passWord.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		passWord.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		passWord.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				
				String pass = String.valueOf(passWord.getPassword());
				if(pass.trim().toLowerCase().equals("password")) {
					passWord.setText("");
					passWord.setForeground(Color.black);
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				String pass = String.valueOf(passWord.getPassword());
				if(pass.trim().equals("")||
						pass.trim().toLowerCase().equals("password")) {
					passWord.setText("password");
					passWord.setForeground(Color.gray);
				}
			}
		});
		passWord.setForeground(Color.GRAY);
		passWord.setText("password");
		
		
		passWord.setHorizontalAlignment(SwingConstants.CENTER);
		
		passWord.setBounds(137, 210, 330, 45);
		contentPane.add(passWord);
		
		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			PreparedStatement st;
			ResultSet rs;
			
			public void actionPerformed(ActionEvent e) {
				
				
				
				String username = user_name.getText();
				String pass = String.valueOf(passWord.getPassword());
			
				String query = "select * from login_page where User_Name = ? and Pass_code = ? ";
				

					try {
						st = Connect.getConnection().prepareStatement(query);
						
						st.setString(1, username);
						st.setString(2, pass);
						
						rs = st.executeQuery();
						

						if (rs.next()) {
							 AdminDashBoard dash = new AdminDashBoard();
							 dash.setVisible(true);
							 
							 dash.setLocationRelativeTo(null);
							 JComponent comp = (JComponent) e.getSource();
							  Window win = SwingUtilities.getWindowAncestor(comp);
							  win.dispose();
										}
						else {
							JOptionPane.showMessageDialog(null,"Invalid User Name or Password","Login Error",2);
							 }
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		
				} 
				
				
			
		});
		loginButton.setForeground(Color.BLACK);
		loginButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				loginButton.setBackground(Color.cyan);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				loginButton.setBackground(Color.blue);
			}
		});
		loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		loginButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		loginButton.setBackground(Color.BLUE);
		loginButton.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		loginButton.setBounds(95, 307, 330, 56);
		contentPane.add(loginButton);
	}
}

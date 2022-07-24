import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class RegisterU extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField fntextField;
	private JTextField lntextField;
	private JTextField pctextField;
	private JTextField untextField;
	private JTextField eidtextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegisterU dialog = new RegisterU();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	Connection con = null;
	public RegisterU() {
		setTitle("Register New User");
		setBounds(100, 100, 450, 350);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.LIGHT_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		this.setLocationRelativeTo(null);
		{
			JLabel fnameLabel = new JLabel("First Name");
			fnameLabel.setBounds(18, 12, 61, 18);
			fnameLabel.setFont(new Font("Segoe UI", Font.ITALIC, 13));
			contentPanel.add(fnameLabel);
		}
		{
			fntextField = new JTextField();
			fntextField.setBounds(85, 11, 344, 20);
			contentPanel.add(fntextField);
			fntextField.setColumns(10);
		}
		{
			JLabel lnameLabel_1 = new JLabel("Last Name");
			lnameLabel_1.setBounds(19, 63, 60, 18);
			lnameLabel_1.setFont(new Font("Segoe UI", Font.ITALIC, 13));
			contentPanel.add(lnameLabel_1);
		}
		{
			lntextField = new JTextField();
			lntextField.setBounds(85, 62, 344, 20);
			contentPanel.add(lntextField);
			lntextField.setColumns(10);
		}
		{
			JLabel pcLabel = new JLabel("Passcode");
			pcLabel.setBounds(29, 114, 50, 18);
			pcLabel.setFont(new Font("Segoe UI", Font.ITALIC, 13));
			contentPanel.add(pcLabel);
		}
		{
			pctextField = new JTextField();
			pctextField.setBounds(85, 113, 344, 20);
			contentPanel.add(pctextField);
			pctextField.setColumns(10);
		}
		{
			JLabel unameLabel = new JLabel("User Name");
			unameLabel.setBounds(16, 165, 63, 18);
			unameLabel.setFont(new Font("Segoe UI", Font.ITALIC, 13));
			contentPanel.add(unameLabel);
		}
		{
			untextField = new JTextField();
			untextField.setBounds(85, 164, 344, 20);
			contentPanel.add(untextField);
			untextField.setColumns(10);
		}
		{
			JLabel eidLabel = new JLabel("Employee ID");
			eidLabel.setBounds(11, 216, 68, 18);
			eidLabel.setBorder(null);
			eidLabel.setFont(new Font("Segoe UI", Font.ITALIC, 13));
			contentPanel.add(eidLabel);
		}
		{
			eidtextField = new JTextField();
			eidtextField.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					char c = e.getKeyChar();
					if (!(Character.isDigit(c)|| (c==KeyEvent.VK_BACK_SPACE)||(c==KeyEvent.VK_DELETE))) {
						e.consume();
					}
				}
			});
			eidtextField.setBounds(85, 215, 344, 20);
			contentPanel.add(eidtextField);
			eidtextField.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.BLUE);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton saveButton = new JButton("Save");
				saveButton.addActionListener(new ActionListener() {
					
					PreparedStatement st;
					ResultSet rs;
					
					public void actionPerformed(ActionEvent e) {
						try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						
						
						con = Connect.getConnection();
						
						
							String fName = fntextField.getText().toString();
							String lName = lntextField.getText().toString();
							String pc = pctextField.getText().toString();
							String uName = untextField.getText().toString();
							
							String eid = eidtextField.getText().toString();
							int idnum = Integer.parseInt(eid);
							
							String query = "insert into login_page (Employee_ID, last_name, first_name, Pass_code, User_Name) values (?,?,?,?,?);";

							st.setInt(1,idnum);
							st.setString(2, lName);
							st.setString(3, fName);
							st.setString(4, pc);
							st.setString(5, uName);
							st = con.prepareStatement(query);
							st.executeUpdate(query);
							
						
							
							
							
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}

					private void saveUser() throws SQLException {
						
						
					}
				});
				saveButton.setFont(new Font("Segoe UI", Font.ITALIC, 11));
				saveButton.setActionCommand("OK");
				buttonPane.add(saveButton);
				getRootPane().setDefaultButton(saveButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setFont(new Font("Segoe UI", Font.ITALIC, 11));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		
			
		}
		

		
	}

}

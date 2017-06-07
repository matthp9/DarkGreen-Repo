/**
 * Matthew Allen Phillips
 * 11 April 2017
 * GUI.java
 */

package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import model.GraphicsUtils;
import model.UserProfile;

/**
 * A graphical panel to display to the user when 
 * they arrive in the application. Requires a
 * name and email (similar to a login page).
 * 
 * Student's Note: This correlates to Professor
 * Weiss' lecture on the "AppContainer".
 * 
 * @author Matt Phillips
 * @version 11 April 2017
 */
public class SetupPanel extends DIYPanel {

	/**
	 * Serial version ID.
	 */
	private static final long serialVersionUID = 4109522843927843411L;

	private String myMessageToUser;

	/**
	 * Constructs and returns a new SetupPanel.
	 */
	public SetupPanel(final GUI theGUI) {
		super(theGUI);
		createDirectories();
		myMessageToUser = "";
		init();
	}

	/**
	 * Initializer helper method for the panel.
	 */
	private void init() {

		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);

		JLabel emailLabel = new JLabel("Email");
		JTextField email  = new JTextField();
		JLabel passLabel = new JLabel("Password");
		JPasswordField pass = new JPasswordField();
		JButton login   = new JButton("Log In");
		JButton create   = new JButton("Create Account");

		email.setPreferredSize(new Dimension(200, 30));
		email.setFont(DIYPanel.MSG_FONT);
		pass.setPreferredSize(new Dimension(200, 30));
		pass.setFont(DIYPanel.MSG_FONT);

		emailLabel.setFont(DIYPanel.MSG_FONT);
		emailLabel.setForeground(Color.WHITE);
		passLabel.setFont(DIYPanel.MSG_FONT);
		passLabel.setForeground(Color.WHITE);

		email.setColumns(20);
		pass.setColumns(20);

		layout.putConstraint(SpringLayout.WEST, emailLabel,
				50,
				SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, emailLabel,
				120,
				SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, email,
				150,
				SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, email,
				120,
				SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, passLabel,
				50,
				SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, passLabel,
				170,
				SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, pass,
				150,
				SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, pass,
				170,
				SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.NORTH, login,
				230,
				SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, login,
				50,
				SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, create,
				230,
				SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, create,
				10,
				SpringLayout.EAST, login);

		add(emailLabel);
		add(email);
		add(passLabel);
		add(pass);
		add(login);
		add(create);

		create.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				String u_email = email.getText();
				
				  int lastIndex = u_email.lastIndexOf('.');
				  int lastIndexAt = u_email.lastIndexOf('@');
				  
				  System.out.println(lastIndex);
				  System.out.println(lastIndexAt);
								
				if (lastIndex > 0 && lastIndexAt > 0) {					
					@SuppressWarnings("deprecation")
					String u_pass = pass.getText();		
					
					if (u_pass.length() > 6) {
	
						UserProfile u = new UserProfile(u_email, u_pass);
	
						try {
							u.exportUser(u_email);
						} catch (IOException e) {
							myMessageToUser = "Could not create user.";
							repaint();
						}
	
						setPanel("Data");
						setMenuBar(true);
						email.setText("");
						pass.setText("");
					} else {
						myMessageToUser = "Password length must be longer than 6 characters";
						repaint();
						System.out.println("Password length must be longer than 6 characters");
					}
				} else {
					myMessageToUser = "Invalid email address";
					repaint();
					System.out.println("Invalid email address");
				}
				
			}
		});

		login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String u_email = email.getText();

				@SuppressWarnings("deprecation")
				String u_pass = pass.getText();

				UserProfile u = null;
				boolean flag  = true;

				myMessageToUser = "Incorrect username or password.";

				try {
					u = (UserProfile) UserProfile.importUser(u_email);
					flag = true;
					repaint();
				} catch (ClassNotFoundException | IOException e) {
					flag = false;
					repaint();
				}

				if (flag) {
					if (u_pass.equals(u.getPassword())) {
						myMessageToUser = "";

						email.setText("");
						pass.setText("");
						
						setPanel("Data");
						setMenuBar(true);
					}
				} else {
					myMessageToUser = "Incorrect username or password.";
					repaint();
				}
			}
		});
	}

	/**
	 * {@inheritDoc}
	 */
	public void paintComponent(final Graphics theGraphics) {
		super.paintComponent(theGraphics);
		final Graphics2D g = (Graphics2D) theGraphics;

		g.setColor(Color.WHITE);
		g.setFont(DIYPanel.HEADER_FONT);
		g.drawString("Welcome! Sign In or Create an Account", 50, 75);

		g.setFont(DIYPanel.MSG_FONT);
		g.drawString(myMessageToUser, 50, 300);
	}
	
	/**
	 * Initializes database directories if the user is here for the first time.
	 */
	private void createDirectories() {
		
		File db = new File(System.getProperty("user.home") + File.separator + "Desktop"
        		+ File.separator + "db");
		if (!db.exists()) {
			db.mkdir();
		}
		File u = new File(System.getProperty("user.home") + File.separator + "Desktop"
        		+ File.separator + "db" + File.separator + "users");
		if (!u.exists()) {
			u.mkdir();
		}
		File m = new File(System.getProperty("user.home") + File.separator + "Desktop"
        		+ File.separator + "db" + File.separator + "materials");
		if (!m.exists()) {
			m.mkdir();
		}
		File c = new File(System.getProperty("user.home") + File.separator + "Desktop"
        		+ File.separator + "db" + File.separator + "consumables");
		if (!c.exists()) {
			c.mkdir();
		}
		File t = new File(System.getProperty("user.home") + File.separator + "Desktop"
        		+ File.separator + "db" + File.separator + "contractors");
		if (!t.exists()) {
			t.mkdir();
		}
		
		
	}
}

/**
 * Matthew Allen Phillips
 * 11 April 2017
 * GUI.java
 */

package view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
public class SetupPanel extends JPanel {
	
	/**
	 * Stores the initial size of the GUI.
	 */
	private static final Dimension DEFAULT_SIZE = new Dimension(800, 600);
	
	/**
	 * Background page color for the SetupPage.
	 */
	private static final Color BACKGROUND_COLOR = new Color(91, 146, 234);

	/**
	 * Serial version ID.
	 */
	private static final long serialVersionUID = 4109522843927843411L;

	/**
	 * Constructs and returns a new SetupPanel.
	 */
	public SetupPanel() {
		super();
		init();
	}
	
	/**
	 * Initializer helper method for the panel.
	 */
	private void init() {
		setBackground(BACKGROUND_COLOR);
		setPreferredSize(DEFAULT_SIZE);
		
		//setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JTextField name  = new JTextField();
		JTextField email = new JTextField();
		JButton submit   = new JButton("Submit");
		
		name.setColumns(20);
		email.setColumns(20);

		add(name);
		add(email);
		add(submit);
	}
}

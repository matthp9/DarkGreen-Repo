/**
 * Matthew Allen Phillips
 * 11 April 2017
 * MenuBar.java
 */

package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 * A menu bar to hold frontend connections
 * to the functionality of the application.
 * 
 * @author Matt Phillips
 * @version 11 April 2017
 */
public class MenuBar extends JMenuBar {

	/**
	 * Serial version ID.
	 */
	private static final long serialVersionUID = -111659266189879540L;
	
	/**
	 * Stores the GUI this menu is set upon
	 * so that dialogs can function properly.
	 */
	private GUI myGUI;
	
	/**
	 * Constructs and returns a new MenuBar.
	 */
	public MenuBar(final GUI theGUI) {
		super();
		myGUI = theGUI;
		init();
	}
	
	/**
	 * Initializer for the menu bar.
	 */
	private void init() {
		JMenu help = new JMenu("Help");
		JMenuItem about = new JMenuItem("About...");
		
		about.addActionListener(new ActionListener() {
			String names = "Matthew Phillips\n\n\n";
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(myGUI, names, "About", JOptionPane.OK_OPTION, new ImageIcon());
			}
		});
		
		add(help);
		help.add(about);
	}
	
}

/**
 * Matthew Allen Phillips
 * 11 April 2017
 * MenuBar.java
 */

package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

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
	 * The help dropdown menu.
	 */
	private JMenu myHelpMenu;
	
	/**
	 * The tools dropdown menu.
	 */
	private JMenu myToolsMenu;
	
	/**
	 * The settings dropdown menu.
	 */
	private JMenu mySettingsMenu;
	
	/**
	 * Field for holding our names (required).
	 */
	private String myMemberNames;
	
	/**
	 * Constructs and returns a new MenuBar.
	 */
	public MenuBar(final GUI theGUI) {
		super();
		myGUI = theGUI;
		myMemberNames = "Matthew Phillips\nVidal Sisneros\nJasvir Dosanjh\nElisha Gentry\n";

		myToolsMenu = new JMenu("Tools");
		mySettingsMenu = new JMenu("Settings");
		myHelpMenu = new JMenu("Help");
		
		init();
	}
	
	/**
	 * Returns the collection of member names, which are to be
	 * displayed inside of a popup menu from the menu bar.
	 * @return
	 */
	public String getMemberNames() {
		return myMemberNames;
	}
	
	/**
	 * Initializer for the menu bar.
	 */
	private void init() {
		JMenuItem about = new JMenuItem("About...");
		
		about.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(myGUI, myMemberNames, "About", JOptionPane.OK_OPTION, new ImageIcon());
			}
		});
		
		initToolsMenu();
		
		initSettingsMenu();
		this.add(myHelpMenu);
		myHelpMenu.add(about);
	}

	private void initToolsMenu() {
		
		JMenuItem data = new JMenuItem("Save/Delete Items");
		JMenuItem calculator = new JMenuItem("Cost Calculator");
		JMenuItem analysis = new JMenuItem("My Resources");

		data.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				myGUI.setPanel("Data");
			}
		});
		
		calculator.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				myGUI.setPanel("Calculator");
			}
		});
		
		analysis.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				myGUI.setPanel("Analysis");
			}
		});
		
		myToolsMenu.add(data);
		myToolsMenu.addSeparator();
		myToolsMenu.add(calculator);
		myToolsMenu.addSeparator();
		myToolsMenu.add(analysis);
		
		this.add(myToolsMenu);
	}
	
	private void initSettingsMenu() {
		
		JMenuItem logout = new JMenuItem("Logout...");
		JMenuItem exit = new JMenuItem("Exit...");
		
		logout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				final int result = JOptionPane.showConfirmDialog(null, 
                        "Do you really want to log out?", 
                        "Confirm", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					myGUI.setPanel("Setup");
					myGUI.setMenu(false);
				}
			}
		});
		
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				final int result = JOptionPane.showConfirmDialog(null, 
                        "Are you sure you want to exit?", 
                        "Confirm", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					myGUI.dispatchEvent(new WindowEvent(myGUI, WindowEvent.WINDOW_CLOSING));
				}
			}
		});
		
		mySettingsMenu.add(logout);
		mySettingsMenu.addSeparator();
		mySettingsMenu.add(exit);
		
		this.add(mySettingsMenu);
	}
}

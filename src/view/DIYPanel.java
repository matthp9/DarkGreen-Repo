/**
 * Matthew Allen Phillips.
 * 11 May 2017
 * DIYPanel.java
 */

package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;

/**
 * An asbstraction of the core panel to be placed
 * inside of the GUI frame. Interchangeable.
 * 
 * @author Matt Phillips
 * @version 11 May 2017
 */
public abstract class DIYPanel extends JPanel {

	/**
	 * Stores the initial size of the GUI.
	 */
	private static final Dimension DEFAULT_SIZE = new Dimension(900, 650);
	
	/**
	 * Background page color for the SetupPage.
	 */
	private static final Color BACKGROUND_COLOR = new Color(109, 138, 163);
	
	public static final Font HEADER_FONT = new Font("Font H", Font.BOLD, 30);
	
	public static final Font MSG_FONT = new Font("Font S", Font.PLAIN, 18);
	
	public static final Font BOLD_FONT = new Font("Font S", Font.BOLD, 18);
	
	/**
	 * Serial version ID.
	 */
	private static final long serialVersionUID = 4310003379012233278L;

	/**
	 * Stores the GUI that this panel is tethered to.
	 */
	private GUI myGUI;

	/**
	 * Constructs and returns a new DIYPanel.
	 */
	public DIYPanel(final GUI theGUI) {
		myGUI = theGUI;
		setPreferredSize(DEFAULT_SIZE);
		setBackground(BACKGROUND_COLOR);
	}
	
	protected void setPanel(final String theKey) {
		myGUI.setPanel(theKey);
	}
	
	protected void setMenuBar(final boolean theKey) {
		myGUI.setMenu(theKey);
	}
}

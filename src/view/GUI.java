/**
 * Matthew Allen Phillips
 * 11 April 2017
 * GUI.java
 */

package view;

import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * Main graphical user interface for the app.
 * 
 * @author Matt Phillips
 * @version 11 April 2017
 */
public class GUI extends JFrame {

	/**
	 * Serial version ID.
	 */
	private static final long serialVersionUID = 7033246162815741430L;
	
	/**
	 * Initial size of the GUI frame.
	 */
	private static final Dimension DEFAULT_SIZE = new Dimension(800, 600);

	/**
	 * Constructs and returns a new GUI.
	 */
	public GUI() {
		super("App");
		init();
	}
	
	/**
	 * Begins frontend runtime for the GUI.
	 */
	public void start() {
		add(new SetupPanel());
		MenuBar m = new MenuBar(this);
		setJMenuBar(m);
		setLocationRelativeTo(null);
        setVisible(true);
	}
	
	/**
	 * Initializer helper method for all frontend GUI aspects.
	 */
	private void init() {
		setMinimumSize(DEFAULT_SIZE);
        pack();
	}
	
}

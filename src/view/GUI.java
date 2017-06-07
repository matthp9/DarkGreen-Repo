/**
 * Matthew Allen Phillips
 * 11 April 2017
 * GUI.java
 */

package view;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Main graphical user interface for the app.
 * 
 * @author Matt Phillips
 * @version 11 April 2017
 */
public class GUI extends JFrame {

	public static final BufferedImage ICON = getImage();
	
	/**
	 * Serial version ID.
	 */
	private static final long serialVersionUID = 7033246162815741430L;
	
	/**
	 * Initial size of the GUI frame.
	 */
	private static final Dimension DEFAULT_SIZE = new Dimension(800, 600);

	/**
	 * The controller menu bar.
	 */
	private MenuBar myMenuBar;
	
	/**
	 * The current panel to display.
	 */
	private DIYPanel myPanel;
	
	/**
	 * The current user of the program.
	 */
	public String user;
	
	// Fields include an instance of each type of panel so
	// that data is preserved when the user wants to switch.
	
	/**
	 * The login/setup panel.
	 */
	private DIYPanel mySetupPanel;
	
	/**
	 * The data colletion panel.
	 */
	private DIYPanel myDataPanel;

	/**
	 * The calculator panel.
	 */
	private DIYPanel myCalculatorPanel;

	/**
	 * The graph analysis panel.
	 */
	private DIYPanel myAnalysisPanel;

	/**
	 * Constructs and returns a new GUI.
	 */
	public GUI() {
		super("ProjectBuddy");
		
		mySetupPanel = new SetupPanel(this);
		myDataPanel = new DataPanel(this);
		myCalculatorPanel = new CalculatorPanel(this);
		myAnalysisPanel = new AnalysisPanel(this);
		
		myPanel = mySetupPanel;
		add(myPanel);
	}
	
	/**
	 * Begins frontend runtime for the GUI.
	 */
	public void start() {
		myMenuBar = new MenuBar(this);
		setJMenuBar(myMenuBar);
		setMenu(false);
		
		setLayout(new CardLayout());
		
		setMinimumSize(DEFAULT_SIZE);
        pack();
		setLocationRelativeTo(null);
        setVisible(true);
	}
	
	/**
	 * Sets the correct panel based off of an input key.
	 * @param theKey the key representing panel ID.
	 */
	public void setPanel(String theKey) {
		getContentPane().removeAll();
		switch (theKey) {
			case "Setup":
				myPanel = mySetupPanel;
				break;
		
			case "Data":
				myPanel = myDataPanel;
				getContentPane().add(myDataPanel);
				break;
				
			case "Calculator":
				myPanel = myCalculatorPanel;
				getContentPane().add(myCalculatorPanel);
				break;
				
			case "Analysis":
				myPanel = myAnalysisPanel;
				getContentPane().add(myAnalysisPanel);
				break;
		}
		getContentPane().add(myPanel);
		repaint();
		myPanel.repaint();
	}
	
	protected void setMenu(final boolean theKey) {
		if (theKey) {
			System.out.println("Signed in");
			myMenuBar.setVisible(true);
		} else {
			System.out.println("Signed out");
			myMenuBar.setVisible(false);
		}
	}
	
	public static BufferedImage getImage() {
		try {
			return ImageIO.read(new FileInputStream("images/icon.png"));
		} catch (FileNotFoundException e) {
			return null;
		} catch (IOException e) {
			return null;
		}
	}
}

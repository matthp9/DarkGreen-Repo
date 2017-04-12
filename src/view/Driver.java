/**
 * Matthew Allen Phillips
 * 3 April 2017
 * Driver.java
 */

package view;

import java.awt.EventQueue;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Program driver. Includes a main. Invokes
 * the initial loading splashscreen while the
 * user waits for the item store to load.
 * Launches the program's main display source,
 * HubGUI once the item store has loaded fully.
 * 
 * @author Matt Phillips
 * @version 3 April 2017
 */
public class Driver {

	/**
	 * Private constructor to prevent instantiation.
	 */
	private Driver () {
		// Do nothing.
	}
	
	/**
     * Sets the preferred Metal look of the GUI.
     */
    private static void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (final UnsupportedLookAndFeelException e) {
            System.out.println("UnsupportedLookAndFeelException");
        } catch (final ClassNotFoundException e) {
            System.out.println("ClassNotFoundException");
        } catch (final InstantiationException e) {
            System.out.println("InstantiationException");
        } catch (final IllegalAccessException e) {
            System.out.println("IllegalAccessException");
        }
    }
	
    /**
     * Invokes GUI.
     * 
     * @param theArgs Command line arguments.
     */
    public static void main(final String[] theArgs) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
            	
            	setLookAndFeel();
            	
            	new GUI().start();
            }
        });
    }
}

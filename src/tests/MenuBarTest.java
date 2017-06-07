package tests;

import static org.junit.Assert.*;
import org.junit.Test;

import view.GUI;
import view.MenuBar;

/**
 * Unit tests for class MenuBar.
 * 
 * @author Matt Phillips
 * @version 15 April 2017
 */
public class MenuBarTest {
    
	private final GUI myTestGUI = new GUI();
	
    /** 
     * Test method for menu bar constructor. 
     * Checks if the correct member name count exists in
     * the about page. Checks to make sure that the 
     * correct team member names are displayed.
     */
    @Test
    public void testMenuBarConstructor() {
        final MenuBar mb = new MenuBar(myTestGUI);
        
        assertEquals("Names are not as expected to be.", "Matthew Phillips\nVidal Sisneros\nJasvir Dosanjh\nElisha Gentry\n", mb.getMemberNames());

        final String[] names = mb.getMemberNames().split("\n");
        
        assertEquals("The name count is not valid.", 4, names.length);
    }
}

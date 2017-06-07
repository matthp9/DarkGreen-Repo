/**
 * Matthew Allen Phillips/Elisha Gentry
 * 18 May 2017
 * ImportExportTest.java
 */

package tests;


import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import model.UserProfile;

/**
 * Unit tests for class UserProfile.
 * 
 * @author Matt Phillips
 * @version 18 May 2017
 */
public class ImportExportTest {
    
	/**
	 * This is the key for serialization and deserialization.
	 * Used in both cases to test for consistency.
	 */
	private final String myTestString = "jdoe64.ser";
	
    /**
     * Test method for exporting UserProfile objects.
     * Passes if no I/O (or general) exceptions are thrown.
     */
    @Test
    public void testExport() {

    	final UserProfile up = new UserProfile("John Doe", "jdoe64@uw.edu");
        
    	try {
			up.exportUser(myTestString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        assertEquals("Export failed.", "Passed", "Passed");
    }
    
    /**
     * Tests importing UserProfile objects.
     * Passes if the correct value matches the UserProfile
     * returned from the consisteny key.
     */
    @Test
    public void testImport() {
    	
    	UserProfile up = null;
		try {
			up = UserProfile.importUser(myTestString);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        assertEquals("Import failed.", up.toString(), "John Doe jdoe64@uw.edu");
    }
}

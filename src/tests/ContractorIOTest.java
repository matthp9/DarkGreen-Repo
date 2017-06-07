/**
 * Matthew Allen Phillips
 * 6 June 2017
 * ContractorIOTest.java
 */

package tests;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import model.ContractorProfile;
import model.Material;

/**
 * Test class for import and export of Contractors.
 * 
 * @author Matt Phillips
 * @version 6 June 2017
 */
public class ContractorIOTest {

	/**
	 * This is the key for serialization and deserialization.
	 * Used in both cases to test for consistency.
	 */
	private final String myTestString = "Mr. Example Contractor";
	
	/**
	 * Tests exporting contractor profiles.
	 */
	@Test
	public void testContractorExport() {
		final ContractorProfile cp = new ContractorProfile(myTestString, "5");
        
    	try {
			cp.exportContractor(myTestString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        assertEquals("Export failed.", "Passed", "Passed");
	}
	
	/**
	 * Tests importing contractor profiles.
	 */
	@Test
	public void testContractorImport() {
		ContractorProfile cp = null;
		
		try {
			cp = ContractorProfile.importContractor(myTestString);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        assertEquals("Import failed.", cp.getMyName(), myTestString);
	}
	
}

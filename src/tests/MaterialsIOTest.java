/**
 * Mathew Allen Phillips
 * 6 June 2017
 * MaterialsIOTest.java
 */

package tests;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import model.Material;

/**
 * Test class for import and export of materials.
 * 
 * @author Matt Phillips
 * @version 6 June 2017
 */
public class MaterialsIOTest {
	
	/**
	 * This is the key for serialization and deserialization.
	 * Used in both cases to test for consistency.
	 */
	private final String myTestString = "Wood";
	
	/**
	 * Tests exporting materials.
	 */
	@Test
	public void testMaterialsExport() {
		
		final Material m = new Material(myTestString, "sq. ft.", 4.50, "Lowe's");
        
    	try {
			m.exportMaterial(myTestString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        assertEquals("Export failed.", "Passed", "Passed");
	}
	
	/**
	 * Tests importing materials.
	 */
	@Test
	public void testMaterialsImport() {
		
		Material m = null;
		try {
			m = Material.importMaterial(myTestString);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        assertEquals("Import failed.", m.getMyDepot(), "Lowe's");
	}
	
}

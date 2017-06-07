/*
 * Vidal Sisneros
 * 6/6/2017
 * TCSS360 
 * Mr. Weiss
 */

package tests;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
import model.Consumable;


/**
 * Test the Consumable Class.
 * 
 * @author Vidal Sisneros
 */
public class ConsumableTest {
	
	private Consumable myCon = new Consumable("Energy", "5", 10.00, "2017-6-6");
	
	/**
	 * Tests contructor and accessor methods.
	 */
	@Test
	public void testConstructor() {
		assertEquals("Incorrect Name returned!", "Energy", myCon.getMyName());
		assertEquals("incorrect Units returned!", "5", myCon.getMyUnit());
		assertEquals("Did not return the correct cost!", 10.00, myCon.getMyCost(),0);
		assertEquals("2017-6-6", myCon.getMySupplier());
	}
	
	/*
	 * Tests export feature.
	 */
	@Test
	public void testExport() throws IOException{
		
		try {
			myCon.exportConsumable("jasvir");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	/*
	 * Tests import feature.
	 */
	@Test
	public void testImport() throws IOException, ClassNotFoundException{
		
		try {
			Consumable.importConsumable("jasvir");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}

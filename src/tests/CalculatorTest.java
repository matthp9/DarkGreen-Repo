package tests;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import model.Material;

/**
 * Unit tests for class CalculatorPanel.
 * 
 * @author Matt Phillips
 * @version 15 April 2017
 */
public class CalculatorTest {

	/**
	 * Test method for calculations.
	 * Ensures that the cost of some amount of
	 * imported material is the correct calculation.
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testCalculatorAddition() {
		
		String m_type = "Wood";
		String amount = "4";
		
		Material m = null;
		try {
			m = Material.importMaterial(m_type);
		} 
		catch (ClassNotFoundException e1) {} 
		catch (IOException e2) {}
		
		if (m != null) {
			double m_costPerUnit = m.getMyCost();
			
			int m_num = 0;
			try {
				m_num = Integer.parseInt(amount);
			} catch (Exception e) {}
			
			double m_total = m_num * m_costPerUnit; 
			
			assertEquals("testCalculatorAddition has failed.", 10.0, m_total);
		}
	}
	
}

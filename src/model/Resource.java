/**
 * Matthew Allen Phillips
 * 18 May 2017
 * ImportExportTest.java
 */

package model;

import java.io.Serializable;

/**
 * Abstraction of DIY project resources.
 * 
 * @author Matt Phillips
 * @version 19 May 2017
 */
public abstract class Resource implements Component, Serializable {

	/**
	 * Serial version ID.
	 */
	private static final long serialVersionUID = 6654975952543898558L;

	/**
	 * Identifying name of this resource.
	 */
	private String myName;
	
	/**
	 * The unit used to measure this resource.
	 */
	private String myUnit;
	
	/**
	 * The cost of this resource per unit.
	 */
	private double myCost;
	
	/**
	 * Constructs and returns a new Resource.
	 */
	public Resource(final String theName,
					final String theUnit,
					final double  theCost) {
		// No parameters are mutable Obejcts.
		myName = theName;
		myUnit = theUnit;
		myCost = theCost;
	}
	
	/**
	 * @return the myName
	 */
	public String getMyName() {
		return myName;
	}

	/**
	 * @return the myUnit
	 */
	public String getMyUnit() {
		return myUnit;
	}

	/**
	 * @return the myCost
	 */
	public double getMyCost() {
		return myCost;
	}
}

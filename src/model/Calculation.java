/**
 * Matthew Allen Phillips
 * 26 May 2017
 * Calculation.java
 */

package model;

/**
 * Represents one single project cost calculation.
 * @author Matt Phillips
 * @version 26 May 2017
 */
public class Calculation {

	private final String myMaterialName;
	private final int myAmount;
	private final double myCostPerUnit;
	private final String myUnit;
	private final double myTotalCost;
	private final String myDepot;
	
	public Calculation(final String theMaterialName,
			final int theAmount,
			final double theCostPerUnit,
			final String theUnit,
			final double theTotalCost,
			final String theDepot) {
		myMaterialName = theMaterialName;
		myAmount = theAmount;
		myCostPerUnit = theCostPerUnit;
		myUnit = theUnit;
		myTotalCost = theTotalCost;
		myDepot = theDepot;
	}

	/**
	 * Returns the name of the material.
	 * @return the myMaterialName.
	 */
	public String getMyMaterialName() {
		return myMaterialName;
	}

	/**
	 * Returns the amount of material used in the calculation.
	 * @return the myAmount.
	 */
	public int getMyAmount() {
		return myAmount;
	}

	/**
	 * Returns the cost per unit.
	 * @return the myCostPerUnit.
	 */
	public double getMyCostPerUnit() {
		return myCostPerUnit;
	}

	/**
	 * Returns the unit of measurement.
	 * @return the myUnit.
	 */
	public String getMyUnit() {
		return myUnit;
	}

	/**
	 * Returns the total cost.
	 * @return the myTotalCost
	 */
	public double getMyTotalCost() {
		return myTotalCost;
	}

	/**
	 * Returns the name of the depot associated with the item.
	 * @return the myDepot
	 */
	public String getMyDepot() {
		return myDepot;
	}
}

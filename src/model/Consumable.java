/**
 * Matthew Allen Phillips
 * 18 May 2017
 * Consumable.java
 */

package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Represents one Consumable Resource in a
 * DIY project.
 * 
 * @author Matt Phillips
 * @version 18 May 2017
 */
public final class Consumable extends Resource {

	/**
	 * Serial version ID - used for serialization.
	 */
	private static final long serialVersionUID = -6206733135121695969L;

	private final String mySupplier;
	
	/**
	 * Constructs and returns a new Consumable.
	 * 
	 * @param theName name of the Consumable.
	 * @param theUnit is the unit of measurement used with the Consumable.
	 * @param i_cost is the initial cost.
	 * @param theSupplier is the name of the supplier.
	 */
	public Consumable(String theName, String theUnit, 
					  double i_cost, String theSupplier) {
		super(theName, theUnit, i_cost);
		mySupplier = theSupplier;
	}

	/**
	 * Sets the name of the supplier.
	 * @return mySupplier is the name of the supplier.
	 */
	public String getMySupplier() {
		return mySupplier;
	}
	
	/**
	 * Exports the consumable info as a .ser file.
	 * @param dest is the name the consumable will be saved under as a .ser file.
	 */
	public void exportConsumable(final String dest) throws IOException {
        ObjectOutputStream oos = null;
        FileOutputStream fout = null;
        File out = new File(System.getProperty("user.home") + File.separator + "Desktop"
        		+ File.separator + "db" + File.separator + "consumables" + File.separator
        		+ dest + ".ser");
        fout = new FileOutputStream(out);
        oos = new ObjectOutputStream(fout);
        oos.writeObject(this);
        oos.close();
    }

	/**
	 * exports the contractor .ser file.
	 * @param dest is the name the consumable is saved under as a .ser file.
	 */
    public static Consumable importConsumable(final String desc) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = null;
        FileInputStream fin = null;
        Consumable c = null;
        File in = new File(System.getProperty("user.home") + File.separator + "Desktop"
        		+ File.separator + "db" + File.separator + "consumables" + File.separator
        		+ desc + ".ser");
        fin = new FileInputStream(in);
        ois = new ObjectInputStream(fin);
        c = (Consumable) ois.readObject();
        System.out.println("READ: " + c.toString());
        fin.close();
        return c;
    }
}

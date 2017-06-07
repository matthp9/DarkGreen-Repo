/**
 * Matthew Allen Phillips
 * 18 May 2017
 * ImportExportTest.java
 */

package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Material extends Resource {

	/**
	 * Serial version ID.
	 */
	private static final long serialVersionUID = -8358538372738247337L;
	
	/**
	 * Where the material is bought.
	 */
	private String myDepot;
	
	/**
	 * Constructs and returns a new Material.
	 * @param theName name of the Material.
	 * @param theUnit unit of the Material.
	 * @param theCost cost of the Material.
	 */
	public Material(String theName, String theUnit, 
					double theCost, String theDepot) {
		super(theName, theUnit, theCost);
		myDepot = theDepot;
	}

	/**
	 * @return the myDepot
	 */
	public String getMyDepot() {
		return myDepot;
	}

	/**
	 * @param myDepot the myDepot to set
	 */
	public void setMyDepot(String myDepot) {}
	
	public void exportMaterial(final String dest) throws IOException {
        ObjectOutputStream oos = null;
        FileOutputStream fout = null;
        File out = new File(System.getProperty("user.home") + File.separator + "Desktop"
        		+ File.separator + "db" + File.separator + "materials" + File.separator
        		+ dest + ".ser");
        System.out.println(out.getAbsolutePath());
        fout = new FileOutputStream(out);
        oos = new ObjectOutputStream(fout);
        oos.writeObject(this);
        oos.close();
    }

    public static Material importMaterial(final String desc) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = null;
        FileInputStream fin = null;
        Material m = null;
        File in = new File(System.getProperty("user.home") + File.separator + "Desktop"
        		+ File.separator + "db" + File.separator + "materials" + File.separator
        		+ desc + ".ser");
        fin = new FileInputStream(in);
        ois = new ObjectInputStream(fin);
        m = (Material) ois.readObject();
        fin.close();
        return m;
    }
}

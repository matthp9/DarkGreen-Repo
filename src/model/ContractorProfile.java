/**
 * Matthew Allen Phillips
 * 18 May 2017
 * ContractorProfile.java
 */

package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Represents the profile of a contractor and saves info.
 * 
 * @author Matt Phillips
 * @version 18 May 2017
 */
public class ContractorProfile extends UserProfile {

	/**
	 * Serial version ID.
	 */
	private static final long serialVersionUID = -3381642827110427152L;
	
	private String myName;
	
	/**
	 * Represents the contractor's reputation.
	 */
	private int myReputation;

	/**
	 * Constructs and returns a new ContractorProfile.
	 * @param tagName user's tagname.
	 * @param email user's email.
	 */
	public ContractorProfile(String tagName, String email) {
		super(tagName, email);
		setMyName(tagName);
		myReputation = 0;
	}

	/**
	 * Returns the contractor's reputation.
	 * @return the myReputation.
	 */
	public int getMyReputation() {
		return myReputation;
	}

	/**
	 * Sets the reputation of a contractor.
	 * @param myReputation the myReputation to set.
	 */
	public void setMyReputation(int myReputation) {
		this.myReputation = myReputation;
	}
	
	/**
	 * Exports the contractor info as a .ser file.
	 * @param dest is the name the contractor will be saved under as a .ser file.
	 */
	public void exportContractor(final String dest) throws IOException {
        ObjectOutputStream oos = null;
        FileOutputStream fout = null;
        File out = new File(System.getProperty("user.home") + File.separator + "Desktop"
        		+ File.separator + "db" + File.separator + "contractors" + File.separator
        		+ dest + ".ser");
        fout = new FileOutputStream(out);
        oos = new ObjectOutputStream(fout);
        oos.writeObject(this);
        oos.close();
    }

	/**
	 * Imports the contractor .ser file.
	 * @param dest is the name the contractor is saved under as a .ser file.
	 */
    public static ContractorProfile importContractor(final String desc) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = null;
        FileInputStream fin = null;
        ContractorProfile c = null;
        File in = new File(System.getProperty("user.home") + File.separator + "Desktop"
        		+ File.separator + "db" + File.separator + "contractors" + File.separator
        		+ desc + ".ser");
        fin = new FileInputStream(in);
        ois = new ObjectInputStream(fin);
        c = (ContractorProfile) ois.readObject();
        fin.close();
        return c;
    }

	/**
	 * Returns the contractor's name.
	 * @return the myName.
	 */
	public String getMyName() {
		return myName;
	}

	/**
	 * Sets the contractor's name.
	 * @param myName the myName to set.
	 */
	public void setMyName(String myName) {
		this.myName = myName;
	}
}

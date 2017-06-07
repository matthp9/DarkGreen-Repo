/**
 * Matthew Allen Phillips
 * 18 May 2017
 * ImportExportTest.java
 */

package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Represents one single User of this program.
 * 
 * @author Matt Phillips
 */
public class UserProfile implements Serializable {
	
    /**
     * Serial version ID.
     */
    private static final long serialVersionUID = 59528155478251485L;

    private String username;
    private String password;
    
    /**
     * UserProfile stores email and password.
     * @param email is the user's email.
     * @param password is the user's password.
     */
    public UserProfile(String email, String password) {
        this.setEmail(email);
        this.setPassword(password);
    }
    
    /**
     * Returns name and password.
     */
    public String toString() {
        return username + " " + password;
    }

    /**
     * Exports user as a .ser.
     * @param dest is the name the user will be as a .ser.
     */
    public void exportUser(final String dest) throws IOException {
        ObjectOutputStream oos = null;
        FileOutputStream fout = null;
        File out = new File(System.getProperty("user.home") + File.separator + "Desktop"
        		+ File.separator + "db" + File.separator + "users" + File.separator
        		+ dest + ".ser");
        fout = new FileOutputStream(out);
        oos = new ObjectOutputStream(fout);
        oos.writeObject(this);
        oos.close();
    }

    /**
     * Imports user .ser into program.
     */
    public static UserProfile importUser(final String desc) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = null;
        FileInputStream fin = null;
        UserProfile u = null;
        File in = new File(System.getProperty("user.home") + File.separator + "Desktop"
        		+ File.separator + "db" + File.separator + "users" + File.separator
        		+ desc + ".ser");
        fin = new FileInputStream(in);
        ois = new ObjectInputStream(fin);
        u = (UserProfile) ois.readObject();
        System.out.println("READ: " + u.toString());
        fin.close();
        return u;
    }

	/**
	 * @return the email
	 */
	public String getEmail() {
		return username;
	}


	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.username = email;
	}


	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}


	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
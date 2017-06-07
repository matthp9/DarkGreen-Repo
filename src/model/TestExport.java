package model;

import java.io.IOException;

public class TestExport
{
// arguments are passed using the text field below this editor
  public static void main(String[] args)
  {
      UserProfile myProfile = new UserProfile("Matt","matthp9@uw.edu");
      String dest = "matt.ser";
      try {
		myProfile.exportUser(dest);
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
     
      UserProfile up = null;
	try {
		up = UserProfile.importUser(dest);
	} catch (ClassNotFoundException | IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      System.out.println(up.toString());
  }
}


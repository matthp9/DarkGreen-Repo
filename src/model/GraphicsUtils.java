/**
 * Matthew Allen Phillips
 * 18 May 2017
 * GraphicsUtils.java
 */

package model;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class GraphicsUtils {

	/**
	 * Utility method. Accepts an image path URL to read an image.
	 * Desired behavior for .jar programs to draw images without
	 * referencing file types in the jar achieved via this method.
	 * 
	 * @param theURL path URL to the desired image file.
	 * @return a BufferedImage object read from the supplied URL.
	 */
	public static BufferedImage loadImg(final String theURL) {
		InputStream input = GraphicsUtils.class.getResourceAsStream(theURL);
		if (input == null) {
			input = GraphicsUtils.class.getResourceAsStream("/" + theURL);
		}
		try {
			return ImageIO.read(input);
		} catch (IOException e) {
			System.out.println("loadImg(URL): IOException reading" + theURL);
		}
		return null;
	}
	
}

package de.thm.mni.nn.ui;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
/**
 * Just a class for providing a Panel.
 * 
 * @author Tobias Knoth
 * 
 */
@SuppressWarnings("serial")
public class ShowImage extends Panel {
	BufferedImage image;

	public ShowImage(File out) {
		try {

			image = ImageIO.read(out);
		} catch (IOException ie) {
			System.out.println("Error:" + ie.getMessage());
		}
	}

	public void paint(Graphics g) {
		g.drawImage(image, 0, 0, null);
	}

}
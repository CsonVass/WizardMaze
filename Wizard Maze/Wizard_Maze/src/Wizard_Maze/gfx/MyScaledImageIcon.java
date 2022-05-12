 package Wizard_Maze.gfx;

import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;


@SuppressWarnings("serial") //compiler required
public class MyScaledImageIcon extends ImageIcon{
	
	public MyScaledImageIcon(BufferedImage image, int width, int height) {
		super(image);
		Image scaler = this.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		this.setImage(scaler); 
	}

}

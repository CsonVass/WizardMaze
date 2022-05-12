package Wizard_Maze.things.positioning;

import java.awt.Shape;
import java.awt.geom.AffineTransform;

public class Transformer {
	
	private Shape shape;
	
	public Transformer(Shape shape) {
		this.shape = shape;
	}
	
	//Rotating around own center with angle
	public Shape rotate(double angle) {
		AffineTransform at = new AffineTransform();	
		at.rotate(Math.toRadians(angle), shape.getBounds().getCenterX(), shape.getBounds().getCenterY());
		shape = at.createTransformedShape(shape);
		
		return shape;
	}
	
	//Rotating around given center with angle
	public Shape rotate(double angle, int x, int y) {
		AffineTransform at = new AffineTransform();	
		at.rotate(Math.toRadians(angle), x, y);
		shape = at.createTransformedShape(shape);
		
		return shape;
	}
	
	//Moving shapes with given coordinates
	public Shape move(int tx, int ty) {
		AffineTransform at = new AffineTransform();	
		at.translate(tx, ty);
		shape = at.createTransformedShape(shape);
		
		return shape;
	}

}

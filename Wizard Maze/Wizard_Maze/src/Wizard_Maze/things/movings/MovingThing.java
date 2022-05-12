package Wizard_Maze.things.movings;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;

import Wizard_Maze.states.GameState;
import Wizard_Maze.things.Thing;
import Wizard_Maze.things.positioning.Transformer;
import Wizard_Maze.tiles.Tile;

public abstract class MovingThing extends Thing {

	//-------------------------ATTRIBUTES---------------------------\\
	public static final float DEFAULT_SPEED = 3.5f;
	
	protected float speed;
	protected int xMove, yMove;
	protected double rotateAngle;
	
	//--------------------------------------------------------------------------\\
	
	//Constructor
	public MovingThing(GameState gameState, int x, int y, double angle, int width, int height, int hitBoxWidth, int hitBoxHeight, BufferedImage texture) {
		super(gameState, x, y, angle, width, height, hitBoxWidth, hitBoxHeight, texture);
		speed = DEFAULT_SPEED;
		xMove = 0;
		yMove = 0;
		rotateAngle = 0;
	}
	
	//General move method for all moving things
	public void move() {
		moveCoordinate(xMove, 0);
		moveCoordinate(0, yMove);
		rotate(rotateAngle);
	}
	
	//Moving the coordinates 
	public void moveCoordinate(int xMove, int yMove) {
		//Making temporary "if moved" moved hitbox
		Transformer transformer = new Transformer(hitBox);
		Shape movedHitBox = transformer.move(xMove, yMove);
		Rectangle boundingBox = movedHitBox.getBounds();
		
		//how many tiles to check 
		int dx = (int) (boundingBox.getWidth() / Tile.TILEWIDTH) + 2;
		int dy = (int) (boundingBox.getHeight() / Tile.TILEHEIGHT) + 2;
		
		//from when to check tiles
		int tx = (int) (boundingBox.getX() / Tile.TILEWIDTH);
		int ty = (int) (boundingBox.getY() / Tile.TILEHEIGHT);
		
		//do intersect with checked solid tile
		Area boundingBoxArea = new Area(boundingBox);
		for(int i = tx; i < tx + dx; i++) {
			for(int j = ty; j < ty + dy; j++) {
				if(gameState.getMaze().getTile(i, j).isSolid()) {
					Area intersection = boundingBoxArea;
					Area tileArea = new Area(new Rectangle(i*Tile.TILEWIDTH, j*Tile.TILEHEIGHT, Tile.TILEWIDTH, Tile.TILEHEIGHT));
					intersection.intersect(tileArea);
					if(!intersection.isEmpty()) {
						hitWall(xMove, yMove);
						return;
					}
				}
			}
		}
		//if not, make moved hitBox the new hitBox
		hitBox = movedHitBox; 
	}
	
	//To do when htiWall (not abstract, but overridden if needed)
	public void hitWall(int tileX, int tileY) {}
	
	//Rotating the hitBox (same logic as with the coordinates)
	public void rotate(double angle) {
		Transformer transformer = new Transformer(hitBox);
		Shape movedHitBox = transformer.rotate(angle);
		Rectangle boundingBox = movedHitBox.getBounds();
		
		int dx = (int) (boundingBox.getWidth() / Tile.TILEWIDTH) + 2;
		int dy = (int) (boundingBox.getHeight() / Tile.TILEHEIGHT) + 2;
		
		int tx = (int) (boundingBox.getX() / Tile.TILEWIDTH);
		int ty = (int) (boundingBox.getY() / Tile.TILEHEIGHT);
		
		
		Area boundingBoxArea = new Area(boundingBox);
		for(int i = tx; i < tx + dx; i++) {
			for(int j = ty; j < ty + dy; j++) {
				if(gameState.getMaze().getTile(i, j).isSolid()) {
					Area intersection = boundingBoxArea;
					Area tileArea = new Area(new Rectangle(i*Tile.TILEWIDTH, j*Tile.TILEHEIGHT, Tile.TILEWIDTH, Tile.TILEHEIGHT));
					intersection.intersect(tileArea);
					if(!intersection.isEmpty())
						return;
				}
			}
		}
		
		hitBox = movedHitBox; 
		this.angle += angle;
	}
	
	
	//moving things must implement kill method
	public abstract void kill();
	
	//Getters & Setters

	public float getSpeed() {
		return speed;
	}

	public float getxMove() {
		return xMove;
	}

	public float getyMove() {
		return yMove;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public void setxMove(int xMove) {
		this.xMove = xMove;
	}

	public void setyMove(int yMove) {
		this.yMove = yMove;
	}

	public double getRotateAngle() {
		return rotateAngle;
	}

	public void setRotateAngle(double rotateAngle) {
		this.rotateAngle = rotateAngle;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	

}

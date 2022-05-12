package Wizard_Maze.things;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
//import java.awt.geom.AffineTransform;
//import java.awt.geom.Area;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Wizard_Maze.states.GameState;
import Wizard_Maze.things.movings.Player;
import Wizard_Maze.things.movings.spells.Spell;

public abstract class Thing {
	
	//-------------------------ATTRIBUTES---------------------------\\
	protected float x, y;
	protected double angle;
	protected int width, height;
	
	//texture
	protected BufferedImage texture;
	
	//gamestate
	protected GameState gameState;	
	
	//hitbox	
	protected Shape hitBox;	
	protected int hitBoxWidth;
	protected int hitBoxHeight;
	
	
	//--------------------------------------------------------------------------\\
	
	//Constructor
	public Thing(GameState gameState, int x, int y, double angle, int width, int height, int hitBoxWidth, int hitBoxHeight, BufferedImage texture) {
		this.gameState = gameState;
		this.x = x;
		this.y = y;
		this.angle = angle ;
		this.width = width;
		this.height = height;
		this.hitBoxWidth = hitBoxWidth;
		this.hitBoxHeight = hitBoxHeight;
		this.texture = texture;
		
		hitBoxWidth = width;
		hitBoxHeight = height;
		
		hitBox = new Rectangle(0, 0, hitBoxWidth, hitBoxHeight);
	}
	
	//child can override if needed
	public void tick() {}
	
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		//Drawing hitbox in case of future developement
		//g2d.draw(hitBox);
		g2d.rotate(Math.toRadians(angle), (int) hitBox.getBounds().getCenterX(), (int) hitBox.getBounds().getCenterY());
		g2d.drawImage(texture, (int) (hitBox.getBounds().getX()- (width - hitBox.getBounds().getWidth()) / 2), 
				(int) (hitBox.getBounds().getY() - (height - hitBox.getBounds().getHeight()) / 2), width, height, null);	

		g2d.dispose();
		
	}
	
	//--------------------------------COLLISION METHODS-----------------------------------\\
	//Collision detection
	public void checkThingCollisionsWithThings(float xOffset, float yOffset) {
		ArrayList<Thing> mazeThings  = gameState.getMaze().getThingManager().getThings();
		for(int i = 0; i < mazeThings.size(); i++) {
			if(mazeThings.get(i).equals(this)) //not collide with itself
				continue;
			
			if(mazeThings.get(i).doCollide(this)) {
				mazeThings.get(i).collideWith(this);
				return;
			}
		}
	}

	//Checking collision between two things with areas
	public boolean doCollide(Thing t) {
		Area intersection = new Area(t.getHitBox());
		intersection.intersect(new Area(hitBox));
		if(!intersection.isEmpty())
			return true;
		return false;		
	}	
	
	//Collision events
	public void collideWith(Thing t) {
		t.collideWith(this);
	}	
	
	public void hitBy(Player p) {}
	
	public void hitBy(Spell b) {}	
	
	

	//-------------------------SETTERS & GETTERS---------------------------\\	

	public void setX(float x) {
		this.x = x;
		hitBox = new Rectangle((int)x + (width - hitBoxWidth) / 2,(int) y + (height - hitBoxHeight) /2 , hitBoxWidth, hitBoxHeight);
	}

	public void setY(float y) {
		this.y = y;
		hitBox = new Rectangle((int)x + (width - hitBoxWidth) / 2,(int) y + (height - hitBoxHeight) /2 , hitBoxWidth, hitBoxHeight);
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}	
	
	public void setHitBox(Shape shape) {
		Rectangle boundsOfShape = shape.getBounds();
		hitBox = new Rectangle((int) boundsOfShape.getX(), (int) boundsOfShape.getY(), hitBoxWidth, hitBoxHeight);
	}
	
	public double getAngle() {
		return angle;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public Shape getHitBox() {
		return hitBox;
	}
	
}

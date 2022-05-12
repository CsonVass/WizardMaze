package Wizard_Maze.things.movings.spells;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Path2D;

import Wizard_Maze.states.GameState;
import Wizard_Maze.things.Thing;
import Wizard_Maze.things.movings.Player;
import Wizard_Maze.things.positioning.Transformer;

public class UltimateSpell extends Spell{	

	private Path2D.Double path;

	public UltimateSpell(GameState gameState) {
		super(gameState, 0, 0, 0, 60, 60, null, DEFAULT_TRIPTIME / 2);
		speed =  DEFAULT_SPEED * 5f;
		
		path = new Path2D.Double();
	}
	 
	@Override
	public void tick() {
		getDirection();
		move();
		path.append(hitBox, false);
		path.closePath();
	}	
	
	//Drawing the whole path
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setColor(Color.green);
		g2d.fill(path);
		g2d.dispose();
		
	}
	
	//Collision events
	@Override
	public void hitBy(Player p) {	
		p.kill();
	}
	
	//Checking collision for the whole path
	@Override
	public boolean doCollide(Thing t) {
		Area intersection = new Area(t.getHitBox());
		intersection.intersect(new Area(path));
		if(!intersection.isEmpty())
			return true;
		return false;
		
	}
	//Goes through solid walls
	 @Override
	public void moveCoordinate(int xMove, int yMove) {
		Transformer transformer = new Transformer(hitBox);
		Shape movedHitBox = transformer.move(xMove, yMove);		
		hitBox = movedHitBox; 
	}


	
	
}

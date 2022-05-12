package Wizard_Maze.things.movings.spells;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Path2D;

import Wizard_Maze.states.GameState;
import Wizard_Maze.things.Thing;

public class LightningSpell extends Spell{
	
	private Path2D.Double path;
	
	//--------------------------------------------------------------------------\\
	
	//Constructor
	public LightningSpell(GameState gameState) {
		super(gameState, 0, 0, 0, 15, 15, null, DEFAULT_TRIPTIME / 2);
		speed =  DEFAULT_SPEED * 4f;
		
		path = new Path2D.Double();
	}
	
	//Every tick appending a new htibox
	@Override
	public void tick() {
		getDirection();
		move();
		path.append(hitBox, false);
	}	
	
	//Rendering the whole path
	@Override
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setColor(Color.red);
		g2d.fill(path);
		g2d.dispose();		
	}

	//Checking if the player collides with any points of the spell path
	@Override
	public boolean doCollide(Thing t) {
		Area intersection = new Area(t.getHitBox());
		intersection.intersect(new Area(path));
		if(!intersection.isEmpty())
			return true;
		return false;
		
	}

	
	
}

package Wizard_Maze.things.movings.spells;

import Wizard_Maze.gfx.Assets;
import Wizard_Maze.states.GameState;

public class ControllSpell extends Spell{
	
	public ControllSpell(GameState gameState) {
		super(gameState, 0, 0, 0, 20, 20, Assets.spells[1], DEFAULT_TRIPTIME);
		speed =  DEFAULT_SPEED * 2f;
	}
	
	//Overrides the basic move method, when casted, the player controlls the spell, meanwhile can't move
	@Override
	public void move() {
		player.setFrozen(true);
		
		xMove = (int) (speed * Math.sin(Math.toRadians(angle)));
		yMove = (int) (-speed * Math.cos(Math.toRadians(angle)));
		double rotateAngle = player.getRotateAngle();
		
		player.setxMove(0);
		player.setyMove(0);
		player.setRotateAngle(0);
		
		moveCoordinate(xMove, 0);
		moveCoordinate(0, yMove);
		rotate(rotateAngle);	
		
		
	}

	
	
}
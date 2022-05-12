package Wizard_Maze.things.movings.spells;


import Wizard_Maze.gfx.Assets;
import Wizard_Maze.states.GameState;

public class SpeedSpell extends Spell{

	public SpeedSpell(GameState gameState) {
		super(gameState, 0, 0, 0, 15, 15, Assets.spells[2], DEFAULT_TRIPTIME / 2);
		speed =  DEFAULT_SPEED * 5f;
	}

	
}

package Wizard_Maze.things.movings.spells;

import Wizard_Maze.gfx.Assets;
import Wizard_Maze.states.GameState;

public class BasicSpell extends Spell{
	
	public BasicSpell(GameState gameState) {
		super(gameState, 0, 0, 0, 20, 20, Assets.spells[0], DEFAULT_TRIPTIME);
		speed =  DEFAULT_SPEED * 2f;
	}

	
	
}

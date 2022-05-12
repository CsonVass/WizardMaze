package Wizard_Maze.things.movings.spells;

import Wizard_Maze.gfx.Assets;
import Wizard_Maze.states.GameState;

public class LongBigSpell extends Spell{

	public LongBigSpell(GameState gameState) {
		super(gameState, 0, 0, 0, 30, 30, Assets.spells[0], DEFAULT_TRIPTIME * 3);
		speed =  DEFAULT_SPEED * 2;
	}
	
	
}


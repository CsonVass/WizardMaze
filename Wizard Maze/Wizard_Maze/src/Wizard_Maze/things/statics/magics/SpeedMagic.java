package Wizard_Maze.things.statics.magics;

import Wizard_Maze.gfx.Assets;
import Wizard_Maze.states.GameState;
import Wizard_Maze.things.movings.spells.SpeedSpell;

public class SpeedMagic extends Magic{

	public SpeedMagic(GameState gameState, int x, int y) {
		super(gameState, x, y, Assets.magics[3], new SpeedSpell(gameState));
	}

	
}

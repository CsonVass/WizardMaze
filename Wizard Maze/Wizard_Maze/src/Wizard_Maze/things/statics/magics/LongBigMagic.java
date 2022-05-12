package Wizard_Maze.things.statics.magics;

import Wizard_Maze.gfx.Assets;
import Wizard_Maze.states.GameState;
import Wizard_Maze.things.movings.spells.LongBigSpell;

public class LongBigMagic extends Magic {
	
	public LongBigMagic(GameState gameState, int x, int y) {
		super(gameState, x, y, Assets.magics[1], new LongBigSpell(gameState));
	}


}

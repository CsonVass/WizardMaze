package Wizard_Maze.things.statics.magics;

import Wizard_Maze.gfx.Assets;
import Wizard_Maze.states.GameState;
import Wizard_Maze.things.movings.spells.LightningSpell;

public class LightningMagic extends Magic {
	

	public LightningMagic(GameState gameState, int x, int y) {
		super(gameState, x, y, Assets.magics[0], new LightningSpell(gameState));
		
	}


}

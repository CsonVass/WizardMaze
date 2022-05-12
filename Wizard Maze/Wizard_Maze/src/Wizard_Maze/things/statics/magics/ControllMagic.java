package Wizard_Maze.things.statics.magics;

import Wizard_Maze.gfx.Assets;
import Wizard_Maze.states.GameState;
import Wizard_Maze.things.movings.spells.ControllSpell;

public class ControllMagic extends Magic {

	public ControllMagic(GameState gameState, int x, int y) {
		super(gameState, x, y, Assets.magics[2], new ControllSpell(gameState));
	}
}

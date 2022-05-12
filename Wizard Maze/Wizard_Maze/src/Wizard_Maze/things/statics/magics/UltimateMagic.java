package Wizard_Maze.things.statics.magics;

import Wizard_Maze.gfx.Assets;
import Wizard_Maze.states.GameState;
import Wizard_Maze.things.movings.Player;
import Wizard_Maze.things.movings.spells.UltimateSpell;

public class UltimateMagic extends Magic {
	
	public UltimateMagic(GameState gameState, int x, int y) {
		super(gameState, x, y, Assets.magics[4], new UltimateSpell(gameState));
	}

	//with 10% probability kills the player who picks it up
	@Override
	public void hitBy(Player player) {
		int r = (int) (Math.random() * 100);
		int p = 10;
		
		if(r < p) {
			System.out.println(r);
			player.kill();
		}else {
			player.getSpellManager().add(containedSpell);
		}
		kill();
	}

}

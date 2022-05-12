package Wizard_Maze.things.statics.magics;


import java.awt.image.BufferedImage;

import Wizard_Maze.states.GameState;
import Wizard_Maze.things.movings.Player;
import Wizard_Maze.things.movings.spells.Spell;
import Wizard_Maze.things.statics.StaticThing;

public abstract class Magic extends StaticThing{

	
	//-------------------------ATTRIBUTES---------------------------\\
	public static final int DEFAULT_WEAPON_WIDTH = 30, 
							DEFAULT_WEAPON_HEIGHT = 30;
	
	protected Spell containedSpell;

	//--------------------------------------------------------------------------\\
	
	//Constructor
	public Magic(GameState gameState, int x, int y, BufferedImage texture, Spell containedSpell) {
		super(gameState, x, y, 0, DEFAULT_WEAPON_WIDTH, DEFAULT_WEAPON_HEIGHT, texture);
		this.containedSpell = containedSpell;
	}
	
	//Collision events
	public void hitBy(Player player) {
		player.getSpellManager().add(containedSpell);
		containedSpell.setPlayer(player);
		kill();
	}
	
	//Removing magic
	public void kill() {
		gameState.getMaze().getThingManager().removeThing(this);
	}
}

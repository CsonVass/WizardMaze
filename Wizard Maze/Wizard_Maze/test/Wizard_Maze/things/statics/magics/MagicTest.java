package Wizard_Maze.things.statics.magics;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import Wizard_Maze.Game;
import Wizard_Maze.mazes.Maze;
import Wizard_Maze.states.GameState;
import Wizard_Maze.things.movings.Player;
import Wizard_Maze.things.movings.spells.SpeedSpell;

public class MagicTest {
	
	Player p1;
	Game game;
	GameState gameState;
	Maze maze;
	Magic magic;
	
	@Before
	public void setUp() {
		Game game = new Game("test", 200, 100);
		game.init();
		gameState = new GameState(game, game.getDisplay().getGame_screen(), 2, "test");
		game.setGameState(gameState);
		maze = gameState.getMaze();
		p1 = maze.getPlayers()[0];
		magic = new SpeedMagic(gameState, 10, 10);
	}
	
	@Test
	public void hitByPlayerTest() {
		maze.getThingManager().addThing(magic);
		
		Assert.assertTrue(maze.getThingManager().getThings().contains(magic));
		
		p1.collideWith(magic);
		
		Assert.assertFalse(maze.getThingManager().getThings().contains(magic));
		Assert.assertTrue(p1.getSpellManager().getAmmunition().getClass().equals(new SpeedSpell(gameState).getClass()));
		
	}
	
}

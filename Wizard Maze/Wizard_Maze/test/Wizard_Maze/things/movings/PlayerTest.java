package Wizard_Maze.things.movings;

import org.junit.Test;

import Wizard_Maze.Game;
import Wizard_Maze.mazes.Maze;
import Wizard_Maze.states.GameState;

import org.junit.Assert;
import org.junit.Before;

public class PlayerTest {
	
	Player p1;
	Player p2;
	Game game;
	GameState gameState;
	Maze maze;
	
	@Before
	public void setUp() {
		Game game = new Game("test", 200, 100);
		game.init();
		gameState = new GameState(game, game.getDisplay().getGame_screen(), 2, "test");
		game.setGameState(gameState);
		maze = gameState.getMaze();
		p1 = maze.getPlayers()[0];
		p2 = maze.getPlayers()[1];
	}
	
	@Test
	public void testAddPoint() {		
		int points_before = p1.getPoints();
		p1.addPoint();
		int points_after = p1.getPoints();
		Assert.assertEquals(0, points_before);
		Assert.assertEquals(1, points_after);
	}
	
	@Test
	public void testLoadPoint() {
		gameState.loadGame("res/saved_games/two_player/test.txt");
		Assert.assertEquals(5, p1.getPoints());
		Assert.assertEquals(6, p2.getPoints());
	}
	
	@Test
	public void testKill() {
		int playersAlive = Player.getPLAYERS_ALIVE();
		p1.kill();
		Assert.assertEquals(playersAlive - 1, Player.getPLAYERS_ALIVE() );
		Assert.assertFalse(p1.isAlive());
		Assert.assertFalse(maze.getThingManager().getThings().contains(p1));
		
	}
	
	@Test
	public void moveCoordinatesTest() {
		double x0 = p1.getHitBox().getBounds().getX(), y0 = p1.getHitBox().getBounds().getY();
		p1.setxMove(1);
		p1.setyMove(1);
		p1.move();
		Assert.assertEquals((int) x0 + 1, (int) p1.getHitBox().getBounds().getX());
		Assert.assertEquals((int) y0 + 1, (int) p1.getHitBox().getBounds().getY());
	}
	
	@Test
	public void moveAngleTest() {
		double angle0 = p1.getAngle();
		p1.setRotateAngle(60);
		p1.move();
		Assert.assertEquals((int) angle0 + 60, (int) p1.getAngle());
	}
	
	

	

}

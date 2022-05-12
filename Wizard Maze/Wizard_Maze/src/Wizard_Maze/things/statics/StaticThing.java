package Wizard_Maze.things.statics;

import java.awt.image.BufferedImage;

import Wizard_Maze.states.GameState;
import Wizard_Maze.things.Thing;

public abstract class StaticThing extends Thing {

	public StaticThing(GameState gameState, int x, int y, double angle, int width, int height, BufferedImage texture) {
		super(gameState, x, y, angle, width, height, width, height, texture);
	}

}

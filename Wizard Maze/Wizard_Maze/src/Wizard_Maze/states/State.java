package Wizard_Maze.states;

import java.awt.CardLayout;

import Wizard_Maze.Game;
import Wizard_Maze.display.screens.Screen;

public abstract class State {
	
	//Game state static
	private static State currentState = null;	
	
	public static void setState(Game game, State state) {
		currentState = state;
		CardLayout cl = (CardLayout) (game.getDisplay().getScreens().getLayout());
		cl.show(game.getDisplay().getScreens(), state.screen.getName());
		state.screen.refresh();
	}
	
	public static State getState() {
		return currentState;
	}
	
	//CLASS
	
	protected Game game;
	protected Screen screen;
	
	public State(Game game, Screen screen) {
		this.game = game;
		this.screen = screen;
	}
	
	public abstract void tick();
	
	public abstract void render();
	
	public Game getGame() {
		return game;
	}


	
	
	
}

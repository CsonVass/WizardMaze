package Wizard_Maze;

import java.util.Timer;

import Wizard_Maze.display.Display;
import Wizard_Maze.gfx.Assets;
import Wizard_Maze.states.GameState;
import Wizard_Maze.states.LoadState;
import Wizard_Maze.states.MenuState;
import Wizard_Maze.states.NewGameState;
import Wizard_Maze.states.State;



public class Game implements Runnable{
	
	//-------------------------ATTRIBUTES---------------------------\\
	
	//Display variables
	private Display display;
	private String title;
	private int width, height;
	
	//Thread variables
	private boolean running = false;
	private Thread thread;
	
	//Satetes
	public State menuState;
	public State  gameState;
	public State loadState;
	public State newGameState;
	
	//Timer
	private Timer timer;
	
	
	//-------------------------CONSTRUCTOR---------------------------\\
	public Game(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
	}
	
	//-------------------------METHODS---------------------------\\
	
	//Tick and Render
	public void tick() {
		if(State.getState() != null) {
			State.getState().tick();
		}
	}
	
	public void render() {
		if(State.getState() != null) {
			State.getState().render();
		}
	}
	
	public void startGameState(int numberOfPlayers, String saveName) {
		gameState = new GameState(this, display.getGame_screen(), numberOfPlayers, saveName);
	}
	

	
	//Thread methods
	
	public void init() {		
		//loading in graphical elements
		Assets.init();
		
		//Generating timer
		timer = new Timer();
				
		//generating display		
		display = new Display(title, width, height);
		
		
		menuState = new MenuState(this, display.getMenu_screen());
		loadState = new LoadState(this, display.getLoad_screen());
		newGameState = new NewGameState(this, display.getNew_game_screen());
		
		State.setState(this, menuState);
		
	}
	

	@Override
	public void run() {		
		init();
		
		int fps = 60;
		double timePerTick = 1000000000L / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long ticks = 0;
		long timerInt = 0;
		
		while(running) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timerInt += now - lastTime;
			lastTime = now;
			
			if(delta >= 1) {
				tick();
				render();
				ticks++;
				delta--;
			}
			if(timerInt >= 1000000000L) {
				System.out.println("FPS:" + ticks);
				ticks = 0;
				timerInt = 0;
			}
		}
		stop();
		timer.cancel();
		display.getFrame().dispose();
	}
	
	public synchronized void start() {
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		if(!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	//-------------------------GETTERS & SETTERS---------------------------\\

	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	public Display getDisplay() {
		return display;
	}

	public void setDisplay(Display display) {
		this.display = display;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public Thread getThread() {
		return thread;
	}

	public void setThread(Thread thread) {
		this.thread = thread;
	}

	public State getMenuState() {
		return menuState;
	}

	public void setMenuState(State menuState) {
		this.menuState = menuState;
	}

	public State getGameState() {
		return gameState;
	}

	public void setGameState(State gameState) {
		this.gameState = gameState;
	}

	public State getLoadState() {
		return loadState;
	}

	public void setLoadState(State loadState) {
		this.loadState = loadState;
	}

	public State getNewGameState() {
		return newGameState;
	}

	public void setNewGameState(State newGameState) {
		this.newGameState = newGameState;
	}

	
	
}

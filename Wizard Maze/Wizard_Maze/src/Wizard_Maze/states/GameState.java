package Wizard_Maze.states;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.IOException;

import Wizard_Maze.Game;
import Wizard_Maze.display.screens.GameScreen;
import Wizard_Maze.display.screens.Screen;
import Wizard_Maze.mazes.Maze;
import Wizard_Maze.things.input.KeyManager;
import Wizard_Maze.tiles.Tile;
import Wizard_Maze.utils.Utils;

public class GameState extends State{
	
	//-------------------------ATTRIBUTES---------------------------\\
	private Maze maze;
	private String saveName;
	private int numberOfPlayers = 2;
	private GameScreen gameScreen;
	
	//Graphics
	private BufferStrategy bs;
	private Graphics g;

	//Inputs
	private KeyManager keyManager;	
	
	//--------------------------------------------------------------------------\\

	//Constructor
	public GameState(Game game, Screen screen, int numberOfPlayers, String saveName) {
		super(game, screen);
		this.maze = new Maze(this, "res/mazes", numberOfPlayers);
		this.numberOfPlayers = numberOfPlayers;
		this.saveName = saveName;
		this.gameScreen = (GameScreen) screen;
		gameScreen.setGameName(saveName);
		
		keyManager = new KeyManager();
		
		gameScreen.getCanvas().setFocusable(true);
		gameScreen.getCanvas().addKeyListener(keyManager);
	}

	@Override
	public void tick() {
		maze.tick();
	}

	@Override
	public void render() {
		bs = gameScreen.getCanvas().getBufferStrategy();
		if(bs == null) {
			gameScreen.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		//Clear all
		g.clearRect(0, 0, gameScreen.getCanvas().getWidth(), gameScreen.getCanvas().getHeight());
		
		//start drawing
		maze.render(g);
		
		//points draw
		g.setFont(new Font("ZapfDingbats", Font.BOLD, 40));
		for(int i = 0; i < numberOfPlayers; i++) {
			g.drawImage(maze.getPlayers()[i].getTexture(), Tile.TILEWIDTH * (13 * (i+1) + 10 * i), gameScreen.getCanvas().getHeight() - Tile.TILEWIDTH * 5 + 5, 75, 75, null);
			g.drawString(Integer.toString(maze.getPlayers()[i].getPoints()), Tile.TILEWIDTH * (13 * (i+1) + 10 * i + 7), gameScreen.getCanvas().getHeight() - Tile.TILEWIDTH * 2);
		}
		
		//finish drawing
		bs.show();
		g.dispose();
	}
	
	public void saveGame() {
		saveName = gameScreen.getGameName();
		String path = "res/saved_games/";
		if(numberOfPlayers == 2)
			path += "two_player/";
		else if(numberOfPlayers == 3)
			path += "three_player/";
		path += saveName + ".txt";
		String points[] = new String[numberOfPlayers];
		for(int i = 0; i < numberOfPlayers; i++) {
			points[i] = Integer.toString(maze.getPlayers()[i].getPoints());
		}
		try {
			Utils.saveFile(path, points);
		}catch(IOException e) {
			gameScreen.setGameName("Not valid name!");
		}
	}
	
	public void loadGame(String path) {
		String file = Utils.loadFileAsString(path);
		
		// s+ regex for 1 or more whitespace characters
		String[] tokens = file.split("\\s+");
		
		//loading in the earnerd points from previous game
		for(int i = 0; i < numberOfPlayers; i++) {
			try {
				maze.getPlayers()[i].setPoints(Integer.parseInt(tokens[i]));
			}catch(Error e) {
				System.out.println(e);
			}
		}
	}
	
	
	
	//-------------------------SETTERS & GETTERS---------------------------\\	

	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}
	
	public void setNumberOfPlayers(int i) {
		numberOfPlayers = i;
	}
	
	public KeyManager getKeyManager() {
		return keyManager;
	}
	
	public Maze getMaze() {
		return maze;
	}
	

}

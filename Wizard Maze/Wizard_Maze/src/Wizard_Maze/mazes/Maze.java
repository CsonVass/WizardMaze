package Wizard_Maze.mazes;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import Wizard_Maze.gfx.Assets;
import Wizard_Maze.states.GameState;
import Wizard_Maze.things.ThingManager;
import Wizard_Maze.things.movings.Player;
import Wizard_Maze.things.statics.magics.MagicManager;
import Wizard_Maze.tiles.Tile;
import Wizard_Maze.utils.Utils;

public class Maze {
	
	//-------------------------------ATTRIBUTES--------------------------\\

	private int width, height;
	private int[][] tiles;
	
	private int[] spawnX;
	private int[] spawnY;	
	
	private Player[] players;
	
	//GameState
	private GameState gameState;
	
	//Path to mazes folder
	private String path;
	
	//Things
	private ThingManager thingManager;	
	
	//Magic
	private MagicManager magicManager;
	

	//--------------------------------------------------------------------------\\
	
	//Constructor
	public Maze(GameState gameState, String path, int numberOfPlayers) {
		this.gameState = gameState;
		this.path = path;
		this.players = new Player[numberOfPlayers];
		
		//Spawn points
		spawnX = new int[3];
		spawnY = new int[3];
		
		//Controll keys
		int[] p1 = {KeyEvent.VK_W,KeyEvent.VK_S, KeyEvent.VK_A,KeyEvent.VK_D, KeyEvent.VK_Q } ;
		int[] p2 = {KeyEvent.VK_UP,KeyEvent.VK_DOWN, KeyEvent.VK_LEFT,KeyEvent.VK_RIGHT, KeyEvent.VK_M } ;
		int[] p3 = {KeyEvent.VK_NUMPAD8, KeyEvent.VK_NUMPAD5, KeyEvent.VK_NUMPAD4, KeyEvent.VK_NUMPAD6, KeyEvent.VK_NUMPAD0} ;
		
		int[][] pKeys = new int[3][4];
		pKeys[0] = p1;
		pKeys[1] = p2;
		pKeys[2] = p3;
		
		//Creating players and giving them the keys
		for(int i = 0; i < numberOfPlayers; i++) {
			players[i] = new Player(gameState, 0, 0, 0, Assets.players[i]);
			players[i].setControl_keys(pKeys[i]);
			players[i].setSpellManager(gameState);
		}
		
		//Loading in maze
		loadMaze(path);
		
		//Generating thing manager
		thingManager = new ThingManager();
		
		//Setting the spawn points for each player
		for(int i = 0; i < numberOfPlayers; i++) {
			players[i].setX(spawnX[i] * Tile.TILEWIDTH);
			players[i].setY(spawnY[i] * Tile.TILEHEIGHT);
			thingManager.addThing(players[i]);
		}
		
		//All players alive at the start
		Player.setPLAYERS_ALIVE(numberOfPlayers);		
		
		
		//Generating magic manager and giving it the maze
		magicManager = new MagicManager(this);
		
		//Starting the magic manager
		magicManager.start();
	}
	
	//Changing maze
	public void changeMaze() {
		loadMaze(path);
		thingManager.removeAll();
		thingManager = new ThingManager();
		for(int i = 0; i < gameState.getNumberOfPlayers(); i++) {
			players[i].setX(spawnX[i] * Tile.TILEWIDTH);
			players[i].setY(spawnY[i] * Tile.TILEHEIGHT);
			if(players[i].isAlive())
				players[i].addPoint();
			players[i].setAlive(true);
			thingManager.addThing(players[i]);
		}
		Player.setPLAYERS_ALIVE(gameState.getNumberOfPlayers());
		
	}
	
	//-----------------------TICK AND RENDER METHODS----------------------------\\
	public void tick() {
		if(Player.getPLAYERS_ALIVE() < 2) {
			changeMaze();
		}
		thingManager.tick();
	}
	
	public void render(Graphics g) {
		
		//rendering tiles (only solid for efficiency)
		for (int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				if(getTile(x, y).isSolid())
					getTile(x,y).render(g, (int) (x * Tile.TILEWIDTH), (int) (y * Tile.TILEHEIGHT));
			}
		}		
		
		//rendering all things in thing manager
		thingManager.render(g);
	}
	//-----------------------------------------------------------------------------\\
	
	//loading in the maze 
	public void loadMaze(String path) {
		//getting tiles from file
		String mazes [] = Utils.filePaths(path);
		int r = (int) (Math.random() * mazes.length);
		String mazeToLoad = path + "/" + mazes[r];
		String file = Utils.loadFileAsString(mazeToLoad);
		
		
		// s+ regex for 1 or more whitespace characters
		String[] tokens = file.split("\\s+");
		
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		
		spawnX[0] = Utils.parseInt(tokens[2]);
		spawnY[0] = Utils.parseInt(tokens[3]);
		
		spawnX[1] = Utils.parseInt(tokens[4]);
		spawnY[1] = Utils.parseInt(tokens[5]);
		
		spawnX[2] = Utils.parseInt(tokens[6]);
		spawnY[2] = Utils.parseInt(tokens[7]);
		

		//Setting up the tiles
		tiles = new int[width][height];
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				tiles[x][y] = Utils.parseInt(tokens[(x+y*width) + 8]);
			}
		}
	}
	
	
	
	//------------------------GETTERS&SETTERS---------------------------------------\\
	public Tile getTile(int x, int y) {
		//in case of glitching out
		if(x < 0 || y < 0 || x >= width || y >= height) {
			return Tile.floorTile;
		}
		
		Tile t = Tile.tile_types[tiles[x][y]];
		
		if(t == null)
			return Tile.floorTile;
		
		return t;		
	}
	
	public Player[] getPlayers() {
		return players;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}

	public ThingManager getThingManager() {
		return thingManager;
	}
	
	public GameState getGameState() {
		return gameState;
	}
	
}

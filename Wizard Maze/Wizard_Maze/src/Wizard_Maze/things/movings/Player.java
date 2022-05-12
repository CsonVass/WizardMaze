package Wizard_Maze.things.movings;


import java.awt.image.BufferedImage;

import Wizard_Maze.states.GameState;
import Wizard_Maze.things.Thing;
import Wizard_Maze.things.movings.spells.Spell;
import Wizard_Maze.things.movings.spells.SpellManager;
import Wizard_Maze.things.statics.magics.Magic;
import Wizard_Maze.tiles.Tile;

public class Player extends MovingThing{	
	
	//-------------------------ATTRIBUTES---------------------------\\	
	//static player size
	public static final int DEFAULT_PLAYER_WIDTH = Tile.TILEWIDTH * 2, 
						DEFAULT_PLAYER_HEIGHT = Tile.TILEHEIGHT * 2;
	
	
	//static player counter
	private static int PLAYERS_ALIVE = 0;
	
	
	//points of player
	private int points;
	
	//control keys
	private int[] control_keys;
	private boolean casted = false;
	private boolean alive = true;
	
	//Spell Manager
	private SpellManager spellManager;
	
	private boolean frozen = false;
	
	//--------------------------------------------------------------------------\\
	
	//Constructor
	public Player(GameState gameState, int x, int y, double angle, BufferedImage texture) {
		super(gameState, x, y, angle, DEFAULT_PLAYER_WIDTH, DEFAULT_PLAYER_HEIGHT, DEFAULT_PLAYER_WIDTH/2, DEFAULT_PLAYER_HEIGHT / 2, texture);
		this.points = 0;
		PLAYERS_ALIVE++;
	}
	

	//for every tick getting input from keyboard, checking collision and if not frozen, move
	@Override
	public void tick() {
		getInput();
		checkThingCollisionsWithThings(xMove, yMove);
		if(!frozen)
			move();
	}
	
	//Death method
	@Override
	public void kill() {
		alive = false;
		gameState.getMaze().getThingManager().removeThing(this);
		PLAYERS_ALIVE--;
	}
	
	
	
	//Collision events
	public void collideWith(Thing t) {
		t.hitBy(this);
	}
	
	public void hitBy(Player p) {}
	
	public void hitBy(Spell b) {}
	
	public void hitBy(Magic m) {}
	 
	

	//Casting spells
	public void cast() {
		spellManager.cast();
	}
	
	//Getting input from keyboard
	public void getInput() {
		xMove = 0;
		yMove = 0;
		rotateAngle = 0;
		
		
		//"up"
		if(gameState.getKeyManager().isKeyPressed(control_keys[0])) {
			xMove = (int) (speed * Math.sin(Math.toRadians(angle)));
			yMove = (int) (-speed * Math.cos(Math.toRadians(angle)));
		}
		
		//"down"
		if(gameState.getKeyManager().isKeyPressed(control_keys[1])) {
			xMove = (int) (-speed * Math.sin(Math.toRadians(angle)));
			yMove = (int) (speed * Math.cos(Math.toRadians(angle)));
		}
		
		//"left"
		if(gameState.getKeyManager().isKeyPressed(control_keys[2])) {
			rotateAngle = - 3;
		}
		
		//"right"
		if(gameState.getKeyManager().isKeyPressed(control_keys[3])) {
			rotateAngle = 3;
		}
		
		//shoot
		if(casted == false && gameState.getKeyManager().isKeyPressed(control_keys[4])) {
			cast();
			casted = true;
		}
		//not_shoot
		if(casted == true && !gameState.getKeyManager().isKeyPressed(control_keys[4])) {
			casted = false;
		}
	}

	
	
	//Getters & Setters
	public static void setPLAYERS_ALIVE(int i) {
		PLAYERS_ALIVE = i;
	}
	
	public static int getPLAYERS_ALIVE() {
		return PLAYERS_ALIVE;
	}
	
	public void setSpellManager(GameState gameState) {
		spellManager = new SpellManager(gameState, this);
	}
	
	public void setControl_keys(int[] control_keys) {
		this.control_keys = control_keys;
	}
	
	public void setAlive(boolean b) {
		if(b)
			spellManager.reset();
		alive = b;
	}
	
	public boolean isAlive() {
		return alive;
	}
	
	public void addPoint() {
		points++;
	}
	
	public int getPoints() {
		return points;
	}
	
	public void setPoints(int n) {
		points = n;
	}
	
	public BufferedImage getTexture() {
		return texture;
	}
	
	public SpellManager getSpellManager() {
		return spellManager;
	}

	public boolean isFrozen() {
		return frozen;
	}

	public void setFrozen(boolean frozen) {
		this.frozen = frozen;
	}
	
}

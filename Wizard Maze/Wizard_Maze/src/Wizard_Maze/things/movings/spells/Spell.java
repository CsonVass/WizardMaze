package Wizard_Maze.things.movings.spells;

import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

import Wizard_Maze.states.GameState;
import Wizard_Maze.things.movings.MovingThing;
import Wizard_Maze.things.movings.Player;

public abstract class Spell extends MovingThing{

	//-------------------------ATTRIBUTES---------------------------\\
	protected static int DEFAULT_TRIPTIME = 10;
	
	protected int tripTime; 	
	
	protected Timer timer;
	
	protected Player player;
	
	//--------------------------------------------------------------------------\\
	
	//Constructor
	public Spell(GameState gameState, int x, int y, double angle, int width, int height, BufferedImage texture, int tripTime) {
		super(gameState, x, y, angle, width, height, width, height, texture);
		this.tripTime = tripTime;
		
		timer = gameState.getGame().getTimer();
	}
	
	@Override
	public void tick() {
		getDirection();
		move();
	}	
	
	//The xMove and yMove for moving spells
	public void getDirection() {
		xMove = (int) (speed * Math.sin(Math.toRadians(angle)));
		yMove = (int) (-speed * Math.cos(Math.toRadians(angle)));
	}
	
	//When shot, player has to wait until automatically reload
	public void shoot(Player player) {
		Spell thisSpell = this;
		TimerTask task = new TimerTask() {
			public void run() {
				gameState.getMaze().getThingManager().removeThing(thisSpell);
				player.getSpellManager().reset();
			}
		};
		long delay = 1000L * tripTime;
		timer.schedule(task, delay);
	}
	
	
	//Collision events
	
	@Override
	public void hitWall(int xMove, int yMove) {
		if(xMove != 0) {
			angle *= -1;
		}
		if(yMove != 0) {
			angle = (angle + 180) * -1;
		}
	}

	//default for spells
	public void hitBy(Player p) {	
		p.kill();
		kill();
	}
	
	//deleting itself from thing manager
	public void kill() {
		gameState.getMaze().getThingManager().removeThing(this);
	}
	

	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public static int getDEFAULT_TRIPTIME() {
		return DEFAULT_TRIPTIME;
	}

}

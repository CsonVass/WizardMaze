package Wizard_Maze.things.statics.magics;

import java.util.Timer;
import java.util.TimerTask;

import Wizard_Maze.mazes.Maze;
import Wizard_Maze.tiles.Tile;

public class MagicManager {	
	
	//-------------------------ATTRIBUTES---------------------------\\
	private Maze maze;
	private Timer timer;

	//--------------------------------------------------------------------------\\
	
	//Constructor
	public MagicManager(Maze maze) {
		this.maze = maze;
		timer = maze.getGameState().getGame().getTimer();
		
	}
	
	//Starting the spawning of magic boxes
	public void start() {
		//every 5 seconds do this
		TimerTask task = new TimerTask() {
			public void run() {
				//Probability of spawning 75%
				int probability = 75;
				int r = (int) (Math.random() * 100);
					if(r < probability) {
						//Spawn random magic to random place
						int magicIndex = (int) (Math.random() * 5);
						int x = (int) (Math.random() * ((maze.getWidth() - 2) * Tile.TILEWIDTH) + Tile.TILEWIDTH);
						int y = (int) (Math.random() * ((maze.getHeight() - 2) * Tile.TILEHEIGHT) + Tile.TILEHEIGHT);
						Magic spawnedMagic;
						switch(magicIndex) {
						case 0:
							spawnedMagic = new LongBigMagic(maze.getGameState(), x, y);
							break;
						case 1:
							spawnedMagic = new LightningMagic(maze.getGameState(), x, y);
							break;
						case 2:
							spawnedMagic = new SpeedMagic(maze.getGameState(), x, y);
							break;
						case 3:
							spawnedMagic = new UltimateMagic(maze.getGameState(), x, y);
							break;
						case 4:
							spawnedMagic = new ControllMagic(maze.getGameState(), x, y);
							break;
						default:
							spawnedMagic = new LongBigMagic(maze.getGameState(), x, y);
						}
						spawnedMagic.setX(x);
						spawnedMagic.setY(y);
						maze.getThingManager().addThing(spawnedMagic);
					}
			}
		};
		long delay = 5 * 1000;
		long period = 5 * 1000;
		timer.schedule(task, delay, period);
	}
	
	
}

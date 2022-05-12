package Wizard_Maze.things.movings.spells;

import java.awt.Rectangle;
import java.awt.geom.Area;

import Wizard_Maze.states.GameState;
import Wizard_Maze.things.movings.Player;
import Wizard_Maze.things.positioning.Transformer;
import Wizard_Maze.tiles.Tile;

public class SpellManager {
	
	//-------------------------ATTRIBUTES---------------------------\\
	private Player player;
		
	private Spell ammunition;	
	
	private GameState gameState;
	
	//--------------------------------------------------------------------------\\
	
	//Constructor
	public SpellManager(GameState gameState, Player player){
		this.gameState = gameState;
		this.player = player;	
		
		reset();
	}
	
	//Casting the spell
	public void cast() {
		//Checking if player has ammunition
		if(ammunition == null)
			return;
		
		//Starting coordinates for the spell
		Rectangle hitBoxBounds = player.getHitBox().getBounds();
		
		ammunition.setX((int) player.getHitBox().getBounds().getCenterX() + (int) (hitBoxBounds.getWidth() / 2 * Math.sqrt(2) + 8));
		ammunition.setY((int) player.getHitBox().getBounds().getY());
		
		Transformer tf = new Transformer(ammunition.getHitBox());
		ammunition.setHitBox(tf.rotate(player.getAngle() - 90, (int) hitBoxBounds.getCenterX(), (int) hitBoxBounds.getCenterY()));
		ammunition.setAngle(player.getAngle());


		int dx = (int) (ammunition.getHitBox().getBounds().getWidth() / Tile.TILEWIDTH) + 2;
		int dy = (int) (ammunition.getHitBox().getBounds().getHeight() / Tile.TILEHEIGHT) + 2;
		
		int tx = (int) (ammunition.getHitBox().getBounds().getX() / Tile.TILEWIDTH);
		int ty = (int) (ammunition.getHitBox().getBounds().getY() / Tile.TILEHEIGHT);
		
		//Directly next to a wall can't cast
		Area boundingBoxArea = new Area(ammunition.getHitBox().getBounds());
		for(int i = tx; i < tx + dx; i++) {
			for(int j = ty; j < ty + dy; j++) {
				if(gameState.getMaze().getTile(i, j).isSolid()) {
					Area intersection = boundingBoxArea;
					Area tileArea = new Area(new Rectangle(i*Tile.TILEWIDTH, j*Tile.TILEHEIGHT, Tile.TILEWIDTH, Tile.TILEHEIGHT));
					intersection.intersect(tileArea);
					if(!intersection.isEmpty()) {
						return;
					}
				}
			}
		}
		//shooting
		gameState.getMaze().getThingManager().addThing(ammunition);
		ammunition.shoot(player);	
		
		ammunition = null;
				
	}
	
	//reseting ammunition
	public void reset() {
		player.setFrozen(false);
		add(new BasicSpell(gameState));
	}
	//adding ammunition
	public void add(Spell spell) {
		ammunition = spell;
		ammunition.setPlayer(player);
	}
	
	public Spell getAmmunition() {
		return ammunition;
	}

}

package Wizard_Maze.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {
	
	//-------------------------ATTRIBUTES---------------------------\\
	//statics
	public static Tile[] tile_types = new Tile[2];
	public static Tile floorTile = new FloorTile(0);
	public static Tile WallTile = new WallTile(1);
	
	public static final int TILEWIDTH = 16, TILEHEIGHT = 16;
	
	protected BufferedImage texture;
	protected final int id;
	
	//--------------------------------------------------------------------------\\
	
	//Constructor
	public Tile(BufferedImage texture, int id) {
		this.texture = texture;
		this.id = id;
		
		tile_types[id] = this;
	}
	
	public void render(Graphics g, int x, int y) {
		g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
	}
	
	public boolean isSolid() {
		return false;
	}
	
	public int getId() {
		return id;
	}
	
	

}

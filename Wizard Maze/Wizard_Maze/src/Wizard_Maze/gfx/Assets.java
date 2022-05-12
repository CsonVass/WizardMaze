package Wizard_Maze.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Assets {
	
	private static final int width = 64, height = 64;
	
	public static BufferedImage floor, wall;
	public static BufferedImage[] players;
	public static BufferedImage[] magics;
	public static BufferedImage[] btn_start;
	public static BufferedImage[] btn_load;
	public static BufferedImage[] btn_exit;
	public static BufferedImage[] btn_back;
	public static BufferedImage[] btn_save;
	public static BufferedImage[] btn_twoPlayers;
	public static BufferedImage[] btn_threePlayers;
	public static BufferedImage[] spells;
	public static BufferedImage logo;
	
	public static void init() {
		SpriteSheet sheet = new SpriteSheet(loadImage("/textures/sheet.png"));
		
		players = new BufferedImage[3];
		magics = new BufferedImage[5];
		btn_start = new BufferedImage[2];
		btn_load = new BufferedImage[2];
		btn_exit = new BufferedImage[2];
		btn_back = new BufferedImage[2];
		btn_save = new BufferedImage[2];
		btn_twoPlayers = new BufferedImage[2];
		btn_threePlayers = new BufferedImage[2];
		spells = new BufferedImage[3];
		
		floor = sheet.crop(width, height, width, height);
		wall = sheet.crop(0, height * 2, width, height);
		
		players[0] = sheet.crop(0, 0, width, height);
		players[1] = sheet.crop(width, 0, width, height);
		players[2] = sheet.crop(0, height, width, height);
		
		magics[0] = sheet.crop(width, height * 2, width, height);
		magics[1] = sheet.crop(width, height * 3, width, height);
		magics[2] = sheet.crop(width, height * 4, width, height);
		magics[3] = sheet.crop(width, height * 5, width, height);
		magics[4] = sheet.crop(width, height * 6, width, height);
		
		btn_start[0] = sheet.crop(width * 2, 0, width * 2, height);
		btn_start[1] = sheet.crop(width * 4, 0, width * 2, height);
		
		btn_load[0] = sheet.crop(width * 2, height, width * 2, height);
		btn_load[1] = sheet.crop(width * 4, height, width * 2, height);
		
		btn_exit[0] = sheet.crop(width * 2, height * 2, width * 2, height);
		btn_exit[1] = sheet.crop(width * 4, height * 2, width * 2, height);
		
		btn_back[0] = sheet.crop(width * 2, height * 3, width * 2, height);
		btn_back[1] = sheet.crop(width * 4, height * 3, width * 2, height);		
		
		btn_save[0] = sheet.crop(width * 2, height * 6, width * 2, height);
		btn_save[1] = sheet.crop(width * 4, height * 6, width * 2, height);		
		
		btn_twoPlayers[0] = sheet.crop(width * 2, height * 4, width * 2, height);
		btn_twoPlayers[1] = sheet.crop(width * 4, height * 4, width * 2, height);
		
		btn_threePlayers[0] = sheet.crop(width * 2, height * 5, width * 2, height);
		btn_threePlayers[1] = sheet.crop(width * 4, height * 5, width * 2, height);
		
		spells[0] = sheet.crop(0, height * 3, width, height);
		spells[1] = sheet.crop(0, height * 4, width, height);
		spells[2] = sheet.crop(0, height * 5, width, height);
		
		logo = sheet.crop(0, height * 9, width * 6, height * 3);
		
		
	}
	
	public static BufferedImage loadImage(String path) {
		try {
			return ImageIO.read(Assets.class.getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
	

	

	
	
	
}

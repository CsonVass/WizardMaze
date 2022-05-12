package Wizard_Maze.display;

import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Wizard_Maze.display.screens.GameScreen;
import Wizard_Maze.display.screens.LoadScreen;
import Wizard_Maze.display.screens.MenuScreen;
import Wizard_Maze.display.screens.NewGameScreen;



public class Display {

	//Frame 
	private JFrame frame;
	private String title;
	private int width, height;
	
	//Panels
	private JPanel screens;
	
	private MenuScreen menu_screen;
	private GameScreen game_screen;
	private LoadScreen load_screen;
	private NewGameScreen new_game_screen;

	
	//Constructor
	public Display(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
		
		createDisplay();
	}
	
	//-----------------------------Methods----------------------------------//	
	
	private void createDisplay() {
		//frame settings
		frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		frame.setResizable(false);		

		frame.setLocationRelativeTo(null);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//panel settings
		menu_screen = new MenuScreen("Menu", this);
		game_screen = new GameScreen("Game", this);
		load_screen = new LoadScreen("Load", this);
		new_game_screen = new NewGameScreen("New Game", this);
		

		screens = new JPanel(new CardLayout());
		
		//putting all together		
		
		screens.add(menu_screen, "Menu");
		screens.add(game_screen, "Game");
		screens.add(load_screen, "Load");
		screens.add(new_game_screen, "New Game");
		
		
		frame.add(screens);
		frame.pack();
	}
	
	
	//GETTERS & SETTERS

	public JFrame getFrame() {
		return frame;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public JPanel getScreens() {
		return screens;
	}

	public MenuScreen getMenu_screen() {
		return menu_screen;
	}

	public GameScreen getGame_screen() {
		return game_screen;
	}

	public LoadScreen getLoad_screen() {
		return load_screen;
	}

	public NewGameScreen getNew_game_screen() {
		return new_game_screen;
	}

}

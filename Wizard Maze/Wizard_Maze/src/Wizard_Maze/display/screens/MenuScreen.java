package Wizard_Maze.display.screens;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JLabel;

import Wizard_Maze.display.Display;
import Wizard_Maze.gfx.Assets;
import Wizard_Maze.gfx.MyScaledImageIcon;
import Wizard_Maze.states.State;

@SuppressWarnings("serial")  //compiler required
public class MenuScreen extends Screen {
	
	//-------------------------ATTRIBUTES---------------------------\\	
	private JLabel logo;
	
	private JButton newGameButton;
	private JButton loadButton;
	private JButton exitButton;
	
	//sizes	
	private int dy;
	private int bw;
	private int bh;
	
	//Icons textures
	private MyScaledImageIcon logoIcon;
	
	private MyScaledImageIcon startIcon;
	private MyScaledImageIcon startOverIcon;
	
	private MyScaledImageIcon loadIcon;
	private MyScaledImageIcon loadOverIcon;
	
	private MyScaledImageIcon exitIcon;
	private MyScaledImageIcon exitOverIcon;
	
	
	//-------------------------ACTION LISTENER CLASSES---------------------------\\
	public class NewGameButtonActionListener implements ActionListener{
		public void actionPerformed(ActionEvent ae) {
			State state = State.getState();
			State.setState(state.getGame(), state.getGame().newGameState);
		}
	}
	
	public class LoadButtonActionListener implements ActionListener{
		public void actionPerformed(ActionEvent ae) {
			State state = State.getState();
			State.setState(state.getGame(), state.getGame().loadState);
		}
	}
	
	public class ExitButtonActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			State.getState().getGame().setRunning(false);
		}
		
	}
	
	//--------------------------------------------------------------------------\\
	
	//Constructor
	public MenuScreen(String name, Display display) {
		super(name, display);
		
		this.bw = width / 4;
		this.bh = height / 10;
		this.dy = bh * 3 / 4;
		
		//Icons
		logoIcon = new MyScaledImageIcon(Assets.logo, bw * 2, bh * 3);
		
		startIcon = new MyScaledImageIcon(Assets.btn_start[0], bw, bh);
		startOverIcon = new MyScaledImageIcon(Assets.btn_start[1], bw, bh);		
		
		loadIcon = new MyScaledImageIcon(Assets.btn_load[0], bw, bh);
		loadOverIcon = new MyScaledImageIcon(Assets.btn_load[1], bw, bh);		
		
		
		exitIcon = new MyScaledImageIcon(Assets.btn_exit[0], bw, bh);
		exitOverIcon = new MyScaledImageIcon(Assets.btn_exit[1], bw, bh);

		
		//Creating the screen
		createScreen();
		
		//Action listeners to buttons	
		NewGameButtonActionListener newGameListener = new NewGameButtonActionListener();
		newGameButton.addActionListener(newGameListener);
		
		LoadButtonActionListener loadListener = new LoadButtonActionListener();
		loadButton.addActionListener(loadListener);
		
		ExitButtonActionListener exitListener = new ExitButtonActionListener();
		exitButton.addActionListener(exitListener);
	}
	
	//Putting together
	public void createScreen() {
		
		setLayout(null);		
		
		//Logo
		logo = new JLabel(logoIcon);
		logo.setBounds(bw / 2, dy, bw * 3, bh * 3);
		
		//Buttons

		newGameButton = new JButton(startIcon);
		newGameButton.setRolloverIcon(startOverIcon);
		newGameButton.setContentAreaFilled(false);
		newGameButton.setBorderPainted(false);
		newGameButton.setBounds(bw * 2 - bw / 2, dy * 2 + (bh * 3), bw, bh);
		
		
		loadButton = new JButton(loadIcon);
		loadButton.setRolloverIcon(loadOverIcon);
		loadButton.setContentAreaFilled(false);
		loadButton.setBorderPainted(false);
		loadButton.setBounds(bw * 2 - bw / 2, dy * 3 + (bh * 4), bw, bh);

		
		exitButton = new JButton(exitIcon);
		exitButton.setRolloverIcon(exitOverIcon);
		exitButton.setContentAreaFilled(false);
		exitButton.setBorderPainted(false);
		exitButton.setBounds(bw * 2 - bw / 2, dy * 4 + (bh * 5), bw, bh);
		
		add(logo);
		add(newGameButton);
		add(loadButton);
		add(exitButton);
	}

	@Override
	public void refresh() {}
	

}

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
public class NewGameScreen extends Screen {
	
	//-------------------------ATTRIBUTES---------------------------\\	
	private JLabel logo;

	private JButton twoPlayerButton;
	private JButton threePlayerButton;
	private JButton backButton;
	
	//sizes	
	private int dy;
	private int bw;
	private int bh;
	
	//Icons textures
	private MyScaledImageIcon logoIcon;
	
	private MyScaledImageIcon twoPlayerIcon;
	private MyScaledImageIcon twoPlayerOverIcon;
	
	private MyScaledImageIcon threePlayerIcon;
	private MyScaledImageIcon threePlayerOverIcon;
	
	private MyScaledImageIcon backIcon;
	private MyScaledImageIcon backOverIcon;
	
	//-------------------------ACTION LISTENER CLASSES---------------------------\\
	public class TwoPlayerButtonActionListener implements ActionListener{
		public void actionPerformed(ActionEvent ae) {
			State state = State.getState();
			state.getGame().startGameState(2, "Unnamed game");
			State.setState(state.getGame(), state.getGame().gameState);
		}
	}
	
	public class ThreePlayerButtonActionListener implements ActionListener{
		public void actionPerformed(ActionEvent ae) {
			State state = State.getState();
			state.getGame().startGameState(3, "Unnamed game");
			State.setState(state.getGame(), state.getGame().gameState);
		}
	}
	
	public class BacktButtonActionListener implements ActionListener{
		public void actionPerformed(ActionEvent ae) {
			State state = State.getState();
			State.setState(state.getGame(), state.getGame().menuState);
		}
		
	}
		
	//--------------------------------------------------------------------------\\
	
	//Constructor
	public NewGameScreen(String name, Display display) {
		super(name, display);
		this.bw = width / 4;
		this.bh = height / 10;
		this.dy = bh * 3 / 4;
		
		//Icons
		logoIcon = new MyScaledImageIcon(Assets.logo, bw * 2, bh * 3);
		
		twoPlayerIcon = new MyScaledImageIcon(Assets.btn_twoPlayers[0], bw, bh);
		twoPlayerOverIcon = new MyScaledImageIcon(Assets.btn_twoPlayers[1], bw, bh);		
		
		threePlayerIcon = new MyScaledImageIcon(Assets.btn_threePlayers[0], bw, bh);
		threePlayerOverIcon = new MyScaledImageIcon(Assets.btn_threePlayers[1], bw, bh);		
		
		backIcon = new MyScaledImageIcon(Assets.btn_back[0], bw, bh);
		backOverIcon = new MyScaledImageIcon(Assets.btn_back[1], bw, bh);
		
		//Creating the screen
		createScreen();
		
		//Action listeners to buttons		
		TwoPlayerButtonActionListener twoPlayerListener = new TwoPlayerButtonActionListener();
		twoPlayerButton.addActionListener(twoPlayerListener);
		
		ThreePlayerButtonActionListener threePlayerListener = new ThreePlayerButtonActionListener();
		threePlayerButton.addActionListener(threePlayerListener);
		
		BacktButtonActionListener backButtonListener = new BacktButtonActionListener();
		backButton.addActionListener(backButtonListener);
	}
	
	
	//Putting together
	public void createScreen() {
		
		setLayout(null);
		
		//Logo
		logo = new JLabel(logoIcon);
		logo.setBounds(bw / 2, dy, bw * 3, bh * 3);
		
		//Buttons

		twoPlayerButton = new JButton(twoPlayerIcon);
		twoPlayerButton.setRolloverIcon(twoPlayerOverIcon);
		twoPlayerButton.setContentAreaFilled(false);
		twoPlayerButton.setBorderPainted(false);
		twoPlayerButton.setBounds(bw * 2 - bw / 2, dy * 2 + (bh * 3), bw, bh);
		
		
		threePlayerButton = new JButton(threePlayerIcon);
		threePlayerButton.setRolloverIcon(threePlayerOverIcon);
		threePlayerButton.setContentAreaFilled(false);
		threePlayerButton.setBorderPainted(false);
		threePlayerButton.setBounds(bw * 2 - bw / 2, dy * 3 + (bh * 4), bw, bh);

		
		backButton = new JButton(backIcon);
		backButton.setRolloverIcon(backOverIcon);
		backButton.setContentAreaFilled(false);
		backButton.setBorderPainted(false);
		backButton.setBounds(bw * 2 - bw / 2, dy * 4 + (bh * 5), bw, bh);
		
		add(logo);
		add(twoPlayerButton);
		add(threePlayerButton);
		add(backButton);
	}

	@Override
	public void refresh() {}


}

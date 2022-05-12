package Wizard_Maze.display.screens;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Wizard_Maze.display.Display;
import Wizard_Maze.gfx.Assets;
import Wizard_Maze.gfx.MyScaledImageIcon;
import Wizard_Maze.states.GameState;
import Wizard_Maze.states.State;

@SuppressWarnings("serial")  //compiler required
public class GameScreen extends Screen {
	
	//-------------------------ATTRIBUTES---------------------------\\
	private Canvas canvas;
	
	private JPanel buttonPanel;
	
	private JButton saveButton;
	private JButton exitButton;
	
	private JTextField gameNameField;
	
	//Icons	textures
	private MyScaledImageIcon saveIcon;
	private MyScaledImageIcon saveOverIcon;
	
	private MyScaledImageIcon exitIcon;
	private MyScaledImageIcon exitOverIcon;
	
	//-------------------------ACTION LISTENER CLASSES---------------------------\\
	public class SavetButtonActionListener implements ActionListener{
		public void actionPerformed(ActionEvent ae) {
			GameState state = (GameState) State.getState();
			state.saveGame();
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
	public GameScreen(String name, Display display) {
		super(name, display);
		
		//Icons
		saveIcon = new MyScaledImageIcon(Assets.btn_save[0], 100, 40);
		saveOverIcon = new MyScaledImageIcon(Assets.btn_save[1], 100, 40);		
		
		exitIcon = new MyScaledImageIcon(Assets.btn_exit[0], 100, 40);
		exitOverIcon = new MyScaledImageIcon(Assets.btn_exit[1], 100, 40);		
		
		//Creating screen
		createScreen();
		
		//Action listeners to buttons	
		SavetButtonActionListener saveButtonListener = new SavetButtonActionListener();
		saveButton.addActionListener(saveButtonListener);
		
		BacktButtonActionListener backButtonListener = new BacktButtonActionListener();
		exitButton.addActionListener(backButtonListener);
		
	}
	
	//Putting together
	public void createScreen() {
		setLayout(new BorderLayout());
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new BorderLayout());		
		
		//canvas settings
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setFocusable(true);
		
		saveButton = new JButton(saveIcon);	
		saveButton.setRolloverIcon(saveOverIcon);
		saveButton.setBorderPainted(false);
		saveButton.setContentAreaFilled(false);
		
		
		exitButton = new JButton(exitIcon);	
		exitButton.setRolloverIcon(exitOverIcon);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		
		gameNameField = new JTextField();
		gameNameField.setFont(new Font(Font.SERIF, Font.PLAIN, 40));
		gameNameField.setVisible(true);
		gameNameField.setText("Name of your game");
		
		buttonPanel.add(saveButton, BorderLayout.WEST);
		buttonPanel.add(exitButton, BorderLayout.EAST);
		buttonPanel.add(gameNameField, BorderLayout.CENTER);
		
		add(canvas, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
		
	}

	@Override
	public void refresh() {}
	
	
	//-------------------------SETTERS & GETTERS---------------------------\\	
	public String getGameName() {
		return gameNameField.getText();
	}
	
	public void setGameName(String name) {
		gameNameField.setText(name);
	}
	
	public Canvas getCanvas() {
		return canvas;
	}	
	
	
}

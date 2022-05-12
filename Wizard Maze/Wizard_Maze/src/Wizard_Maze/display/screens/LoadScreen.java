package Wizard_Maze.display.screens;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Wizard_Maze.display.Display;
import Wizard_Maze.gfx.Assets;
import Wizard_Maze.gfx.MyScaledImageIcon;
import Wizard_Maze.states.GameState;
import Wizard_Maze.states.State;
import Wizard_Maze.utils.Utils;

@SuppressWarnings("serial")  //compiler required
public class LoadScreen extends Screen {
	
	//-------------------------ATTRIBUTES---------------------------\\	
	private JPanel buttonPanel;
	private JPanel scrollPanePanel;
	private JPanel textPanel;
	
	private JButton backButton;
	private JButton startButton;
	
	private JScrollPane twoPlayerScroll;
	private JScrollPane threePlayerScroll;
	
	private DefaultListModel<String> twoPlayerListModel;
	private DefaultListModel<String> threePlayerListModel;
	
	private JList<String> twoPlayerList;
	private JList<String> threePlayerList;
	
	private JLabel twoPlayer;
	private JLabel threePlayer;
	
	//data for loading in	
	private String loadName = "Unnamed";
	private int numberOfPlayers;
		
	private String twoPlayerSaveFiles []; 
	private String threePlayerSaveFiles []; 
	
	//sizes	
	private int bw;
	private int bh;
	
	
	//Icons textures	
	private MyScaledImageIcon loadGameIcon;
	private MyScaledImageIcon loadGameOverIcon;
	
	private MyScaledImageIcon backIcon;
	private MyScaledImageIcon backOverIcon;
	
	//-------------------------ACTION LISTENER CLASSES---------------------------\\
			
	//Buttons
	public class StartButtonActionListener implements ActionListener{
		public void actionPerformed(ActionEvent ae) {
			State state = State.getState();
			state.getGame().startGameState(numberOfPlayers, loadName);
			State.setState(state.getGame(), state.getGame().gameState);
			GameState gameState = (GameState) state.getGame().gameState;
			String path = "res/saved_games/";
			if(numberOfPlayers == 2)
				path += "two_player/";
			if(numberOfPlayers == 3)
				path += "three_player/";
			path += loadName + ".txt";
			gameState.loadGame(path);
			
		}
	}
	
	public class BacktButtonActionListener implements ActionListener{
		public void actionPerformed(ActionEvent ae) {
			State state = State.getState();
			State.setState(state.getGame(), state.getGame().menuState);
		}
		
	}
	
	
	//Lists
	public class TwoPlayerListActionlistener implements ListSelectionListener{
		
		@Override
		public void valueChanged(ListSelectionEvent e) {
			threePlayerList.clearSelection();		
			startButton.setEnabled(true);
			loadName = (String) twoPlayerList.getSelectedValue();
			numberOfPlayers = 2;
		}
		
	}
	
	public class ThreePlayerListActionlistener implements ListSelectionListener{

		@Override
		public void valueChanged(ListSelectionEvent e) {
			twoPlayerList.clearSelection();	
			startButton.setEnabled(true);
			loadName = (String) threePlayerList.getSelectedValue();
			numberOfPlayers = 3;
		}
		
	}
			
	//--------------------------------------------------------------------------\\
	
	//Constructor
	public LoadScreen(String name, Display display) {
		super(name, display);
		
		this.height = display.getHeight();
		this.bw = width / 4;
		this.bh = height / 10;		
		
		//Icons		 
		loadGameIcon = new MyScaledImageIcon(Assets.btn_load[0], bw, bh);
		loadGameOverIcon = new MyScaledImageIcon(Assets.btn_load[1], bw, bh);		
		
		backIcon = new MyScaledImageIcon(Assets.btn_back[0], bw, bh);
		backOverIcon = new MyScaledImageIcon(Assets.btn_back[1], bw, bh);
		
		//Creating the screen
		createScreen();
		
		//Action listeners to buttons & lists
		StartButtonActionListener startButtonListener = new StartButtonActionListener();
		startButton.addActionListener(startButtonListener);
		
		BacktButtonActionListener backButtonListener = new BacktButtonActionListener();
		backButton.addActionListener(backButtonListener);
		
		TwoPlayerListActionlistener twoPlayerListActionlistener = new TwoPlayerListActionlistener();
		twoPlayerList.addListSelectionListener(twoPlayerListActionlistener);
		
		ThreePlayerListActionlistener threePlayerListActionlistener = new ThreePlayerListActionlistener();
		threePlayerList.addListSelectionListener(threePlayerListActionlistener);		
	}
	
	
	//Putting together
	public void createScreen() {
		
		setLayout(new BorderLayout());
	
		//Buttons	
		startButton = new JButton(loadGameIcon);
		startButton.setRolloverIcon(loadGameOverIcon);
		startButton.setContentAreaFilled(false);
		startButton.setBorderPainted(false);
		startButton.setEnabled(false);
		
		backButton = new JButton(backIcon);
		backButton.setRolloverIcon(backOverIcon);
		backButton.setContentAreaFilled(false);
		backButton.setBorderPainted(false);
		
		//Button panel
		buttonPanel = new JPanel();
		buttonPanel.add(backButton);
		buttonPanel.add(startButton);
		
		//File name lists
		twoPlayerListModel = new DefaultListModel<String>();
		threePlayerListModel = new DefaultListModel<String>();
		
		twoPlayerList = new JList<String>();
		threePlayerList = new JList<String>();
		
		//Setting the lists with listmodels
		setLists();
		
		twoPlayerList.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
		threePlayerList.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
		
		twoPlayerList.setBackground(Color.LIGHT_GRAY);
		threePlayerList.setBackground(Color.LIGHT_GRAY);
		
		//Scroll for filenames	
		twoPlayerScroll = new JScrollPane(threePlayerList);		
		threePlayerScroll = new JScrollPane(twoPlayerList);		
	
		
		//ScrollPane Panel
		scrollPanePanel = new JPanel();
		scrollPanePanel.setLayout(new GridLayout(1,2));
		scrollPanePanel.add(threePlayerScroll);
		scrollPanePanel.add(twoPlayerScroll);
		
		
		//JLabels
		twoPlayer = new JLabel("2 PLAYER");
		threePlayer = new JLabel("3 PLAYER");
		twoPlayer.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
		threePlayer.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
		
		
		//Text panel
		textPanel = new JPanel();
		textPanel.setLayout(new GridLayout(1,2));
		
		textPanel.add(twoPlayer);
		textPanel.add(threePlayer);
		
	
		add(textPanel, BorderLayout.NORTH);
		add(scrollPanePanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);

	
	}
	
	//Refreshing the list so closed save file can be reloaded without restarting the game 
	@Override
	public void refresh() {
		setLists();		
		startButton.setEnabled(false);
	}
	
	//Setting the lists with listmodels
	public void setLists() {		
		twoPlayerListModel.clear();
		threePlayerListModel.clear();
		
		twoPlayerSaveFiles = Utils.getFileNamesWithoutExtension("res/saved_games/two_player");
		threePlayerSaveFiles  = Utils.getFileNamesWithoutExtension("res/saved_games/three_player");
		
		for(int i = 0; i < twoPlayerSaveFiles.length; i++)
			twoPlayerListModel.addElement(twoPlayerSaveFiles[i]);
		
		
		for(int i = 0; i < threePlayerSaveFiles.length; i++)
			threePlayerListModel.addElement(threePlayerSaveFiles[i]);
		
		
		twoPlayerList.setModel(twoPlayerListModel);
		threePlayerList.setModel(threePlayerListModel);
	}
	
	
	
}

package Wizard_Maze.display.screens;

import java.awt.Dimension;

import javax.swing.JPanel;

import Wizard_Maze.display.Display;

@SuppressWarnings("serial")  //compiler required
public abstract class Screen extends JPanel{
	
	protected int width, height;
	protected Display display;

	public Screen(String name, Display display) {
		setName(name);		
		this.display = display;
		width = display.getWidth();
		height = display.getHeight();
		
		this.setPreferredSize(new Dimension(width, height));
		this.setMinimumSize(new Dimension(width, height));
		this.setMaximumSize(new Dimension(width, height));
	}
	
	public abstract void createScreen();

	public abstract void refresh();
	
	
}

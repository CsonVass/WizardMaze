package Wizard_Maze.things.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener{
	
	//pressed keys array
	private boolean[] keys;
	
	
	//--------------------------------------------------------------------------\\
	
	//Constructor
	public KeyManager() {
		keys = new boolean[256];
	}
	

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
	public boolean isKeyPressed(int i) {
		if(i < 0 || i > 255)
			return false;
		return keys[i];
	}

}

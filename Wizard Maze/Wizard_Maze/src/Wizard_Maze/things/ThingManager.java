package Wizard_Maze.things;

import java.awt.Graphics;
import java.util.ArrayList;


public class ThingManager {
	
	private ArrayList<Thing> things;	
	
	//--------------------------------------------------------------------------\\
	
	//Constructor
	public ThingManager() {
		things = new ArrayList<Thing>();
	}
	
	//ticking all things in thing manager
	public void tick() {
		for(int i = 0; i < things.size(); i++) {
			Thing t = things.get(i);
			t.tick();
		}
	}
	
	//rendering all things in thing manager
	public void render(Graphics g) {
		try {
			for(Thing t : things) {
				t.render(g);
			}
		}catch(Exception e){
			System.out.print(e);
		}
	}
	
	public void addThing(Thing t) {
		things.add(t);
	}
	
	public void removeThing(Thing t) {
		things.remove(t);
	}
	
	public void removeAll() {
		int meret = things.size();
		for(int i = 0; i < meret; i++) {
			things.remove(0);
		}
	}

	
	//Getters & Setters

	public ArrayList<Thing> getThings() {
		return things;
	}
}

package Entity;

import gui.Drawable;
import gui.Window;
import texture.Camera;
import texture.Material;

public class Player extends Drawable{

	private Material mat = new Material("Sprites/Idle.png");
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	public Player(Window window) {
	super(window);
	
	}
	

}

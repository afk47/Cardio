package com.nucleardiesel.cardio.entity;

import com.nucleardiesel.cardio.gui.Scene;
import com.nucleardiesel.cardio.gui.Window;

public class Fireball extends Entity{
	
	private boolean done = false;
	private Scene currentScene;
	private int index;
	
	public Fireball(Window window) {
		super(window);
		doDefaultAnimation();
		size = .4f;
		setPosition(0, 0);
	}
	
	public Fireball(Window window, Scene scene,int index) {
		super(window);
		doDefaultAnimation();
		size = .4f;
		this.index = index;
	}

	@Override
	protected void doDefaultAnimation() {
		setAnimation("Fireball", 1);
	}
	
	@Override
	public void update() {
		super.update();
		addPosition(new float[] {15,0,0});
		
		if(currentframe != lastFrame) {
			setFrame((int) Math.floor(currentframe));
		}
		
		if(position[0] > 200) {
			done = true;
		}
	}
	
	public String getState() {
		return currentAnimation;
	}

	@Override
	public boolean shouldDestroy() {
		return done;
	}
	
	
	
	
	
}

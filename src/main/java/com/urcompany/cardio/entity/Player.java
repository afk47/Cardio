package com.urcompany.cardio.entity;

import com.urcompany.cardio.Main;
import com.urcompany.cardio.gui.Drawable;
import com.urcompany.cardio.gui.Window;
import com.urcompany.cardio.texture.Camera;
import com.urcompany.cardio.texture.Material;
import com.urcompany.cardio.texture.Model;

public class Player extends Drawable {

	private Material mat;
	
	/*
	 * 
	 * 
	 */
	@Override
	public void update() {
		super.update();
		
		if(currentAnimation != "Idle" && animationCompleted) {
			idle();
		}
		setFrame((int) currentframe);
		updateModel(new Model(vertices, texture_coords, indices));
		
		
	}

	public Player(Window window) {
		super(window);
		size = 400;
		position = new float[] { -300, -300, 0 };
		super.setTotalFrames((float) 11);
	}



	public void attack() {
		setAnimation(7, "Attack1", 0.5f);
	}

	protected void refreshTexture() {
		idle();
	}

	private void idle() {
		setAnimation(11, "Idle", 1);
		
	}

}

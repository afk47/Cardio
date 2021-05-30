package com.urcompany.cardio.entity;

import com.urcompany.cardio.Main;
import com.urcompany.cardio.gui.Drawable;
import com.urcompany.cardio.gui.Window;
import com.urcompany.cardio.texture.Texture;
import com.urcompany.cardio.texture.AnimationLoader;
import com.urcompany.cardio.texture.Model;

public class Player extends Drawable {

	@Override
	public void update() {
		super.update();
		if(currentAnimation != "Idle" && animationCompleted) {
			idle();
		}
		setFrame((int) currentframe);
		// updateModel(new Model(vertices, texture_coords, indices));
		
		
	}

	public Player(Window window) {
		super(window);
		attack();
		size = 400;
		setPosition(-300,-300);
	} 



	public void attack() {
		setAnimation("Attack1", 0.5f);
		setTotalFrames(7);
	}

	protected void refreshTexture() {
		attack();
	}

	private void idle() {
		setAnimation("Idle", 1);
		
	}

}

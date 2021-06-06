package com.nucleardiesel.cardio.entity;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import com.nucleardiesel.cardio.Main;
import com.nucleardiesel.cardio.gui.Drawable;
import com.nucleardiesel.cardio.gui.Window;
import com.nucleardiesel.cardio.texture.AnimationLoader;
import com.nucleardiesel.cardio.texture.Model;
import com.nucleardiesel.cardio.texture.Texture;

public class Player extends Entity{

	
	
	public Player(Window window) {
		super(window);
		doDefaultAnimation();
		size = 2;
		setPosition(-300, -300);
	}
	
	public void update() {
		super.update();
		if (currentAnimation.equals("Jump") && animationCompleted) {
			fall();
		}
		if(currentAnimation.equals("Jump")) {
			addPosition(new float[] { 0f, 20f, 0f });
		}
		if (!currentAnimation.equals("Idle") && animationCompleted) {
			idle();
		}
		if(currentframe != lastFrame) {
			setFrame((int) Math.floor(currentframe));
		}
		
	}
	public void fall() {
		setAnimation("Fall", .1f);
		
	}

	public void attack(){
		setAnimation("Attack1", 0.5f);
	}


	public void idle() {
		setAnimation("Idle", 1);

	}
	
	public void run() {
		if(currentAnimation != "Attack1" && currentAnimation != "Run" || !(currentframe < frames-1))
		setAnimation("Run", .5f);

	}

	@Override
	protected void doDefaultAnimation() {
		setAnimation("Idle", 1);
		
	}
	
	public void turnLeft(){
		if(!flippedHorizontal) {
			flipHorizontal();
		}
		flippedHorizontal = true;
	}

	public void turnRight() {
		if(flippedHorizontal) {
			flipHorizontal();
		}
		flippedHorizontal = false;
	}

	public void jump() {
		setAnimation("Jump", .1f);
		
	}
	
	public String getState() {
		return currentAnimation;
	}
	
	
	

}

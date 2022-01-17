package com.nucleardiesel.cardio.entity;

import org.joml.Matrix4f;

import com.nucleardiesel.cardio.gui.Drawable;
import com.nucleardiesel.cardio.gui.Scene;
import com.nucleardiesel.cardio.gui.Window;
import com.nucleardiesel.cardio.texture.Model;
import com.nucleardiesel.cardio.texture.Texture;
import com.nucleardiesel.cardio.texture.TexturedModel;

public class SlashEffect extends Entity{

	private boolean done = false;
	private Scene currentScene;
	private int index;
	
	public SlashEffect(Window window) {
		super(window);
		doDefaultAnimation();
		size = .4f;
		setPosition(0, 0);
	}
	
	public SlashEffect(Window window, Scene scene,int index) {
		super(window);
		doDefaultAnimation();
		size = .4f;
		this.index = index;
	}

	@Override
	protected void doDefaultAnimation() {
		setAnimation("slash", .5f);
	}
	
	@Override
	public void update() {
		super.update();
		
		
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

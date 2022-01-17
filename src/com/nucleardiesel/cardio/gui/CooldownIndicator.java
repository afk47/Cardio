package com.nucleardiesel.cardio.gui;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import com.nucleardiesel.cardio.Main;
import com.nucleardiesel.cardio.texture.Model;
import com.nucleardiesel.cardio.texture.Texture;
import com.nucleardiesel.cardio.texture.TexturedModel;

public class CooldownIndicator implements Drawable{

	TexturedModel model = new TexturedModel("/images/Healthbar.png");

	Matrix4f pos;
	Matrix4f target;
	protected Matrix4f projection;
	float size = 1;
	float[] position = new float[] { -100, 100, 0 };
	private Window win;
	private float cooldown;
	private float time = 0;
	private boolean leftJustified = true;

	private boolean shouldDestroy = false;
	
	public CooldownIndicator(Window window, float cooldown) {
		win = window;
		projection = new Matrix4f().setOrtho2D(-window.getWidth() / 2, window.getWidth() / 2, -window.getHeight() / 2,
				window.getHeight() / 2);
		size = 4f;
		position = new float[] { 0, 300, 0 };
		this.cooldown = cooldown;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void bind(int sampler) {
		model.bind(sampler);
	}

	@Override
	public Matrix4f translate() {

		target = new Matrix4f();
		pos = new Matrix4f().setTranslation(new Vector3f(position)).scale(((cooldown - time)/cooldown) * 20, 1 , 1);
		pos.scale(size);
		target = projection.mul(pos, target);

		return target;
	}

	@Override
	public void setAnimation(String file, float length) {

	}

	@Override
	public void setFrame(int f) {

	}

	@Override
	public void setPosition(float x, float y) {
		position[0] = x;
		position[1] = y;
	}

	@Override
	public void setTotalFrames(float f) {
	}

	@Override
	public TexturedModel getModel() {
		return model;
	}

	@Override
	public void setTexture(Texture mat) {
		model.setTexture(mat);
	}

	@Override
	public void updateModel(Model newModel) {
		model.setModel(newModel);
	}

	@Override
	public void addPosition(float[] v) {
		for (int i = 0; i < v.length; i++) {
			position[i] += v[i];
		}
	}

	@Override
	public void update() {
		float timepassed = (float) Main.getPassed();
		
		time +=timepassed;
		if(time >= cooldown) {
			shouldDestroy = true;
		}
		
	}

	@Override
	public String getState() {
		// TODO Auto-generated method stub
		return "Cooldown";
	}

	@Override
	public boolean shouldDestroy() {
		// TODO Auto-generated method stub
		return shouldDestroy;
	}
}

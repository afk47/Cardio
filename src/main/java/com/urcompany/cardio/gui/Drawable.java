package com.urcompany.cardio.gui;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import com.urcompany.cardio.Main;
import com.urcompany.cardio.texture.Camera;
import com.urcompany.cardio.texture.Material;
import com.urcompany.cardio.texture.Model;

public abstract class Drawable {

	protected float[] vertices = new float[] { -1f, 1f, 0, 1f, 1f, 0, 1f, -1f, 0, -1f, -1f, 0, };

	protected float[] texture_coords = new float[] { 0, 0, 1, 0, 1, 1, 0, 1, };

	protected int[] indices = new int[] { 0, 1, 2, 2, 3, 0 };

	protected Material mat = new Material("/Sprites/Take Hit.png");;
	protected int states;
	protected Camera cam;
	protected Matrix4f projection;
	protected float[] position = { 0, 0, 0 };
	protected float size = 400;
	protected Model model = new Model(vertices, texture_coords, indices);
	protected Window win;
	protected float currentframe = 0;
	protected float frames = 1;

	Matrix4f target;
	Matrix4f pos;

	protected float animationTimer = 0;
	protected float animationLength = 0;
	protected boolean animationCompleted = false;
	protected String currentAnimation = "Take Hit";
	
	/*
	 * 
	 * Creates an object in the center of the screen with a texture not rendered until 
	 * renderer object uses render(Drawable) method
	 * 
	 * 
	 */
	public Drawable(Window window) {
		win = window;
		projection = new Matrix4f().setOrtho2D(-window.getWidth() / 2, window.getWidth() / 2, -window.getHeight() / 2,
				window.getHeight() / 2);
		cam = new Camera(window.getWidth(), window.getHeight());
		mat.remove();
		refreshTexture();
	}

	public void update() {
		animationTimer += Main.getPassed();
		
		if(animationTimer >= animationLength) {
			animationTimer = 0;
			animationCompleted = true;
		}
		
		currentframe = frames * (animationTimer / animationLength); 
		
		
		
		
//		projection = new Matrix4f().setOrtho2D(-win.getWidth() / 2, win.getWidth() / 2, -win.getHeight() / 2, win.getHeight() / 2); //Creates distortion?? supposed to prevent it lol
	}

	public void bind(int sampler) {
		mat.bind(sampler);
	}

	protected abstract void refreshTexture();

	/*
	 * Moves the object to the target position
	 * 
	 */
	public Matrix4f translate() {
		target = new Matrix4f();
		pos = new Matrix4f().setTranslation(new Vector3f(position)).scale(size);

		target = projection.mul(pos, target);
		return target;
	}

	// GETTERS AND SETTERS
	protected void setAnimation(float framesTotal, String file, float length) {
		mat.remove();
		animationTimer = 0;
		currentAnimation = file;
		setMaterial(new Material("/Sprites/" + file + ".png"));
		animationLength = length;
		frames = framesTotal;
		animationCompleted = false;
	}

	protected void setFrame(int f) {
		float framepos = 1 / frames;
		framepos = framepos * f;
		texture_coords = new float[] { 0 + framepos, 0, 1 / frames + framepos, 0, 1 / frames + framepos, 1,
				0 + framepos, 1, };
	}

	protected void setTotalFrames(float f) {
		frames = f;
	}

	public Model getModel() {
		return model;
	}

	public void setMaterial(Material mat) {
		this.mat = mat;
	}

	public void updateModel(Model newModel) {
		model = newModel;
	}
	
	public void addPosition(float[] v) {
		for(int i = 0; i < v.length; i++) {
			position[i] += v[i];
		}
	}


}

package com.urcompany.cardio.gui;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import com.urcompany.cardio.Main;
import com.urcompany.cardio.texture.Texture;
import com.urcompany.cardio.texture.AnimationLoader;
import com.urcompany.cardio.texture.Model;

public abstract class Drawable {


	protected Texture mat = new Texture("/Sprites/Take Hit.png");
	protected int states;
	protected Matrix4f projection;
	protected float[] position = { 0, 0, 0 };
	protected float size = 400;
	protected Model model = new Model();
	protected Window win;
	protected float currentframe = 0;
	protected float frames = 1;

	Matrix4f target;
	Matrix4f pos;

	protected float animationTimer = 0;
	protected float animationLength = 0;
	protected boolean animationCompleted = false;
	protected String currentAnimation = "Take Hit";
	private AnimationLoader testLoader = new AnimationLoader();;
	
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
		refreshTexture();
	}

	public void update() {
		animationTimer += Main.getPassed();
		
		if(animationTimer >= animationLength) {
			animationTimer = 0;
			animationCompleted = true;
		}
		
		currentframe = frames * (animationTimer / animationLength); 
		
		setFrame((int)Math.floor(currentframe));
		
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
	/*
	 * @Param length animation length (in seconds)
	 * @Param file animation file name
	 * @Param framesTotal total frames in animation
	 * 
	 */
	protected void setAnimation(String file, float length) {
		mat.remove();
		testLoader.loadAnimation(file);
		animationTimer = 0;
		currentframe = 0;
		currentAnimation = file;
		setMaterial(new Texture("/Sprites/" + file + ".png"));
		animationLength = length;
		animationCompleted = false;
		
	}

	protected void setFrame(int f) {
		testLoader.loadFrame(f);
		model = new Model(testLoader.getFrameCoordinates());
		
	}

	public void setPosition(float x, float y) {
		position[0] = x;
		position[1] = y;
	}
	protected void setTotalFrames(float f) {
		frames = f;
	}

	public Model getModel() {
		return model;
	}

	public void setMaterial(Texture mat) {
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

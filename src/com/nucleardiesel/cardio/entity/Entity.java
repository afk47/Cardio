package com.nucleardiesel.cardio.entity;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import com.nucleardiesel.cardio.Main;
import com.nucleardiesel.cardio.gui.Drawable;
import com.nucleardiesel.cardio.gui.Window;
import com.nucleardiesel.cardio.texture.AnimationLoader;
import com.nucleardiesel.cardio.texture.Model;
import com.nucleardiesel.cardio.texture.Texture;
import com.nucleardiesel.cardio.texture.TexturedModel;

public abstract class Entity implements Drawable {

	protected Matrix4f projection;
	protected float[] position = { 0, 0, 0 };
	protected float size = 400;
	private Window win;
	protected float currentframe = 0;
	protected float frames = 0;
	private Matrix4f target;
	private Matrix4f pos;
	protected float animationTimer = 0;
	protected float animationLength = 1;
	protected boolean animationCompleted = false;
	protected String currentAnimation = "";
	private AnimationLoader animLoader = new AnimationLoader();
	protected float lastFrame;
	protected TexturedModel model = new TexturedModel();
	protected boolean flippedHorizontal = false;


	/**
	 * performs an update every frame
	 * 
	 */
	@Override
	public void update() {

		lastFrame = (float) Math.floor(currentframe);
		animationTimer += Main.getPassed();

		if (animationTimer >= animationLength) {
			animationTimer = 0;
			animationCompleted = true;
		}
		//gets current frame by dividing animation frames by time for total animation 
		currentframe = (frames + 1) * (animationTimer / animationLength);

	}

	/**
	 * @param window used to get the width and height of the current window
	 * 
	 */
	public Entity(Window window) {
		win = window;
		projection = new Matrix4f().setOrtho2D(-win.getWidth() / 2, win.getWidth() / 2, -win.getHeight() / 2,
				win.getHeight() / 2);
	}

	protected abstract void doDefaultAnimation();

	@Override
	public void bind(int sampler) {
		model.bind(sampler);
	}

	
	@Override
	public Matrix4f translate() {
		target = new Matrix4f();
		pos = new Matrix4f().setTranslation(new Vector3f(position)).scale(animLoader.getFrameWidth() , animLoader.getFrameHeight(), 1);
		pos.scale(size);
		
		target = projection.mul(pos, target);
		if (flippedHorizontal) {
			target.scale(-1, 1, 1);
		}
		return target;
	}

	/**
	 * mirrors the model for the entity
	 * 
	 */
	public void flipHorizontal() {
		target = new Matrix4f();
		pos = new Matrix4f().setTranslation(new Vector3f(position)).scale(animLoader.getFrameWidth() , animLoader.getFrameHeight(), 1);
		
		pos.scale(-1, 0, -1);
		target = projection.mul(pos, target);
		flippedHorizontal = true;
	}

	
	/**
	 * 
	 * @param file - name of animation
	 * @param length - animation length (in seconds)
	 * @param path - path for file (relative to resources/textures/Sprites)
	 * 
	 */
	@Override
	public void setAnimation(String file, float length) {
		model.remove();
		animLoader.loadAnimation(file);
		frames = animLoader.getTotalFrames();
		animationTimer = 0;
		currentframe = 0;
		currentAnimation = file;
		animationLength = length;
		setTexture(new Texture("/Sprites/" + file + ".png"));

		animationCompleted = false;

	}
	
	/**
	 * 
	 * @param file - name of animation
	 * @param length - animation length (in seconds)
	 * @param path - path for file (relative to resources/textures/Sprites)
	 * 
	 */
	public void setAnimation(String file, float length, String path) {
		setPath(path);
		model.remove();
		animLoader.loadAnimation(file);
		frames = animLoader.getTotalFrames();
		animationTimer = 0;
		currentframe = 0;
		currentAnimation = file;
		animationLength = length;
		setTexture(new Texture("/Sprites/" + path + file + ".png"));
		setFrame(0);
		animationCompleted = false;
		
		
	}
	
	/**
	 * @param f - frame to display
	 * 
	 */
	@Override
	public void setFrame(int f) {
		animLoader.loadFrame(f);
		model.setModel(animLoader.getFrameCoordinates());

	}
	
	/**
	 * @param x X location
	 * @param y Y location
	 */
	@Override
	public void setPosition(float x, float y) {
		position[0] = x;
		position[1] = y;
	}

	/**
	 * @param f number of frames as a float
	 * 
	 */
	@Override
	public void setTotalFrames(float f) {
		frames = f;
	}

	
	@Override
	public TexturedModel getModel() {
		return model;
	}

	/**
	 * @param mat Texture for model
	 * 
	 */
	@Override
	public void setTexture(Texture mat) {
		model.setTexture(mat);
		;
	}
	
	/**
	 * @param mat Texture for model
	 * 
	 */
	@Override
	public void updateModel(Model newModel) {
		model.setModel(newModel);
	}

	/**
	 * @param v float array to be added (x,y,z)
	 * 
	 */
	@Override
	public void addPosition(float[] v) {
		for (int i = 0; i < v.length; i++) {
			position[i] += v[i];
		}
	}

	/**
	 * returns position
	 * 
	 */
	public float[] getPosition() {
		return position;
	}
	
	/**
	 * used to determine whether or not the entity should be destroyed
	 * 
	 */
	public boolean shouldDestroy() {
		return false;
	}
	
	/**
	 * @param s path (relative to Sprites folder)
	 * 
	 */
	public void setPath(String s) {
		animLoader.setPath(s);
	}
}

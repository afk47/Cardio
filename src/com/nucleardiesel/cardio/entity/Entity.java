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

	@Override
	public void update() {
//		if (win.hasResized()) {
//			projection = new Matrix4f().setOrtho2D(-win.getWidth() / 2, win.getWidth() / 2, -win.getHeight() / 2,
//					win.getHeight() / 2);
//		}

		lastFrame = currentframe;
		animationTimer += Main.getPassed();

		if (animationTimer >= animationLength) {
			animationTimer = 0;
			animationCompleted = true;
		}

		currentframe = frames * (animationTimer / animationLength);

	}

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

	public void flipHorizontal() {
		target = new Matrix4f();
		pos = new Matrix4f().setTranslation(new Vector3f(position)).scale(animLoader.getFrameWidth() , animLoader.getFrameHeight(), 1);
		
		pos.scale(-1, 0, -1);
		target = projection.mul(pos, target);
		flippedHorizontal = true;
	}

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

	@Override
	public void setFrame(int f) {
		animLoader.loadFrame(f);


	}

	@Override
	public void setPosition(float x, float y) {
		position[0] = x;
		position[1] = y;
	}

	@Override
	public void setTotalFrames(float f) {
		frames = f;
	}

	@Override
	public TexturedModel getModel() {
		return model;
	}

	@Override
	public void setTexture(Texture mat) {
		model.setTexture(mat);
		;
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

	public float[] getPosition() {
		return position;
	}
	
	public boolean shouldDestroy() {
		return false;
	}
	
	public void setPath(String s) {
		animLoader.setPath(s);
	}
}

package com.nucleardiesel.cardio.gui;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import static org.lwjgl.opengl.GL11.*;

import java.nio.IntBuffer;

import org.lwjgl.opengl.*;

import com.nucleardiesel.cardio.controllers.Input;
import com.nucleardiesel.cardio.texture.AnimationLoader;
import com.nucleardiesel.cardio.texture.Model;
import com.nucleardiesel.cardio.texture.Texture;
import com.nucleardiesel.cardio.texture.TexturedModel;

public class Card implements Drawable, Hoverable {

	protected Matrix4f projection;
	private float[] position = { -700, -500, 0 };
	protected float size = 400;
	private Window win;
	protected float currentframe = 0;
	protected float frames = 0;
	private Matrix4f target;
	private Matrix4f pos;
	protected float animationTimer = 0;
	protected float animationLength = 1;
	protected boolean animationCompleted = false;
	protected String currentAnimation = "Card";
	private AnimationLoader animLoader = new AnimationLoader();
	protected float lastFrame;
	protected TexturedModel model = new TexturedModel();
	protected boolean flippedHorizontal = false;
	private Input input;
	private int count = 0;
	private boolean hovering = false;

	public Card(Window w) {
		win = w;
		input = win.getInput();
		projection = new Matrix4f().setOrtho2D(-win.getWidth() / 2, win.getWidth() / 2, -win.getHeight() / 2,
				win.getHeight() / 2);
		setAnimation("Card", 0);
		setFrame(0);
		size = .2f;
	}

	@Override
	public void update() {
		Vector3f mouse = input.getMousePosition();
		if (invertProj(translate()).testPoint(mouse.x / (win.getWidth() / 2), mouse.y / (win.getHeight() / 2), 0)) {
			if (!hovering) {
				hovering = true;
				hoverStart();
			}
			hoverCallback();
		} else {
			if (hovering) {
				hovering = false;
				hoverEnd();
			}
		}
	}

	@Override
	public void hoverStart() {
		
	}

	@Override
	public void hoverEnd() {
		position[1] = -500;
	}

	@Override
	public void hoverCallback() {
		if(position[1] < -400) {
			position[1] +=5;
		}
		
		if(input.isMouseButtonDown(0)) {
			System.out.println("CLICKED"); 
			
			hovering = false;
			hoverEnd();
			
			playcard();
		}
		
	}

	private void playcard() {
			//TODO implement card playing 
			position[1] = -9999; //Removes Card From Screen Must Implement
	}

	@Override
	public void bind(int sampler) {
		model.bind(sampler);
	}

	public Matrix4f translate() {
		target = new Matrix4f();
		pos = new Matrix4f().setTranslation(new Vector3f(position)).scale(animLoader.getFrameWidth(),
				animLoader.getFrameHeight(), 0.5f);
		pos.scale(size);
		target = projection.mul(pos, target);
		if (flippedHorizontal) {
			target.scale(-1, 1, 0.5f);
		}
		return target;
	}

	public void flipHorizontal() {
		target = new Matrix4f();
		pos = new Matrix4f().setTranslation(new Vector3f(position)).scale(size);
		pos.scale(-1, 0, 0.5f);
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
		model.setModel(animLoader.getFrameCoordinates());

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

	private Matrix4f invertProj(Matrix4f matrix) {
		Matrix4f invertedProjection = matrix.invert(new Matrix4f());
		return invertedProjection;
		// return new Vector4f(eyeCoords.x/eyeCoords.w, eyeCoords.y/eyeCoords.w,
		// eyeCoords.z/eyeCoords.w, 0.0f);

	}

}
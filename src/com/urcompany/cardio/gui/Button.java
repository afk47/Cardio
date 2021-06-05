package com.urcompany.cardio.gui;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import com.urcompany.cardio.controllers.Input;
import com.urcompany.cardio.texture.AnimationLoader;
import com.urcompany.cardio.texture.Model;
import com.urcompany.cardio.texture.Texture;
import com.urcompany.cardio.texture.TexturedModel;
import static org.lwjgl.opengl.GL11.*;

import java.nio.IntBuffer;

import org.lwjgl.opengl.*;

public class Button implements Drawable, Hoverable {

	protected Matrix4f projection;
	private float[] position = { 0, 0, 0 };
	protected float size = 400;
	private Window win;
	protected float currentframe = 0;
	protected float frames = 0;
	private Matrix4f target;
	private Matrix4f pos;
	protected float animationTimer = 0;
	protected float animationLength = 1;
	protected boolean animationCompleted = false;
	protected String currentAnimation = "Button";
	private AnimationLoader animLoader = new AnimationLoader();
	protected float lastFrame;
	protected TexturedModel model = new TexturedModel();
	protected boolean flippedHorizontal = false;
	private Input input;
	private int count = 0;
	private boolean hovering = false;

	public Button(Window w) {
		win = w;
		input = win.getInput();
		projection = new Matrix4f().setOrtho2D(-win.getWidth() / 2, win.getWidth() / 2, -win.getHeight() / 2,
				win.getHeight() / 2);
		setAnimation("Button", 1);
		setFrame(0);
		size = 5;
	}

	@Override
	public void update() {
		Vector3f mouse = input.getMousePosition();
		System.out.println(invertProj(translate()));
		System.out.println(mouse.x / (win.getWidth() / 2) + " " + mouse.y / (win.getHeight() / 2));

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
		System.out.println("hover start called");
	}

	@Override
	public void hoverEnd() {
		System.out.println("hover end called");
	}

	@Override
	public void hoverCallback() {
		System.out.println("Hovering");
		// System.out.println(count++); //FOR TEMPORARY USE ONLY REMOVE AS SOON AS
		// HOVERING WORKS CORRECTLY
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
		   // return new Vector4f(eyeCoords.x/eyeCoords.w, eyeCoords.y/eyeCoords.w, eyeCoords.z/eyeCoords.w, 0.0f);
		
	}

}

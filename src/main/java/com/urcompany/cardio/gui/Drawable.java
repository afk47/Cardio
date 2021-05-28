package com.urcompany.cardio.gui;

import org.joml.Matrix4f;
import org.joml.Vector3f;

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
	Matrix4f target;
	Matrix4f pos;
	
	/*
	 * Creates an object in the center of the screen
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
	public void bind(int sampler) {
		
		//
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

	public void update() {
		projection = new Matrix4f().setOrtho2D(-win.getWidth() / 2, win.getWidth() / 2, -win.getHeight() / 2,
				win.getHeight() / 2);
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

}

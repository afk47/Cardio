package gui;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector3fc;
import org.joml.Vector4f;

import main.Main;
import texture.Camera;
import texture.Material;
import texture.Model;

public abstract class Drawable {

	protected float[] vertices = new float[] { -4f, 0.5f, 0, 4f, 0.5f, 0, 4f, -0.5f, 0, -4f, -0.5f, 0, };

	protected float[] texture_coords = new float[] { 0, 0, 1, 0, 1, 1, 0, 1, };

	protected int[] indices = new int[] { 0, 1, 2, 2, 3, 0 };

	protected Material mat = new Material("Sprites/idle.png");
	protected int states;
	protected Camera cam;
	protected Matrix4f projection;
	protected float[] position = { 0, 0, 0 };
	protected float size = 500;
	protected Model model = new Model(vertices, texture_coords, indices);

	Matrix4f target;
	Matrix4f pos;
	
	/*
	 * Creates an object in the center of the screen
	 * 
	 * 
	 */
	public Drawable(Window window) {
		projection = new Matrix4f().setOrtho2D(-window.getWidth() / 2, window.getWidth() / 2, -window.getHeight() / 2,
				window.getHeight() / 2);
		cam = new Camera(window.getWidth(), window.getHeight());
	}
	public void bind(int sampler) {
		mat.bind(sampler);
	}

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

	public abstract void update();

	public void render() {
		Main.renderer.render(this);

	}

	public Model getModel() {
		return model;
	}

	public void setMaterial(Material mat) {
		this.mat = mat;
	}

}

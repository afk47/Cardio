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

	protected Material mat;
	protected int states;
	protected Camera cam;
	protected Matrix4f projection;
	protected float[] position;
	private Matrix4f scale;
	private int size = 1;
	
	public void bind(int sampler) {
		mat.bind(sampler);
	}
	
	/*
	 * Moves the object to the target position
	 * 
	 */
	private Matrix4f translate() {
		Matrix4f target = new Matrix4f();
		Matrix4f pos = new Matrix4f().setTranslation(new Vector3f(position)).scale(size);

		
		target = projection.mul(pos, target);

		return target;
	}
	
	private void scale(int scale) {
	}
	public abstract void update();
	
	public void render() {
		Main.renderer.render(this);
		
	}

	public abstract Model getModel();

	public Matrix4f getTarget() {
		return translate();
	}
	
}

package com.urcompany.cardio.texture;

import org.joml.Matrix4f;

public class TexturedModel {

	private Texture texture = new Texture("/Sprites/Fall.png");
	private Model model = new Model();

	public TexturedModel(String string) {
		setTexture(string);
	}

	public TexturedModel() {}

	
	public void remove() {
		texture.remove();
	}
	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public void setModel(float[] coords) {
		model = new Model(coords);
	}

	public void render() {
		model.render();
	}
	
	
	//Texture Methods
	public Texture getTexture() {
		
		return texture;
	}

	public void setTexture(Texture texture) {

		this.texture = texture;
	}
	
	public void setTexture(String file) {
		this.texture = new Texture(file);
		
	}

	public void bind(int i) {
		texture.bind(i);
	}
	
	public int getWidth() {
		return texture.getTexture().getWidth();
	}

	public int getHeight() {
		return texture.getTexture().getHeight();
	}
	


}

package com.nucleardiesel.cardio.gui;

import org.joml.Matrix4f;

import com.nucleardiesel.cardio.texture.Model;
import com.nucleardiesel.cardio.texture.Texture;
import com.nucleardiesel.cardio.texture.TexturedModel;

public interface Drawable {

	void update();

	void bind(int sampler);

	/*
	 * Moves the object to the target position
	 * 
	 */
	Matrix4f translate();

	void setAnimation(String file, float length);

	void setFrame(int f);

	void setPosition(float x, float y);

	void setTotalFrames(float f);

	TexturedModel getModel();

	void setTexture(Texture mat);

	void updateModel(Model newModel);

	void addPosition(float[] v);
}

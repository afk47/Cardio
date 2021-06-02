package com.urcompany.cardio.gui;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import com.urcompany.cardio.Main;
import com.urcompany.cardio.texture.Texture;
import com.urcompany.cardio.texture.TexturedModel;
import com.urcompany.cardio.texture.AnimationLoader;
import com.urcompany.cardio.texture.Model;

public interface Drawable {




	public void update();

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


	public void setTexture(Texture mat); 


	public void updateModel(Model newModel); 

	public void addPosition(float[] v); 



}

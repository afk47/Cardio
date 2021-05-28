package com.urcompany.cardio.entity;

import com.urcompany.cardio.gui.Drawable;
import com.urcompany.cardio.gui.Window;
import com.urcompany.cardio.texture.Material;
import com.urcompany.cardio.texture.Model;

public class Player extends Drawable {

	private float currentframe = 0;
	private float frames = 11;
	private int framelimiter = 0;

	@Override
	public void update() {
		super.update();

		if (framelimiter < 6) {						// \
			framelimiter += 1;
		} else {
			framelimiter = 0;						//    \
		}

		if (framelimiter / frames == 0) {			//       } - - Used To iterate through animation and framelimiter determines how slow the animation plays
			if (currentframe < frames) {
				currentframe += 1;
			} else {
				currentframe = 0;					//    /
			}
		}											// /

		//TODO Move to Drawable and add flag on whether to update have it animated
		//TODO TODO TODO!!!!!! Make compatible with spritesheets made in texturepacker using XML(generic)
		float framepos = 1 / frames;
		framepos = framepos * currentframe;
		texture_coords = new float[] { 0 + framepos, 0, 1 / frames + framepos, 0, 1 / frames + framepos, 1, 0 + framepos, 1, }; // DIVIDES THE IMAGE INTO var "frames" EQUAL PARTS and Iterates through them to animate it

		updateModel(new Model(vertices, texture_coords, indices));
	}

	public Player(Window window) {
		super(window);
		size = 400;
		position = new float[]{-300,-300,0};

	}

	public void setTime(double time2) {

	}

	@Override
	protected void refreshTexture() {
		// TODO Auto-generated method stub
		mat = new Material("/Sprites/Idle.png");

	}

}

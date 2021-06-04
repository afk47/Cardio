package com.urcompany.cardio.gui;

import java.util.ArrayList;

public abstract class Scene {

	private ArrayList<Drawable> drawables = new ArrayList<Drawable>();
	private String name;
	protected Window win;
	
	public Scene(Window w, String name) {
		this.name = name;
		this.win = w;
	}

	public abstract void init();

	public abstract void update();

	public void updateScene() {
		update();
		for (Drawable d : drawables) {
			d.update();
		}
	}

	public ArrayList<Drawable> getContents(){
		return drawables;
	}

	public Window getWindow() {
		return win;
	}

	public void addDrawable(Drawable d) {
		drawables.add(d);
	}

	public void addDrawables(Drawable[] ds) {
		for (Drawable d : ds) {
			drawables.add(d);
		}
	}
}

package com.nucleardiesel.cardio.gui;

import com.nucleardiesel.cardio.controllers.SceneController;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class Scene {

	private ArrayList<Drawable> drawables = new ArrayList<Drawable>();
	private final String name;
	protected SceneController controller;	// SceneController and Window available to all scenes.
	protected Window window;
	
	public Scene(String name) {
		this.name = name;
	}

	public abstract void init();

	public abstract void update();

	public void updateScene() {
		update();
		for (Drawable d : drawables) {
			d.update();
		}
	}

	public void addDrawable(Drawable d) {
		drawables.add(d);
	}
	
	public void setDrawable(Drawable d, int index) {
		drawables.set(index, d);
	}
	
	public void removeDrawable(int index) {
		drawables.remove(index);
		
	}

	public void addDrawables(Drawable[] ds) {
		drawables.addAll(Arrays.asList(ds));
	}

	public void setController(SceneController c) {
		controller = c;
	}

	public void setWindow(Window w) {
		window = w;
	}

	public ArrayList<Drawable> getContents(){
		return drawables;
	}

	public Window getWindow() {
		return controller.getWindow();
	}

	public String getName() {
		return name;
	}
}

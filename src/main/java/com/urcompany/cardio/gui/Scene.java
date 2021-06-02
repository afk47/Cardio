package com.urcompany.cardio.gui;

import java.util.ArrayList;

public class Scene {

	Window window;
	ArrayList<Drawable> contents = new ArrayList<Drawable>();
	
	public Scene(Window win) {
		window = win;
	}
	public Window getWindow() {
		return window;
	}
	
	public ArrayList<Drawable> getContents(){
		return contents;
	}
}

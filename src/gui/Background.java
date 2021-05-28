package gui;

import texture.Material;

public class Background extends Drawable{

	public Background(Window window) {
		super(window);
		size = 1000;
		position = new float[]{0, 450, 0};
		// TODO Auto-generated constructor stub
	}

	protected void refreshTexture() {
		// TODO Auto-generated method stub
		mat = new Material("/images/Background.png");
		
	}

	@Override
	public void update() {
		super.update();
		
	}
}

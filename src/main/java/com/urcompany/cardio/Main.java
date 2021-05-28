package com.urcompany.cardio;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glEnable;

import org.lwjgl.opengl.GL;

import com.urcompany.cardio.controllers.Timer;
import com.urcompany.cardio.entity.Player;
import com.urcompany.cardio.gui.Background;
import com.urcompany.cardio.gui.Drawable;
import com.urcompany.cardio.gui.Window;
import com.urcompany.cardio.texture.Renderer;

public class Main implements Runnable {

	public static int score;
	private boolean fullscreen = true;

	private Thread thread;
	private boolean running = false;
	public static Window win;

	private String title = "Game";
	public static Renderer renderer;

	double frame_cap = 1.0 / 60.0;
	double time = Timer.getTime();
	double frame_time = 0;
	int frames = 0;

	public Player p1;
	public Background bg;

	public void start() {
		running = false;
		thread = new Thread(this, "Game");
		thread.start();
	}

	/*
	 * Initializes the window and sets the main thread to running
	 *
	 */
	private void init() {
		Window.setCallbacks();
		running = true;

		win = new Window();
		win.setFullscreen(fullscreen);
		win.createWindow(title);
		GL.createCapabilities();

		p1 = new Player(win);
		bg = new Background(win);

		renderer = new Renderer();

		glEnable(GL_TEXTURE_2D);
	}

	/*
	 * Main Loop while running is true calls Update and Render methods Sets running
	 * to false when window closes
	 */
	@Override
	public void run() {
		init();
		boolean can_render = false;

		double unprocessed = 0;
		while (running) {

			/*
			 * FPS COUNTER:
			 *
			 */
			double time_2 = Timer.getTime();
			double passed = time_2 - time;
			unprocessed += passed;
			frame_time += passed;

			time = time_2;
			while (unprocessed >= frame_cap) {
				unprocessed -= frame_cap;
				can_render = true;
				update();

				if (frame_time >= 1.0) {
					frame_time = 0;
					System.out.println("FPS: " + frames);
					frames = 0;
				}

			}

			if(can_render) {
				render();

				if (win.shouldClose()) {
		 			running = false;

				}
				win.swapBuffers();
				frames++;
			}

		}
	}

	/*
	 * updates screen and checks for new events
	 *
	 */
	private void update() {
		win.update();
		running =  !win.getInput().isKeyPressed(GLFW_KEY_ESCAPE);
	}

	/*
	 * Renders the main screen
	 *
	 */
	private void render() {
		Drawable[] draw = new Drawable[]{bg,p1};
		p1.update();
		bg.update();
		renderer.render(draw);
	}

	/*
	 * Creates a new main thread
	 */
	public static void main(String[] args) {
		new Main().start();
	}

	public static void addScore(int i) {
		score += i;
	}

}

package com.nucleardiesel.cardio.gui;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.GL_VIEWPORT;
import static org.lwjgl.opengl.GL11.glGetIntegerv;
import static org.lwjgl.system.MemoryStack.*;
import java.nio.IntBuffer;

import javax.swing.text.Position;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.opengl.GL11;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nucleardiesel.cardio.controllers.Input;

import org.lwjgl.system.*;

public class Window {

	private static final Logger logger = LoggerFactory.getLogger(Window.class);

	private int width;
	private int height;
	private boolean fullscreen = false;
	private long window;
	private Input input;
	private IntBuffer heightp;
	private IntBuffer widthp;
	private IntBuffer xpos;
	private IntBuffer ypos;
	private GLFWWindowSizeCallback windowSizeCallback;
	private boolean hasResized;

	public Window() {
	}

	public void createWindow(String title) {
		createWindow(title, 1920, 1080);

	}

	/*
	 * Creates a window
	 *
	 * @PARAM title : title of window
	 * 
	 * @PARAM w : width of the window
	 * 
	 * @PARAM h : height of the window
	 *
	 */
	public void createWindow(String title, int w, int h) {
		setSize(w, h);

		if (!glfwInit()) {
			logger.error("Failed to initialize GLFW");
			throw new IllegalStateException("Failed to initialize GLFW");
		}

		window = glfwCreateWindow(width, height, title, 0, 0);

		if (window == 0)
			throw new IllegalStateException("Window Initialization Failed");

		try (MemoryStack stack = stackPush()) {
			widthp = stack.mallocInt(1);
			heightp = stack.mallocInt(1);
			xpos = stack.mallocInt(1);
			ypos = stack.mallocInt(1);
			glfwGetWindowPos(window, xpos, ypos);
			glfwGetWindowSize(window, widthp, heightp);
			GLFWVidMode vid = glfwGetVideoMode(glfwGetPrimaryMonitor());
			glfwSetWindowPos(window, (vid.width() - widthp.get(0)) / 2, (vid.height() - heightp.get(0)) / 2);
		}

		glfwMakeContextCurrent(window);
		glfwSwapInterval(0); // disables vsync. 1 to enable.
		glfwShowWindow(window);

		input = new Input(window);

		setLocalCallbacks();
	}

	/*
	 * Sets Callbacks for errors
	 *
	 *
	 */
	public static void setCallbacks() {
		glfwSetErrorCallback(new GLFWErrorCallback() {
			@Override
			public void invoke(int error, long desc) {
				throw new IllegalStateException(GLFWErrorCallback.getDescription(desc));
			}

		});
	}

	private void setLocalCallbacks() {
		windowSizeCallback = new GLFWWindowSizeCallback() {

			@Override
			public void invoke(long window, int argwidth, int argheight) {
				width = argwidth;
				height = argheight;
				hasResized = true;
				setSize(width, height);

				GL11.glViewport(0, 0, width, height);
			}
		};

		glfwSetWindowSizeCallback(window, windowSizeCallback);
	}

	/*
	 * Getters/Setters as well as update methods to refresh the window
	 *
	 *
	 */
	public boolean shouldClose() {
		return glfwWindowShouldClose(window);
	}

	public void swapBuffers() {
		glfwSwapBuffers(window);
		glfwGetWindowSize(window, widthp, heightp);
	}

	public void setSize(int w, int h) {
		width = w;
		height = h;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Input getInput() {
		return input;
	}

	public void update() {
		if (widthp.get(0) != width || heightp.get(0) != height) {
			hasResized = true;
			System.out.println("Resize detected");
		}
		input.update();
		glfwPollEvents();
	}

	public void setFullscreen(boolean fullscreen) {
		this.fullscreen = fullscreen;
	}

	public boolean hasResized() {
		return hasResized;
	}

	public void getPosition() {
		try (MemoryStack stack = stackPush()) {
			xpos = stack.mallocInt(1);
			ypos = stack.mallocInt(1);
			glfwGetWindowPos(window, xpos, ypos);
		}
	}
}

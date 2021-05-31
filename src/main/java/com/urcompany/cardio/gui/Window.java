package com.urcompany.cardio.gui;

import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;
import static org.lwjgl.glfw.GLFW.glfwGetVideoMode;
import static org.lwjgl.glfw.GLFW.glfwGetWindowSize;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSetErrorCallback;
import static org.lwjgl.glfw.GLFW.glfwSetWindowPos;
import static org.lwjgl.glfw.GLFW.glfwSetWindowSizeCallback;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;

import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.urcompany.cardio.controllers.Input;

public class Window {

	private static final Logger logger = LoggerFactory.getLogger(Window.class);

	private int width = 1000;
	private int height = 1000;
	private boolean fullscreen = true;
	private long window;
	private Input input;
	private int[] heightp;
	private int[] widthp;
	private GLFWWindowSizeCallback windowSizeCallback;
	private boolean hasResized;

	public Window() {

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

			}
		};

		glfwSetWindowSizeCallback(window, windowSizeCallback);
	}

	/*
	 * Creates a window
	 *
	 * @PARAM title : title of window
	 *
	 */
	public void createWindow(String title) {
		setSize(1920, 1080);
		if (!glfwInit()) {
			logger.error("Failed to initialize window");
			System.exit(1);
		}
		glfwGetWindowSize(glfwGetPrimaryMonitor(), widthp, heightp);
		
		window = glfwCreateWindow(width, height, title, fullscreen ? glfwGetPrimaryMonitor() : 0, 0);

		if (window == 0)
			throw new IllegalStateException("Window Initialization Failed");

		GLFWVidMode vid = glfwGetVideoMode(glfwGetPrimaryMonitor());

		if (!fullscreen) {
			glfwSetWindowPos(window, (vid.width() - width) / 2, (vid.height() - height) / 2);

			glfwShowWindow(window);

		} else {
			setSize(widthp[0], heightp[0]);
		}
		input = new Input(window);

		glfwMakeContextCurrent(window);
		setLocalCallbacks();
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
		IntBuffer w = BufferUtils.createIntBuffer(1);
		IntBuffer h = BufferUtils.createIntBuffer(1);
		glfwGetWindowSize(window, w, h);
		width = w.get(0);
		height = h.get(0);
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
		hasResized = false;
		input.update();
		glfwPollEvents();
	}

	public void setFullscreen(boolean fullscreen) {
		this.fullscreen = fullscreen;
	}

	public boolean hasResized() {
		return hasResized;
	}

}

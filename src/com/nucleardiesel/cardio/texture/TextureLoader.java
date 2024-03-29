package com.nucleardiesel.cardio.texture;


import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TextureLoader {

	private static final Logger logger = LoggerFactory.getLogger(TextureLoader.class);

	private ByteBuffer image;
	private int width;
	private int height;

	public TextureLoader(int width, int height, ByteBuffer image) {
		this.image = image;
		this.height = height;
		this.width = width;
	}

	public static TextureLoader loadImage(String path) {
		ByteBuffer image;
		int width;
		int height;
		try (MemoryStack stack = MemoryStack.stackPush()) {
			IntBuffer comp = stack.mallocInt(1);
			IntBuffer w = stack.mallocInt(1);
			IntBuffer h = stack.mallocInt(1);

			image = STBImage.stbi_load(path, w, h, comp, 4);
			if (image == null) {
				logger.error("Couldn't load texture at location: {}",path);
			}
			width = w.get();
			height = h.get();
		}
		return new TextureLoader(width, height, image);
	}


	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public ByteBuffer getImage() {
		return image;
	}

}

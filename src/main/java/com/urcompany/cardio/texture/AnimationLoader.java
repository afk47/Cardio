package com.urcompany.cardio.texture;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.json.*;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class AnimationLoader {

	private static final Logger logger = LoggerFactory.getLogger(AnimationLoader.class);
	private int h = -1;
	private int w = -1;
	private float[] texture_coords;
	private String animation = "Attack1";// Attack is currently a placeholder
	private int frames = 0;
	private int currentFrame = 0;

	float fx =0;
	float fy =0;
	float fh =0;
	float fw =0;

	public void loadAnimation(String s) {
		animation = s;
		try {

			File json = new File("src/main/resources/textures/Sprites/" + s + ".json");
			ObjectNode node = new ObjectMapper().readValue(json, ObjectNode.class);

			frames = node.get("frames").size();
			if (node.has("meta")) {
				String jsonString = "" + node.get("meta");
				node = new ObjectMapper().readValue(jsonString, ObjectNode.class);
				jsonString = "" + node.get("size");
				node = new ObjectMapper().readValue(jsonString, ObjectNode.class);
				w = node.get("w").asInt();
				h = node.get("h").asInt();
			}

		} catch (JsonMappingException e) {
			logger.debug("JsonMappingException", e);
		} catch (JsonProcessingException e) {

			logger.debug("JsonProcessingException", e);
		} catch (IOException e) {
			logger.debug("IOException", e);
		}

	}

	public void loadFrame(String s, int i) {
		if (frames < 1 || animation != s) {
			loadAnimation(s);
		}
		loadFrame(i);
	}

	public void loadFrame(int i) {

		try {

			File json = new File("src/main/resources/textures/Sprites/" + animation + ".json");
			ObjectNode node = new ObjectMapper().readValue(json, ObjectNode.class);

			if (node.has("frames")) {
				String jsonString = "" + node.get("frames");
				node = new ObjectMapper().readValue(jsonString, ObjectNode.class);
				if (node.has(animation + "-" + i + ".png")) {
					frames = node.size();
					jsonString = "" + node.get(animation + "-" + i + ".png");
					node = new ObjectMapper().readValue(jsonString, ObjectNode.class);
					if (node.has("frame")) {
						jsonString = "" + node.get("frame");
						node = new ObjectMapper().readValue(jsonString, ObjectNode.class);

						fx = (float) node.get("x").asInt();// frame x coord
						fy = (float) node.get("y").asInt();// frame y coord
						fh = (float) node.get("h").asInt();// frame height
						fw = (float) node.get("w").asInt();// frame width

						// converts frame coords to usable frame coords (percentage)
						float x1 = fx / w;
						float x2 = (fx + fw) / w;
						float y1 = fy / h;
						float y2 = (fy + fh) / h;

						texture_coords = new float[] { x1, y1, x2, y1, x2, y2, x1, y2, };

					}
				}
				jsonString = null;
			}
			json = null;
			node = null;

		} catch (JsonMappingException e) {
			logger.debug("JsonMappingException", e);
		} catch (JsonProcessingException e) {

			logger.debug("JsonProcessingException", e);
		} catch (IOException e) {
			logger.debug("IOException", e);
		}

	}

	public void loadNextFrame() {
		loadFrame(++currentFrame);
	}

	public float[] getFrameCoordinates() {
		return texture_coords;
	}

	public int getTotalFrames() {
		return frames;
	}
	
	public float getFrameHeight() {
		return fh;
	}
	
	public float getFrameWidth() {
		return fw;
	}

}

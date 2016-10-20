package com.vgdc.spooky;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;

/************************************
 *                                  *
 * Randomly generates a map for us. *
 * @author Evan S.                  *
 *                                  *
 ************************************/
/**
 * The map works by being a simple .png that we can
 * load assets from. This seems like a dumb way to do this
 */
public class MapGenerator {

	// All our potential tiles and what they'll be
	// Represented with
	private enum Tile {
		TREE   (0.0f, 1.0f, 0.0f),	// Green
		BUSH   (1.0f, 1.0f, 0.0f),	// Yellow
		GROUND (0.0f, 0.0f, 0.0f),	// Black
		ROCK   (0.5f, 0.5f, 0.5f);	// 50% Grey

		public final float r;	// Red value
		public final float g;	// Green value
		public final float b;	// Blue value

		Tile(float r, float g, float b) {
			this.r = r;
			this.g = g;
			this.b = b;
		}
	}

	/**
	 * Allegedly is supposed to somehow generate a map.
	 * @param width - the width (in pixels) of the final image
	 * @param height - ''  height ''                      ''
	 * @param seed - seed to generate the map with (not sure if necessary)
	 */
	public Pixmap generate (int width, int height, double seed) {
		Pixmap map = new Pixmap(width, height, Format.RGB888);
		map.drawRectangle(0, 0, width, height);
		return map;
	}
}

package com.vgdc.spooky;

import java.util.Random;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.vgdc.utils.Constants;

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
	} // Yeah screw that. This is def. for the level loader

	// Let's use a disgusting peasant array instead.
	private final int TREE = 0, BUSH = 1, GROUND = 2, ROCK = 3;
	private float[][] colorVals = {
		{0.0f, 1.0f, 0.0f},
		{1.0f, 1.0f, 0.0f},
		{0.0f, 0.0f, 0.0f},
		{0.5f, 0.5f, 0.5f}
	};

	/**
	 * Allegedly is supposed to somehow generate a map.
	 * @param width - the width (in pixels) of the final image
	 * @param height - ''  height ''                      ''
	 * @param seed - seed to generate the map with (not sure if necessary)
	 */
	public Pixmap generate (int width, int height, long seed) {
		Random rng = new Random(seed);
		Pixmap map = new Pixmap(width, height, Format.RGB888);
		map.drawRectangle(0, 0, width, height);
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				// Actual Math, like with weighting and shit, begins here.
				int ind = rng.nextInt(100); // Basically we're gonna pretend we're working with percentages.
				/**
				 * Step 1:
				 * INITIAL DRAWING: COMPLETELY RANDOM
				 */
				// We want roughly 75% of our pixels to be the ground.
				if (ind<75)
					ind = GROUND;
				else if (ind>=75 && ind<90)
					ind = TREE;
				else if (ind>=90 && ind<95)
					ind = BUSH;
				else if (ind>=95 && ind<100)
					ind = ROCK;
				map.setColor(colorVals[ind][0], colorVals[ind][1], colorVals[ind][2], 1);
				map.drawPixel(i, j);
				/**
				 * Step 2:
				 * BORDER: COMPLETELY TREES
				 */
				// We know we want our border to be 100% Trees.
				if ((i == 0) || (j == 0) || (i == Constants.MAP_HEIGHT-1) || (j == Constants.MAP_HEIGHT-1)) {
					map.setColor(colorVals[TREE][0], colorVals[TREE][1], colorVals[TREE][2], 1);
					map.drawPixel(i, j);
				}

				/**
				 * Step 3:
				 * PATHING: MAKE SURE THE PLAYER DOESN'T GET STUCK
				 */
				if (map.getPixel(i, j) == 255) {
					int count = 0; // how many of the pixels adjacent to (i,j) are not ground
					if (map.getPixel(i-1, j) != 255)
						count++;
					if (map.getPixel(i+1, j) != 255)
						count++;
					if (map.getPixel(i, j-1) != 255)
						count++;
					if (map.getPixel(i, j-1) != 255)
						count++;
					// And we're doing it by filling in a trapped block with a tree.
					if (count == 4)
						map.drawPixel(i,j);
				}
			}
		}
		return map;
	}
}
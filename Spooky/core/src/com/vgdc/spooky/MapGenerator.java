package com.vgdc.spooky;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.vgdc.utils.Constants;

/*******************************************
 *                                         *
 * Pseudo-Randomly generates a map for us. *
 * @author Violet M.                         *
 *                                         *
 *******************************************/
/**
 * The map works by reading pixels from a pixmap and translating them
 * into big boy sprites.
 */
public class MapGenerator {
	private static final String TAG = MapGenerator.class.getName();

	private final int TREE = 0, BUSH = 1, GROUND = 2, ROCK = 3, PLAYER = 4, CANDY = 5;
	private float[][] colorVals = {
		{0.0f, 1.0f, 0.0f},
		{1.0f, 1.0f, 0.0f},
		{0.0f, 0.0f, 0.0f},
		{1.0f, 0.0f, 0.0f},
		{1.0f, 1.0f, 1.0f},
		{1.0f, 0.0f, 1.0f}
	};

	boolean spawnedPlayer = false;
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
						map.setColor(colorVals[TREE][0], colorVals[TREE][1], colorVals[TREE][2], 1);
						map.drawPixel(i,j);
				}

				/**
				 * Step 4:
				 * PLAYER: CHOOSE A BLACK SQUARE
				 * Spoilers: RN it's just the first black square.
				 */
				if (map.getPixel(i, j) == 255) {
					/**
					 * Step 5:
					 * Draw a Candy? Randomly?
					 * When your win condition is random AMIRITE FELLAS
					 */
					if (Math.random() > .95) {
						map.setColor(colorVals[CANDY][0], colorVals[CANDY][1], colorVals[CANDY][2], 1);
						map.drawPixel(i, j);
					}

					// Actually step 4
					if (!spawnedPlayer) {
						map.setColor(colorVals[PLAYER][0], colorVals[PLAYER][1], colorVals[PLAYER][2], 1);
						map.drawPixel(i, j);
						spawnedPlayer = true;
						Gdx.app.log(TAG, "Chose a place to spawn the player: (" +i+ ", " + j+ ")");

					}
				}
			}
		}

		return map;
	}
}

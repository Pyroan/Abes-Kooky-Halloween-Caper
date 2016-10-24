package com.vgdc.spooky;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
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
/**
 * Generally I've been testing it with the seed 12345789, but I need to find one
 * that generates blocked off areas on me map.
 * or, I could have it draw more trees which would lead to more messed-up stuff.
 */
public class MapGenerator {
	private static final String TAG = MapGenerator.class.getName();

	// Constants so I don't have to remember where to access the colorVals array.
	// I know this was originally an Enum that i changed to an array but it might be worth 
	//   turning this back into an enum.
	private final int TREE = 0, BUSH = 1, GROUND = 2, ROCK = 3, PLAYER = 4, CANDY = 5;
	// Defines what colors we're generating.
	private final float[][] colorVals = {
		{0.0f, 1.0f, 0.0f},
		{1.0f, 1.0f, 0.0f},
		{0.0f, 0.0f, 0.0f},
		{1.0f, 0.0f, 0.0f},
		{1.0f, 1.0f, 1.0f},
		{1.0f, 0.0f, 1.0f}
	};

	// Array of all the ground area.
	private Array<Vector2> groundPixels;
	
	// Our actual map.
	private Pixmap map;
	// Random number generator
	private Random rng;
	
	// Whether the map has spawned the player yet.
	boolean spawnedPlayer = false;
	
	public MapGenerator(int width, int height, long seed) {
		rng = new Random(seed);
		map = new Pixmap(width, height, Format.RGB888);
		groundPixels = new Array<Vector2>();
	}
	/**
	 * Allegedly is supposed to somehow generate a map.
	 */
	// TODO: Encapsulate each step into it's own method?
	public Pixmap generate() {
		map.drawRectangle(0, 0, map.getWidth(), map.getHeight());
		for (int x = 0; x < map.getWidth(); x++) {
			for (int y = 0; y < map.getHeight(); y++) {
				/**
				 *  Actual Math, like with weighting and shit, begins here.
				 */
				int object = rng.nextInt(100); // Basically we're gonna pretend we're working with percentages.
				/**
				 * Step 1:
				 * INITIAL DRAWING: COMPLETELY RANDOM
				 */
				// We want roughly 75% of our pixels to be the ground.
				if (object<60) {
					object = GROUND;
					// Add location of the ground to the array.
					// This comes in handy later setting up paths.
					groundPixels.add(new Vector2(x, y));
				}
				else if (object>=60 && object<90)
					object = TREE;
				else if (object>=90 && object<95)
					object = BUSH;
				else if (object>=95 && object<100)
					object = ROCK;
				setObject(object);
				map.drawPixel(x, y);
				
				/**
				 * Step 2:
				 * BORDER: COMPLETELY TREES
				 */
				// We know we want our border to be 100% Trees.
				if ((x == 0) || (y == 0) || (x == map.getWidth()-1) || (y == map.getHeight()-1)) {
					setObject(TREE);
					map.drawPixel(x, y);
				}

				/**
				 * Step 3:
				 * PATHING: MAKE SURE THE PLAYER DOESN'T GET STUCK
				 * This is the hard part that's gonna get way more intense.
				 */
				if (map.getPixel(x, y) == 255) {
					int count = 0; // how many of the pixels adjacent to (i,j) are not ground
					if (map.getPixel(x-1, y) != 255)
						count++;
					if (map.getPixel(x+1, y) != 255)
						count++;
					if (map.getPixel(x, y-1) != 255)
						count++;
					if (map.getPixel(x, y-1) != 255)
						count++;
					// And we're doing it by filling in a trapped block with a tree.
					if (count == 4)
						setObject(TREE);
						map.drawPixel(x,y);
				}

				/**
				 * Step 4:
				 * PLAYER: CHOOSE A BLACK SQUARE
				 * Spoilers: RN it's just the first black square.
				 */
				if (map.getPixel(x, y) == 255) {
					/**
					 * Step 5:
					 * Draw a Candy? Randomly?
					 * When your win condition is random AMIRITE FELLAS
					 */
					if (Math.random() > .95) {
						setObject(CANDY);
						map.drawPixel(x, y);
					}

					// Actually step 4
					if (!spawnedPlayer) {
						setObject(PLAYER);
						map.drawPixel(x, y);
						spawnedPlayer = true;
						Gdx.app.log(TAG, "Chose a place to spawn the player: (" +x+ ", " + y+ ")");

					}
				}
			}
		}

		return map;
	}
	
	/**
	 * Sets the color the pixmap is drawing with to one of our
	 * predetermined values by letting you specify one of our objects.
	 * @param object the object
	 */
	private void setObject(int object) {
		map.setColor(colorVals[object][0], colorVals[object][1], colorVals[object][2], 1);
	}
	
	/**
	 * Goal: Create an algorithm that makes sure the entirety of the ground tiles are connected.
	 * Step 0: Somehow translate the groundPixels array into something I can use (?????)
	 * Step 1: Use a depth first search to determine connected components of the graph (Easy?)
	 * Step 2: Somehow choose some pixels to turn into ground pixels to connect unconnected components,
	 *    or turn a small unconnected component into more trees (Medium?)
	 *    
	 * This may help: http://www.java2blog.com/2015/12/depth-first-search-in-java.html
	 */
	private void fixPathing() {
		
	}
}

package com.vgdc.spooky;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.vgdc.objects.AbstractGameObject;
import com.vgdc.objects.Bush;
import com.vgdc.objects.Floor;
import com.vgdc.objects.Rock;
import com.vgdc.objects.Tree;

/**
 * Our Level within the game.
 * @author Violet M.
 *
 */
// TODO: Implement this once objects exist.
public class Level {
	public static final String TAG = Level.class.getName();

	// All our potential tiles and what they'll be
	// Represented with
	private enum TILE {
		TREE   (0, 255, 0),	// Green
		BUSH   (255, 255, 0),	// Yellow
		GROUND (0, 0, 0),	// Black
		ROCK   (255, 0, 0);	// Red

		private int color;

		TILE(int r, int g, int b) {
			color = r<<24|g<<16|b<<8|0xff;
		}

		public boolean sameColor (int color) {
			return this.color == color;
		}

		public int getColor () {
			return color;
		}
	}

	public Level(Pixmap pixmap) {
		init(pixmap);
	}

	// Objects
	public Array<Tree> trees;
	public Array<Bush> bushes;
	public Array<Rock> rocks;
	public Array<Floor> tiles;

	private void init(Pixmap pixmap) {
		// objects
		trees = new Array<Tree>();
		bushes = new Array<Bush>();
		rocks = new Array<Rock>();
		tiles = new Array<Floor>();
;
		for (int pixelY = 0; pixelY < pixmap.getHeight(); pixelY++) {
			for (int pixelX = 0; pixelX < pixmap.getWidth(); pixelX++) {
				AbstractGameObject obj = null;
				float offsetHeight = 0;
				// Height goes from bottom to top.
				float baseHeight = pixmap.getHeight() - pixelY;
				// get color of current pixel as 32 bit RGBA value
				int currentPixel = pixmap.getPixel(pixelX, pixelY);

				/**
				 * Find matching color value to identify block type at (x,y)
				 * point and create the corresponding game object if there
				 * is a match.
				 */

				// Floor
				// TODO: Move this so it autogens the floor.
					// draw floor.
					obj = new Floor();
					obj.position.set(pixelX, baseHeight * obj.dimension.y + offsetHeight);
					tiles.add((Floor)obj);

				// Tree
				if (TILE.TREE.sameColor(currentPixel)) {
					obj = new Tree();
					obj.position.set(pixelX, baseHeight* obj.dimension.y + offsetHeight);
					trees.add((Tree)obj);

				}

				// Bush (did 9/11)
				else if (TILE.BUSH.sameColor(currentPixel)) {
					obj = new Bush();
					obj.position.set(pixelX, baseHeight * obj.dimension.y + offsetHeight);
					bushes.add((Bush)obj);
				}

				// Rock
				else if (TILE.ROCK.sameColor(currentPixel)) {
					obj = new Rock();
					obj.position.set(pixelX, baseHeight * obj.dimension.y + offsetHeight);
					rocks.add((Rock)obj);
				}

				else if (TILE.GROUND.sameColor(currentPixel)) {

				}

				// Unknown pixel color.
				else {
					int r = 0xff & (currentPixel >>> 24); // red color channel
					int g = 0xff & (currentPixel >>> 16); // green color channel
					int b = 0xff & (currentPixel >>> 8);  // blue color channel
					int a = 0xff & (currentPixel);
					Gdx.app.error(TAG, "Unknown object at x<" + pixelX + "> y<"
							+ pixelY + ">: r<" + r + "> g<" + g + "> b<" + b + "> a<" + a + ">");
				}
			}
		}

		// The floor

		// Free memory
		pixmap.dispose();
		Gdx.app.debug(TAG,"Level Loaded");
	}

	public void render (SpriteBatch batch) {
		// Draw Floor
		for (Floor floor: tiles)
			floor.render(batch);

		// Draw Rocks
		for (Rock rock: rocks)
			rock.render(batch);

		// Draw Bushes
		for (Bush /*GHW*/ bush /*GW*/: bushes /*Jeb*/)
			bush.render(batch);

		// Draw trees
		for (Tree tree: trees)
			tree.render(batch);
	}

	public void update (float deltaTime) {
		for (Floor floor: tiles)
			floor.update(deltaTime);
		for (Rock rock: rocks)
			rock.update(deltaTime);
		for (Bush bush: bushes)
			bush.update(deltaTime);
		for (Tree tree: trees)
			tree.update(deltaTime);
	}
}

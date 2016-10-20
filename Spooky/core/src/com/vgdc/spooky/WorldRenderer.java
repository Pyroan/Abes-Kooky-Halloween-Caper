package com.vgdc.spooky;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.vgdc.utils.Constants;

/**
 * Draws our world
 * @author Evan S.
 *
 */
public class WorldRenderer implements Disposable {

	private OrthographicCamera camera;
	private SpriteBatch batch;
	private WorldController worldController;

	public WorldRenderer(WorldController worldController) {
		this.worldController = worldController;
		init();
	}

	/*
	 *  Sets up our camera and our SpriteBatch
	 */
	private void init() {
		batch = new SpriteBatch();
		camera = new OrthographicCamera (Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
		camera.position.set(0,0,0);
		camera.update();
	}


	/**
	 * Frees resources used in this class.
	 */
	@Override
	public void dispose() {
		batch.dispose();
	}

}

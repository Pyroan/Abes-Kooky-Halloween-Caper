package com.vgdc.spooky;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.vgdc.ui.Fonts;
import com.vgdc.utils.Constants;

/**
 * TFW It's the most important class in the game
 * but you can't explain what it does.
 * TODO add an actual comment.
 * @author Violet M.
 * Everything using box2d:
 * @author Lis O.
 */
public class Spooky extends ApplicationAdapter {
	// The player controller.
	// Is in progress.
	PlayerControls controller;

	// Draws the whole world.
	WorldRenderer worldRenderer;
	// Updates the world, including the level and
	// the player and the music and everything.
	WorldController worldController;

	@Override
	public void create () {

		// Initialize all the non-box2d stuff.
		Assets.instance.init(new AssetManager());
		Fonts.instance.init();
		worldController = new WorldController();
		worldRenderer = new WorldRenderer(worldController);
		initializeThings();

	}

	@Override
	public void render () {
		//dis lis's
		float deltaTime = (Gdx.graphics.getDeltaTime());

		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		worldController.update(deltaTime);
		worldRenderer.render();
	}
	
	/**
	 * Handles the window being resized.
	 */
	@Override
	public void resize(int width, int height) {
		worldRenderer.resize(width, height);
	}

	/**
	 * Frees memory.
	 */
	@Override
	public void dispose() {
		worldRenderer.dispose();
		Assets.instance.dispose();
	}

	public void initializeThings()
	{
		controller = new PlayerControls();
	}
}

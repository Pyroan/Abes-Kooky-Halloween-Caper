package com.vgdc.spooky;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.vgdc.utils.CameraHelper;
import com.vgdc.utils.Constants;

/**
 * I'd be lying if i said I was prepared to explain
 * what this does.
 * In class it's been functioning simultaneously
 * as an input handler and a collision detector.
 * @author Evan S.
 *
 */
public class WorldController {

	public Level level;

	private Game game;

	public CameraHelper cameraHelper;

	public WorldController() {
		init();
	}

	public WorldController (Game game) {
		this.game = game;
		init();
	}

	private void init() {
		cameraHelper = new CameraHelper();
		initLevel();
	}

	private void initLevel() {
		MapGenerator mg = new MapGenerator();
		long seed = 123456789; // Seed can be up to 9 digits long.
		level = new Level(mg.generate(Constants.MAP_WIDTH, Constants.MAP_HEIGHT, seed));
	}

	public void update(float deltaTime) {

	}
}

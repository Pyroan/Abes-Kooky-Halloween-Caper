package com.vgdc.spooky;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.vgdc.utils.CameraHelper;
import com.vgdc.utils.Constants;
import audio.MusicPlayer;

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

	public PlayerControls controller;

	private static final String TAG = WorldController.class.getName();

	public WorldController() {
		init();
	}

	public WorldController (Game game) {
		this.game = game;
		init();
	}

	private void init() {
		cameraHelper = new CameraHelper();
		controller = new PlayerControls();
		initLevel();
	}

	private void initLevel() {
		MapGenerator mg = new MapGenerator();
		long seed = 123456789; // Seed can be up to 9 digits long.
		level = new Level(mg.generate(Constants.MAP_WIDTH, Constants.MAP_HEIGHT, seed));
	}

	public void update(float deltaTime)
	{
		handleCameraMovement(deltaTime);
		(MusicPlayer.getMusic()).play();
	}

	private void moveCamera(float x, float y)
	{
		x += cameraHelper.getPosition().x;
		y += cameraHelper.getPosition().y;
		cameraHelper.setPosition(x, y);
	}

	public void handleCameraMovement(float deltaTime)
	{
		float cameraMoveSpeed = 5 * deltaTime;
		if(Gdx.input.isKeyPressed(Keys.W))
		{
			moveCamera(0, cameraMoveSpeed);
		}
		if(Gdx.input.isKeyPressed(Keys.A))
		{
			moveCamera(-cameraMoveSpeed, 0 );
		}
		if (Gdx.input.isKeyPressed(Keys.S))
		{
			moveCamera(0, -cameraMoveSpeed);
		}
		if(Gdx.input.isKeyPressed(Keys.D))
		{
			moveCamera(cameraMoveSpeed, 0);
		}
	}
}

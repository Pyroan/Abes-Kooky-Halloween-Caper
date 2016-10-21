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
 * @author Violet M.
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
		cameraHelper.setTarget(level.player);
	}

	public void update(float deltaTime)
	{
		handleCameraMovement(deltaTime);
		handlePlayerMovement(deltaTime);
		level.update(deltaTime);
		cameraHelper.update(deltaTime);
		updateMusic(deltaTime);
	}

	public void updateMusic(float deltaTime)
	{
		MusicPlayer.backgroundSong.setVolume(0.1f);
		MusicPlayer.backgroundSong.play();
		if(Math.random() > 0.98)
		{
			MusicPlayer.nathanielSnoring.play();
		}
		if(MusicPlayer.nathanielSnoring.getPosition() >= 7.0f)
		{
			MusicPlayer.nathanielSnoring.stop();
		}
		MusicPlayer.wind.setVolume(0.2f);
		MusicPlayer.wind.play();
	}

	private void moveCamera(float x, float y)
	{
		x += cameraHelper.getPosition().x;
		y += cameraHelper.getPosition().y;
		cameraHelper.setPosition(x, y);
	}

	public void handleCameraMovement(float deltaTime)
	{
		if (cameraHelper.hasTarget()) return;

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

	public void handlePlayerMovement(float deltaTime)
	{
		if (!cameraHelper.hasTarget(level.player)) return;

		if (Gdx.input.isKeyPressed(Keys.W)) {
			level.player.velocity.y = level.player.terminalVelocity.y;
		} else if (Gdx.input.isKeyPressed(Keys.S)) {
			level.player.velocity.y = -level.player.terminalVelocity.y;
		}

		if (Gdx.input.isKeyPressed(Keys.A)) {
			level.player.velocity.x = -level.player.terminalVelocity.x;
		}else if (Gdx.input.isKeyPressed(Keys.D))
			level.player.velocity.x = level.player.terminalVelocity.x;
	}
}

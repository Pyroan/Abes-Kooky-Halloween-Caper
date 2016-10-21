package com.vgdc.spooky;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.math.Rectangle;
import com.vgdc.objects.AbstractGameObject;
import com.vgdc.objects.Bush;
import com.vgdc.objects.Candy;
import com.vgdc.objects.Player;
import com.vgdc.objects.Rock;
import com.vgdc.objects.Tree;
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

	public int numberOfCandies;
	public int collectedCandies;

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
		collectedCandies = 0;
		initLevel();
	}

	private void initLevel() {
		MapGenerator mg = new MapGenerator();
		long seed = 123456789; // Seed can be up to 9 digits long.
		level = new Level(mg.generate(Constants.MAP_WIDTH, Constants.MAP_HEIGHT, seed));
		cameraHelper.setTarget(level.player);
		numberOfCandies = level.getNumberOfCandies();
	}

	public void update(float deltaTime)
	{
		if (collectedCandies == numberOfCandies) {
			// As a nod to spectrum shooter, have it
			// crash the game upon victory.
			Gdx.app.log("", "THAS PRETTY NEAT.")
			System.exit(0);
			return;
		}
		handleCameraMovement(deltaTime);
		handlePlayerMovement(deltaTime);
		level.update(deltaTime);
		testForCollision();
		cameraHelper.update(deltaTime);
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



	Rectangle r1 = new Rectangle();
	Rectangle r2 = new Rectangle();

	private void onCollisionPlayerWithCandy (Candy candy) {
		candy.collected = true;
		collectedCandies++;
		Gdx.app.log(TAG, "Candy Collected");
	}

	private void onCollisionPlayerWithWall (AbstractGameObject wall) {
		Player player = level.player;
		float yDiff = Math.abs(wall.bounds.y - player.bounds.y);
		float yGoal = wall.bounds.height/2 + player.bounds.height/2;

		// If  they're too close together, move them apart.
		if (yDiff < yGoal){

			if (player.position.y > wall.position.y) {
				player.position.y += (1 - yGoal - yDiff);
			}
			if (player.position.y < wall.position.y){
				player.position.y -= (1 - yGoal - yDiff);
			}
		}

		float xDiff = Math.abs(wall.bounds.x - player.bounds.x);
		float xGoal = wall.bounds.width/2 + player.bounds.width/2;

		if (xDiff < xGoal) {
			if (player.position.x > wall.position.x) {
				player.position.x += (1 - xGoal - xDiff);
			}
			if (player.position.x < wall.position.x){
				player.position.x -= (1 -xGoal - xDiff);
			}
		}

		float y = 1-yGoal - yDiff;
		float x = 1-xDiff-xGoal;
		Gdx.app.log(TAG, "U HIT DA WALL. " + y + " " + x );
	}

	private void testForCollision () {
		r1.set(level.player.position.x, level.player.position.y,
				level.player.bounds.width, level.player.bounds.height);

		for (Candy candy : level.candies) {
			r2.set(candy.position.x, candy.position.y, candy.bounds.width, candy.bounds.height);
			if (!r1.overlaps(r2)) continue;
			if (!candy.collected)
				onCollisionPlayerWithCandy(candy);
			break;
		}

		// I don't wanna test all these walls :\
		for (Tree tree: level.trees) {
			r2.set(tree.position.x, tree.position.y, tree.bounds.width, tree.bounds.height);
			if (!r1.overlaps(r2)) continue;
			onCollisionPlayerWithWall(tree);
		}

		for (Bush bush: level.bushes) {
			r2.set(bush.position.x, bush.position.y, bush.bounds.width, bush.bounds.height);
			if (!r1.overlaps(r2)) continue;
			onCollisionPlayerWithWall(bush);
		}

		for (Rock rock: level.rocks) {
			r2.set(rock.position.x, rock.position.y, rock.bounds.width, rock.bounds.height);
			if (!r1.overlaps(r2)) continue;
			onCollisionPlayerWithWall(rock);
		}
	}
}

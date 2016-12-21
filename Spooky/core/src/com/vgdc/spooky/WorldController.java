package com.vgdc.spooky;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Align;
import com.vgdc.audio.MusicPlayer;
import com.vgdc.encounters.EncounterHandler;
import com.vgdc.objects.AbstractGameObject;
import com.vgdc.objects.Bush;
import com.vgdc.objects.Candy;
import com.vgdc.objects.HorizontalHouse;
import com.vgdc.objects.Player;
import com.vgdc.objects.Rock;
import com.vgdc.objects.Tree;
import com.vgdc.objects.VerticalHouse;
import com.vgdc.ui.UIController;
import com.vgdc.utils.CameraHelper;
import com.vgdc.utils.Constants;

/**
 * Essentially handles everything physically in our world,
 * like collision, player movement, and the level which holds
 * all the objects in the world.
 *
 * This class is getting extremely out of hand.
 * @author Evan S.
 *
 */
public class WorldController {

	public Level level;

	private Game game;

	public CameraHelper cameraHelper;

	public PlayerControls controller;

	public MusicPlayer musicPlayer;

	public UIController uiController;

	public World b2world;

	// Eventually:
	//	public CollisionHandler collisionHandler;

	// Should maybe be handled by UI controller?
	public EncounterHandler encounterHandler;

	public int numberOfCandies;
	public int collectedCandies;

	public ParticleEffect snow = new ParticleEffect();

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
		encounterHandler = new EncounterHandler();
		controller = new PlayerControls();
		musicPlayer = new MusicPlayer();
		uiController = new UIController();
		snow.load(Gdx.files.internal("particles/Snow"), Gdx.files.internal("particles"));
		collectedCandies = 0;
		initLevel();
		initPhysics();
		// Eventually
		//		b2world.setContactListener(contactHandler);
	}

	/**
	 * Initializes the level (right now with a given seed)
	 */
	private void initLevel() {
		long seed = 123456789; // Seed can be up to 9 digits long (for now).
		//		MapGenerator mg = new MapGenerator(Constants.MAP_WIDTH, Constants.MAP_HEIGHT, seed);
		AlternativeMapGen mg = new AlternativeMapGen(Constants.MAP_WIDTH, Constants.MAP_HEIGHT, seed);
		// We're actually not gonna use Procedural generation for now but I'll leave the code for later.
		// That's disgusting how Dare I do that.
		level = new Level(Constants.LEVEL_NAME);
		//		level = new Level(mg.getPixmap());
		mg.dispose();
		if (!Constants.DEBUGGING_MAP) cameraHelper.setTarget(level.player);
		numberOfCandies = level.getNumberOfCandies();
	}

	public void update(float deltaTime)
	{
		if (collectedCandies == numberOfCandies) {
			// As a nod to spectrum shooter, have it
			// crash the game upon victory.
			Gdx.app.log("", "THAS PRETTY NEAT.");
			System.exit(0);
			return;
		}
		encounterHandler.update(deltaTime);
		// Input Handling
		handleDebugInput(deltaTime);
		handleCameraMovement(deltaTime);
		handlePlayerMovement(deltaTime);
		// Update UI/Level Objects
		uiController.update(deltaTime);
		level.update(deltaTime);
		b2world.step(1/60f, 8, 3);
		// Move Camera
		cameraHelper.update(deltaTime);
		// Play Music
		musicPlayer.update(deltaTime);
		// Update Snow.
		snow.update(deltaTime);
	}


	/**
	 * Moves the camera to a place.
	 * @param x how far to move it.
	 * @param y how far to move it.
	 */
	private void moveCamera(float x, float y)
	{
		x += cameraHelper.getPosition().x;
		y += cameraHelper.getPosition().y;
		cameraHelper.setPosition(x, y);
	}

	/**
	 * Handles input for debug features,
	 * like letting the camera move freely
	 * @param deltaTime
	 */
	public void handleDebugInput(float deltaTime)
	{
		if (Gdx.input.isKeyJustPressed(Keys.BACKSPACE))
			if (!cameraHelper.hasTarget())
				cameraHelper.setTarget(level.player);
			else
				cameraHelper.setTarget(null);

		// Test a mock encounter.
	}

	/**
	 * Moves the camera around if the camera doesn't have a target.
	 * @param deltaTime
	 */
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

	/**
	 * Moves the player around
	 * @param deltaTime
	 */
	public void handlePlayerMovement(float deltaTime)
	{
		if (!cameraHelper.hasTarget(level.player)) return;
		Vector2 moveVector = new Vector2();
		if (Gdx.input.isKeyPressed(Keys.W)) {
			moveVector.y = level.player.terminalVelocity.y;
			level.player.setTexture(level.player.back);
		} else if (Gdx.input.isKeyPressed(Keys.S)) {
			moveVector.y = -level.player.terminalVelocity.y;
			level.player.setTexture(level.player.front);
		}

		if (Gdx.input.isKeyPressed(Keys.A)) {
			moveVector.x = -level.player.terminalVelocity.x;
			level.player.setTexture(level.player.left);
		}else if (Gdx.input.isKeyPressed(Keys.D)) {
			moveVector.x = level.player.terminalVelocity.x;
			level.player.setTexture(level.player.right);
		}

		level.player.body.setLinearVelocity(moveVector);
	}


	/*************************************
	 *    Beware: Physics Begins Here    *
	 *************************************/

	/**
	 * Sets up Box2D Bodies
	 */
	private void initPhysics()
	{
		if (b2world != null) b2world.dispose();
		b2world = new World(new Vector2(0, 0), true);
		// Trees
		for (Tree tree: level.trees)
			makeBody(tree, BodyType.StaticBody, 0, "Tree", Align.bottom);
		// Houses
		for (HorizontalHouse house: level.horizontalHouses)
			makeBody(house, BodyType.StaticBody, 0, "House", Align.bottom);
		for (VerticalHouse house: level.verticalHouses)
			makeBody(house, BodyType.StaticBody, 0, "House", Align.bottom);
		// Rocks
		for (Rock rock: level.rocks)
			makeBody(rock, BodyType.StaticBody, 0, "Rock", Align.bottom);
		// Candies
		for (Candy candy: level.candies)
			makeBody(candy, BodyType.KinematicBody, 0, "Candy", Align.center);
		// Player
		makeBody(level.player, BodyType.DynamicBody, 0, "Player", Align.center);
	}

	/**
	 * Makes a new box-shaped body for an object
	 * @param object The object we're creating a body for
	 * @param bodyType The type of body to be created
	 * @param friction The friction component of the body's fixture
	 * @param data The name of the object, used for collision handling.
	 */
	private void makeBody(AbstractGameObject object, BodyType bodyType, float friction, String data, int alignment)
	{
		Vector2 origin = new Vector2();

		BodyDef bodyDef = new BodyDef();
		bodyDef.type = bodyType;
		bodyDef.position.set(object.position);
		Body body = b2world.createBody(bodyDef);
		body.setUserData(data);
		object.body = body;

		PolygonShape polygonShape = new PolygonShape();
		// Need to offset center of box depending on alignment
		if (alignment == Align.bottom)
		{
			origin.x = object.bounds.x / 2.0f;
			origin.y = object.bounds.y / 2.0f;
			
		} 
		else if (alignment == Align.center)
		{
			origin.x = object.dimension.x / 2.0f;
			origin.y = object.dimension.y / 2.0f;
		} 
		else
		{
			Gdx.app.error(TAG, "Invalid Alignment for " + data + " : " + alignment);
		}
		
		polygonShape.setAsBox(object.bounds.x/2.0f,
				object.bounds.y/2.0f, origin, 0);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = polygonShape;
		fixtureDef.friction = friction;
		body.createFixture(fixtureDef);
		polygonShape.dispose();
	}
}

package com.vgdc.spooky;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.vgdc.objects.Tree;
import com.vgdc.utils.Constants;

import box2dLight.RayHandler;



public class Spooky extends ApplicationAdapter {
	SpriteBatch batch;

	PlayerControls variableName;

	PlayerControls controller;

	WorldRenderer worldRenderer;
	WorldController worldController;

	//box2d stuffs
	World world;
	Body player;
	Box2DDebugRenderer b2dr;

	//box2d light stuff
	RayHandler rayHandler;


	public Level level;

	@Override
	public void create () {
		//trying to make box2D work

		//init box2d world, setting gravity vector (0 gravity for now)
		world = new World(new Vector2(0, 0f), false);
		//wold handler
		//worldRenderer extends b2dr
		//player created and placed in the world
		player = createPlayer();

		//light stuff
		rayHandler = new RayHandler(world);
		rayHandler.setAmbientLight(.2f);


		Assets.instance.init(new AssetManager());
		worldController = new WorldController();
		worldRenderer = new WorldRenderer(worldController);
		batch = new SpriteBatch();
		initializeThings();

	}

	@Override
	public void render () {
		//dis lis's
		update(Gdx.graphics.getDeltaTime());

		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		worldController.update(Gdx.graphics.getDeltaTime());
		worldRenderer.render();
		worldRenderer.render(world);
		rayHandler.render();
		batch.begin();
		batch.end();
	}

	//for box2d
	public void update(float delta){
		//world logic before render
		world.step(1/60f, 6, 2);
		rayHandler.update();
	}

	@Override
	public void resize(int width, int height) {
		worldRenderer.resize(width, height);
	}

	@Override
	public void dispose() {
		worldRenderer.dispose();
		Assets.instance.dispose();
		world.dispose();
		rayHandler.dispose();

	}

	public void initializeThings()
	{
		controller = new PlayerControls();
	}



	//Lis made this for box2d
	public Body createPlayer(){
		Body pBody;
		//physical properties of the body
		BodyDef def = new BodyDef();
		def.type = BodyType.DynamicBody;
		def.position.set(0, 0);
		def.fixedRotation = true;
		//places in the world
		pBody = world.createBody(def);

		PolygonShape shape = new PolygonShape();
		//hit box i guess
		shape.setAsBox (8/2, 8/2);

		pBody.createFixture(shape, 1.0f);
		shape.dispose();

		return pBody;
	}

}

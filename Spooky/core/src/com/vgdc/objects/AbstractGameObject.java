package com.vgdc.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.vgdc.spooky.Spooky;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
/**
 * Abstraction of a game object that lets us move things,
 * and whatnot.
 * @author Evan S.
 *
 */
public abstract class AbstractGameObject {
	public Vector2 position;
	public Vector2 dimension;
	public Vector2 origin;
	public Vector2 scale;
	public float rotation;

	public Vector2 velocity;
	public Vector2 terminalVelocity;
	public Vector2 friction; // It's more realistic if our player
	// doesn't just stop in their tracks.

	public Vector2 acceleration;
	public Vector2 bounds;
	
	public Body body;

	public AbstractGameObject() {
		position = new Vector2();
		dimension = new Vector2(1,1);
		origin = new Vector2();
		scale = new Vector2(1,1);
		rotation = 0;
		velocity = new Vector2();
		terminalVelocity = new Vector2(1,1);
		friction = new Vector2();
		acceleration = new Vector2();
		bounds = new Vector2();

	}

	/**
	 * Updates motion along the X axis.
	 */
	protected void updateMotionX(float deltaTime) {
		if (velocity.x!=0) {
			// Apply friction
			if (velocity.x > 0)
				velocity.x = Math.max(velocity.x - friction.x*deltaTime, 0);
			else
				velocity.x = Math.min(velocity.x + friction.x * deltaTime, 0);
		}
		// Apply acceleration
		velocity.x += acceleration.x * deltaTime;
		// Make sure the object's velocity does not exceed the
		// Positive or negative terminal velocity.
		velocity.x = MathUtils.clamp(velocity.x, -terminalVelocity.x, terminalVelocity.x);
	}

	/**
	 * Updates motion along Y axis
	 */
	protected void updateMotionY (float deltaTime) {
		if (velocity.y!=0) {
			// Apply friction
			if (velocity.y > 0)
				velocity.y = Math.max(velocity.y - friction.y * deltaTime, 0);
			else
				velocity.y = Math.min(velocity.y + friction.y * deltaTime, 0);
		}
		// Apply acceleration
		velocity.y += acceleration.y * deltaTime;
		// Make sure the object's velocity does not exceed the
		// positive or negative terminal velocity
		velocity.y = MathUtils.clamp(velocity.y, -terminalVelocity.y, terminalVelocity.y);
	}

	/**
	 * Updates position of object wrt delta time.
	 * @param deltaTime
	 */
	public void update (float deltaTime) {
		
		if (body == null)
		{
		updateMotionX(deltaTime);
		updateMotionY(deltaTime);
		// Move to new position
		position.x += velocity.x * deltaTime;
		position.y += velocity.y * deltaTime;
		} else
		{
			position.set(body.getPosition());
		}
	}

	public abstract void render (SpriteBatch batch);


	public abstract float getWidth();
	public abstract float getHeight();
}

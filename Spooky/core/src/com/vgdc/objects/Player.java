package com.vgdc.objects;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Our Player: A big black square.
 * @author Derek B., Evan S.
 *
 */
public class Player extends AbstractGameObject
{
	private Texture tex;

	Pixmap pixmap = new Pixmap(200, 200, Format.RGBA8888);
	public Player()
	{
		pixmap.setColor(0f,0f,0f,1f);
		pixmap.fillCircle(pixmap.getWidth()/2, pixmap.getHeight()/2, 50);
		tex = new Texture(pixmap);

		terminalVelocity.set(3.0f, 3.0f);
		friction.set(12.0f, 12.0f);
		origin.set(dimension.x/2, dimension.y/2);
	}
	@Override
	public void render(SpriteBatch batch) {
		batch.draw(tex, position.x, position.y, origin.x,
				origin.y, dimension.x, dimension.y, scale.x,
				scale.y, rotation, 0, 0, tex.getWidth(),
				tex.getHeight(), false, false);
	}

	public float getWidth() {
		return tex.getWidth();
	}

	public float getHeight() {
		return tex.getHeight();
	}


}

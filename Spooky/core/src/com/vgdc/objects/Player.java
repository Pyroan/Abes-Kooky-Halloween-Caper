package com.vgdc.objects;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.vgdc.spooky.Assets;

/**
 * Our Player: A big black square.
 * @author Derek B., Violet M.
 *
 */
public class Player extends AbstractGameObject
{
	private Texture tex;
	private TextureRegion reg;

	Pixmap pixmap = new Pixmap(200, 200, Format.RGBA8888);
	public Player()
	{

//		pixmap.setColor(0f,0f,0f,1f);
//		pixmap.fillCircle(pixmap.getWidth()/2, pixmap.getHeight()/2, 50);
//		tex = new Texture(pixmap);
		reg = new TextureRegion(Assets.instance.front.front1);
		dimension.set(1, 1);
		bounds.set(origin.x,origin.y, dimension.x * .75f, dimension.y * .75f);
		origin.set(dimension.x/2, dimension.y/2);
		terminalVelocity.set(3.0f, 3.0f);
		friction.set(12.0f, 12.0f);

	}
	@Override
	public void render(SpriteBatch batch) {
		batch.draw(reg.getTexture(), position.x, position.y,
				origin.x, origin.y, dimension.x, dimension.y,
				scale.x, scale.y, rotation, reg.getRegionX(), reg.getRegionY(),
				reg.getRegionWidth(), reg.getRegionHeight(), false, false);
	}

}

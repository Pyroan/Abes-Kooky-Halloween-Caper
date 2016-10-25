package com.vgdc.encounters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

/**
 * Keeps track of all our encounters
 * and watches for their triggers,
 * setting them off when appropriate.
 * @author Evan S.
 *
 */
public class EncounterHandler {

	public Array<AbstractEncounter> encounters;

	public EncounterHandler() {
		encounters = new Array<AbstractEncounter>();
		initEncounters();
	}

	private void initEncounters() {
		MockEncounter mEncounter = new MockEncounter();
		encounters.add(mEncounter);
	}

	public void render(SpriteBatch batch) {
		renderTextBox();
		for (int i = 0; i < encounters.size; i++) {
			if (encounters.get(i).isTriggered) {
				encounters.get(i).render(batch);
			}
		}
	}

	private void renderTextBox() {

	}

}

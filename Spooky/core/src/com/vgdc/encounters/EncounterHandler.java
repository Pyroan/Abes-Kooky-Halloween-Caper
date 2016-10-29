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

	// List of all the encounters we can use.
	public Array<AbstractEncounter> encounters;

	/**
	 * Initializes AbstractEncounter array
	 */
	public EncounterHandler() {
		encounters = new Array<AbstractEncounter>();
		initEncounters();
	}

	/**
	 * For now, just makes a mock encounter.
	 * Eventually will make all the encounters?
	 */
	private void initEncounters() {
		MockEncounter mEncounter = new MockEncounter();
		encounters.add(mEncounter);
	}

	/**
	 * Checks to see if any events have been triggered.
	 * @param batch
	 */
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

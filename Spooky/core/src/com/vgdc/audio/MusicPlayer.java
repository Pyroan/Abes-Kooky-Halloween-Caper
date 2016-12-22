package com.vgdc.audio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

/**
 * Handles our music
 * @author Nathaniel
 * But Evan was nice and actually moved all his code
 * into here from Worldcontroller. WHY WAS IT IN
 * WORLDCONTROLLER?
 */
public class MusicPlayer
{
	private Music backgroundSong;
	private Music nathanielSnoring;
	private Music snore1;
	private Music snore2;
	private Music snore3;
	private Music wind;

	/**
	 * Initialize all our Music.
	 */
	public MusicPlayer()
	{
		backgroundSong =
				Gdx.audio.newMusic(Gdx.files.internal("Snow in October.mp3"));
		nathanielSnoring =
				Gdx.audio.newMusic(Gdx.files.internal("Nathaniel Snoring.mp3"));
		snore1 = 
				Gdx.audio.newMusic(Gdx.files.internal("NathanielSnoring1.mp3"));
		snore2 = 
				Gdx.audio.newMusic(Gdx.files.internal("NathanielSnoring1.mp3"));
		snore3 = 
				Gdx.audio.newMusic(Gdx.files.internal("NathanielSnoring1.mp3"));
		wind = Gdx.audio.newMusic(Gdx.files.internal("wind.mp3"));
		wind.setVolume(0.2f);
		backgroundSong.setVolume(.5f);
		backgroundSong.play();
	}

	/**
	 * Updates music.
	 * @param deltaTime
	 */
	public void update(float deltaTime) {
		
		if (Math.random() > .99)
		{
			double snoreChoice = Math.random();
			if(snoreChoice <= 0.33)
			{
				snore1.play();
			}
			if(snoreChoice > 0.33 && snoreChoice <= 0.66)
			{
				snore2.play();
			}
			if(snoreChoice > 0.66)
			{
				snore3.play();
			}
		}
		wind.play();
	}
}

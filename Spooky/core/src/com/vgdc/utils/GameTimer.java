package com.vgdc.utils;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Keeps track of time in both a raw format and a more
 * user-friendly string format.
 * @author Violet M.
 */
public class GameTimer {

	// Time since initialization (in seconds)
	private float rawTime;
	// String representation of time, in form HH:MM:SS
	private String time = "";
	
	
	/**
	 * Creates a new Timer starting at 0 seconds.
	 */
	public GameTimer()
	{
		this(0);
	}
	
	/**
	 * If you want to initialize this with a specific time for some reason.
	 * @param startTime Time (in seconds) to start the timer from.
	 */
	public GameTimer(int startTime)
	{
		rawTime = startTime;
		translateTime();
	}
	
	/**
	 * Turns raw time into actual real time that we can 
	 * look at and not throw up.
	 */
	private void translateTime()
	{
		int minutes = (int)rawTime / 60;
		int seconds = (int)rawTime % 60;
		int hours = 0;	// If anyone winds up playing the game
						// long enough to see hours, ohhh boy.
		// Turns minutes bigger than 59 into hours.
		if (minutes >= 60)
		{
			hours = minutes / 60;
			minutes = minutes % 60;
		}
		
		// Calculations done. Now I gotta make it pretty.
		
		// Only bother with hours if they've actually passed.
		if (hours > 0) time = hours + ":";
		else time = " ";
		// If minutes and seconds < 10, need to add a 0
		if (minutes < 10) time+= "0";
		time+= minutes + ":";
		if (seconds < 10) time+="0";
		time+= seconds + "";
	}
	
	/**
	 * Adds the time since last frame to the current time.
	 * @param deltaTime
	 */
	public void update(float deltaTime)
	{
		rawTime+=deltaTime;
		translateTime();
	}
	
	public void render(SpriteBatch batch)
	{
		// JOKE'S ON YOU NO RENDERING HERE YET.
		
		// Uncomment this to spam the console with
		// debug messages.
//		System.out.println("Timer: " + time);
	}
	
	/**
	 * @return String representation of time, in form (HH:)MM:SS
	 */
	public String getTime()
	{
		return time;
	}
	
	/**
	 * @return Raw time in seconds (float).
	 */
	public float getRawTime()
	{
		return rawTime;
	}
	
}

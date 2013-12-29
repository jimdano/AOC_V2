package fr.istic.aoc.metronome.moteur;

import fr.istic.aoc.components.command.ICommand;

public interface IMoteur {

	/**
	 * @return true if the engine is started; false otherwise
	 */
	boolean isStarted();
	
	/**
	 * Starts the engine
	 */
	void start();
	
	/**
	 * Stops the engine
	 */
	void stop();
	
	/**
	 * @return the number of time by extent
	 */
	int getBpm();
	
	/**
	 * Sets the number of time by extent with the given parameter
	 * @param value number of time by extent
	 */
	void setBpm(int value);
	
	/**
	 * @return the tempo
	 */
	int getTempo();
	
	/**
	 * Sets the tempo value
	 * @param value tempo value
	 */
	void setTempo(int value);
	
	/**
	 * Register a new ICommand to use for beats
	 * @param cmd the command
	 */
	void setBipCommand(ICommand beatCmd);
	
	/**
	 * Return the registred ICommand used for beats
	 */
	ICommand getBipCommand();
	
	/**
	 * Called by the beat command to trigger a beat
	 */
	void bip();
}

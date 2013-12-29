package fr.istic.aoc.metronome.controller;

public interface IController {

	/**
	 * Used to start the engine
	 */
	void start();
	
	/**
	 * Used to stop the engine
	 */
	void stop();
	
	/**
	 * Used to increase the number of beat per measure
	 */
	void inc();

	/**
	 * Used to decrease the number of beat per measure
	 */
	void dec();

	/**
	 * Used to update the tempo
	 */
	void tempo();
}

package fr.istic.aoc.metronome.controller;

public interface IControllerListener {
	
	/**
	 * @param bpm
	 */
	void onBPMChanged(int bpm);
	
	/**
	 * @param tempo
	 */
	void onTempoChanged(int tempo);
	
	/**
	 * @param tempo
	 * @param bpm
	 */
	void onStart(int tempo, int bpm);

	/**
	 * 
	 */
	void onStop();
}

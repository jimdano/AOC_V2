package fr.isitc.aoc.metronome.state;

import fr.istic.aoc.components.api.IHorloge;
import fr.istic.aoc.metronome.moteur.IMoteur;


public interface IMoteurState {

	/**
	 * @return true if it is in startedState: false otherwise
	 */
	boolean isStarted();
	
	/**
	 * Start has been called on engine, do some work accordingly
	 * @param engine
	 * @param clock
	 */
	void start(IMoteur engine, IHorloge clock);
	
	/**
	 * Stop has been called on engine, do some work accordingly
	 * @param engine
	 * @param clock
	 */
	void stop(IMoteur engine, IHorloge clock);
	
	/**
	 * Tempo value has been changed
	 * @param engine
	 * @param clock
	 */
	void setTempo(IMoteur engine, IHorloge clock);
}

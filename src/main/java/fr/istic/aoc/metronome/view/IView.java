package fr.istic.aoc.metronome.view;

import fr.istic.aoc.metronome.simulation.ISimulation;

/**
 * @author Jimmy
 */
public interface IView extends ISimulation {

	/**
	 * @param minTempo
	 * @param maxTempo
	 * @param defaultTempo
	 */
	void setTempoValues(int minTempo, int maxTempo, int defaultTempo);

	/**
	 * @param minBpm
	 * @param maxBpm
	 * @param defaultBpm
	 */
	void setBPMValues(int minBpm, int maxBpm, int defaultBpm);
}

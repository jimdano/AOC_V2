package fr.istic.aoc.metronome.moteur;

public interface IMoteurListener {
	
	/**
	 * @param bpm the new bpm value
	 */
	void onBPMChanged(int bpm);
	
	/**
	 * @param tempo the new tempo value
	 */
	void onTempoChanged(int tempo);
	
	/**
	 * Tell the listener that the engine started
	 * @param tempo the current tempo
	 * @param bpm the current bpm
	 */
	void onStart(int tempo, int bpm);
	
	/**
	 * Tell the listener that the engine stopped
	 */
	void onStop();
	
	/**
	 * Tell the listener that is time to make a beat
	 */
	void onBip();
	
	/**
	 * Tell the listener that is time to make a measure
	 */
	void onMesure();
}

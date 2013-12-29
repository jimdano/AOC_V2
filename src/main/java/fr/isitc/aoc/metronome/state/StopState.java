package fr.isitc.aoc.metronome.state;

import fr.istic.aoc.components.api.IHorloge;
import fr.istic.aoc.metronome.moteur.IMoteur;

public class StopState implements IMoteurState {

	public static IMoteurState stop = new StopState();

	public boolean isStarted() {
		return false;
	}

	public void start(IMoteur moteur, IHorloge horloge) {
		float delay = (60 /(float) moteur.getTempo()) * 1000;
		horloge.activerPeriodiquement(moteur.getBipCommand(), 0, (int) delay);
	}

	public void stop(IMoteur moteur, IHorloge horloge) {
	}

	public void setTempo(IMoteur moteur, IHorloge horloge) {
	}
}

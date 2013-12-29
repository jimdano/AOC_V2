package fr.isitc.aoc.metronome.state;

import fr.istic.aoc.components.api.IHorloge;
import fr.istic.aoc.components.command.ICommand;
import fr.istic.aoc.metronome.moteur.IMoteur;

public class StartState implements IMoteurState {

	public static IMoteurState start = new StartState();
	
	public boolean isStarted() {
		return true;
	}

	public void start(IMoteur moteur, IHorloge horloge) {
	}

	public void stop(IMoteur moteur, IHorloge horloge) {
		horloge.desactiver(moteur.getBipCommand());
	}

	public void setTempo(IMoteur moteur, IHorloge horloge) {
		ICommand bip = moteur.getBipCommand();
		horloge.desactiver(bip);
		float delay = (60 /(float) moteur.getTempo()) * 1000;
		horloge.activerPeriodiquement(bip, (int) delay, (int) delay);
	}
}

package fr.istic.aoc.metronome.horloge;

import java.util.Hashtable;
import java.util.Timer;
import java.util.TimerTask;

import fr.istic.aoc.components.api.IHorloge;
import fr.istic.aoc.components.command.ICommand;

public class Horloge implements IHorloge {

	private Hashtable<ICommand, Timer> taches;
	
	/**
	 * @return the taches
	 */
	public Hashtable<ICommand, Timer> getTaches() {
		return taches;
	}

	/**
	 * @param taches the taches to set
	 */
	public void setTaches(Hashtable<ICommand, Timer> taches) {
		this.taches = taches;
	}

	public Horloge() {
		taches = new Hashtable<ICommand, Timer>();
	}
	
	public void activerPeriodiquement(final ICommand cmd, int delay, int period) {		
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				cmd.execute();
			}
		};
		Timer t = new Timer();
		t.scheduleAtFixedRate(task, delay, period);
		taches.put(cmd, t);
	}

	public void activerApresDelai(final ICommand cmd, int delay) {
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				cmd.execute();
			}
		};
		Timer t = new Timer();
		t.schedule(task, delay);
		taches.put(cmd, t);
	}

	public void desactiver(ICommand cmd) {
		Timer timer = taches.get(cmd);
		if (timer != null) {
			timer.cancel();
			taches.remove(cmd);
		}
	}

}

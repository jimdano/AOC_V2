package fr.istic.aoc.metronome.moteur;

import fr.isitc.aoc.metronome.state.IMoteurState;
import fr.isitc.aoc.metronome.state.StartState;
import fr.isitc.aoc.metronome.state.StopState;
import fr.istic.aoc.components.api.IHorloge;
import fr.istic.aoc.components.command.ICommand;
import fr.istic.aoc.metronome.controller.MetronomeController;
import fr.istic.aoc.metronome.horloge.Horloge;

public class MoteurMetronome implements IMoteur {

	private int tempo;
	private int bpm;
	private int bipCount;

	private IHorloge horloge;
	private ICommand bipCmd;
	private IMoteurState state = StopState.stop;

	public MoteurMetronome(int tempo, int bpm) {
		this.tempo = tempo;
		this.bpm = bpm;
		this.bipCount = 0;
		this.bipCmd = new ICommand() {
			public void execute() {
				System.err.println("Pas de Bip command");
			}
		};
		this.horloge = new Horloge();
	}

	public boolean isStarted() {
		return state.isStarted();
	}

	public void start() {
		state.start(this, horloge);
		state = StartState.start;
		MetronomeController.getInstance().onStart(tempo, bpm);
	}

	public void stop() {
		state.stop(this, horloge);
		state = StopState.stop;
		MetronomeController.getInstance().onStop();
	}

	public int getBpm() {
		return bpm;
	}

	public void setBpm(int value) {
		bpm = value;
		MetronomeController.getInstance().onBPMChanged(value);
	}

	public int getTempo() {
		return tempo;
	}

	public void setTempo(int value) {
		tempo = value;
		state.setTempo(this, horloge);
		MetronomeController.getInstance().onTempoChanged(value);
	}

	public void setBipCommand(ICommand cmd) {
		bipCmd = cmd;
	}

	public ICommand getBipCommand() {
		return bipCmd;
	}

	public void bip() {
		MetronomeController.getInstance().onBip();
		bipCount++;
		if (bipCount % bpm == 0) {
			MetronomeController.getInstance().onMesure();
			bipCount = 0;
		}
	}
	
	/**
	 * @return the bipCount
	 */
	public int getBipCount() {
		return bipCount;
	}

	/**
	 * @param bipCount the bipCount to set
	 */
	public void setBipCount(int bipCount) {
		this.bipCount = bipCount;
	}
}

package fr.istic.aoc.metronome.launcher;

import javax.swing.JFrame;

import fr.istic.aoc.metronome.controller.MetronomeController;
import fr.istic.aoc.metronome.moteur.MoteurMetronome;
import fr.istic.aoc.metronome.view.Metronome;

/**
 * @author Jimmy
 */
public class MetronomeLauncher extends JFrame {

	private static final long serialVersionUID = 1L;

	private static final int width = 280;
	private static final int height = 220;

	private Metronome view;
	private MoteurMetronome engine;

	public MetronomeLauncher() {
		super("Métronomium V1");
		
		view = new Metronome();
		MetronomeController.getInstance();
		engine = new MoteurMetronome(MetronomeController.INIT_TEMPO, MetronomeController.INIT_BPM);
		MetronomeController.getInstance().setMoteur(engine);
		MetronomeController.getInstance().setView(view);
		MetronomeController.getInstance().addControllerListener(view);
		view.init();
		MetronomeController.getInstance().init();
		setSize(width, height);
		setLocationRelativeTo(this.getParent());
		add(this.view);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new MetronomeLauncher();
	}
}

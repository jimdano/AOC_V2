/**
 * 
 */
package fr.istic.aoc.metronome.controller;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import fr.isitc.aoc.metronome.command.BipCommand;
import fr.istic.aoc.metronome.moteur.MoteurMetronome;
import fr.istic.aoc.metronome.view.Metronome;

/**
 * @author jimmy
 *
 */
public class MetronomeControllerTest {

	private static final int MIN_BPM = MetronomeController.MIN_BPM;
	private static final int MAX_BPM = MetronomeController.MAX_BPM;
	private static final int INIT_TEMPO = MetronomeController.INIT_TEMPO;
	private static final int INIT_BPM = MetronomeController.INIT_BPM;
	
	private Metronome view;
	private MoteurMetronome moteur;
	private MetronomeController ctrl;
	private Metronome view2;
	private MoteurMetronome moteur2;
	
	@Before
	public void setUp() throws Exception {
		ctrl = MetronomeController.getInstance();
		moteur = new MoteurMetronome(INIT_TEMPO, INIT_BPM);
		moteur.setBipCommand(new BipCommand(moteur));
		view = new Metronome();
		ctrl.setMoteur(moteur);
		ctrl.addControllerListener(view);
		ctrl.setView(view);
		view.init();
	}
	
	/**
	 * Test method for {@link fr.istic.aoc.metronome.controller.MetronomeController#getInstance()}.
	 */
	@Test
	public void testGetInstance() {
		assertNotNull(ctrl);
	}

	/**
	 * Test method for {@link fr.istic.aoc.metronome.controller.MetronomeController#init()}.
	 */
	@Test
	public void testInit() {
		
	}

	/**
	 * Test method for {@link fr.istic.aoc.metronome.controller.MetronomeController#start()}.
	 */
	@Test
	public void testStart() {
		ctrl.start();
		assertTrue(ctrl.getMoteur().isStarted());
	}

	/**
	 * Test method for {@link fr.istic.aoc.metronome.controller.MetronomeController#stop()}.
	 */
	@Test
	public void testStop() {
		ctrl.start();
		assertTrue(ctrl.getMoteur().isStarted());
		ctrl.stop();
		assertFalse(ctrl.getMoteur().isStarted());
	}

	/**
	 * Test method for {@link fr.istic.aoc.metronome.controller.MetronomeController#inc()}.
	 */
	@Test
	public void testInc() {
		boolean same = false;
		if(moteur.getBpm() == MAX_BPM){
			ctrl.dec();
			int val = moteur.getBpm();
			ctrl.inc();
			assertTrue(moteur.getBpm() == (val+1) && moteur.getBpm() == MAX_BPM);
		} else {
			while(!same) {
				int val = moteur.getBpm();
				ctrl.inc();
				if(val == moteur.getBpm()){
					same = true;
				}
			}
			assertTrue(moteur.getBpm() == MAX_BPM);
		}
	}

	/**
	 * Test method for {@link fr.istic.aoc.metronome.controller.MetronomeController#dec()}.
	 */
	@Test
	public void testDec() {
		boolean same = false;
		if(moteur.getBpm() == MIN_BPM){
			ctrl.inc();
			int val = moteur.getBpm();
			ctrl.dec();
			assertTrue(moteur.getBpm() == (val-1) && moteur.getBpm() == MIN_BPM);
		} else {
			while(!same) {
				int val = moteur.getBpm();
				ctrl.dec();
				if(val == moteur.getBpm()){
					same = true;
				}
			}
			assertTrue(moteur.getBpm() == MIN_BPM);
		}
	}

	/**
	 * Test method for {@link fr.istic.aoc.metronome.controller.MetronomeController#tempo()}.
	 */
	@Test
	public void testTempo() {
		//pas possible de tester sans mocks...
	}

	/**
	 * Test method for {@link fr.istic.aoc.metronome.controller.MetronomeController#getMoteur()}.
	 */
	@Test
	public void testGetMoteur() {
		assertTrue(ctrl.getMoteur() == moteur);
	}
	
	/**
	 * Test method for {@link fr.istic.aoc.metronome.controller.MetronomeController#setMoteur(fr.istic.aoc.metronome.moteur.IMoteur)}.
	 */
	@Test
	public void testSetMoteur() {
		assertTrue(ctrl.getMoteur() == moteur);
		ctrl.setMoteur(null);
		assertFalse(ctrl.getMoteur() == moteur);
		moteur2 = new MoteurMetronome(INIT_TEMPO,INIT_BPM);
		ctrl.setMoteur(moteur2);
		assertTrue(ctrl.getMoteur() == moteur2);
	}

	/**
	 * Test method for {@link fr.istic.aoc.metronome.controller.MetronomeController#getView()}.
	 */
	@Test
	public void testGetView() {
		assertTrue(ctrl.getView() == view);
	}
	
	/**
	 * Test method for {@link fr.istic.aoc.metronome.controller.MetronomeController#setView(fr.istic.aoc.metronome.view.IView)}.
	 */
	@Test
	public void testSetView() {
		assertTrue(ctrl.getView() == view);
		ctrl.setView(null);
		assertFalse(ctrl.getView() == view);
		view2 = new Metronome();
		ctrl.setView(view2);
		assertTrue(ctrl.getView() == view2);
	}
}

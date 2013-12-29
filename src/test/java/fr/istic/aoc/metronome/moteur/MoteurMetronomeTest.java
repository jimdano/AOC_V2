/**
 * 
 */
package fr.istic.aoc.metronome.moteur;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fr.isitc.aoc.metronome.command.BipCommand;
import fr.istic.aoc.components.command.ICommand;
import fr.istic.aoc.metronome.controller.MetronomeController;
import fr.istic.aoc.metronome.view.Metronome;

/**
 * @author jimmy
 *
 */
public class MoteurMetronomeTest {

	private static final int TEMPO = MetronomeController.INIT_TEMPO;
	private static final int BPM = MetronomeController.INIT_BPM;
	
	private MoteurMetronome moteur;
	private ICommand bipCmd;
	private BipCommand bipCmd2;
	private Metronome view;
	private MetronomeController ctrl;
	
	@Before
	public void setUp() throws Exception {
		moteur = new MoteurMetronome(TEMPO, BPM);
		bipCmd = new BipCommand(moteur);
		moteur.setBipCommand(bipCmd);
		ctrl = MetronomeController.getInstance();
		view = new Metronome();
		ctrl.setMoteur(moteur);
		ctrl.addControllerListener(view);
		ctrl.setView(view);
		view.init();
	}
	
	/**
	 * Test method for {@link fr.istic.aoc.metronome.moteur.MoteurMetronome#isStarted()}.
	 */
	@Test
	public void testIsStarted() {
		assertFalse(moteur.isStarted());
	}

	/**
	 * Test method for {@link fr.istic.aoc.metronome.moteur.MoteurMetronome#start()}.
	 */
	@Test
	public void testStart() {
		assertFalse(moteur.isStarted());
		moteur.start();		
		assertTrue(moteur.isStarted());
	}

	/**
	 * Test method for {@link fr.istic.aoc.metronome.moteur.MoteurMetronome#stop()}.
	 */
	@Test
	public void testStop() {
		assertFalse(moteur.isStarted());
		moteur.start();		
		assertTrue(moteur.isStarted());
		moteur.stop();
		assertFalse(moteur.isStarted());
	}

	/**
	 * Test method for {@link fr.istic.aoc.metronome.moteur.MoteurMetronome#getBpm()}.
	 */
	@Test
	public void testGetBpm() {
		assertEquals(BPM, moteur.getBpm());
	}

	/**
	 * Test method for {@link fr.istic.aoc.metronome.moteur.MoteurMetronome#setBpm(int)}.
	 */
	@Test
	public void testSetBpm() {
		moteur.setBpm(BPM + 1 );
		assertEquals(BPM+1, moteur.getBpm());
	}

	/**
	 * Test method for {@link fr.istic.aoc.metronome.moteur.MoteurMetronome#getTempo()}.
	 */
	@Test
	public void testGetTempo() {
		assertEquals(TEMPO, moteur.getTempo());
	}

	/**
	 * Test method for {@link fr.istic.aoc.metronome.moteur.MoteurMetronome#setTempo(int)}.
	 */
	@Test
	public void testSetTempo() {
		moteur.setTempo(TEMPO + 1);
		assertEquals(TEMPO+1, moteur.getTempo());
	}

	/**
	 * Test method for {@link fr.istic.aoc.metronome.moteur.MoteurMetronome#getBipCommand()}.
	 */
	@Test
	public void testGetBipCommand() {
		assertEquals(bipCmd, moteur.getBipCommand());
	}
	
	/**
	 * Test method for {@link fr.istic.aoc.metronome.moteur.MoteurMetronome#setBipCommand(fr.istic.aoc.components.command.ICommand)}.
	 */
	@Test
	public void testSetBipCommand() {
		bipCmd2 = new BipCommand(moteur);
		moteur.setBipCommand(bipCmd2);
		assertEquals(bipCmd2, moteur.getBipCommand());
	}
	
	@Test
	public void testGetBipCount() {
		assertTrue(moteur.getBipCount() == 0);
	}

	@Test
	public void testSetBipCount() {
		assertTrue(moteur.getBipCount() == 0);
		moteur.setBipCount(4);
		assertTrue(moteur.getBipCount() == 4);
	}

	/**
	 * Test method for {@link fr.istic.aoc.metronome.moteur.MoteurMetronome#bip()}.
	 */
	@Test
	public void testBip() {
		assertTrue(moteur.getBipCount() == 0);
		while(moteur.getBipCount() < MetronomeController.INIT_BPM -1){
			moteur.bip();
		}
		assertTrue(moteur.getBipCount() == MetronomeController.INIT_BPM -1);
		moteur.bip();
		assertTrue(moteur.getBipCount() == 0);
	}
}

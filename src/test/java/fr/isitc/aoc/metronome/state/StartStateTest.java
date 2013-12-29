/**
 * 
 */
package fr.isitc.aoc.metronome.state;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import fr.isitc.aoc.metronome.command.BipCommand;
import fr.istic.aoc.metronome.horloge.Horloge;
import fr.istic.aoc.metronome.moteur.MoteurMetronome;

/**
 * @author jimmy
 *
 */
public class StartStateTest {

	private static final int TEMPO = 10;
	private static final int BPM = 4;
	private MoteurMetronome moteur;
	private Horloge horloge;
	private BipCommand bipCmd;
	private IMoteurState state;
	
	@Before
	public void setUp() throws Exception {
		state = StartState.start;
		moteur = new MoteurMetronome(TEMPO, BPM);
		bipCmd = new BipCommand(moteur);
		moteur.setBipCommand(bipCmd);
		moteur.start();
		horloge = new Horloge();
	}
	/**
	 * Test method for {@link fr.isitc.aoc.metronome.state.StartState#isStarted()}.
	 */
	@Test
	public void testIsStarted() {
		assertTrue(state.isStarted());
	}

	/**
	 * Test method for {@link fr.isitc.aoc.metronome.state.StartState#start(fr.istic.aoc.metronome.moteur.IMoteur, fr.istic.aoc.components.api.IHorloge)}.
	 */
	@Test
	public void testStart() {
		IMoteurState stateCopy = state;
		state.start(moteur, horloge);
		assertEquals(state, stateCopy);
	}

	/**
	 * Test method for {@link fr.isitc.aoc.metronome.state.StartState#stop(fr.istic.aoc.metronome.moteur.IMoteur, fr.istic.aoc.components.api.IHorloge)}.
	 */
	@Test
	public void testStop() {
		state.stop(moteur, horloge);
		assertTrue(horloge.getTaches().size() == 0);
	}

	/**
	 * Test method for {@link fr.isitc.aoc.metronome.state.StartState#setTempo(fr.istic.aoc.metronome.moteur.IMoteur, fr.istic.aoc.components.api.IHorloge)}.
	 */
	@Test
	public void testSetTempo() {
		assertTrue(horloge.getTaches().size() == 0);
		state.setTempo(moteur, horloge);
		assertTrue(horloge.getTaches().size() == 1);
	}

}

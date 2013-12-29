package fr.isitc.aoc.metronome.state;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import fr.isitc.aoc.metronome.command.BipCommand;
import fr.istic.aoc.metronome.horloge.Horloge;
import fr.istic.aoc.metronome.moteur.MoteurMetronome;

public class StopStateTest {

	private static final int TEMPO = 10;
	private static final int BPM = 4;
	private MoteurMetronome moteur;
	private Horloge horloge;
	private BipCommand bipCmd;
	private IMoteurState state;
	
	@Before
	public void setUp() throws Exception {
		state = StopState.stop;
		moteur = new MoteurMetronome(TEMPO, BPM);
		bipCmd = new BipCommand(moteur);
		moteur.setBipCommand(bipCmd);
		moteur.stop();
		horloge = new Horloge();
	}
	
	@Test
	public void testIsStarted() {
		assertFalse(state.isStarted());
	}

	@Test
	public void testStart() {
		assertTrue(horloge.getTaches().size() == 0);
		state.start(moteur, horloge);
		assertTrue(horloge.getTaches().size() == 1);
		assertNotNull(horloge.getTaches().get(bipCmd));
	}

	@Test
	public void testStop() {
		IMoteurState stateCopy = state;
		state.stop(moteur, horloge);
		assertEquals(state, stateCopy);
	}

	@Test
	public void testSetTempo() {
		IMoteurState stateCopy = state;
		state.setTempo(moteur, horloge);
		assertEquals(state, stateCopy);
	}

}

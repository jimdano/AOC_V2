/**
 * 
 */
package fr.istic.aoc.metronome.horloge;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Hashtable;
import java.util.Timer;

import org.junit.Before;
import org.junit.Test;

import fr.isitc.aoc.metronome.command.BipCommand;
import fr.istic.aoc.components.command.ICommand;
import fr.istic.aoc.metronome.moteur.MoteurMetronome;

/**
 * @author jimmy
 *
 */
public class HorlogeTest {

	private static final int TEMPO = 10;
	private static final int BPM = 4;
	private MoteurMetronome moteur;
	private static int delay = 19;
	private static int period = 12;
	private Horloge horloge;
	private BipCommand bipCmd;
	
	@Before
	public void setUp() throws Exception {
		moteur = new MoteurMetronome(TEMPO, BPM);
		bipCmd = new BipCommand(moteur);
		horloge = new Horloge();
	}
	
	/**
	 * Test method for {@link fr.istic.aoc.metronome.horloge.Horloge#Horloge()}.
	 */
	@Test
	public void testHorloge() {
		assertNotNull(horloge.getTaches());
		assertTrue(horloge.getTaches().size() == 0);
	}

	/**
	 * Test method for {@link fr.istic.aoc.metronome.horloge.Horloge#activerPeriodiquement(fr.istic.aoc.components.command.ICommand, int, int)}.
	 */
	@Test
	public void testActiverPeriodiquement() {
		assertTrue(horloge.getTaches().size() == 0);
		horloge.activerPeriodiquement(bipCmd, delay, period);
		assertTrue(horloge.getTaches().size() == 1);
		assertNotNull(horloge.getTaches().get(bipCmd));
	}

	/**
	 * Test method for {@link fr.istic.aoc.metronome.horloge.Horloge#activerApresDelai(fr.istic.aoc.components.command.ICommand, int)}.
	 */
	@Test
	public void testActiverApresDelai() {
		assertTrue(horloge.getTaches().size() == 0);
		horloge.activerApresDelai(bipCmd, delay);
		assertTrue(horloge.getTaches().size() == 1);
		assertNotNull(horloge.getTaches().get(bipCmd));
	}

	/**
	 * Test method for {@link fr.istic.aoc.metronome.horloge.Horloge#desactiver(fr.istic.aoc.components.command.ICommand)}.
	 */
	@Test
	public void testDesactiver() {
		Hashtable<ICommand, Timer> task = new Hashtable<ICommand, Timer>();
		task.put(bipCmd, new Timer());
		horloge.desactiver(bipCmd);
		assertTrue(horloge.getTaches().size() == 0);
		horloge.setTaches(task);
		assertTrue(horloge.getTaches().size() == 1);
		horloge.desactiver(bipCmd);
		assertTrue(horloge.getTaches().size() == 0);
	}
}

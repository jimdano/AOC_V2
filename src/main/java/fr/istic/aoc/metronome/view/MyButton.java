package fr.istic.aoc.metronome.view;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;


/**
 * @author Jimmy
 */
public class MyButton extends JButton implements MouseListener {

	private static final long serialVersionUID = 1L;

	private boolean pressed = false;

	public MyButton(String s) {
		super(s);
	}

	public boolean isPressed() {
		return pressed;
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
		pressed = true;
	}

	public void mouseReleased(MouseEvent e) {
		pressed = false;
	}
}

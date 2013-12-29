package fr.istic.aoc.metronome.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import fr.istic.aoc.components.api.IAfficheur;
import fr.istic.aoc.components.api.IClavier;
import fr.istic.aoc.components.api.IEmetteurSonore;
import fr.istic.aoc.components.api.IHorloge;
import fr.istic.aoc.components.api.IMolette;
import fr.istic.aoc.metronome.controller.IControllerListener;
import fr.istic.aoc.metronome.controller.MetronomeController;

/**
 * @author Jimmy
 */
public class Metronome extends JPanel implements IView, IControllerListener {

	private static final long serialVersionUID = 1L;
	
	private static final String LED_TEMPO_LABEL = "TEMPO";
	private static final String LED_MESURE_LABEL = "MESURE";
	
	private static final String START_BOUTON = "Start";
	private static final String STOP_BOUTON = "Stop";
	private static final String INC_BOUTON = "Inc";
	private static final String DEC_BOUTON = "Dec";

	private JSlider tempoSlider;

	private ImageIcon ledRouge;
	private ImageIcon ledBleue;
	private ImageIcon ledOff;
	
	private JTextField displayLabel;
	private JLabel[] ledLabels;

	private MyButton[] cmdButtons;
	
	private MyButton startBtn; 
	private MyButton stopBtn;	
	private MyButton incBtn;	
	private MyButton decBtn;	
	
	private int minBPM;
	private int maxBPM;

	private JPanel ledsPanel;

	private JPanel btnsPanel;

	public Metronome() {

	}

	public void init(){
		initComponents();
		configureView();
		tempoSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent evt) {
				MetronomeController.getInstance().tempo();
			}
		});
		
		startBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				MetronomeController.getInstance().start();
			}
		});
		
		stopBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				MetronomeController.getInstance().stop();
			}
		});
		
		incBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				MetronomeController.getInstance().inc();
			}
		});
		
		decBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				MetronomeController.getInstance().dec();
			}
		});
	}
	
	private void initComponents() {
		tempoSlider = new JSlider(JSlider.VERTICAL);
		
		ledBleue = new ImageIcon(getClass().getResource("/images/bleu.png"));
		ledRouge = new ImageIcon(getClass().getResource("/images/rouge.png"));
		ledOff = new ImageIcon(getClass().getResource("/images/blanc.png"));
		
		displayLabel = new JTextField();
		displayLabel.setEditable(false);
		ledLabels = new JLabel[2];
		ledLabels[IAfficheur.TEMPO] = new JLabel(LED_TEMPO_LABEL, ledOff, JLabel.RIGHT);
		ledLabels[IAfficheur.MEASURE] = new JLabel(LED_MESURE_LABEL, ledOff, JLabel.RIGHT);

		startBtn = new MyButton(START_BOUTON);
		stopBtn = new MyButton(STOP_BOUTON);
		incBtn = new MyButton(INC_BOUTON);
		decBtn = new MyButton(DEC_BOUTON);
		
		cmdButtons = new MyButton[4];
		cmdButtons[IClavier.START] = startBtn;
		cmdButtons[IClavier.STOP] = stopBtn;
		cmdButtons[IClavier.INC] = incBtn;
		cmdButtons[IClavier.DEC] = decBtn;
	}

	private void configureView() {
		setLayout(new BorderLayout());
		
		tempoSlider.setMajorTickSpacing(100);
		tempoSlider.setMinorTickSpacing(15);
		tempoSlider.setPaintTicks(true);
		tempoSlider.setPaintLabels(true);
		tempoSlider.setBorder(new EmptyBorder(0, 10, 0, 0));
		
		displayLabel.setHorizontalAlignment(JTextField.CENTER);
		ledsPanel = new JPanel(new GridLayout(1, 2));
		ledsPanel.setBorder(new EmptyBorder(0, 0, 0, 10));
		ledsPanel.add(ledLabels[0]);
		ledsPanel.add(ledLabels[1]);
		
		btnsPanel = new JPanel(new GridLayout(1, 4));
		btnsPanel.add(startBtn);
		btnsPanel.add(stopBtn);
		btnsPanel.add(incBtn);
		btnsPanel.add(decBtn);

		add(tempoSlider, BorderLayout.WEST);
		add(displayLabel, BorderLayout.NORTH);
		add(ledsPanel, BorderLayout.CENTER);
		add(btnsPanel, BorderLayout.SOUTH);
	}
	
	public void setTempoValues(int min, int max, int init) {
		tempoSlider.setMinimum(min);
		tempoSlider.setMaximum(max);
		tempoSlider.setValue(init);
		onTempoChanged(init);
	}
	
	public void setBPMValues(int minBpm, int maxBpm, int defaultBpm) {
		minBPM = minBpm;
		maxBPM = maxBpm;
	}
	
	public void onStart(int tempo, int bpm) {
		startBtn.setEnabled(false);
		stopBtn.setEnabled(true);
		
		onTempoChanged(tempo);
		onBPMChanged(bpm);
	}
	
	public void onStop() {
		startBtn.setEnabled(true);
		stopBtn.setEnabled(false);
		incBtn.setEnabled(false);
		decBtn.setEnabled(false);
	}

	public void onBPMChanged(int bpm) {
		if (bpm > minBPM) decBtn.setEnabled(true);
		else decBtn.setEnabled(false);
		
		if (bpm < maxBPM) incBtn.setEnabled(true);
		else incBtn.setEnabled(false);
	}

	public void onTempoChanged(int tempo) {
		displayLabel.setText(String.valueOf(tempo)+ " b/min");
	}
		
	public void allumerLed(int id) {
		switch (id) {
			case IAfficheur.TEMPO:
				ledLabels[id].setIcon(ledRouge);
				break;
			case IAfficheur.MEASURE:
				ledLabels[id].setIcon(ledBleue);
				break;
			default:
				System.err.println("Not cool");
				break;
		}
	}

	public void eteindreLed(int id) {
		ledLabels[id].setIcon(ledOff);
	}

	public void afficherTempo(int value) {
		displayLabel.setText(String.valueOf(value));		
	}

	public boolean touchePressee(int id) {
		return cmdButtons[id].isPressed();
	}

	public float position() {
		return tempoSlider.getValue();
	}

	public void emettreClick() {
		Toolkit.getDefaultToolkit().beep();
	}

	public IClavier getClavier() {
		return this;
	}

	public IHorloge getHorloge() {
		return null;
	}

	public IAfficheur getAfficheur() {
		return this;
	}

	public IEmetteurSonore getEmetteur() {
		return this;
	}

	public IMolette getMolette() {
		return this;
	}
}

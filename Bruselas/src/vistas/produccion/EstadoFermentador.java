package vistas.produccion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class EstadoFermentador extends JFrame {

	private JPanel contentPane;
	private JPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EstadoFermentador frame = new EstadoFermentador();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EstadoFermentador() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 566, 488);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(6, 6, 554, 454);
		contentPane.add(panel);
		panel.setLayout(null);

		
		
//		UIManager.put( "ProgressBar.background", Color.RED);
//		UIManager.put( "ProgressBar.selectionBackground", Color.RED);
//		UIManager.put( "ProgressBar.selectionForeground", Color.RED);
		JProgressBar progressBar = new JProgressBar();
		progressBar.setOrientation(SwingConstants.VERTICAL);
		progressBar.setBounds(84, 80, 374, 338);
		panel.add(progressBar);
		progressBar.setMinimum(0);
		progressBar.setMaximum(2500);
		progressBar.setValue(1000);
		
//		progressBar.setStringPainted(true);
		
		
		
	}
} 

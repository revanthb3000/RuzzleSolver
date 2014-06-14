package org.rb.ruzzlsolver.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;


import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.rb.ruzzlesolver.RuzzleSolver;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class MainApp {

	private JFrame frmRuzzleSolver;
	private JTextField wordLimitTextField;
	private JTextField puzzleStringTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainApp window = new MainApp();
					window.frmRuzzleSolver.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		System.setProperty("http.proxySet", "false");
		frmRuzzleSolver = new JFrame();
		frmRuzzleSolver.setTitle("Ruzzle Solver (RB)");
		frmRuzzleSolver.setBounds(100, 100, 631, 260);
		frmRuzzleSolver.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRuzzleSolver.getContentPane().setLayout(null);
		
		wordLimitTextField = new JTextField();
		wordLimitTextField.setText("500");
		wordLimitTextField.setBounds(316, 58, 122, 16);
		frmRuzzleSolver.getContentPane().add(wordLimitTextField);
		wordLimitTextField.setColumns(10);
		
		JLabel lblWordLimit = new JLabel("Word Limit");
		lblWordLimit.setBounds(220, 58, 91, 16);
		frmRuzzleSolver.getContentPane().add(lblWordLimit);
		
		puzzleStringTextField = new JTextField();
		puzzleStringTextField.setText("abcd efgh hijk lmno");
		puzzleStringTextField.setBounds(316, 26, 143, 20);
		frmRuzzleSolver.getContentPane().add(puzzleStringTextField);
		puzzleStringTextField.setColumns(10);
		
		JLabel lblWhatDoWe = new JLabel("What do we have to solve ?");
		lblWhatDoWe.setBounds(138, 28, 173, 16);
		frmRuzzleSolver.getContentPane().add(lblWhatDoWe);
		
		JButton btnNewButton = new JButton("Solve Puzzle");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RuzzleSolver.startSolving(puzzleStringTextField.getText(), Integer.parseInt(wordLimitTextField.getText()));			
			}
		});
		btnNewButton.setBounds(208, 105, 251, 26);
		frmRuzzleSolver.getContentPane().add(btnNewButton);
		
		JLabel lblifYouHavent = new JLabel("*If you haven't configured this app yet, run Calibrator.jar");
		lblifYouHavent.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
		lblifYouHavent.setBounds(170, 156, 337, 16);
		frmRuzzleSolver.getContentPane().add(lblifYouHavent);
		
		JLabel lblMadeByRevanthb = new JLabel("Made by revanthb3000");
		lblMadeByRevanthb.setBounds(244, 206, 194, 16);
		frmRuzzleSolver.getContentPane().add(lblMadeByRevanthb);
		
	}
}

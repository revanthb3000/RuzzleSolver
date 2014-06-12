package org.rb.ruzzlsolver.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;


import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.rb.ruzzlesolver.RuzzleSolver;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainApp {

	private JFrame frame;
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
					window.frame.setVisible(true);
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
		frame = new JFrame();
		frame.setBounds(100, 100, 576, 193);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		wordLimitTextField = new JTextField();
		wordLimitTextField.setText("500");
		wordLimitTextField.setBounds(280, 44, 122, 16);
		frame.getContentPane().add(wordLimitTextField);
		wordLimitTextField.setColumns(10);
		
		JLabel lblWordLimit = new JLabel("Word Limit");
		lblWordLimit.setBounds(184, 44, 91, 16);
		frame.getContentPane().add(lblWordLimit);
		
		puzzleStringTextField = new JTextField();
		puzzleStringTextField.setText("abcd efgh hijk lmno");
		puzzleStringTextField.setBounds(280, 12, 143, 20);
		frame.getContentPane().add(puzzleStringTextField);
		puzzleStringTextField.setColumns(10);
		
		JLabel lblWhatDoWe = new JLabel("What do we have to solve ?");
		lblWhatDoWe.setBounds(102, 14, 173, 16);
		frame.getContentPane().add(lblWhatDoWe);
		
		JButton btnNewButton = new JButton("Solve Puzzle");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RuzzleSolver.startSolving(puzzleStringTextField.getText(), Integer.parseInt(wordLimitTextField.getText()));			
			}
		});
		btnNewButton.setBounds(172, 91, 251, 26);
		frame.getContentPane().add(btnNewButton);
		
	}
}

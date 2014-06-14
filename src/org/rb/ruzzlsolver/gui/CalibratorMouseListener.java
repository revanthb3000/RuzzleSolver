package org.rb.ruzzlsolver.gui;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class CalibratorMouseListener implements MouseListener {

	private static int clickNumber = 1;
	
	private static int[][] X = new int[4][4];

	private static int[][] Y = new int[4][4];
	
	private JLabel label = null;
	
	private JFrame parentHandle = null;
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		Point point = MouseInfo.getPointerInfo().getLocation();
		System.out.println("Clicked bitch - " + point);
		clickNumber++;
		label.setText("Click on the tile #" + clickNumber);
		if(clickNumber==16){
	        WindowEvent wev = new WindowEvent(parentHandle, WindowEvent.WINDOW_CLOSING);
	        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}

	public void setLabel(JLabel label) {
		this.label = label;
	}

	public void setParentHandle(JFrame parentHandle) {
		this.parentHandle = parentHandle;
	}
	

}

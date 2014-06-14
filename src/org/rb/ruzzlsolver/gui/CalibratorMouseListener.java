package org.rb.ruzzlsolver.gui;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * The mouse listener that reads screen co-ordinates, stores it and then updates the config file.
 * @author RB
 *
 */
public class CalibratorMouseListener implements MouseListener {

	/**
	 * Keeping count of the number of clicks.
	 */
	private static int clickNumber = 1;

	/**
	 * The X co-ordinates.
	 */
	private static int[][] X = new int[4][4];


	/**
	 * The Y co-ordinates.
	 */
	private static int[][] Y = new int[4][4];

	/**
	 * Just a iterator variable for X
	 */
	private static int globalXCount = 0;

	/**
	 * Just a iterator variable for Y
	 */
	private static int globalYCount = 0;

	/**
	 * The label in the Main App that we'll be playing with.
	 */
	private JLabel label = null;

	/**
	 * ParentHandle - used to close the main app.
	 */
	private JFrame parentHandle = null;
	
	/**
	 * Name of the configuration file.
	 */
	private final String CONFIG_FILE_NAME = "positions.conf";

	/**
	 * What am I doing here ?
	 * Incrementing click count, storing co-ordinates and then moving on to the next tile.
	 */
	@Override
	public void mouseClicked(MouseEvent arg0) {
		Point point = MouseInfo.getPointerInfo().getLocation();
		clickNumber++;

		if (clickNumber == 18) {
			WindowEvent wev = new WindowEvent(parentHandle, WindowEvent.WINDOW_CLOSING);
			Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
		}

		X[globalXCount][globalYCount] = point.x;
		Y[globalXCount][globalYCount] = point.y;
		globalYCount++;
		if (globalYCount == 4) {
			globalXCount++;
			globalYCount = 0;
		}

		if (clickNumber == 17) {
			updateConfigFile();
			label.setText("Settings saved. Click again to close this window.");
		} else {
			label.setText("Click on tile #" + clickNumber + " (Row No. " + (globalXCount + 1) + " & Column No. " + (globalYCount + 1) + ")");
		}
	}

	/**
	 * This functions will take the values of my X,Y arrays and put it into the configuration file.
	 */
	private void updateConfigFile() {
		try {
			int xCount = 0, yCount = 0;
			FileReader fileReader = new FileReader(CONFIG_FILE_NAME);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line = "";
			String fileContent = "";
			while ((line = bufferedReader.readLine()) != null) {
				if ((line.trim().contains("#")) || (line.trim().equals(""))) {
					fileContent += line + "\n";
				} else {
					fileContent += X[xCount][yCount] + "," + Y[xCount][yCount] + "" + "\n";
					yCount++;
					if (yCount == 4) {
						xCount++;
						yCount = 0;
					}
				}
			}
			bufferedReader.close();
			fileReader.close();
			
			FileWriter fileWriter = new FileWriter(CONFIG_FILE_NAME);
			fileWriter.write(fileContent);
			fileWriter.close();
			
		} catch (Exception e) {
			System.out.println("Exception while updating file.");
			e.printStackTrace();
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

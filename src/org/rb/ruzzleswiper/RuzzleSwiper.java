package org.rb.ruzzleswiper;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.ArrayList;

import org.rb.ruzzlesolver.ds.Position;

/**
 * This function will be used to automate the word swiping process. Bluestacks
 * is used and hence the mouse pointer can be used to control actions.
 * 
 * @author RB
 * 
 */
public class RuzzleSwiper {

	/**
	 * This is used to represent the X-Y co-ordinates of each tile on the
	 * screen.
	 */
	private Position[][] tileCoordinates;

	/**
	 * This is the amount of time between movement between two tiles.
	 * I've found 25 to be a good enough value. Using 30 for safety.
	 * Set a higher value to debug it.
	 */
	private int pauseTime;

	public RuzzleSwiper(int time) {
		pauseTime = time;
		tileCoordinates = new Position[4][4];

		tileCoordinates[0][0] = new Position(1176, 306, 4);
		tileCoordinates[0][1] = new Position(1293, 306, 4);
		tileCoordinates[0][2] = new Position(1412, 306, 4);
		tileCoordinates[0][3] = new Position(1527, 306, 4);

		tileCoordinates[1][0] = new Position(1176, 430, 4);
		tileCoordinates[1][1] = new Position(1293, 430, 4);
		tileCoordinates[1][2] = new Position(1412, 430, 4);
		tileCoordinates[1][3] = new Position(1527, 430, 4);

		tileCoordinates[2][0] = new Position(1176, 537, 4);
		tileCoordinates[2][1] = new Position(1293, 537, 4);
		tileCoordinates[2][2] = new Position(1412, 537, 4);
		tileCoordinates[2][3] = new Position(1527, 537, 4);

		tileCoordinates[3][0] = new Position(1176, 657, 4);
		tileCoordinates[3][1] = new Position(1293, 657, 4);
		tileCoordinates[3][2] = new Position(1412, 657, 4);
		tileCoordinates[3][3] = new Position(1527, 657, 4);
	}

	/**
	 * Given a list of tiles, this guy swipes them all.
	 * 
	 * @param positions
	 */
	public void clickPositions(ArrayList<Position> positions) {
		try {
			Robot robot = new Robot();
			Position position = tileCoordinates[positions.get(0).getX()][positions.get(0).getY()];
			robot.mouseMove(position.getX(), position.getY());
			robot.mousePress(InputEvent.BUTTON1_MASK);
			Thread.sleep(pauseTime);

			for (int i = 1; i < positions.size(); i++) {
				position = tileCoordinates[positions.get(i).getX()][positions.get(i).getY()];
				robot.mouseMove(position.getX(), position.getY());
				Thread.sleep(pauseTime);
			}

			robot.mouseRelease(InputEvent.BUTTON1_MASK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

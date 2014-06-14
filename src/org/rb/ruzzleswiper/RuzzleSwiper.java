package org.rb.ruzzleswiper;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.BufferedReader;
import java.io.FileReader;
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
	
	/**
	 * Name of the Configuration file.
	 */
	private final String CONFIG_FILE_NAME = "positions.conf";

	public RuzzleSwiper(int time) {
		pauseTime = time;
		tileCoordinates = new Position[4][4];
		int[][] X = new int[4][4];
		int[][] Y = new int[4][4];

		try{
			int xCount = 0, yCount = 0;
			FileReader fileReader = new FileReader(CONFIG_FILE_NAME);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line = "";
			while((line=bufferedReader.readLine())!=null){
				if((line.trim().contains("#")) || (line.trim().equals(""))){
					continue;
				}
				else{
					X[xCount][yCount] = Integer.parseInt(line.split(",")[0].trim());
					Y[xCount][yCount] = Integer.parseInt(line.split(",")[1].trim());
					yCount++;
					if(yCount==4){
						xCount++;
						yCount=0;
					}
				}
			}
			bufferedReader.close();
			fileReader.close();
		}
		catch(Exception e){
			System.out.println("Exception while reading file.");
			e.printStackTrace();
		}

		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				tileCoordinates[i][j] = new Position(X[i][j], Y[i][j], 4);
			}
		}
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

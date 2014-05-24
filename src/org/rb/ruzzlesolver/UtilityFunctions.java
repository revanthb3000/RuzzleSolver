package org.rb.ruzzlesolver;

import java.util.ArrayList;

import org.rb.ruzzlesolver.ds.Position;

/**
 * This contains frequent functions that will be used by the main program.
 * @author RB
 *
 */
public class UtilityFunctions {

	/**
	 * Simple function used to perform a deep copy of an arraylist
	 * @param positions
	 * @return
	 */
	public static ArrayList<Position> getNewList(ArrayList<Position> positions) {
		ArrayList<Position> newPositions = new ArrayList<Position>();
		for (Position position : positions) {
			newPositions.add(position);
		}
		return newPositions;
	}
}

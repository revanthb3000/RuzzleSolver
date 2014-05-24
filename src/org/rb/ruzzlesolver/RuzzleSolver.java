package org.rb.ruzzlesolver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.rb.ruzzlesolver.ds.Dictionary;
import org.rb.ruzzlesolver.ds.Position;
import org.rb.ruzzleswiper.RuzzleSwiper;

public class RuzzleSolver {

	/**
	 * This is how we represent our table. A 4x4 array.
	 */
	private static char[][] table = new char[4][4];

	/**
	 * For the sake of generalization
	 */
	private static final int BOARD_SIZE = 4;

	/**
	 * This dictionary is basically a List of all words contained in the Ruzzle word space.
	 */
	private static Dictionary dictionary = new Dictionary();

	/**
	 * All valid words for a puzzle are stored here.
	 */
	private static ArrayList<String> validWords = new ArrayList<String>();

	/**
	 * This is a mapping from a word to the required tiles to be pressed.
	 */
	private static Map<String, ArrayList<Position>> wordTilesMapping = new HashMap<String, ArrayList<Position>>();

	
	/**
	 * The main function of sorts. Takes in an input, forms the table, solves the puzzle and prints the results in ascending order of length.
	 * @param inputString
	 */
	public static void startSolving(String inputString) {
		getTableFromString(inputString);
		solvePuzzle();

		System.out.println("Number of words : " + validWords.size());
		ArrayList<Position> tiles = null;

		CustomStringComparator myComparator = new CustomStringComparator("");
		Collections.sort(validWords, myComparator);

		RuzzleSwiper ruzzleSwiper = new RuzzleSwiper(30);
		for (String word : validWords) {
			System.out.println(word);
			tiles = wordTilesMapping.get(word);
			ruzzleSwiper.clickPositions(tiles);
			for (Position position : tiles) {
				System.out.print("(" + position.getX() + "," + position.getY() + ")->");
			}
			System.out.println("END\n");
		}
	}

	/**
	 * Question - is of the following format: 'abcd efgh hijk lmno' Where the 4
	 * strings are 4 rows !
	 * 
	 * @param question
	 */
	private static void getTableFromString(String question) {
		String[] rows = question.toUpperCase().split(" ");
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				table[i][j] = rows[i].charAt(j);
			}
		}
	}

	/**
	 * This function is basically used to initiate the DFS we will perform. Goes to each position and starts a DFS from it.
	 */
	private static void solvePuzzle() {
		ArrayList<Position> coveredPositions = new ArrayList<Position>();

		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				Position position = new Position(i, j, BOARD_SIZE);
				solve("" + table[i][j], coveredPositions, position);
			}
		}
	}

	/**
	 * A recursive function that performs DFS and checks to see if a word is valid or not. Stops when you're out of tiles.
	 * 
	 * TODO Performance Enhancement : Build a suffix tree and check if the node with the suffix arg has any children. That will help skip loads of paths.
	 * 
	 * @param suffix
	 * @param coveredPositions
	 * @param currentPosition
	 */
	private static void solve(String suffix, ArrayList<Position> coveredPositions, Position currentPosition) {
		ArrayList<Position> newCoveredPositions = UtilityFunctions.getNewList(coveredPositions);
		newCoveredPositions.add(currentPosition);
		
		ArrayList<Position> adjacentPositions = currentPosition.getAdjacentPositions();
		for (Position adjacentPosition : adjacentPositions) {
			Boolean isCovered = false;
			for (Position position : newCoveredPositions) {
				if ((position.getX() == adjacentPosition.getX()) && (position.getY() == adjacentPosition.getY())) {
					isCovered = true;
					break;
				}
			}

			if (isCovered)
				continue;
			
			String word = suffix + table[adjacentPosition.getX()][adjacentPosition.getY()];

			if (dictionary.isWordValid(word)) {
				if (!validWords.contains(word)) {
					validWords.add(word);

					ArrayList<Position> tilePositions = UtilityFunctions.getNewList(newCoveredPositions);
					tilePositions.add(adjacentPosition);

					wordTilesMapping.put(word, tilePositions);
				}
			}
			solve(word, newCoveredPositions, adjacentPosition);
		}
	}

}

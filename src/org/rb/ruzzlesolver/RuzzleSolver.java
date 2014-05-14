package org.rb.ruzzlesolver;

import java.awt.TextArea;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JTextArea;

import org.rb.ruzzlesolver.ds.Dictionary;
import org.rb.ruzzlesolver.ds.Position;

public class RuzzleSolver {
	
	private static char[][] table = new char[4][4];
	
	private static final int BOARD_SIZE = 4;
	
	private static Dictionary dictionary = new Dictionary();
	
	private static ArrayList<String> validWords = new ArrayList<String>();

	public static void startSolving(String inputString){
		getTableFromString(inputString);
		solvePuzzle();
		System.out.println(validWords.size());
		CustomStringComparator myComparator = new CustomStringComparator("");
		Collections.sort(validWords, myComparator);
		for(String word: validWords){
			System.out.println(word);
		}
	}
	
	/**
	 * Question - is of the following format:
	 * 'abcd efgh hijk lmno'
	 * Where the 4 strings are 4 rows !
	 * @param question
	 */
	private static void getTableFromString(String question){
		String[] rows = question.toUpperCase().split(" ");
		for(int i=0;i<BOARD_SIZE;i++){
			for(int j=0;j<BOARD_SIZE;j++){
				table[i][j] = rows[i].charAt(j);
			}
		}
	}
	
	private static void solvePuzzle(){
		ArrayList<Position> coveredPositions = new ArrayList<Position>();
		
		for(int i=0;i<BOARD_SIZE;i++){
			for(int j=0;j<BOARD_SIZE;j++){
				Position position = new Position(i, j, BOARD_SIZE);
				solve("" + table[i][j], coveredPositions, position);
			}
		}
	}
	
	private static void solve(String suffix, ArrayList<Position> coveredPositions, Position currentPosition){
		ArrayList<Position> newCoveredPositions = getNewList(coveredPositions);
		newCoveredPositions.add(currentPosition);
		ArrayList<Position> adjacentPositions = currentPosition.getAdjacentPositions();
		for(Position adjacentPosition : adjacentPositions){
			Boolean isCovered = false;
			for(Position position : newCoveredPositions){
				if((position.getX()==adjacentPosition.getX())&&(position.getY()==adjacentPosition.getY())){
					isCovered = true;
					break;
				}
			}

			if(isCovered){
				continue;
			}
			String word = suffix + table[adjacentPosition.getX()][adjacentPosition.getY()];
			
			if(dictionary.isWordValid(word)){
				if(!validWords.contains(word)){
					validWords.add(word);
				}
				//System.out.println(word);
			}
			solve(word, newCoveredPositions, adjacentPosition);
		}
	}
	
	private static ArrayList<Position> getNewList(ArrayList<Position> positions){
		ArrayList<Position> newPositions = new ArrayList<Position>();
		for(Position position : positions){
			newPositions.add(position);
		}
		return newPositions;
	}
}

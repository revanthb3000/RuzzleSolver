package org.rb.ruzzlesolver.ds;

import java.util.ArrayList;


public class Position {
	
	private int x;
	
	private int y;
	
	private int boardSize;
	
	public Position(int x, int y, int boardSize) {
		super();
		this.x = x;
		this.y = y;
		this.boardSize = boardSize;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public int getBoardSize() {
		return boardSize;
	}

	public void setBoardSize(int boardSize) {
		this.boardSize = boardSize;
	}

	public ArrayList<Position> getAdjacentPositions(){
		ArrayList<Position> adjPositions = new ArrayList<Position>();

		if((x+1)<boardSize)
		adjPositions.add(new Position(x+1, y, boardSize));
		
		if(((x+1)<boardSize) && ((y+1)<boardSize))
		adjPositions.add(new Position(x+1, y+1, boardSize));

		if((y+1)<boardSize)
		adjPositions.add(new Position(x, y+1, boardSize));

		if((x-1)>=0)
		adjPositions.add(new Position(x-1, y, boardSize));

		if(((x-1)>=0) && ((y-1)>=0))
		adjPositions.add(new Position(x-1, y-1, boardSize));

		if((y-1)>=0)
		adjPositions.add(new Position(x, y-1, boardSize));

		if(((x+1)<boardSize) && ((y-1)>=0))
		adjPositions.add(new Position(x+1, y-1, boardSize));

		if(((x-1)>=0) && ((y+1)<boardSize))
		adjPositions.add(new Position(x-1, y+1, boardSize));

		return adjPositions;
	}

	@Override
	public String toString() {
		return "Position [x=" + x + ", y=" + y + ", boardSize=" + boardSize
				+ "]";
	}

}

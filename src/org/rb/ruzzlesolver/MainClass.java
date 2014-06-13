package org.rb.ruzzlesolver;

public class MainClass {

	public static void main(String[] args){
		int roundNumber = 3;

		int wordLimit = 0;
		if(roundNumber==1){
			wordLimit = 80;
		}
		else if(roundNumber==2){
			wordLimit = 70;
		}
		else{
			wordLimit = 1000;
		}
		RuzzleSolver.startSolving("issm eatt nile ceve", wordLimit);
	}
	
}

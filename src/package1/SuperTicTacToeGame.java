package package1;

import java.util.Stack;
/***********************************************************************
 * Super Tic Tac Toe Game class.  This class controls all the back end 
 * for the tic tac toe board.  This class does the actual setting of
 * the status of each cell of the board and determines the status of
 * the game and winners.
 * 
 * @author Robert Molenhouse
 * @version 1.0
 *
 **********************************************************************/

public class SuperTicTacToeGame {
	/** two dim array of Cell's to represent the board*/
	private Cell[][] board;
	
	/** current enum status of the game*/
	private GameStatus status;
	
	/** Which player has the current turn*/
	private Cell turn;
	
	/** Player that starts each game*/
	private Cell startTurn;
	
	/** number of rows and columns that make up the board*/
	private int boardSize = 3;
	
	/** number of connections needed to win*/
	private int winSize = 3;
	
	/** keeps track of previous moves row*/
	private Stack<Integer> lastRow;
	
	/** keeps track of previous moves column*/
	private Stack<Integer> lastCol;

	/*******************************************************************
	 * Constructor for the tic tac toe game.  sets the board size, win
	 * connections size, and which player should go first.  Also, 
	 * creates the board.
	 * 
	 * @param boardSize, number of rows and columns in the board.
	 * @param winSize, number of connections needed to win.
	 * @param turn, which player should start the game.
	 ******************************************************************/
	public SuperTicTacToeGame(int boardSize,int winSize ,Cell turn) {
		this.boardSize = boardSize;
		board = new Cell[boardSize][boardSize];
		this.turn = turn;
		startTurn = turn;
		this.winSize = winSize;
		lastRow = new Stack<Integer>();
		lastCol = new Stack<Integer>();
	}
	/*******************************************************************
	 * Getter method for the board.
	 * 
	 * @return a two dim array of Cells which shows the board.
	 ******************************************************************/
	public Cell[][] getBoard() {
		return board;
	}
	
	/*******************************************************************
	 * takes the selection a player makes and places the proper marker
	 * in that tile.
	 * 
	 * @param row, which row was selected.
	 * @param col, which column was selected.
	 ******************************************************************/
	public void select(int row, int col) {
		if ((board[row][col] != Cell.X) && (board[row][col] != Cell.O)){
			if (turn == Cell.X){
				board[row][col] = Cell.X;
				lastRow.push(row);
				lastCol.push(col);
			}
			else{
				board[row][col] = Cell.O;
				lastRow.push(row);
				lastCol.push(col);
			}

			if (turn == Cell.X)
				turn = Cell.O;
			else
				turn = Cell.X;
		}
	}
	
	/*******************************************************************
	 * Removes the last selection from the board one at a time all the '
	 * way to the beginning of the game.
	 ******************************************************************/
	public void undo(){
		//check if the board is empty, if not, remove the last move.
		if(!lastRow.empty() && !lastCol.empty()){
		int row = lastRow.peek();
		int col = lastCol.peek();
		board[row][col] = Cell.EMPTY;
		lastRow.pop();
		lastCol.pop();
		
		//set the turn back to the previous players.
		if(turn == Cell.X)
			turn = Cell.O;
		else 
			turn = Cell.X;
		}
	}
	
	/*******************************************************************
	 * sets the board back to empty and sets the turn back to the
	 * chosen starter.
	 ******************************************************************/
	public void reset(){
		for(int r = 0; r < boardSize; r++){
			for(int c = 0; c < boardSize; c++){
				board[r][c] = Cell.EMPTY;
				turn = startTurn;
			}
		}
	}

	/*******************************************************************
	 * Checks for winners and cats and returns the current Game Status.
	 * 
	 * @return enum for the current status of the game.
	 ******************************************************************/
	public GameStatus getGameStatus(){
		
		//Check for horizontal X winners, wrapping wins count.
		for(int r = 0; r < boardSize; r++){
			for(int c = 0; c < boardSize; c++){
				
				//check for winSize of 3
				if(winSize == 3 && board[r][c] == Cell.X && 
					board[r][(c+1) % boardSize] == Cell.X && 
					board[r][(c+2) % boardSize] == Cell.X)
					return GameStatus.X_WON;
				
				//Check for winSize of 4
				if(winSize == 4 &&  board[r][c] == Cell.X && 
					board[r][(c+1) % boardSize] == Cell.X && 
					board[r][(c+2) % boardSize] == Cell.X && 
					board[r][(c+3) % boardSize] == Cell.X)
						return GameStatus.X_WON;
				
				//Check for winSize of 5
				if(winSize == 5 && board[r][c] == Cell.X && 
					board[r][(c+1) % boardSize] == Cell.X && 
					board[r][(c+2) % boardSize] == Cell.X && 
					board[r][(c+3) % boardSize] == Cell.X &&
					board[r][(c+4) % boardSize] == Cell.X)
						return GameStatus.X_WON;
				
				//Check for winSize of 6
				if(winSize == 6 && board[r][c] == Cell.X && 
					board[r][(c+1) % boardSize] == Cell.X && 
					board[r][(c+2) % boardSize] == Cell.X && 
					board[r][(c+3) % boardSize] == Cell.X && 
					board[r][(c+4) % boardSize] == Cell.X && 
					board[r][(c+5) % boardSize] == Cell.X)
						return GameStatus.X_WON;
				
				//Check for winSize of 7
				if(winSize == 7 && board[r][c] == Cell.X && 
					board[r][(c+1) % boardSize] == Cell.X &&
					board[r][(c+2) % boardSize] == Cell.X && 
					board[r][(c+3) % boardSize] == Cell.X && 
					board[r][(c+4) % boardSize] == Cell.X && 
					board[r][(c+5) % boardSize] == Cell.X && 
					board[r][(c+6) % boardSize] == Cell.X)
						return GameStatus.X_WON;
				
				//Check for horizontal O winners, wrapping counts.
				//check for winSize of 3
				if(winSize == 3 && board[r][c] == Cell.O && 
					board[r][(c+1) % boardSize] == Cell.O && 
					board[r][(c+2) % boardSize] == Cell.O)
					return GameStatus.O_WON;
				
				//Check for winSize of 4
				if(winSize == 4 &&  board[r][c] == Cell.O && 
					board[r][(c+1) % boardSize] == Cell.O && 
					board[r][(c+2) % boardSize] == Cell.O && 
					board[r][(c+3) % boardSize] == Cell.O)
						return GameStatus.O_WON;
				
				//Check for winSize of 5
				if(winSize == 5 && board[r][c] == Cell.O && 
					board[r][(c+1) % boardSize] == Cell.O && 
					board[r][(c+2) % boardSize] == Cell.O && 
					board[r][(c+3) % boardSize] == Cell.O &&
					board[r][(c+4) % boardSize] == Cell.O)
						return GameStatus.O_WON;
				
				//Check for winSize of 6
				if(winSize == 6 && board[r][c] == Cell.O && 
					board[r][(c+1) % boardSize] == Cell.O && 
					board[r][(c+2) % boardSize] == Cell.O && 
					board[r][(c+3) % boardSize] == Cell.O && 
					board[r][(c+4) % boardSize] == Cell.O && 
					board[r][(c+5) % boardSize] == Cell.O)
						return GameStatus.O_WON;
				
				//Check for winSize of 7
				if(winSize == 7 && board[r][c] == Cell.O && 
					board[r][(c+1) % boardSize] == Cell.O &&
					board[r][(c+2) % boardSize] == Cell.O && 
					board[r][(c+3) % boardSize] == Cell.O && 
					board[r][(c+4) % boardSize] == Cell.O && 
					board[r][(c+5) % boardSize] == Cell.O && 
					board[r][(c+6) % boardSize] == Cell.O)
						return GameStatus.O_WON;

				}
			
		}
			
		//Check for vertical X winner. Wrappers count.
		for(int r = 0; r < boardSize; r++){
			for(int c = 0; c < boardSize; c++){
				
				//check for winSize of 3
				if(winSize == 3 && board[r][c] == Cell.X && 
					board[(r+1) % boardSize][c] == Cell.X && 
					board[(r+2) % boardSize][c] == Cell.X)
					return GameStatus.X_WON;
				
				//Check for winSize of 4
				if(winSize == 4 &&  board[r][c] == Cell.X && 
					board[(r+1) % boardSize][c] == Cell.X &&
					board[(r+2) % boardSize][c] == Cell.X && 
					board[(r+3) % boardSize][c] == Cell.X)
						return GameStatus.X_WON;
				
				//Check for winSize of 5
				if(winSize == 5 && board[r][c] == Cell.X && 
					board[(r+1) % boardSize][c] == Cell.X && 
					board[(r+2) % boardSize][c] == Cell.X && 
					board[(r+3) % boardSize][c] == Cell.X && 
					board[(r+4) % boardSize][c] == Cell.X)
						return GameStatus.X_WON;
				
				//Check for winSize of 6
				if(winSize == 6 && board[r][c] == Cell.X && 
					board[(r+1) % boardSize][c] == Cell.X && 
					board[(r+2) % boardSize][c] == Cell.X && 
					board[(r+3) % boardSize][c] == Cell.X && 
					board[(r+4) % boardSize][c] == Cell.X && 
					board[(r+5) % boardSize][c] == Cell.X)
						return GameStatus.X_WON;
				
				//Check for winSize of 7
				if(winSize == 7 && board[r][c] == Cell.X && 
					board[(r+1) % boardSize][c] == Cell.X && 
					board[(r+2) % boardSize][c] == Cell.X && 
					board[(r+3) % boardSize][c] == Cell.X && 
					board[(r+4) % boardSize][c] == Cell.X && 
					board[(r+5) % boardSize][c] == Cell.X && 
					board[(r+6) % boardSize][c] == Cell.X)
						return GameStatus.X_WON;
				
				//Check for vertical O win. Wrappers counts.
				//Check for winSize of 3
				if(winSize == 3 && board[r][c] == Cell.O && 
					board[(r+1) % boardSize][c] == Cell.O && 
					board[(r+2) % boardSize][c] == Cell.O)
						return GameStatus.O_WON;
				
				//Check for winSize of 4
				if(winSize == 4 &&  board[r][c] == Cell.O && 
					board[(r+1) % boardSize][c] == Cell.O &&
					board[(r+2) % boardSize][c] == Cell.O && 
					board[(r+3) % boardSize][c] == Cell.O)
						return GameStatus.O_WON;
				
				//Check for winSize of 5
				if(winSize == 5 && board[r][c] == Cell.O && 
					board[(r+1) % boardSize][c] == Cell.O && 
					board[(r+2) % boardSize][c] == Cell.O && 
					board[(r+3) % boardSize][c] == Cell.O && 
					board[(r+4) % boardSize][c] == Cell.O)
						return GameStatus.O_WON;
				
				//Check for winSize of 6
				if(winSize == 6 && board[r][c] == Cell.O && 
					board[(r+1) % boardSize][c] == Cell.O && 
					board[(r+2) % boardSize][c] == Cell.O && 
					board[(r+3) % boardSize][c] == Cell.O && 
					board[(r+4) % boardSize][c] == Cell.O && 
					board[(r+5) % boardSize][c] == Cell.O)
						return GameStatus.O_WON;
				
				//Check for winSize of 7
				if(winSize == 7 && board[r][c] == Cell.O && 
					board[(r+1) % boardSize][c] == Cell.O && 
					board[(r+2) % boardSize][c] == Cell.O && 
					board[(r+3) % boardSize][c] == Cell.O && 
					board[(r+4) % boardSize][c] == Cell.O && 
					board[(r+5) % boardSize][c] == Cell.O && 
					board[(r+6) % boardSize][c] == Cell.O)
						return GameStatus.O_WON;
				}
		}
		
		try{
		for(int r = 0; r < boardSize-2; r++){
			for(int c = 0; c < boardSize-2; c++){
				
				//check for winSize of 3
				if(winSize == 3 && board[r][c] == Cell.X && 
					board[r+1][c+1] == Cell.X &&
			    	board[r+2][c+2] == Cell.X)
					return GameStatus.X_WON;
				
				//Check for winSize of 4
				if(winSize == 4 &&  board[r][c] == Cell.X && 
					board[r+1][c+1] == Cell.X && 
		        	board[r+2][c+2] == Cell.X &&
		        	board[r+3][c+3] == Cell.X)
						return GameStatus.X_WON;
				
				//Check for winSize of 5
				if(winSize == 5 && board[r][c] == Cell.X && 
					board[r+1][c+1] == Cell.X &&
					board[r+2][c+2] == Cell.X &&
			    	board[r+3][c+3] == Cell.X && 
			    	board[r+4][c+4] == Cell.X)
						return GameStatus.X_WON;
				
				//Check for winSize of 6
				if(winSize == 6 && board[r][c] == Cell.X && 
					board[r+1][c+1] == Cell.X && 
					board[r+2][c+2] == Cell.X &&
					board[r+3][c+3] == Cell.X && 
					board[r+4][c+4] == Cell.X &&
			    	board[r+5][c+5] == Cell.X)
						return GameStatus.X_WON;
				
				//Check for winSize of 7
				if(winSize == 7 && board[r][c] == Cell.X && 
					board[r+1][c+1] == Cell.X && 
					board[r+2][c+2] == Cell.X &&
					board[r+3][c+3] == Cell.X && 
			    	board[r+4][c+4] == Cell.X &&
			    	board[r+5][c+5] == Cell.X && 
			    	board[r+6][c+6] == Cell.X)
						return GameStatus.X_WON;
				
				//check for winSize of 3
				if(winSize == 3 && board[r][c] == Cell.O && 
					board[r+1][c+1] == Cell.O &&
			    	board[r+2][c+2] == Cell.O)
					return GameStatus.O_WON;
				
				//Check for winSize of 4
				if(winSize == 4 &&  board[r][c] == Cell.O && 
					board[r+1][c+1] == Cell.O && 
		        	board[r+2][c+2] == Cell.O &&
		        	board[r+3][c+3] == Cell.O)
						return GameStatus.O_WON;
				
				//Check for winSize of 5
				if(winSize == 5 && board[r][c] == Cell.O && 
					board[r+1][c+1] == Cell.O &&
					board[r+2][c+2] == Cell.O &&
					board[r+3][c+3] == Cell.O && 
			    	board[r+4][c+4] == Cell.O)
						return GameStatus.O_WON;
				
				//Check for winSize of 6
				if(winSize == 6 && board[r][c] == Cell.O && 
					board[r+1][c+1] == Cell.O && 
					board[r+2][c+2] == Cell.O &&
					board[r+3][c+3] == Cell.O && 
			    	board[r+4][c+4] == Cell.O &&
			    	board[r+5][c+5] == Cell.O)
						return GameStatus.O_WON;
				
				//Check for winSize of 7
				if(winSize == 7 && board[r][c] == Cell.O && 
					board[r+1][c+1] == Cell.O && 
					board[r+2][c+2] == Cell.O &&
					board[r+3][c+3] == Cell.O && 
					board[r+4][c+4] == Cell.O &&
					board[r+5][c+5] == Cell.O && 
					board[r+6][c+6] == Cell.O)
						return GameStatus.O_WON;
			}
		}
		
		
		//check for SW to NE diagonal winner.
		for(int r = (winSize - 1); r < boardSize; r++){
			for(int c = 0; c < boardSize-2; c++){
				
				//check for winSize of 3
				if(winSize == 3 && board[r][c] == Cell.X && 
					board[r-1][c+1] == Cell.X &&
			    	board[r-2][c+2] == Cell.X)
					return GameStatus.X_WON;
				
				//Check for winSize of 4
				if(winSize == 4 &&  board[r][c] == Cell.X && 
					board[r-1][c+1] == Cell.X && 
		        	board[r-2][c+2] == Cell.X &&
		        	board[r-3][c+3] == Cell.X)
						return GameStatus.X_WON;
				
				//Check for winSize of 5
				if(winSize == 5 && board[r][c] == Cell.X && 
					board[r-1][c+1] == Cell.X &&
					board[r-2][c+2] == Cell.X &&
			    	board[r-3][c+3] == Cell.X && 
			    	board[r-4][c+4] == Cell.X)
						return GameStatus.X_WON;
				
				//Check for winSize of 6
				if(winSize == 6 && board[r][c] == Cell.X && 
					board[r-1][c+1] == Cell.X && 
					board[r-2][c+2] == Cell.X &&
					board[r-3][c+3] == Cell.X && 
					board[r-4][c+4] == Cell.X &&
			    	board[r-5][c+5] == Cell.X)
						return GameStatus.X_WON;
				
				//Check for winSize of 7
				if(winSize == 7 && board[r][c] == Cell.X && 
					board[r-1][c+1] == Cell.X && 
					board[r-2][c+2] == Cell.X &&
					board[r-3][c+3] == Cell.X && 
			    	board[r-4][c+4] == Cell.X &&
			    	board[r-5][c+5] == Cell.X && 
			    	board[r-6][c+6] == Cell.X)
						return GameStatus.X_WON;
				
				//check for winSize of 3
				if(winSize == 3 && board[r][c] == Cell.O && 
					board[r-1][c+1] == Cell.O &&
			    	board[r-2][c+2] == Cell.O)
					return GameStatus.O_WON;
				
				//Check for winSize of 4
				if(winSize == 4 &&  board[r][c] == Cell.O && 
					board[r-1][c+1] == Cell.O && 
		        	board[r-2][c+2] == Cell.O &&
		        	board[r-3][c+3] == Cell.O)
						return GameStatus.O_WON;
				
				//Check for winSize of 5
				if(winSize == 5 && board[r][c] == Cell.O && 
					board[r-1][c+1] == Cell.O &&
					board[r-2][c+2] == Cell.O &&
					board[r-3][c+3] == Cell.O && 
			    	board[r-4][c+4] == Cell.O)
						return GameStatus.O_WON;
				
				//Check for winSize of 6
				if(winSize == 6 && board[r][c] == Cell.O && 
					board[r-1][c+1] == Cell.O && 
					board[r-2][c+2] == Cell.O &&
					board[r-3][c+3] == Cell.O && 
			    	board[r-4][c+4] == Cell.O &&
			    	board[r-5][c+5] == Cell.O)
						return GameStatus.O_WON;
				
				//Check for winSize of 7
				if(winSize == 7 && board[r][c] == Cell.O && 
					board[r-1][c+1] == Cell.O && 
					board[r-2][c+2] == Cell.O &&
					board[r-3][c+3] == Cell.O && 
					board[r-4][c+4] == Cell.O &&
					board[r-5][c+5] == Cell.O && 
					board[r-6][c+6] == Cell.O)
						return GameStatus.O_WON;
			}}}
				
				catch (ArrayIndexOutOfBoundsException e){
					
				}
		
		
		//check for cats.
		if(isCats())
			return GameStatus.CATS;
		else
			return GameStatus.IN_PROGRESS;
	}

	/*******************************************************************
	 * Check to see if the game is cats by checking if all the spaces 
	 * are full if a winner is not found.
	 * 
	 * @return true if the game is cats, false if not.
	 ******************************************************************/
	private Boolean isCats() {
		int cats = 0;
		for (int r = 0; r < boardSize; r++)
			for (int c = 0; c < boardSize; c++)
				if (board[r][c] == Cell.X || board[r][c] == Cell.O)
					cats++;

		if (cats == (boardSize * boardSize))
			return true;
		else
			return false;
	}
}

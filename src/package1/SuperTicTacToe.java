package package1;

import javax.swing.*;
import java.awt.*;

/***********************************************************************
 * This is a game of tic tac toe that allows the user to specify the
 * board size and the number of connections needed to win.  Board sizes
 * range from 3x3 to 9x9 and winning connections from 3 to 7.
 * 
 * @author Robert Molenhouse
 * @version 1.0
 *
 **********************************************************************/

public class SuperTicTacToe {
	/** The Frame for the main GUI */
	private static JFrame frame;
	
	/** The number of rows and columns on the board */
	private static int boardSize = 3;
	
	/** Number of connections needed to win */
	private static int winSize = 3;
	
	/** The player that goes first */
	private static Cell firstPlayer;
	
	/** The board and all its components */
	private static SuperTicTacToePanel board;
	
	/** ContentPane*/
	private static Container contentPane;
	
	/** Number of times the board has been reset per session*/
	private static int reset = 0;

	/*******************************************************************
	 * creates the front end GUI and runs the program.  Prompts users
	 * for game settings and then creates it.
	 * @param args
	 ******************************************************************/
	public static void main(String[] args) {
		//create the frame
		frame = new JFrame();
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Super Tic Tac Toe");
		
		//get content pane.
		contentPane = frame.getContentPane();
		
		//display board size prompt
		promptBoardSize();
		
		//prompt for first player
		promptFirstPlayer();
		
		//add the selected board.
		board = new SuperTicTacToePanel(boardSize,winSize,firstPlayer,
													0,0);
		
		contentPane.add(board);
		
		//set size and visible
		frame.setSize(720, 580);
		frame.setVisible(true);
	}

	/*******************************************************************
	 * Creates a JOptionPane to ask the user which size game board and 
	 * how many connections are needed to win.  If the user selects a 
	 * size of winning connections that is too big for the board they 
	 * selected, give a warning message and close the program.
	 ******************************************************************/
	private static void promptBoardSize() {
		// prompt for the user to select a board size.
		Object[] sizes = { "3x3", "4x4", "5x5", "6x6", "7x7", 
														 "8x8", "9x9" };
		Object[] wins = {3,4,5,6,7};
		
		JPanel panel = new JPanel();
		JComboBox sizeChoice = new JComboBox(sizes);
		JComboBox winChoice = new JComboBox(wins);

		panel.add(new JLabel("Please choose a board size."));
		panel.add(sizeChoice);
		panel.add(new JLabel("How many connections to win?"));
		panel.add(winChoice);

		int result = JOptionPane.showConfirmDialog(null, panel, 
					 "Set Up Game", JOptionPane.OK_CANCEL_OPTION);

		Object selectedSize = sizeChoice.getSelectedItem();
		Object selectedWin = winChoice.getSelectedItem();

		if (result == JOptionPane.OK_OPTION) {

			//read boardSize choice.
			if (selectedSize.equals("3x3")) {
				boardSize = 3;
			} else if (selectedSize.equals("4x4")) {
				boardSize = 4;
			} else if (selectedSize.equals("5x5")) {
				boardSize = 5;
			} else if (selectedSize.equals("6x6")) {
				boardSize = 6;
			} else if (selectedSize.equals("7x7")) {
				boardSize = 7;
			} else if (selectedSize.equals("8x8")) {
				boardSize = 8;
			} else if (selectedSize.equals("9x9")) {
				boardSize = 9;
			}	
			
			//read winSize Choice.
			if((int) selectedWin <= boardSize)
				winSize = (int) selectedWin;
			else{
				if(reset == 0){
				JOptionPane.showMessageDialog(null, 
				     "Number of Connections needed to win cannot"
				      + " exceed the board size.");
				System.exit(0);
				}
				else{
				JOptionPane.showMessageDialog(null,
					"Number of Connections needed to win cannot"
						  + " exceed the board size.");
				reset();
				}
			}
		}
		if (result ==JOptionPane.CANCEL_OPTION){
			if(reset == 0)
			System.exit(0);
		}
	}
	
	/*******************************************************************
	 * Creates a JOptionPane to ask the user which player goes first.
	 ******************************************************************/
	private static void promptFirstPlayer() {
		
		
		// prompt for the user to select the first player.
		Object[] players = { "X", "O"};
		JPanel panel = new JPanel();
		JComboBox sizeChoice = new JComboBox(players);

		panel.add(new JLabel("Please choose who goes first"));
		panel.add(sizeChoice);

		int result = JOptionPane.showConfirmDialog(null, panel, 
				   "Select first player",
				          JOptionPane.OK_CANCEL_OPTION);

		Object selectedPlayer = sizeChoice.getSelectedItem();

		if (result == JOptionPane.OK_OPTION) {

			if (selectedPlayer.equals("O")) {
				 firstPlayer = Cell.O;
			} else if (selectedPlayer.equals("X")) {
				 firstPlayer = Cell.X;
			}
		}
		if (result ==JOptionPane.CANCEL_OPTION){
			if(reset == 0)
			System.exit(0);
		}
	}
	
	/*******************************************************************
	 * Allows the user after the initial creation of the board to resize
	 * the board, the connections needed to win, and who goes first.
	 ******************************************************************/
	public static void reset(){
		reset++;
		promptBoardSize();
		promptFirstPlayer();
		contentPane.remove(board);
		board = new SuperTicTacToePanel(boardSize,winSize, firstPlayer,
	 SuperTicTacToePanel.getxWinInt(),SuperTicTacToePanel.getoWinInt());
		contentPane.add(board);
		contentPane.revalidate();
		contentPane.repaint();
	}
}

package package1;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

/***********************************************************************
 * Front end GUI for the super tic tac toe game.  It creates a board of
 * buttons which can be interacted with to place a X or O on the board.
 * It keeps track of each players wins and displays it.  Also provides 
 * various menu buttons with can be used for changing the board, undoing
 * moves and quitting.
 * 
 * @author Robert Molenhouse
 * @version 1.0
 *
 **********************************************************************/
public class SuperTicTacToePanel extends JPanel{
	/**Two dim array of buttons to create the board. */
	private JButton[][] board;
	
	/**Two dim array of cell's tells the board what is in each button */
	private Cell[][] iCell;
	
	/**Button to quit the game. */
	private JButton quitButton;
	
	/**Button to undo the previous move. */
	private JButton undoButton;
	
	/**Button to all user to change game settings. */
	private JButton resetButton;
	
	/**The game the board is displaying */
	private SuperTicTacToeGame game;
	
	/**ActionListener for all the buttons */
	private ButtonListener listener;
	
	/**X symbol. */
	private ImageIcon xIcon;
	
	/**O symbol */
	private ImageIcon oIcon;
	
	/**Empty space symbol. */
	private ImageIcon emptyIcon;
	
	/**Size of the board. */
	private int boardSize;
	
	/**Displays number of X wins. */
	private JLabel xWins;
	
	/**Displays number of O wins */
	private JLabel oWins;
	
	/**Number of times x has won. */
	private static int xWinInt = 0;
	
	/**Number of times o has won. */
	private static int oWinInt = 0;
	
	/**Number of connected needed to win. */
	private int winSize;
	
	/**JPanel which displays the actual board. */
	private JPanel boardPanel;
	
	/**JPanel where all the menu buttons and win counter is located. */
	private JPanel menuPanel;
	
	/*******************************************************************
	 * Constructor for the panel.  It creates two different panels, one 
	 * for the board and one for all the other buttons and displays.
	 * 
	 * @param boardSize, number of rows and columns.
	 * @param winSize, number of connections needed to win.
	 * @param cell, starting player to pass onto the game.
	 * @param xWin, number of previous x wins.
	 * @param oWin, number of previous o wins.
	 ******************************************************************/
	public SuperTicTacToePanel(int boardSize, int winSize, Cell cell, 
							int xWin, int oWin){
		
		this.boardSize = boardSize;
		this.winSize = winSize;
		oWinInt = oWin;
		xWinInt = xWin;
		
		setLayout(new BorderLayout());
		
		//create two panels, one for the  board, and one for the menu.
		boardPanel = new JPanel();
		menuPanel = new JPanel();
		
		boardPanel.setLayout(new GridLayout(boardSize,boardSize));
		
		
		//Create action listener
		listener = new ButtonListener();
		
		//Create the game
		game = new SuperTicTacToeGame(boardSize, winSize, cell);
		
		//Create the icons
		xIcon = new ImageIcon ("x.jpg");
		oIcon = new ImageIcon ("o.jpg");
		emptyIcon = new ImageIcon ("empty.jpg");
		
		//create the board
		board = new JButton[boardSize][boardSize];
		
		//instantiate menu panel items.
		quitButton = new JButton("Quit");
		quitButton.addActionListener(listener);
		undoButton = new JButton("Undo");
		undoButton.addActionListener(listener);
		resetButton = new JButton("Reset");
		resetButton.addActionListener(listener);
		menuPanel.add(quitButton);
		menuPanel.add(undoButton);
		menuPanel.add(resetButton);
		menuPanel.add(xWins = new JLabel());
		menuPanel.add(oWins = new JLabel());
		
		
		redisplayWins();
		
		makeBoard();
		
		//add all to the content pane.
		add(boardPanel, BorderLayout.CENTER);
		add(menuPanel, BorderLayout.SOUTH);

	}
	
	/*******************************************************************
	 * Creates the main board with JButtons.
	 */
	private void makeBoard(){
		// populate the board with buttons.
		for (int row = 0; row < boardSize; row++)
			for (int col = 0; col < boardSize; col++) {
				board[row][col] = new JButton("", emptyIcon);
				board[row][col].addActionListener(listener);
				board[row][col].setBackground(Color.WHITE);
				board[row][col].setBorder
								 (new LineBorder(Color.lightGray, 2));
				boardPanel.add(board[row][col]);
			}
	}
	
	/*******************************************************************
	 * Assigns image icons to the board button based on what moves 
	 * have been make.
	 ******************************************************************/
	private void displayBoard() {
		//get the board from the game.	
		iCell = game.getBoard();
				
		//set the icon for each cell
		for(int row = 0; row < boardSize; row++)
			for(int col = 0; col < boardSize; col++) {
				if(iCell[row][col] == Cell.O)
					board[row][col].setIcon(oIcon);
						
				if(iCell[row][col] == Cell.X)
					board[row][col].setIcon(xIcon);
						
				if(iCell[row][col] == Cell.EMPTY)
					board[row][col].setIcon(emptyIcon);
					}
	}
	
	/*******************************************************************
	 * Dynamically resizes the board based on user inpupt.
	 ******************************************************************/
	private void boardReset(){
		SuperTicTacToe.reset();
	}
	
	/*******************************************************************
	 * Return number of x wins.
	 * @return int of x wins.
	 ******************************************************************/
	public static int getxWinInt() {
		return xWinInt;
	}

	/*******************************************************************
	 * Return number of o wins.
	 * @return int of o wins.
	 ******************************************************************/
	public static int getoWinInt() {
		return oWinInt;
	}

	/*******************************************************************
	 * Changes the JLabels for number of previous wins.
	 ******************************************************************/
	private void redisplayWins(){
		xWins.setText("X Wins: " + xWinInt);
		oWins.setText("O Wins: " + oWinInt);
	}
	
	/*******************************************************************
	 * Private class the is the action listener for all the buttons
	 * in the game.
	 ******************************************************************/
	private class ButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			//check if undo button was called.
			if(undoButton == e.getSource()){
				game.undo();
				displayBoard();
			}
			//check if reset button was called.
			if(resetButton == e.getSource()){
				boardReset();
			}
			//check if wuit button was called.
			if (quitButton == e.getSource()){
			//Make option pane to confirm quit.
			int confirm = JOptionPane.showConfirmDialog 
			(null, "Are you sure you want to quit?", "Quit",
					JOptionPane.YES_NO_OPTION);
			//system exit if quit is confirmed.
			if(confirm == JOptionPane.YES_OPTION){
				System.exit(0);
			}
			}
			
			//iterate through board buttons.
			for (int r = 0; r < boardSize; r++) 
				for (int c = 0; c < boardSize; c++) 
					//Locate button that was called.
					if (board[r][c] == e.getSource()) 
						game.select(r,c);
						displayBoard();	
			
		//After the last button action, have the game check the status
		//and return if there was a winner or game is cats.
					
			//Check for X win.
			if (game.getGameStatus() == GameStatus.X_WON) {
				JOptionPane.showMessageDialog(null, "X Won!");
				game.reset();
				displayBoard();
				xWinInt ++;
				redisplayWins();
			}
			
			//Check for O wins.
			if (game.getGameStatus() == GameStatus.O_WON) {
				JOptionPane.showMessageDialog(null, "O Won!");
				game.reset();
				displayBoard();
				oWinInt ++;
				redisplayWins();
			}
			
			//Check for cats.
			if (game.getGameStatus() == GameStatus.CATS) {
				JOptionPane.showMessageDialog(null, "CATS!");
				game.reset();
				displayBoard();
				redisplayWins();
			}
			
		}
		
	}
}
			

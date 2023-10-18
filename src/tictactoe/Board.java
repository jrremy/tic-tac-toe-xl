package tictactoe;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * Represents the GUI for the Tic-Tac-Toe game. It includes a game board
 * consisting of Tile objects.
 * 
 * @author Jeremy D.
 */
public class Board implements ActionListener {
   private static JFrame boardFrame;
   private JButton pauseButton;
   private Tile[][] tiles;
   private int rows;
   private int cols;
   
   // Which playe's turn it is ("X" or "O").
   private String turn;
   
   // The icons to be displayed on the win/loss/tie dialog.
   private ImageIcon thumbsUp;
   private ImageIcon thumbsDown;
   private ImageIcon xWin;
   private ImageIcon oWin;
   
   /**
    * Creates a new Board object with a specified number of rows and columns.
    * 
    * @param selectedRows Number of rows of tiles for the board.
    * @param selectedCols Number of columns of tiles for the board.
    */
   public Board(int selectedRows, int selectedCols) {
      turn = "X";
      rows = selectedRows;
      cols = selectedCols;
      boardFrame = new JFrame("New Game");
      boardFrame.setLayout(null);
      boardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
      // Placing all the tiles on the board.
      tiles = new Tile[rows][cols];
      int x = 30;
      int y = 30;
      int frameWidth = x;
      int frameHeight = y;
      for(int r = 0; r < rows; r++) {
         for(int c = 0; c < cols; c++) {
            tiles[r][c] = new Tile();
            tiles[r][c].getButton().setLocation(x, y);
            boardFrame.add(tiles[r][c].getButton());
            tiles[r][c].getButton().addActionListener(this);
            if(r == rows - 1 && c == cols - 1) {
               // The size of the boardFrame is determined by the position of the bottom right tile
               frameWidth = x + 105;
               frameHeight = y + 130;
            }
            x = x + 70;
         }
         x = 30;
         y = y + 70;
      }        
      
      // The width and height of the frame depends on the amount of rows and columns.
      boardFrame.setSize(frameWidth, frameHeight);
      
      // Creates the pause button at the top left corner.
      pauseButton = new JButton("Pause");
      pauseButton.setBounds(5, 5, 70, 20);
      pauseButton.setFocusPainted(false);
      pauseButton.addActionListener(this);
      boardFrame.add(pauseButton);
        
      boardFrame.setVisible(true);
   }
   
   /**
    * Gets the JFrame of the board.
    * 
    * @return The boardFrame field.
    */
   public static JFrame getFrame() {
      return boardFrame;
   }
   
   /**
    * Performs an opponent move for the singleplayer mode.
    * The computer opponent places an O on a random tile that has not been used yet.
    */
   private void opponentPlay() {
      boolean hasPlayed = false;
      Random rand = new Random();
      while(!hasPlayed) {
         int randX = rand.nextInt(cols);
         int randY = rand.nextInt(rows);
         if(!tiles[randY][randX].getUsed()) {
            hasPlayed = true;
            tiles[randY][randX].getButton().setText("O");
            tiles[randY][randX].setUsed(true);
            if(check("O", randY, randX).equals("win")) {
               winScreen("O", 0);
            }
            else if(check("O", randY, randX).equals("tie")) {
               winScreen("T", 0);
            }
         }
      }
   }
   
   /**
    * Checks for a win for a given letter X or O at a given position on the board.
    * 
    * @param letter The player being checked for a win (X or O).
    * @param r The row position of the turn being checked.
    * @param c The column position of the turn being checked.
    * @return "win" if the turn is a win, "tie" if the turn is the last on the board and isn't a win, "none" otherwise.
    */
   private String check(String letter, int r, int c) {
      // Centered vertical check.
      if(r > 0 && r < rows - 1) {
         if(tiles[r][c].getText().equals(letter) && tiles[r - 1][c].getText().equals(letter) && tiles[r + 1][c].getText().equals(letter)) {
            return "win";
         }
      }
      
      // Centered horizontal check.
      if(c > 0 && c < cols - 1) {
         if(tiles[r][c].getText().equals(letter) && tiles[r][c - 1].getText().equals(letter) && tiles[r][c + 1].getText().equals(letter)) {
            return "win";
         }
      }
      
      // Centered down right diagonal check.
      if((r > 0 && r < rows - 1) && (c > 0 && c < cols - 1)) {
         if(tiles[r][c].getText().equals(letter) && tiles[r - 1][c - 1].getText().equals(letter) && tiles[r + 1][c + 1].getText().equals(letter)) {
            return "win";
         }
      }
      
      // Centered up right diagonal check.
      if((r > 0 && r < rows - 1) && (c > 0 && c < cols - 1)) {
         if(tiles[r][c].getText().equals(letter) && tiles[r + 1][c - 1].getText().equals(letter) && tiles[r - 1][c + 1].getText().equals(letter)) {
            return "win";
         }
      }
      
      // Forward-shifted vertical check.
      if(r < rows - 2) {
         if(tiles[r][c].getText().equals(letter) && tiles[r + 1][c].getText().equals(letter) && tiles[r + 2][c].getText().equals(letter)) {
            return "win";
         }
      }
      
      // Forward-shifted horizontal check.
      if(c < cols - 2) {
         if(tiles[r][c].getText().equals(letter) && tiles[r][c + 1].getText().equals(letter) && tiles[r][c + 2].getText().equals(letter)) {
            return "win";
         }
      }
      
      // Forward-shifted down right diagonal check.
      if(r < rows - 2 && c < cols - 2) {
         if(tiles[r][c].getText().equals(letter) && tiles[r + 1][c + 1].getText().equals(letter) && tiles[r + 2][c + 2].getText().equals(letter)) {
            return "win";
         }
      }
      
      // Forward-shifted up right diagonal check.
      if(r > 1 && c < cols - 2) {
         if(tiles[r][c].getText().equals(letter) && tiles[r - 1][c + 1].getText().equals(letter) && tiles[r - 2][c + 2].getText().equals(letter)) {
            return "win";
         }
      }
      
      // Backward-shifted vertical check.
      if(r > 1) {
         if(tiles[r][c].getText().equals(letter) && tiles[r - 1][c].getText().equals(letter) && tiles[r - 2][c].getText().equals(letter)) {
            return "win";
         }
      }
      
      // Backward-shifted horizontal check.
      if(c > 1) {
         if(tiles[r][c].getText().equals(letter) && tiles[r][c - 1].getText().equals(letter) && tiles[r][c - 2].getText().equals(letter)) {
            return "win";
         }
      }
      
      // Backward-shifted down right diagonal check.
      if(r > 1 && c > 1) {
         if(tiles[r][c].getText().equals(letter) && tiles[r - 1][c - 1].getText().equals(letter) && tiles[r - 2][c - 2].getText().equals(letter)) {
            return "win";
         }
      }
      
      // Backward-shifted up right diagonal check.
      if(r < rows - 2 && c > 1) {
         if(tiles[r][c].getText().equals(letter) && tiles[r + 1][c - 1].getText().equals(letter) && tiles[r + 2][c - 2].getText().equals(letter)) {
            return "win";
         }
      }
      
      // Tie check. At this point, the player's turn is not a win.
      // If all tiles are used, meaning this turn used the last available tile, then it's a tie.
      boolean allPlayed = true;
      for(int ir = 0; ir < rows; ir++) {
         for(int ic = 0; ic < cols; ic++) {
            if(!tiles[ir][ic].getUsed()) {
               allPlayed = false;
            }
         }
      }
      if(allPlayed) {
         return "tie";
      }
      
      // The turn was neither a win or a tie.
      return "none";
   }
   
   /**
    * Edits the board to show the result of the game and notifies via a JOptionPane.
    * 
    * @param letter The result of the game; "X" for player X win, "O" for player O win, "T" for tie.
    */
   private void winScreen(String letter, int mode) {
      // As a design choice, disable all buttons from being clicked.
      for(int r = 0; r < rows; r++) {
         for(int c = 0; c < cols; c++) {
            tiles[r][c].getButton().setEnabled(false);
         }
      }
      pauseButton.setEnabled(false);
      
      // If singleplayer win or X win in multiplayer, display win with green board.
      if(letter.equals("X")) {
         boardFrame.getContentPane().setBackground(Color.GREEN);
         String[] buttons = {"Quit", "Play again"};
         thumbsUp = new ImageIcon(Board.class.getResource("/resources/icons/thumbsup.png"));
         xWin = new ImageIcon(Board.class.getResource("/resources/icons/x-win.png"));
         int winMsg;
         if(mode == 0) {
            winMsg = JOptionPane.showOptionDialog(null, "You win!", "You win!", JOptionPane.PLAIN_MESSAGE, 1, thumbsUp, buttons, buttons[1]);
         }
         else {
            winMsg = JOptionPane.showOptionDialog(null, "Player X Wins!", "Player X Wins!", JOptionPane.PLAIN_MESSAGE, 1, xWin, buttons, buttons[1]);
         }
         if(winMsg == 0) {
            System.exit(0);
         }
         else if(winMsg == 1) {
            boardFrame.dispose();
            new StartMenu();
         }
      }
      
      // If singleplayer loss or player O win in multiplayer, display win with red board.
      else if(letter.equals("O")) {
         boardFrame.getContentPane().setBackground(Color.RED);
         String[] buttons = {"Quit", "Play again"};
         thumbsDown = new ImageIcon(Board.class.getResource("/resources/icons/thumbsdown.png"));
         oWin = new ImageIcon(Board.class.getResource("/resources/icons/o-win.png"));
         int winMsg;
         if(mode == 0) {
            winMsg = JOptionPane.showOptionDialog(null, "You lose.", "You lose.", JOptionPane.PLAIN_MESSAGE, 1, thumbsDown, buttons, buttons[1]);
         }
         else {
            winMsg = JOptionPane.showOptionDialog(null, "Player O Wins!", "Player O Wins!", JOptionPane.PLAIN_MESSAGE, 1, oWin, buttons, buttons[1]);
         }
         if(winMsg == 0) {
            System.exit(0);
         }
         else if(winMsg == 1) {
            boardFrame.dispose();
            new StartMenu();
         }
      }
      
      // If neither player won, display tie with gray board.
      else if(letter.equals("T")){
         boardFrame.getContentPane().setBackground(Color.GRAY);
         String[] buttons = {"Quit", "Play again"};
         int winMsg = JOptionPane.showOptionDialog(null, "It's a tie.", "You lose.", JOptionPane.PLAIN_MESSAGE, 1, null, buttons, buttons[1]);
         if(winMsg == 0) {
            System.exit(0);
         }
         else if(winMsg == 1) {
            boardFrame.dispose();
            new StartMenu();
         }
      }
   }
   
   /**
    * {@inheritDoc}
    */
   @Override
   public void actionPerformed(ActionEvent e) {
      // Searches whole board to see which tile button was clicked.
      for(int r = 0; r < rows; r++) {
         for(int c = 0; c < cols; c++) {
            // If a tile button is clicked and hasn't been played yet, register the turn.
            if((e.getSource() == tiles[r][c].getButton()) && !tiles[r][c].getUsed()) {
               // Puts X or O on tile button depending on who played it.
               // Tile button's text is set to the turn field because of the alternating turns in multiplayer.
               // In singleplayer and by default, turn is always "X".
               tiles[r][c].getButton().setText(turn);
               tiles[r][c].setUsed(true);               
               
               // If the mode is singleplayer, a win or tie is displayed if the turn is a win or tie, or a random opponent turn is performed.
               if(StartMenu.getMode() == 0) {
                  if(check("X", r, c).equals("win")) {
                     winScreen("X", 0);
                  }
                  else if(check("X", r, c).equals("tie")) {
                     winScreen("T", 0);
                  }
                  else if(check("X", r, c).equals("none")) {
                     opponentPlay();
                  }
               } 
               
               // Otherwise, if the mode is multiplayer, a win or tie is displayed if the turn is a win or tie, or it becomes the other player's turn.
               else {
                  if(check(turn, r, c).equals("win")) {
                     winScreen(turn, 1);
                  }
                  else if(check(turn, r, c).equals("tie")) {
                     winScreen("T", 1);
                  }
                  if(turn.equals("X")) {
                     turn = "O";
                  }
                  else {
                     turn = "X";
                  }
               }
                    
            }
         }
      }
      
      // If the pause button is clicked, open the pause menu.
      if(e.getSource() == pauseButton) {
         new PauseMenu();
      }
   }
}

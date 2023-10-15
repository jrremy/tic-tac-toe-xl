package tictactoe;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

/** 
 * Represents the start menu where the user selects their preferred board size and game mode.
 * 
 * @author Jeremy D.
 */
public class StartMenu implements ActionListener {
   private JFrame startFrame;
   private JPanel startPanel;
   private static JSpinner rowsField;
   private static JSpinner colsField;
   private static JComboBox modeField;
   private JButton startButton;
   
   /**
    * Constructs a new StartMenu object with default values.
    */
   public StartMenu() {
      startFrame = new JFrame("Tic Tac Toe XL");
      startPanel = new JPanel();
      startFrame.setLayout(null);
      startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      startFrame.setSize(400, 350);
      startFrame.setResizable(false);
      startFrame.add(startPanel);
      
      Font titleFont = new Font("SimSun", Font.BOLD, 30);
      JLabel title = new JLabel("Tic Tac Toe XL");
      title.setFont(titleFont);
      title.setBounds(75, 30, 250, 30);
      startFrame.add(title);
      
      Font labelFont = new Font("SimSun", Font.BOLD, 20);
      
      // Where the user chooses the number of rows for the board
      SpinnerModel rowsValue = new SpinnerNumberModel(3, 3, 100, 1);
      rowsField = new JSpinner(rowsValue);
      rowsField.setBounds(90, 90, 100, 30);
      JLabel rowsLabel = new JLabel("Rows:");
      rowsLabel.setFont(labelFont);
      rowsLabel.setBounds(30, 90, 200, 30);
      startFrame.add(rowsLabel);
      startFrame.add(rowsField);
      
      // Where the user chooses the number of rows for the board
      SpinnerModel colsValue = new SpinnerNumberModel(3, 3, 100, 1);
      colsField = new JSpinner(colsValue);
      colsField.setBounds(90, 130, 100, 30);
      JLabel colsLabel = new JLabel("Cols:");
      colsLabel.setFont(labelFont);
      colsLabel.setBounds(30, 130, 200, 30);
      startFrame.add(colsLabel);
      startFrame.add(colsField);
        
      // Where the user chooses the mode for the game
      String[] modes = {"Singleplayer", "Multiplayer"};
      modeField = new JComboBox(modes);
      modeField.setSelectedIndex(0);
      JLabel modeLabel = new JLabel("Mode:");
      modeLabel.setFont(labelFont);
      modeLabel.setBounds(30, 170, 200, 30);
      modeField.setBounds(90, 170, 100, 30);
      startFrame.add(modeLabel);
      startFrame.add(modeField);
      
      startButton = new JButton("Start");
      startButton.setBounds(160, 220, 80, 30);
      startButton.addActionListener(this);
      startFrame.add(startButton);
      
      startFrame.setVisible(true);
   }
   
   /**
    * Gets the number of rows selected in the JSpinner for setting the game board's row count.
    * @return The number of rows selected for the game board.
    */
   public static int getRows() {
      //return Integer.parseInt(rowsField.getText());
      return (Integer) rowsField.getValue();
   }
   
   /**
    * Gets the number of columns selected in the JSpinner for setting the game board's row count.
    * @return The number of columns selected for the game board.
    */
   public static int getCols() {
      return (Integer) colsField.getValue();
   }
   
   /**
    * Gets the index of the selected element of the JComboBox for setting the mode of the game.
    * @return 0 for singleplayer mode, 1 for multiplayer mode.
    */
   public static int getMode() {
      return modeField.getSelectedIndex();
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public void actionPerformed(ActionEvent e) {
      // If the start button is clicked, get rid of the start menu and create/open a new board passing in the values entered in rowsField and colsField.
      if(e.getSource() == startButton) {
         startFrame.dispose();
         new Board(getRows(), getCols());
      }        
   }
   
   /**
    * The main method. The start menu is created here.
    * 
    * @param args The command-line arguments.
    */
   public static void main(String[] args) {
      new StartMenu();
   }
}

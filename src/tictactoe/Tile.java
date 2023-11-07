package tictactoe;

import java.awt.Font;
import javax.swing.JButton;

/**
 * Represents a tile.
 * 
 * @author Jeremy D.
 */
public class Tile {
   private JButton tileButton;

   // The availability of the tile.
   private boolean used;

   /**
    * Constructs a new Tile object with default values.
    */
   public Tile() {
      tileButton = new JButton("");
      tileButton.setSize(60, 60);
      tileButton.setFocusPainted(false);

      Font tileFont = new Font("Arial", Font.BOLD, 30);
      tileButton.setFont(tileFont);

      used = false;
   }

   /**
    * Gets the JButton of the tile.
    * 
    * @return The tileButton field.
    */
   public JButton getButton() {
      return tileButton;
   }

   /**
    * Gets the text in the JButton of the tile.
    * 
    * @return The text in tileButton.
    */
   public String getText() {
      return tileButton.getText();
   }

   /**
    * Gets the availability of the tile.
    * 
    * @return The value of the used field.
    */
   public boolean getUsed() {
      return used;
   }

   /**
    * Sets the availability of the tile.
    * 
    * @param input The value to assign to the used field.
    */
   public void setUsed(boolean input) {
      used = input;
   }
}

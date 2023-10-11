package tictactoe;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Represents the pause menu.
 * 
 * @auther Jeremy D.
 */
public class PauseMenu implements ActionListener {
   private JFrame pauseFrame;
   private JPanel pausePanel;
   private JButton resumeButton;
   private JButton restartButton;
   private JButton mainMenuButton;
   private JButton quitButton;
   
   /**
    * Constructs a new PauseMenu object with default values.
    */
   public PauseMenu() {
      pauseFrame = new JFrame();
      pausePanel = new JPanel(new FlowLayout());
      pauseFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      pauseFrame.setSize(100, 200);
      pauseFrame.setLocation(100,100);
      pauseFrame.setResizable(false);
      pauseFrame.add(pausePanel);
            
      Font labelFont = new Font("SimSun", Font.PLAIN, 20);
      JLabel pauseMenuLabel = new JLabel("Paused");
      pauseMenuLabel.setFont(labelFont);
      pausePanel.add(pauseMenuLabel);
      
      resumeButton = new JButton("Resume");
      resumeButton.addActionListener(this);
      pausePanel.add(resumeButton);
      
      restartButton = new JButton("Restart");
      restartButton.addActionListener(this);
      pausePanel.add(restartButton);
      
      mainMenuButton = new JButton("Main Menu");
      mainMenuButton.addActionListener(this);
      pausePanel.add(mainMenuButton);
      
      quitButton = new JButton("Quit");
      quitButton.addActionListener(this);
      pausePanel.add(quitButton);
      
      pauseFrame.setVisible(true);
   }
   
   /**
    * {@inheritDoc}
    */
   @Override
   public void actionPerformed(ActionEvent e) {
      if(e.getSource() == resumeButton) {
         pauseFrame.dispose();
      }
      if(e.getSource() == restartButton) {
         Board.getFrame().dispose();
         pauseFrame.dispose();
         new Board();
      }
      if(e.getSource() == mainMenuButton) {
         Board.getFrame().dispose();
         pauseFrame.dispose();
         new StartMenu();
      }
      if(e.getSource() == quitButton) {
         System.exit(0);
      }
   }
}

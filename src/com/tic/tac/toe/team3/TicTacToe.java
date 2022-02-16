package com.tic.tac.toe.team3;

import java.awt.*;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

public class TicTacToe extends JPanel {
    private String playerMark = "Gryffindor";
    private Icon gryffindorIcon = new ImageIcon("Resources/gryffindorIcon.PNG");
    private Icon slytherinIcon = new ImageIcon("Resources/slytherinIcon.PNG");
    private JButton[] buttons = new JButton[9];
    BackgroundMusic bg = new BackgroundMusic();
    Window window = new Window();

    //Ctor Set to init buttons and board with
    // 3 rows and 3 column at start
    public TicTacToe() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        setLayout(new GridLayout(3, 3));//This is a method from the gridlayout class to set rows and columnsSEast West
        initializeButtons();
        bg.audioFile();


    }

    // A Jbutton array method used to create 9 buttons
    // set the text, add action listeners
    // and add them to the screen
    public void initializeButtons() {

        for (int i = 0; i <= 8; i++) {
            buttons[i] = new JButton();
            buttons[i].setText("-");
            buttons[i].setBackground(Color.BLACK);// part of the color class java.awt
            buttons[i].addActionListener(e -> {
                JButton buttonClicked = (JButton) e.getSource(); //Save the action of particular button that was clicked
                buttonClicked.setText(String.valueOf(playerMark)); // Sets the Player Mark
                if (playerMark.equals("Gryffindor")) {//
                    playerMark = "Slytherin";
                    buttonClicked.setIcon(gryffindorIcon);
                } else {
                    playerMark = "Gryffindor";
                    buttonClicked.setIcon(slytherinIcon);
                }
                displayWinner();


            });

            add(buttons[i]); //adds this button to JPanel

        }
    }

    // Display the Winner
    public void displayWinner() {
        Window window = new Window();
        if(checkForWinner()) {
            if(playerMark == "Gryffindor"){                     // reverse the player marks because after we put Gryffindor and we win,
                playerMark = "Slytherin";                       //the game changes it to Slytherin, but Gryffindor is the winner
                window.display(false);
            }
            else {
                playerMark ="Gryffindor";
                window.display(true);
            }

            JOptionPane jPane = new JOptionPane();              //The J action Pane that popup to ask if we want to play again
            int dialogResult = JOptionPane.showConfirmDialog(jPane, "Game Over. "+ playerMark + " Wins. Would you like to play again?","Game over.",
                    JOptionPane.YES_NO_OPTION);

            if(dialogResult == JOptionPane.YES_OPTION) resetTheButtons();
            else System.exit(0);
        }

        else if(checkDraw()) {
            JOptionPane pane = new JOptionPane();
            int dialogResult = JOptionPane.showConfirmDialog(pane, "Draw. Lets Play again?","Game over.", JOptionPane.YES_NO_OPTION);
            if(dialogResult == JOptionPane.YES_OPTION)  resetTheButtons();
            else System.exit(0);
        }
    }

    // method used to reset the buttons, to set the board up and play again
    private void resetTheButtons() {
        playerMark = "Gryffindor";
        for (int i = 0; i < 9; i++) {

            buttons[i].setText("-");
            buttons[i].setIcon(null);
            buttons[i].setBackground(Color.BLACK);

        }
    }

    // Checks for draw
    public boolean checkDraw() {
        boolean full = true;
        for (int i = 0; i < 9; i++) {
            if (buttons[i].getText().charAt(0) == '-') {// getText()to convert text to char
                full = false;
            }
        }
        return full;
    }

    // checks for a winner by calling the checkRows(), checkColumns(), and checkDiagonals()
    public boolean checkForWinner() {
        if (checkRows() || checkColumns() || checkDiagonals()) return true;
        else return false;
    }

    // check all valid ways[3] to win by row
    //if 0-1-2, 3-4-5, or 6-7-8 are equal return true
    public boolean checkRows() {
        int i = 0;
        for (int j = 0; j < 3; j++) {
            if (buttons[i].getText().equals(buttons[i + 1].getText()) && buttons[i].getText().equals(buttons[i + 2].getText())
                    && buttons[i].getText().charAt(0) != '-') {             //The key to make the game work, makes sure the spaces are not empty!
                return true;
            }
            i = i + 3;

        }
        return false;
    }

    // checks all valid ways[3] you can win through columns
    //if 0-3-6, 1-4-7, or 2-5-8 are equal then return true
    public boolean checkColumns() {
        int i = 0;
        for (int j = 0; j < 3; j++) {
            if (buttons[i].getText().equals(buttons[i + 3].getText()) && buttons[i].getText().equals(buttons[i + 6].getText())
                    && buttons[i].getText().charAt(0) != '-') {
                return true;
            }
            i++;
        }
        return false;
    }

    // checks all valid ways[2] you can win through diagonals
    //only 2 options so we are able to hard code (0-4-8 & 2-4-6)
    public boolean checkDiagonals() {
        if (buttons[0].getText().equals(buttons[4].getText()) && buttons[0].getText().equals(buttons[8].getText())
                && buttons[0].getText().charAt(0) != '-')
            return true;
        else if (buttons[2].getText().equals(buttons[4].getText()) && buttons[2].getText().equals(buttons[6].getText())
                && buttons[2].getText().charAt(0) != '-')
            return true;
        else
            return false;
    }
}
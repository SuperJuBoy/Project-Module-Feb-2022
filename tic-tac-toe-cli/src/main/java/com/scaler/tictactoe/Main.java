package com.scaler.tictactoe;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {

        Game game = new Game("X", "O");
        Player winner;
        int numOfTurns = 0;

        do{
            System.out.println("Please enter input for :" + game.getNextTurn().getCharacter());
            String input = game.readInput();
            while(!game.isInputValid(input))
                input = game.readInput();

            game.nextAttempt(Integer.parseInt(input));
            game.printGameState();
            numOfTurns++;
        }while((winner = game.checkVictory(game.getGameState())) == null && (numOfTurns < 9));

        if(winner == null){
            System.out.println("Its a tie!!");
        }else{
            System.out.println("Winner is " + (winner.getCharacter() == "X"? "O":"X"));
        }

        /*
        TODO: Create the entire game; steps are:
            1. Construct game object with 2 player characters
            2. For every turn, print whose turn it is, and state of game (3x3 box)
            3. Read input (between 1-9) as the box to be marked by next player
            4. Validate input and mark the box
                4.1 If input invalid, make player enter box no again
            5. Repeat steps 2-4 until either;
                5.1  checkVictory function shows any player has won
                5.2  all boxes have been marked
         */
    }
}

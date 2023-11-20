package com.scaler.tictactoe;


import lombok.Getter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Game {
    @Getter
    private Player p1;
    @Getter
    private Player p2;

    @Getter
    private Player nextTurn;

    @Getter
    private String[][] gameState = new String[3][3];

    public Game(String p1Char, String p2Char) {
        p1 = new Player(p1Char);
        p2 = new Player(p2Char);

        // init next turn for player 1
        nextTurn = p1;
    }

    public String getCharInBox(int box) {
        int row = (box - 1) / 3;
        int col = (box - 1) % 3;

        if (box < 1 || box > 9) throw new IllegalArgumentException("Box no. must be between 1 and 9");
        else return gameState[row][col];
    }

    public boolean isInputValid(String input){
        int boxNo = 0;
        try{
            boxNo = Integer.parseInt(input);
        }catch(NumberFormatException e){
            System.out.println("Enter a number");
            return false;
        }

        if((boxNo >= 1 && boxNo <= 9) && (getCharInBox(boxNo) == null)){
            return true;
        }else{
            if(boxNo < 1 || boxNo > 9)
                System.out.println("Box no. must be between 1 and 9, please provide another input");
            else
                System.out.println("Box is already occupied, please provide another input");

            return false;
        }

    }

    /**
     * This method is to play the next turn
     *
     * @param box the box in which to be marked
     */
    public void nextAttempt(int box) {
        int row = (box - 1) / 3;
        int col = (box - 1) % 3;

        if (box < 1 || box > 9) throw new IllegalArgumentException("Box no. must be between 1 and 9");
        if (gameState[row][col] != null) throw new IllegalStateException("This box is not empty");

        gameState[row][col] = nextTurn.getCharacter();

        // switch turn of players
        if (nextTurn == p1) nextTurn = p2;
        else nextTurn = p1;
    }

    public String readInput(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            return br.readLine();
        } catch (IOException e) {
            throw new RuntimeException("Unable to read input");
        }
    }
    /**
     * Checks board state and tells if any winners
     *
     * @return p1 or p2 whoever has won; or null if no winner yet
     */
    public Player checkVictory(String[][] gameState) {
        Player winner;
        if((winner = checkColumn(gameState)) != null){
            System.out.println("Winner by col!");
            return winner;
        }else if((winner = checkRow(gameState)) != null){
            System.out.println("Winner by row!");
            return winner;
        }else if((winner = checkDiagonal(gameState)) != null){
            System.out.println("Winner by diagonal!");
            return winner;
        }else{
            return null;
        }
    }

    public Player checkColumn(String[][] gameState){
        for(int i=0; i<3; i++){
            if((gameState[0][i] != null) && (gameState[0][i].equals(gameState[1][i])) && (gameState[0][i].equals(gameState[2][i]))){
                return nextTurn;
            }
        }
        return null;
    }

    public Player checkRow(String[][] gameState){
        for(int i=0; i<3; i++){
            if((gameState[i][0] != null) && (gameState[i][0].equals(gameState[i][1])) && (gameState[i][0].equals(gameState[i][2]))){
                return nextTurn;
            }
        }
        return null;
    }

    public Player checkDiagonal(String[][] gameState){
        if((gameState[0][0] != null) && (gameState[0][0].equals(gameState[1][1])) && (gameState[0][0].equals(gameState[2][2]))){
            return nextTurn;
        }else if((gameState[2][0] != null) && (gameState[2][0].equals(gameState[1][1])) && (gameState[2][0].equals(gameState[0][2]))){
            return nextTurn;
        }else{
            return null;
        }
    }
    public void printGameState() {
        System.out.println(
        "  " + gameState[0][0] + " | " + gameState[0][1] + " | " + gameState[0][2] + "\n" +
                "------------\n" +
                "  " + gameState[1][0] + " | " + gameState[1][1] + " | " + gameState[1][2] + "\n" +
                "------------\n" +
                "  " + gameState[2][0] + " | " + gameState[2][1] + " | " + gameState[2][2] + "\n");
    }
}

package game;
import GUI.GameGUI;

import java.util.*;

public class Game {

    /** the upper player */
    public Player player0;

    /** the player locates downward*/
    public Player player1;

    /** the board for this game*/
    public Board playBoard;

    /** the number of rank on the board */
    public int rank = 8;

    /** the number of file on the board */
    public int file = 8;

    /** whether game is end */
    public boolean isEnd;

    public int endState;

    GameGUI gameGUI;

    public Game() {
        gameGUI = new GameGUI(this);
        playBoard = new Board(this);

        // combine GUI and logic settings
        playBoard.addCustomPiece = gameGUI.chessBoard.setCustomPiece;
        playBoard.presentTurn = gameGUI.chessBoard.presentPlayer;
        playBoard.boardGUI = gameGUI.chessBoard;

        playBoard.setPieces();
        isEnd = false;
    }

    /**
     * judge whether the game should end
     * checkmate or stalemate happens
     * @param moving player for this turn
     * @param standing player who is waiting for next turn
     */
    public boolean judge(Player moving, Player standing) {
        playBoard.isCheckmate(moving, standing);
        if (player0.isLose || player1.isLose) {
            isEnd = true;
        } else if (playBoard.isStalemate(moving, standing)) {
            isEnd = true;
        }
        return isEnd;
    }

    /**
     * game start interface
     * @return whether game is end
     */
    public boolean start() {
        System.out.println("Game start!");

        while (!isEnd) {

        }

        if (player1.isLose) {
            System.out.println("player0 win!!");
        } else if (player0.isLose) {
            System.out.println("player1 win!!");
        } else {
            System.out.println("Draw!!");
        }
        return isEnd;
    }


    public static void main(String args[]) {
        Game game = new Game();
        //game.start();
    }
}

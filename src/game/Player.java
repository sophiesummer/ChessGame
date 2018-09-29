package game;
import pieces.*;
import java.util.*;

/**
 * Player class which contains player information.
 */
public class Player {

    /** the color of pieces */
    public int color;

    /** a list of pieces the player owned */
    public List<Pieces> pieces;

    /** whether the player is in check */
    public boolean isChecked;

    /** whether the player is lose*/
    public boolean isLose;

    public int score;

    public String name;

    /**
     * initialize player attributes
     * @param color the color of player's pieces, 0 is black
     */
    public Player(int color, String name) {
        this.color = color;
        this.name = name;
        isChecked = false;
        isLose = false;
        score = 0;
    }


    /**
     * assign pieces on the board to the player
     * @param pieces a list of pieces given to the player
     */
    public void getPieces(List<Pieces> pieces) {
        this.pieces = pieces;
    }

    /**
     * check whether this player has this piece
     * @param p the piece being checked
     * @return whether the piece exists in player's pieces list
     */
    public boolean hasThePiece(Pieces p) {
        return pieces.contains(p);
    }

}

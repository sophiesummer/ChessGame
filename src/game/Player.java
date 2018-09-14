package game;
import pieces.*;
import java.util.*;

public class Player {

    public int color;
    public List<Pieces> pieces;
    public boolean isChecked;
    public Board playBoard;
    public boolean isLose;

    public Player(int color, Board playBoard) {
        this.color = color;
        isChecked = false;
        this.playBoard = playBoard;
        isLose = false;
    }

    // assign pieces on the board to the player
    public void getPieces(List<Pieces> pieces) {
        this.pieces = pieces;
    }

    public boolean hasThePiece(Pieces p) {
        return pieces.contains(p);
    }

}

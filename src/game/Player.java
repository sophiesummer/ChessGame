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

    public void getPieces(List<Pieces> pieces) {
        this.pieces = pieces;
    }


    public boolean hasThePiece(Pieces p) {
        return pieces.contains(p);
    }

}

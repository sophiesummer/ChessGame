package game;
import pieces.*;
import java.util.*;

public class Player {

    public int color;
    public List<Pieces> pieces;
    public boolean isChecked;

    public Player(int color) {
        this.color = color;
        isChecked = false;
    }

    public void getPieces(List<Pieces> pieces) {
        this.pieces = pieces;
    }

    public boolean play(Pieces p, int prevX, int prevY, int newX, int newY) {
        if (isChecked || !hasThePiece(p)) {
            return false;
        }
        return true;
    }

    public boolean hasThePiece(Pieces p) {
        return pieces.contains(p);
    }

}

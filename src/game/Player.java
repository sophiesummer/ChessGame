package game;
import pieces.*;

import java.util.*;

public class Player {

    public int color;
    public List<Pieces> pieces;

    public Player(int color) {
        this.color = color;
    }

    public void getPieces(List<Pieces> pieces) {
        this.pieces = pieces;
    }

    public boolean play(Type p, int prevX, int prevY, int newX, int newY) {
        return false;

    }

    public boolean hasThePiece(Pieces p) {
        return pieces.contains(p);
    }

}

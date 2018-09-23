package pieces;

import game.*;

import java.util.List;

public abstract class Pieces {
    /** rank position of the piece */
    protected int x;

    /** file position of the piece */
    protected int y;

    /** the player piece belongs to */
    public Player player;

    /** the type the piece should be */
    public Type type;

    public Pieces(int x, int y, Player player) {
        this.x = x;
        this.y = y;
        this.player = player;
    }


    public int[] getPosition() {
        return new int[]{x, y};
    }

    public Type getType(){
        return type;
    }

    public Player getPlayer() {
        return this.player;
    }

    /**
     * check pieces follow their own rules
     * @param newX destination rank of the piece
     * @param newY destination file of the piece
     * @return whether this step is a valid move
     */
    public abstract boolean isValidMove(int newX, int newY);


    /**
     * store the passing positions
     * a helper function for later check leap over other pieces
     * @param newX destination rank of the piece
     * @param newY destination file of the piece
     * @return a list of squares that the piece passing
     */
    public abstract List<int[]> moving(int newX, int newY);


    /**
     * update the piece position
     * @param newX destination rank of the piece
     * @param newY destination file of the piece
     */
    public void move(int newX, int newY) {
        x = newX;
        y = newY;
    }

    /**
     * set piece's rank position
     * @param x new rank position
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * set piece's file position
     * @param y new file position
     */
    public void setY(int y) {
        this.y = y;
    }

}

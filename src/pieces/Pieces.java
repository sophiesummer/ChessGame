package pieces;

import game.*;

import java.util.List;

public interface Pieces {

    int[] getPosition();

    Player getPlayer();

    boolean isValidMove(int newX, int newY);

    List<int[]> moving(int newX, int newY);

    void move(int newX, int newY);

}

package pieces;

import game.*;

import java.util.List;

public interface Pieces {

    int[] getPosition();

    Type getType();

    Player getPlayer();

    // check pieces follow their own rules
    boolean isValidMove(int newX, int newY);

    // store the leaping over positions, for later check
    List<int[]> moving(int newX, int newY);

    void move(int newX, int newY);

    void setX(int x);

    void setY(int y);

}

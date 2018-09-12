package game;
import java.util.*;

public class Game {

    public Player player0;
    public Player player1;

    public Board board;


    public Game() {

        player0 = new Player(0);
        player1 = new Player(1);

        board = new Board(player0, player1);


    }

    public void start() {

    }
}

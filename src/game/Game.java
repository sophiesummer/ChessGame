package game;
import java.util.*;

public class Game {

    public Player player0;
    public Player player1;

    public Board board;


    public Game(int rank, int file) {

        player0 = new Player(0);
        player1 = new Player(1);

        board = new Board(rank, file, player0, player1);


    }

    public void start() {

    }


    public void over() {

    }



    public static void main(String args[]) {
        Game game = new Game(8, 8);
        game.start();
    }
}

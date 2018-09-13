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
        System.out.println("Game start!");

        Random rand = new Random();
        int playFirst = rand.nextInt(1);
        if (playFirst == 1) { // always player0 plays first, so swap two players
            Player temp = player0;
            player0 = player1;
            player1 = temp;
        }

        while (true) {
            //board.putPieces(player0)


        }


    }


    public void over() {

    }



    public static void main(String args[]) {
        Game game = new Game(8, 8);
        game.start();
    }
}

package game;
import java.util.*;

public class Game {

    public Player player0;
    public Player player1;

    public Board playBoard;
    public int rank;
    public int file;
    private boolean isEnd;


    public Game(int rank, int file) {
        this.rank = rank;
        this.file = file;

        player0 = new Player(0, playBoard);
        player1 = new Player(1, playBoard);
        playBoard = new Board(rank, file, player0, player1);
        playBoard.setPieces();
        isEnd = false;
    }

    /**
     * judge whether the game should end
     * checkmate or stalemate happens
     * @param moving player for this turn
     * @param standing player who is waiting for next turn
     */
    public void judge(Player moving, Player standing) {
        playBoard.isCheckmate(moving, standing);
        if (player0.isLose || player1.isLose) {
            isEnd = true;
        } else if (playBoard.isStalemate(moving, standing)) {
            isEnd = true;
        }
    }


    public void start() {
        System.out.println("Game start!");

        // decide who goes first
        Random rand = new Random();
        int playFirst = rand.nextInt(1);
        if (playFirst == 1) { // always player0 plays first, so swap two players
            Player temp = player0;
            player0 = player1;
            player1 = temp;
        }

        while (!isEnd) {
            int player0StartX;
            int player0StartY;
            int player0EndX;
            int player0EndY;

            System.out.println("player0: input the start position and destination: ");
            do {
                player0StartX = new Scanner(System.in).nextInt();
                player0StartY = new Scanner(System.in).nextInt();
                player0EndX = new Scanner(System.in).nextInt();
                player0EndY = new Scanner(System.in).nextInt();
            } while(playBoard.checkValid(player0, player0StartX, player0StartY, player0EndX, player0EndY));

            playBoard.putPieces(player0, player0StartX, player0StartY, player0EndX, player0EndY);
            judge(player0, player1);

            if (isEnd) {
                continue;
            }

            int player1StartX;
            int player1StartY;
            int player1EndX;
            int player1EndY;

            System.out.println("player1: input the start position and destination: ");
            do {
                player1StartX = new Scanner(System.in).nextInt();
                player1StartY = new Scanner(System.in).nextInt();
                player1EndX = new Scanner(System.in).nextInt();
                player1EndY = new Scanner(System.in).nextInt();
            } while(playBoard.checkValid(player1, player1StartX, player1StartY, player1EndX, player1EndY));

            playBoard.putPieces(player1, player1StartX, player1StartY, player1EndX, player1EndY);
            judge(player1, player0);
        }

        if (player1.isLose) {
            System.out.println("player0 win!!");
        } else if (player0.isLose) {
            System.out.println("player1 win!!");
        } else {
            System.out.println("Draw!!");
        }
    }


    public static void main(String args[]) {
        Game game = new Game(8, 8);
        game.start();
    }
}

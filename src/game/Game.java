package game;
import java.util.*;

public class Game {

    /** the upper player */
    public Player player0;

    /** the player locates downward*/
    public Player player1;

    /** the board for this game*/
    public Board playBoard;

    /** the number of rank on the board */
    public int rank;

    /** the number of file on the board */
    public int file;

    /** whether game is end */
    private boolean isEnd;

    public boolean addCustomPiece;


    /**
     * initialize game attributes
     * @param rank the number of rank on the board
     * @param file the number of file on the board
     */
    public Game(int rank, int file, boolean addCustomPiece, String name0, String name1) {
        this.rank = rank;
        this.file = file;
        this.addCustomPiece = addCustomPiece;

        player0 = new Player(0, playBoard, name0); //
        player1 = new Player(1, playBoard, name1); //
        playBoard = new Board(this);
        playBoard.setPieces();
        isEnd = false;
    }

    /**
     * judge whether the game should end
     * checkmate or stalemate happens
     * @param moving player for this turn
     * @param standing player who is waiting for next turn
     */
    public boolean judge(Player moving, Player standing) {
        playBoard.isCheckmate(moving, standing);
        if (player0.isLose || player1.isLose) {
            isEnd = true;
        } else if (playBoard.isStalemate(moving, standing)) {
            isEnd = true;
        }
        return isEnd;
    }


    /**
     * game start interface
     * @return whether game is end
     */
    public boolean start() {
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
            int player0StartX, player0StartY, player0EndX, player0EndY;

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

            int player1StartX, player1StartY, player1EndX, player1EndY;

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
        return isEnd;
    }

//
//    public static void main(String args[]) {
//        Game game = new Game(8, 8);
//        game.start();
//    }
}

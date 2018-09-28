package GUI;

import game.Game;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class GameGUI {

    JFrame window;
    BoardGUI chessBoard;
    boolean customize;
    Game presentGame;

    GameGUI() {
        window = new JFrame("MY Chess Game");
        window.setResizable(false);
        window.setLayout(new BorderLayout());

        window.setLocation(100, 100);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(new Dimension(640, 720));
        setUpMenu(window);

        chessBoard = new BoardGUI(presentGame); // user input to choose
        window.add(chessBoard, BorderLayout.CENTER);
        window.add(new GamePanel(presentGame), BorderLayout.NORTH);
        window.setVisible(true); // set lastly
    }

    private void setUpMenu(JFrame window) {
        JMenuBar menubar = new JMenuBar();

        JMenu game = new JMenu("Game");
        JMenuItem player = new JMenuItem("Choose Player");
        JMenuItem restart = new JMenuItem("Restart Game");
        JMenuItem customPiece = new JMenuItem("Add Custom Pieces");
        game.add(player);
        game.add(restart);
        game.add(customPiece);


        JMenu steps = new JMenu("Steps");
        JMenuItem forfeit = new JMenuItem("Forfeit");
        //open.addActionListener(this);
        steps.add(forfeit);

        menubar.add(game);
        menubar.add(steps);
        window.setJMenuBar(menubar);
    }


    public static void main(String[] args) {
        new GameGUI();
    }
}

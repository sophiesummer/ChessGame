package GUI;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class GameGUI {

    JFrame window;
    BoardGUI chessBoard;

    GameGUI() {
        window = new JFrame("MY Chess Game");
        window.setResizable(false);
        window.setLayout(new BorderLayout());
        window.setVisible(true);
        window.setLocation(100, 100);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(new Dimension(640, 640));

        chessBoard = new BoardGUI();
        window.add(chessBoard, BorderLayout.CENTER);
        setUpMenu(window);

    }

    private void setUpMenu(JFrame window) {
        JMenuBar menubar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenuItem open = new JMenuItem("Open");
        //open.addActionListener(this);
        file.add(open);

        JMenu game = new JMenu("Game");
        JMenuItem player = new JMenuItem("choose player");
        JMenuItem start = new JMenuItem("start game");
        game.add(player);
        game.add(start);

        menubar.add(file);
        menubar.add(game);
        window.setJMenuBar(menubar);
    }



    public static void main(String[] args) {
        new GameGUI();

//        File imageCheck = new File("src/GUI/image/bRook.png");
//
//        if(imageCheck.exists())
//            System.out.println("Image file found!!@@@");
//        else
//            System.out.println("Image file not found!");
    }
}

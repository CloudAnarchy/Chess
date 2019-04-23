package com.nickpappas;

public class Main {

    public static void main(String[] args) {
        Board board = new Board();
        int WIDTH = 600;
        int HEIGHT = 600;
        board.createBoard();
        board.setSize(WIDTH, HEIGHT);
        board.setVisible(true);

    }
}

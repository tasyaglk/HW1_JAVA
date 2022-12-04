package org.example;

import java.util.ArrayList;

public class ComputersTurn {

    private final Board board;
    private int fl = 0;

    public ComputersTurn(Board board) {
        this.board = board;
        ArrayList<Points> posMov = new ArrayList<Points>();
        posMov = board.whichfreeCells(2, 1);
        if (posMov.size() == 0) {
            System.out.println("Draw");
            fl = 1;
        }
        if (fl == 0) {
            board.futureSteps(posMov);
            System.out.println("Computer's turn:");
            board.printBoard();
        }
    }

    int check() {
        return fl;
    }

}

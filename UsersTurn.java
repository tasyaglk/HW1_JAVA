package org.example;

import java.util.*;

public class UsersTurn {

    private int fl = 0;
    private final List<Integer> allowedPositions = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7);
    private final Board board;


    public UsersTurn(Board board, int colour, int colourRival) {
        this.board = board;
        Scanner scanner = new Scanner(System.in);

        int cor = 0;
        while (cor == 0) {
            ArrayList<Points> posMov = new ArrayList<>();
            posMov = board.whichfreeCells(colour, colourRival);
            if (posMov.size() == 0) {
                System.out.println("Draw");
                fl = 1;
            }
            if(fl == 0)
            {
                board.possibleMoves(posMov);
                System.out.println("Your possible move");
                for (Points possible : posMov) {
                    System.out.println("(" + possible.x + ", " + possible.y + ")");
                }
                System.out.println("Please, enter your move");
                Checker ch = new Checker();
                int x = ch.Checker(1);
                int y = ch.Checker(1);
                // нужно вводить каждое число с новой строки
//                int x = 0;
//                boolean isInputWorking = true;
//                do {
//                    try {
//                        x = scanner.nextInt();
//                        if (allowedPositions.contains(x)) {
//                            isInputWorking = false;
//                        } else {
//                            System.out.println("Incorrect command");
//                        }
//                    } catch (InputMismatchException ex) {
//                        System.out.println("Incorrect input");
//                        scanner.next();
//                    }
//                } while (isInputWorking);
//
//                int y = 0;
//                 isInputWorking = true;
//                do {
//                    try {
//                        y = scanner.nextInt();
//                        if (allowedPositions.contains(y)) {
//                            isInputWorking = false;
//                        } else {
//                            System.out.println("Incorrect command");
//                        }
//                    } catch (InputMismatchException ex) {
//                        System.out.println("Incorrect input");
//                        scanner.next();
//                    }
//                } while (isInputWorking);

                Points move = new Points(x, y);
                for (int k = 0; k < 8; k++) {
                    cor = board.isCorrect(move, k, colourRival, colour);
                    if (cor == 1) {
                        System.out.println("User's turn:");
                        board.printBoard();
                        break;
                    }
                }
                if (cor == 0) {
                    System.out.println("This is the wrong move. Try again!");
                }
            }
        }
    }

    int uCheck() {
        return fl;
    }
}



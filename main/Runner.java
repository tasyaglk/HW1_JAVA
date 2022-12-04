package org.example;

import java.util.*;

public class Runner {

    private final List<Integer> allowedCommands = Arrays.asList(1, 2);
    public int maxUser1 = 0;
    public int maxUser2 = 0;
    public int maxUser = 0;

    public void startGame() {
        String tmp = "-1";
        while (!Objects.equals(tmp, "3")) {
            System.out.println("Сhoose difficulty\n1 if easy level 2 if difficult");

            Checker ch = new Checker();
            int choice = ch.Checker(0);

            if (choice == 2) {
                System.out.println("Sorry, but this has not been developed yet=(\nLet's play in easy mode!");
            }
            System.out.println("Select turn:\n1 if Computer or 2 if User");
            Board b = new Board();

            choice = ch.Checker(0);

            if (choice == 1) {
                //B = user 1
                //W - comp 2
                b.firstFill();
                b.printBoard();
                int cnt = 0;
                while (!b.gameOver()) {
                    if (cnt % 2 == 1) {
                        ComputersTurn cT = new ComputersTurn(b);
                        if (cT.check() == 1) {
                            break;
                        }
                    } else {
                        UsersTurn uT = new UsersTurn(b, 1, 2);
                        if (uT.uCheck() == 1) {
                            break;
                        }
                    }
                    cnt++;
                }
                Runner r = new Runner();
                ScoreUserVsComputer s = new ScoreUserVsComputer(b, r);
            } else if (choice == 2) {
                //B = user 1
                //W - user 2
                b.firstFill();
                b.printBoard();
                int cnt = 0;
                while (!b.gameOver()) {
                    if (cnt % 2 == 1) {
                        UsersTurn uT = new UsersTurn(b, 2, 1);
                        if (uT.uCheck() == 1) {
                            break;
                        }
                    } else {
                        UsersTurn uT = new UsersTurn(b, 1, 2);
                        if (uT.uCheck() == 1) {
                            break;
                        }
                    }
                    cnt++;
                }
                Runner r = new Runner();
                ScoreForUserVsUser s = new ScoreForUserVsUser(b, r);

            }
            System.out.println("If you want to stop playing, enter 3. Otherwise, enter something else");
            Scanner scanner = new Scanner(System.in);
            tmp = scanner.next();
            if (Objects.equals(tmp, "3")) {
                b.closeGame();
            }
        }
    }
}

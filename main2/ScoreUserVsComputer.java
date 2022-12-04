package org.example;

public class ScoreUserVsComputer {
    private final Board board;
    private final Runner r;
    public ScoreUserVsComputer(Board board, Runner r) {

        this.board = board;
        this.r = r;
        int swh = board.whoIsWin(2);
        int sbl = board.whoIsWin(1);
        if (swh > sbl) {
            r.maxUser = Math.max(64 - swh, r.maxUser);
            System.out.println("Computer won with score " + swh);
        } else if (swh < sbl) {
            r.maxUser = Math.max(r.maxUser, sbl);
            System.out.println("User won with score " + sbl);
        } else {
            r.maxUser = Math.max(r.maxUser, sbl);
            System.out.println("Draw\n");
        }
        System.out.println("The best result of the user in the game with the computer - " + r.maxUser);
    }
}

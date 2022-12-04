package org.example;

public class ScoreForUserVsUser {
    private final Board board;
    private final Runner r;

    public ScoreForUserVsUser(Board board, Runner r) {
        this.board = board;
        this.r = r;
        int swh = board.whoIsWin(2);
        int sbl = board.whoIsWin(1);
        if (swh > sbl) {
            r.maxUser2 = Math.max(r.maxUser2, swh);
            System.out.println("User_2 won\n");
        } else if (swh < sbl) {
            r.maxUser1 = Math.max(r.maxUser1, swh);
            System.out.println("User_1 won\n");
        } else {
            r.maxUser2 = Math.max(r.maxUser2, swh);
            r.maxUser1 = Math.max(r.maxUser1, swh);
            System.out.println("Draw\n");
        }
        if (r.maxUser2 > r.maxUser1) {
            System.out.println("The best result of the user in the game with user - " + r.maxUser2 + " by User2");
        } else {
            System.out.println("The best result of the user in the game with user - " + r.maxUser1 + " by User1");
        }
    }
}

package org.example;

import java.util.ArrayList;
import java.util.Comparator;

public class Board {
    public static final int FREE = 0;
    public static final int BLACK = 1;
    public static final int WHITE = 2;
    public static final int QUESTION = 3;

    private final int[][] board = new int[8][8];
    private final int[][] boardSave1 = new int[8][8];
    private final int[][] boardSave2 = new int[8][8];

    public void firstFill() {
        board[3][3] = WHITE;
        board[4][4] = WHITE;
        board[3][4] = BLACK;
        board[4][3] = BLACK;
    }

    private final double[] sum_around = new double[8];

    public boolean gameOver() {
        int whitesum = 0;
        int blacksum = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                String val = " ";
                if (board[i][j] == BLACK) {
                    blacksum++;
                }
                if (board[i][j] == WHITE) {
                    whitesum++;
                }
            }
        }

        if ((blacksum + whitesum) == 64) {
            return true;
        }
        return false;
    }

    public void boardToSave1() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                boardSave1[i][j] = board[i][j];
            }
        }
    }

    public void reWriteBoard1() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = boardSave1[i][j];
            }
        }
    }

    public void boardToSave2() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                boardSave2[i][j] = board[i][j];
            }
        }
    }

    public void reWriteBoard2() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = boardSave2[i][j];
            }
        }
    }


    public int whoIsWin(int colour) {
        int whitesum = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == colour) {
                    whitesum++;
                }
            }
        }
        return whitesum;
    }

    public void closeGame() {
        System.out.println("Good bye!!!");
        System.exit(1);
    }

    // будущее для разработки ходов для пользователя
    public ArrayList<Points> whichfreeCells(int colour, int colourRival) {
        ArrayList<Points> freeCells = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == colour) {
                    //freeCells.add(new Points(i, j));
                    int cntsum = 0;
                    Points cntcoord = new Points(i, j);
                    int fl = 0;
                    //left
                    if (j - 1 != -1) {
                        cntcoord.y--;
                        while (board[cntcoord.x][cntcoord.y] == colourRival) {
                            if (cntcoord.y == 0) {
                                fl = 1;
                                break;
                            }
                            cntcoord.y--;
                            cntsum++;
                        }
                        if (fl != 1 && cntsum != 0 && board[cntcoord.x][cntcoord.y] == FREE) {
                            Points pcnt = new Points(cntcoord.x, cntcoord.y);
                            int loopfl = 0;
                            for (Points k : freeCells) {
                                if (k.x == cntcoord.x && k.y == cntcoord.y) {
                                    loopfl = 1;
                                    break;
                                }
                            }
                            if (loopfl == 0) {
                                freeCells.add(pcnt);
                            }
                        }
                    }
                    // left down
                    cntcoord.x = i;
                    cntcoord.y = j;
                    cntsum = 0;
                    fl = 0;
                    if (i + 1 != 8 && j - 1 != -1) {
                        cntcoord.x++;
                        cntcoord.y--;
                        while (board[cntcoord.x][cntcoord.y] == colourRival) {
                            if (cntcoord.x == 7 || cntcoord.y == 0) {
                                fl = 1;
                                break;
                            }
                            cntcoord.y--;
                            cntcoord.x++;
                            cntsum++;
                        }
                        if (fl != 1 && cntsum != 0 && board[cntcoord.x][cntcoord.y] == FREE) {
                            Points pcnt = new Points(cntcoord.x, cntcoord.y);
                            int loopfl = 0;
                            for (Points k : freeCells) {
                                if (k.x == cntcoord.x && k.y == cntcoord.y) {
                                    loopfl = 1;
                                    break;
                                }
                            }
                            if (loopfl == 0) {
                                freeCells.add(pcnt);
                            }
                        }
                    }
                    //down
                    cntcoord.x = i;
                    cntcoord.y = j;
                    cntsum = 0;
                    fl = 0;
                    if (i + 1 != 8) {
                        cntcoord.x++;
                        while (board[cntcoord.x][cntcoord.y] == colourRival) {
                            if (cntcoord.x == 7) {
                                fl = 1;
                                break;
                            }
                            cntcoord.x++;
                            cntsum++;
                        }
                        if (fl != 1 && cntsum != 0 && board[cntcoord.x][cntcoord.y] == FREE) {
                            Points pcnt = new Points(cntcoord.x, cntcoord.y);
                            int loopfl = 0;
                            for (Points k : freeCells) {
                                if (k.x == cntcoord.x && k.y == cntcoord.y) {
                                    loopfl = 1;
                                    break;
                                }
                            }
                            if (loopfl == 0) {
                                freeCells.add(pcnt);
                            }
                        }
                    }
                    // right down
                    cntcoord.x = i;
                    cntcoord.y = j;
                    cntsum = 0;
                    fl = 0;
                    if (i + 1 != 8 && j + 1 != 8) {
                        cntcoord.x++;
                        cntcoord.y++;
                        while (board[cntcoord.x][cntcoord.y] == colourRival) {
                            if (cntcoord.x == 7 || cntcoord.y == 7) {
                                fl = 1;
                                break;
                            }
                            cntcoord.x++;
                            cntcoord.y++;
                            cntsum++;
                        }
                        if (fl != 1 && cntsum != 0 && board[cntcoord.x][cntcoord.y] == FREE) {
                            Points pcnt = new Points(cntcoord.x, cntcoord.y);
                            int loopfl = 0;
                            for (Points k : freeCells) {
                                if (k.x == cntcoord.x && k.y == cntcoord.y) {
                                    loopfl = 1;
                                    break;
                                }
                            }
                            if (loopfl == 0) {
                                freeCells.add(pcnt);
                            }
                        }
                    }
                    //right
                    cntcoord.x = i;
                    cntcoord.y = j;
                    cntsum = 0;
                    fl = 0;
                    if (j + 1 != 8) {
                        cntcoord.y++;
                        while (board[cntcoord.x][cntcoord.y] == colourRival) {
                            if (cntcoord.y == 7) {
                                fl = 1;
                                break;
                            }
                            cntcoord.y++;
                            cntsum++;
                        }
                        if (fl != 1 && cntsum != 0 && board[cntcoord.x][cntcoord.y] == FREE) {
                            Points pcnt = new Points(cntcoord.x, cntcoord.y);
                            int loopfl = 0;
                            for (Points k : freeCells) {
                                if (k.x == cntcoord.x && k.y == cntcoord.y) {
                                    loopfl = 1;
                                    break;
                                }
                            }
                            if (loopfl == 0) {
                                freeCells.add(pcnt);
                            }
                        }
                    }
                    //right up
                    cntcoord.x = i;
                    cntcoord.y = j;
                    cntsum = 0;
                    fl = 0;
                    if (i - 1 != -1 && j + 1 != 8) {
                        cntcoord.x--;
                        cntcoord.y++;
                        while (board[cntcoord.x][cntcoord.y] == colourRival) {
                            if (cntcoord.x == 0 || cntcoord.y == 7) {
                                fl = 1;
                                break;
                            }
                            cntcoord.x--;
                            cntcoord.y++;
                            cntsum++;
                        }
                        if (fl != 1 && cntsum != 0 && board[cntcoord.x][cntcoord.y] == FREE) {
                            Points pcnt = new Points(cntcoord.x, cntcoord.y);
                            int loopfl = 0;
                            for (Points k : freeCells) {
                                if (k.x == cntcoord.x && k.y == cntcoord.y) {
                                    loopfl = 1;
                                    break;
                                }
                            }
                            if (loopfl == 0) {
                                freeCells.add(pcnt);
                            }
                        }
                    }
                    //  up
                    cntcoord.x = i;
                    cntcoord.y = j;
                    cntsum = 0;
                    fl = 0;
                    if (i - 1 != -1) {
                        cntcoord.x--;
                        while (board[cntcoord.x][cntcoord.y] == colourRival) {
                            if (cntcoord.x == 0) {
                                fl = 1;
                                break;
                            }
                            cntcoord.x--;
                            cntsum++;
                        }
                        if (fl != 1 && cntsum != 0 && board[cntcoord.x][cntcoord.y] == FREE) {
                            Points pcnt = new Points(cntcoord.x, cntcoord.y);
                            int loopfl = 0;
                            for (Points k : freeCells) {
                                if (k.x == cntcoord.x && k.y == cntcoord.y) {
                                    loopfl = 1;
                                    break;
                                }
                            }
                            if (loopfl == 0) {
                                freeCells.add(pcnt);
                            }
                        }
                    }
                    //up left
                    cntcoord.x = i;
                    cntcoord.y = j;
                    cntsum = 0;
                    fl = 0;
                    if (i - 1 != -1 && j - 1 != -1) {
                        cntcoord.x--;
                        cntcoord.y--;
                        while (board[cntcoord.x][cntcoord.y] == colourRival) {
                            if (cntcoord.x == 0 || cntcoord.y == 0) {
                                fl = 1;
                                break;
                            }
                            cntcoord.x--;
                            cntcoord.y--;
                            cntsum++;
                        }
                        if (fl != 1 && cntsum != 0 && board[cntcoord.x][cntcoord.y] == FREE) {
                            Points pcnt = new Points(cntcoord.x, cntcoord.y);
                            int loopfl = 0;
                            for (Points k : freeCells) {
                                if (k.x == cntcoord.x && k.y == cntcoord.y) {
                                    loopfl = 1;
                                    break;
                                }
                            }
                            if (loopfl == 0) {
                                freeCells.add(pcnt);
                            }
                        }
                    }
                }
            }
        }
        return freeCells;
    }


    public void possibleMoves(ArrayList<Points> move) {
        int[][] board2 = new int[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board2[i][j] = board[i][j];
            }
        }
        //board2 = board;
        for (Points coord : move) {
            board2[coord.x][coord.y] = QUESTION;
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                String val = " ";
                if (board2[i][j] == BLACK) {
                    val = "B";
                }
                if (board2[i][j] == WHITE) {
                    val = "W";
                }
                if (board2[i][j] == QUESTION) {
                    val = "?";
                }
                System.out.printf("|" + val);
            }
            System.out.printf("|");
            System.out.println();
        }
        System.out.println();
    }


    public void printBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                String val = " ";
                if (board[i][j] == BLACK) {
                    val = "B";
                }
                if (board[i][j] == WHITE) {
                    val = "W";
                }
                System.out.printf("|" + val);
            }
            System.out.println("|");
        }
        System.out.println();
    }


    public void paintBoard(Points begin, int dir, int colLine, int colB) {
        Points vec = new Points(0, 0);
        if (dir == 0) {
            vec.x = 0;
            vec.y = -1;
        }
        if (dir == 1) {
            vec.x = 1;
            vec.y = -1;
        }
        if (dir == 2) {
            vec.x = 1;
            vec.y = 0;
        }
        if (dir == 3) {
            vec.x = 1;
            vec.y = 1;
        }
        if (dir == 4) {
            vec.x = 0;
            vec.y = 1;
        }
        if (dir == 5) {
            vec.x = -1;
            vec.y = 1;
        }
        if (dir == 6) {
            vec.x = -1;
            vec.y = 0;
        }
        if (dir == 7) {
            vec.x = -1;
            vec.y = -1;
        }
        board[begin.x][begin.y] = colB;
        begin.x += vec.x;
        begin.y += vec.y;
        while (board[begin.x][begin.y] == colLine) {
            board[begin.x][begin.y] = colB;
            begin.x += vec.x;
            begin.y += vec.y;
        }
        board[begin.x][begin.y] = colB;
    }

    //функция по тому какие ходы можем делать
    public void futureSteps(ArrayList<Points> possibleMov) {
        double finalMax = 0;
        int finalDir = 0;
        Points start = new Points(0, 0);
        for (Points k : possibleMov) {
            Points dir;
            int maxsum = 0;
            for (int k1 = 0; k1 < 8; k1++) {
                sum_around[k1] = 0.0;
            }
            Points now = new Points(k.x, k.y);
            Points course = new Points(0, -1);
            //(0, -1)
            if (now.y != 0) {
                sum_around[0] = dirMove(course, now);
            }

            course.x = 1;
            course.y = -1;
            if (now.x != 7 && now.y != 0) {
                sum_around[1] = dirMove(course, now);
            }

            course.x = 1;
            course.y = 0;
            if (now.x != 7) {
                sum_around[2] = dirMove(course, now);
            }

            course.x = 1;
            course.y = 1;
            if (now.x != 7 && now.y != 7) {
                sum_around[3] = dirMove(course, now);
            }

            course.x = 0;
            course.y = 1;
            if (now.y != 7) {
                sum_around[4] = dirMove(course, now);
            }

            course.x = -1;
            course.y = 1;
            if (now.x != 0 && now.y != 7) {
                sum_around[5] = dirMove(course, now);
            }

            course.x = -1;
            course.y = 0;
            if (now.x != 0) {
                sum_around[6] = dirMove(course, now);
            }

            course.x = -1;
            course.y = -1;
            if (now.x != 0 && now.y != 0) {
                sum_around[7] = dirMove(course, now);
            }

            for (int k1 = 0; k1 < 8; k1++) {
                if (sum_around[k1] > finalMax) {
                    finalMax = sum_around[k1];
                    finalDir = k1;
                    start.x = k.x;
                    start.y = k.y;
                }
            }
        }
        paintBoard(start, finalDir, BLACK, WHITE);
    }

    //возвращает ответ из данной позиции во всех направлениях
    public double dirMove(Points p, Points location) {
        int cntsum = 0;
        double ans = 0;
        Points cntcoord = new Points(location.x, location.y);
        int cntLine = 1;
        int cntCornOrSide = 0;
        int fl = 0;
        //left
        if (p.x == 0 && p.y == -1) {
            cntcoord.y--;
            if (cntcoord.y == -1) {
                fl = 1;
            }
            while (fl == 0 && board[cntcoord.x][cntcoord.y] == BLACK) {
                if (cntcoord.y == 0) {
                    fl = 1;
                    break;
                }
                cntcoord.y--;
                cntsum++;
            }
            if (fl != 1 && cntsum != 0 && board[cntcoord.x][cntcoord.y] == WHITE) {
                if ((cntcoord.x == 0 && cntcoord.y == 0) || (cntcoord.x == 7 && cntcoord.y == 0)) {
                    cntCornOrSide = 1;
                    cntLine = 2;
                } else if (cntcoord.x == 0 || cntcoord.x == 7) {
                    cntLine = 2;
                }
                Func a = new Func();
                ans = a.Funcc(cntsum, cntLine, cntCornOrSide);

            }
        }
        // left down
        if (p.x == 1 && p.y == -1) {
            cntcoord.x++;
            cntcoord.y--;
            if (cntcoord.x == 8 && cntcoord.y == -1) {
                fl = 1;
            }
            while (fl == 0 && board[cntcoord.x][cntcoord.y] == BLACK) {
                if (cntcoord.x == 7 || cntcoord.y == 0) {
                    fl = 1;
                    break;
                }
                cntcoord.y--;
                cntcoord.x++;
                cntsum++;
            }
            if (fl != 1 && cntsum != 0 && board[cntcoord.x][cntcoord.y] == WHITE) {
                if ((cntcoord.x == 7 && cntcoord.y == 0)) {
                    cntCornOrSide = 1;
                } else if (cntcoord.y == 0 || cntcoord.x == 7) {
                    cntLine = 1;
                }
                Func a = new Func();
                ans = a.Funcc(cntsum, cntLine, cntCornOrSide);
            }
        }
        //down
        if (p.x == 1 && p.y == 0) {
            cntcoord.x++;
            if (cntcoord.x == 7) {
                fl = 1;
            }
            while (fl == 0 && board[cntcoord.x][cntcoord.y] == BLACK) {
                if (cntcoord.x == 7) {
                    fl = 1;
                    break;
                }
                cntcoord.x++;
                cntsum++;
            }
            if (fl != 1 && cntsum != 0 && board[cntcoord.x][cntcoord.y] == WHITE) {
                if ((cntcoord.x == 7 && cntcoord.y == 0) || (cntcoord.x == 7 && cntcoord.y == 7)) {
                    cntCornOrSide = 1;
                    cntLine = 2;
                } else if (cntcoord.y == 0 || cntcoord.y == 7) {
                    cntLine = 2;
                }
                Func a = new Func();
                ans = a.Funcc(cntsum, cntLine, cntCornOrSide);
            }
        }
        //right down
        if (p.x == 1 && p.y == 1) {
            cntcoord.x++;
            cntcoord.y++;
            if (cntcoord.x == 7 || cntcoord.y == 7) {
                fl = 1;
            }
            while (fl == 0 && board[cntcoord.x][cntcoord.y] == BLACK) {
                if (cntcoord.x == 7 || cntcoord.y == 7) {
                    fl = 1;
                    break;
                }
                cntcoord.x++;
                cntcoord.y++;
                cntsum++;
            }
            if (fl != 1 && cntsum != 0 && board[cntcoord.x][cntcoord.y] == WHITE) {
                if ((cntcoord.x == 7 && cntcoord.y == 7)) {
                    cntCornOrSide = 1;
                } else if (cntcoord.y == 7 || cntcoord.x == 7) {
                    cntLine = 1;
                }
                Func a = new Func();
                ans = a.Funcc(cntsum, cntLine, cntCornOrSide);
            }
        }
        //right
        if (p.x == 0 && p.y == 1) {
            cntcoord.y++;
            if (cntcoord.y == 7) {
                fl = 1;
            }
            while (fl == 0 && board[cntcoord.x][cntcoord.y] == BLACK) {
                if (cntcoord.y == 7) {
                    fl = 1;
                    break;
                }
                cntcoord.y++;
                cntsum++;
            }
            if (fl != 1 && cntsum != 0 && board[cntcoord.x][cntcoord.y] == WHITE) {
                if ((cntcoord.x == 0 && cntcoord.y == 7) || (cntcoord.x == 7 && cntcoord.y == 7)) {
                    cntCornOrSide = 1;
                    cntLine = 2;
                } else if (cntcoord.x == 0 || cntcoord.x == 7) {
                    cntLine = 2;
                }
                Func a = new Func();
                ans = a.Funcc(cntsum, cntLine, cntCornOrSide);
            }
        }
        //right up
        if (p.x == -1 && p.y == 1) {
            cntcoord.x--;
            cntcoord.y++;
            if (cntcoord.x == 0 || cntcoord.y == 7) {
                fl = 1;
            }
            while (fl == 0 && board[cntcoord.x][cntcoord.y] == BLACK) {
                if (cntcoord.x == 0 || cntcoord.y == 7) {
                    fl = 1;
                    break;
                }
                cntcoord.x--;
                cntcoord.y++;
                cntsum++;
            }
            if (fl != 1 && cntsum != 0 && board[cntcoord.x][cntcoord.y] == WHITE) {
                if ((cntcoord.x == 0 && cntcoord.y == 7)) {
                    cntCornOrSide = 1;
                } else if (cntcoord.y == 7 || cntcoord.x == 0) {
                    cntLine = 1;
                }
                Func a = new Func();
                ans = a.Funcc(cntsum, cntLine, cntCornOrSide);
            }
        }
        // up
        if (p.x == -1 && p.y == 0) {
            cntcoord.x--;
            if (cntcoord.x == 0) {
                fl = 1;
            }
            while (fl == 0 && board[cntcoord.x][cntcoord.y] == BLACK) {
                if (cntcoord.x == 0) {
                    fl = 1;
                    break;
                }
                cntcoord.x--;
                cntsum++;
            }
            if (fl != 1 && cntsum != 0 && board[cntcoord.x][cntcoord.y] == WHITE) {
                if ((cntcoord.x == 0 && cntcoord.y == 0) || (cntcoord.x == 0 && cntcoord.y == 7)) {
                    cntCornOrSide = 1;
                    cntLine = 2;
                } else if (cntcoord.y == 0 || cntcoord.y == 7) {
                    cntLine = 2;
                }
                Func a = new Func();
                ans = a.Funcc(cntsum, cntLine, cntCornOrSide);
            }
        }
        // left up
        if (p.x == -1 && p.y == -1) {
            cntcoord.x--;
            cntcoord.y--;
            if (cntcoord.x == 0 || cntcoord.y == 0) {
                fl = 1;
            }
            while (fl == 0 && board[cntcoord.x][cntcoord.y] == BLACK) {
                if (cntcoord.x == 0 || cntcoord.y == 0) {
                    fl = 1;
                    break;
                }
                cntcoord.x--;
                cntcoord.y--;
                cntsum++;
            }
            if (fl != 1 && cntsum != 0 && board[cntcoord.x][cntcoord.y] == WHITE) {
                if ((cntcoord.x == 0 && cntcoord.y == 0)) {
                    cntCornOrSide = 1;
                } else if (cntcoord.y == 0 || cntcoord.x == 0) {
                    cntLine = 1;
                }
                Func a = new Func();
                ans = a.Funcc(cntsum, cntLine, cntCornOrSide);
            }
        }
        return ans;
    }


    public int isCorrect(Points p, int dir, int colourLine, int colourBox) {
        int fl = 0;
        int cnt = 0;
        int flans = 0;
        if (p.x < 0 || p.x > 8 || p.y < 0 || p.y > 8) {
            fl = 1;
        }
        if (fl == 0 && board[p.x][p.y] == FREE) {
            Points cntcrd = new Points(p.x, p.y);
            // x = 0; y = -1
            if (dir == 0) {
                cntcrd.y--;
                if (cntcrd.y <= 0 || cntcrd.y >= 7 || cntcrd.x < 0 || cntcrd.x > 7) {
                    fl = 1;
                }
                while (fl == 0 && board[cntcrd.x][cntcrd.y] == colourLine) {
                    if (cntcrd.y == 0) {
                        fl = 1;
                        break;
                    }
                    cntcrd.y--;
                    cnt++;
                }
                if (fl == 0 && cnt != 0 && board[cntcrd.x][cntcrd.y] == colourBox) {
                    paintBoard(cntcrd, 4, colourLine, colourBox);
                    flans = 1;
                }
            }
            // x = 1; y = -1
            if (dir == 1) {
                cntcrd.y--;
                cntcrd.x++;
                if (cntcrd.y <= 0 || cntcrd.x >= 7 || cntcrd.x <= 0 || cntcrd.y >= 7) {
                    fl = 1;
                }
                while (fl == 0 && board[cntcrd.x][cntcrd.y] == colourLine) {
                    if (cntcrd.y == 0 || cntcrd.x == 7) {
                        fl = 1;
                        break;
                    }
                    cntcrd.y--;
                    cntcrd.x++;
                    cnt++;
                }
                if (fl == 0 && cnt != 0 && board[cntcrd.x][cntcrd.y] == colourBox) {
                    paintBoard(cntcrd, 5, colourLine, colourBox);
                    flans = 1;
                }
            }
            // x = 1; y = 0
            if (dir == 2) {
                cntcrd.x++;
                if (cntcrd.x >= 7 || cntcrd.x <= 0 || cntcrd.y >= 8 || cntcrd.y < 0) {
                    fl = 1;
                }
                while (fl == 0 && board[cntcrd.x][cntcrd.y] == colourLine) {
                    if (cntcrd.x == 7) {
                        fl = 1;
                        break;
                    }
                    cntcrd.x++;
                    cnt++;
                }
                if (fl == 0 && cnt != 0 && board[cntcrd.x][cntcrd.y] == colourBox) {
                    paintBoard(cntcrd, 6, colourLine, colourBox);
                    flans = 1;
                }
            }
            // x = 1; y = 1
            if (dir == 3) {
                cntcrd.x++;
                cntcrd.y++;
                if (cntcrd.y >= 7 || cntcrd.x >= 7 || cntcrd.y <= 0 || cntcrd.x <= 0) {
                    fl = 1;
                }
                while (fl == 0 && board[cntcrd.x][cntcrd.y] == colourLine) {
                    if (cntcrd.x == 7 || cntcrd.y == 7) {
                        fl = 1;
                        break;
                    }
                    cntcrd.x++;
                    cntcrd.y++;
                    cnt++;
                }
                if (fl == 0 && cnt != 0 && board[cntcrd.x][cntcrd.y] == colourBox) {
                    paintBoard(cntcrd, 7, colourLine, colourBox);
                    flans = 1;
                }
            }
            // x = 0; y = 1
            if (dir == 4) {
                cntcrd.y++;
                if (cntcrd.y >= 7 || cntcrd.y <= 0 || cntcrd.x < 0 || cntcrd.x > 7) {
                    fl = 1;
                }
                while (fl == 0 && board[cntcrd.x][cntcrd.y] == colourLine) {
                    if (cntcrd.y == 7) {
                        fl = 1;
                        break;
                    }
                    cntcrd.y++;
                    cnt++;
                }
                if (fl == 0 && cnt != 0 && board[cntcrd.x][cntcrd.y] == colourBox) {
                    paintBoard(cntcrd, 0, colourLine, colourBox);
                    flans = 1;
                }
            }
            // x = -1; y = 1
            if (dir == 5) {
                cntcrd.x--;
                cntcrd.y++;
                if (cntcrd.y >= 7 || cntcrd.y <= 0 || cntcrd.x >= 7 || cntcrd.x <= 0) {
                    fl = 1;
                }
                while (fl == 0 && board[cntcrd.x][cntcrd.y] == colourLine) {
                    if (cntcrd.y == 7 || cntcrd.x == 0) {
                        fl = 1;
                        break;
                    }
                    cntcrd.x--;
                    cntcrd.y++;
                    cnt++;
                }
                if (fl == 0 && cnt != 0 && board[cntcrd.x][cntcrd.y] == colourBox) {
                    paintBoard(cntcrd, 1, colourLine, colourBox);
                    flans = 1;
                }
            }
            // x = -1; y = 0
            if (dir == 6) {
                cntcrd.x--;
                if (cntcrd.x <= 0 || cntcrd.x >= 7 || cntcrd.y < 0 || cntcrd.y > 7) {
                    fl = 1;
                }
                while (fl == 0 && board[cntcrd.x][cntcrd.y] == colourLine) {
                    if (cntcrd.x == 0) {
                        fl = 1;
                        break;
                    }
                    cntcrd.x--;
                    cnt++;
                }
                if (fl == 0 && cnt != 0 && board[cntcrd.x][cntcrd.y] == colourBox) {
                    paintBoard(cntcrd, 2, colourLine, colourBox);
                    flans = 1;
                }
            }
            // x = -1; y = -1
            if (dir == 7) {
                cntcrd.x--;
                cntcrd.y--;
                if (cntcrd.y <= 0 || cntcrd.x <= 0 || cntcrd.y >= 7 || cntcrd.x >= 7) {
                    fl = 1;
                }
                while (fl == 0 && board[cntcrd.x][cntcrd.y] == colourLine) {
                    if (cntcrd.y == 0 || cntcrd.x == 0) {
                        fl = 1;
                        break;
                    }
                    cntcrd.x--;
                    cntcrd.y--;
                    cnt++;
                }
                if (fl == 0 && cnt != 0 && board[cntcrd.x][cntcrd.y] == colourBox) {
                    paintBoard(cntcrd, 3, colourLine, colourBox);
                    flans = 1;
                }
            }
        }
        return flans;
    }
}

// простите, что так много. Но я не поняла, как разделять на классы функции, которые напрямую работают с доской(по индексам)
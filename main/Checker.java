package org.example;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Checker {
    private final List<Integer> allowedCommands = Arrays.asList(1, 2);
    private final List<Integer> allowedPositions = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7);

    public int Checker(int fl) {
        Scanner scanner = new Scanner(System.in);
        int par = 0;
        boolean isInputWorking = true;
        if (fl == 0) {
            do {
                try {
                    par = scanner.nextInt();
                    if (allowedCommands.contains(par)) {
                        isInputWorking = false;
                    } else {
                        System.out.println("Incorrect command");
                    }
                } catch (InputMismatchException ex) {
                    System.out.println("Incorrect input");
                    scanner.next();
                }
            } while (isInputWorking);
        } else {
            do {
                try {
                    par = scanner.nextInt();
                    if (allowedPositions.contains(par)) {
                        isInputWorking = false;
                    } else {
                        System.out.println("Incorrect command");
                    }
                } catch (InputMismatchException ex) {
                    System.out.println("Incorrect input");
                    scanner.next();
                }
            } while (isInputWorking);
        }
        return par;
    }
}

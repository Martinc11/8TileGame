package com.company;


/**
 * Runs the eight tile solver. Catches an exception from the view and exits if the user enters "quit".
 */

public class Main {

    public static void main(String[] args) {
        View view = new View();
        EightTileSolver eightTileSolver = new EightTileSolver(view);

        try {
            eightTileSolver.play(view);
        } catch (Exception e) {
            System.out.println("Thanks for playing!");
            System.exit(0);
        }

    }


}

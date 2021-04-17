package com.company;

import java.util.ArrayList;

enum States {
    EASY, MEDIUM, HARD, HARDER, HARDEST
}

public class EightTileSolver {


    int[][] veryEasyState = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 0, 8}};

    int[][] mediumState = {
            {1, 2, 0},
            {4, 5, 3},
            {7, 8, 6}};

    int[][] otherMediumState = {
            {2, 3, 6},
            {1, 4, 8},
            {7, 5, 0}};

    int[][] hard = {
            {0, 1, 2},
            {4, 5, 3},
            {7, 8, 6}};

    int[][] hardest = {
            {8, 6, 7},
            {2, 5, 4},
            {3, 0, 1}};

    ArrayList<Board> startingStates = new ArrayList<>();
    View view = null;
    Board startState = null;
    Algorithm algorithm = null;

    public EightTileSolver(View view) {
        this.view = view;
        startingStates.add(new Board(veryEasyState));
        startingStates.add(new Board(mediumState));
        startingStates.add(new Board(otherMediumState));
        startingStates.add(new Board(hard));
        startingStates.add(new Board(hardest));
    }

    public void play(View view) throws Exception {


        while (true) {
            printBoardOptions();
            startState = assignBoard();
            view.output("Okay, you've selected this starting state: " + startState.toString());
            view.output("Would you like to preform a Breadth First Search [1] or Depth First Search [2]? Enter the corresponding integer: ");
            int algo = assignAlgorithm();
            String algoName = algo == 1 ? "Bread First Search" : "Depth First Search";
            view.output("Okay! Running a " + algoName + " on: " + startState.toString());
            Thread.sleep(2000);
            long start = System.currentTimeMillis();
            if (algorithm instanceof BreadthFirstSearch){
                ((BreadthFirstSearch) algorithm).run(startState);
            } else {
                ((DepthFirstSearch) algorithm).run(startState);
            }
            long end = System.currentTimeMillis();
            view.output("\nSuccess! Goal found after " + (end - start) / 1000.0 + " seconds!");
            Thread.sleep(4000);
            view.output("\n\n");
        }


    }

    public void printBoardOptions(){
        view.output("Welcome to Eight Tile Puzzle Solver! Select one of the following puzzle configurations to run by entering the integer in the brackets after the name: \n You can enter 'quit' at anytime to exit \n\n");
        int i = 0;
        for (States state : States.values()) {
            view.output(state.name() + " [" + i + "]");
            view.output(startingStates.get(i++).toString() + "\n");
        }
    }

    public Board assignBoard() throws Exception {
        while (true) {
            int input = view.intInput();
            if (input >= 0 && input <= 4) {
                return startingStates.get(input);
            } else {
                view.output("Enter an integer between 0 and 4! \n");
            }
        }
    }

    public int assignAlgorithm() throws Exception {
        while(true){
            int input = view.intInput();
            if (input == 1 || input == 2){
                algorithm = input == 1 ? new BreadthFirstSearch() : new DepthFirstSearch();
                return input;
            } else {
                view.output("Enter either 1 or 2! \n");
            }
        }
    }
}

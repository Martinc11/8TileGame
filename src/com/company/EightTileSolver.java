package com.company;

import java.util.ArrayList;
import java.util.TreeSet;

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

    /**
     * Main game loop. Prompts the user to chose a starting state and an algorithm and then displays the result of the run.
     */
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
        view.output("Welcome to Eight Tile Puzzle Solver! Select one of the following puzzle configurations to run by entering the integer in the brackets after the name. Also, by entering 5," +
                "you can create your own board configuration.  \n You can enter 'quit' at anytime to exit \n\n");
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
            } else if (input == 5) {
                return getUserBoard();
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
    public Board getUserBoard() throws Exception {
        while (true) {
            int[] userArray = getIntArray();
            int inversions = 0;
            for (int i = 0; i < userArray.length - 1; i++) {
                for (int j = i + 1; j < 9; j++) {
                    if (userArray[i] > 0 && userArray[j] > 0 && userArray[i] > userArray[j]) {
                        inversions++;
                    }
                }
            }
            if (inversions % 2 == 0) {
                int[][] userBoard = new int[3][3];
                int k = 0;
                for (int i = 0; i < userBoard.length; i++) {
                    for (int j = 0; j < userBoard[i].length; j++) {
                        userBoard[i][j] = userArray[k];
                        k++;
                    }
                }
                Board board = new Board(userBoard);
                return board;
            }
            else {
                System.out.println("The board was an unsolvable board! Please enter a new board.");
            }
        }
    }


    private int[] getIntArray() throws Exception {
        view.output("Enter your board configuration in row major order as one long integer. e.g. enter 123456780 for the board configuration: \n[1,2,3]\n[4,5,6]\n[7,8,0] ");
        while (true){
            int userInt = view.intInput();
            if(String.valueOf(userInt).length() != 9){
                view.output("The integer need to be 9 digits, include one zero (0), and have no repeating digits!");
            } else {
                int[] userArray = new int[9];
                for(int i = 8; i >= 0; i--){
                    int digit = userInt % 10;
                    userArray[i] = digit;
                    userInt = userInt / 10;
                }
                if(checkForUniqueValues(userArray)){
                    return userArray;
                }
            }
        }
    }

    private boolean checkForUniqueValues(int[] userArray){
        TreeSet<Integer> lazyWay = new TreeSet<>();
        boolean hasZero = false;
        for(int i = 0; i < 9; i++){
            if (userArray[i] == 0){
                hasZero = true;
            }
            lazyWay.add(new Integer(userArray[i]));
        }
        if (lazyWay.size() == 9 && hasZero){
            return true;
        }
        return false;
    }
}

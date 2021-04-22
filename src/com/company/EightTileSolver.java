package com.company;

import java.util.ArrayList;
import java.util.TreeSet;

enum States {
    EASY, MEDIUM, HARD, HARDER, HARDEST
}

public class EightTileSolver {

    //board configurations that are provided to the users
    int[][] veryEasyState = { //0 inversions
            {1, 2, 3},
            {4, 5, 6},
            {7, 0, 8}};

    int[][] mediumState = { //4 inversions
            {1, 2, 0},
            {4, 5, 3},
            {7, 8, 6}};

    int[][] otherMediumState = { //10 inversions
            {2, 3, 6},
            {1, 4, 8},
            {7, 5, 0}};

    int[][] hard = { //4 inversions
            {0, 1, 2},
            {4, 5, 3},
            {7, 8, 6}};

    int[][] hardest = { //24 inversions
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

    /**
     * Welcoming "screen" that greets the user and prints the board configurations that are provided.
     */
    public void printBoardOptions() {
        view.output("Welcome to Eight Tile Puzzle Solver! Select one of the following puzzle configurations to run by entering the integer in the brackets after the name. Also, by entering 5," +
                "you can create your own board configuration.  \n You can enter 'quit' at anytime to exit \n\n");
        int i = 0;
        for (States state : States.values()) {
            view.output(state.name() + " [" + i + "]");
            view.output(startingStates.get(i++).toString() + "\n");
        }
    }

    /**
     * Checks for if the user has chosen one of the provided board configurations or chosen to create their own board configuration.
     */
    public Board assignBoard() throws Exception {
        while (true) {
            int input = view.intInput();
            if (input >= 0 && input <= 4) {
                return startingStates.get(input);
            } else if (input == 5) {
                return getUserBoard();
            } else {
                view.output("Enter an integer between 0 and 5! \n");
            }
        }
    }

    /**
     * After the user has chosen their board configurations or created their board configuration, they can either enter a 1 to perform
     * Breadth First Search or enter a 2 to perform a Depth First Search on the board configuration.
     */
    public int assignAlgorithm() throws Exception {
        while(true){
            int input = view.intInput();
            if (input == 1 || input == 2) {
                algorithm = input == 1 ? new BreadthFirstSearch() : new DepthFirstSearch();
                return input;
            } else {
                view.output("Enter either 1 or 2! \n");
            }
        }
    }
    /**
     * After the user enters their board configuration, this checks that the board is solvable and converts the 1d array into a 2d array if it's solvable.
     * A board is solvable if the number of inversions are even. Otherwise, if the number of inversions are odd, then the board is unsolvable.
     */
    public Board getUserBoard() throws Exception {
        while (true) {
            int[] userArray = getIntArray();
            int inversions = 0;
            for (int i = 0; i < userArray.length - 1; i++) {
                for (int j = i + 1; j < userArray.length; j++) {
                    if (userArray[i] > 0 && userArray[j] > 0 && userArray[i] > userArray[j]) { //checks for inversions
                        inversions++;   //increment inversions
                    }
                }
            }
            if (inversions % 2 == 0) { //board is solvable if inversions is an even number
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
            else { //board is unsolvable if inversions is an odd number
                System.out.println("The board was an unsolvable board! Please enter a new board.");
            }
        }
    }

    /**
     * if the user chooses to create their own board configuration, they will be asked to enter an 9 digit integer that contains the digits contains the digits 1, 2, 3, 4, 5, 6, 7, 8, 0..
     * Checks that the integer entered is a valid integer that can be used for a board configuration by checking that the integer has 9 digits, has one zero, and has no repeating digits.
     */
    private int[] getIntArray() throws Exception {
        view.output("Enter your board configuration in row major order as one long integer. e.g. enter 123456780 for the board configuration: \n[1,2,3]\n[4,5,6]\n[7,8,0] ");
        while (true) {
            int userInt = view.intInput();
            if(String.valueOf(userInt).length() != 9) {
                view.output("The integer needs to be 9 digits, include one zero (0), and have no repeating digits!");
            } else {
                int[] userArray = new int[9];
                for(int i = 8; i >= 0; i--) {
                    int digit = userInt % 10;
                    userArray[i] = digit;
                    userInt = userInt / 10;
                }
                if(checkForUniqueValues(userArray)) {
                    return userArray;
                }
                else {
                    System.out.println("The integer needs to no repeating digits and contains the digits 1, 2, 3, 4, 5, 6, 7, 8, 0.");
                }
            }
        }
    }

    /**
     * Ensures that the 9 digit integer entered has no repeating digits and contains the digits 1, 2, 3, 4, 5, 6, 7, 8, 0.
     * A TreeSet doesn't allow duplicate elements to be added. If there are repeating digits, then the duplicate digits will be ignored.
     * Therefore, if the TreeSet doesn't equal 9, then there are repeating digits.
     */
    private boolean checkForUniqueValues(int[] userArray) {
        TreeSet<Integer> lazyWay = new TreeSet<>();
        //boolean hasZero = false;
        for(int i = 0; i < 9; i++) {
            if (userArray[i] == 9) {
                return false;
            }
            lazyWay.add(new Integer(userArray[i]));
        }
        if (lazyWay.size() == 9) {
            return true;
        }
        /*
        for(int i = 0; i < 9; i++) {
            if (userArray[i] == 0) {
                hasZero = true;
            }
            lazyWay.add(new Integer(userArray[i]));
        }
        if (lazyWay.size() == 9 && hasZero) {
            return true;
        }*/
        return false;
    }
}

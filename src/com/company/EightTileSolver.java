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
            Thread.sleep(2000);  // sleep execution because otherwise the user probably won't see the last message
            long start = System.currentTimeMillis();
            if (algorithm instanceof BreadthFirstSearch){
                ((BreadthFirstSearch) algorithm).run(startState);
            } else {
                ((DepthFirstSearch) algorithm).run(startState);
            }
            long end = System.currentTimeMillis();
            view.output("\nSuccess! Goal found after " + (end - start) / 1000.0 + " seconds!");
            Thread.sleep(4000); // same here.
            view.output("\n\n");
        }


    }

    /**
     * Prints the initial pre-made board configurations and instructions.
     */

    public void printBoardOptions(){
        view.output("Welcome to Eight Tile Puzzle Solver! Select one of the following puzzle configurations to run by entering the integer in the brackets after the name. Also, by entering 5, " +
                "you can create your own board configuration.  \n You can enter 'quit' at anytime to exit \n\n");
        int i = 0;
        for (States state : States.values()) {
            view.output(state.name() + " [" + i + "]");
            view.output(startingStates.get(i++).toString() + "\n");
        }
    }

    /**
     * Assigns either a pre-made start state or a user given board configuration to startState in the main loop
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
     * Assigns the algorithm to be used on the board configuration from the user's input
     */
    public int assignAlgorithm() throws Exception {
        while(true){
            int input = view.intInput();
            if (input == 1 || input == 2){
                algorithm = input == 1 ? new BreadthFirstSearch() : new DepthFirstSearch(); // assign the algorithm data field with either a BFS or DFS instance.
                return input;
            } else {
                view.output("Enter either 1 or 2! \n");
            }
        }
    }

    /**
     * If the user enters a valid array, i.e. 9 digits with no duplicate values and 1 zero, then check whether the board is solvable or not.
     * Solvability is based on the number of inversions in the board. For example, if 8 comes before 5 in the array, that's 1 inversion. There needs to be an
     * even number for the board to be solvable.
     * @return
     * @throws Exception
     */
    public Board getUserBoard() throws Exception {
        while (true) {
            int[] userArray = getIntArray();  // the userArray at this point has been checked for number of digits, duplicates, and a 0.
            int inversions = 0;
            for (int i = 0; i < userArray.length - 1; i++) { //loop through user array and count the number of inversions
                for (int j = i + 1; j < 9; j++) {
                    if (userArray[i] > 0 && userArray[j] > 0 && userArray[i] > userArray[j]) {
                        inversions++;
                    }
                }
            }
            if (inversions % 2 == 0) {  // only board with an even number of inversions are solvable.
                int[][] userBoard = new int[3][3];
                int k = 0;
                for (int i = 0; i < userBoard.length; i++) {
                    for (int j = 0; j < userBoard[i].length; j++) {   //populate a 2D array to pass into Board constructor from userArray
                        userBoard[i][j] = userArray[k];
                        k++;
                    }
                }
                Board board = new Board(userBoard);
                return board;
            }
            else {
                view.output("The board was an unsolvable board! Please enter a new board.\n"); //odd number of inversion, so prompt user for new board configuration
            }
        }
    }

    /**
     * Gets the int array from the user, checks it size, and then calls checkForUniqueValues to ensure that there are no duplicates and 1 zero.
     */
    private int[] getIntArray() throws Exception {
        view.output("Enter your board configuration in row major order as one long integer. e.g. enter 123456780 for the board configuration: \n[1, 2, 3]\n[4, 5, 6]\n[7, 8, 0] ");
        while (true){ // loops until the user enters a valid array
            int userInt = view.intInput();
            if(String.valueOf(userInt).length() != 9){
                view.output("The integer need to be 9 digits, include one zero (0), and have no repeating digits! Try again: ");
            } else {
                int[] userArray = new int[9];
                for(int i = 8; i >= 0; i--){ //peel off the last digit of the user entered integer and add it to an array
                    int digit = userInt % 10; //isolates the last digit
                    userArray[i] = digit;
                    userInt = userInt / 10; // divide by 10 to remove the last digit from the number after its been added to the array
                }
                if(checkForUniqueValues(userArray)){
                    return userArray;
                } else {
                    view.output("The integer need to be 9 digits, include one zero (0), and have no repeating digits! Try again: ");
                }
            }
        }
    }

    /**
     * lazy way to make sure the user entered array has a zero and no duplicates. Add all the values to a set, if the set isn't of size 9 after all the values have been added then
     * there was a duplicate.
     */
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

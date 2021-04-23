package com.company;


import java.util.HashSet;
import java.util.Stack;

/**
 * Implementation of the DFS algorithm. Does not use recursion as that would require the whole tree being generated prior to the algorithm running.
 * Instead uses a stack, with each board's children being added to stack if the board is not the goal state. Next iteration pops off and evaluates the child.
 */

public class DepthFirstSearch implements Algorithm {
    HashSet<Board> closed = new HashSet<>(); //HashSet closed is for states of boards that have been used and can't be used again
    int[][] goalState = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 0}};
    Board goalBoard = new Board(goalState);
    Stack<Board> stack = new Stack<>();
    private int numberOfBoards = 0;


    public boolean run(Board state) {

        boolean foundGoal = false;
        stack.push(state);

        while (!foundGoal && !stack.isEmpty()) {

            Board currentBoard = stack.pop();
            System.out.printf("%s    %s%d%s%d\n", currentBoard.toString(), "Current Depth: ", currentBoard.getDepth(), ", Board number: ", numberOfBoards++);
            closed.add(currentBoard);
            if (!currentBoard.equals(goalBoard)) {
                generateChildrenDfs(currentBoard); // the generated children get added to the stack, and then on the next iteration one of the children is evaluated, putting that child's children on the stack and so on.
            } else {
                foundGoal = true;
            }
        }
        return foundGoal;
    }


    private void generateChildrenDfs(Board currentBoard) {

        int[] indexes = currentBoard.findBlankTile();
        int row = indexes[0];
        int col = indexes[1];


        //Slide the blank tile (0) up if possible
        if (row - 1 >= 0) {
            Board clonedBoard = currentBoard.clone();
            int[][] tileArray = clonedBoard.getBoard();
            tileArray[row][col] = tileArray[row - 1][col];
            tileArray[row - 1][col] = 0;
            if (!closed.contains(clonedBoard)) {
                clonedBoard.setDepth(currentBoard.getDepth() + 1);
                stack.push(clonedBoard);
            }
        }

        //Slide the blank tile (0) down if possible
        if (row + 1 <= 2) {
            Board clonedBoard = currentBoard.clone();
            int[][] tileArray = clonedBoard.getBoard();
            tileArray[row][col] = tileArray[row + 1][col];
            tileArray[row + 1][col] = 0;
            if (!closed.contains(clonedBoard)) {
                clonedBoard.setDepth(currentBoard.getDepth() + 1);
                stack.push(clonedBoard);
            }
        }
        // Slide to the left
        if (col - 1 >= 0) {
            Board clonedBoard = currentBoard.clone();
            int[][] tileArray = clonedBoard.getBoard();
            tileArray[row][col] = tileArray[row][col - 1];
            tileArray[row][col - 1] = 0;
            if (!closed.contains(clonedBoard)) {
                clonedBoard.setDepth(currentBoard.getDepth() + 1);
                stack.push(clonedBoard);
            }
        }
        // Slide to the right
        if (col + 1 <= 2) {
            Board clonedBoard = currentBoard.clone();
            int[][] tileArray = clonedBoard.getBoard();
            tileArray[row][col] = tileArray[row][col + 1];
            tileArray[row][col + 1] = 0;
            if (!closed.contains(clonedBoard)) {
                clonedBoard.setDepth(currentBoard.getDepth() + 1);
                stack.push(clonedBoard);
            }
        }
    }
}

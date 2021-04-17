package com.company;

import java.util.*;

/**
 * Class for Breadth First Search. Note that a hashset and a queue are used for an open list and are somewhat redundant. We wanted to use
 * a hashset for checking if a generated child is already on the open or closed list, because the size of the tree can become
 * quite large and a hashset provides roughly O(1) performance on contains operations. The hashset however does not have a "first"
 * element so the queue allows for that, and then we can remove the Board retrieved from the Queue and use it to remove itself from the hashset.
 */

public class BreadthFirstSearch implements Algorithm {
    Queue<Board> queue = new LinkedList<>();
    HashSet<Board> closed = new HashSet<>();
    HashSet<Board> open = new HashSet<>();


    int[][] goalSequence = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 0}};

    Board goalBoard = new Board(goalSequence);


    public boolean run(Board startState) {

        //startState.setVisited(true);
        System.out.println(startState.toString());
        queue.add(startState);
        closed.add(startState);
        boolean foundGoal = false;

        while (!foundGoal && !queue.isEmpty()) {

            Board currentBoard = queue.remove();  // get the next item from the queue, and then remove it from the Open set.
            open.remove(currentBoard);

            // Just so we know the program is doing something.
            System.out.printf("%s    %s%d\n", currentBoard.toString(), "Current Depth: ", currentBoard.getDepth());


            // get the first state from the queue if the current board isn't the goal
            if (!currentBoard.equals(goalBoard)) {
                generateChildBfs(currentBoard);  // generate child.
                closed.add(currentBoard);
            } else {
                foundGoal = true;

            }
        }
        return foundGoal;
    }


    /**
     * This generates all possible children of the current board, and adds them to the open set if the generated child
     * has not been previously evaluated i.e. it is on the closed set. It also adds them to the queue, which is the main data structure used for
     * the BFS loop.
     *
     * @param currentBoard
     */
    private void generateChildBfs(Board currentBoard) {
        int[] indexs = currentBoard.findBlankTile();
        int x = indexs[0];
        int y = indexs[1];

        //Slide the blank tile (0) up if possible
        if (x - 1 >= 0) {  //prevent index out of bounds
            Board newBoard = currentBoard.clone();
            int[][] tileArray = newBoard.getBoard();
            tileArray[x][y] = tileArray[x - 1][y];
            tileArray[x - 1][y] = 0;
            if (hasNotBeenVisited(newBoard)) {
                newBoard.setDepth(currentBoard.getDepth() + 1);
                open.add(newBoard);
                queue.add(newBoard);
            }
        }

        //Slide the blank tile (0) down if possible
        if (x + 1 <= 2) {
            Board newBoard = currentBoard.clone();
            int[][] tileArray = newBoard.getBoard();
            tileArray[x][y] = tileArray[x + 1][y];
            tileArray[x + 1][y] = 0;
            if (hasNotBeenVisited(newBoard)) {
                newBoard.setDepth(currentBoard.getDepth() + 1);
                open.add(newBoard);
                queue.add(newBoard);
            }
        }


        // Slide to the left
        if (y - 1 >= 0) {
            Board newBoard = currentBoard.clone();
            int[][] tileArray = newBoard.getBoard();
            tileArray[x][y] = tileArray[x][y - 1];
            tileArray[x][y - 1] = 0;
            if (hasNotBeenVisited(newBoard)) {
                newBoard.setDepth(currentBoard.getDepth() + 1);
                open.add(newBoard);
                queue.add(newBoard);
            }
        }

        // Slide to the right
        if (y + 1 <= 2) {
            Board newBoard = currentBoard.clone();
            int[][] tileArray = newBoard.getBoard();
            tileArray[x][y] = tileArray[x][y + 1];
            tileArray[x][y + 1] = 0;
            if (hasNotBeenVisited(newBoard)) {
                newBoard.setDepth(currentBoard.getDepth() + 1);
                open.add(newBoard);
                queue.add(newBoard);
            }
        }
    }


    private boolean hasNotBeenVisited(Board board) {
        return (!open.contains(board) && !closed.contains(board));
    }
}
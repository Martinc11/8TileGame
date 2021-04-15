package com.company;

import java.util.*;

public class BreadthFirstSearch {
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

            Board currentBoard = queue.remove();
            open.remove(currentBoard);

            if(currentBoard.getDepth() % 5 == 0){  // Just so we know the program is doing something.
                System.out.printf("%s    %s%d", currentBoard.toString(), "Current Depth: ", currentBoard.getDepth());
            }

            // get the first state from the queue
            if (!currentBoard.equals(goalBoard)){
                generateChildBfs(currentBoard);  // generate child.
                closed.add(currentBoard);
            } else {
                foundGoal = true;
            }

        }


        return false;
    }


    private void generateChildBfs(Board currentBoard) {
        int[] indexs = currentBoard.findBlankTile();
        int x  = indexs[0];
        int y = indexs[1];

        //Slide the blank tile (0) up if possible
        if ( x - 1 >= 0 ){  //prevent index out of bounds
            Board newBoard = currentBoard.clone();
            int[][] tileArray =  newBoard.getBoard();
            tileArray[x][y] = tileArray[x-1][y];
            tileArray[x-1][y] = 0;
            if(hasNotBeenVisited(newBoard)){
                newBoard.setDepth(currentBoard.getDepth() + 1);
                open.add(newBoard);
                queue.add(newBoard);
            }
        }

        //Slide the blank tile (0) down if possible
        if ( x + 1 <= 2 ) {
            Board newBoard = currentBoard.clone();
            int[][] tileArray =  newBoard.getBoard();
            tileArray[x][y] = tileArray[x+1][y];
            tileArray[x+1][y] = 0;
            if(hasNotBeenVisited(newBoard)){
                newBoard.setDepth(currentBoard.getDepth() + 1);
                open.add(newBoard);
                queue.add(newBoard);
            }
        }
        
        
        // Slide to the left
        if (y - 1 >= 0){
            Board newBoard = currentBoard.clone();
            int[][] tileArray =  newBoard.getBoard();
            tileArray[x][y] = tileArray[x][y - 1];
            tileArray[x][y - 1] = 0;
            if(hasNotBeenVisited(newBoard)){
                newBoard.setDepth(currentBoard.getDepth() + 1);
                open.add(newBoard);
                queue.add(newBoard);
            }
        }

        // Slide to the right
        if (y + 1 <= 2) {  
            Board newBoard = currentBoard.clone();
            int[][] tileArray =  newBoard.getBoard();
            tileArray[x][y] = tileArray[x][y + 1];
            tileArray[x][y + 1] = 0;
            if(hasNotBeenVisited(newBoard)){
                newBoard.setDepth(currentBoard.getDepth() + 1);
                open.add(newBoard);
                queue.add(newBoard);
            }
        }
    }


    private boolean hasNotBeenVisited(Board board){
        return (!open.contains(board) && !closed.contains(board));
    }
}
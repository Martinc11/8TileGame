package com.company;
import java.util.ArrayList;
import java.util.HashSet;

public class DepthFirstSearch {
    HashSet<Board> closed = new HashSet<>();
    int[][] goalState = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 0}};
    Board goalBoard = new Board(goalState);
    boolean goalBoardFound = false;

    public boolean depthFirstSearch(Board state) {
        if (state.equals(goalBoard)) {
            return true;
        }
        closed.add(state);
        ArrayList<Board> children = generateChildrenDfs(state);
        for (int i = 0; i < children.size(); i++) {
            depthFirstSearch(children.get(i));
        }
        if(goalBoard.equals(state)) {
            goalBoardFound = true;
        }

        return goalBoardFound;
    }

    private ArrayList<Board> generateChildrenDfs(Board currentBoard) {
        ArrayList<Board> children = new ArrayList<>();
        int[] indexes = currentBoard.findBlankTile();
        int row  = indexes[0];
        int col = indexes[1];

        //Slide the blank tile (0) up if possible
        if ( row - 1 >= 0 ) {
            Board clonedBoard = currentBoard.clone();
            int[][] tileArray = clonedBoard.getBoard();
            tileArray[row][col] = tileArray[row - 1][col];
            tileArray[row + 1][col] = 0;
            if(!closed.contains(clonedBoard)) {
                clonedBoard.setDepth(currentBoard.getDepth() + 1);
                children.add(clonedBoard);
            }
        }

        //Slide the blank tile (0) down if possible
        if ( row + 1 <= 2 ) {
            Board clonedBoard = currentBoard.clone();
            int[][] tileArray = clonedBoard.getBoard();
            tileArray[row][col] = tileArray[row + 1][col];
            tileArray[row + 1][col] = 0;
            if(!closed.contains(clonedBoard)){
                clonedBoard.setDepth(currentBoard.getDepth() + 1);
                children.add(clonedBoard);
            }
        }
        // Slide to the left
        if (col - 1 >= 0){
            Board clonedBoard = currentBoard.clone();
            int[][] tileArray = clonedBoard.getBoard();
            tileArray[row][col] = tileArray[row][col - 1];
            tileArray[row][col - 1] = 0;
            if(!closed.contains(clonedBoard)){
                clonedBoard.setDepth(currentBoard.getDepth() + 1);
                children.add(clonedBoard);
            }
        }

        // Slide to the right
        if (col + 1 <= 2) {
            Board clonedBoard = currentBoard.clone();
            int[][] tileArray =  clonedBoard.getBoard();
            tileArray[row][col] = tileArray[row][col + 1];
            tileArray[row][col + 1] = 0;
            if(!closed.contains(clonedBoard)){
                clonedBoard.setDepth(currentBoard.getDepth() + 1);
                children.add(clonedBoard);
            }
        }

        return children;
    }


}

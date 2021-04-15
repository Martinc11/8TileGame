package com.company;

import java.util.*;

public class BreadthFirstSearch {
    Queue<Board> queue = new LinkedList<>();
    HashSet<Board> closed = new HashSet<>();
    HashSet<Board> open = new HashSet<>();


    public boolean breadthFirstSearch(Board startState) {

        //startState.setVisited(true);
        System.out.println(startState.toString());
        queue.add(startState);
        closed.add(startState);

        while (!queue.isEmpty()) {
            Board currentBoard = queue.remove();
            generateChildBfs(currentBoard);
            closed.add(currentBoard);


        }


        return true;
    }


    private Board generateChildBfs(Board currentBoard) {
        int[] indexs = currentBoard.findBlankTile();
        int x  = indexs[0];
        int y = indexs[1];

        //Slide the blank tile (0) up if possible
        if ( x - 1 >= 0 ){
            Board newBoard = currentBoard.clone();
            int[][] tileArray =  newBoard.getBoard();
            tileArray[x][y] = tileArray[x-1][y];
            tileArray[x+1][y] = 0;
            if(!checkIfVisited(newBoard)){
                newBoard.setDepth(currentBoard.getDepth() + 1);
                open.add(newBoard);
            }
        }

        //Slide the blank tile (0) down if possible
        if ( x + 1 <= 2 ) {
            Board newBoard = currentBoard.clone();
            int[][] tileArray =  newBoard.getBoard();
            tileArray[x][y] = tileArray[x+1][y];
            tileArray[x+1][y] = 0;
            if(!checkIfVisited(newBoard)){
                newBoard.setDepth(currentBoard.getDepth() + 1);
                open.add(newBoard);
            }
        }






        return null;
    }


    private boolean checkIfVisited(Board board){
        return !(open.contains(board) || closed.contains(board));
    }
}
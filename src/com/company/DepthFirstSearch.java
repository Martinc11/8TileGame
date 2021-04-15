package com.company;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;
public class DepthFirstSearch {
    Stack<Board> stack = new Stack<>();
    HashSet<Board> closed = new HashSet<>();
    int[][] goalState = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 0}};
    Board goalBoard = new Board(goalState);
    public boolean depthFirstSearch(Board state) {
        if (state.equals(goalBoard)) {
            return true;
        }
        closed.add(state);
        ArrayList<Board> children = generateChildrenDfs(state);
        for (int i = 0; i < children.size(); i++) {
            depthFirstSearch(children.get(i));
        }

        return true;
    }

    private ArrayList<Board> generateChildrenDfs(Board currentBoard) {
        ArrayList<Board> children = new ArrayList<>();
        int[] indexs = currentBoard.findBlankTile();
        int x  = indexs[0];
        int y = indexs[1];

        //Slide the blank tile (0) up if possible
        if ( x - 1 >= 0 ){
            Board newBoard = currentBoard.clone();
            int[][] tileArray =  newBoard.getBoard();
            tileArray[x][y] = tileArray[x-1][y];
            tileArray[x+1][y] = 0;
            if(!closed.contains(newBoard)){
                newBoard.setDepth(currentBoard.getDepth() + 1);
                children.add(newBoard);
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





}

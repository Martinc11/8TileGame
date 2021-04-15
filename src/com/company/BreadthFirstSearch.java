package com.company;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch {
    Queue<Board> queue = new LinkedList<>();
    HashSet<Board> closed = new HashSet<>();
    HashSet<Board> open = new HashSet<>();


    public boolean breadthFirstSearch(Board startState) {

        startState.setVisited(true);
        System.out.println(startState.toString());
        queue.add(startState);
        closed.add(startState);

        while (!queue.isEmpty()) {
            Board currentBoard = queue.remove();
            generateChildBfs(currentBoard);


        }


        return false;
    }


    private Board generateChildBfs(Board currentBoard) {


        return null;
    }

    //Method to find the blank tile (0) on the current board
    private int[] findBlankTile(Board currentBoard) {
        int[] indexes = new int[2]; //the index where the blank tile is

        //iterate through the board to find the blank tile
        for (int i = 0; i < currentBoard.getBoard().length; i++) {
            for (int j = 0; j < currentBoard.getBoard()[i].length; j++) {
                if (currentBoard.getBoard()[i][j] == 0) { //if blank tile is found
                    indexes[0] = i;
                    indexes[1] = j;
                    return indexes; //return the indexes where the blank tile is
                }
            }
        }
        return null;
    }
}
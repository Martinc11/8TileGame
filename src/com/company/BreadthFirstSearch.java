package com.company;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch {
    Queue<Board> queue = new LinkedList<>();
    ArrayList<Board> closed = new ArrayList<>();

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

    private int[] findBlankTile() {
        return null;
    }
}
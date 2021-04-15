package com.company;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static void main(String[] args) {

        int[][] startState = {
                {2, 3, 6},
                {1, 4, 8},
                {7, 5, 0}};

        Board board = new Board(startState);

        int[][] goalState = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 0}};

        System.out.println(board.toString());

    }


    private boolean breadthFirstSearch(Board startState){
        Queue<Board> queue = new LinkedList<>();
        ArrayList<Board> closed = new ArrayList<>();
        startState.setVisited(true);
        System.out.println(startState.toString());
        queue.add(startState);
        closed.add(startState);

        while(!queue.isEmpty()){

        }





        return false;
    }


    private int[][] generateChild(){
        return null;
    }
}

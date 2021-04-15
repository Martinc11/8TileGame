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

        int[][] veryEasyState = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 0, 8}};



        Board board = new Board(veryEasyState);

        BreadthFirstSearch Bfs = new BreadthFirstSearch();
        Bfs.run(board);




    }

}

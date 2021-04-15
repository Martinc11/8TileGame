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

        Board board2 = new Board(goalState);

        int[][] testState = {
                {2, 3, 6},
                {1, 4, 8},
                {7, 5, 0}};

        Board board3 = new Board(testState);

        /*System.out.println(board.toString());
        System.out.println(board.equals(board2));
        System.out.println(board.equals(board3));
        System.out.println(board.hashCode());
        System.out.println(board3.hashCode());
        System.out.println(board2.hashCode());*/

        Board clonedBoard = board.clone();
        System.out.println(board.hashCode());
        System.out.println(clonedBoard.hashCode());
        System.out.println(board.equals(clonedBoard));


    }

}

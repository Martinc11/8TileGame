package com.company;

import java.util.Arrays;

public class Board {

    int[][] board = null;
    boolean visited = false;

    public Board(int[][] tiles){
        this.board = tiles;
    }


    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public boolean isVisited() {
        return visited;
    }

    @Override
    public String toString() {
        return "Board{" +
                "board=" + Arrays.toString(board) +
                '}';
    }
}

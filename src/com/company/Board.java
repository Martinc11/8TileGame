package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {

    int[][] board;
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
        StringBuilder sb = new StringBuilder();
        for(int[] s1 : board) {
            sb.append(Arrays.toString(s1)).append('\n');
        }
        return sb.toString();
    }
}

package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board1 = (Board) o;
        for(int i = 0; i < 3; i++){
            int[] subArray1 = this.board[i];
            int[] subArray2 = board1.board[i];
            if(!Arrays.deepEquals(board, board1.board)){
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(board);
    }

    public int[][] getBoard() {
        return board;
    }
}

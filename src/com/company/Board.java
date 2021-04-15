package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Board implements Cloneable {

    int[][] board;

    int depth = 0;

    public Board(int[][] tiles){
        this.board = tiles;
    }

    public Board(int[][] tiles, int depth) {
        this.depth = depth;
        this.board = tiles;
    }


    //Method to find the blank tile (0) on the current board
    public int[] findBlankTile() {
        int[] indexes = new int[2]; //the index where the blank tile is

        //iterate through the board to find the blank tile
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 0) { //if blank tile is found
                    indexes[0] = i;
                    indexes[1] = j;
                    return indexes; //return the indexes where the blank tile is
                }
            }
        }
        return null;
    }



    public void setBoard(int[][] board) {
        this.board = board;
    }

    public int[][] getBoard() {
        return board;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getDepth() {
        return depth;
    }

    /**
     * Override equals so its comparing sub arrays too.
     */
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

    /**
     * Override hashCode to use Hashset in BFS class
     * @return
     */
    @Override
    public int hashCode() {
        return Arrays.deepHashCode(board);
    }



    @Override
    public Board clone() {
        if (board == null) {
            return null;
        }

        int[][] result = new int[board.length][];
        for (int i = 0; i < board.length; i++) {
            result[i] = Arrays.copyOf(board[i], board[i].length);
        }
        return new Board(result, depth);
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

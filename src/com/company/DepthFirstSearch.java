package com.company;
import java.util.HashSet;
import java.util.Stack;
public class DepthFirstSearch {
    HashSet<Board> closed = new HashSet<>();

    public boolean depthFirstSearch(Board startState) {
        //int depth = startState.depth;
        if (depth == 0) {
            return false;
        }

        int j = startState.getBoard()[0].length;
        Stack<Board> stack = new Stack<>();

        stack.push(startState);
        return true;
    }





}

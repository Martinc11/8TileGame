package com.company;

import java.util.Scanner;

/**
 * Handles getting input from the user and displaying a message on the console. Input for this program should be either an integer or "quit".
 * If "quit" is entered, an exception is propagated back to main() which exits the execution.
 */

public class View {

    public void output(String message) {
        System.out.print(message);
    }

    public Integer intInput () throws Exception {

        Scanner input = new Scanner(System.in);
        String userInput = input.next();

        boolean isInt = false;
        while (!isInt) {
            try {
                if (userInput.toLowerCase().equals("quit") ) {
                    throw new Exception();
                }
                Integer.parseInt(userInput);
                isInt = true;
            } catch (NumberFormatException e) {
                output("Entry must be an integer, try again: ");
                userInput = input.next();
            }
        }

        return Integer.parseInt(userInput);
    }
}


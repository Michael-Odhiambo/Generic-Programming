/**
 * In mathematics, several operations are defined on sets. The union of two sets A and B is
 * a set that contains all the elements that are in A together with all the elements that are in
 * B. The intersection of A and B is the set that contains elements that are in both A and B.
 * The difference of A and B is the set that contains all the elements of A except for those elements
 * that are also in B
 *
 * Suppose that A and B are variables of type set in Java. The mathematical operations on A and B can
 * be computed using methods from the Set interface. In particular: A.addAll(B) computes the union of
 * A and B; A.retainAll(B) computes the intersection of A and B; and A.removeAll(B) computes the difference
 * of A and B. ( These operations change the contents of the set A, while the mathematical operations
 * create a new set without changing A, but that difference is not relevant to this exercise. )
 *
 * For this exercise, you should write a program that can be used as a set "set calculator" for simple
 * operations on sets of non-negative integers. ( Negative integers are not allowed. ) For input and output,
 * a set of such integers will be written as a list of integers, separated by commas and, optionally, spaces
 * and enclosed in square brackets. For example: [1, 2, 3] or [17, 42, 9, 53, 108]. The characters +, *, and -
 * will be used for the union, intersection, and difference operations. The user of the program will type in
 * lines of input containing two sets, separated by an operator. The program should perform the operation and
 * print the resulting set.
 *
 * To represent sets of non-negative integers, use sets of type TreeSet<Integer>. Read the user's input, create
 * two TreeSets, and use the appropriate TreeSet method to perform the requested operation on the two sets. Your
 * program should be able to read and process any number of lines of input. If a line contains a syntax error,
 * your program should not crash. It should report the error and move on to the next line of input. ( Note: To
 * print out a Set, A, of Integers, you can just say System.out.print(A). I've chosen the syntax for sets to be the
 * same as that used by the system for outputting a set.
 */

import java.util.TreeSet;

public class SetCalculator {

    public static void main( String[] args ) {

        System.out.println( "This program will compute union, intersection and " );
        System.out.println( "set difference of sets of integers." );
        System.out.println("");
        System.out.println("");
        System.out.println( "Enter set computations ( press return to end )" );

        while ( true ) {

            System.out.println( "\n?" );
            TextIO.skipBlanks();

            if ( TextIO.peek() == '\n' ) {
                // The input line is empty.
                // Exit the loop and end the program.
                break;
            }
            try {
                calc();  // Reads and processes on line of input.
            }
            catch ( IllegalArgumentException e ) {
                // An error was found in the input line.
                System.out.println( "Error in input: " + e.getMessage() );

            }
            TextIO.getln();  // Read and discard the rest of the line. If there was no error,
                             // the only thing that is discarded is the end-of-line character.
        }
    }

    /**
     * Read a line of input, consisting of two sets separated by an operator. Perform
     * the operation and output the value. If any syntax error is found in the input, an
     * IllegalArgumentException is thrown.
     */
    private static void calc() {

        TreeSet<Integer> A, B;  // The two sets of integers.
        char op;                // The operator, +, *, or -.

        A = readSet();  // Read the first set.

        TextIO.skipBlanks();

        if ( TextIO.peek() != '*' && TextIO.peek() != '+' && TextIO.peek() != '-' )
            throw new IllegalArgumentException( "Expected *, +, or - after first set." );

        op = TextIO.getAnyChar();  // Read the operator.

        B = readSet();  // Read the second set.

        TextIO.skipBlanks();

        if ( TextIO.peek() != '\n' )
            throw new IllegalArgumentException( "Extra unexpected input." );

        /*
        Perform the operation. This modifies set A to represent the answer. Display the answer
        by printing out A. The output format for a set of integers is the same at the input format
        used in this program.
         */
        if ( op == '+' )
            A.addAll(B);
        else if ( op == '*' )
            A.retainAll(B);
        else
            A.removeAll(B);

        System.out.print( "Value:  " + A );
    }

    /**
     * Reads a set of non-negative integers from standard input, and stores them in a TreeSet that contains
     * objects belonging to the wrapper class Integer. The set must be enclosed between square brackets and
     * must contain a list of zero or more non-negative integers, separated by commas. Spaces are allowed anywhere.
     * If the input is not of the correct form, an IllegalArgumentException is thrown.
     */
    private static TreeSet<Integer> readSet() {

        TreeSet<Integer> set = new TreeSet<>();

        TextIO.skipBlanks();

        if ( TextIO.peek() != '[' )
            throw new IllegalArgumentException( "Expected '[' at start of set." );
        TextIO.getAnyChar();  // Read the '['

        TextIO.skipBlanks();
        if ( TextIO.peek() == ']' ) {
            // The set has no integers. This is the empty set, which is legal. Return the value.
            TextIO.getAnyChar();  // Read the ']'
            return set;
        }
        while ( true ) {
            // Read the next integer and add it to the set.
            TextIO.skipBlanks();
            if ( !Character.isDigit( TextIO.peek() ) )
                throw new IllegalArgumentException( "Expected an integer." );

            int n = TextIO.getInt();  // Read the integer.
            set.add( Integer.valueOf(n) );
            TextIO.skipBlanks();

            if ( TextIO.peek() == ']' )
                break;  // ']' marks the end of the set.
            else if ( TextIO.peek() == ',' )
                TextIO.getAnyChar();  // Read a comma and continue.
            else
                throw new IllegalArgumentException( "Expected ',' or ']'." );
        }
        TextIO.getAnyChar();  // Read the ']' that ended the set.

        return set;

    }
}

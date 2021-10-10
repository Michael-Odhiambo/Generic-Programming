
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;
import java.util.Comparator;


/**
 * This program will read words from an input file, and count the number of occurences
 * of each word. The word data is written to an output file twice, once with the words
 * in alphabetical order and once with the words ordered by number of occurences. The
 * user specifies the input file and the output file.
 *
 * The program demonstrates several parts of Java's framework for generic programming:
 * TreeMap, List sorting, Comparators, etc.
 */
public class WordCount {

    /**
     * Represents the data we need about a word: the word and the number of times it has
     * been encountered.
     */
    private static class WordData {
        String word;
        int count;

        // Constructor for creating a WordData object when we encounter a new word.
        WordData( String word ) {
            this.word = word;
            count = 1;  // The initial value of count is 1.
        }
    }

    public static void main( String[] args ) {

        System.out.println( "\n\nThis program will ask you to select an input file." );
        System.out.println( "It will make a list of all the words that occur in the file " );
        System.out.println( "along with the number of times that each word occurs. " );
        System.out.println( "This list will be output twice, first in alphabetical order, then " );
        System.out.println( "in order of frequency of occurrence with the most common word " );
        System.out.println( "at the top and the least common at the end. " );
        System.out.println( "After reading the input file, the program asks you to select an " );
        System.out.println( "output file. If you select a file, the list of words will be written to " );
        System.out.println( "that file; if you cancel, the list will be written to standard output." );
        System.out.println( "All words are converted to lower case. \n\n" );
        System.out.print( "Press return to begin." );

        TextIO.getln();  // Wait for the user to press return.

        try {
            if ( TextIO.readUserSelectedFile() == false ) {
                System.out.println( "No input file selected. Exiting." );
                System.exit(1);
            }

            // Create a TreeMap to hold the data. Read the file and record data in the map about the
            // words that are found in the file.
            TreeMap<String, WordData> words = new TreeMap<>();
            String word = readNextWord();

            while ( word != null ) {
                word = word.toLowerCase();  // Convert word to lower case.
                WordData data = words.get( word );

                if ( data == null )
                    words.put( word, new WordData( word ) );
                else
                    data.count++;
                word = readNextWord();
            }
            System.out.println( "Number of different words found in file: " + words.size() );
            System.out.println();

            if ( words.size() == 0 ) {
                System.out.println( "No words found in file." );
                System.out.println( "Exiting without saving data." );
                System.exit(0);
            }

            // Copy the word data into an array list, and sort the list into order of decreasing frequency,
            // using a lambda expression for the Comparator that will be used in sorting.
            ArrayList<WordData> wordsByFrequency = new ArrayList<>( words.values() );
            Collections.sort( wordsByFrequency, ( a, b ) -> b.count - a.count );

            // Output the data from the map and from the list.
            TextIO.writeUserSelectedFile();  // If user cancels, output automatically goes to standard output.

            TextIO.putln( words.size() + " words found in file: \n" );
            TextIO.putln( "List of words in alphabetical order ( with counts in parentheses ): \n" );

            for ( WordData data : words.values() )
                TextIO.putln( "  " + data.word + " ( " + data.count + " )" );

            TextIO.putln( "\n\nList of words by frequency of occurrence: \n" );
            for ( WordData data : wordsByFrequency )
                TextIO.putln( "  " + data.word + " ( " + data.count + " ) " );

            System.out.println( "\n\nDone.\n\n" );
        }
        catch ( Exception e ) {
            System.out.println( "Sorry, an error has occurred." );
            System.out.println( "Error message: " + e.getMessage() );
            e.printStackTrace();
        }
        System.exit(0);  // Might be necessary, because of use of file dialogs.
    }

    /**
     * Read the next word from TextIO, if there is one. First, skip past any non-letter in the input.
     * If an end-of-file is encountered before a word is found, return null. Otherwise, read and return
     * the word. A word is defined as a sequence of letters. Also, a word can include an apostrophe if
     * the apostrophe is surrounded by letters on each side.
     * @return the next word from TextIO, or null if an end-of-file is encountered.
     */
    private static String readNextWord() {
        TextIO.skipBlanks();

        char ch = TextIO.peek();  // Look at the next character in input.

        while ( ch != TextIO.EOF && !Character.isLetter( ch ) ) {
            TextIO.getAnyChar();  // Read the character.
            ch = TextIO.peek();   // Look at the next character.


        }
        if ( ch == TextIO.EOF )  // Encountered end-of-file.
            return null;

        // At this point, we know that the next character is a letter, so read a word.
        String word = "";  // This will be the word that is read.

        while ( true ) {
            word += TextIO.getAnyChar();  // Append the letter onto word.
            ch = TextIO.peek();  // Look at next character.

            if ( ch == '\'' ) {
                /*
                The next character is an apostrophe. Read it, ad if the following character
                is a letter, add both the apostrophe and the letter onto the word and continue
                reading the word. If the character after the apostrophe is not a letter, the word
                is done, so break out of the loop.
                 */
                TextIO.getAnyChar();  // Read the apostrophe.
                ch = TextIO.peek();   // Look at the char that follows the apostrophe.

                if ( Character.isLetter( ch ) ) {
                    word += "\'" + TextIO.getAnyChar();
                    ch = TextIO.peek();

                }
                else
                    break;
            }
            if ( !Character.isLetter( ch ) ) {
                // If the next character is not a letter, the word is finished, so break out of the loop.
                break;
            }
            // If we haven't broken out of the loop, next char is a letter.
        }
        return word;
    }
}

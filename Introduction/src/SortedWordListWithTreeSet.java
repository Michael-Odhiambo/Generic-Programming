
import java.util.TreeSet;

/**
 * This program makes an alphabetical list of all the words in a file selected
 * by the user. All words are converted to lowercase. The list of words can be written
 * to a file. The words from the input file are stored in a TreeSet, which means that
 * duplicates are removed and the words are output in alphabetical order.
 */
public class SortedWordListWithTreeSet {

    public static void main( String[] args ) {

        System.out.println( "\n\nThis program will ask you to select an input file." );
        System.out.println( "It will read that file and make an alphabetical list of all the" );
        System.out.println( "words in the file. After reading the file, the program asks" );
        System.out.println( "you to select an output file. If you select a file, the list of words" );
        System.out.println( "will be written to that file; if you cancel, the list will be written" );
        System.out.println( "to standard output. Words are converted to lower case, and duplicates" );
        System.out.println( "are eliminated from the list.\n\n" );

        System.out.print( "Press return to begin." );
        TextIO.getln();  // Wait for the user to press return.

        try {
            if ( TextIO.readUserSelectedFile() == false ) {
                System.out.println( "No input file selected. Exiting." );
                System.exit( 1 );

            }

            TreeSet<String> wordSet = new TreeSet<>();  // The words from the file.
            String word = readNextWord();

            while ( word != null ) {
                // word == null when the end of file is reached.
                word = word.toLowerCase();  // Convert word to lower case.
                wordSet.add( word );  // Adds word to set only if it is not already present.

                word = readNextWord();

            }

            System.out.println( "Number of different words found in file: " + wordSet.size() );
            System.out.println();

            if ( wordSet.size() == 0 ) {
                System.out.println( "No words found in file." );
                System.out.println( "Exiting without saving data." );
                System.exit(0);

            }
            TextIO.writeUserSelectedFile();  // If user cancels, output automatically goes to standard output.
            TextIO.putln( wordSet.size() + " words found in file: \n" );
            for ( String w : wordSet )
                TextIO.putln( "  " + w );  // Words are output in sorted order.

            System.out.println( "\n\nDone.\n\n" );

        }
        catch ( Exception e ) {
            System.out.println( "Sorry, an error has occurred." );
            System.out.println( "Error message: " + e.getMessage() );

        }
        System.exit(0);  // Might be necessary because of file dialogs.
    }



    /**
     * Read the next word from TextIO, if there is one. First, skip past
     * any non-letters in the input. If an end-of-file is encountered before a word
     * is found, return null. Otherwise, read and return the word. A word is defined
     * as a sequence of letters. Also, a word can include an apostrophe if the
     * apostrophe is surrounded by letters on each side.
     * @return the next word from TextIO, or null if an end-of-file is encountered.
     */
    private static String readNextWord() {

        char ch = TextIO.peek();  // Look at the next character in input.

        while ( ch != TextIO.EOF && !Character.isLetter( ch ) ) {
            TextIO.getAnyChar();  // Read the character.
            ch = TextIO.peek();
        }
        if ( ch == TextIO.EOF )  // Encountered end-of-file.
            return null;

        // At this point, we know that the next character is a letter, so read a word.
        String word = "";  // This will be the word that is read.

        while ( true ) {
            word += TextIO.getAnyChar();  // Append the letter onto word.
            ch = TextIO.peek();  // Look at the next character.

            if ( ch == '\'') {
                // The next character is an apostrophe. Read it, and if the following
                // character is a letter, add both the Apostrophe and the letter onto the
                // word and continue reading the word. If the character after the apostrophe
                // is not a letter, the word is done, so break out of the loop.
                word += TextIO.getAnyChar();  // Read the apostrophe.
                ch = TextIO.peek();  // Look at the character that follows apostrophe.

                if ( Character.isLetter( ch ) ) {
                    word += TextIO.getAnyChar();
                    ch = TextIO.peek();  // Look at the next char.

                }
                else
                    break;

            }
            if ( !Character.isLetter( ch ) ) {
                // If the next character is not a letter, the word is finished, so break out of the loop.
                break;
            }
            // We haven't broken out of the loop, so the next character is a letter.

        }
        return word;
    }
}

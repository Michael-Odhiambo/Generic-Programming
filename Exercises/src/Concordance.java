
import java.util.TreeSet;
import java.util.Comparator;

public class Concordance {

    private static int currentLine = 0;

    public static void main( String[] args ) {

        System.out.println( "Press return to begin." );
        TextIO.getln();  // Wait for the user to press return.

        try {
            if ( TextIO.readUserSelectedFile() == false ) {
                System.out.println( "No input file selected. Exiting." );
                System.exit( 0 );
            }

            TreeSet<wordInfo> wordSet = new TreeSet<>( Comparator.comparing( s -> s.word ) );
            String word = readNextWord();

            while ( word != null ) {
                word = word.toLowerCase();
                // Exclude words with length less than three and the word "the".
                if ( word.length() > 3 && !( word == "the" ) ) {
                    if ( wordSet.contains( word ) ) {
                        wordInfo theWord = wordSet.
                    }
                }

                word = readNextWord();

            }

            System.out.println( "Number of different words found in file: " + wordSet.size() );
            System.out.println();

            if ( wordSet.size() == 0 ) {
                System.out.println( "No words found in file." );
                System.out.println( "Exiting without saving data." );
                System.exit(0);
            }

            TextIO.writeUserSelectedFile();  // If the user cancels, output automatically goes to standard output.
            TextIO.putln( wordSet.size() + " words found in file: \n" );

            for ( wordInfo theWord : wordSet ) {
                System.out.print( theWord.word + " " );
                for ( int i : theWord.linesWordAppearsOn )
                    System.out.print( i + ", " );

                System.out.println();

            }

            System.out.println( "\n\nDone.\n\n" );
        }
        catch ( Exception e ) {
            System.out.println( "Sorry, an error occurred." );
            System.out.println( "Error message: " + e.getMessage() );
        }
        System.exit( 0 );  // Might be necessary because of file dialogs.

    }

    /**
     * This method reads a single line from the input file.
     */
    private static String readNextWord() {
        TextIO.skipBlanks();

        char ch = TextIO.peek();

        while ( ch != TextIO.EOF && !Character.isLetter( ch ) ) {
            TextIO.getAnyChar();
            ch = TextIO.peek();
        }

        if ( ch == TextIO.EOF )
            return null;

        String word = "";  // At this point, we are sure that the next character is a letter.

        while ( true ) {
            word += TextIO.getAnyChar();
            ch = TextIO.peek();

            if ( ch == '\'' ) {
                word += TextIO.getAnyChar();
                ch = TextIO.peek();

            }
            if ( !Character.isLetter( ch ) ) {
                if ( ch == '\n' )
                    currentLine++;
                break;
            }
        }
        return word;
    }


    /**
     * This nested class represents information about a word, which includes the word itself.
     */
    private static class wordInfo {

        String word;  // The word.
        TreeSet<Integer> linesWordAppearsOn;  // The unique lines that this word appears on.

        wordInfo( String word ) {
            this.word = word;
            linesWordAppearsOn = new TreeSet<>();

        }
    }
}

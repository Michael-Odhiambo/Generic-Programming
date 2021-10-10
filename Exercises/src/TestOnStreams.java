
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestOnStreams {

    private static ScoreData[] scoreData = new ScoreData[] {

            new ScoreData("Smith","John",70),
            new ScoreData("Doe","Mary",85),
            new ScoreData("Page","Alice",82),
            new ScoreData("Cooper","Jill",97),
            new ScoreData("Flintstone","Fred",66),
            new ScoreData("Rubble","Barney",80),
            new ScoreData("Smith","Judy",48),
            new ScoreData("Dean","James",90),
            new ScoreData("Russ","Joe",55),
            new ScoreData("Wolfe","Bill",73),
            new ScoreData("Dart","Mary",54),
            new ScoreData("Rogers","Chris",78),
            new ScoreData("Toole","Pat",51),
            new ScoreData("Khan","Omar",93),
            new ScoreData("Smith","Ann",95)

    };

    public static void main( String[] args ) {
        int numberOfStudents = (int) Arrays.stream( scoreData )
                .parallel()
                .count();

        System.out.println("Number of students: " + numberOfStudents );

        int averageScore = Arrays.stream( scoreData )
                .parallel()
                .mapToInt( s -> s.score )
                .sum();

        System.out.println( "The average score is: " + averageScore/numberOfStudents );

        int studentsWithA = (int) Arrays.stream( scoreData )
                .parallel()
                .filter( s -> s.score >= 90 )
                .count();

        System.out.println( "Students with As: " + studentsWithA );

        List<String> failStudents = Arrays.stream( scoreData )
                .filter( s -> s.score < 70 )
                .map( s -> s.firstName + " " + s.lastName )
                .collect( Collectors.toList() );

        System.out.println( "Fail students: " );
        failStudents.stream().forEach( System.out::println );

        System.out.println();

        System.out.println( "Data sorted by last name: " );

        Arrays.stream( scoreData )
                .sorted( Comparator.comparing(s -> s.lastName) )
                .forEach( s -> System.out.println( ( s.lastName + " " + s.firstName ) ) );

        System.out.println();

        // Student's names ordered by score.
        Arrays.stream( scoreData )
                .sorted( Comparator.comparing( s -> s.score ) )
                .map( s -> ( s.firstName + " " + s.lastName + " " + s.score ) )
                .forEach( System.out::println );
    }







    private static class ScoreData {
        String firstName;
        String lastName;
        int score;

        ScoreData( String lastName, String firstName, int score ) {
            this.lastName = lastName;
            this.firstName = firstName;
            this.score = score;

        }
    }
}

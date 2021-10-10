
import java.util.Map;
import java.util.Set;
import java.util.Iterator;
import java.util.TreeMap;
/**
 * This program represents a simple implementation of a phone directory using a TreeMap. The entries in the
 * TreeMap are sorted according to their keys, which in this case is their names.
 */
public class PhoneDirectoryUsingTreeMap {

    private static Map<String, String> phoneDirectory = new TreeMap<>();

    public static void main( String[] args ) {

        addContact( "Michael Odhiambo", "0798312988" );
        addContact( "Everlyne Odero", "0700032175" );
        addContact( "Edwina Odero", "0727172423" );
        addContact( "Benard Odero", "0725714262" );
        addContact( "Ferdinand Oluoch", "0720609700" );
        addContact( "Godfrey Oduor", "0719447650" );
        addContact( "Patricia Achieng", "0700303800" );
        addContact( "Kezia Kavukuywa", "0738992987" );

        // ----------------------------------- Tests involving an Iterator ----------------------------------------

        Iterator<String> keyIterator = getNames().iterator();

        // Print all the names in the directory.
        while ( keyIterator.hasNext() ) {
            String name = keyIterator.next();
            System.out.println( name );

        }

        // Print all the names and corresponding numbers.
        Iterator< Map.Entry<String, String> > entryIterator = getNamesAndNumbers().iterator();

        while ( entryIterator.hasNext() ) {
            Map.Entry<String, String> entry = entryIterator.next();
            System.out.println( entry.getKey() + "  :  " + entry.getValue() );
        }

        // --------------------------------------------------------------------------------------------------------

        System.out.println();

        System.out.println( getNumber( "Benard Odero" ) );

    }

    private static void addContact( String name, String phoneNumber ) {
        phoneDirectory.put( name, phoneNumber );

    }

    private static String getNumber( String name ) {
        return phoneDirectory.get( name );
    }

    /**
     * Get all the names from the directory.
     * @return A set containing all the names in the directory.
     */
    private static Set<String> getNames() {
        return phoneDirectory.keySet();
    }

    /**
     * Delete a given name from the directory.
     */
    private static void deleteContact( String name ) {
        if ( phoneDirectory.containsKey( name ) )
            phoneDirectory.remove( name );
    }

    /**
     * Get the key-value pairs from the phone directory.
     */
    private static Set<Map.Entry<String, String>> getNamesAndNumbers() {
        return phoneDirectory.entrySet();

    }



}

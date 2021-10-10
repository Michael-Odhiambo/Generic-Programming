

import java.util.Collection;
import java.util.function.Predicate;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class Predicates {

    public static void main( String[] args ) {

    }

    public static <T> void remove( Collection<T> coll, Predicate<T> pred ) {
        /*
        Remove every object, obj, from coll for which pred.test(obj) is true.
        ( This does the same thing as coll.remove( pred ). )
         */
        Iterator<T> iterator = coll.iterator();
        T entry;

        while ( iterator.hasNext() ) {
            entry = iterator.next();

            if ( pred.test( entry ) ) {
                iterator.remove();
            }

        }


    }

    public static <T> void retain( Collection<T> coll, Predicate<T> pred ) {
        /*
        Remove every object, obj, from coll for which pred.test(obj) is false. ( That
        is, retain the objects for which the predicate is true. )
         */
        Iterator<T> iterator = coll.iterator();
        T entry;

        while ( iterator.hasNext() ) {
            entry = iterator.next();

            if ( !pred.test( entry ) ) {
                iterator.remove();
            }
        }
    }

    public static <T> List<T> collect( Collection<T> coll, Predicate<T> pred ) {
        /*
        Return a List that contains all the objects, obj, from the collection, coll, such
        that pred.test(obj) is true.
         */
        List<T> newList = new ArrayList<>();

        Iterator<T> iterator = coll.iterator();
        T entry;

        while ( iterator.hasNext() ) {
            entry = iterator.next();

            if ( pred.test( entry ) )
                newList.add( entry );
        }

        return newList;
    }

    public static <T> int find( ArrayList<T> list, Predicate<T> pred ) {
        /*
        Return the index of the first item in list for which the predicate is true, if any.
        If there is no such item, return -1.
         */
        Iterator<T> iterator = list.iterator();
        T entry;

        while ( iterator.hasNext() ) {
            entry = iterator.next();

            if ( pred.test( entry ) ) {
                return list.indexOf( entry );
            }
        }
        return -1;
    }
}

/*
SRM 155
The Incas used a sophisticated system of record keeping consisting of bundles
of knotted cords. Such a bundle of cords is called a quipu. Each individual
cord represents a single number. Surprisingly, the Incas used a base-10
positional system, just like we do today. Each digit of a number is represented
by a cluster of adjacent knots, with spaces between neighboring clusters. The
digit is determined by the number of knots in the cluster. For example, the
number 243 would be represented by a cord with knots tied in the following
pattern
     -XX-XXXX-XXX-
where each uppercase 'X' represents a knot and each '-' represents an unknotted
segment of cord (all quotes for clarity only).  Unlike many ancient
civilizations, the Incas were aware of the concept of zero, and used it in
their quipus. A zero is represented by a cluster containing no knots. For
example, the number 204003 would be represented by a cord with knots tied in
the following pattern
     -XX--XXXX---XXX-
        ^^    ^^^
        ^^    ^^^
        ^^    two zeros between these three segments
        ^^
        one zero between these two segments
Notice how adjacent dashes signal the presence of a zero.  Your task is to
translate a single quipu cord into an integer. The cord will be given as a
String knots containing only the characters 'X' and '-'. There will be a single
'-' between each cluster of 'X's, as well as a leading '-' and a trailing '-'.
The first cluster will not be empty.
*/

import java.util.*;

class Quipu {
    public static int readKnots(String knots) { 
        knots = knots.substring(1,knots.length()-1);
        ArrayList<String> output = new ArrayList<String>();
        List<String> knotList = Arrays.asList(knots.split("-",-1));
        Integer knotLength;
        for (String k : knotList) {
            if (k.length() == 0) {
                output.add("0");
            }
            else if (k.contains("X")){
                knotLength = k.length();
                output.add(knotLength.toString());
            }
        }
        return Integer.valueOf(String.join("",output));
    }
    public static void main(String[] args) { 
        System.out.println(readKnots("-XX-XXXX-XXX-"));
        System.out.println(readKnots("-XX--XXXX---XXX-"));
        System.out.println(readKnots("-X-"));
        System.out.println(readKnots("-X-------"));
        System.out.println(readKnots("-XXXXXXXXX--XXXXXXXXX-XXXXXXXXX-XXXXXXX-XXXXXXXXX-"));
    }
}


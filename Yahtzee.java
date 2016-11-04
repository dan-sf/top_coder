/*
SRM 146
This task is about the scoring in the first phase of the die-game Yahtzee,
where five dice are used. The score is determined by the values on the upward
die faces after a roll. The player gets to choose a value, and all dice that
show the chosen value are considered active. The score is simply the sum of
values on active dice.

Say, for instance, that a player ends up with the die faces showing 2, 2, 3, 5
and 4. Choosing the value two makes the dice showing 2 active and yields a
score of 2 + 2 = 4, while choosing 5 makes the one die showing 5 active,
yielding a score of 5.

Your method will take as input an int[] toss, where each element represents the
upward face of a die, and return the maximum possible score with these values.
*/

import java.util.*;

class Yahtzee {
    public static int maxPoints(int[] toss) {
        HashMap<Integer, Integer> check_toss = new HashMap<Integer, Integer>();
        for (int i : toss) {
            if (check_toss.containsKey(i)) {
                int addTo = check_toss.get(i);
                check_toss.put(i, i + addTo);
            }
            else {
                check_toss.put(i,i);
            }
        }

        Integer[] output = check_toss.values().toArray(new Integer[0]);
        int max = 0;

        for (int i : output) {
            if (i > max) {
                max = i;
            }
        }

        return max;
    }
    public static void main(String[] args) {
        System.out.println(maxPoints(new int[] { 2, 2, 3, 5, 4 }));
        System.out.println(maxPoints(new int[] { 6, 4, 1, 1, 3 }));
        System.out.println(maxPoints(new int[] { 5, 3, 5, 3, 3 }));
    }
}


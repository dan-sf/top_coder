/*
SRM 170
Consider a sequence {x0, x1, x2, ...}. A relation that defines some term xn in
terms of previous terms is called a recurrence relation. A linear recurrence
relation is one where the recurrence is of the form xn = ck-1xn-1 + ck-2xn-2 +
... + c0xn-k, where all the ci are real-valued constants, k is the length of
the recurrence relation, and n is an arbitrary positive integer which is
greater than or equal to k.

You will be given a int[] coefficients, indicating, in order, c0, c1, ...,
ck-1. You will also be given a int[] initial, giving the values of x0, x1, ...,
xk-1, and an int N. Your method should return xN modulo 10.

Note that the value of X modulo 10 equals the last digit of X if X is
non-negative. However, if X is negative, this is not true; instead, X modulo 10
equals ((10 - ((-X) modulo 10)) modulo 10). For example, (-16) modulo 10 = ((10
- (16 modulo 10)) modulo 10) = (10 - 6) modulo 10 = 4.

More specifically, if coefficients is of size k, then the recurrence relation
will be xn = coefficients[k - 1] * xn-1 + coefficients[k - 2] * xn-2 + ... +
coefficients[0] * xn-k.  For example, if coefficients = {2,1}, initial = {9,7},
and N = 6, then our recurrence relation is xn = xn-1 + 2 * xn-2 and we have x0
= 9 and x1 = 7. Then x2 = x1 + 2 * x0 = 7 + 2 * 9 = 25, and similarly, x3 = 39,
x4 = 89, x5 = 167, and x6 = 345, so your method would return (345 modulo 10) =
5.
*/

import java.util.*;
import java.math.*;

class RecurrenceRelation {
    public static int moduloTen(int[] coefficients, int[] initial, int N) {
        BigInteger bigZero = BigInteger.ZERO;
        BigInteger bigTen = BigInteger.TEN;
        BigInteger x_value = bigZero;

        // Convert initial to a series of BigInts
        ArrayList<BigInteger> series = new ArrayList<BigInteger>();
        for (int x : initial) {
            series.add(BigInteger.valueOf(x));
        }

        // Loop up to N value creating the recurrence series
        int start_value = 0;
        for (int i=0; i<=N; i++) {
            if (i < coefficients.length) {
                x_value = series.get(i);
            }
            else {
                // Calculate the next x value from previously calulated x values
                x_value = bigZero;
                start_value = series.size() - coefficients.length;
                for (int j=0; j<coefficients.length; j++) {
                    x_value = x_value.add(series.get(j+start_value).multiply(BigInteger.valueOf(coefficients[j])));
                }
                series.add(x_value);
            }
        }

        // If x value is negative return ((10 - ((-X) modulo 10)) modulo 10)
        // else return X modulo 10
        if (x_value.compareTo(bigZero) == -1)
            return bigTen.subtract(x_value.negate().mod(bigTen).mod(bigTen)).intValue();
        else
            return x_value.mod(bigTen).intValue();
    }

    public static void main(String args[]) {
        System.out.println(moduloTen(new int[] {2,1}, new int[] {9,7}, 6)); // Returns: 5
        System.out.println(moduloTen(new int[] {1,1}, new int[] {0,1}, 9)); // Returns: 4
        System.out.println(moduloTen(new int[] {2}, new int[] {1}, 20)); // Returns: 6
        System.out.println(moduloTen(new int[] {2}, new int[] {1}, 64)); // Returns: 6
        System.out.println(moduloTen(new int[] {25,143}, new int[] {0,0}, 100000)); // Returns: 0
        System.out.println(moduloTen(new int[] {9,8,7,6,5,4,3,2,1,0}, new int[] {1,2,3,4,5,6,7,8,9,10}, 654)); // Returns: 5
        System.out.println(moduloTen(new int[] {901,492,100}, new int[] {-6,-15,-39}, 0)); // Returns: 4
    }
}

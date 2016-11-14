/*
SRM 200
When evaluating a mathematical expression, there is the possibility of
ambiguity. If you wanted to know the result of "3 + 5 * 7", you might first
evaluate the (3+5) and get 56, or first evaluate the (5*7) and get 38. This
ambiguity can be resolved by using the order of operations: first do
multiplication and division (from left to right), and then after all those are
done, do addition and subtraction (again from left to right). Here, the correct
result would be the 38.

While this is unambiguous, it certainly is somewhat annoying. You think it
would be easier if people did all math from left to right, all the time, and
want to make a simple expression evaluator to do so.

The expression will be given to you as a String expr. It will consist of one
digit numbers (0 through 9) alternating with operators (+, -, or *), with no
spaces between them. Thus, expr would follow the format Digit Operator Digit
Operator .... Digit. For example, the expression given above would be given as
"3+5*7".

Your method should return an int representing the value of the expression when
evaluated from left to right.
*/

import java.util.*;

class NoOrderOfOperations {
    public static int evaluate(String expr) { 
        ArrayList<Integer> current = new ArrayList<Integer>();
        boolean add = false; boolean sub = false; boolean mul = false;
        for (Character ch : expr.toCharArray()) {
            if (ch == '+') add = true;
            else if (ch == '-') sub = true;
            else if (ch == '*') mul = true;
            else current.add(Character.getNumericValue(ch));

            if (current.size() == 2) {
                calc(current, add, sub, mul);
                add = sub = mul = false;
            }
        }

        return current.get(0);
    }

    public static void calc(ArrayList<Integer> values, boolean add, boolean sub, boolean mul) {
        int output = 0;
        if (add) output = values.get(0) + values.get(1);
        else if (sub) output = values.get(0) - values.get(1);
        else if (mul) output = values.get(0) * values.get(1);
        values.clear();
        values.add(output);
    }

    public static void main(String[] args) {
        System.out.println(evaluate("3+5*7"));
        System.out.println(evaluate("4-8*9*1"));
        System.out.println(evaluate("0"));
        System.out.println(evaluate("1*2*3*4*5*6*7*8*9"));
    }
}

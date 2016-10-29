/*
SRM 147
Julius Caesar used a system of cryptography, now known as Caesar Cipher, which
shifted each letter 2 places further through the alphabet (e.g. 'A' shifts to
'C', 'R' shifts to 'T', etc.). At the end of the alphabet we wrap around, that
is 'Y' shifts to 'A'.

We can, of course, try shifting by any number. Given an encoded text and a
number of places to shift, decode it.

For example, "TOPCODER" shifted by 2 places will be encoded as "VQREQFGT". In
other words, if given (quotes for clarity) "VQREQFGT" and 2 as input, you will
return "TOPCODER". See example 0 below.
*/

import java.util.*;

class CCipher {
    public static String decode( String cipherText, int shift) {
        ArrayList<Character> output = new ArrayList<Character>();
        ArrayList<Character> alfa = new ArrayList<Character>();
        for (int i=0; i<26; i++) {
            alfa.add((char) ('A' + i));
        }

        for (char letter : cipherText.toCharArray()) {
            int letterIndex = alfa.indexOf(letter);
            int outputIndex = (letterIndex - shift) % alfa.size();
            if (outputIndex < 0)
                outputIndex += alfa.size();
            output.add(alfa.get(outputIndex));
        }

        StringBuilder result = new StringBuilder(output.size());
        for (Character c : output) {
            result.append(c);
        }

        return result.toString();
    }
    public static void main(String[] agrs) {
        System.out.println(decode("VQREQFGT",2));
        System.out.println(decode("ABCDEFGHIJKLMNOPQRSTUVWXYZ", 10));
        System.out.println(decode("TOPCODER", 0));
        System.out.println(decode("ZWBGLZ", 25));
        System.out.println(decode("DBNPCBQ", 1));
        System.out.println(decode("LIPPSASVPH", 4));
    }
}


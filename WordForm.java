/*
SRM 173
In English, the letters A, E, I, O and U are the vowels. Y is also considered
to be a vowel if it's not preceded by another vowel and is not the first letter
in a word (the fact that a vowel is defined to some extent in terms of itself
does not make it ambiguous). If a letter is not a vowel then it is a consonant.
So in "TOY" the consonants are T and Y, and in "SYZYGY" they are S, Z and G.

A sequence of vowels will be denoted by uppercase letter 'V' and a sequence of
consonants will be denoted by uppercase letter 'C'. A word can then be
described as an alternating sequence of 'C' and 'V'. For instance, the word
"WHEREABOUTS" has the sequence CVCVCVC, and the words "YORK" and "TOY" both
have the sequence CVC. It's not permissible to have two or more consecutive 'V'
or 'C' in the sequence.

Create a class WordForm containing the method getSequence which takes a String
word and returns a String containing the word's sequence as described above.
The word may contain both uppercase and lowercase letters, but your method
should be case insensitive (see example 2).
*/

class WordForm {
    public static String getSequence(String word) {
        word = word.toLowerCase();
        String sequence = "";
        char[] vowels = {'a', 'e', 'i', 'o', 'u', 'y'};
        char letter;
        char previousLetterType = 'x';

        for (int i=0; i<word.length(); i++) {
            letter = word.charAt(i);
            if (isIn(letter, vowels)) {
                if (letter == 'y') {
                    if (i == 0 || previousLetterType == 'V') {
                        sequence = addTo(sequence, "C");
                        previousLetterType = 'C';
                    }
                    else {
                        sequence = addTo(sequence, "V");
                        previousLetterType = 'V';
                    }
                }
                else {
                    sequence = addTo(sequence, "V");
                    previousLetterType = 'V';
                }
            }
            else {
                sequence = addTo(sequence, "C");
                previousLetterType = 'C';
            }
        }

        return sequence;
    }

    public static String addTo(String sequence, String letterType) {
        if (sequence.length() == 0) {
            sequence = letterType;
        }
        else if (sequence.charAt(sequence.length() - 1) != letterType.charAt(0)) {
            sequence += letterType;
        }
        return sequence;
    }

    public static boolean isIn(char letter, char[] check) {
        for (char c : check) {
            if (c == letter) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(getSequence("WHEREABOUTS"));
        System.out.println(getSequence("yoghurt"));
        System.out.println(getSequence("YipPy"));
        System.out.println(getSequence("AyYyEYye"));
        System.out.println(getSequence("yC"));
    }
}

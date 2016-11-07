/*
SRM 208
A group of people stand before you arranged in rows and columns. Looking from
above, they form an R by C rectangle of people. You will be given a String[]
people containing the height of each person. Elements of people correspond to
rows in the rectangle. Each element contains a space-delimited list of integers
representing the heights of the people in that row. Your job is to return 2
specific heights in a int[]. The first is computed by finding the shortest
person in each row, and then finding the tallest person among them (the
"tallest-of-the-shortest"). The second is computed by finding the tallest
person in each column, and then finding the shortest person among them (the
"shortest-of-the-tallest").
*/

class TallPeople {
    public static int[] getPeople(String[] people) {
        int[] output = new int[2];
        int insideLen = people.length;
        int outsideLen = 0;
        for (Character ch : people[0].toCharArray()) {
            if (ch != ' ') outsideLen++;
        }

        int[][] heightHolder = new int[outsideLen][insideLen];
        int[] tallestOfShort = new int[insideLen];
        int[] shortestOfTall = new int[outsideLen];

        for (int i=0; i<people.length; i++) {
            tallestOfShort[i] = getShortest(people[i]);
            int counter = 0;
            for (Character ch : people[i].toCharArray()) {
                if (ch != ' ') {
                    heightHolder[counter][i] = Character.getNumericValue(ch);
                    counter++;
                }
            }
        }

        for (int h = 0; h < heightHolder.length; h++) {
            int tall = getTallest(heightHolder[h]);
            shortestOfTall[h] = tall;
        }

        output[0] = getTallest(tallestOfShort);
        output[1] = getShortest(shortestOfTall);

        return output;
    }

    public static int getTallest(int[] people) {
        int max = people[0];
        for (int i : people) {
            if (i > max) max = i;
        }
        return max;
    }

    public static int getShortest(int[] people) {
        int min = people[0];
        for (int i : people) {
            if (i < min) min = i;
        }
        return min;
    }

    public static int getShortest(String people) {
        int min = Character.getNumericValue(people.charAt(0));
        for (char ch : people.toCharArray()) {
            if (ch != ' ') {
                if (Character.getNumericValue(ch) < min)
                    min = Character.getNumericValue(ch);
            }
        }
        return min;
    }

    public static void printThis(int[] nums) {
        for (int i : nums) { System.out.print(i); System.out.print(", "); }
        System.out.println("");
    }

    public static void main(String[] args) {
        printThis(getPeople(new String[] {"9 2 3", "4 8 7"}));
        printThis(getPeople(new String[] {"1 2", "4 5", "3 6"}));
        printThis(getPeople(new String[] {"1 1", "1 1"}));
    }
}

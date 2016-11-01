/*
SRM 162
You have a piece of paper that you need to fold to fit into a box with a given
width and length. Each time you fold the paper, you can fold it in half across
either its width or length, but you can only fold the paper 8 times (after 8
times, the paper is too dense to fold again).

You will be given a int[] paper, which contains the width and length of the
paper in inches, and a int[] box, which contains the width and length of the
box in inches. In both cases, the first element is the width and the second
element is the length. Your method should return the fewest number of folds
which would allow you to fit the paper into the box. You can rotate the paper
90 degrees if it will fit with fewer folds, but the paper must lie completely
flat inside the box. If the paper cannot be fit into the box with 8 folds or
fewer, return -1.
*/

class PaperFold {
    public static int fold(float width, float height, int[] box) {
        int folds = 0;

        while (width > (float) box[0]) {
            width /= 2.0f;
            folds += 1;
        }

        while (height > (float) box[1]) {
            height /= 2.0f;
            folds += 1;
        }

        return folds;
    }
    public static int numFolds(int[] paper, int[] box) {
        int folds = fold((float) paper[0], (float) paper[1], box);
        int folds90 = fold((float) paper[1], (float) paper[0], box);

        if (folds > 8 && folds90 > 8) return -1;
        return (folds < folds90) ? folds : folds90;
    }
    public static void main(String[] args) {
        System.out.println(numFolds(new int[] {8,11}, new int[] {6,10}));
        System.out.println(numFolds(new int[] {11, 17}, new int[] {6, 4}));
        System.out.println(numFolds(new int[] {11, 17}, new int[] {5, 4}));
        System.out.println(numFolds(new int[] {1000,1000}, new int[] {62,63}));
        System.out.println(numFolds(new int[] {100,30}, new int[] {60,110}));
        System.out.println(numFolds(new int[] {1895, 6416}, new int[] {401, 1000}));
    }
}

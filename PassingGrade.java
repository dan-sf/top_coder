/*
SRM 185
You are studying for the final exam in a tough course, and want to know how
many points you need to score on the final to pass the course. You know how
many points you earned on each assignment (pointsEarned), how many points were
possible on each assignment (pointsPossible), and how many points are possible
on the final exam (finalExam). You need to earn a minimum of 65% of the total
possible points to pass the course. Assume your score on the final exam will be
an integral number of points between 0 and finalExam, inclusive. Return the
number of points you need to score on the final to pass the course, or -1 if it
is impossible for you to pass the course.
*/

class PassingGrade {
    public static int pointsNeeded(int[] pointsEarned, int[] pointsPossible, int finalExam) {
        int pointsEarnedTotal = sum(pointsEarned);
        int pointsPossibleTotal = sum(pointsPossible);
        float finalGradeTarget = 0.65f;
        float pointsNeeded = finalGradeTarget * (pointsPossibleTotal + finalExam) - pointsEarnedTotal;

        if (pointsNeeded < 0) return 0;
        else if (pointsNeeded > finalExam) return -1;
        else return (int) Math.ceil(pointsNeeded);
    }
    public static int sum(int[] values) {
        int total = 0;
        for (int i : values) {
            total += i;
        }
        return total;
    }
    public static void main (String[] args) {
        System.out.println(pointsNeeded(new int[] {55,77,82,60}, new int[] {100,100,100,100}, 300));
        System.out.println(pointsNeeded(new int[] {1,2,3,4}, new int[] {2,3,4,5}, 7));// Returns: 4
        System.out.println(pointsNeeded(new int[] {1,2,2,1}, new int[] {9,9,9,9}, 9));// Returns: -1
        System.out.println(pointsNeeded(new int[] {7,8,7,6}, new int[] {8,8,8,8}, 9));// Returns: 0
        System.out.println(pointsNeeded(new int[] {17,23,50,200,19,56,83,91,77,9,0},
                                        new int[] {20,30,50,250,20,70,100,100,100,10,10},
                                        400));// Returns: 129
        System.out.println(pointsNeeded(new int[] {600,600,600,600,600,600,600,600,600,600,600,600,600,600,600,600,600,600,600,600},
                                        new int[] {1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,901},
                                        3000));// Returns: 2886
    }
}


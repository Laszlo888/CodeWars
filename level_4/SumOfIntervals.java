/*
    Write a function called sumIntervals/sum_intervals() that accepts an array of intervals,
    and returns the sum of all the interval lengths. Overlapping intervals should only be counted once.

    Intervals:

    Intervals are represented by a pair of integers in the form of an array.
    The first value of the interval will always be less than the second value.
    Interval example: [1, 5] is an interval from 1 to 5. The length of this interval is 4.

    Overlapping Intervals:

    List containing overlapping intervals:
    [1,4],[7, 10],[3, 5]
    The sum of the lengths of these intervals is 7.
    Since [1, 4] and [3, 5] overlap, we can treat the interval as [1, 5], which has a length of 4.

    Examples:
    // null argument
    Interval.sumIntervals(null);  // => 0

    // empty intervals
    Interval.sumIntervals(new int[][]{});  // => 0
    Interval.sumIntervals(new int[][]{2,2}, {5,5});  // => 0

    // disjoined intervals
    Interval.sumIntervals(new int[][]{{1,2},{3,5}});  // => (2-1) + (5-3) = 3

    // overlapping intervals
    Interval.sumIntervals(new int[][]{{1,4},{3,6},{2,8}});  // [1,8] => 7
*/

import java.util.*;

public class SumOfIntervals {

    public static void main(String[] args) {
        System.out.println(sumIntervals(new int[][]{}));
    }

    public static int sumIntervals(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        } else {
            int result = 0;
            //searching max,min number for ArrayList length
            int max = 0;
            int min = 0;
            for (int i = 0; i < intervals.length; i++) {
                for (int j = 0; j < intervals[i].length; j++) {
                    if (intervals[i][j] >= 0) {
                        if (intervals[i][j] > max) {
                            max = intervals[i][j];
                        }
                    } else {
                        if (intervals[i][j] < min) {
                            min = intervals[i][j];
                        }
                    }
                }
            }

            List<Boolean> intervalStorePositive = new ArrayList<>(max + 1);
            List<Boolean> intervalStoreNegative = new ArrayList<>(Math.abs(min) + 1);
            // fill lists with null values
            for (int i = 0; i < max + 1; i++) {
                intervalStorePositive.add(null);
            }
            if (min < 0) {
                for (int i = 0; i < Math.abs(min) + 1; i++) {
                    intervalStoreNegative.add(null);
                }
            }

            boolean overlappedOrNot = false;
            for (int i = 0; i < intervals.length; i++) {
                // check overlapping
                for (int k = intervals[i][0]; k < intervals[i][1]; k++) {
                    if (intervals[i][0] >= 0) {
                        if (intervalStorePositive.get(k) != null) {
                            overlappedOrNot = true;
                        }
                    } else if (intervals[i][0] < 0 && intervals[i][1] < 0) {
                        if (intervalStoreNegative.get(Math.abs(k)) != null) {
                            overlappedOrNot = true;
                        }
                    } else if (intervals[i][0] < 0 && intervals[i][1] >= 0) {
                        if (k < 0) {
                            if (intervalStoreNegative.get(Math.abs(k)) != null) {
                                overlappedOrNot = true;
                            }
                        } else {
                            if (intervalStorePositive.get(k) != null) {
                                overlappedOrNot = true;
                            }
                        }
                    }
                }
                // add intervals to lists and count result
                if (overlappedOrNot == false) {
                    result += intervals[i][1] - intervals[i][0];
                    for (int j = intervals[i][0]; j < intervals[i][1]; j++) {
                        if (j >= 0) {
                            intervalStorePositive.set(j, true);
                        } else {
                            intervalStoreNegative.set(Math.abs(j), true);
                        }
                    }
                } else {
                    for (int j = intervals[i][0]; j < intervals[i][1]; j++) {
                        if (j >= 0) {
                            if (intervalStorePositive.get(j) == null) {
                                intervalStorePositive.set(j, true);
                                result += 1;
                            }
                        } else {
                            if (intervalStoreNegative.get(Math.abs(j)) == null) {
                                intervalStoreNegative.set(Math.abs(j), true);
                                result += 1;
                            }
                        }
                    }
                }
                overlappedOrNot = false;
            }

            System.out.println("Positive: " + intervalStorePositive);
            System.out.println("Negative: " + intervalStoreNegative);

            return result;
        }
    }
}
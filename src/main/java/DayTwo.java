import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

public class DayTwo {
    public static void main(String[] args) {
        InputStream inputStream = DayTwo.class.getClassLoader().getResourceAsStream("daytwo/input.txt");
        Scanner scanner = new Scanner(inputStream);

        int safe1 = 0;
        int safe2 = 0;
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] parts = line.split(" ");
            int[] input = Arrays.stream(parts).mapToInt(Integer::parseInt).toArray();

            //pt1
            if (isSafe(input)) {
                safe1++;
            }

            //pt2
            if (isSafePt2(input)) {
                safe2++;
            }
        }

        System.out.println(safe1);
        System.out.println(safe2);
    }

    public static boolean isSafe(int[] in) {
        boolean increasing = in[0] < in[in.length - 1];

        int difference;
        for (int i = 1; i < in.length; i++) {
            if (increasing) {
                difference = in[i] - in[i - 1];
            } else {
                difference = in[i - 1] - in[i];
            }

            if (difference < 1 || difference > 3) {
                return false;
            }

        }
        return true;
    }

    public static boolean isSafePt2(int[] in) {
        if (isSafe(in)) return true;

        for (int i = 0; i < in.length; i++) {
            int[] newArray = removeIndexFromArray(in, i);
            if (isSafe(newArray)) {
                return true;
            }
        }
        return false;
    }

    public static int[] removeIndexFromArray(int[] in, int index) {
        int[] newArray = new int[in.length - 1];

        System.arraycopy(in, 0, newArray, 0, index);
        System.arraycopy(in, index + 1, newArray, index, in.length - 1 - index);

        return newArray;
    }
}

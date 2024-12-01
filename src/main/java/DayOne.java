import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class DayOne {
    public static void main(String[] args) {
        InputStream stream = DayOne.class.getResourceAsStream("/dayone/input.txt");
        Scanner reader = new Scanner(stream);

        List<Integer> one = new ArrayList<>();
        List<Integer> two = new ArrayList<>();

        while (reader.hasNext()) {
            String line = reader.nextLine();
            String[] parts = line.split(" {3}");

            one.add(Integer.parseInt(parts[0]));
            two.add(Integer.parseInt(parts[1]));
        }

        one.sort(Integer::compareTo);
        two.sort(Integer::compareTo);

        int totalDiff = 0;

        for (int i = 0; i < one.size(); i++) {
            totalDiff += Math.abs(one.get(i) - two.get(i));
        }

        System.out.println(totalDiff);

        int score = 0;
        for (Integer integer : one) {
            int countInTwo = Collections.frequency(two, integer);
            score += countInTwo * integer;
        }

        System.out.println(score);
    }
}

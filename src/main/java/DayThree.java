import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DayThree {
    public static void main(String[] args) {
        InputStream inputStream = DayThree.class.getClassLoader().getResourceAsStream("daythree/input.txt");
        Scanner scanner = new Scanner(inputStream);

        List<String> lines = new ArrayList<>();

        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            lines.add(line);
        }

        //pt1
        {
            int result = 0;
            Pattern mulPattern = Pattern.compile("mul\\(\\d+,\\d+\\)");
            for (String line : lines) {
                Matcher matcher = mulPattern.matcher(line);
                while (matcher.find()) {
                    String[] parts = matcher.group().split(",");
                    int a = Integer.parseInt(parts[0].substring(4));
                    int b = Integer.parseInt(parts[1].substring(0, parts[1].length() - 1));
                    result += a * b;
                }
            }
            System.out.println(result);
        }

        {
            int result = 0;
            Pattern pattern = Pattern.compile("mul\\(\\d+,\\d+\\)|do\\(\\)|don't\\(\\)");
            for (String line : lines) {
                Matcher matcher = pattern.matcher(line);
                boolean enabled = true;
                while (matcher.find()) {
                    if(matcher.group().equals("do()")) {
                        enabled = true;
                    } else if(matcher.group().equals("don't()")) {
                        enabled = false;
                    } else if(enabled) {
                        String[] repl = matcher.group().replace("mul(", "").replace(")", "").split(",");
                        int a = Integer.parseInt(repl[0]);
                        int b = Integer.parseInt(repl[1]);

                        result += a * b;
                    }
                }
            }

            System.out.println(result);
        }


    }
}

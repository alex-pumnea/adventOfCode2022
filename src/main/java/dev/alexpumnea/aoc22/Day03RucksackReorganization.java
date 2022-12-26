package dev.alexpumnea.aoc22;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Day03RucksackReorganization {
    public static void main(String[] args) {

        Map<String, Integer> alphabetWithScore = getAlphabetWithMappedScore();

        List<String> duplicates = getDuplicates();
        List<String> commonInGroupOfThree = getCommonInGroupOfThree();

        int sum = getSumEachLine(alphabetWithScore, duplicates);

        int sumGroupOfThree = getSumEachLine(alphabetWithScore, commonInGroupOfThree);

        System.out.println(sum);
        System.out.println(sumGroupOfThree);

    }

    private static Map<String, Integer> getAlphabetWithMappedScore() {
        char[] alphabet = "0abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        Map<String, Integer> alphabetWithScore = new HashMap<>();

        for (int i = 0; i < alphabet.length; i++) {
            alphabetWithScore.put(String.valueOf(alphabet[i]), i);
        }
        return alphabetWithScore;
    }

    private static int getSumEachLine(Map<String, Integer> alphabetWithScore, List<String> duplicates) {
        int sum = 0;

        for (String duplicate : duplicates) {
            if (alphabetWithScore.containsKey(duplicate)) {
                sum += alphabetWithScore.get(duplicate);
            }
        }
        return sum;
    }

    private static List<String> getDuplicates() {
        Path path = Paths.get("res/day03.txt");
        List<String> duplicates = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {

            String line = reader.readLine();

            while (line != null) {

                String list1 = line.substring(0, (line.length() / 2));
                String list2 = line.substring(line.length() / 2);
                duplicates.add(findDuplicateInStream(list1, list2));

                // read next line
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return duplicates;
    }

    private static List<String> getCommonInGroupOfThree() {
        Path path = Paths.get("res/day03.txt");
        List<String> commonInGroupOfThree = new ArrayList<>();
        String first = "";
        String second = "";
        String third = "";

        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {

            String line = reader.readLine();

            while (line != null) {

                for (int i = 0; i < 3; i++) {
                    switch (i) {
                        case 0 -> first = line;
                        case 1 -> second = line;
                        case 2 -> third = line;
                        default -> throw new UnsupportedOperationException();
                    }
                    // read next line
                    line = reader.readLine();
                }

                commonInGroupOfThree.add(findCommonInGroupOfThree(first, second, third));


            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return commonInGroupOfThree;
    }

    private static String findCommonInGroupOfThree(String first, String second, String third) {
        return first.chars()
                .distinct()
                .mapToObj(ch -> String.valueOf((char) ch))
                .filter(second::contains)
                .filter(third::contains)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public static String findDuplicateInStream(String str1, String str2) {

        return str1.chars()
                .distinct()
                .mapToObj(ch -> String.valueOf((char) ch))
                .filter(str2::contains)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

    }


}

package dev.alexpumnea.aoc22;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day01CalorieCounting {
    public static void main(String[] args) throws IOException {
        final Logger logger = LoggerFactory.getLogger(Day01CalorieCounting.class);
        final Path path = Paths.get("src/main/resources/day01.txt");
        System.out.println(path);
        final List<String> inputData = Files.readAllLines(path.toAbsolutePath());
        final Map<Integer, Integer> caloriesPerElf = sumOfCaloriesPerEachElf(inputData);

        String resultPart1 = String.valueOf(getMaximumAmountOfCaloriesCarriedByElf(caloriesPerElf));
        logger.info(resultPart1);

        String resultPart2 = String.valueOf(sumOfCaloriesOfTop3Elf(caloriesPerElf));
        logger.info(resultPart2);
    }

    private static Map<Integer, Integer> sumOfCaloriesPerEachElf(List<String> inputData) {
        int sum = 0;
        int elfCounter = 0;
        Map<Integer, Integer> elfTotalCalories = new HashMap<>();

        for (String line : inputData) {
            if (!line.isEmpty()) {
                sum += Integer.parseInt(line);
                continue;
            }
            elfTotalCalories.put(elfCounter, sum);
            sum = 0;
            elfCounter++;
        }
        return elfTotalCalories;
    }

    private static int getMaximumAmountOfCaloriesCarriedByElf(Map<Integer, Integer> sumOfCaloriesPerEachElf) {
        return sumOfCaloriesPerEachElf
                .values()
                .stream()
                .reduce(0, Integer::max);
    }

    private static int sumOfCaloriesOfTop3Elf(Map<Integer, Integer> sumOfCaloriesPerEachElf) {
        return sumOfCaloriesPerEachElf
                .values()
                .stream()
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .reduce(0, Integer::sum);
    }
}

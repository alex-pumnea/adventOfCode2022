package dev.alexpumnea.aoc22;

import org.apache.commons.lang3.Range;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day04CampCleanup {

    public List<Integer> getNumberOfContainedPairs() {
        Path path = FileSystems.getDefault().getPath("src/main/resources", "day04.txt");
        List<Integer> result = new ArrayList<>();
        int containingRangeCount = 0;
        int overlapRangeCount = 0;

        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {

            String line = reader.readLine();

            while (line != null) {

                String[] ranges = line.split(",");
                String[] firstRange = ranges[0].split("-");
                String[] secondRange = ranges[1].split("-");

                if (firstRangeContainsSecondRange(firstRange, secondRange) || secondRangeContainsFirstRange(secondRange, firstRange)) {
                    containingRangeCount++;
                }

                if (firstRangeOverlapsSecondRange(firstRange, secondRange)) {
                    overlapRangeCount++;
                }

                // read next line
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        result.add(containingRangeCount);
        result.add(overlapRangeCount);

        return result;
    }

    public boolean firstRangeOverlapsSecondRange(String[] range1, String[] range2) {
        int lowerBoundFirstRange = Integer.parseInt(range1[0]);
        int upperBoundFirstRange = Integer.parseInt(range1[1]);
        int lowerBoundSecondRange = Integer.parseInt(range2[0]);
        int upperBoundSecondRange = Integer.parseInt(range2[1]);

        final Range<Integer> firstRange = Range.between(lowerBoundFirstRange, upperBoundFirstRange);
        final Range<Integer> secondRange = Range.between(lowerBoundSecondRange, upperBoundSecondRange);

        return firstRange.isOverlappedBy(secondRange);
    }

    public boolean firstRangeContainsSecondRange(String[] range1, String[] range2) {
        int lowerBoundFirstRange = Integer.parseInt(range1[0]);
        int upperBoundFirstRange = Integer.parseInt(range1[1]);
        int lowerBoundSecondRange = Integer.parseInt(range2[0]);
        int upperBoundSecondRange = Integer.parseInt(range2[1]);

        final Range<Integer> firstRange = Range.between(lowerBoundFirstRange, upperBoundFirstRange);
        final Range<Integer> secondRange = Range.between(lowerBoundSecondRange, upperBoundSecondRange);

        return firstRange.containsRange(secondRange);
    }

    public boolean secondRangeContainsFirstRange(String[] range2, String[] range1) {
        int lowerBoundFirstRange = Integer.parseInt(range1[0]);
        int upperBoundFirstRange = Integer.parseInt(range1[1]);
        int lowerBoundSecondRange = Integer.parseInt(range2[0]);
        int upperBoundSecondRange = Integer.parseInt(range2[1]);

        final Range<Integer> firstRange = Range.between(lowerBoundFirstRange, upperBoundFirstRange);
        final Range<Integer> secondRange = Range.between(lowerBoundSecondRange, upperBoundSecondRange);

        return secondRange.containsRange(firstRange);
    }
}

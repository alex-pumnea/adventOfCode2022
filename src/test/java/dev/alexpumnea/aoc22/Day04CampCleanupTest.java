package dev.alexpumnea.aoc22;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day04CampCleanupTest {

    Day04CampCleanup day04CampCleanup = new Day04CampCleanup();
    String[] firstRange = {"28", "47"};
    String[] secondRange = {"45", "47"};


    @Test
    void getNumberOfContainedPairs() {
        List<Integer> expected = Arrays.asList(524, 798);
        List<Integer> actual = day04CampCleanup.getNumberOfContainedPairs();
        Assertions.assertIterableEquals(expected, actual);
    }

    @Test
    void checkFirstRangeContainsSecondRange() {
        boolean result = day04CampCleanup.firstRangeContainsSecondRange(firstRange, secondRange);
        assertTrue(result);
    }

    @Test
    void secondRangeContainsFirstRange() {
        boolean result = day04CampCleanup.secondRangeContainsFirstRange(secondRange, firstRange);
        assertFalse(result);
    }

    @Test
    void firstRangeOverlapsSecondRange() {
        boolean result = day04CampCleanup.firstRangeOverlapsSecondRange(firstRange, secondRange);
        assertTrue(result);
    }
}
package dev.alexpumnea.aoc22;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class Day02RockPaperScissors {
    static final int ROCK = 1;
    static final int PAPER = 2;
    static final int SCISSORS = 3;

    static final int LOSS = 0;
    static final int DRAW = 3;
    static final int WIN = 6;

    public static void main(String[] args) throws Exception {
        Map<String, Integer> shapes = new HashMap<>();
        shapes.put("A", ROCK);
        shapes.put("B", PAPER);
        shapes.put("C", SCISSORS);
        shapes.put("X", ROCK);
        shapes.put("Y", PAPER);
        shapes.put("Z", SCISSORS);

        BufferedReader br = new BufferedReader(new FileReader("res/day02.txt"));

        int score = 0;

        int predscore = 0;

        String line;
        while ((line = br.readLine()) != null) {
            String[] match = line.trim().split(" ");

            if (match.length != 2) continue;

            if (shapes.get(match[0]) == shapes.get(match[1])) {
                score += DRAW;
            } else if (
                    shapes.get(match[0]) == ROCK &&
                            shapes.get(match[1]) == PAPER
            ) {
                score += WIN;
            } else if (
                    shapes.get(match[0]) == PAPER &&
                            shapes.get(match[1]) == SCISSORS
            ) {
                score += WIN;
            } else if (
                    shapes.get(match[0]) == SCISSORS &&
                            shapes.get(match[1]) == ROCK
            ) {
                score += WIN;
            } else {
                score += LOSS;
            }

            score += shapes.get(match[1]);

            if (match[1].equals("X")) {
                predscore += LOSS;

                if (shapes.get(match[0]) == ROCK) {
                    predscore += SCISSORS;
                }
                if (shapes.get(match[0]) == PAPER) {
                    predscore += ROCK;
                }
                if (shapes.get(match[0]) == SCISSORS) {
                    predscore += PAPER;
                }
            }
            if (match[1].equals("Y")) {
                predscore += DRAW;
                predscore += shapes.get(match[0]);
            }
            if (match[1].equals("Z")) {
                predscore += WIN;

                if (shapes.get(match[0]) == ROCK) {
                    predscore += PAPER;
                }
                if (shapes.get(match[0]) == PAPER) {
                    predscore += SCISSORS;
                }
                if (shapes.get(match[0]) == SCISSORS) {
                    predscore += ROCK;
                }
            }
        }

        System.out.println(score);
        System.out.println(predscore);
    }
}
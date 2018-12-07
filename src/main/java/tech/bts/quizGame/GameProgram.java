package tech.bts.quizGame;

import java.util.List;
import java.util.Scanner;

public class GameProgram {
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        Game game = new Game();
        System.out.println("Hi! I'm Trivia :) Can you write me your name?");
        game.setUserName(scanner.nextLine());

        while (true) {
            System.out.println("Nice to meet you, " + game.getUserName() + "\nAre you ready to start? [Yes/No]");
            if (scanner.nextLine().equals("Yes")) {
                playGame(game);
                game.checkPoints();
                break;
            } else {
                break;
            }
        }
    }

    public static void playGame(Game game) throws Exception {
        Scanner scanner = new Scanner(System.in);
        List<Quiz> quizzes = game.getQuizzes();
        boolean horribleAnswer = false;
        for (int i = 0; i < quizzes.size(); i++) {
            Quiz quiz = quizzes.get(i);
            System.out.println(quiz.getQuestion());
            String answer = scanner.nextLine();
            if (quiz.getCorrectAnswers().contains(answer)) {
                System.out.println("Correct answer!!");
                game.setPoints(1);
                if (horribleAnswer) {
                    System.out.println("Extra point!!");
                    game.setPoints(1);
                    horribleAnswer = false;
                }
            } else if (quiz.getHorribleAnswers().contains(answer)) {
                System.out.println("Horrible answer!!");
                game.setPoints(-2);
                horribleAnswer = true;
            } else {
                System.out.println("Wrong answer...");
                horribleAnswer = false;
            }

        }
    }
}

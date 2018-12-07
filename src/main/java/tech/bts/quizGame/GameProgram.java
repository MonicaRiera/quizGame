package tech.bts.quizGame;

import java.util.Scanner;

public class GameProgram {
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        Game game = new Game();
        System.out.println("Hi! I'm Trivia :) Can you write me your name?");
        game.setUserName(scanner.nextLine());

        while (true) {
            System.out.println("Nice to meet you, " + game.getUserName() + "\nAre you ready to start? [Yes/No]");
            if (scanner.nextLine().toUpperCase().equals("YES")) {
                game.getQuizzes();
                game.play();
                game.checkPoints();
                break;
            } else {
                break;
            }
        }
    }
}

package tech.bts.quizGame;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private List<Quiz> quizzes;
    private int points;
    private String userName;
    private int maxScore;

    public Game() {
        this.quizzes = new ArrayList<Quiz>();
        this.points = 0;
        this.maxScore = 0;
    }

    public List<Quiz> getQuizzes() throws Exception{
        Gson gson = new Gson();
        BufferedReader reader = new BufferedReader( new FileReader("quizzes.json"));
        String json = reader.readLine();
        Type type = new TypeToken<List<Quiz>>(){}.getType();
        quizzes = gson.fromJson(json, type);
        this.maxScore = quizzes.size();
        return quizzes;
    }

    public void addQuiz(Quiz quiz) throws Exception{
        quizzes.add(quiz);
        this.maxScore = quizzes.size();
        Gson gson = new Gson();
        String json = gson.toJson(quizzes);
        PrintWriter writer = new PrintWriter("quizzes.json");
        writer.println(json);
        writer.close();
    }

    public int getPoints() {
        return points;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public String getUserName() {
        return userName;
    }

    public void setPoints(int points) {
        this.points += points;
        if (this.points < 0) {
            this.points = 0;
        }
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void checkPoints() {
        if (this.points >= this.maxScore * 0.7) {
            System.out.println("Congratulations! You won the game with a score of " + this.points + " points over " + this.maxScore);
        } else {
            System.out.println("Your score is only " + this.points + ". You lose...");
        }
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        boolean horribleAnswer = false;
        if (this.quizzes.size() == 0) {
            throw new NoQuizzesException();
        }

        for (int i = 0; i < this.quizzes.size(); i++) {
            Quiz quiz = this.quizzes.get(i);
            System.out.println(quiz.getQuestion());
            String answer = scanner.nextLine().toUpperCase();
            if (quiz.getCorrectAnswers().contains(answer)) {
                System.out.println("Correct answer!!");
                setPoints(1);
                if (horribleAnswer) {
                    System.out.println("Extra point!!");
                    setPoints(1);
                    horribleAnswer = false;
                }
            } else if (quiz.getHorribleAnswers().contains(answer)) {
                System.out.println("Horrible answer!!");
                setPoints(-2);
                horribleAnswer = true;
            } else {
                System.out.println("Wrong answer...");
                horribleAnswer = false;
            }

        }
    }
}

package tech.bts.quizGame;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

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
        Gson gson = new Gson();
        String json = gson.toJson(quizzes);
        PrintWriter writer = new PrintWriter("quizzes.json");
        writer.println(json);
        writer.close();
    }

    public int getPoints() {
        return points;
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
}

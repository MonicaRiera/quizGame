package tech.bts.quizGame;

import java.util.ArrayList;
import java.util.List;

public class Quiz {

    private String question;
    private List<String> correctAnswers;
    private List<String> horribleAnswers;

    public Quiz(String question) {
        this.question = question;
        correctAnswers = new ArrayList<String>();
        horribleAnswers = new ArrayList<String>();
    }

    public void addCorrectAnswer(String answer) {
        correctAnswers.add(answer);
    }

    public void addHorribleAnswer(String answer) {
        horribleAnswers.add(answer);
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getCorrectAnswers() {
        return correctAnswers;
    }

    public List<String> getHorribleAnswers() {
        return horribleAnswers;
    }

    public void setCorrectAnswers(List<String> correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public void setHorribleAnswers(List<String> horribleAnswers) {
        this.horribleAnswers = horribleAnswers;
    }

    @Override
    public String toString() {
        String result = "Question: " + question + "\n";
        for (int i = 0; i < correctAnswers.size(); i++) {
            result += "Correct answer " + (i + 1) + ": " + correctAnswers.get(i) + "\n";
        }
        for (int i = 0; i < horribleAnswers.size(); i++) {
            result += "Horrible answer " + (i + 1) + ": " + horribleAnswers.get(i) + "\n";
        }
        return result;
    }
}

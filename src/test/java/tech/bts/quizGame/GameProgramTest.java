package tech.bts.quizGame;

import org.junit.Test;


import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class GameProgramTest {

    @Test
    public void initial_values() {
        Game game = new Game();

        assertThat(game.getMaxScore(), is(0));
        assertThat(game.getPoints(), is(0));
    }

    @Test
    public void load_quizzes() throws Exception{
        Game game = new Game();
        game.getQuizzes();
        assertThat(game.getMaxScore(), is(game.getQuizzes().size()));
    }

    @Test (expected = NoQuizzesException.class)
    public void not_playing_without_quizzes() {
        Game game = new Game();
        game.play();
    }

    @Test
    public void create_quiz() {
        String question = "Test question";
        Quiz quiz = new Quiz(question);
        assertThat(quiz.getCorrectAnswers().size(), is(0));
        assertThat(quiz.getHorribleAnswers().size(), is(0));

        List<String> correctAnswers = Arrays.asList("answer1", "answer2");
        List<String> horribleAnswers = Arrays.asList("horrible1", "horrible2");
        quiz.setCorrectAnswers(correctAnswers);
        quiz.setHorribleAnswers(horribleAnswers);
        assertThat(quiz.getCorrectAnswers().size(), is(2));
        assertThat(quiz.getHorribleAnswers().size(), is(2));
    }

    @Test
    public void add_quiz_to_game() throws Exception{
        String question = "Test question";
        List<String> correctAnswers = Arrays.asList("answer1", "answer2");
        List<String> horribleAnswers = Arrays.asList("horrible1", "horrible2");
        Quiz quiz = new Quiz(question);
        quiz.setCorrectAnswers(correctAnswers);
        quiz.setHorribleAnswers(horribleAnswers);
        Game game = new Game();
        game.getQuizzes();
        game.addQuiz(quiz);

        assertThat(game.getQuizzes().size(), is(8));
    }
}
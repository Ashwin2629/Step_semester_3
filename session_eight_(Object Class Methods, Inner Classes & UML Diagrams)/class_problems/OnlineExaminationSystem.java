import java.util.*;

abstract class Question {
    protected String questionText;
    protected int marks;

    public Question(String questionText, int marks) {
        this.questionText = questionText;
        this.marks = marks;
    }

    public abstract boolean evaluate(String answer);

    public int getMarks() {
        return marks;
    }
}

class MultipleChoiceQuestion extends Question {
    private String correctAnswer;

    public MultipleChoiceQuestion(String questionText,
                                  int marks,
                                  String correctAnswer) {
        super(questionText, marks);
        this.correctAnswer = correctAnswer;
    }

    public boolean evaluate(String answer) {
        return answer.equalsIgnoreCase(correctAnswer);
    }
}

class TrueFalseQuestion extends Question {
    private boolean correctAnswer;

    public TrueFalseQuestion(String questionText,
                             int marks,
                             boolean correctAnswer) {
        super(questionText, marks);
        this.correctAnswer = correctAnswer;
    }

    public boolean evaluate(String answer) {
        return Boolean.parseBoolean(answer) == correctAnswer;
    }
}

class Attempt {
    private Student student;
    private Examination examination;
    private Map<Question, String> answers = new HashMap<>();
    private boolean submitted = false;

    public Attempt(Student student, Examination examination) {
        this.student = student;
        this.examination = examination;
    }

    public void answerQuestion(Question question, String answer) {
        if (submitted) {
            System.out.println("Cannot change answers for a submitted examination by " + student.getName() + ".");
            return;
        }

        answers.put(question, answer);
        System.out.println("Answer recorded for " + student.getName() + ".");
    }

    public void submit() {
        if (submitted) {
            System.out.println("Attempt already submitted by " + student.getName() + ".");
            return;
        }

        submitted = true;
        System.out.println("Submitting exam for " + student.getName() + ".");

        int score = 0;
        int total = 0;

        for (Question question : examination.getQuestions()) {
            total += question.getMarks();

            if (question.evaluate(answers.get(question))) {
                System.out.println("Correct (" + question.getMarks() + " points)");
                score += question.getMarks();
            } else {
                System.out.println("Incorrect (0 points)");
            }
        }

        System.out.println("Total score for " + student.getName() + ": " + score + "/" + total);
    }
}

class Student {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Examination {
    private String name;
    private List<Question> questions = new ArrayList<>();

    public Examination(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public List<Question> getQuestions() {
        return questions;
    }
}

public class OnlineExaminationSystem {
    public static void main(String[] args) {

        Student student = new Student("Student 1");

        Examination exam = new Examination("Exam A");

        Question q1 =
                new MultipleChoiceQuestion(
                        "Which is a programming language?",
                        5,
                        "C");

        Question q2 =
                new TrueFalseQuestion(
                        "Java is an OOP language.",
                        5,
                        true);

        exam.addQuestion(q1);
        exam.addQuestion(q2);

        System.out.println("Exam A started by Student 1.");

        Attempt attempt = new Attempt(student, exam);

        attempt.answerQuestion(q1, "C");
        attempt.answerQuestion(q2, "false");

        System.out.println("Exam A submitted by Student 1.");
        attempt.submit();

        attempt.answerQuestion(q1, "Java");
    }
}
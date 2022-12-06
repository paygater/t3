/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class Question {
    private String Question;
    private String AnswerA;
    private String AnswerB;
    private String AnswerC;
    private String AnswerD;
    private String AnswerOfQuestion;

    public Question() {
    }

    
    public Question(String Question, String AnswerA, String AnswerB, String AnswerC, String AnswerD, String AnswerOfQuestion) {
        this.Question = Question;
        this.AnswerA = AnswerA;
        this.AnswerB = AnswerB;
        this.AnswerC = AnswerC;
        this.AnswerD = AnswerD;
        this.AnswerOfQuestion = AnswerOfQuestion;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String Question) {
        this.Question = Question;
    }

    public String getAnswerA() {
        return AnswerA;
    }

    public void setAnswerA(String AnswerA) {
        this.AnswerA = AnswerA;
    }

    public String getAnswerB() {
        return AnswerB;
    }

    public void setAnswerB(String AnswerB) {
        this.AnswerB = AnswerB;
    }

    public String getAnswerC() {
        return AnswerC;
    }

    public void setAnswerC(String AnswerC) {
        this.AnswerC = AnswerC;
    }

    public String getAnswerD() {
        return AnswerD;
    }

    public void setAnswerD(String AnswerD) {
        this.AnswerD = AnswerD;
    }

    public String getAnswerOfQuestion() {
        return AnswerOfQuestion;
    }

    public void setAnswerOfQuestion(String AnswerOfQuestion) {
        this.AnswerOfQuestion = AnswerOfQuestion;
    }

    @Override
    public String toString() {
        return "Question{" + "Question=" + Question + ", AnswerA=" + AnswerA + ", AnswerB=" + AnswerB + ", AnswerC=" + AnswerC + ", AnswerD=" + AnswerD + ", AnswerOfQuestion=" + AnswerOfQuestion + '}';
    }
    
    
}

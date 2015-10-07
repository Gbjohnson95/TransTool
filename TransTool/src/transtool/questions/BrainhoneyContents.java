/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transtool.questions;

import java.util.ArrayList;

/**
 *
 * @author hallm8
 */
public class BrainhoneyContents {

    private ArrayList<String> rightAnswer;
    private String body;
    private ArrayList<String> qChoice;
    private String interactionType;
    private String score;
    private String partial;
    private String questionID;
    
    private String quizName;

    public BrainhoneyContents() {
    }
    
    public void printContents(){
        System.out.println("body: " + body);
        System.out.println("Interaction Type: " + interactionType);
        System.out.println("Score: " + score);
        System.out.println("Is this partial? " + partial);
        System.out.println("Question ID: " + questionID);
        System.out.println("how many choices? " + rightAnswer.size());
        System.out.println("Question Choices: " + qChoice.size());
        System.out.println(quizName);
    }
    

    public ArrayList<String> getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(ArrayList<String> rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public ArrayList<String> getqChoice() {
        return qChoice;
    }

    public void setqChoice(ArrayList<String> qChoice) {
        this.qChoice = qChoice;
    }

    public String getInteractionType() {
        return interactionType;
    }

    public void setInteractionType(String interactionType) {
        this.interactionType = interactionType;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String isPartial() {
        return partial;
    }

    public void setPartial(String partial) {
        this.partial = partial;
    }

    public String getQuestionID() {
        return questionID;
    }

    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    
}

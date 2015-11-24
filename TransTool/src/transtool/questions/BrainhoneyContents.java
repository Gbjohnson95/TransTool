/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transtool.questions;

import java.util.ArrayList;

/**
 * Brainhoney Contents:
 *   Each Brainhoney question has information that is necessary to attach.
 *  This includes: 
 *  - rightAnswer     - This is the correct answer(s), usually a number
 *  - body            - Body text for the quiz question
 *  - qChoice         - viable choices for each quiz question.  Sometimes blank.
 *  - interactionType - The type of question.
 *  - score           - Point value of the question
 *  - partial         - Is this quiz worth partial points, or full points?
 *  - questionID      - The Brainhoney ID for each question.  This is necessary
 *                      in order to find the link from each quiz.
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

    /*
     * Default constructor.  Creates new, and that's about it.
    */
    public BrainhoneyContents() {
        rightAnswer = new ArrayList<>();
        qChoice     = new ArrayList<>();
    }
    
    /***********
     * PRINT CONTENTS
     *  prints out the contents of this class.  Made for debugging purposes.
     */
    public void printContents(){
        System.out.println("body: " + body);
        System.out.println("Interaction Type: " + interactionType);
        System.out.println("Score: " + score);
        System.out.println("Is this partial? " + partial);
        System.out.println("Question ID: " + questionID);
        System.out.println("how many choices? " + rightAnswer.size());
        System.out.println("Question Choices: " + qChoice.size());
    }
    
    /**
     * RIGHT ANSWER
     *  The correct answer to the question 
     * @return Right Answer
     */
    public ArrayList<String> getRightAnswer() {
        return rightAnswer;
    }
    
    /**
     * RIGHT ANSWER
     *  The correct answer to the question 
     * 
     * @param rightAnswer - Takes in Right Answer and changes value.
     */
    public void setRightAnswer(ArrayList<String> rightAnswer) {
        this.rightAnswer = rightAnswer;
    }


    
    /**
     * GET BODY
     * The body text for the question.  
     * 
     * @return Body
     */
    public String getBody() {
        return body;
    }

    /**
     * GET BODY
     * The body text for the question.  
     * 
     * 
     * @param body - Takes in body and changes value
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * GET QUESTION CHOICE
     *   An array list that is filled with possible answers
     * 
     * @return Question Choice
     */
    public ArrayList<String> getqChoice() {
        return qChoice;
    }

    /**
     * GET QUESTION CHOICE
     *   An array list that is filled with possible answers
     * 
     * @param qChoice - Question Choice
     */
    public void setqChoice(ArrayList<String> qChoice) {
        this.qChoice = qChoice;
    }

    /**
     * INTERACTION TYPE
     *  What kind of quiz question is this?
     * 
     * @return Interaction Type
     */
    public String getInteractionType() {
        return interactionType;
    }

    /**
     * INTERACTION TYPE
     *  What kind of quiz question is this?
     * 
     * @param interactionType  - Interaction Type
     */
    public void setInteractionType(String interactionType) {
        this.interactionType = interactionType;
    }

    /**
     * SCORE
     *  
     *  Returns the point value of the quiz.
     * 
     * @return Score
     */
    public String getScore() {
        return score;
    }

    /**
     * SCORE
     * 
     *  Returns the point value of the quiz.
     * 
     * @param score - Sets score
     */
    public void setScore(String score) {
        this.score = score;
    }

    /**
     * IS PARTIAL
     * 
     * A string, stating true or false, if the question is worth partial or 
     * full credit.  By partial, it means that some questions are worth a 
     * percentage of the full point value.
     * 
     * @return String is partial?
     */
    public String isPartial() {
        return partial;
    }

    /**
     * IS PARTIAL
     * 
     * A string, stating true or false, if the question is worth partial or 
     * full credit.  By partial, it means that some questions are worth a 
     * percentage of the full point value.
     * 
     *  
     * @param partial - is Partial?
     */
    public void setPartial(String partial) {
        this.partial = partial;
    }

    /**
     * QUESTION ID
     * 
     *  Question ID is used for association with a quiz in Brainhoney
     * 
     * @return Question ID
     */
    public String getQuestionID() {
        return questionID;
    }

    /**
     * QUESTION ID
     * 
     *  Question ID is used for association with a quiz in Brainhoney
     * 
     * @param questionID - Question ID
     */
    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }
}

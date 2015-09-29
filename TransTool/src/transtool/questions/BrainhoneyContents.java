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

    public BrainhoneyContents() {
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

}

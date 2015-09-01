/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transtool.quiz;

import transtool.xmlTools.*;

/**
 *
 * @author gbjohnson
 */
public class quiz {

    xmlWriter doc;

    public quiz() {
        newAssesment("demo");
        newSection("Main");
        newQuestion("Question1", "Question Text");
        addQuestionResponce("ResponceA", false);
        addQuestionResponce("ResponceB", true);
        endQuestion();
        endSection();
        endAssesment();
    }

    public int count = 0;

    private String ident(String prefix, String suffix) {
        String str = prefix + count + suffix;
        return str;
    }

    private void newAssesment(String title) {
        
    }

    private void endAssesment() {

    }

    private void newSection(String title) {

    }

    private void endSection() {

    }

    private void newQuestion(String questionTitle, String question) {

    }

    private void endQuestion() {

    }

    private void addQuestionResponce(String response, boolean isAnswer) {

    }

    //private void addFeedback(String feedback) {
    //}
}

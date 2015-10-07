/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transtool.xmlTools;

import java.util.ArrayList;
import transtool.questions.BrainhoneyContents;

/**
 *
 * @author hallm8
 * 
 * 
 */
public class Quiz {
    private String quizName;
    private ArrayList<String> quizQuestions;
    private ArrayList<BrainhoneyContents> brainhoney;

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    public ArrayList<String> getQuizQuestions() {
        return quizQuestions;
    }

    public void setQuizQuestions(ArrayList<String> quizQuestions) {
        this.quizQuestions = quizQuestions;
    }

    public ArrayList<BrainhoneyContents> getBrainhoney() {
        return brainhoney;
    }

    public void setBrainhoney(ArrayList<BrainhoneyContents> brainhoney) {
        this.brainhoney = brainhoney;
    }  
}

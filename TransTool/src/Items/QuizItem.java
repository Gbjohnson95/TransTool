/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Items;

import org.w3c.dom.Element;

/**
 *
 * @author hallm8
 */
public class QuizItem extends Item{
    private String category;
    private String gradeable;
    private String weight;
    private String attemptLimit;
    private String securityLevel;
    private String password;
    
    private Element quizItem;
    
    @Override
    public void writeItem() {
        
        
        
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getGradeable() {
        return gradeable;
    }

    public void setGradeable(String gradeable) {
        this.gradeable = gradeable;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getAttemptLimit() {
        return attemptLimit;
    }

    public void setAttemptLimit(String attemptLimit) {
        this.attemptLimit = attemptLimit;
    }

    public String getSecurityLevel() {
        return securityLevel;
    }

    public void setSecurityLevel(String securityLevel) {
        this.securityLevel = securityLevel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Element getQuizItem() {
        return quizItem;
    }

    public void setQuizItem(Element quizItem) {
        this.quizItem = quizItem;
    }
    
    
    public void populateClass () {
        if (quizItem != null){
            
        }
        else
            System.out.println("Object not initialized!  Please initialize!");
    }
    
    
    
}

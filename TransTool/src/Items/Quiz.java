/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Items;

/**
 *
 * @author hallm8
 */
public class Quiz extends Item{
    private String category;
    private String gradeable;
    private String weight;
    private String attemptLimit;
    private String securityLevel;
    private String password;
    
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
    
    
}

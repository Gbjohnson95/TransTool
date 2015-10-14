/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GradeItems;

/**
 *
 * @author hallm8
 */
public class GradeCategories {
    String catID;
    String catName;
    String catWeight;
    String dropLowest;
    

    
    public void outputAll(){
        System.out.println("Category ID: " + catID);
        System.out.println("Category Weight: " + catWeight);
        System.out.println("Category Name: " + catName);
    }
    /**
     * 
     * @return 
     */
    public String getCatID() {
        return catID;
    }

    /**
     * 
     * @param catID 
     */
    public void setCatID(String catID) {
        this.catID = catID;
    }

    /**
     * 
     * @return 
     */
    public String getCatName() {
        return catName;
    }

    /**
     * 
     * @param catName 
     */
    public void setCatName(String catName) {
        this.catName = catName;
    }

    /**
     * 
     * @return 
     */
    public String getCatWeight() {
        return catWeight;
    }

    /**
     * 
     * @param catWeight 
     */
    public void setCatWeight(String catWeight) {
        this.catWeight = catWeight;
    }

    /**
     * 
     * @return 
     */
    public String getDropLowest() {
        return dropLowest;
    }

    /**
     * 
     * @param dropLowest 
     */
    public void setDropLowest(String dropLowest) {
        this.dropLowest = dropLowest;
    }
    
    
}

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
public class Item {
    protected String name;
    protected String savePath;
    protected String parent;
    protected String itemID;
    protected String ident;
    protected String ref;
    protected String resourceCode;
    protected String location;
    protected String gradeAssociation;
    
    
    public void writeItem(){
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return savePath;
    }

    public void setPath(String path) {
        this.savePath = path;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public String getIdent() {
        return ident;
    }

    public void setIdent(String ident) {
        this.ident = ident;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getResourceCode() {
        return resourceCode;
    }

    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode;
    }
    
    
    
    
    //public Element getElement(){
        
        
        // Fill this out in a bit.
        
        
        
        
    //}

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getGradeAssociation() {
        return gradeAssociation;
    }

    public void setGradeAssociation(String gradeAssociation) {
        this.gradeAssociation = gradeAssociation;
    }
    
    
    
    
}

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
public class Item {

    protected String name;
    protected String savePath;
    protected String parent;
    protected String itemID;
    protected String ident;
    protected String resourceCode;
    protected String location;
    protected String gradeAssociation;
    protected String category;
    protected String itemType;
    protected String pathAndName;
    protected String materialType;
    protected String linkTarget;
    protected String href;
    protected String gradeable;
    protected String weight;
    protected String gradeItem;
    protected String bodyText;
    protected String brainhoneyPath;

    public Item() {
        bodyText = new String();
        name = new String();
    }

    /**
     *
     */
    public void writeItem() {

    }

    /**
     *
     * @return
     */
    public String getGradeable() {
        return gradeable;
    }

    /**
     *
     * @param gradeable
     */
    public void setGradeable(String gradeable) {
        this.gradeable = gradeable;
    }

    /**
     *
     * @return
     */
    public String getWeight() {
        return weight;
    }

    /**
     *
     * @param weight
     */
    public void setWeight(String weight) {
        this.weight = weight;
    }

    /**
     *
     * @return
     */
    public String getGradeItem() {
        return gradeItem;
    }

    /**
     *
     * @param gradeItem
     */
    public void setGradeItem(String gradeItem) {
        this.gradeItem = gradeItem;
    }

    /**
     *
     * @return
     */
    public String getCategory() {
        return category;
    }

    /**
     *
     * @param category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     *
     * @return
     */
    public String getItemType() {
        return itemType;
    }

    /**
     *
     * @param itemType
     */
    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    /**
     *
     * @return
     */
    public String getHref() {
        return href;
    }

    /**
     *
     * @param href
     */
    public void setHref(String href) {
        this.href = href;
    }

    /**
     *
     * @return
     */
    public String getLinkTarget() {
        return linkTarget;
    }

    public void setLinkTarget(String linkTarget) {
        this.linkTarget = linkTarget;
    }

    /**
     *
     * @return
     */
    public String getMaterialType() {
        return materialType;
    }

    /**
     *
     * @param materialType
     */
    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    /**
     *
     * @return
     */
    public String getPathAndName() {
        return pathAndName;
    }

    /**
     *
     * @param pathAndName
     */
    public void setPathAndName(String pathAndName) {
        this.pathAndName = pathAndName;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public String getPath() {
        return savePath;
    }

    /**
     *
     * @param path
     */
    public void setPath(String path) {
        this.savePath = path;
    }

    /**
     *
     * @return
     */
    public String getParent() {
        return parent;
    }

    /**
     *
     * @param parent
     */
    public void setParent(String parent) {
        this.parent = parent;
    }

    /**
     *
     * @return
     */
    public String getItemID() {
        return itemID;
    }

    /**
     *
     * @param itemID
     */
    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    /**
     *
     * @return
     */
    public String getSavePath() {
        return savePath;
    }

    /**
     *
     * @param savePath
     */
    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    /**
     *
     * @return
     */
    public String getIdent() {
        return ident;
    }

    /**
     *
     * @param ident
     */
    public void setIdent(String ident) {
        this.ident = ident;
    }

    /**
     *
     * @return
     */
    public String getResourceCode() {
        return resourceCode;
    }

    /**
     *
     * @param resourceCode
     */
    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode;
    }

    /**
     *
     * @return
     */
    public String getLocation() {
        return location;
    }

    /**
     *
     * @param location
     */
    public void setLocation(String location) {
        this.location = new String();
        for (int i = 0; i < location.length(); i++) {
            if (location.charAt(i) == '/') {
                this.location += "\\";
            } else {
                this.location += location.charAt(i);
            }
        }
    }

    /**
     *
     * @return
     */
    public String getGradeAssociation() {
        return gradeAssociation;
    }

    /**
     *
     * @param gradeAssociation
     */
    public void setGradeAssociation(String gradeAssociation) {
        this.gradeAssociation = gradeAssociation;
    }

    public String getBodyText() {
        return bodyText;
    }

    public void setBodyText(String bodyText) {
        this.bodyText = bodyText;
    }

    public String getBrainhoneyPath() {
        return brainhoneyPath;
    }

    public void setBrainhoneyPath(String brainhoneyPath) {
        this.brainhoneyPath = brainhoneyPath;
    }

}
